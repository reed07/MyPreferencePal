package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.ChapterTocFrame;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class kg extends kp {
    public static final Creator<kg> CREATOR = new kh();
    private final String a;
    private final boolean b;
    private final boolean d;
    private final String[] e;
    private final kp[] f;

    public kg(String str, boolean z, boolean z2, String[] strArr, kp[] kpVarArr) {
        super(ChapterTocFrame.ID);
        this.a = str;
        this.b = z;
        this.d = z2;
        this.e = strArr;
        this.f = kpVarArr;
    }

    kg(Parcel parcel) {
        super(ChapterTocFrame.ID);
        this.a = (String) vf.a(parcel.readString());
        boolean z = true;
        this.b = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.d = z;
        this.e = (String[]) vf.a(parcel.createStringArray());
        int readInt = parcel.readInt();
        this.f = new kp[readInt];
        for (int i = 0; i < readInt; i++) {
            this.f[i] = (kp) parcel.readParcelable(kp.class.getClassLoader());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        kg kgVar = (kg) obj;
        return this.b == kgVar.b && this.d == kgVar.d && vf.a((Object) this.a, (Object) kgVar.a) && Arrays.equals(this.e, kgVar.e) && Arrays.equals(this.f, kgVar.f);
    }

    public final int hashCode() {
        int i = ((((this.b ? 1 : 0) + true) * 31) + (this.d ? 1 : 0)) * 31;
        String str = this.a;
        return i + (str != null ? str.hashCode() : 0);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeByte(this.b ? (byte) 1 : 0);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeStringArray(this.e);
        parcel.writeInt(this.f.length);
        for (kp writeParcelable : this.f) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }
}
