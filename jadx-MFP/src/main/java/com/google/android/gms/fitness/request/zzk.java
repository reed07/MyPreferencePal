package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

@Class(creator = "DataInsertRequestCreator")
@Reserved({3, 1000})
public final class zzk extends AbstractSafeParcelable {
    public static final Creator<zzk> CREATOR = new zzl();
    @Field(getter = "getDataSet", id = 1)
    private final DataSet zzeb;
    @Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "shouldSkipSync", id = 4)
    private final boolean zzgq;

    @Constructor
    zzk(@Param(id = 1) DataSet dataSet, @Param(id = 2) IBinder iBinder, @Param(id = 4) boolean z) {
        this.zzeb = dataSet;
        this.zzgj = zzcr.zzj(iBinder);
        this.zzgq = z;
    }

    public zzk(DataSet dataSet, zzcq zzcq, boolean z) {
        this.zzeb = dataSet;
        this.zzgj = zzcq;
        this.zzgq = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (com.google.android.gms.common.internal.Objects.equal(r1.zzeb, ((com.google.android.gms.fitness.request.zzk) r2).zzeb) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(@android.support.annotation.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r2 == r1) goto L_0x0015
            boolean r0 = r2 instanceof com.google.android.gms.fitness.request.zzk
            if (r0 == 0) goto L_0x0013
            com.google.android.gms.fitness.request.zzk r2 = (com.google.android.gms.fitness.request.zzk) r2
            com.google.android.gms.fitness.data.DataSet r0 = r1.zzeb
            com.google.android.gms.fitness.data.DataSet r2 = r2.zzeb
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzk.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzeb);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataSet", this.zzeb).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzeb, i, false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzgq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
