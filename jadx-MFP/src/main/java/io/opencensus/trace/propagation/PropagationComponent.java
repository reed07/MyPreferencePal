package io.opencensus.trace.propagation;

public abstract class PropagationComponent {
    private static final PropagationComponent NOOP_PROPAGATION_COMPONENT = new NoopPropagationComponent();

    private static final class NoopPropagationComponent extends PropagationComponent {
        private NoopPropagationComponent() {
        }

        public BinaryFormat getBinaryFormat() {
            return BinaryFormat.getNoopBinaryFormat();
        }
    }

    public abstract BinaryFormat getBinaryFormat();

    public static PropagationComponent getNoopPropagationComponent() {
        return NOOP_PROPAGATION_COMPONENT;
    }
}
