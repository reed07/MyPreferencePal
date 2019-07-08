package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import java.util.Arrays;

@TargetApi(21)
/* compiled from: IMASDK */
public final class de {
    private final int[] a;
    private final int b = 8;

    private de(int[] iArr, int i) {
        this.a = Arrays.copyOf(iArr, iArr.length);
        Arrays.sort(this.a);
    }

    public final boolean a(int i) {
        return Arrays.binarySearch(this.a, i) >= 0;
    }

    public final int a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof de)) {
            return false;
        }
        de deVar = (de) obj;
        return Arrays.equals(this.a, deVar.a) && this.b == deVar.b;
    }

    public final int hashCode() {
        return this.b + (Arrays.hashCode(this.a) * 31);
    }

    public final String toString() {
        int i = this.b;
        String arrays = Arrays.toString(this.a);
        StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 67);
        sb.append("AudioCapabilities[maxChannelCount=");
        sb.append(i);
        sb.append(", supportedEncodings=");
        sb.append(arrays);
        sb.append("]");
        return sb.toString();
    }

    static {
        new de(new int[]{2}, 8);
    }
}
