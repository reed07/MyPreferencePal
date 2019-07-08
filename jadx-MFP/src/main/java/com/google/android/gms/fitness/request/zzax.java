package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;
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

@Class(creator = "SessionRegistrationRequestCreator")
@Reserved({3, 1000})
public final class zzax extends AbstractSafeParcelable {
    public static final Creator<zzax> CREATOR = new zzay();
    @Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getIntent", id = 1)
    private final PendingIntent zzhi;
    @Field(getter = "getRegistrationOption", id = 4)
    private final int zzii;

    @Constructor
    zzax(@Param(id = 1) PendingIntent pendingIntent, @Param(id = 2) IBinder iBinder, @Param(id = 4) int i) {
        zzcq zzcq;
        this.zzhi = pendingIntent;
        if (iBinder == null) {
            zzcq = null;
        } else {
            zzcq = zzcr.zzj(iBinder);
        }
        this.zzgj = zzcq;
        this.zzii = i;
    }

    public zzax(PendingIntent pendingIntent, zzcq zzcq, int i) {
        this.zzhi = pendingIntent;
        this.zzgj = zzcq;
        this.zzii = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzhi, i, false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzii);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(BaseGmsClient.KEY_PENDING_INTENT, this.zzhi).add("sessionRegistrationOption", Integer.valueOf(this.zzii)).toString();
    }

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof zzax) {
                zzax zzax = (zzax) obj;
                if (this.zzii == zzax.zzii && Objects.equal(this.zzhi, zzax.zzhi)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzhi, Integer.valueOf(this.zzii));
    }
}
