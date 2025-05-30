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
package dev.ikm.komet_test.rules.actions.axiom;

import dev.ikm.komet_test.framework.panel.axiom.AxiomSubjectRecord;
import dev.ikm.tinkar.coordinate.edit.EditCoordinate;
import dev.ikm.tinkar.coordinate.edit.EditCoordinateRecord;
import dev.ikm.tinkar.coordinate.view.calculator.ViewCalculator;
import dev.ikm.tinkar.entity.graph.DiTreeEntity;
import javafx.event.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveAxiomAction extends AbstractAxiomAction {
    private static final Logger LOG = LoggerFactory.getLogger(RemoveAxiomAction.class);

    public RemoveAxiomAction(String text, AxiomSubjectRecord axiomSubjectRecord,
                             ViewCalculator viewCalculator, EditCoordinate editCoordinate) {
        super(text, axiomSubjectRecord, viewCalculator, editCoordinate);
    }

    @Override
    public void doAction(ActionEvent t, AxiomSubjectRecord axiomSubjectRecord, EditCoordinateRecord editCoordinate) {
        DiTreeEntity.Builder treeBuilder = axiomSubjectRecord.axiomTree().removeVertex(axiomSubjectRecord.axiomIndex());
        DiTreeEntity newTree = treeBuilder.build();
        putUpdatedDiTree(axiomSubjectRecord, editCoordinate, newTree);
    }
}
