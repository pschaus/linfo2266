package localsearch.minils.engine.core;



public abstract class AbstractInvariant implements Invariant {

    /**
     * The solver in which the constraint is created
     */
    public final SolverLS ls;

    public AbstractInvariant(SolverLS ls) {
        this.ls = ls;
    }

}
