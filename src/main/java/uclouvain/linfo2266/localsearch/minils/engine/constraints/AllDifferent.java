package uclouvain.linfo2266.localsearch.minils.engine.constraints;

import uclouvain.linfo2266.localsearch.minils.Factory;
import uclouvain.linfo2266.localsearch.minils.engine.core.AbstractInvariant;
import uclouvain.linfo2266.localsearch.minils.engine.core.Constraint;
import uclouvain.linfo2266.localsearch.minils.engine.core.IntVarLS;

import java.util.Set;
import java.util.TreeMap;

public class AllDifferent extends AbstractInvariant implements Constraint {

    final IntVarLS[] x;

    IntVarLS violation;
    TreeMap<Integer,Integer> counters;
    Set<IntVarLS> scope;

    public AllDifferent(IntVarLS [] x) {
        super(x[0].getSolver());
        scope = Set.of(x);
        this.x = x;
        this.violation = Factory.makeIntVar(ls);
        counters = new TreeMap<>();
        for (int i = 0; i < x.length; i++) {
            x[i].register(this,i);

            if (counters.containsKey(x[i].value())) {
                counters.put(x[i].value(),counters.get(x[i].value())+1);
            } else {
                counters.put(x[i].value(),1);
            }
            violation.setValue(x.length - counters.size());

            violation.setDependent();
        }
    }

    @Override
    public int violation(IntVarLS x) {
        if (scope.contains(x)) return  counters.get(x.value())-1;
        else return 0;
    }

    @Override
    public IntVarLS violation() {
        // n - nombre de valeurs différentes
        return violation;
    }

    @Override
    public void update(IntVarLS x, int idx, int oldValue) {
        counters.put(oldValue,counters.get(oldValue)-1);
        if (counters.get(oldValue) == 0) {
            counters.remove(oldValue);
        }
        if (counters.containsKey(x.value())) {
            counters.put(x.value(),counters.get(x.value())+1);
        } else {
            counters.put(x.value(),1);
        }
        violation.setValue(this.x.length - counters.size());
    }
}
