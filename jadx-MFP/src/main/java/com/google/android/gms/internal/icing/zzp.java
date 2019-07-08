package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@ShowFirstParty
@Class(creator = "GetRecentContextCall_ResponseCreator")
@Reserved({1000})
public final class zzp extends AbstractSafeParcelable implements Result {
    public static final Creator<zzp> CREATOR = new zzq();
    @Field(id = 1)
    private Status zzv;
    @Field(id = 2)
    private List<zzx> zzw;
    @Field(id = 3)
    @Deprecated
    private String[] zzx;

    public zzp() {
    }

    public final Status getStatus() {
        return this.zzv;
    }

    @Constructor
    zzp(@Param(id = 1) Status status, @Param(id = 2) List<zzx> list, @Param(id = 3) String[] strArr) {
        this.zzv = status;
        this.zzw = list;
        this.zzx = strArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzv, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzw, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzx, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
