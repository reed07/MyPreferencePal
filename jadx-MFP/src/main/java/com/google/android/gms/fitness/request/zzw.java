package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
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
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

@Class(creator = "DataUpdateListenerUnregistrationRequestCreator")
@Reserved({1000})
public final class zzw extends AbstractSafeParcelable {
    public static final Creator<zzw> CREATOR = new zzx();
    @Nullable
    @Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getIntent", id = 1)
    private final PendingIntent zzhi;

    @Constructor
    public zzw(@Param(id = 1) PendingIntent pendingIntent, @Param(id = 2) IBinder iBinder) {
        this.zzhi = pendingIntent;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public final String toString() {
        return "DataUpdateListenerUnregistrationRequest";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzhi, i, false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (com.google.android.gms.common.internal.Objects.equal(r1.zzhi, ((com.google.android.gms.fitness.request.zzw) r2).zzhi) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(@android.support.annotation.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r2 == r1) goto L_0x0015
            boolean r0 = r2 instanceof com.google.android.gms.fitness.request.zzw
            if (r0 == 0) goto L_0x0013
            com.google.android.gms.fitness.request.zzw r2 = (com.google.android.gms.fitness.request.zzw) r2
            android.app.PendingIntent r0 = r1.zzhi
            android.app.PendingIntent r2 = r2.zzhi
            boolean r2 = com.google.android.gms.common.internal.Objects.equal(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzw.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzhi);
    }
}
