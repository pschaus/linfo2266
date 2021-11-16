package localsearch.minils.engine.core;


public interface Constraint extends Invariant {

    public void update(IntVarLS x, int idx, int oldValue);
    public IntVarLS violation();
    public int violation(IntVarLS x);

}
