package dev.ikm.komet_test.kview.mvvm.view.pattern;

import dev.ikm.komet_test.framework.events.EvtBusFactory;
import dev.ikm.komet_test.kview.events.pattern.PropertyPanelEvent;
import dev.ikm.komet_test.kview.events.pattern.ShowPatternFormInBumpOutEvent;
import dev.ikm.komet_test.kview.mvvm.viewmodel.PatternPropertiesViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.carlfx.cognitive.loader.InjectViewModel;

import java.util.UUID;

import static dev.ikm.komet_test.kview.events.pattern.PropertyPanelEvent.CLOSE_PANEL;
import static dev.ikm.komet_test.kview.events.pattern.ShowPatternFormInBumpOutEvent.SHOW_ADD_FIELDS;
import static dev.ikm.komet_test.kview.mvvm.viewmodel.PatternFieldsViewModel.TOTAL_EXISTING_FIELDS;
import static dev.ikm.komet_test.kview.mvvm.viewmodel.PatternViewModel.PATTERN_TOPIC;

public class ContinueAddFieldsController {

    @FXML
    private Label confirmationTitle;

    @FXML
    private Button addFieldButton;

    @FXML
    private Button closePanelButton;

    @InjectViewModel
    private PatternPropertiesViewModel patternPropertiesViewModel;

    /**
     * when adding a field, the user is directed to a confirmation screen
     * to add more fields
     * @param actionEvent
     */
    @FXML
    private void addField(ActionEvent actionEvent) {
        actionEvent.consume();
        int totalExistingFields = patternPropertiesViewModel.getPropertyValue(TOTAL_EXISTING_FIELDS);
        EvtBusFactory.getDefaultEvtBus().publish(getPatternTopic(),
                new ShowPatternFormInBumpOutEvent(actionEvent.getSource(), SHOW_ADD_FIELDS, totalExistingFields));
    }

    /**
     * close properties panel
     * @param actionEvent
     */
    @FXML
    private void closeProperties(ActionEvent actionEvent) {
        actionEvent.consume();
        EvtBusFactory.getDefaultEvtBus().publish(getPatternTopic(),
                new PropertyPanelEvent(actionEvent.getSource(), CLOSE_PANEL));
    }

    private UUID getPatternTopic() {
        return patternPropertiesViewModel.getPropertyValue(PATTERN_TOPIC);
    }

}
