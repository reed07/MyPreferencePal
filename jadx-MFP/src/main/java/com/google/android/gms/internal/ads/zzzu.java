package com.google.android.gms.internal.ads;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@zzark
public final class zzzu extends zzwf {
    public zzzu(zzwf zzwf) {
        super(zzwf.zzckk, zzwf.height, zzwf.heightPixels, zzwf.zzckl, zzwf.width, zzwf.widthPixels, zzwf.zzckm, zzwf.zzckn, zzwf.zzcko, zzwf.zzckp);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzckk, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
