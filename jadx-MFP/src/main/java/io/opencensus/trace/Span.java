package io.opencensus.trace;

import io.opencensus.internal.Utils;
import io.opencensus.trace.internal.BaseMessageEventUtils;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public abstract class Span {
    private static final Set<Options> DEFAULT_OPTIONS = Collections.unmodifiableSet(EnumSet.noneOf(Options.class));
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.emptyMap();
    private final SpanContext context;
    private final Set<Options> options;

    public enum Kind {
        SERVER,
        CLIENT
    }

    public enum Options {
        RECORD_EVENTS
    }

    public abstract void end(EndSpanOptions endSpanOptions);

    protected Span(SpanContext spanContext, @Nullable EnumSet<Options> enumSet) {
        Set<Options> set;
        this.context = (SpanContext) Utils.checkNotNull(spanContext, "context");
        if (enumSet == null) {
            set = DEFAULT_OPTIONS;
        } else {
            set = Collections.unmodifiableSet(EnumSet.copyOf(enumSet));
        }
        this.options = set;
        Utils.checkArgument(!spanContext.getTraceOptions().isSampled() || this.options.contains(Options.RECORD_EVENTS), "Span is sampled, but does not have RECORD_EVENTS set.");
    }

    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
        addMessageEvent(BaseMessageEventUtils.asMessageEvent(networkEvent));
    }

    public void addMessageEvent(MessageEvent messageEvent) {
        Utils.checkNotNull(messageEvent, "messageEvent");
        addNetworkEvent(BaseMessageEventUtils.asNetworkEvent(messageEvent));
    }

    public final SpanContext getContext() {
        return this.context;
    }
}
