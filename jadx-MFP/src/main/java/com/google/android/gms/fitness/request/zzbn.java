package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

@Class(creator = "UnsubscribeRequestCreator")
@Reserved({4, 1000})
public final class zzbn extends AbstractSafeParcelable {
    public static final Creator<zzbn> CREATOR = new zzbo();
    @Nullable
    @Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getDataType", id = 1)
    private final DataType zzq;
    @Field(getter = "getDataSource", id = 2)
    private final DataSource zzr;

    @Constructor
    zzbn(@Param(id = 1) DataType dataType, @Param(id = 2) DataSource dataSource, @Param(id = 3) IBinder iBinder) {
        this.zzq = dataType;
        this.zzr = dataSource;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzbn(DataType dataType, DataSource dataSource, @Nullable zzcq zzcq) {
        this.zzq = dataType;
        this.zzr = dataSource;
        this.zzgj = zzcq;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzq, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzr, i, false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof zzbn) {
                zzbn zzbn = (zzbn) obj;
                if (Objects.equal(this.zzr, zzbn.zzr) && Objects.equal(this.zzq, zzbn.zzq)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq);
    }
}
