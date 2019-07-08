package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.GeobFrame;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class kk extends kp {
    public static final Creator<kk> CREATOR = new kl();
    private final String a;
    private final String b;
    private final String d;
    private final byte[] e;

    public kk(String str, String str2, String str3, byte[] bArr) {
        super(GeobFrame.ID);
        this.a = str;
        this.b = str2;
        this.d = str3;
        this.e = bArr;
    }

    kk(Parcel parcel) {
        super(GeobFrame.ID);
        this.a = (String) vf.a(parcel.readString());
        this.b = (String) vf.a(parcel.readString());
        this.d = (String) vf.a(parcel.readString());
        this.e = (byte[]) vf.a(parcel.createByteArray());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        kk kkVar = (kk) obj;
        return vf.a((Object) this.a, (Object) kkVar.a) && vf.a((Object) this.b, (Object) kkVar.b) && vf.a((Object) this.d, (Object) kkVar.d) && Arrays.equals(this.e, kkVar.e);
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
        return ((hashCode2 + i) * 31) + Arrays.hashCode(this.e);
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.a;
        String str3 = this.b;
        String str4 = this.d;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 36 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
        sb.append(str);
        sb.append(": mimeType=");
        sb.append(str2);
        sb.append(", filename=");
        sb.append(str3);
        sb.append(", description=");
        sb.append(str4);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.d);
        parcel.writeByteArray(this.e);
    }
}
