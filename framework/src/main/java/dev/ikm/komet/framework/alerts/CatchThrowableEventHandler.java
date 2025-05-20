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
package dev.ikm.komet_test.framework.alerts;


import dev.ikm.tinkar.common.alert.AlertStreams;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.function.Consumer;

public class CatchThrowableEventHandler implements Consumer<ActionEvent> {
    final Consumer<ActionEvent> innerEventHandler;

    public CatchThrowableEventHandler(Consumer<ActionEvent> innerEventHandler) {
        this.innerEventHandler = innerEventHandler;
    }

    @Override
    public void accept(ActionEvent actionEvent) {
        try {
            this.innerEventHandler.accept(actionEvent);
        } catch (Exception e) {
            AlertStreams.dispatchToRoot(e);
        }
    }
}
