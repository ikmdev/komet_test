package dev.ikm.komet_test.sampler.controllers;

import dev.ikm.komet_test.kview.controls.KLImageControl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SampleImageController {

    @FXML
    private KLImageControl imageControl;

    @FXML
    private Label samplerDescription;

    public void initialize() {
        samplerDescription.setText("Image Control used to edit images.");
    }
}