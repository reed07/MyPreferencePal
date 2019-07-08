package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.ads.interactivemedia.v3.internal.js.a;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class ju implements a {
    public static final Creator<ju> CREATOR = new jv();
    public final String a;
    public final String b;
    public final long c;
    public final long d;
    public final byte[] e;
    private int f;

    public ju(String str, String str2, long j, long j2, byte[] bArr) {
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = j2;
        this.e = bArr;
    }

    public final int describeContents() {
        return 0;
    }

    ju(Parcel parcel) {
        this.a = (String) vf.a(parcel.readString());
        this.b = (String) vf.a(parcel.readString());
        this.c = parcel.readLong();
        this.d = parcel.readLong();
        this.e = (byte[]) vf.a(parcel.createByteArray());
    }

    public final int hashCode() {
        if (this.f == 0) {
            String str = this.a;
            int i = 0;
            int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
            String str2 = this.b;
            if (str2 != null) {
                i = str2.hashCode();
            }
            int i2 = (hashCode + i) * 31;
            long j = this.c;
            int i3 = (i2 + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.d;
            this.f = ((i3 + ((int) (j2 ^ (j2 >>> 32)))) * 31) + Arrays.hashCode(this.e);
        }
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ju juVar = (ju) obj;
        return this.c == juVar.c && this.d == juVar.d && vf.a((Object) this.a, (Object) juVar.a) && vf.a((Object) this.b, (Object) juVar.b) && Arrays.equals(this.e, juVar.e);
    }

    public final String toString() {
        String str = this.a;
        long j = this.d;
        String str2 = this.b;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 46 + String.valueOf(str2).length());
        sb.append("EMSG: scheme=");
        sb.append(str);
        sb.append(", id=");
        sb.append(j);
        sb.append(", value=");
        sb.append(str2);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d);
        parcel.writeByteArray(this.e);
    }
}
