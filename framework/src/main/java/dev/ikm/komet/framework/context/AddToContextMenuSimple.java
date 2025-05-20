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
package dev.ikm.komet_test.framework.context;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.controlsfx.control.PopOver;
import dev.ikm.komet_test.framework.activity.ActivityStream;
import dev.ikm.komet_test.framework.activity.ActivityStreams;
import dev.ikm.komet_test.framework.controls.EntityLabelWithDragAndDrop;
import dev.ikm.komet_test.framework.search.SearchControllerAndNode;
import dev.ikm.komet_test.framework.view.ViewProperties;
import dev.ikm.tinkar.common.alert.AlertObject;
import dev.ikm.tinkar.common.alert.AlertStreams;
import dev.ikm.tinkar.common.id.PublicIdStringKey;
import dev.ikm.tinkar.common.service.PrimitiveData;
import dev.ikm.tinkar.entity.*;
import dev.ikm.tinkar.terms.EntityFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AddToContextMenuSimple implements AddToContextMenu {
    private static final Logger LOG = LoggerFactory.getLogger(AddToContextMenuSimple.class);

    private static String recursiveEntityToString(EntityFacade entityFacade) {
        return recursiveEntityToString(entityFacade.nid());
    }

    private static String recursiveEntityToString(int nid) {
        StringBuilder sb = new StringBuilder();
        recursiveEntityToString(nid, sb);
        return sb.toString();
    }

    private static void recursiveEntityToString(int nid, StringBuilder sb) {
        Entity entity = Entity.getFast(nid);
        sb.append(entity.toString());
        sb.append("\n\n");
        for (int semanticNid : PrimitiveData.get().semanticNidsForComponent(nid)) {
            recursiveEntityToString(semanticNid, sb);
        }
    }

    @Override
    public void addToContextMenu(Control control, ContextMenu contextMenu, ViewProperties viewProperties,
                                 ObservableValue<EntityFacade> entityFocusProperty,
                                 SimpleIntegerProperty selectionIndexProperty, Runnable unlink) {
        MenuItem searchForAlternative = new MenuItem("Search for alternative concept");
        searchForAlternative.setOnAction(new SearchForConceptActionEventHandler(control, viewProperties,
                o -> {
                    if (control instanceof EntityLabelWithDragAndDrop entityLabelWithDragAndDrop) {
                        LOG.info("Double click on: " + o);
                        switch (o) {
                            case SemanticEntityVersion semanticVersion ->
                                    entityLabelWithDragAndDrop.setEntity(semanticVersion.referencedComponent());
                            case ConceptEntityVersion conceptVersion ->
                                    entityLabelWithDragAndDrop.setEntity(conceptVersion.entity());
                            default ->
                                    AlertStreams.getRoot().dispatch(AlertObject.makeWarning("Can't handle double click on class " + o.getClass(),
                                            o.toString()));
                        }
                    }
                 },
                entityFocusProperty.getValue()));
        contextMenu.getItems().add(searchForAlternative);

        for (PublicIdStringKey<ActivityStream> activityStreamKey : ActivityStreams.KEYS) {
            ObservableList<EntityFacade> streamHistory = ActivityStreams.get(activityStreamKey).getHistory();
            if (!streamHistory.isEmpty()) {
                if (control instanceof EntityLabelWithDragAndDrop entityLabelWithDragAndDrop) {
                    Menu setFromHistory = new Menu("Set from " + activityStreamKey.getString() + " history");
                    contextMenu.getItems().add(setFromHistory);
                    for (EntityFacade entityFacade : streamHistory) {
                        MenuItem historyItem = new MenuItem(viewProperties.calculator().getFullyQualifiedDescriptionTextWithFallbackOrNid(entityFacade));
                        setFromHistory.getItems().add(historyItem);
                        historyItem.setStyle("-fx-pref-width: 500");
                        historyItem.setOnAction(event -> {
                            Entity historicalEntity = Entity.getFast(entityFacade);
                            switch (historicalEntity) {
                                case SemanticEntity semanticEntity -> entityLabelWithDragAndDrop.setEntity(semanticEntity.topEnclosingComponent());
                                case ConceptEntity conceptEntity -> entityLabelWithDragAndDrop.setEntity(conceptEntity);
                                default -> AlertStreams.getRoot().dispatch(AlertObject.makeError(
                                        new IllegalStateException("Unexpected value: " + historicalEntity)));
                            }
                        });
                    }
                }
            }
        }


        MenuItem copyEntityToString = new MenuItem("Copy entity toString()");
        copyEntityToString.setOnAction(event -> {
            EntityFacade entityFacade = entityFocusProperty.getValue();
            if (entityFacade != null) {
                Entity entity = Entity.getFast(entityFacade.nid());
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(entity.toString());
                clipboard.setContent(content);
            }
        });
        contextMenu.getItems().add(copyEntityToString);

        MenuItem copyEntityToStringRecursive = new MenuItem("Copy entity toString() recursive");
        copyEntityToStringRecursive.setOnAction(event -> {
            EntityFacade entityFacade = entityFocusProperty.getValue();
            if (entityFacade != null) {
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(recursiveEntityToString(entityFacade));
                clipboard.setContent(content);
            }
        });
        contextMenu.getItems().add(copyEntityToStringRecursive);

        MenuItem copyPublicId = new MenuItem("Copy text and public id");
        copyPublicId.setOnAction(event -> {
            EntityFacade entityFacade = entityFocusProperty.getValue();
            if (entityFacade != null) {
                String text = PrimitiveData.textFast(entityFacade.nid());
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(text + ": " + entityFacade.publicId().asUuidList().toString());
                clipboard.setContent(content);
            }
        });
        contextMenu.getItems().add(copyPublicId);
    }
}
