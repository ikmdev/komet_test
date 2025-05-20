package dev.ikm.komet_test.layout.orchestration.process;

import dev.ikm.komet_test.layout.event.KlPerformanceEvent;
import dev.ikm.komet_test.layout.event.KlRequestEvent;
import dev.ikm.komet_test.layout.orchestration.KlEventOrchestrator;
import org.eclipse.collections.api.list.ImmutableList;

public class MultiComponentSelect implements KlEventOrchestrator {
    @Override
    public ImmutableList<KlRequestEvent> orchestrate(KlPerformanceEvent klPerformanceEvent) {
        return null;
    }
}
