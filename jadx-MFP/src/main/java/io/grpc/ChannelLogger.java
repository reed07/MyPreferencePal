package io.grpc;

import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi
@ThreadSafe
public abstract class ChannelLogger {

    public enum ChannelLogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public abstract void log(ChannelLogLevel channelLogLevel, String str);

    public abstract void log(ChannelLogLevel channelLogLevel, String str, Object... objArr);
}
