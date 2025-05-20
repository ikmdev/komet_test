package dev.ikm.komet_test.layout;

import dev.ikm.komet_test.framework.observable.ObservableVersion;

public interface KlVersionType<OV extends ObservableVersion> {
    /**
     * Retrieves the class type of the observable version class that this factory creates components for.
     * Enables runtime access to the generic version class that would otherwise be erased.
     *
     * @return A {@link Class} object representing the type of the observable version (OV).
     */
    Class<OV> versionType();
}
