package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@zzark
@Class(creator = "AdDataParcelCreator")
public final class zzvv extends AbstractSafeParcelable {
    public static final Creator<zzvv> CREATOR = new zzvw();
    @Field(id = 1)
    private final String zzcix;
    @Field(id = 2)
    private final String zzciy;

    @Constructor
    public zzvv(@Param(id = 1) String str, @Param(id = 2) String str2) {
        this.zzcix = str;
        this.zzciy = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzcix, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzciy, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
