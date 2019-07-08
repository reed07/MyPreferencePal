package io.opencensus.trace;

import io.opencensus.internal.Utils;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class BlankSpan extends Span {
    public static final BlankSpan INSTANCE = new BlankSpan();

    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
    }

    public String toString() {
        return "BlankSpan";
    }

    private BlankSpan() {
        super(SpanContext.INVALID, null);
    }

    public void addMessageEvent(MessageEvent messageEvent) {
        Utils.checkNotNull(messageEvent, "messageEvent");
    }

    public void end(EndSpanOptions endSpanOptions) {
        Utils.checkNotNull(endSpanOptions, "options");
    }
}
