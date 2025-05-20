package dev.ikm.komet_test.framework.observable;

public sealed interface FieldLocatorForComponent extends FieldLocator
    permits ComponentFieldLocator, ComponentFieldListElementLocator {
}
