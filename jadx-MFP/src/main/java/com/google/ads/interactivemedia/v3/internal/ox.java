package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;

/* compiled from: IMASDK */
public final class ox {
    public final long a;
    public final long b;
    private final String c;
    private int d;

    public ox(String str, long j, long j2) {
        if (str == null) {
            str = "";
        }
        this.c = str;
        this.a = j;
        this.b = j2;
    }

    public final Uri a(String str) {
        return qi.a(str, this.c);
    }

    private final String b(String str) {
        return qi.b(str, this.c);
    }

    public final ox a(ox oxVar, String str) {
        String b2 = b(str);
        if (oxVar == null || !b2.equals(oxVar.b(str))) {
            return null;
        }
        long j = this.b;
        long j2 = -1;
        if (j != -1) {
            long j3 = this.a;
            if (j3 + j == oxVar.a) {
                long j4 = oxVar.b;
                if (j4 != -1) {
                    j2 = j + j4;
                }
                ox oxVar2 = new ox(b2, j3, j2);
                return oxVar2;
            }
        }
        long j5 = oxVar.b;
        if (j5 != -1) {
            long j6 = oxVar.a;
            if (j6 + j5 == this.a) {
                long j7 = this.b;
                if (j7 != -1) {
                    j2 = j5 + j7;
                }
                ox oxVar3 = new ox(b2, j6, j2);
                return oxVar3;
            }
        }
        return null;
    }

    public final int hashCode() {
        if (this.d == 0) {
            this.d = ((((((int) this.a) + 527) * 31) + ((int) this.b)) * 31) + this.c.hashCode();
        }
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ox oxVar = (ox) obj;
        return this.a == oxVar.a && this.b == oxVar.b && this.c.equals(oxVar.c);
    }

    public final String toString() {
        String str = this.c;
        long j = this.a;
        long j2 = this.b;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 81);
        sb.append("RangedUri(referenceUri=");
        sb.append(str);
        sb.append(", start=");
        sb.append(j);
        sb.append(", length=");
        sb.append(j2);
        sb.append(")");
        return sb.toString();
    }
}
