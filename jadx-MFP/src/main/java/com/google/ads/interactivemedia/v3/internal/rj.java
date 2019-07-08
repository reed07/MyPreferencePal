package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;

/* compiled from: IMASDK */
final class rj {
    public final int a;
    public final int b;
    public final String c;

    public rj(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        rj rjVar = (rj) obj;
        return this.a == rjVar.a && this.b == rjVar.b && TextUtils.equals(this.c, rjVar.c);
    }

    public final int hashCode() {
        int i = ((this.a * 31) + this.b) * 31;
        String str = this.c;
        return i + (str != null ? str.hashCode() : 0);
    }
}
