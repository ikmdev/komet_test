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
package dev.ikm.komet_test.framework.uncertain;

//~--- JDK imports ------------------------------------------------------------

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import org.eclipse.collections.api.set.ImmutableSet;
import dev.ikm.komet_test.terms.KometTerm;
import dev.ikm.komet_test.framework.view.SimpleEqualityBasedListProperty;
import dev.ikm.komet_test.framework.view.SimpleEqualityBasedObjectProperty;
import dev.ikm.komet_test.framework.view.SimpleEqualityBasedSetProperty;
import dev.ikm.tinkar.coordinate.stamp.StampPath;
import dev.ikm.tinkar.coordinate.stamp.StampPathImmutable;
import dev.ikm.tinkar.coordinate.stamp.StampPositionRecord;
import dev.ikm.tinkar.terms.ConceptFacade;
import dev.ikm.tinkar.terms.EntityProxy;

//~--- non-JDK imports --------------------------------------------------------

//~--- classes ----------------------------------------------------------------

/**
 * The Class ObservableStampPathImpl.
 *
 * 
 */

public class ObservableStampPathNoOverride
        extends ObservableStampPathBase {


   //~--- constructors --------------------------------------------------------

    private ObservableStampPathNoOverride(int pathConceptNid,
                                          ImmutableSet<StampPositionRecord> origins) {
        this(StampPathImmutable.make(pathConceptNid, origins));
    }

    private ObservableStampPathNoOverride(StampPathImmutable stampPathImmutable, String coordinateName) {
        super(stampPathImmutable, coordinateName);
    }
    private ObservableStampPathNoOverride(StampPathImmutable stampPathImmutable) {
        super(stampPathImmutable, "Stamp path");
    }

    @Override
    public void setExceptOverrides(StampPathImmutable updatedCoordinate) {
        setValue(updatedCoordinate);
    }

    public static ObservableStampPathNoOverride make(StampPathImmutable stampPathImmutable) {
        return new ObservableStampPathNoOverride(stampPathImmutable);
    }

    public static ObservableStampPathNoOverride make(StampPathImmutable stampPathImmutable, String coordinateName) {
        return new ObservableStampPathNoOverride(stampPathImmutable, coordinateName);
    }

    @Override
    protected ListProperty<StampPositionRecord> makePathOriginsAsListProperty(StampPath stampPath) {
        return new SimpleEqualityBasedListProperty<StampPositionRecord>(this,
                KometTerm.PATH_ORIGINS_FOR_STAMP_PATH.toXmlFragment(),
                FXCollections.observableList(stampPath.getPathOrigins().toList()));
    }

    @Override
    protected SetProperty<StampPositionRecord> makePathOriginsProperty(StampPath stampPath) {
        return new SimpleEqualityBasedSetProperty<StampPositionRecord>(this,
                KometTerm.PATH_ORIGINS_FOR_STAMP_PATH.toXmlFragment(),
                FXCollections.observableSet(stampPath.getPathOrigins().toSet()));
    }

    @Override
    protected ObjectProperty<ConceptFacade> makePathConceptProperty(StampPath stampPath) {
        return new SimpleEqualityBasedObjectProperty<>(this,
                KometTerm.PATH_FOR_PATH_COORDINATE.toXmlFragment(),
                stampPath.pathConcept());
    }

    @Override
    public StampPathImmutable getOriginalValue() {
        return getValue();
    }


    @Override
    protected final StampPathImmutable baseCoordinateChangedListenersRemoved(ObservableValue<? extends StampPathImmutable> observable, StampPathImmutable oldValue, StampPathImmutable newValue) {
        this.pathConceptProperty().setValue(EntityProxy.Concept.make(newValue.pathConceptNid()));
        this.pathOriginsProperty().setValue(FXCollections.observableSet(newValue.getPathOrigins().toSet()));
        this.pathOriginsAsListProperty().setValue(FXCollections.observableList(newValue.getPathOrigins().toList()));
        return newValue;
    }

}

