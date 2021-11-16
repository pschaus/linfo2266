package localsearch.minils;


import localsearch.minils.engine.core.*;
import localsearch.minils.engine.invariants.SumInvariant;

import java.util.function.Function;

public class Factory {

    private Factory() {
        throw new UnsupportedOperationException();
    }

    public static SolverLS makeSolver() {
        return new MiniLS();
    }

    public static IntVarLS makeIntVar(SolverLS ls) {
        return new IntVarLSImpl(ls);
    }

    public static IntVarLS makeIntVar(SolverLS ls, int initVal) {
        IntVarLSImpl x = new IntVarLSImpl(ls);
        x.setValue(initVal);
        return x;
    }

    public static IntVarLS[] makeIntVarArray(SolverLS ls, int n, int domSize) {
        IntVarLS[] res = new IntVarLS[n];
        for (int i = 0; i < res.length; i++) {
            res[i] = new IntVarLSImpl(ls,domSize);
        }
        return res;
    }

    /**
     * Creates an array of variables with specified lambda function
     *
     * @param n the number of variables to create
     * @param body the function that given the index i in the array creates/map the corresponding {@link IntVar}
     * @return an array of n variables
     *         with variable at index <i>i</i> generated as {@code body.get(i)}
     */
    public static IntVarLS[] makeIntVarArray(int n, Function<Integer, IntVarLS> body) {
        IntVarLS[] t = new IntVarLS[n];
        for (int i = 0; i < n; i++)
            t[i] = body.apply(i);
        return t;
    }

    public static IntVarLS sum(IntVarLS ... xs) {
        IntVarLS y = new IntVarLSImpl(xs[0].getSolver(), Integer.MAX_VALUE);
        new SumInvariant(xs,y);
        return y;
    }

    public static IntVarLS plus(IntVarLS x, int offset) {
        return new IntVarViewOffsetLS(x, offset);
    }




}
