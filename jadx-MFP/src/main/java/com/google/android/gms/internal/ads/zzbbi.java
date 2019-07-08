package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@zzark
@Class(creator = "VersionInfoParcelCreator")
@Reserved({1})
public final class zzbbi extends AbstractSafeParcelable {
    public static final Creator<zzbbi> CREATOR = new zzbbj();
    @Field(id = 2)
    public String zzdp;
    @Field(id = 3)
    public int zzeou;
    @Field(id = 4)
    public int zzeov;
    @Field(id = 5)
    public boolean zzeow;
    @Field(id = 6)
    public boolean zzeox;

    public zzbbi(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzbbi(int i, int i2, boolean z, boolean z2) {
        this(14300000, i2, true, false, z2);
    }

    private zzbbi(int i, int i2, boolean z, boolean z2, boolean z3) {
        String str = z ? "0" : AppEventsConstants.EVENT_PARAM_VALUE_YES;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 36);
        sb.append("afma-sdk-a-v");
        sb.append(i);
        sb.append(".");
        sb.append(i2);
        sb.append(".");
        sb.append(str);
        this(sb.toString(), i, i2, z, z3);
    }

    @Constructor
    zzbbi(@Param(id = 2) String str, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) boolean z, @Param(id = 6) boolean z2) {
        this.zzdp = str;
        this.zzeou = i;
        this.zzeov = i2;
        this.zzeow = z;
        this.zzeox = z2;
    }

    public static zzbbi zzaav() {
        return new zzbbi(12451009, 12451009, true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdp, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzeou);
        SafeParcelWriter.writeInt(parcel, 4, this.zzeov);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzeow);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzeox);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
