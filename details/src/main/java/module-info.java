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
import dev.ikm.komet_test.details.DetailsNodeFactory;
import dev.ikm.komet_test.framework.KometNodeFactory;

module dev.ikm.komet_test.details {

    requires transitive dev.ikm.komet_test.framework;

    opens dev.ikm.komet_test.details;
    exports dev.ikm.komet_test.details;
    exports dev.ikm.komet_test.details.concept;

    provides KometNodeFactory
        with DetailsNodeFactory; //, ConceptDetailsNodeFactory; // IKM-544 hide concept detail node from option
}