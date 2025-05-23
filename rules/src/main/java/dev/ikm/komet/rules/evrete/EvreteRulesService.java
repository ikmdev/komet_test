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
package dev.ikm.komet_test.rules.evrete;

import dev.ikm.komet_test.framework.performance.Statement;
import dev.ikm.komet_test.framework.rulebase.Consequence;
import dev.ikm.komet_test.framework.rulebase.RuleService;
import dev.ikm.komet_test.framework.view.ViewProperties;
import dev.ikm.komet_test.rules.annotated.AxiomFocusedRules;
import dev.ikm.komet_test.rules.annotated.ComponentFocusRules;
import dev.ikm.komet_test.rules.annotated.NewConceptRules;
import dev.ikm.komet_test.rules.annotated.NewPatternRules;
import dev.ikm.tinkar.common.sets.ConcurrentHashSet;
import dev.ikm.tinkar.coordinate.edit.EditCoordinate;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.evrete.Configuration;
import org.evrete.KnowledgeService;
import org.evrete.api.ActivationMode;
import org.evrete.api.Knowledge;
import org.evrete.api.StatelessSession;
import org.evrete.dsl.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class EvreteRulesService implements RuleService {
    public static final String ENV_CONSEQUENCES = "ENV_CONSEQUENCES";
    public static final String ENV_VIEW_PROPERTIES = "ENV_VIEW_PROPERTIES";
    public static final String ENV_EDIT_COORDINATE = "ENV_EDIT_COORDINATE";

    private static final Logger LOG = LoggerFactory.getLogger(EvreteRulesService.class);

    private final Knowledge knowledge;

    public EvreteRulesService() throws IOException {
        Instant t0 = Instant.now();
        Configuration conf = new Configuration();

        // Literal data requires the Java compiler and significantly increases build time.
        // Setting this option will fast-fail the engine in case of a literal condition/action.
        conf.set(Configuration.DISABLE_LITERAL_DATA, "true");

        for (Map.Entry<Object, Object> confEntry: conf.entrySet()) {
            LOG.info(confEntry.toString());
        }

        KnowledgeService service = new KnowledgeService(conf);

        this.knowledge = service.newKnowledge()
                .importRules(
                        Constants.PROVIDER_JAVA_CLASS,
                        List.of(
                                ComponentFocusRules.class,
                                NewConceptRules.class,
                                AxiomFocusedRules.class,
                                NewPatternRules.class
                        )
                );
        Instant t1 = Instant.now();

        // Log the timing. With literal conditions disabled, the cold start time
        // is expected to be in the range of tens of milliseconds.
        LOG.info("Constructed EvreteRulesService, duration: {}ms", Duration.between(t0, t1).toMillis());
    }

    @Override
    public ImmutableList<Consequence<?>> execute(String knowledgeBaseName,
                                                 ImmutableList<Statement> statements,
                                                 ViewProperties viewProperties, EditCoordinate editCoordinate) {

        StatelessSession session = this.knowledge.newStatelessSession(ActivationMode.CONTINUOUS);

        // Create a collector for resulting consequences
        ConcurrentHashSet<Consequence<?>> globalActionSet = new ConcurrentHashSet<>();

        // Set the collector and other necessary objects as session's environment variables
        session.set(ENV_CONSEQUENCES, globalActionSet);
        session.set(ENV_VIEW_PROPERTIES, viewProperties);
        session.set(ENV_EDIT_COORDINATE, editCoordinate);

        // Insert the statements
        session.insert(statements.castToList());

        session.fire((handle, object) -> {
            // Inspect memory objects
            LOG.atDebug().log("handle: " + handle + " object: " + object);
        });

        LOG.atDebug().log("Set items: " + globalActionSet.stream().toList());

        MutableList<Consequence<?>> globalActionList = Lists.mutable.ofAll(globalActionSet);
        globalActionList.sort(Comparable::compareTo);
        return globalActionList.toImmutableList();
    }
}