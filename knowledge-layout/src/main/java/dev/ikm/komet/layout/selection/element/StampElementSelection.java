package dev.ikm.komet_test.layout.selection.element;

import dev.ikm.komet_test.layout.selection.SelectableElement;
import dev.ikm.komet_test.layout.selection.VersionSelection;
import dev.ikm.tinkar.terms.ConceptFacade;

public record StampElementSelection(VersionSelection.StampElement stampElement) implements SelectableElement {

    @Override
    public ConceptFacade conceptForEnum() {
        return stampElement.conceptForEnum();
    }
}
