package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.List;
import javax.annotation.Nullable;

@zzark
@Class(creator = "AdRequestParcelCreator")
public final class zzwb extends AbstractSafeParcelable {
    public static final Creator<zzwb> CREATOR = new zzwd();
    @Field(id = 3)
    public final Bundle extras;
    @Field(id = 1)
    public final int versionCode;
    @Field(id = 2)
    @Deprecated
    public final long zzcjb;
    @Field(id = 4)
    @Deprecated
    public final int zzcjc;
    @Field(id = 5)
    public final List<String> zzcjd;
    @Field(id = 6)
    public final boolean zzcje;
    @Field(id = 7)
    public final int zzcjf;
    @Field(id = 8)
    public final boolean zzcjg;
    @Field(id = 9)
    public final String zzcjh;
    @Field(id = 10)
    public final zzzs zzcji;
    @Field(id = 11)
    public final Location zzcjj;
    @Field(id = 12)
    public final String zzcjk;
    @Field(id = 13)
    public final Bundle zzcjl;
    @Field(id = 14)
    public final Bundle zzcjm;
    @Field(id = 15)
    public final List<String> zzcjn;
    @Field(id = 16)
    public final String zzcjo;
    @Field(id = 17)
    public final String zzcjp;
    @Field(id = 18)
    @Deprecated
    public final boolean zzcjq;
    @Field(id = 19)
    @Nullable
    public final zzvv zzcjr;
    @Field(id = 20)
    public final int zzcjs;
    @Field(id = 21)
    @Nullable
    public final String zzcjt;

    @Constructor
    public zzwb(@Param(id = 1) int i, @Param(id = 2) long j, @Param(id = 3) Bundle bundle, @Param(id = 4) int i2, @Param(id = 5) List<String> list, @Param(id = 6) boolean z, @Param(id = 7) int i3, @Param(id = 8) boolean z2, @Param(id = 9) String str, @Param(id = 10) zzzs zzzs, @Param(id = 11) Location location, @Param(id = 12) String str2, @Param(id = 13) Bundle bundle2, @Param(id = 14) Bundle bundle3, @Param(id = 15) List<String> list2, @Param(id = 16) String str3, @Param(id = 17) String str4, @Param(id = 18) boolean z3, @Param(id = 19) zzvv zzvv, @Param(id = 20) int i4, @Param(id = 21) @Nullable String str5) {
        this.versionCode = i;
        this.zzcjb = j;
        this.extras = bundle == null ? new Bundle() : bundle;
        this.zzcjc = i2;
        this.zzcjd = list;
        this.zzcje = z;
        this.zzcjf = i3;
        this.zzcjg = z2;
        this.zzcjh = str;
        this.zzcji = zzzs;
        this.zzcjj = location;
        this.zzcjk = str2;
        this.zzcjl = bundle2 == null ? new Bundle() : bundle2;
        this.zzcjm = bundle3;
        this.zzcjn = list2;
        this.zzcjo = str3;
        this.zzcjp = str4;
        this.zzcjq = z3;
        this.zzcjr = zzvv;
        this.zzcjs = i4;
        this.zzcjt = str5;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, this.zzcjb);
        SafeParcelWriter.writeBundle(parcel, 3, this.extras, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcjc);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzcjd, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzcje);
        SafeParcelWriter.writeInt(parcel, 7, this.zzcjf);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzcjg);
        SafeParcelWriter.writeString(parcel, 9, this.zzcjh, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzcji, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzcjj, i, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzcjk, false);
        SafeParcelWriter.writeBundle(parcel, 13, this.zzcjl, false);
        SafeParcelWriter.writeBundle(parcel, 14, this.zzcjm, false);
        SafeParcelWriter.writeStringList(parcel, 15, this.zzcjn, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzcjo, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzcjp, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzcjq);
        SafeParcelWriter.writeParcelable(parcel, 19, this.zzcjr, i, false);
        SafeParcelWriter.writeInt(parcel, 20, this.zzcjs);
        SafeParcelWriter.writeString(parcel, 21, this.zzcjt, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzwb)) {
            return false;
        }
        zzwb zzwb = (zzwb) obj;
        if (this.versionCode != zzwb.versionCode || this.zzcjb != zzwb.zzcjb || !Objects.equal(this.extras, zzwb.extras) || this.zzcjc != zzwb.zzcjc || !Objects.equal(this.zzcjd, zzwb.zzcjd) || this.zzcje != zzwb.zzcje || this.zzcjf != zzwb.zzcjf || this.zzcjg != zzwb.zzcjg || !Objects.equal(this.zzcjh, zzwb.zzcjh) || !Objects.equal(this.zzcji, zzwb.zzcji) || !Objects.equal(this.zzcjj, zzwb.zzcjj) || !Objects.equal(this.zzcjk, zzwb.zzcjk) || !Objects.equal(this.zzcjl, zzwb.zzcjl) || !Objects.equal(this.zzcjm, zzwb.zzcjm) || !Objects.equal(this.zzcjn, zzwb.zzcjn) || !Objects.equal(this.zzcjo, zzwb.zzcjo) || !Objects.equal(this.zzcjp, zzwb.zzcjp) || this.zzcjq != zzwb.zzcjq || this.zzcjs != zzwb.zzcjs || !Objects.equal(this.zzcjt, zzwb.zzcjt)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.versionCode), Long.valueOf(this.zzcjb), this.extras, Integer.valueOf(this.zzcjc), this.zzcjd, Boolean.valueOf(this.zzcje), Integer.valueOf(this.zzcjf), Boolean.valueOf(this.zzcjg), this.zzcjh, this.zzcji, this.zzcjj, this.zzcjk, this.zzcjl, this.zzcjm, this.zzcjn, this.zzcjo, this.zzcjp, Boolean.valueOf(this.zzcjq), Integer.valueOf(this.zzcjs), this.zzcjt);
    }

    public final zzwb zzpm() {
        Bundle bundle;
        Bundle bundle2 = this.zzcjl.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle2 == null) {
            Bundle bundle3 = this.extras;
            this.zzcjl.putBundle("com.google.ads.mediation.admob.AdMobAdapter", bundle3);
            bundle = bundle3;
        } else {
            bundle = bundle2;
        }
        zzwb zzwb = new zzwb(this.versionCode, this.zzcjb, bundle, this.zzcjc, this.zzcjd, this.zzcje, this.zzcjf, this.zzcjg, this.zzcjh, this.zzcji, this.zzcjj, this.zzcjk, this.zzcjl, this.zzcjm, this.zzcjn, this.zzcjo, this.zzcjp, this.zzcjq, this.zzcjr, this.zzcjs, this.zzcjt);
        return zzwb;
    }
}
