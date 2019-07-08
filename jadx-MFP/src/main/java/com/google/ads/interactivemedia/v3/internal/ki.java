package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;

/* compiled from: IMASDK */
public final class ki extends kp {
    public static final Creator<ki> CREATOR = new kj();
    public final String a;
    public final String b;
    private final String d;

    public ki(String str, String str2, String str3) {
        super(CommentFrame.ID);
        this.d = str;
        this.a = str2;
        this.b = str3;
    }

    ki(Parcel parcel) {
        super(CommentFrame.ID);
        this.d = (String) vf.a(parcel.readString());
        this.a = (String) vf.a(parcel.readString());
        this.b = (String) vf.a(parcel.readString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ki kiVar = (ki) obj;
        return vf.a((Object) this.a, (Object) kiVar.a) && vf.a((Object) this.d, (Object) kiVar.d) && vf.a((Object) this.b, (Object) kiVar.b);
    }

    public final int hashCode() {
        String str = this.d;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
        String str2 = this.a;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.b;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public final String toString() {
        String str = this.c;
        String str2 = this.d;
        String str3 = this.a;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 25 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append(": language=");
        sb.append(str2);
        sb.append(", description=");
        sb.append(str3);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.b);
    }
}
