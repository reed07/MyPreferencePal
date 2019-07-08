package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;

/* compiled from: IMASDK */
public final class kq extends kp {
    public static final Creator<kq> CREATOR = new kr();
    public final String a;
    public final String b;
    public final String d;

    public kq(String str, String str2, String str3) {
        super(InternalFrame.ID);
        this.a = str;
        this.b = str2;
        this.d = str3;
    }

    kq(Parcel parcel) {
        super(InternalFrame.ID);
        this.a = (String) vf.a(parcel.readString());
        this.b = (String) vf.a(parcel.readString());
        this.d = (String) vf.a(parcel.readString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        kq kqVar = (kq) obj;
        return vf.a((Object) this.b, (Object) kqVar.b) && vf.a((Object) this.a, (Object) kqVar.a) && vf.a((Object) this.d, (Object) kqVar.d);
    }

    public final int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.d;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.a;
        String str3 = this.b;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append(": domain=");
        sb.append(str2);
        sb.append(", description=");
        sb.append(str3);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.a);
        parcel.writeString(this.d);
    }
}