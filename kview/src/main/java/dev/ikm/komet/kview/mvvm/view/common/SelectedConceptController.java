/*
 * Copyright © 2015 Integrated Knowledge Management (support@ikm.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ikm.komet_test.kview.mvvm.view.common;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SelectedConceptController {

    @FXML
    private Label conceptName;

    @FXML
    private Button closeButton;

    @FXML
    private ImageView identicon;


    public void setConceptName(String conceptNameStr) {
        this.conceptName.setText(conceptNameStr);
    }

    public void setCloseButtonAction(EventHandler<ActionEvent> closeButtonAction) {
        this.closeButton.setOnAction(closeButtonAction);
    }

    public void setIdenticon(Image identicon) {
        this.identicon.setImage(identicon);
    }
}
