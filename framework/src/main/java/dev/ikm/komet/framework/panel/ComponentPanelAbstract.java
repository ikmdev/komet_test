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
package dev.ikm.komet_test.framework.panel;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import dev.ikm.komet_test.framework.StyleClasses;
import dev.ikm.komet_test.framework.observable.*;
import dev.ikm.komet_test.framework.panel.concept.ConceptPanel;
import dev.ikm.komet_test.framework.panel.pattern.PatternPanel;
import dev.ikm.komet_test.framework.panel.semantic.SemanticPanel;
import dev.ikm.komet_test.framework.view.ViewProperties;
import dev.ikm.tinkar.common.service.TinkExecutor;
import dev.ikm.tinkar.common.service.PrimitiveData;
import dev.ikm.tinkar.coordinate.view.calculator.ViewCalculator;
import dev.ikm.tinkar.entity.Entity;
import dev.ikm.tinkar.entity.SemanticEntity;
import dev.ikm.tinkar.terms.EntityFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;

public abstract class ComponentPanelAbstract {
    private static final Logger LOG = LoggerFactory.getLogger(ComponentPanelAbstract.class);
    protected final ObservableSet<Integer> referencedNids;
    protected final BorderPane componentDetailPane = new BorderPane();
    protected final VBox componentPanelBox = new VBox(8);
    protected final ViewProperties viewProperties;


    {
        this.componentDetailPane.setCenter(this.componentPanelBox);
        this.componentPanelBox.getStyleClass().add(StyleClasses.COMPONENT_PANEL.toString());
    }

    protected ComponentPanelAbstract(ViewProperties viewProperties) {
        this(viewProperties, FXCollections.observableSet(new HashSet<>()));
    }

    protected ComponentPanelAbstract(ViewProperties viewProperties, ObservableSet<Integer> referencedNids) {
        this.viewProperties = viewProperties;
        this.referencedNids = referencedNids;
    }

    public final ObservableSet<Integer> getReferencedNids() {
        return referencedNids;
    }

    public abstract <C extends EntityFacade> Optional<C> getComponent();

    public ViewCalculator calculator() {
        return viewProperties.calculator();
    }

    public VBox getComponentPanelBox() {
        return componentPanelBox;
    }

    protected void addSemanticReferences(ObservableEntitySnapshot entity, SimpleObjectProperty<EntityFacade> topEnclosingComponentProperty) {
        if (entity != null) {
            TinkExecutor.threadPool().execute(() -> {
                PrimitiveData.get().forEachSemanticNidForComponent(entity.nid(), semanticNid -> {
                    Platform.runLater(() -> referencedNids.add(semanticNid));
                    SemanticEntity semanticEntity = Entity.getFast(semanticNid);
                    if (!semanticEntity.canceled()) {
                        Platform.runLater(() -> {
                            ObservableSemanticSnapshot semanticSnapshot = (ObservableSemanticSnapshot) ObservableEntity.get(semanticEntity).getSnapshot(this.viewProperties.calculator());
                            ComponentPanelAbstract semanticPanel = makeComponentPanel(semanticSnapshot, topEnclosingComponentProperty);
                            ComponentPanelAbstract.this.componentPanelBox.getChildren().add(semanticPanel.getComponentDetailPane());
                        });
                    }
                });
            });
        }
    }

    public ComponentPanelAbstract makeComponentPanel(ObservableEntitySnapshot entitySnapshot, SimpleObjectProperty<EntityFacade> topEnclosingComponentProperty) {
        if (entitySnapshot instanceof ObservableConceptSnapshot conceptSnapshot) {
            return new ConceptPanel(conceptSnapshot, viewProperties, topEnclosingComponentProperty, referencedNids);
        } else if (entitySnapshot instanceof ObservableSemanticSnapshot semanticEntity) {
            return new SemanticPanel(semanticEntity, viewProperties, topEnclosingComponentProperty, referencedNids);
        } else if (entitySnapshot instanceof ObservablePatternSnapshot patternEntity) {
            return new PatternPanel(patternEntity, viewProperties, topEnclosingComponentProperty, referencedNids);
        } else {
            throw new IllegalStateException("Can't handle: " + entitySnapshot);
        }
    }

    public Node getComponentDetailPane() {
        return componentDetailPane;
    }

}
