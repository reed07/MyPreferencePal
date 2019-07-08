package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.ads.interactivemedia.v3.internal.js.a;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class hj implements a {
    public static final Creator<hj> CREATOR = new hk();
    public final String a;
    public final byte[] b;
    public final int c;
    private final int d;

    public hj(String str, byte[] bArr, int i, int i2) {
        this.a = str;
        this.b = bArr;
        this.d = i;
        this.c = i2;
    }

    public final int describeContents() {
        return 0;
    }

    private hj(Parcel parcel) {
        this.a = (String) vf.a(parcel.readString());
        this.b = new byte[parcel.readInt()];
        parcel.readByteArray(this.b);
        this.d = parcel.readInt();
        this.c = parcel.readInt();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        hj hjVar = (hj) obj;
        return this.a.equals(hjVar.a) && Arrays.equals(this.b, hjVar.b) && this.d == hjVar.d && this.c == hjVar.c;
    }

    public final int hashCode() {
        return ((((((this.a.hashCode() + 527) * 31) + Arrays.hashCode(this.b)) * 31) + this.d) * 31) + this.c;
    }

    public final String toString() {
        String str = "mdta: key=";
        String valueOf = String.valueOf(this.a);
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b.length);
        parcel.writeByteArray(this.b);
        parcel.writeInt(this.d);
        parcel.writeInt(this.c);
    }

    /* synthetic */ hj(Parcel parcel, byte b2) {
        this(parcel);
    }
}
