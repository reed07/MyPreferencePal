package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import io.grpc.ChannelLogger;
import io.grpc.ChannelLogger.ChannelLogLevel;
import io.grpc.InternalChannelz.ChannelTrace.Event.Builder;
import io.grpc.InternalChannelz.ChannelTrace.Event.Severity;
import java.text.MessageFormat;
import java.util.logging.Level;

final class ChannelLoggerImpl extends ChannelLogger {
    private final TimeProvider time;
    private final ChannelTracer tracer;

    ChannelLoggerImpl(ChannelTracer channelTracer, TimeProvider timeProvider) {
        this.tracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer, "tracer");
        this.time = (TimeProvider) Preconditions.checkNotNull(timeProvider, TimestampAnalyticsHelper.ATTR_TIME);
    }

    public void log(ChannelLogLevel channelLogLevel, String str) {
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            this.tracer.logOnly(javaLogLevel, str);
        }
        if (isTraceable(channelLogLevel)) {
            trace(channelLogLevel, str);
        }
    }

    public void log(ChannelLogLevel channelLogLevel, String str, Object... objArr) {
        String str2;
        Level javaLogLevel = toJavaLogLevel(channelLogLevel);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            str2 = MessageFormat.format(str, objArr);
            this.tracer.logOnly(javaLogLevel, str2);
        } else {
            str2 = null;
        }
        if (isTraceable(channelLogLevel)) {
            if (str2 == null) {
                str2 = MessageFormat.format(str, objArr);
            }
            trace(channelLogLevel, str2);
        }
    }

    private boolean isTraceable(ChannelLogLevel channelLogLevel) {
        return channelLogLevel != ChannelLogLevel.DEBUG && this.tracer.isTraceEnabled();
    }

    private void trace(ChannelLogLevel channelLogLevel, String str) {
        if (channelLogLevel != ChannelLogLevel.DEBUG) {
            this.tracer.traceOnly(new Builder().setDescription(str).setSeverity(toTracerSeverity(channelLogLevel)).setTimestampNanos(this.time.currentTimeNanos()).build());
        }
    }

    private Severity toTracerSeverity(ChannelLogLevel channelLogLevel) {
        switch (channelLogLevel) {
            case ERROR:
                return Severity.CT_ERROR;
            case WARNING:
                return Severity.CT_WARNING;
            default:
                return Severity.CT_INFO;
        }
    }

    private Level toJavaLogLevel(ChannelLogLevel channelLogLevel) {
        switch (channelLogLevel) {
            case ERROR:
                return Level.FINE;
            case WARNING:
                return Level.FINER;
            default:
                return Level.FINEST;
        }
    }
}
