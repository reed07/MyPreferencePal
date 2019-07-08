package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.ads.zzark;

@zzark
@Class(creator = "InterstitialAdParameterParcelCreator")
@Reserved({1})
public final class zzaq extends AbstractSafeParcelable {
    public static final Creator<zzaq> CREATOR = new zzar();
    @Field(id = 2)
    public final boolean zzbpa;
    @Field(id = 3)
    public final boolean zzbpb;
    @Field(id = 4)
    private final String zzbpc;
    @Field(id = 5)
    public final boolean zzbpd;
    @Field(id = 6)
    public final float zzbpe;
    @Field(id = 7)
    public final int zzbpf;
    @Field(id = 8)
    public final boolean zzbpg;
    @Field(id = 9)
    public final boolean zzbph;
    @Field(id = 10)
    public final boolean zzbpi;

    public zzaq(boolean z, boolean z2, boolean z3, float f, int i, boolean z4, boolean z5, boolean z6) {
        this(z, z2, null, z3, f, i, z4, z5, z6);
    }

    @Constructor
    zzaq(@Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 4) String str, @Param(id = 5) boolean z3, @Param(id = 6) float f, @Param(id = 7) int i, @Param(id = 8) boolean z4, @Param(id = 9) boolean z5, @Param(id = 10) boolean z6) {
        this.zzbpa = z;
        this.zzbpb = z2;
        this.zzbpc = str;
        this.zzbpd = z3;
        this.zzbpe = f;
        this.zzbpf = i;
        this.zzbpg = z4;
        this.zzbph = z5;
        this.zzbpi = z6;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbpa);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbpb);
        SafeParcelWriter.writeString(parcel, 4, this.zzbpc, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzbpd);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzbpe);
        SafeParcelWriter.writeInt(parcel, 7, this.zzbpf);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzbpg);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzbph);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzbpi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
