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
package dev.ikm.komet_test.framework.rulebase;

import java.util.NoSuchElementException;
import java.util.Optional;

import dev.ikm.tinkar.common.service.PluggableService;

public enum RuleServiceFinder {
    INSTANCE;
    final RuleService service;

    RuleServiceFinder() {
        Optional<RuleService> optionalService = PluggableService.load(RuleService.class).findFirst();
        if (optionalService.isPresent()) {
            this.service = optionalService.get();
        } else {
            throw new NoSuchElementException("No " + RuleService.class.getName() +
                    " found by PluggableService...");
        }
    }

    public RuleService get() {
        return service;
    }
}
