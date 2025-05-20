package dev.ikm.komet_test.framework.observable;

public sealed interface FieldLocatorForAssociatedComponent extends FieldLocator
        permits AssociatedComponentField, AssociatedComponentFieldListElement {
    int associatedComponentNid();

    default FieldLocatorForComponent componentFieldLocator() {
        return switch (this) {
            case AssociatedComponentFieldListElement associatedComponentFieldListElement ->
                    new ComponentFieldListElementLocator(associatedComponentFieldListElement.category(),
                            associatedComponentFieldListElement.index());
            case AssociatedComponentField associatedComponentField ->
                    new ComponentFieldLocator(associatedComponentField.category());
        };
    }
}

