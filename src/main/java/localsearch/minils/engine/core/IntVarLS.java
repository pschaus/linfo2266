package localsearch.minils.engine.core;

public interface IntVarLS {
    public void setDependent();
    public boolean isDependent();
    int value();
    void setValue(int v);
    void register(Invariant inv, int idx);
    SolverLS getSolver();
    int getSwapDelta(IntVarLS x1, IntVarLS x2);
    public void swap(IntVarLS other);
}
