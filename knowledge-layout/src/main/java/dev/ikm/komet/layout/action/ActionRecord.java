package dev.ikm.komet_test.layout.action;

import dev.ikm.tinkar.terms.ConceptFacade;

import java.util.UUID;
/**
 dev.ikm.komet_test.framework.observable.ObservableField;
 dev.ikm.komet_test.framework.observable.ObservableFieldDefinition;

 dev.ikm.tinkar.coordinate.stamp.change.FieldChangeRecord
 dev.ikm.tinkar.coordinate.stamp.change.VersionChangeRecord
 dev.ikm.tinkar.coordinate.stamp.change.ChangeChronology

 */

public record ActionRecord(UUID actionId, ConceptFacade topic) {
}
