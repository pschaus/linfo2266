package localsearch.minils.engine.invariants;



import localsearch.minils.engine.core.AbstractInvariant;
import localsearch.minils.engine.core.IntVarLS;

import java.util.ArrayList;

public class SumInvariant extends AbstractInvariant {

    IntVarLS[] x;
    IntVarLS y;


    public SumInvariant(IntVarLS [] x, IntVarLS y) {
        super(y.getSolver());
        this.x = x;
        this.y = y;
        int tot = 0;
        for (int i = 0; i < x.length; i++) {
            x[i].register(this,i);
            tot += x[i].value();
        }
        y.setValue(tot);
        y.setDependent();
    }

    @Override
    public void update(IntVarLS var, int idx, int oldValue) {
        y.setValue(y.value()-oldValue+var.value());
    }
}
