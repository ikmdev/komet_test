package dev.ikm.komet_test.kview.klfields.integerfield;

import dev.ikm.komet_test.framework.observable.ObservableField;
import dev.ikm.komet_test.framework.view.ObservableView;
import dev.ikm.komet_test.layout.component.version.field.KlField;
import dev.ikm.komet_test.layout.component.version.field.KlFieldFactory;
import dev.ikm.komet_test.layout.component.version.field.KlIntegerField;

public class KlIntegerFieldFactory implements KlFieldFactory<Integer> {

    @Override
    public KlField<Integer> create(ObservableField<Integer> observableField, ObservableView observableView, boolean editable) {
        return new DefaultKlIntegerField(observableField, observableView, editable);
    }

    @Override
    public Class<? extends KlField<Integer>> getFieldInterface() {
        return KlIntegerField.class;
    }

    @Override
    public Class<? extends KlField<Integer>> getFieldImplementation() {
        return DefaultKlIntegerField.class;
    }

    @Override
    public String getName() {
        return "Integer Field Factory";
    }

    @Override
    public String getDescription() {
        return "An Integer field";
    }
}