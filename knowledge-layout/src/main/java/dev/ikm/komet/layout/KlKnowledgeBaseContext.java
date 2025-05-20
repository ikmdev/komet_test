package dev.ikm.komet_test.layout;

import dev.ikm.komet_test.layout.context.KlContext;
import dev.ikm.komet_test.layout.context.KlContextProvider;

public non-sealed interface KlKnowledgeBaseContext extends KlObject, KlContextProvider {
    KlContext context();
}
