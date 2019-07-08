package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.MlltFrame;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class ks extends kp {
    public static final Creator<ks> CREATOR = new kt();
    public final int a;
    public final int b;
    public final int[] d;
    public final int[] e;
    private final int f;

    public ks(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        super(MlltFrame.ID);
        this.f = i;
        this.a = i2;
        this.b = i3;
        this.d = iArr;
        this.e = iArr2;
    }

    public final int describeContents() {
        return 0;
    }

    ks(Parcel parcel) {
        super(MlltFrame.ID);
        this.f = parcel.readInt();
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.d = (int[]) vf.a(parcel.createIntArray());
        this.e = (int[]) vf.a(parcel.createIntArray());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ks ksVar = (ks) obj;
        return this.f == ksVar.f && this.a == ksVar.a && this.b == ksVar.b && Arrays.equals(this.d, ksVar.d) && Arrays.equals(this.e, ksVar.e);
    }

    public final int hashCode() {
        return ((((((((this.f + 527) * 31) + this.a) * 31) + this.b) * 31) + Arrays.hashCode(this.d)) * 31) + Arrays.hashCode(this.e);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f);
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeIntArray(this.d);
        parcel.writeIntArray(this.e);
    }
}
