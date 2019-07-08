package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@Class(creator = "NonagonRequestParcelCreator")
@ParametersAreNonnullByDefault
public final class zzatb extends AbstractSafeParcelable {
    public static final Creator<zzatb> CREATOR = new zzatc();
    @Field(id = 3)
    private final ApplicationInfo applicationInfo;
    @Field(id = 4)
    private final String packageName;
    @Field(id = 6)
    @Nullable
    private final PackageInfo zzdwh;
    @Field(id = 5)
    private final List<String> zzdwt;
    @Field(id = 7)
    private final String zzdxc;
    @Field(id = 1)
    private final Bundle zzdzn;
    @Field(id = 2)
    private final zzbbi zzdzo;
    @Field(id = 8)
    private final boolean zzdzp;
    @Field(id = 9)
    private final String zzdzq;

    @Constructor
    public zzatb(@Param(id = 1) Bundle bundle, @Param(id = 2) zzbbi zzbbi, @Param(id = 3) ApplicationInfo applicationInfo2, @Param(id = 4) String str, @Param(id = 5) List<String> list, @Param(id = 6) @Nullable PackageInfo packageInfo, @Param(id = 7) String str2, @Param(id = 8) boolean z, @Param(id = 9) String str3) {
        this.zzdzn = bundle;
        this.zzdzo = zzbbi;
        this.packageName = str;
        this.applicationInfo = applicationInfo2;
        this.zzdwt = list;
        this.zzdwh = packageInfo;
        this.zzdxc = str2;
        this.zzdzp = z;
        this.zzdzq = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzdzn, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdzo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.applicationInfo, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.packageName, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzdwt, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzdwh, i, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzdxc, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdzp);
        SafeParcelWriter.writeString(parcel, 9, this.zzdzq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
