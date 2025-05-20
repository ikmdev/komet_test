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
module dev.ikm.komet_test.kview.test {

    requires org.testfx.core;
    requires org.testfx.junit5;
    requires org.testfx.monocle;
    requires org.junit.jupiter;
    requires org.mockito;
    requires dev.ikm.komet_test.framework;
    requires dev.ikm.komet_test.kview;
    requires org.carlfx.axonic;
    requires org.carlfx.cognitive;

    /**
     * tinkar dependencies
     */
    requires dev.ikm.tinkar.composer;

    exports dev.ikm.komet_test.kview.mvvm.login;
    opens dev.ikm.komet_test.kview.mvvm.login;

    /**
     * 3rd party libs
     */
    requires org.slf4j;
    requires org.junit.jupiter.engine;
    requires org.junit.jupiter.params;
    requires org.junit.jupiter.api;
    requires one.jpro.platform.auth.core;
    opens dev.ikm.komet_test.kview.state.test to org.junit.platform.commons;

    exports dev.ikm.komet_test.kview.state.test;

    uses dev.ikm.komet_test.framework.events.EvtBus;
}
