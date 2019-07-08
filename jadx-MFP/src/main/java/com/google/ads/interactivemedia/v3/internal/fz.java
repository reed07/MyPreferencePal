package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class fz {
    public final gb a;
    public final gb b;

    public fz(gb gbVar) {
        this(gbVar, gbVar);
    }

    public fz(gb gbVar, gb gbVar2) {
        this.a = (gb) qi.a(gbVar);
        this.b = (gb) qi.a(gbVar2);
    }

    public final String toString() {
        String str;
        String valueOf = String.valueOf(this.a);
        if (this.a.equals(this.b)) {
            str = "";
        } else {
            String valueOf2 = String.valueOf(this.b);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 2);
            sb.append(", ");
            sb.append(valueOf2);
            str = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(str).length());
        sb2.append("[");
        sb2.append(valueOf);
        sb2.append(str);
        sb2.append("]");
        return sb2.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        fz fzVar = (fz) obj;
        return this.a.equals(fzVar.a) && this.b.equals(fzVar.b);
    }

    public final int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }
}
