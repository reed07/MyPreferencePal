package io.opencensus.trace;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.NetworkEvent.Type;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
@Deprecated
final class AutoValue_NetworkEvent extends NetworkEvent {
    private final long compressedMessageSize;
    private final Timestamp kernelTimestamp;
    private final long messageId;
    private final Type type;
    private final long uncompressedMessageSize;

    static final class Builder extends io.opencensus.trace.NetworkEvent.Builder {
        private Long compressedMessageSize;
        private Timestamp kernelTimestamp;
        private Long messageId;
        private Type type;
        private Long uncompressedMessageSize;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public io.opencensus.trace.NetworkEvent.Builder setType(Type type2) {
            if (type2 != null) {
                this.type = type2;
                return this;
            }
            throw new NullPointerException("Null type");
        }

        /* access modifiers changed from: 0000 */
        public io.opencensus.trace.NetworkEvent.Builder setMessageId(long j) {
            this.messageId = Long.valueOf(j);
            return this;
        }

        public io.opencensus.trace.NetworkEvent.Builder setUncompressedMessageSize(long j) {
            this.uncompressedMessageSize = Long.valueOf(j);
            return this;
        }

        public io.opencensus.trace.NetworkEvent.Builder setCompressedMessageSize(long j) {
            this.compressedMessageSize = Long.valueOf(j);
            return this;
        }

        public NetworkEvent build() {
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
                AutoValue_NetworkEvent autoValue_NetworkEvent = new AutoValue_NetworkEvent(this.kernelTimestamp, this.type, this.messageId.longValue(), this.uncompressedMessageSize.longValue(), this.compressedMessageSize.longValue());
                return autoValue_NetworkEvent;
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Missing required properties:");
            sb5.append(str);
            throw new IllegalStateException(sb5.toString());
        }
    }

    private AutoValue_NetworkEvent(@Nullable Timestamp timestamp, Type type2, long j, long j2, long j3) {
        this.kernelTimestamp = timestamp;
        this.type = type2;
        this.messageId = j;
        this.uncompressedMessageSize = j2;
        this.compressedMessageSize = j3;
    }

    @Nullable
    public Timestamp getKernelTimestamp() {
        return this.kernelTimestamp;
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
        sb.append("NetworkEvent{kernelTimestamp=");
        sb.append(this.kernelTimestamp);
        sb.append(", type=");
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

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        if (r7.compressedMessageSize == r8.getCompressedMessageSize()) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8 instanceof io.opencensus.trace.NetworkEvent
            r2 = 0
            if (r1 == 0) goto L_0x004d
            io.opencensus.trace.NetworkEvent r8 = (io.opencensus.trace.NetworkEvent) r8
            io.opencensus.common.Timestamp r1 = r7.kernelTimestamp
            if (r1 != 0) goto L_0x0016
            io.opencensus.common.Timestamp r1 = r8.getKernelTimestamp()
            if (r1 != 0) goto L_0x004b
            goto L_0x0020
        L_0x0016:
            io.opencensus.common.Timestamp r3 = r8.getKernelTimestamp()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x004b
        L_0x0020:
            io.opencensus.trace.NetworkEvent$Type r1 = r7.type
            io.opencensus.trace.NetworkEvent$Type r3 = r8.getType()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x004b
            long r3 = r7.messageId
            long r5 = r8.getMessageId()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x004b
            long r3 = r7.uncompressedMessageSize
            long r5 = r8.getUncompressedMessageSize()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x004b
            long r3 = r7.compressedMessageSize
            long r5 = r8.getCompressedMessageSize()
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 != 0) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r0 = 0
        L_0x004c:
            return r0
        L_0x004d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.opencensus.trace.AutoValue_NetworkEvent.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Timestamp timestamp = this.kernelTimestamp;
        long hashCode = (long) (((((timestamp == null ? 0 : timestamp.hashCode()) ^ 1000003) * 1000003) ^ this.type.hashCode()) * 1000003);
        long j = this.messageId;
        long j2 = (long) (((int) (hashCode ^ (j ^ (j >>> 32)))) * 1000003);
        long j3 = this.uncompressedMessageSize;
        long j4 = (long) (((int) (j2 ^ (j3 ^ (j3 >>> 32)))) * 1000003);
        long j5 = this.compressedMessageSize;
        return (int) (j4 ^ (j5 ^ (j5 >>> 32)));
    }
}
