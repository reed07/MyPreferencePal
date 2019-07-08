package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Arrays;
import javax.annotation.Nullable;

@ShowFirstParty
@Class(creator = "RegisterSectionInfoCreator")
@Reserved({1000, 8, 9, 10})
public final class zzs extends AbstractSafeParcelable {
    public static final Creator<zzs> CREATOR = new zzu();
    @Field(id = 1)
    private final String name;
    @Field(defaultValue = "1", id = 4)
    private final int weight;
    @Field(id = 2)
    private final String zzaa;
    @Field(id = 3)
    private final boolean zzab;
    @Field(id = 5)
    private final boolean zzac;
    @Field(id = 6)
    private final String zzad;
    @Field(id = 7)
    @Nullable
    private final zzn[] zzae;
    @Field(id = 11)
    private final String zzaf;
    @Field(id = 12)
    private final zzv zzag;

    @Constructor
    zzs(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) boolean z, @Param(id = 4) int i, @Param(id = 5) boolean z2, @Param(id = 6) String str3, @Param(id = 7) zzn[] zznArr, @Param(id = 11) String str4, @Param(id = 12) zzv zzv) {
        this.name = str;
        this.zzaa = str2;
        this.zzab = z;
        this.weight = i;
        this.zzac = z2;
        this.zzad = str3;
        this.zzae = zznArr;
        this.zzaf = str4;
        this.zzag = zzv;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzaa, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzab);
        SafeParcelWriter.writeInt(parcel, 4, this.weight);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzac);
        SafeParcelWriter.writeString(parcel, 6, this.zzad, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.zzae, i, false);
        SafeParcelWriter.writeString(parcel, 11, this.zzaf, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzag, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, this.zzaa, Boolean.valueOf(this.zzab), Integer.valueOf(this.weight), Boolean.valueOf(this.zzac), this.zzad, Integer.valueOf(Arrays.hashCode(this.zzae)), this.zzaf, this.zzag);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzs)) {
            return false;
        }
        zzs zzs = (zzs) obj;
        return this.zzab == zzs.zzab && this.weight == zzs.weight && this.zzac == zzs.zzac && Objects.equal(this.name, zzs.name) && Objects.equal(this.zzaa, zzs.zzaa) && Objects.equal(this.zzad, zzs.zzad) && Objects.equal(this.zzaf, zzs.zzaf) && Objects.equal(this.zzag, zzs.zzag) && Arrays.equals(this.zzae, zzs.zzae);
    }
}
