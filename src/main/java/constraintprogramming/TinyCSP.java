package constraintprogramming;

import java.util.*;

public class TinyCSP {

    static int nRecur = 0;

    List<Constraint> constraints = new LinkedList<>();
    List<Variable> variables = new LinkedList<>();

    public Variable makeVariable(int size) {
        Variable x = new Variable(size);
        variables.add(x);
        return  x;
    }

    public void notEqual(Variable x, Variable y, int offset) {
        constraints.add(new NotEqual(x,y,offset));
    }

    public void fixPoint() {
        boolean fix = false;
        while (!fix) {
            fix = true;
            for (Constraint c: constraints) {
                fix &= !c.propagate();
            }
        }
    }

    private ArrayList<Domain> backupDomains() {
        ArrayList<Domain> backup = new ArrayList<>();
        for (Variable x: variables) {
            backup.add(x.dom.clone());
        }
        return backup;
    }

    private void restoreDomains(ArrayList<Domain> backup) {
        for (int i = 0 ; i < variables.size(); i++) {
            variables.get(i).dom = backup.get(i);
        }
    }

    Optional<Variable> firstNotFixed() {
        return variables.stream().filter(x -> !x.dom.isFixed()).findFirst();
    }

    Optional<Variable> smallestNotFixed() {
        int min = Integer.MAX_VALUE;
        Variable y = null;
        for (Variable x : variables) {
            if (!x.dom.isFixed() && x.dom.size() < min) {
                y = x;
                min = y.dom.size();
            }
        }
        return y == null ? Optional.empty() : Optional.of(y);
    }

    public void dfs(ArrayList<int[]> solutions) {

        nRecur += 1;

        // pickup a variable that is not yet fixed if any
        Optional<Variable> notFixed = firstNotFixed();
        //Optional<Variable> notFixed = smallestNotFixed();
        if (!notFixed.isPresent()) { // all variables fixed, a solution is found
            int [] sol = variables.stream().mapToInt(x -> x.dom.min()).toArray();
            solutions.add(sol);
        }
        else {
            Variable y = notFixed.get(); // take the unfixed variable
            int v = y.dom.min();
            ArrayList<Domain> backup = backupDomains();

            // left branch x = v
            try {
                y.dom.fix(v);
                fixPoint();
                dfs(solutions);
            } catch (Inconsistency i) {}

            restoreDomains(backup);

            // right branch x != v
            try {
                y.dom.remove(v);
                fixPoint();
                dfs(solutions);
            } catch (Inconsistency i) {}
        }
    }


    static class Inconsistency extends RuntimeException {

    }

    class Domain {

        private BitSet values;

        /**
         * Initializes a domain with {0, ... ,n-1}
         * @param n
         */
        public Domain(int n) {
            values = new BitSet(n);
            values.set(0,n);
        }

        private Domain(BitSet dom) {
            this.values = dom;
        }

        /**
         * Verifies if only one value left
         * @return true if onnly one value left
         */
        public boolean isFixed() {
            return size() == 1;
        }

        /**
         * Gets the domain size
         * @return the domain size
         */
        public int size() {
            return values.cardinality();
        }

        /**
         * Gets the minimum of the domain
         * @return the minimum of the domain
         */
        public int min() {
            return values.nextSetBit(0);
        }

        /**
         * Removes value v
         * @param v
         * @return if the value was present before
         */
        public boolean remove(int v) {
            if (0 <= v && v < values.length()) {
                if (values.get(v)) {
                    values.clear(v);
                    if (size() == 0) throw new Inconsistency();
                    return true;
                }
            }
            return false;
        }

        /**
         * Fixes the domain to value v
         * @param v a value that is in the domain
         */
        public void fix(int v) {
            if (!values.get(v)) throw new Inconsistency();
            values.clear();
            values.set(v);
        }

        @Override
        public Domain clone() {
            return new Domain((BitSet) values.clone());
        }

    }

    class Variable {

        Domain dom;

        public Variable(int n) {
            dom = new Domain(n);
        }
    }

    abstract class Constraint {
        /**
         * Propagate the constraint and return true if any value could be removed
         * @return true if at least one value of one variable could be removed
         */
        abstract boolean propagate();
    }

    // x != y + offset
    class NotEqual extends Constraint {

        Variable x,y;
        int offset;

         public NotEqual(Variable x, Variable y, int offset) {
            this.x = x;
            this.y = y;
            this.offset = offset;
         }

        public NotEqual(Variable x, Variable y) {
            this(x,y,0);
        }

        @Override
        boolean propagate() {
            if (x.dom.isFixed()) {
                return y.dom.remove(x.dom.min() - offset);
            }
            if (y.dom.isFixed()) {
                return x.dom.remove(y.dom.min() + offset);
            }
            return  false;
        }
    }

}
