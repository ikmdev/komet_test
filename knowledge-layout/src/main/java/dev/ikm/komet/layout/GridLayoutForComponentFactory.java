package dev.ikm.komet_test.layout;

import dev.ikm.komet_test.framework.observable.FieldLocator;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;

public interface GridLayoutForComponentFactory {
    ImmutableMap<FieldLocator, GridLayoutForComponent> create(ImmutableList<FieldLocator> componentFieldSpecifications);
}
