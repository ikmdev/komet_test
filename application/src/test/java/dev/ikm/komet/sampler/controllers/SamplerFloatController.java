package dev.ikm.komet_test.sampler.controllers;

import dev.ikm.komet_test.kview.controls.KLFloatControl;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SamplerFloatController {
    @FXML
    private KLFloatControl floatControl;

    @FXML
    private Label floatControlText;

    @FXML
    private Label samplerDescription;

    public void initialize()
    {
        samplerDescription.setText("Float Control that's used to edit KLFloatField");

        floatControlText.textProperty().bind(new ObjectBinding<>() {
            {
                super.bind(floatControl.valueProperty());
            }

            @Override
            protected String computeValue() {
                return String.valueOf(floatControl.getValue());
            }
        });
    }
}