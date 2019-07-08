package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;
import java.util.List;

/* compiled from: IMASDK */
public final class js implements Parcelable {
    public static final Creator<js> CREATOR = new jt();
    private final a[] a;

    /* compiled from: IMASDK */
    public interface a extends Parcelable {
    }

    public js(a... aVarArr) {
        if (aVarArr == null) {
            aVarArr = new a[0];
        }
        this.a = aVarArr;
    }

    public final int describeContents() {
        return 0;
    }

    public js(List<? extends a> list) {
        this.a = new a[list.size()];
        list.toArray(this.a);
    }

    js(Parcel parcel) {
        this.a = new a[parcel.readInt()];
        int i = 0;
        while (true) {
            a[] aVarArr = this.a;
            if (i < aVarArr.length) {
                aVarArr[i] = (a) parcel.readParcelable(a.class.getClassLoader());
                i++;
            } else {
                return;
            }
        }
    }

    public final int a() {
        return this.a.length;
    }

    public final a a(int i) {
        return this.a[i];
    }

    public final js a(a... aVarArr) {
        a[] aVarArr2 = this.a;
        a[] aVarArr3 = (a[]) Arrays.copyOf(aVarArr2, aVarArr2.length + aVarArr.length);
        System.arraycopy(aVarArr, 0, aVarArr3, this.a.length, aVarArr.length);
        return new js((a[]) vf.a((T[]) aVarArr3));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.a, ((js) obj).a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a.length);
        for (a writeParcelable : this.a) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }
}
