package uclouvain.linfo2266.localsearch.minils.engine.core;


import java.util.ArrayList;
import java.util.List;

public class IntVarLSImpl implements  IntVarLS {

    static class InvariantIdx {
        Invariant inv;
        int idx;
        InvariantIdx(Invariant inv, int idx) {
            this.inv = inv;
            this.idx = idx;
        }
    }

    SolverLS ls;
    int minValue = 0;
    int maxValue = 0;

    boolean dependent;

    List<InvariantIdx> invariantList = new ArrayList<InvariantIdx>();

    int v;

    public IntVarLSImpl(SolverLS ls, int domSize) {
        this.ls = ls;
        this.minValue = 0;
        this.maxValue = domSize-1;
        this.v = minValue;
    }

    public IntVarLSImpl(SolverLS ls) {
        this.ls = ls;
        this.minValue = Integer.MIN_VALUE;
        this.maxValue = Integer.MAX_VALUE;
    }



    @Override
    public void register(Invariant inv, int idx) {
        invariantList.add(new InvariantIdx(inv,idx));
    }

    @Override
    public int value() {
        return v;
    }

    @Override
    public void setValue(int value) {
        int old = this.v;
        this.v = value;
        for (InvariantIdx invIdx: invariantList) {
            invIdx.inv.update(this,invIdx.idx,old);
        }
    }

    @Override
    public int getSwapDelta(IntVarLS x1, IntVarLS x2) {
        int oldValue = value();
        x1.swap(x2);
        int delta = value()-oldValue;
        x1.swap(x2);
        return delta;
    }

    @Override
    public void swap(IntVarLS other) {
        int tmp = value();
        setValue(other.value());
        other.setValue(tmp);
    }

    @Override
    public SolverLS getSolver() {
        return ls;
    }

    @Override
    public void setDependent() {
        dependent = true;
    }

    @Override
    public boolean isDependent() {
        return dependent;
    }
}
