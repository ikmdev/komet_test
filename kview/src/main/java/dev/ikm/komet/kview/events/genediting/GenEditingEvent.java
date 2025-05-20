package dev.ikm.komet_test.kview.events.genediting;

import dev.ikm.komet_test.framework.events.Evt;
import dev.ikm.komet_test.framework.events.EvtType;
import dev.ikm.komet_test.framework.observable.ObservableField;

import java.util.List;

public class GenEditingEvent extends Evt {

    public static final EvtType<GenEditingEvent> PUBLISH = new EvtType<>(Evt.ANY, "GEN_EDIT_PUBLISH_SEMANTIC");

    public static final EvtType<GenEditingEvent> CONFIRM_REFERENCE_COMPONENT = new EvtType<>(Evt.ANY, "CONFIRM_REFERENCE_COMPONENT");

    public static final EvtType<GenEditingEvent> VERSION_UPDATED = new EvtType<>(Evt.ANY, "VERSION_UPDATED");

    private List<Object> list;

    private int nid;

    public GenEditingEvent(Object source,EvtType eventType, List<Object> list, int nid){
        super(source,eventType);
        this.list = list;
        this.nid = nid;
    }

    public GenEditingEvent(Object source, EvtType<GenEditingEvent> eventType) {
        super(source,eventType);
    }

    public List<?> getList(){
        return list;
    }

    public int getNid() {
        return nid;
    }
}
