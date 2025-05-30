package dev.ikm.komet_test.kview.klfields.stringfield;

import dev.ikm.komet_test.framework.observable.ObservableField;
import dev.ikm.komet_test.framework.view.ObservableView;
import dev.ikm.komet_test.kview.controls.KLReadOnlyDataTypeControl;
import dev.ikm.komet_test.kview.controls.KLStringControl;
import dev.ikm.komet_test.kview.klfields.BaseDefaultKlField;
import dev.ikm.komet_test.layout.component.version.field.KlStringField;
import javafx.scene.Parent;

public class DefaultKlStringField extends BaseDefaultKlField<String> implements KlStringField {

    public DefaultKlStringField(ObservableField<String> observableStringField, ObservableView observableView, boolean isEditable) {
        super(observableStringField, observableView, isEditable);

        Parent node;
        if (isEditable) {
            KLStringControl stringControl = new KLStringControl();

            stringControl.textProperty().bindBidirectional(observableStringField.valueProperty());
            stringControl.setTitle(getTitle());

            node = stringControl;
        } else {
            KLReadOnlyDataTypeControl<String> readOnlyStringControl = new KLReadOnlyDataTypeControl<>(String.class);

            readOnlyStringControl.valueProperty().bindBidirectional(observableStringField.valueProperty());
            readOnlyStringControl.setTitle(getTitle());

            node = readOnlyStringControl;
        }

        setKlWidget(node);
    }
}