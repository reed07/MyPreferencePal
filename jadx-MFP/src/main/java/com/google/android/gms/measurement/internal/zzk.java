package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "AppMetadataCreator")
@Reserved({1, 20})
public final class zzk extends AbstractSafeParcelable {
    public static final Creator<zzk> CREATOR = new zzl();
    @Field(id = 2)
    public final String packageName;
    @Field(id = 6)
    public final long zzade;
    @Field(id = 3)
    public final String zzafi;
    @Field(id = 12)
    public final String zzafk;
    @Field(defaultValueUnchecked = "Integer.MIN_VALUE", id = 11)
    public final long zzafo;
    @Field(id = 5)
    public final String zzafp;
    @Field(id = 7)
    public final long zzafq;
    @Field(defaultValue = "true", id = 9)
    public final boolean zzafr;
    @Field(id = 13)
    public final long zzafs;
    @Field(defaultValue = "true", id = 16)
    public final boolean zzaft;
    @Field(defaultValue = "true", id = 17)
    public final boolean zzafu;
    @Field(id = 19)
    public final String zzafv;
    @Field(id = 8)
    public final String zzagm;
    @Field(id = 10)
    public final boolean zzagn;
    @Field(id = 14)
    public final long zzago;
    @Field(id = 15)
    public final int zzagp;
    @Field(id = 18)
    public final boolean zzagq;
    @Field(id = 4)
    public final String zzts;

    zzk(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i, boolean z3, boolean z4, boolean z5, String str7) {
        Preconditions.checkNotEmpty(str);
        this.packageName = str;
        this.zzafi = TextUtils.isEmpty(str2) ? null : str2;
        this.zzts = str3;
        this.zzafo = j;
        this.zzafp = str4;
        this.zzade = j2;
        this.zzafq = j3;
        this.zzagm = str5;
        this.zzafr = z;
        this.zzagn = z2;
        this.zzafk = str6;
        this.zzafs = j4;
        this.zzago = j5;
        this.zzagp = i;
        this.zzaft = z3;
        this.zzafu = z4;
        this.zzagq = z5;
        this.zzafv = str7;
    }

    @Constructor
    zzk(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) long j, @Param(id = 7) long j2, @Param(id = 8) String str5, @Param(id = 9) boolean z, @Param(id = 10) boolean z2, @Param(id = 11) long j3, @Param(id = 12) String str6, @Param(id = 13) long j4, @Param(id = 14) long j5, @Param(id = 15) int i, @Param(id = 16) boolean z3, @Param(id = 17) boolean z4, @Param(id = 18) boolean z5, @Param(id = 19) String str7) {
        this.packageName = str;
        this.zzafi = str2;
        this.zzts = str3;
        this.zzafo = j3;
        this.zzafp = str4;
        this.zzade = j;
        this.zzafq = j2;
        this.zzagm = str5;
        this.zzafr = z;
        this.zzagn = z2;
        this.zzafk = str6;
        this.zzafs = j4;
        this.zzago = j5;
        this.zzagp = i;
        this.zzaft = z3;
        this.zzafu = z4;
        this.zzagq = z5;
        this.zzafv = str7;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzafi, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzts, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzafp, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzade);
        SafeParcelWriter.writeLong(parcel, 7, this.zzafq);
        SafeParcelWriter.writeString(parcel, 8, this.zzagm, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzafr);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzagn);
        SafeParcelWriter.writeLong(parcel, 11, this.zzafo);
        SafeParcelWriter.writeString(parcel, 12, this.zzafk, false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzafs);
        SafeParcelWriter.writeLong(parcel, 14, this.zzago);
        SafeParcelWriter.writeInt(parcel, 15, this.zzagp);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzaft);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzafu);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzagq);
        SafeParcelWriter.writeString(parcel, 19, this.zzafv, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
