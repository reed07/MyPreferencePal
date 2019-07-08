package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class kc extends kp {
    public static final Creator<kc> CREATOR = new kd();
    private final byte[] a;

    public kc(String str, byte[] bArr) {
        super(str);
        this.a = bArr;
    }

    kc(Parcel parcel) {
        super((String) vf.a(parcel.readString()));
        this.a = (byte[]) vf.a(parcel.createByteArray());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        kc kcVar = (kc) obj;
        return this.c.equals(kcVar.c) && Arrays.equals(this.a, kcVar.a);
    }

    public final int hashCode() {
        return ((this.c.hashCode() + 527) * 31) + Arrays.hashCode(this.a);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeByteArray(this.a);
    }
}
