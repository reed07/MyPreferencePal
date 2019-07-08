package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class ku extends kp {
    public static final Creator<ku> CREATOR = new kv();
    public final String a;
    public final byte[] b;

    public ku(String str, byte[] bArr) {
        super(PrivFrame.ID);
        this.a = str;
        this.b = bArr;
    }

    ku(Parcel parcel) {
        super(PrivFrame.ID);
        this.a = (String) vf.a(parcel.readString());
        this.b = (byte[]) vf.a(parcel.createByteArray());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ku kuVar = (ku) obj;
        return vf.a((Object) this.a, (Object) kuVar.a) && Arrays.equals(this.b, kuVar.b);
    }

    public final int hashCode() {
        String str = this.a;
        return (((str != null ? str.hashCode() : 0) + 527) * 31) + Arrays.hashCode(this.b);
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.a;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 8 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(": owner=");
        sb.append(str2);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeByteArray(this.b);
    }
}
