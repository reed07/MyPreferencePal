package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "InitializationParamsCreator")
public final class zzdy extends AbstractSafeParcelable {
    public static final Creator<zzdy> CREATOR = new zzdz();
    @Field(id = 5)
    public final String origin;
    @Field(id = 1)
    public final long zzade;
    @Field(id = 2)
    public final long zzadf;
    @Field(id = 3)
    public final boolean zzadg;
    @Field(id = 4)
    public final String zzadh;
    @Field(id = 6)
    public final String zzadi;
    @Field(id = 7)
    public final Bundle zzadj;

    @Constructor
    zzdy(@Param(id = 1) long j, @Param(id = 2) long j2, @Param(id = 3) boolean z, @Param(id = 4) String str, @Param(id = 5) String str2, @Param(id = 6) String str3, @Param(id = 7) Bundle bundle) {
        this.zzade = j;
        this.zzadf = j2;
        this.zzadg = z;
        this.zzadh = str;
        this.origin = str2;
        this.zzadi = str3;
        this.zzadj = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzade);
        SafeParcelWriter.writeLong(parcel, 2, this.zzadf);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzadg);
        SafeParcelWriter.writeString(parcel, 4, this.zzadh, false);
        SafeParcelWriter.writeString(parcel, 5, this.origin, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzadi, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzadj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
