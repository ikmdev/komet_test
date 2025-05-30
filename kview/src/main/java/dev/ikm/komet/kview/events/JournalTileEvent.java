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
package dev.ikm.komet_test.kview.events;

import dev.ikm.komet_test.framework.events.Evt;
import dev.ikm.komet_test.framework.events.EvtType;
import dev.ikm.komet_test.framework.preferences.PrefX;

public class JournalTileEvent extends Evt {
    public static final EvtType<JournalTileEvent> CREATE_JOURNAL_TILE = new EvtType<>(Evt.ANY, "CREATE JOURNAL TILE");
    public static final EvtType<JournalTileEvent> UPDATE_JOURNAL_TILE = new EvtType<>(Evt.ANY, "UPDATE JOURNAL TILE");

    private PrefX journalWindowSettingsMap = PrefX.create();

    public JournalTileEvent(Object source, EvtType<? extends Evt> evtType) {
        super(source, evtType);
    }

    public JournalTileEvent(Object source, EvtType<? extends Evt> evtType,
                            PrefX journalWindowSettingsMap) {

        super(source, evtType);
        this.journalWindowSettingsMap = journalWindowSettingsMap;
    }

    public PrefX getJournalWindowSettingsMap() {
        return journalWindowSettingsMap;
    }

    public void setJournalWindowSettingsMap(PrefX journalWindowSettingsMap) {
        this.journalWindowSettingsMap = journalWindowSettingsMap;
    }
}
