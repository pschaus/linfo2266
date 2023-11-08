package uclouvain.linfo2266.localsearch.minils.engine.core;



public class IntVarViewOffsetLS extends IntVarLSImpl {

    final int offset;

    public IntVarViewOffsetLS(IntVarLS x, int offset) {
        super(x.getSolver());
        this.offset = offset;
        x.register(new Invariant() {
            @Override
            public void update(IntVarLS x, int idx, int oldValue) {
                setValue(x.value()+offset);
            }
        }, 0);
    }
}
