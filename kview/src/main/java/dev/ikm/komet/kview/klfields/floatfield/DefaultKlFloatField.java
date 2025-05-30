package dev.ikm.komet_test.kview.klfields.floatfield;

import dev.ikm.komet_test.framework.observable.ObservableField;
import dev.ikm.komet_test.framework.view.ObservableView;
import dev.ikm.komet_test.kview.controls.KLFloatControl;
import dev.ikm.komet_test.kview.controls.KLReadOnlyDataTypeControl;
import dev.ikm.komet_test.kview.klfields.BaseDefaultKlField;
import dev.ikm.komet_test.layout.component.version.field.KlFloatField;
import javafx.scene.Parent;

public class DefaultKlFloatField extends BaseDefaultKlField<Float> implements KlFloatField {

    public DefaultKlFloatField(ObservableField<Float> observableFloatField, ObservableView observableView, boolean isEditable) {
        super(observableFloatField, observableView, isEditable);

        Parent node;
        if (isEditable) {
            KLFloatControl floatControl = new KLFloatControl();

            floatControl.valueProperty().bindBidirectional(observableFloatField.valueProperty());
            floatControl.setTitle(getTitle());

            node = floatControl;
        } else {
            KLReadOnlyDataTypeControl<Float> readOnlyStringControl = new KLReadOnlyDataTypeControl<>(Float.class);

            readOnlyStringControl.valueProperty().bindBidirectional(observableFloatField.valueProperty());
            readOnlyStringControl.setTitle(getTitle());

            node = readOnlyStringControl;
        }
        setKlWidget(node);
    }
}
