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
package dev.ikm.komet_test.set;


import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import dev.ikm.komet_test.collection.CollectionNode;
import dev.ikm.komet_test.collection.CollectionType;
import dev.ikm.komet_test.framework.propsheet.editor.IntIdCollectionEditor;
import dev.ikm.komet_test.framework.propsheet.editor.IntIdSetEditor;
import dev.ikm.komet_test.framework.view.ViewProperties;
import dev.ikm.komet_test.preferences.KometPreferences;
import dev.ikm.tinkar.common.id.IntIdSet;
import dev.ikm.tinkar.common.id.IntIds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetNode extends CollectionNode<IntIdSet> {
    private static final Logger LOG = LoggerFactory.getLogger(SetNode.class);
    protected static final String TITLE = "Set Manager";

    public SetNode(ViewProperties viewProperties, KometPreferences nodePreferences) {
        super(viewProperties, nodePreferences);
        this.collectionItemsProperty.set(IntIds.set.empty());
    }

    @Override
    protected IntIdCollectionEditor<IntIdSet> getCollectionEditor(ViewProperties viewProperties,
                                                                  SimpleObjectProperty<IntIdSet> collectionItems) {
        return new IntIdSetEditor(viewProperties, collectionItems);
    }

    @Override
    protected CollectionType getCollectionType() {
        return CollectionType.SET;
    }

    public Node getMenuIconGraphic() {
        Label menuIcon = new Label("{…}");
        return menuIcon;
    }

    @Override
    public Class factoryClass() {
        return SetNodeFactory.class;
    }

    @Override
    public String getDefaultTitle() {
        return TITLE;
    }
}