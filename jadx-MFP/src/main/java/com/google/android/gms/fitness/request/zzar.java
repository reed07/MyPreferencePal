package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
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
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

@Class(creator = "SensorUnregistrationRequestCreator")
@Reserved({4, 1000})
public final class zzar extends AbstractSafeParcelable {
    public static final Creator<zzar> CREATOR = new zzas();
    @Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getIntent", id = 2)
    private final PendingIntent zzhi;
    @Field(getter = "getListenerBinder", id = 1, type = "android.os.IBinder")
    private final zzt zzhr;

    @Constructor
    zzar(@Param(id = 1) IBinder iBinder, @Param(id = 2) PendingIntent pendingIntent, @Param(id = 3) IBinder iBinder2) {
        zzt zzt;
        if (iBinder == null) {
            zzt = null;
        } else {
            zzt = zzu.zza(iBinder);
        }
        this.zzhr = zzt;
        this.zzhi = pendingIntent;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    public zzar(@Nullable zzt zzt, @Nullable PendingIntent pendingIntent, zzcq zzcq) {
        this.zzhr = zzt;
        this.zzhi = pendingIntent;
        this.zzgj = zzcq;
    }

    public final String toString() {
        return String.format("SensorUnregistrationRequest{%s}", new Object[]{this.zzhr});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        zzt zzt = this.zzhr;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 1, zzt == null ? null : zzt.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhi, i, false);
        zzcq zzcq = this.zzgj;
        if (zzcq != null) {
            iBinder = zzcq.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
