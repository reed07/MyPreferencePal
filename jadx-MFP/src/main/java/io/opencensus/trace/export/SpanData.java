package io.opencensus.trace.export;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class SpanData {

    @Immutable
    public static abstract class Attributes {
        Attributes() {
        }
    }

    @Immutable
    public static abstract class Links {
        Links() {
        }
    }

    @Immutable
    public static abstract class TimedEvent<T> {
        TimedEvent() {
        }
    }

    @Immutable
    public static abstract class TimedEvents<T> {
        TimedEvents() {
        }
    }

    SpanData() {
    }
}
