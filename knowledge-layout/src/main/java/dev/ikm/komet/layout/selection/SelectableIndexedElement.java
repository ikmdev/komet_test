package dev.ikm.komet_test.layout.selection;

import dev.ikm.komet_test.layout.selection.element.FieldDefinitionSelection;
import dev.ikm.komet_test.layout.selection.element.FieldValueSelection;

public sealed interface SelectableIndexedElement extends SelectableElement
    permits FieldDefinitionSelection, FieldValueSelection {
    int index();
}
