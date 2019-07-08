package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.concurrent.TimeUnit;

@Class(creator = "SessionStartRequestCreator")
@Reserved({3, 1000})
public final class zzaz extends AbstractSafeParcelable {
    public static final Creator<zzaz> CREATOR = new zzba();
    @Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getSession", id = 1)
    private final Session zzz;

    @Constructor
    zzaz(@Param(id = 1) Session session, @Param(id = 2) IBinder iBinder) {
        this.zzz = session;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzaz(Session session, zzcq zzcq) {
        Preconditions.checkArgument(session.getStartTime(TimeUnit.MILLISECONDS) < System.currentTimeMillis(), "Cannot start a session in the future");
        Preconditions.checkArgument(session.isOngoing(), "Cannot start a session which has already ended");
        this.zzz = session;
        this.zzgj = zzcq;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (com.google.android.gms.common.internal.Objects.equal(r1.zzz, ((com.google.android.gms.fitness.request.zzaz) r2).zzz) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(@android.support.annotation.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r2 == r1) goto L_0x0015
            boolean r0 = r2 instanceof com.google.android.gms.fitness.request.zzaz
            if (r0 == 0) goto L_0x0013
            com.google.android.gms.fitness.request.zzaz r2 = (com.google.android.gms.fitness.request.zzaz) r2
            com.google.android.gms.fitness.data.Session r0 = r1.zzz
            com.google.android.gms.fitness.data.Session r2 = r2.zzz
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzaz.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzz);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.zzz).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzz, i, false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
