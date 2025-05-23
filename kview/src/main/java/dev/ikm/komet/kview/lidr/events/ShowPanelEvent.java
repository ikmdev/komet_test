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
package dev.ikm.komet_test.kview.lidr.events;

import dev.ikm.komet_test.framework.events.Evt;
import dev.ikm.komet_test.framework.events.EvtType;

public class ShowPanelEvent extends Evt {

    public static final EvtType<ShowPanelEvent> SHOW_ADD_DEVICE = new EvtType<>(Evt.ANY, "SHOW_ADD_DEVICE");
    public static final EvtType<ShowPanelEvent> SHOW_ADD_ANALYTE_GROUP = new EvtType<>(Evt.ANY, "SHOW_ADD_ANALYTE_GROUP");
    public static final EvtType<ShowPanelEvent> SHOW_MANUAL_ADD_RESULTS = new EvtType<>(Evt.ANY, "SHOW_MANUAL_ADD_RESULTS");

    /**
     * Constructs a prototypical Event.
     *
     * @param source         the object on which the Event initially occurred
     * @param eventType
     */
    public ShowPanelEvent(Object source, EvtType eventType) {
        super(source, eventType);
    }
}
