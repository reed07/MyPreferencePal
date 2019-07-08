package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class ou {
    public final String a;
    public final String b;
    private final String c;

    public ou(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ou ouVar = (ou) obj;
        return vf.a((Object) this.a, (Object) ouVar.a) && vf.a((Object) this.b, (Object) ouVar.b) && vf.a((Object) this.c, (Object) ouVar.c);
    }

    public final int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }
}
