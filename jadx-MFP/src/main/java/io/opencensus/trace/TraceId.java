package io.opencensus.trace;

import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class TraceId implements Comparable<TraceId> {
    public static final TraceId INVALID = new TraceId(new byte[16]);
    private final byte[] bytes;

    private TraceId(byte[] bArr) {
        this.bytes = bArr;
    }

    public String toLowerBase16() {
        return LowerCaseBase16Encoding.encodeToString(this.bytes);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceId)) {
            return false;
        }
        return Arrays.equals(this.bytes, ((TraceId) obj).bytes);
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TraceId{traceId=");
        sb.append(toLowerBase16());
        sb.append("}");
        return sb.toString();
    }

    public int compareTo(TraceId traceId) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr = this.bytes;
            byte b = bArr[i];
            byte[] bArr2 = traceId.bytes;
            if (b != bArr2[i]) {
                return bArr[i] < bArr2[i] ? -1 : 1;
            }
        }
        return 0;
    }
}
