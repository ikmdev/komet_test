package dev.ikm.komet_test.kview.events.genediting;

import dev.ikm.komet_test.framework.events.Evt;
import dev.ikm.komet_test.framework.events.EvtType;
import dev.ikm.komet_test.framework.view.ViewProperties;
import dev.ikm.tinkar.terms.EntityFacade;

public class MakeGenEditingWindowEvent extends Evt {

    public static final EvtType<MakeGenEditingWindowEvent> OPEN_GEN_EDIT = new EvtType<>(Evt.ANY, "OPEN_GEN_EDIT");

    public static final EvtType<MakeGenEditingWindowEvent> OPEN_GEN_AUTHORING = new EvtType<>(Evt.ANY, "OPEN_GEN_AUTHORING");

    private EntityFacade component;


    private ViewProperties viewProperties;

    /**
     * Constructs a prototypical Event.
     *
     * @param source    the object on which the Event initially occurred
     * @param eventType
     * @throws IllegalArgumentException if source is null
     */
    public MakeGenEditingWindowEvent(Object source, EvtType eventType, EntityFacade component, ViewProperties viewProperties) {
        super(source, eventType);
        this.viewProperties = viewProperties;
        this.component = component;
    }

    public EntityFacade getComponent() {
        return component;
    }

    public ViewProperties getViewProperties() {
        return viewProperties;
    }

}
