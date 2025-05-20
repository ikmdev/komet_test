package dev.ikm.komet_test.layout.event;

import dev.ikm.komet_test.layout.component.KlComponentArea;
import dev.ikm.komet_test.layout.selection.Selection;

/**
 * Represents a request event within the Kl framework. This event carries
 * necessary information including the recipient component that handles
 * the event.
 */
public interface KlRequestEvent extends KlEvent {
    /**
     * Retrieves the recipient of the KlRequestEvent.
     *
     * @return the recipient component associated with this event.
     */
    KlComponentArea recipient();

    /**
     * Retrieves the selection upon which the event's requested action is targeted.
     *
     * @return a Selection instance for the requested event
     */
    Selection requestSelection();
}
