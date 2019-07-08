package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
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
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzx;
import com.google.android.gms.location.zzy;

@Class(creator = "LocationRequestUpdateDataCreator")
@Reserved({1000})
public final class zzbf extends AbstractSafeParcelable {
    public static final Creator<zzbf> CREATOR = new zzbg();
    @Field(defaultValueUnchecked = "null", id = 4)
    private PendingIntent zzbv;
    @Field(defaultValueUnchecked = "LocationRequestUpdateData.OPERATION_ADD", id = 1)
    private int zzcg;
    @Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 6, type = "android.os.IBinder")
    private zzaj zzcj;
    @Field(defaultValueUnchecked = "null", id = 2)
    private zzbd zzdl;
    @Field(defaultValueUnchecked = "null", getter = "getLocationListenerBinder", id = 3, type = "android.os.IBinder")
    private zzx zzdm;
    @Field(defaultValueUnchecked = "null", getter = "getLocationCallbackBinder", id = 5, type = "android.os.IBinder")
    private zzu zzdn;

    @Constructor
    zzbf(@Param(id = 1) int i, @Param(id = 2) zzbd zzbd, @Param(id = 3) IBinder iBinder, @Param(id = 4) PendingIntent pendingIntent, @Param(id = 5) IBinder iBinder2, @Param(id = 6) IBinder iBinder3) {
        this.zzcg = i;
        this.zzdl = zzbd;
        zzaj zzaj = null;
        this.zzdm = iBinder == null ? null : zzy.zzc(iBinder);
        this.zzbv = pendingIntent;
        this.zzdn = iBinder2 == null ? null : zzv.zzb(iBinder2);
        if (!(iBinder3 == null || iBinder3 == null)) {
            IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzaj = queryLocalInterface instanceof zzaj ? (zzaj) queryLocalInterface : new zzal(iBinder3);
        }
        this.zzcj = zzaj;
    }

    public static zzbf zza(zzu zzu, @Nullable zzaj zzaj) {
        zzbf zzbf = new zzbf(2, null, null, null, zzu.asBinder(), zzaj != null ? zzaj.asBinder() : null);
        return zzbf;
    }

    public static zzbf zza(zzx zzx, @Nullable zzaj zzaj) {
        zzbf zzbf = new zzbf(2, null, zzx.asBinder(), null, null, zzaj != null ? zzaj.asBinder() : null);
        return zzbf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdl, i, false);
        zzx zzx = this.zzdm;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 3, zzx == null ? null : zzx.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbv, i, false);
        zzu zzu = this.zzdn;
        SafeParcelWriter.writeIBinder(parcel, 5, zzu == null ? null : zzu.asBinder(), false);
        zzaj zzaj = this.zzcj;
        if (zzaj != null) {
            iBinder = zzaj.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 6, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
