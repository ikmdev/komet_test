package dev.ikm.komet_test.kview.klfields.booleanfield;

import dev.ikm.komet_test.framework.observable.ObservableField;
import dev.ikm.komet_test.framework.view.ObservableView;
import dev.ikm.komet_test.kview.controls.KLBooleanControl;
import dev.ikm.komet_test.kview.controls.KLReadOnlyDataTypeControl;
import dev.ikm.komet_test.kview.klfields.BaseDefaultKlField;
import dev.ikm.komet_test.layout.component.version.field.KlBooleanField;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Parent;

public class DefaultKlBooleanField extends BaseDefaultKlField<Boolean> implements KlBooleanField {
    public DefaultKlBooleanField(ObservableField<Boolean> observableBooleanField, ObservableView observableView, boolean isEditable) {
        super(observableBooleanField, observableView, isEditable);

        Parent klWidget;
        if (isEditable) {
            KLBooleanControl klBooleanControl = new KLBooleanControl();

            klBooleanControl.setTitle(getTitle());
            klBooleanControl.valueProperty().bindBidirectional(observableBooleanField.valueProperty());

            klWidget = klBooleanControl;
        } else {
            KLReadOnlyDataTypeControl<Boolean> klReadOnlyBooleanControl = new KLReadOnlyDataTypeControl<>(Boolean.class);
            klReadOnlyBooleanControl.setTitle(getTitle());

            ObjectProperty<Boolean> booleanProperty = observableBooleanField.valueProperty();

            klReadOnlyBooleanControl.valueProperty().bind(booleanProperty);

            klWidget = klReadOnlyBooleanControl;
        }
        setKlWidget(klWidget);
    }
}
