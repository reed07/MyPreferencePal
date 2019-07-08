package io.opencensus.trace.propagation;

public abstract class TextFormat {
    private static final NoopTextFormat NOOP_TEXT_FORMAT = new NoopTextFormat();

    public static abstract class Getter<C> {
    }

    private static final class NoopTextFormat extends TextFormat {
        private NoopTextFormat() {
        }
    }

    public static abstract class Setter<C> {
    }
}
