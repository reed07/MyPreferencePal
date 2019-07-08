package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "MutateRequestCreator")
@Reserved({4})
public final class zzx extends AbstractSafeParcelable {
    public static final Creator<zzx> CREATOR = new zzy();
    @Field(id = 1)
    private final int type;
    @Nullable
    @Field(id = 2)
    private final Thing[] zzfh;
    @Nullable
    @Field(id = 3)
    private final String[] zzfi;
    @Nullable
    @Field(id = 5)
    private final String[] zzfj;

    @Constructor
    zzx(@Param(id = 1) int i, @Nullable @Param(id = 2) Thing[] thingArr, @Nullable @Param(id = 3) String[] strArr, @Nullable @Param(id = 5) String[] strArr2) {
        if (i != 6) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                default:
                    i = 0;
                    break;
            }
        }
        this.type = i;
        this.zzfh = thingArr;
        this.zzfi = strArr;
        this.zzfj = strArr2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.type);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzfh, i, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzfi, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzfj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
