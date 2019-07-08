package io.opencensus.trace;

import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class SpanId implements Comparable<SpanId> {
    public static final SpanId INVALID = new SpanId(new byte[8]);
    private final byte[] bytes;

    private SpanId(byte[] bArr) {
        this.bytes = bArr;
    }

    public String toLowerBase16() {
        return LowerCaseBase16Encoding.encodeToString(this.bytes);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanId)) {
            return false;
        }
        return Arrays.equals(this.bytes, ((SpanId) obj).bytes);
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpanId{spanId=");
        sb.append(toLowerBase16());
        sb.append("}");
        return sb.toString();
    }

    public int compareTo(SpanId spanId) {
        for (int i = 0; i < 8; i++) {
            byte[] bArr = this.bytes;
            byte b = bArr[i];
            byte[] bArr2 = spanId.bytes;
            if (b != bArr2[i]) {
                return bArr[i] < bArr2[i] ? -1 : 1;
            }
        }
        return 0;
    }
}
