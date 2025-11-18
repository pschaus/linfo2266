package uclouvain.linfo2266.localsearch.minils.examples;

import uclouvain.linfo2266.localsearch.minils.engine.constraints.ConstraintSystem;
import uclouvain.linfo2266.localsearch.minils.engine.core.Constraint;
import uclouvain.linfo2266.localsearch.minils.engine.core.IntVarLS;
import uclouvain.linfo2266.localsearch.minils.engine.core.SolverLS;
import uclouvain.linfo2266.localsearch.minils.engine.constraints.AllDifferent;
import uclouvain.linfo2266.localsearch.minils.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sudoku {

    public static void main(String[] args) {

        int[] instance1 = new int[]{
                0, 0, 0, 1, 0, 0, 0, 0, 0,
                0, 6, 0, 0, 7, 3, 0, 0, 4,
                0, 0, 8, 4, 0, 0, 6, 3, 0,
                8, 0, 0, 6, 0, 0, 0, 9, 0,
                0, 3, 0, 0, 0, 0, 0, 5, 0,
                0, 4, 0, 0, 0, 7, 0, 0, 2,
                0, 7, 5, 0, 0, 4, 1, 0, 0,
                3, 0, 0, 9, 5, 0, 0, 7, 0,
                0, 0, 0, 0, 0, 6, 0, 0, 0};


        int[] instance2 = new int[]{
                0, 0, 0, 2, 0, 0, 0, 6, 3,
                3, 0, 0, 0, 0, 5, 4, 0, 1,
                0, 0, 1, 0, 0, 3, 9, 8, 0,
                0, 0, 0, 0, 0, 0, 0, 9, 0,
                0, 0, 0, 5, 3, 8, 0, 0, 0,
                0, 3, 0, 0, 0, 0, 0, 0, 0,
                0, 2, 6, 3, 0, 0, 5, 0, 0,
                5, 0, 3, 7, 0, 0, 0, 0, 8,
                4, 7, 0, 0, 0, 1, 0, 0, 0};

        int[] instance3 = new int[]{
                5, 0, 0, 0, 0, 8, 0, 7, 0,
                0, 0, 3, 4, 7, 6, 1, 5, 0,
                1, 0, 0, 0, 0, 0, 3, 0, 8,
                0, 6, 5, 0, 4, 2, 0, 0, 1,
                0, 8, 0, 5, 6, 1, 0, 4, 0,
                4, 0, 0, 8, 3, 0, 5, 9, 0,
                6, 0, 7, 0, 0, 0, 0, 0, 5,
                0, 5, 1, 6, 8, 4, 7, 0, 0,
                0, 4, 0, 7, 0, 0, 0, 0, 9};

        int[] problem = instance1;

        Sudoku sudoku = new Sudoku(problem);
        sudoku.solve();

    }

    class Pair {
        final int a;
        final int b;
        int iter;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static class Permutation {

        int [] value;
        int [] index;

        Permutation(int n) {
            value = new int[n];
            index = new int[n];
            for (int i = 0; i < n; i++) {
                value[i] = i;
                index[i] = i;
            }
        }

        public void set(int i, int v) {
            value[index[v]] = value[i];
            index[value[i]] = index[v];
            value[i] = v;
            index[v] = i;
        }

        public int get(int i) {
            return value[i];
        }
    }

    @FunctionalInterface
    interface
    SudokuObserver {
        void update(int iteration, int i1,int j1, int i2, int j2);
    }

    IntVarLS[] grid;
    IntVarLS violation;
    ConstraintSystem constraintSystem;
    ArrayList<Pair> possibleSwaps;

    private List<SudokuObserver> observers = new ArrayList<SudokuObserver>();
    Random rand = new Random(0);

    Sudoku(int [] problem) {
        // initialization satisfying hint positions
        int [] init = new int[81];
        for (int i = 0; i < 9; i++) {
            Permutation p = new Permutation(9); // permutation of 0 .. 8 on the line
            for (int j = 0; j < 9; j++) {
                if (problem[i*9+j] > 0) { // hint position detected
                    p.set(j,problem[i*9+j]-1);
                }
            }
            for (int j = 0; j < 9; j++) {
                init[i*9+j] = p.get(j)+1;
            }
        }

        List<List<Integer>> blocks = new ArrayList<>();
        // the indices for each 3x3 blocks
        for (int gi = 0; gi < 3; gi++) {
            for (int gj = 0; gj < 3; gj++) {
                List<Integer> blockIndices = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        blockIndices.add((gi*3+i)*9+(gj*3+j));
                    }
                }
                blocks.add(blockIndices);
            }
        }


        SolverLS ls = Factory.makeSolver();

        // init is an array with the hint position correctly placed
        // and other positions arbitrarily placed but with a correct
        // permutation on each line
        grid = Factory.makeIntVarArray(9*9, i -> Factory.makeIntVar(ls,init[i]));

        printGrid();

        ArrayList<Constraint> constraints = new ArrayList<>();
        for (int k = 0; k < 9; k++) {
            final int i = k;
            Constraint allDiffCol = new AllDifferent(Factory.makeIntVarArray(9, j -> grid[j * 9 + i]));
            Constraint allDiffBlock = new AllDifferent(Factory.makeIntVarArray(9, j -> grid[blocks.get(i).get(j)]));
            constraints.add(allDiffBlock);
            constraints.add(allDiffCol);
        }

        constraintSystem = new ConstraintSystem(constraints.toArray(new Constraint[]{}));
        violation = constraintSystem.violation();

        // swap two cells on the same line
        possibleSwaps = new ArrayList<>();
        for (int l = 0; l < 9; l++) {
            for (int i = 0; i < 9; i++) {
                for (int j = i+1; j < 9; j++) {
                    int v1 = l*9+i;
                    int v2 = l*9+j;
                    if (problem[v1] == 0 && problem[v2] == 0) {
                        possibleSwaps.add(new Pair(v1,v2));
                    }
                }
            }
        }
    }


    public void attach(SudokuObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(int iter, Pair swap){
        for (SudokuObserver observer : observers) {
            observer.update(iter,swap.a/9,swap.a%9,swap.b/9, swap.b%9);
        }
    }

    public void printGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i*9+j].value()+" ");
            }
            System.out.println();
        }
    }

    public int[][] violations() {
        int [][] violations = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                violations[i][j] = constraintSystem.violation(grid[i*9+j]);
            }
        }
        return violations;
    }

    public void solve() {
        int iter = 1;
        int tabu = 20;
        while (constraintSystem.violation().value() > 0) {
            if (iter > 100000) break;
            iterationTabu(iter++,tabu);
            iterationGreedy(iter++);
            //System.out.println(violation.value());
        }
    }

    public void iterationGreedy(int iter) {
        Pair bestSwap = bestSwap();
        grid[bestSwap.a].swap(grid[bestSwap.b]);
        notifyAllObservers(iter,bestSwap);
    }


    public Pair bestSwap() {
        Pair bestSwap = null;
        int bestDelta = Integer.MAX_VALUE;
        for (Pair p : possibleSwaps) {
            int delta = violation.getSwapDelta(grid[p.a],grid[p.b]);
            if (delta < bestDelta) {
                bestDelta = delta;
                bestSwap = p;
            }
        }
        return bestSwap;
    }



    public void iterationTabu(int iter, int tabu) {
        Pair bestSwap = bestSwapNonTabu(iter);
        grid[bestSwap.a].swap(grid[bestSwap.b]);
        bestSwap.iter = iter + rand.nextInt(tabu);
        notifyAllObservers(iter,bestSwap);
    }


    public Pair bestSwapNonTabu(int iter) {
        Pair bestSwap = null;
        int bestDelta = Integer.MAX_VALUE;
        for (Pair p : possibleSwaps) {
            if (p.iter < iter) {
                int delta = violation.getSwapDelta(grid[p.a],grid[p.b]);
                if (delta < bestDelta) {
                    bestDelta = delta;
                    bestSwap = p;
                }
            }
        }
        return  bestSwap;
    }

}
