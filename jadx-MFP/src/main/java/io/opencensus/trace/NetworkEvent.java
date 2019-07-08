package io.opencensus.trace;

import io.opencensus.common.Timestamp;
import io.opencensus.internal.Utils;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
@Deprecated
public abstract class NetworkEvent extends BaseMessageEvent {

    @Deprecated
    public static abstract class Builder {
        public abstract NetworkEvent build();

        public abstract Builder setCompressedMessageSize(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder setMessageId(long j);

        /* access modifiers changed from: 0000 */
        public abstract Builder setType(Type type);

        public abstract Builder setUncompressedMessageSize(long j);

        Builder() {
        }
    }

    public enum Type {
        SENT,
        RECV
    }

    public abstract long getCompressedMessageSize();

    @Nullable
    public abstract Timestamp getKernelTimestamp();

    public abstract long getMessageId();

    public abstract Type getType();

    public abstract long getUncompressedMessageSize();

    public static Builder builder(Type type, long j) {
        return new Builder().setType((Type) Utils.checkNotNull(type, "type")).setMessageId(j).setUncompressedMessageSize(0).setCompressedMessageSize(0);
    }

    NetworkEvent() {
    }
}
