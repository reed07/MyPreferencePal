package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

/* compiled from: IMASDK */
public class rx implements Parcelable {
    public static final Creator<rx> CREATOR = new ry();
    public static final rx u = new rx();
    public final String v;
    public final String w;
    public final boolean x;
    public final int y;

    rx() {
        this(null, null, false, 0);
    }

    public int describeContents() {
        return 0;
    }

    rx(String str, String str2, boolean z, int i) {
        this.v = vf.b(str);
        this.w = vf.b(str2);
        this.x = z;
        this.y = i;
    }

    rx(Parcel parcel) {
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.x = vf.a(parcel);
        this.y = parcel.readInt();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        rx rxVar = (rx) obj;
        return TextUtils.equals(this.v, rxVar.v) && TextUtils.equals(this.w, rxVar.w) && this.x == rxVar.x && this.y == rxVar.y;
    }

    public int hashCode() {
        String str = this.v;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.w;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((((hashCode + i) * 31) + (this.x ? 1 : 0)) * 31) + this.y;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        vf.a(parcel, this.x);
        parcel.writeInt(this.y);
    }
}
