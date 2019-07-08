package com.google.android.gms.fitness.request;

import android.os.IBinder;
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
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbe;
import com.google.android.gms.internal.fitness.zzbf;

@Class(creator = "DailyTotalRequestCreator")
@Reserved({3, 1000})
public final class zzg extends AbstractSafeParcelable {
    public static final Creator<zzg> CREATOR = new zzh();
    @Field(getter = "getCallbackBinder", id = 1, type = "android.os.IBinder")
    private final zzbe zzgk;
    @Field(getter = "getLocalDataOnly", id = 4)
    private final boolean zzgl;
    @Nullable
    @Field(getter = "getDataType", id = 2)
    private DataType zzq;

    @Constructor
    zzg(@Param(id = 1) IBinder iBinder, @Nullable @Param(id = 2) DataType dataType, @Param(id = 4) boolean z) {
        this.zzgk = zzbf.zzb(iBinder);
        this.zzq = dataType;
        this.zzgl = z;
    }

    public zzg(zzbe zzbe, @Nullable DataType dataType, boolean z) {
        this.zzgk = zzbe;
        this.zzq = dataType;
        this.zzgl = z;
    }

    public final String toString() {
        String str = "DailyTotalRequest{%s}";
        Object[] objArr = new Object[1];
        DataType dataType = this.zzq;
        objArr[0] = dataType == null ? "null" : dataType.zzm();
        return String.format(str, objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzgk.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzq, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzgl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
