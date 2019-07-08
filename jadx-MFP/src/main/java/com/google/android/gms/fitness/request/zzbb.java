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
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzco;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;

@Class(creator = "SessionStopRequestCreator")
@Reserved({4, 1000})
public final class zzbb extends AbstractSafeParcelable {
    public static final Creator<zzbb> CREATOR = new zzbc();
    @Nullable
    @Field(getter = "getName", id = 1)
    private final String name;
    @Nullable
    @Field(getter = "getIdentifier", id = 2)
    private final String zzdz;
    @Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    private final zzcn zzij;

    @Constructor
    zzbb(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) IBinder iBinder) {
        this.name = str;
        this.zzdz = str2;
        this.zzij = zzco.zzi(iBinder);
    }

    public zzbb(@Nullable String str, @Nullable String str2, zzcn zzcn) {
        this.name = str;
        this.zzdz = str2;
        this.zzij = zzcn;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            if (obj instanceof zzbb) {
                zzbb zzbb = (zzbb) obj;
                if (Objects.equal(this.name, zzbb.name) && Objects.equal(this.zzdz, zzbb.zzdz)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, this.zzdz);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add(Columns.IDENTIFIER, this.zzdz).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzdz, false);
        zzcn zzcn = this.zzij;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
