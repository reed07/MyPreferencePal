package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class zzbr implements Parcelable {
    @Deprecated
    public static final Creator<zzbr> CREATOR = new zzbs();
    private String value;
    private String zzno;
    private String zzyf;

    @Deprecated
    public final int describeContents() {
        return 0;
    }

    public final String getId() {
        return this.zzno;
    }

    public final String getValue() {
        return this.value;
    }

    @Deprecated
    public zzbr() {
    }

    @Deprecated
    zzbr(Parcel parcel) {
        this.zzno = parcel.readString();
        this.zzyf = parcel.readString();
        this.value = parcel.readString();
    }

    @Deprecated
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzno);
        parcel.writeString(this.zzyf);
        parcel.writeString(this.value);
    }
}
