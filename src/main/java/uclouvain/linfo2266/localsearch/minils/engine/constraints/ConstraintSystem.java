package uclouvain.linfo2266.localsearch.minils.engine.constraints;

import uclouvain.linfo2266.localsearch.minils.Factory;
import uclouvain.linfo2266.localsearch.minils.engine.core.Constraint;
import uclouvain.linfo2266.localsearch.minils.engine.core.IntVarLS;

public class ConstraintSystem implements Constraint {


    IntVarLS violation;
    Constraint [] constraints;


    public ConstraintSystem(Constraint ... constraints) {
        this.constraints = constraints;
        IntVarLS [] violations = new IntVarLS[constraints.length];
        for (int i = 0; i < constraints.length; i++) {
            violations[i] = constraints[i].violation();
        }
        violation = Factory.sum(violations);
    }

    @Override
    public int violation(IntVarLS x) {
        // TODO
        int tot = 0;
        for (Constraint c: constraints) {
            tot += c.violation(x);
        }
        return  tot;
    }

    @Override
    public IntVarLS violation() {
        // n - nombre de valeurs différentes
        return violation;
    }

    public void update(IntVarLS x, int idx, int oldValue) {

    }


}
