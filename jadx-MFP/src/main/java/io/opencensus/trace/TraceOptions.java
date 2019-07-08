package io.opencensus.trace;

import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class TraceOptions {
    public static final TraceOptions DEFAULT = fromByte(0);
    private final byte options;

    public static final class Builder {
    }

    private TraceOptions(byte b) {
        this.options = b;
    }

    public static TraceOptions fromByte(byte b) {
        return new TraceOptions(b);
    }

    public boolean isSampled() {
        return hasOption(1);
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceOptions)) {
            return false;
        }
        if (this.options != ((TraceOptions) obj).options) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new byte[]{this.options});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TraceOptions{sampled=");
        sb.append(isSampled());
        sb.append("}");
        return sb.toString();
    }

    private boolean hasOption(int i) {
        return (i & this.options) != 0;
    }
}
