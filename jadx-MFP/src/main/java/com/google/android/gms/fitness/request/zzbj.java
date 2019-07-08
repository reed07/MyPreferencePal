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
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import com.myfitnesspal.feature.payments.model.MfpProduct.ProductType;

@Class(creator = "SubscribeRequestCreator")
@Reserved({4, 1000})
public final class zzbj extends AbstractSafeParcelable {
    public static final Creator<zzbj> CREATOR = new zzbk();
    @Nullable
    @Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Nullable
    @Field(getter = "getSubscription", id = 1)
    private Subscription zzio;
    @Field(getter = "isServerOnly", id = 2)
    private final boolean zzip;

    @Constructor
    zzbj(@Param(id = 1) Subscription subscription, @Param(id = 2) boolean z, @Param(id = 3) IBinder iBinder) {
        this.zzio = subscription;
        this.zzip = z;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzbj(Subscription subscription, boolean z, @Nullable zzcq zzcq) {
        this.zzio = subscription;
        this.zzip = false;
        this.zzgj = zzcq;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(ProductType.SUBSCRIPTION, this.zzio).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzio, i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzip);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
