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
package dev.ikm.komet_test.reasoner;

import dev.ikm.tinkar.terms.ConceptFacade;

import java.util.Optional;

public class StringWithOptionalConceptFacade {
    final String label;
    final ConceptFacade conceptFacade;

    public StringWithOptionalConceptFacade(String label, ConceptFacade conceptFacade) {
        this.label = label;
        this.conceptFacade = conceptFacade;
    }
    public StringWithOptionalConceptFacade(String label) {
        this.label = label;
        this.conceptFacade = null;
    }

    public String getLabel() {
        return label;
    }

    public Optional<ConceptFacade> getOptionalConceptSpecification() {
        return Optional.ofNullable(conceptFacade);
    }

    @Override
    public String toString() {
        return label;
    }
}