package dev.ikm.komet_test.layout.orchestration;

import dev.ikm.komet_test.layout.event.KlRequestEvent;
import org.eclipse.collections.api.list.ImmutableList;

public interface KlEventDispatcher {
    /**
     * Dispatches a list of KlRequestEvents to their recipients.
     *
     * @param klRequestEvents The list of KlRequestEvents to be dispatched.
     */
    void dispatch(ImmutableList<KlRequestEvent> klRequestEvents);
}
