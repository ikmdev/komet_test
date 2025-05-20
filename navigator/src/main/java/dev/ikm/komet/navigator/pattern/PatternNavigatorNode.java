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
package dev.ikm.komet_test.navigator.pattern;


import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import dev.ikm.komet_test.framework.ExplorationNodeAbstract;
import dev.ikm.komet_test.framework.view.ViewProperties;
import dev.ikm.komet_test.navigator.graph.GraphNavigatorNode;
import dev.ikm.komet_test.preferences.KometPreferences;
import dev.ikm.tinkar.entity.Entity;
import dev.ikm.tinkar.entity.EntityVersion;
import dev.ikm.tinkar.terms.EntityFacade;
import dev.ikm.tinkar.terms.EntityProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PatternNavigatorNode extends ExplorationNodeAbstract {
    private static final Logger LOG = LoggerFactory.getLogger(GraphNavigatorNode.class);
    protected static final String STYLE_ID = "pattern-navigator-node";
    protected static final String TITLE = "Pattern Navigator";
    final AnchorPane root;
    final PatternViewController controller;

    public PatternNavigatorNode(ViewProperties viewProperties, KometPreferences nodePreferences) {
        super(viewProperties, nodePreferences);
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dev/ikm/komet/navigator/PatternView.fxml"));
            this.root = loader.load();
            this.controller = loader.getController();
            this.controller.setProperties(this, viewProperties, nodePreferences);
            ObservableList<TreeItem<Object>> selectedItems = this.controller.getTreeView().getSelectionModel().getSelectedItems();
            selectedItems.addListener((ListChangeListener.Change<? extends TreeItem<Object>> c) -> {
                MutableList<EntityFacade> selectedItemList = Lists.mutable.empty();
                for (TreeItem<Object> item : c.getList()) {
                    if (item.getValue() instanceof Integer nid) {
                        selectedItemList.add(EntityProxy.make(nid));
                    } else if (item.getValue() instanceof Entity entity) {
                        selectedItemList.add(EntityProxy.make(entity.nid()));
                    } else if (item.getValue() instanceof EntityVersion entityVersion) {
                        selectedItemList.add(EntityProxy.make(entityVersion.nid()));
                    }
                }
                if (!selectedItemList.isEmpty()) {
                    dispatchActivity(selectedItemList.toImmutable());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean showActivityStreamIcon() {
        return false;
    }

    @Override
    public String getDefaultTitle() {
        return TITLE;
    }

    @Override
    public void handleActivity(ImmutableList<EntityFacade> entities) {
        // Nothing to do...
    }

    @Override
    public void revertAdditionalPreferences() {

    }

    @Override
    public String getStyleId() {
        return STYLE_ID;
    }

    @Override
    protected void saveAdditionalPreferences() {

    }

    @Override
    public Node getNode() {
        return root;
    }

    @Override
    public void close() {
        // nothing to do...
    }

    @Override
    public boolean canClose() {
        return true;
    }

    @Override
    public Class factoryClass() {
        return PatternNavigatorFactory.class;
    }
}
