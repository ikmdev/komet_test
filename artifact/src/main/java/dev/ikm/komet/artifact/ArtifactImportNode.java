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
package dev.ikm.komet_test.artifact;

import dev.ikm.komet_test.framework.ExplorationNodeAbstract;
import dev.ikm.komet_test.framework.view.ViewProperties;
import dev.ikm.komet_test.preferences.KometPreferences;
import dev.ikm.tinkar.terms.EntityFacade;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.eclipse.collections.api.list.ImmutableList;

import java.io.IOException;

public class ArtifactImportNode extends ExplorationNodeAbstract {
    protected static final String STYLE_ID = "import-node";
    protected static final String TITLE = "Import Artifact";

    public ArtifactImportNode(ViewProperties viewProperties, KometPreferences nodePreferences) {
        super(viewProperties, nodePreferences);
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
        // No additional preferences.
    }

    @Override
    public String getStyleId() {
        return STYLE_ID;
    }

    @Override
    protected void saveAdditionalPreferences() {
        // No additional fields.
    }

    @Override
    public Node getNode() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ArtifactImport.fxml"));
        try {
            Pane pane = loader.load();
            return pane;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }

    @Override
    public boolean canClose() {
        return false;
    }

    @Override
    public Class<ArtifactImportNodeFactory> factoryClass() {
        return ArtifactImportNodeFactory.class;
    }
}
