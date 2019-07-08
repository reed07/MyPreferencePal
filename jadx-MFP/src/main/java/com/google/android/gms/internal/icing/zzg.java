package com.google.android.gms.internal.icing;

import android.accounts.Account;
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
import java.util.BitSet;

@ShowFirstParty
@Class(creator = "DocumentContentsCreator")
@Reserved({1000})
public final class zzg extends AbstractSafeParcelable {
    public static final Creator<zzg> CREATOR = new zzi();
    @Field(id = 4)
    private final Account account;
    @Field(id = 1)
    private final zzl[] zzi;
    @Field(id = 2)
    private final String zzj;
    @Field(id = 3)
    private final boolean zzk;

    @Constructor
    zzg(@Param(id = 1) zzl[] zzlArr, @Param(id = 2) String str, @Param(id = 3) boolean z, @Param(id = 4) Account account2) {
        this.zzi = zzlArr;
        this.zzj = str;
        this.zzk = z;
        this.account = account2;
    }

    zzg(String str, boolean z, Account account2, zzl... zzlArr) {
        this(zzlArr, str, z, account2);
        if (zzlArr != null) {
            BitSet bitSet = new BitSet(zzr.zzy.length);
            for (zzl zzl : zzlArr) {
                int i = zzl.zzs;
                if (i != -1) {
                    if (bitSet.get(i)) {
                        String str2 = "Duplicate global search section type ";
                        String valueOf = String.valueOf(zzr.zza(i));
                        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    }
                    bitSet.set(i);
                }
            }
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 1, this.zzi, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzj, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzk);
        SafeParcelWriter.writeParcelable(parcel, 4, this.account, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzj, Boolean.valueOf(this.zzk), this.account, Integer.valueOf(Arrays.hashCode(this.zzi)));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzg)) {
            return false;
        }
        zzg zzg = (zzg) obj;
        if (!Objects.equal(this.zzj, zzg.zzj) || !Objects.equal(Boolean.valueOf(this.zzk), Boolean.valueOf(zzg.zzk)) || !Objects.equal(this.account, zzg.account) || !Arrays.equals(this.zzi, zzg.zzi)) {
            return false;
        }
        return true;
    }
}
