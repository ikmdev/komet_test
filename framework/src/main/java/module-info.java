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
import dev.ikm.komet_test.framework.dnd.DragRegistry;
import dev.ikm.komet_test.framework.events.DefaultEvtBus;
import dev.ikm.komet_test.framework.rulebase.RuleService;
import dev.ikm.komet_test.preferences.PreferencesService;
import dev.ikm.tinkar.common.service.CachingService;

open module dev.ikm.komet_test.framework {
    exports dev.ikm.komet_test.framework.activity;
    exports dev.ikm.komet_test.framework.alerts;
    exports dev.ikm.komet_test.framework.annotations;
    exports dev.ikm.komet_test.framework.builder;
    exports dev.ikm.komet_test.framework.concurrent;
    exports dev.ikm.komet_test.framework.context;
    exports dev.ikm.komet_test.framework.controls;
    exports dev.ikm.komet_test.framework.dnd;
    exports dev.ikm.komet_test.framework.docbook;
    exports dev.ikm.komet_test.framework.graphics;
    exports dev.ikm.komet_test.framework.observable;
    exports dev.ikm.komet_test.framework.panel;
    exports dev.ikm.komet_test.framework.performance.impl;
    exports dev.ikm.komet_test.framework.performance;
    exports dev.ikm.komet_test.framework.preferences;
    exports dev.ikm.komet_test.framework.progress;
    exports dev.ikm.komet_test.framework.propsheet.editor to org.controlsfx.controls, dev.ikm.komet_test.list, dev.ikm.komet_test.application;
    exports dev.ikm.komet_test.framework.propsheet;
    exports dev.ikm.komet_test.framework.rulebase;
    exports dev.ikm.komet_test.framework.search;
    exports dev.ikm.komet_test.framework.temp;
    exports dev.ikm.komet_test.framework.uncertain;
    exports dev.ikm.komet_test.framework.view;
    exports dev.ikm.komet_test.framework;
    exports dev.ikm.komet_test.framework.window;
    exports dev.ikm.komet_test.framework.tabs;
    exports dev.ikm.komet_test.framework.panel.axiom;
    exports dev.ikm.komet_test.framework.events;
    exports dev.ikm.komet_test.framework.events.appevents;

    provides CachingService with dev.ikm.komet_test.framework.dnd.DragRegistry.CacheProvider;
    requires io.github.classgraph;
    requires dev.ikm.tinkar.collection;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.fontawesome;
    requires org.kordamp.ikonli.foundation;
    requires org.kordamp.ikonli.icomoon;
    requires org.kordamp.ikonli.ionicons4;
    requires org.kordamp.ikonli.mapicons;
    requires org.kordamp.ikonli.material2;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.kordamp.ikonli.materialdesign;
    requires org.kordamp.ikonli.octicons;
    requires org.kordamp.ikonli.runestroicons;
    requires org.kordamp.ikonli.unicons;
    requires org.carlfx.cognitive;
    requires static java.compiler;
    requires static dev.ikm.jpms.recordbuilder.core;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.swing;
    requires transitive org.controlsfx.controls;
    requires transitive dev.ikm.komet_test.preferences;
    requires transitive dev.ikm.komet_test.terms;
    requires transitive dev.ikm.tinkar.common;
    requires transitive dev.ikm.tinkar.coordinate;
    requires transitive dev.ikm.tinkar.entity;
    requires transitive dev.ikm.tinkar.terms;
    requires transitive dev.ikm.jpms.eclipse.collections;
    requires transitive dev.ikm.jpms.eclipse.collections.api;
    requires transitive org.kordamp.ikonli.javafx;
    requires transitive org.slf4j;
    requires transitive dev.ikm.tinkar.ext.lang.owl; // Owl expression builder

    uses dev.ikm.komet_test.framework.concurrent.TaskListsService;
    uses dev.ikm.komet_test.preferences.PreferencesService;
    uses dev.ikm.komet_test.framework.KometNodeFactory;
    uses dev.ikm.tinkar.common.alert.AlertReportingService;
    uses dev.ikm.komet_test.framework.rulebase.RuleService;


    provides dev.ikm.komet_test.framework.events.EvtBus
            with dev.ikm.komet_test.framework.events.DefaultEvtBus;

    uses dev.ikm.komet_test.framework.events.EvtBus;
}
