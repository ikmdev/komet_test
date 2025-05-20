package dev.ikm.komet_test.kview.klfields.booleanfield;

import dev.ikm.komet_test.framework.observable.ObservableField;
import dev.ikm.komet_test.framework.view.ObservableView;
import dev.ikm.komet_test.layout.component.version.field.KlBooleanField;
import dev.ikm.komet_test.layout.component.version.field.KlField;
import dev.ikm.komet_test.layout.component.version.field.KlFieldFactory;

public class KlBooleanFieldFactory implements KlFieldFactory<Boolean> {
    @Override
    public KlField<Boolean> create(ObservableField observableField, ObservableView observableView, boolean editable) {
        return new DefaultKlBooleanField(observableField, observableView, editable);
    }

    @Override
    public Class<? extends KlField<Boolean>> getFieldInterface() {
        return KlBooleanField.class;
    }

    @Override
    public Class<? extends KlField<Boolean>> getFieldImplementation() {
        return DefaultKlBooleanField.class;
    }

    @Override
    public String getName() {
        return "Boolean Field Factory";
    }

    @Override
    public String getDescription() {
        return "A Radio button group";
    }
}
