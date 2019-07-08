package io.opencensus.trace.export;

public abstract class ExportComponent {

    private static final class NoopExportComponent extends ExportComponent {
        private final SampledSpanStore noopSampledSpanStore;

        private NoopExportComponent() {
            this.noopSampledSpanStore = SampledSpanStore.newNoopSampledSpanStore();
        }
    }

    public static ExportComponent newNoopExportComponent() {
        return new NoopExportComponent();
    }
}
