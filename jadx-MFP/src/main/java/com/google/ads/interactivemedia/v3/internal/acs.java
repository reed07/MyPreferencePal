package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class acs extends aeo {
    private final String TXXX;

    acs(String str) {
        if (str != null) {
            this.TXXX = str;
            return;
        }
        throw new NullPointerException("Null TXXX");
    }

    /* access modifiers changed from: 0000 */
    public final String TXXX() {
        return this.TXXX;
    }

    public final String toString() {
        String str = this.TXXX;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 28);
        sb.append("TimedMetadataWithKeys{TXXX=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof aeo)) {
            return false;
        }
        return this.TXXX.equals(((aeo) obj).TXXX());
    }

    public final int hashCode() {
        return this.TXXX.hashCode() ^ 1000003;
    }
}
