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
package dev.ikm.komet_test.progress;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.controlsfx.control.TaskProgressView;
import org.eclipse.collections.api.list.ImmutableList;
import dev.ikm.komet_test.framework.ExplorationNodeAbstract;
import dev.ikm.komet_test.framework.concurrent.TaskListsService;
import dev.ikm.komet_test.framework.view.ViewProperties;
import dev.ikm.komet_test.preferences.KometPreferences;
import dev.ikm.tinkar.terms.EntityFacade;
import org.kordamp.ikonli.javafx.FontIcon;

public class ProgressNode extends ExplorationNodeAbstract {
    protected static final String STYLE_ID = "activity-node";
    protected static final String TITLE = "Activity";

    final Node activityGraphic = getTitleGraphic();
    final RotateTransition rotation = new RotateTransition(Duration.seconds(1.5), activityGraphic);
    TaskProgressView<Task<?>> progressView = new TaskProgressView<>();
    TaskListsService taskLists = TaskListsService.get();

    {
        rotation.setCycleCount(Animation.INDEFINITE);
        rotation.setByAngle(360);
        rotation.setInterpolator(Interpolator.LINEAR);
        activityGraphic.setTranslateZ(activityGraphic.getBoundsInLocal().getWidth() / 2.0);
        activityGraphic.setRotationAxis(Rotate.Z_AXIS);
    }

    {
        progressView.setRetainTasks(true);
        ProgressViewSkin skin = new ProgressViewSkin<>(progressView);
        progressView.setSkin(skin);
        Bindings.bindContent(progressView.getTasks(), taskLists.executingTasks());
        progressView.getTasks().addListener(this::listInvalidated);
        if (progressView.getTasks().isEmpty()) {
            rotation.stop();
        } else {
            rotation.play();
        }
    }

    public ProgressNode(ViewProperties viewProperties, KometPreferences nodePreferences) {
        super(viewProperties, nodePreferences);
    }

    public ProgressNode() {
        super();
    }

    private static Node getTitleGraphic() {
        FontIcon icon = new FontIcon();
        icon.setIconLiteral("icm-spinner9:12:white");
        icon.setId("activity-node");
        return icon;
    }

    private void listInvalidated(Observable list) {
        if (progressView.getTasks().isEmpty()) {
            rotation.stop();
        } else {
            rotation.play();
        }
    }

    @Override
    public String getDefaultTitle() {
        return TITLE;
    }

    @Override
    public void handleActivity(ImmutableList<EntityFacade> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void revertAdditionalPreferences() {

    }

    @Override
    public String getStyleId() {
        return STYLE_ID;
    }

    @Override
    public Node getTitleNode() {
        return activityGraphic;
    }

    @Override
    protected void saveAdditionalPreferences() {

    }

    @Override
    public Node getNode() {
        return progressView;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean canClose() {
        return false;
    }

    @Override
    public Class factoryClass() {
        return ProgressNodeFactory.class;
    }
}
