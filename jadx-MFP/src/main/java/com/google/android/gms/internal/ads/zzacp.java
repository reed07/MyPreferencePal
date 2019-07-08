package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@zzark
@Class(creator = "NativeAdOptionsParcelCreator")
public final class zzacp extends AbstractSafeParcelable {
    public static final Creator<zzacp> CREATOR = new zzacq();
    @Field(id = 1)
    public final int versionCode;
    @Field(id = 2)
    public final boolean zzdcs;
    @Field(id = 3)
    public final int zzdct;
    @Field(id = 4)
    public final boolean zzdcu;
    @Field(id = 5)
    public final int zzdcv;
    @Nullable
    @Field(id = 6)
    public final zzzw zzdcw;
    @Field(id = 7)
    private final boolean zzdcx;

    public zzacp(NativeAdOptions nativeAdOptions) {
        boolean shouldReturnUrlsForImageAssets = nativeAdOptions.shouldReturnUrlsForImageAssets();
        int imageOrientation = nativeAdOptions.getImageOrientation();
        boolean shouldRequestMultipleImages = nativeAdOptions.shouldRequestMultipleImages();
        int adChoicesPlacement = nativeAdOptions.getAdChoicesPlacement();
        zzzw zzzw = nativeAdOptions.getVideoOptions() != null ? new zzzw(nativeAdOptions.getVideoOptions()) : null;
        this(3, shouldReturnUrlsForImageAssets, imageOrientation, shouldRequestMultipleImages, adChoicesPlacement, zzzw, nativeAdOptions.zzhz());
    }

    @Constructor
    public zzacp(@Param(id = 1) int i, @Param(id = 2) boolean z, @Param(id = 3) int i2, @Param(id = 4) boolean z2, @Param(id = 5) int i3, @Param(id = 6) zzzw zzzw, @Param(id = 7) boolean z3) {
        this.versionCode = i;
        this.zzdcs = z;
        this.zzdct = i2;
        this.zzdcu = z2;
        this.zzdcv = i3;
        this.zzdcw = zzzw;
        this.zzdcx = z3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzdcs);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdct);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzdcu);
        SafeParcelWriter.writeInt(parcel, 5, this.zzdcv);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzdcw, i, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzdcx);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
