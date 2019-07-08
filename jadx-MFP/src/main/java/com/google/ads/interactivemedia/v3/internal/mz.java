package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class mz implements Parcelable {
    public static final Creator<mz> CREATOR = new na();
    public static final mz a = new mz(new mx[0]);
    public final int b;
    private final mx[] c;
    private int d;

    public mz(mx... mxVarArr) {
        this.c = mxVarArr;
        this.b = mxVarArr.length;
    }

    public final int describeContents() {
        return 0;
    }

    mz(Parcel parcel) {
        this.b = parcel.readInt();
        this.c = new mx[this.b];
        for (int i = 0; i < this.b; i++) {
            this.c[i] = (mx) parcel.readParcelable(mx.class.getClassLoader());
        }
    }

    public final mx a(int i) {
        return this.c[i];
    }

    public final int a(mx mxVar) {
        for (int i = 0; i < this.b; i++) {
            if (this.c[i] == mxVar) {
                return i;
            }
        }
        return -1;
    }

    public final int hashCode() {
        if (this.d == 0) {
            this.d = Arrays.hashCode(this.c);
        }
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        mz mzVar = (mz) obj;
        return this.b == mzVar.b && Arrays.equals(this.c, mzVar.c);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        for (int i2 = 0; i2 < this.b; i2++) {
            parcel.writeParcelable(this.c[i2], 0);
        }
    }
}
