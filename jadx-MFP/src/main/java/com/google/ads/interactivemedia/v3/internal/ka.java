package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class ka extends kp {
    public static final Creator<ka> CREATOR = new kb();
    private final String a;
    private final String b;
    private final int d;
    private final byte[] e;

    public ka(String str, String str2, int i, byte[] bArr) {
        super(ApicFrame.ID);
        this.a = str;
        this.b = str2;
        this.d = i;
        this.e = bArr;
    }

    ka(Parcel parcel) {
        super(ApicFrame.ID);
        this.a = (String) vf.a(parcel.readString());
        this.b = (String) vf.a(parcel.readString());
        this.d = parcel.readInt();
        this.e = (byte[]) vf.a(parcel.createByteArray());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ka kaVar = (ka) obj;
        return this.d == kaVar.d && vf.a((Object) this.a, (Object) kaVar.a) && vf.a((Object) this.b, (Object) kaVar.b) && Arrays.equals(this.e, kaVar.e);
    }

    public final int hashCode() {
        int i = (this.d + 527) * 31;
        String str = this.a;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return ((hashCode + i2) * 31) + Arrays.hashCode(this.e);
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.a;
        String str3 = this.b;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 25 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append(": mimeType=");
        sb.append(str2);
        sb.append(", description=");
        sb.append(str3);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.d);
        parcel.writeByteArray(this.e);
    }
}
