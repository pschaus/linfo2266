package localsearch.minils.engine.core;

public class UpdateEvent {

    Invariant inv;
    int idx;
    int oldValue;

    public UpdateEvent(Invariant inv, int idx, int oldValue) {
        this.inv = inv;
        this.idx = idx;
        this.oldValue = oldValue;
    }
}
