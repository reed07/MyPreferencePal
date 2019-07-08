package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@zzark
@Class(creator = "VideoOptionsParcelCreator")
@Reserved({1})
public final class zzzw extends AbstractSafeParcelable {
    public static final Creator<zzzw> CREATOR = new zzzx();
    @Field(id = 2)
    public final boolean zzcnf;
    @Field(id = 3)
    public final boolean zzcng;
    @Field(id = 4)
    public final boolean zzcnh;

    public zzzw(VideoOptions videoOptions) {
        this(videoOptions.getStartMuted(), videoOptions.getCustomControlsRequested(), videoOptions.getClickToExpandRequested());
    }

    @Constructor
    public zzzw(@Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 4) boolean z3) {
        this.zzcnf = z;
        this.zzcng = z2;
        this.zzcnh = z3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzcnf);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzcng);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzcnh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
