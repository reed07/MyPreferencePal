package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.ChapterFrame;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class ke extends kp {
    public static final Creator<ke> CREATOR = new kf();
    private final String a;
    private final int b;
    private final int d;
    private final long e;
    private final long f;
    private final kp[] g;

    public ke(String str, int i, int i2, long j, long j2, kp[] kpVarArr) {
        super(ChapterFrame.ID);
        this.a = str;
        this.b = i;
        this.d = i2;
        this.e = j;
        this.f = j2;
        this.g = kpVarArr;
    }

    public final int describeContents() {
        return 0;
    }

    ke(Parcel parcel) {
        super(ChapterFrame.ID);
        this.a = (String) vf.a(parcel.readString());
        this.b = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        int readInt = parcel.readInt();
        this.g = new kp[readInt];
        for (int i = 0; i < readInt; i++) {
            this.g[i] = (kp) parcel.readParcelable(kp.class.getClassLoader());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ke keVar = (ke) obj;
        return this.b == keVar.b && this.d == keVar.d && this.e == keVar.e && this.f == keVar.f && vf.a((Object) this.a, (Object) keVar.a) && Arrays.equals(this.g, keVar.g);
    }

    public final int hashCode() {
        int i = (((((((this.b + 527) * 31) + this.d) * 31) + ((int) this.e)) * 31) + ((int) this.f)) * 31;
        String str = this.a;
        return i + (str != null ? str.hashCode() : 0);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        parcel.writeInt(this.g.length);
        for (kp writeParcelable : this.g) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }
}
