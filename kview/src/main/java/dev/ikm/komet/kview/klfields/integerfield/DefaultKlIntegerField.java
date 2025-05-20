package dev.ikm.komet_test.kview.klfields.integerfield;

import dev.ikm.komet_test.framework.observable.ObservableField;
import dev.ikm.komet_test.framework.view.ObservableView;
import dev.ikm.komet_test.kview.controls.KLIntegerControl;
import dev.ikm.komet_test.kview.controls.KLReadOnlyDataTypeControl;
import dev.ikm.komet_test.kview.klfields.BaseDefaultKlField;
import dev.ikm.komet_test.layout.component.version.field.KlIntegerField;
import javafx.scene.Parent;

public class DefaultKlIntegerField extends BaseDefaultKlField<Integer> implements KlIntegerField {

    public DefaultKlIntegerField(ObservableField<Integer> observableIntegerField, ObservableView observableView, boolean isEditable) {
        super(observableIntegerField, observableView, isEditable);

        Parent node;
        if (isEditable) {
            KLIntegerControl integerControl = new KLIntegerControl();
            integerControl.valueProperty().bindBidirectional(observableIntegerField.valueProperty());
            integerControl.setTitle(getTitle());
            node = integerControl;
        } else {
            KLReadOnlyDataTypeControl<Integer> readOnlyIntegerControl = new KLReadOnlyDataTypeControl<>(Integer.class);
            readOnlyIntegerControl.valueProperty().bindBidirectional(observableIntegerField.valueProperty());
            readOnlyIntegerControl.setTitle(getTitle());
            node = readOnlyIntegerControl;
        }
        setKlWidget(node);
    }
}