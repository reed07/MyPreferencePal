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
import com.google.android.gms.internal.fitness.zzbn;
import com.google.android.gms.internal.fitness.zzbo;

@Class(creator = "DataTypeReadRequestCreator")
@Reserved({4, 1000})
public final class zzs extends AbstractSafeParcelable {
    public static final Creator<zzs> CREATOR = new zzt();
    @Field(getter = "getName", id = 1)
    private final String name;
    @Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    private final zzbn zzhh;

    @Constructor
    zzs(@Param(id = 1) String str, @Param(id = 3) IBinder iBinder) {
        this.name = str;
        this.zzhh = zzbo.zze(iBinder);
    }

    public zzs(String str, zzbn zzbn) {
        this.name = str;
        this.zzhh = zzbn;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (com.google.android.gms.common.internal.Objects.equal(r1.name, ((com.google.android.gms.fitness.request.zzs) r2).name) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(@android.support.annotation.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r2 == r1) goto L_0x0015
            boolean r0 = r2 instanceof com.google.android.gms.fitness.request.zzs
            if (r0 == 0) goto L_0x0013
            com.google.android.gms.fitness.request.zzs r2 = (com.google.android.gms.fitness.request.zzs) r2
            java.lang.String r0 = r1.name
            java.lang.String r2 = r2.name
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzs.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return Objects.hashCode(this.name);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzhh.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
