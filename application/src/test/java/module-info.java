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

import dev.ikm.komet_test.framework.KometNodeFactory;
import dev.ikm.komet_test.framework.concurrent.TaskListsService;
import dev.ikm.komet_test.framework.events.DefaultEvtBus;
import dev.ikm.tinkar.common.service.DataServiceController;
import dev.ikm.tinkar.common.service.DefaultDescriptionForNidService;
import dev.ikm.tinkar.common.service.PublicIdService;
import dev.ikm.tinkar.entity.EntityService;
import dev.ikm.tinkar.entity.StampService;

module dev.ikm.komet_test.application.test {

    exports dev.ikm.komet_test.app.test;
    opens dev.ikm.komet_test.app.test;

    /* Sampler App */
    exports dev.ikm.komet_test.sampler;
    opens dev.ikm.komet_test.sampler to javafx.fxml;
    exports dev.ikm.komet_test.sampler.controllers;
    opens dev.ikm.komet_test.sampler.controllers to javafx.fxml;

    exports dev.ikm.komet_test.debouncepoc;

    // TODO Not happy that I have to specify these here... Can't dynamically add modules?
    requires dev.ikm.tinkar.provider.spinedarray;
    requires dev.ikm.tinkar.provider.mvstore;
    requires dev.ikm.tinkar.provider.ephemeral;
    // End not happy...
    requires org.carlfx.cognitive;
    requires com.pixelduke.fxcomponents;
    requires javafx.controls;
    requires javafx.fxml;
    requires nsmenufx;
    requires org.controlsfx.controls;
    requires dev.ikm.komet_test.classification;
    requires dev.ikm.komet_test.details;
    requires dev.ikm.komet_test.builder;
    requires dev.ikm.komet_test.kview;
    requires dev.ikm.komet_test.artifact;
    requires dev.ikm.komet_test.executor;
    requires dev.ikm.komet_test.list;
    requires dev.ikm.komet_test.navigator;
    requires dev.ikm.komet_test.preferences;
    requires dev.ikm.komet_test.progress;
    requires dev.ikm.komet_test.search;
    requires dev.ikm.tinkar.common;
    requires dev.ikm.tinkar.entity;
    requires dev.ikm.tinkar.provider.entity;
    requires dev.ikm.tinkar.provider.search;
    requires dev.ikm.tinkar.terms;
    requires org.kordamp.ikonli.javafx;
    requires jdk.jdwp.agent;
    requires transitive dev.ikm.komet_test.rules;
    requires org.junit.jupiter.api;
    requires dev.ikm.tinkar.composer;

    requires dev.ikm.komet_test.application;
    requires org.testfx.core;
    requires org.testfx.junit5;
    requires org.testfx.monocle;
    requires org.junit.jupiter;

    exports dev.ikm.komet_test.app.test.integration.testfx;
    opens dev.ikm.komet_test.app.test.integration.testfx;

    uses dev.ikm.tinkar.common.service.DataServiceController;
    uses dev.ikm.tinkar.common.service.DefaultDescriptionForNidService;
    uses dev.ikm.tinkar.entity.EntityService;
    uses dev.ikm.komet_test.framework.KometNodeFactory;
    uses dev.ikm.tinkar.common.service.PublicIdService;
    uses dev.ikm.tinkar.entity.StampService;
    uses dev.ikm.komet_test.framework.concurrent.TaskListsService;
    uses dev.ikm.komet_test.framework.events.DefaultEvtBus;

    // For ScenicView...
    //requires org.scenicview.scenicview;
}