package io.opencensus.trace;

import io.opencensus.trace.export.ExportComponent;
import io.opencensus.trace.propagation.PropagationComponent;

public abstract class TraceComponent {

    private static final class NoopTraceComponent extends TraceComponent {
        private final ExportComponent noopExportComponent;

        public Tracer getTracer() {
            return Tracer.getNoopTracer();
        }

        public PropagationComponent getPropagationComponent() {
            return PropagationComponent.getNoopPropagationComponent();
        }

        private NoopTraceComponent() {
            this.noopExportComponent = ExportComponent.newNoopExportComponent();
        }
    }

    public abstract PropagationComponent getPropagationComponent();

    public abstract Tracer getTracer();

    static TraceComponent newNoopTraceComponent() {
        return new NoopTraceComponent();
    }
}
