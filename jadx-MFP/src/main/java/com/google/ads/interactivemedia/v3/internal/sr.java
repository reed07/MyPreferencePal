package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class sr {
    public final Uri a;
    public final int b;
    public final byte[] c;
    public final long d;
    public final long e;
    public final long f;
    private final String g;
    private final int h;

    public sr(Uri uri, int i) {
        this(uri, 0, -1, null, 1);
    }

    public sr(Uri uri, long j, long j2, String str) {
        this(uri, j, j, j2, str, 0);
    }

    public sr(Uri uri, long j, long j2, String str, int i) {
        this(uri, j, j, -1, str, i);
    }

    private sr(Uri uri, long j, long j2, long j3, String str, int i) {
        this(uri, null, j, j2, j3, str, i);
    }

    private sr(Uri uri, byte[] bArr, long j, long j2, long j3, String str, int i) {
        this(uri, 1, null, j, j2, j3, str, i);
    }

    private sr(Uri uri, int i, byte[] bArr, long j, long j2, long j3, String str, int i2) {
        byte[] bArr2 = bArr;
        long j4 = j;
        long j5 = j2;
        long j6 = j3;
        boolean z = true;
        qi.b(j4 >= 0);
        qi.b(j5 >= 0);
        if (j6 <= 0 && j6 != -1) {
            z = false;
        }
        qi.b(z);
        this.a = uri;
        this.b = i;
        if (bArr2 == null || bArr2.length == 0) {
            bArr2 = null;
        }
        this.c = bArr2;
        this.d = j4;
        this.e = j5;
        this.f = j6;
        this.g = str;
        this.h = i2;
    }

    public final boolean a(int i) {
        return (this.h & i) == i;
    }

    public final String toString() {
        String b2 = b(this.b);
        String valueOf = String.valueOf(this.a);
        String arrays = Arrays.toString(this.c);
        long j = this.d;
        long j2 = this.e;
        long j3 = this.f;
        String str = this.g;
        int i = this.h;
        StringBuilder sb = new StringBuilder(String.valueOf(b2).length() + 94 + String.valueOf(valueOf).length() + String.valueOf(arrays).length() + String.valueOf(str).length());
        sb.append("DataSpec[");
        sb.append(b2);
        sb.append(" ");
        sb.append(valueOf);
        sb.append(", ");
        sb.append(arrays);
        sb.append(", ");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(", ");
        sb.append(j3);
        sb.append(", ");
        sb.append(str);
        sb.append(", ");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    public static String b(int i) {
        switch (i) {
            case 1:
                return HttpConstants.METHOD_GET;
            case 2:
                return HttpConstants.METHOD_POST;
            case 3:
                return "HEAD";
            default:
                throw new AssertionError(i);
        }
    }

    public final sr a(long j) {
        long j2 = this.f;
        long j3 = -1;
        if (j2 != -1) {
            j3 = j2 - j;
        }
        long j4 = j3;
        if (j == 0 && this.f == j4) {
            return this;
        }
        sr srVar = new sr(this.a, this.b, this.c, this.d + j, this.e + j, j4, this.g, this.h);
        return srVar;
    }
}
