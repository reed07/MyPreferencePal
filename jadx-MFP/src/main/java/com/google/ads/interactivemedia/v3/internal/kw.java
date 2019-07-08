package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: IMASDK */
public final class kw extends kp {
    public static final Creator<kw> CREATOR = new kx();
    private final String a;
    private final String b;

    public kw(String str, String str2, String str3) {
        super(str);
        this.a = str2;
        this.b = str3;
    }

    kw(Parcel parcel) {
        super((String) vf.a(parcel.readString()));
        this.a = parcel.readString();
        this.b = (String) vf.a(parcel.readString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        kw kwVar = (kw) obj;
        return this.c.equals(kwVar.c) && vf.a((Object) this.a, (Object) kwVar.a) && vf.a((Object) this.b, (Object) kwVar.b);
    }

    public final int hashCode() {
        int hashCode = (this.c.hashCode() + 527) * 31;
        String str = this.a;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.b;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 8 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(": value=");
        sb.append(str2);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
