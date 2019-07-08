package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

@Class(creator = "StopBleScanRequestCreator")
@Reserved({3, 1000})
public final class zzbh extends AbstractSafeParcelable {
    public static final Creator<zzbh> CREATOR = new zzbi();
    @Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getBleScanCallbackBinder", id = 1, type = "android.os.IBinder")
    private final zzae zzik;

    @Constructor
    zzbh(@Param(id = 1) IBinder iBinder, @Param(id = 2) IBinder iBinder2) {
        zzae zzae;
        if (iBinder == null) {
            zzae = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
            if (queryLocalInterface instanceof zzae) {
                zzae = (zzae) queryLocalInterface;
            } else {
                zzae = new zzag(iBinder);
            }
        }
        this.zzik = zzae;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    public zzbh(zzae zzae, zzcq zzcq) {
        this.zzik = zzae;
        this.zzgj = zzcq;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzik.asBinder(), false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
