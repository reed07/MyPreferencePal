package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class ar {
    public final cf a;
    private boolean b;

    public ar(cf cfVar) {
        this.a = cfVar;
    }

    public final void a() {
        this.b = true;
    }

    public final void a(as asVar) {
        if (!this.b) {
            asVar.a(this.a);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((ar) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
