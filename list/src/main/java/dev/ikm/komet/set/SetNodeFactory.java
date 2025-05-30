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

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import dev.ikm.komet_test.collection.CollectionNode;
import dev.ikm.komet_test.framework.KometNode;
import dev.ikm.komet_test.framework.KometNodeFactory;
import dev.ikm.komet_test.framework.activity.ActivityStream;
import dev.ikm.komet_test.framework.activity.ActivityStreamOption;
import dev.ikm.komet_test.framework.activity.ActivityStreams;
import dev.ikm.komet_test.framework.preferences.Reconstructor;
import dev.ikm.komet_test.framework.view.ObservableViewNoOverride;
import dev.ikm.komet_test.preferences.KometPreferences;
import dev.ikm.tinkar.common.id.PublicIdStringKey;
import javafx.scene.control.Label;

public class SetNodeFactory implements KometNodeFactory {
    protected static final String STYLE_ID = CollectionNode.STYLE_ID;
    protected static final String TITLE = SetNode.TITLE;
    
    public static SetNodeFactory provider() {
		return new SetNodeFactory();
	}

	private SetNodeFactory() {
		super();
	}

    @Override
    public void addDefaultNodePreferences(KometPreferences nodePreferences) {

    }

    @Override
    public SetNode create(ObservableViewNoOverride windowView, KometPreferences nodePreferences) {
        return reconstructor(windowView, nodePreferences);
    }

    @Reconstructor
    public static SetNode reconstructor(ObservableViewNoOverride windowView, KometPreferences nodePreferences) {
        return new SetNode(windowView.makeOverridableViewProperties(), nodePreferences);
    }

    @Override
    public Class<? extends KometNode> kometNodeClass() {
        return SetNode.class;
    }

    @Override
    public ImmutableList<PublicIdStringKey<ActivityStream>> defaultActivityStreamChoices() {
        return Lists.immutable.of(ActivityStreams.LIST);
    }

    @Override
    public ImmutableList<PublicIdStringKey<ActivityStreamOption>> defaultOptionsForActivityStream(PublicIdStringKey<ActivityStream> streamKey) {
        if (ActivityStreams.LIST.equals(streamKey)) {
            return Lists.immutable.of(ActivityStreamOption.PUBLISH.keyForOption());
        }
        return Lists.immutable.empty();
    }

    @Override
    public String getMenuText() {
        return TITLE;
    }

    public Label getMenuIconGraphic() {
        Label menuIcon = new Label("{…}");
        return menuIcon;
    }

    @Override
    public String getStyleId() {
        return STYLE_ID;
    }

}