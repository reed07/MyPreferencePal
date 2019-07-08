package io.opencensus.trace;

import io.opencensus.trace.MessageEvent.Type;

final class AutoValue_MessageEvent extends MessageEvent {
    private final long compressedMessageSize;
    private final long messageId;
    private final Type type;
    private final long uncompressedMessageSize;

    static final class Builder extends io.opencensus.trace.MessageEvent.Builder {
        private Long compressedMessageSize;
        private Long messageId;
        private Type type;
        private Long uncompressedMessageSize;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public io.opencensus.trace.MessageEvent.Builder setType(Type type2) {
            if (type2 != null) {
                this.type = type2;
                return this;
            }
            throw new NullPointerException("Null type");
        }

        /* access modifiers changed from: 0000 */
        public io.opencensus.trace.MessageEvent.Builder setMessageId(long j) {
            this.messageId = Long.valueOf(j);
            return this;
        }

        public io.opencensus.trace.MessageEvent.Builder setUncompressedMessageSize(long j) {
            this.uncompressedMessageSize = Long.valueOf(j);
            return this;
        }

        public io.opencensus.trace.MessageEvent.Builder setCompressedMessageSize(long j) {
            this.compressedMessageSize = Long.valueOf(j);
            return this;
        }

        public MessageEvent build() {
            String str = "";
            if (this.type == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" type");
                str = sb.toString();
            }
            if (this.messageId == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" messageId");
                str = sb2.toString();
            }
            if (this.uncompressedMessageSize == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" uncompressedMessageSize");
                str = sb3.toString();
            }
            if (this.compressedMessageSize == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" compressedMessageSize");
                str = sb4.toString();
            }
            if (str.isEmpty()) {
                AutoValue_MessageEvent autoValue_MessageEvent = new AutoValue_MessageEvent(this.type, this.messageId.longValue(), this.uncompressedMessageSize.longValue(), this.compressedMessageSize.longValue());
                return autoValue_MessageEvent;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Missing required properties:");
            sb5.append(str);
            throw new IllegalStateException(sb5.toString());
        }
    }

    private AutoValue_MessageEvent(Type type2, long j, long j2, long j3) {
        this.type = type2;
        this.messageId = j;
        this.uncompressedMessageSize = j2;
        this.compressedMessageSize = j3;
    }

    public Type getType() {
        return this.type;
    }

    public long getMessageId() {
        return this.messageId;
    }

    public long getUncompressedMessageSize() {
        return this.uncompressedMessageSize;
    }

    public long getCompressedMessageSize() {
        return this.compressedMessageSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MessageEvent{type=");
        sb.append(this.type);
        sb.append(", messageId=");
        sb.append(this.messageId);
        sb.append(", uncompressedMessageSize=");
        sb.append(this.uncompressedMessageSize);
        sb.append(", compressedMessageSize=");
        sb.append(this.compressedMessageSize);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MessageEvent)) {
            return false;
        }
        MessageEvent messageEvent = (MessageEvent) obj;
        if (!(this.type.equals(messageEvent.getType()) && this.messageId == messageEvent.getMessageId() && this.uncompressedMessageSize == messageEvent.getUncompressedMessageSize() && this.compressedMessageSize == messageEvent.getCompressedMessageSize())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long hashCode = (long) ((this.type.hashCode() ^ 1000003) * 1000003);
        long j = this.messageId;
        long j2 = (long) (((int) (hashCode ^ (j ^ (j >>> 32)))) * 1000003);
        long j3 = this.uncompressedMessageSize;
        long j4 = (long) (((int) (j2 ^ (j3 ^ (j3 >>> 32)))) * 1000003);
        long j5 = this.compressedMessageSize;
        return (int) (j4 ^ (j5 ^ (j5 >>> 32)));
    }
}
