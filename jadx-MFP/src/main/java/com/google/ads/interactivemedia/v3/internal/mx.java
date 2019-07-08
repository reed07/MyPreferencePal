package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class mx implements Parcelable {
    public static final Creator<mx> CREATOR = new my();
    public final int a;
    private final bs[] b;
    private int c;

    public mx(bs... bsVarArr) {
        qi.c(bsVarArr.length > 0);
        this.b = bsVarArr;
        this.a = bsVarArr.length;
    }

    public final int describeContents() {
        return 0;
    }

    mx(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = new bs[this.a];
        for (int i = 0; i < this.a; i++) {
            this.b[i] = (bs) parcel.readParcelable(bs.class.getClassLoader());
        }
    }

    public final bs a(int i) {
        return this.b[i];
    }

    public final int a(bs bsVar) {
        int i = 0;
        while (true) {
            bs[] bsVarArr = this.b;
            if (i >= bsVarArr.length) {
                return -1;
            }
            if (bsVar == bsVarArr[i]) {
                return i;
            }
            i++;
        }
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b) + 527;
        }
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        mx mxVar = (mx) obj;
        return this.a == mxVar.a && Arrays.equals(this.b, mxVar.b);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        for (int i2 = 0; i2 < this.a; i2++) {
            parcel.writeParcelable(this.b[i2], 0);
        }
    }
}
