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
package dev.ikm.komet_test.search;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import dev.ikm.komet_test.framework.KometNode;
import dev.ikm.komet_test.framework.KometNodeFactory;
import dev.ikm.komet_test.framework.activity.ActivityStream;
import dev.ikm.komet_test.framework.activity.ActivityStreamOption;
import dev.ikm.komet_test.framework.activity.ActivityStreams;
import dev.ikm.komet_test.framework.preferences.Reconstructor;
import dev.ikm.komet_test.framework.view.ObservableViewNoOverride;
import dev.ikm.komet_test.preferences.KometPreferences;
import dev.ikm.tinkar.common.id.PublicIdStringKey;

public class SearchNodeFactory implements KometNodeFactory {
    protected static final String STYLE_ID = SearchNode.STYLE_ID;
    protected static final String TITLE = SearchNode.TITLE;
    
    public static SearchNodeFactory provider() {
		return new SearchNodeFactory();
	}

	public SearchNodeFactory() {
		super();
	}

    @Override
    public void addDefaultNodePreferences(KometPreferences nodePreferences) {

    }

    @Override
    public SearchNode create(ObservableViewNoOverride windowView, KometPreferences nodePreferences) {
        return reconstructor(windowView, nodePreferences);
    }

    @Reconstructor
    public static SearchNode reconstructor(ObservableViewNoOverride windowView, KometPreferences nodePreferences) {
        return new SearchNode(windowView.makeOverridableViewProperties(), nodePreferences);
    }

    @Override
    public Class<? extends KometNode> kometNodeClass() {
        return SearchNode.class;
    }

    @Override
    public ImmutableList<PublicIdStringKey<ActivityStream>> defaultActivityStreamChoices() {
        return Lists.immutable.of(ActivityStreams.SEARCH);
    }

    @Override
    public ImmutableList<PublicIdStringKey<ActivityStreamOption>> defaultOptionsForActivityStream(PublicIdStringKey<ActivityStream> streamKey) {
        if (defaultActivityStreamChoices().contains(streamKey)) {
            return Lists.immutable.of(ActivityStreamOption.PUBLISH.keyForOption());
        }
        return Lists.immutable.empty();
    }

    @Override
    public String getMenuText() {
        return TITLE;
    }

    @Override
    public String getStyleId() {
        return STYLE_ID;
    }
}