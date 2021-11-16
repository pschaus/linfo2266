package localsearch.minils.engine.constraints;

import localsearch.minils.Factory;
import localsearch.minils.engine.core.AbstractInvariant;
import localsearch.minils.engine.core.Constraint;
import localsearch.minils.engine.core.IntVarLS;
import localsearch.minils.engine.invariants.SumInvariant;

import java.util.TreeMap;

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
