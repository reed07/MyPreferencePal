package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "ApplicationCreator")
@Reserved({2, 1000})
public final class zzb extends AbstractSafeParcelable {
    public static final Creator<zzb> CREATOR = new zzc();
    public static final zzb zzad = new zzb("com.google.android.gms", null);
    @Field(getter = "getPackageName", id = 1)
    private final String packageName;
    @Nullable
    @Field(getter = "getDomainName", id = 3)
    private final String zzae;

    public static zzb zza(String str) {
        if ("com.google.android.gms".equals(str)) {
            return zzad;
        }
        return new zzb(str, null);
    }

    @Constructor
    public zzb(@Param(id = 1) String str, @Nullable @Param(id = 3) String str2) {
        this.packageName = (String) Preconditions.checkNotNull(str);
        this.zzae = str2;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzb)) {
            return false;
        }
        zzb zzb = (zzb) obj;
        return this.packageName.equals(zzb.packageName) && Objects.equal(this.zzae, zzb.zzae);
    }

    public final int hashCode() {
        return Objects.hashCode(this.packageName, this.zzae);
    }

    public final String toString() {
        return String.format("Application{%s:%s}", new Object[]{this.packageName, this.zzae});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzae, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
