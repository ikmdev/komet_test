import dev.ikm.komet_test.kview.klwindows.EntityKlWindowFactory;

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
module dev.ikm.komet_test.kview {
    requires transitive dev.ikm.komet_test.framework;
    requires dev.ikm.komet_test.search;
    requires dev.ikm.tinkar.provider.search;
    requires dev.ikm.komet_test.navigator;
    requires dev.ikm.komet_test.classification;
    requires dev.ikm.komet_test.progress;
    requires org.carlfx.cognitive;
    requires org.carlfx.axonic;
    requires dev.ikm.tinkar.composer;

    // JPro related modules
    requires jpro.webapi;
    requires one.jpro.platform.auth.core;
    requires one.jpro.platform.file;

    requires transitive dev.ikm.komet_test.layout;
    requires jdk.jfr;
    requires org.apache.commons.logging;

    exports dev.ikm.komet_test.kview.state;
    exports dev.ikm.komet_test.kview.state.pattern;

    opens dev.ikm.komet_test.kview.mvvm.view.details to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.details;

    opens dev.ikm.komet_test.kview.mvvm.view.properties to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.properties;

    opens dev.ikm.komet_test.kview.mvvm.view.timeline to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.timeline;

    opens dev.ikm.komet_test.kview.mvvm.view.journal to javafx.fxml, org.carlfx.cognitive, dev.ikm.komet_test.application;
    exports dev.ikm.komet_test.kview.mvvm.view.journal;

    opens dev.ikm.komet_test.kview.mvvm.view.landingpage to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.landingpage;

    opens dev.ikm.komet_test.kview.mvvm.view.search to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.search;

    opens dev.ikm.komet_test.kview.mvvm.view.reasoner to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.reasoner;

    opens dev.ikm.komet_test.kview.fxutils.window to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.fxutils.window;

    opens dev.ikm.komet_test.kview.mvvm.view.common to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.common;

    exports dev.ikm.komet_test.kview.mvvm.model;
    exports dev.ikm.komet_test.kview.fxutils;

    opens dev.ikm.komet_test.kview.mvvm.model to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.events;

    exports dev.ikm.komet_test.kview.mvvm.view.stamp;
    opens dev.ikm.komet_test.kview.mvvm.view.stamp to javafx.fxml, org.carlfx.cognitive;

    exports dev.ikm.komet_test.kview.mvvm.viewmodel;

    opens dev.ikm.komet_test.kview.lidr.mvvm.viewmodel to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.lidr.mvvm.viewmodel;

    opens dev.ikm.komet_test.kview.lidr.mvvm.model;
    exports dev.ikm.komet_test.kview.lidr.mvvm.model;
    opens dev.ikm.komet_test.kview.data.schema;
    exports dev.ikm.komet_test.kview.data.schema;
    opens dev.ikm.komet_test.kview.data.persistence;
    exports dev.ikm.komet_test.kview.data.persistence;

    opens dev.ikm.komet_test.kview.lidr.mvvm.view.details to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.lidr.mvvm.view.details;
    opens dev.ikm.komet_test.kview.lidr.mvvm.view.properties to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.lidr.mvvm.view.properties;
    opens dev.ikm.komet_test.kview.lidr.mvvm.view.device to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.lidr.mvvm.view.device;
    opens dev.ikm.komet_test.kview.lidr.mvvm.view.analyte to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.lidr.mvvm.view.analyte;
    opens dev.ikm.komet_test.kview.lidr.mvvm.view.results to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.lidr.mvvm.view.results;
    opens dev.ikm.komet_test.kview.mvvm.view.progress to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.progress;

    // General editing
    opens dev.ikm.komet_test.kview.mvvm.view.genediting to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.mvvm.view.genediting;
    exports dev.ikm.komet_test.kview.events.genediting;

    // TODO a temporary export screen for next gen ui.
    opens dev.ikm.komet_test.kview.mvvm.view;
    exports dev.ikm.komet_test.kview.mvvm.view;

    opens dev.ikm.komet_test.kview.mvvm.view.changeset;
    exports dev.ikm.komet_test.kview.mvvm.view.changeset;

    opens dev.ikm.komet_test.kview.mvvm.view.login;
    exports dev.ikm.komet_test.kview.mvvm.view.login;

    exports dev.ikm.komet_test.kview.mvvm.view.descriptionname;
    opens dev.ikm.komet_test.kview.mvvm.view.descriptionname to javafx.fxml, org.carlfx.cognitive;

    exports dev.ikm.komet_test.kview.mvvm.view.pattern;
    opens dev.ikm.komet_test.kview.mvvm.view.pattern to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.events.pattern;

    exports dev.ikm.komet_test.kview.mvvm.view.navigation;
    opens dev.ikm.komet_test.kview.mvvm.view.navigation to javafx.fxml, org.carlfx.cognitive;

    exports dev.ikm.komet_test.kview.controls;
    opens dev.ikm.komet_test.kview.controls;
    opens dev.ikm.komet_test.kview.controls.skin to javafx.controls;
    opens dev.ikm.komet_test.kview.klfields.readonly to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.klfields.readonly;

    exports dev.ikm.komet_test.kview;
    opens dev.ikm.komet_test.kview;
    exports dev.ikm.komet_test.kview.klwindows.genediting;
    opens dev.ikm.komet_test.kview.klwindows.genediting to javafx.fxml, org.carlfx.cognitive;
    exports dev.ikm.komet_test.kview.klwindows;
    opens dev.ikm.komet_test.kview.klwindows to javafx.fxml, org.carlfx.cognitive;
    opens dev.ikm.komet_test.kview.mvvm.viewmodel to dev.ikm.komet_test.application, javafx.fxml, org.carlfx.cognitive;

    provides dev.ikm.komet_test.framework.KometNodeFactory with dev.ikm.komet_test.kview.mvvm.view.details.DetailsNodeFactory, dev.ikm.komet_test.kview.mvvm.view.properties.PropertiesNodeFactory;

    provides EntityKlWindowFactory with
            dev.ikm.komet_test.kview.klwindows.concept.ConceptKlWindowFactory,
            dev.ikm.komet_test.kview.klwindows.pattern.PatternKlWindowFactory,
            dev.ikm.komet_test.kview.klwindows.lidr.LidrKlWindowFactory,
            dev.ikm.komet_test.kview.klwindows.genediting.GenEditingKlWindowFactory;

    uses dev.ikm.komet_test.framework.events.EvtBus;
    uses EntityKlWindowFactory;
}
