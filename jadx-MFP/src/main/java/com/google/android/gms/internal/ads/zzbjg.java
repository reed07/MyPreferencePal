package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "GassRequestParcelCreator")
public final class zzbjg extends AbstractSafeParcelable {
    public static final Creator<zzbjg> CREATOR = new zzbjh();
    @Field(id = 2)
    private final String packageName;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(id = 3)
    private final String zzfcv;

    @Constructor
    zzbjg(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2) {
        this.versionCode = i;
        this.packageName = str;
        this.zzfcv = str2;
    }

    public zzbjg(String str, String str2) {
        this(1, str, str2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzfcv, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
