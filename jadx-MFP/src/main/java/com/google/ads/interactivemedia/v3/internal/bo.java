package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class bo implements Comparable<bo> {
    public final cg a;
    public int b;
    public long c;
    public Object d;

    public bo(cg cgVar) {
        this.a = cgVar;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        int i;
        bo boVar = (bo) obj;
        if ((this.d == null) != (boVar.d == null)) {
            if (this.d == null) {
                return 1;
            }
            i = -1;
        } else if (this.d == null) {
            return 0;
        } else {
            int i2 = this.b - boVar.b;
            if (i2 != 0) {
                return i2;
            }
            i = vf.b(this.c, boVar.c);
        }
        return i;
    }
}
