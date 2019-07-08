package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@ShowFirstParty
@Class(creator = "DocumentIdCreator")
@Reserved({1000})
public final class zzj extends AbstractSafeParcelable {
    public static final Creator<zzj> CREATOR = new zzk();
    @Field(id = 1)
    private final String packageName;
    @Field(id = 2)
    private final String zzm;
    @Field(id = 3)
    private final String zzn;

    @Constructor
    public zzj(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) String str3) {
        this.packageName = str;
        this.zzm = str2;
        this.zzn = str3;
    }

    public final String toString() {
        return String.format("DocumentId[packageName=%s, corpusName=%s, uri=%s]", new Object[]{this.packageName, this.zzm, this.zzn});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzm, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
