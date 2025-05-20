package dev.ikm.komet_test.layout.window;

import dev.ikm.komet_test.layout.KlGadget;
import dev.ikm.komet_test.layout.KlStateCommands;
import dev.ikm.komet_test.layout.context.KlContext;
import dev.ikm.komet_test.layout.context.KlContextProvider;
import dev.ikm.komet_test.layout.context.KnowledgeBaseContext;
import javafx.scene.Node;

/**
 * Placeholder for now.
 */
public non-sealed interface KlJournalWindow<FX extends Node> extends KlStateCommands, KlContextProvider, KlGadget<FX> {
    @Override
    default KlContext context() {
        if (this.fxGadget() instanceof Node node) {
            return KlGadget.context(node);
        }
        return KnowledgeBaseContext.INSTANCE.context();
    }
}
