package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.Goal;
import java.util.List;

@Class(creator = "GoalsResultCreator")
@Reserved({1000})
public class GoalsResult extends AbstractSafeParcelable implements Result {
    public static final Creator<GoalsResult> CREATOR = new zzf();
    @Field(getter = "getStatus", id = 1)
    private final Status zzir;
    @Field(getter = "getGoals", id = 2)
    private final List<Goal> zziu;

    @Constructor
    public GoalsResult(@Param(id = 1) Status status, @Param(id = 2) List<Goal> list) {
        this.zzir = status;
        this.zziu = list;
    }

    public List<Goal> getGoals() {
        return this.zziu;
    }

    public Status getStatus() {
        return this.zzir;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getGoals(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
