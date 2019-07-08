package com.google.android.gms.fitness.request;

import android.os.IBinder;
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
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbq;
import com.google.android.gms.internal.fitness.zzbr;
import com.google.android.gms.internal.fitness.zzf;
import com.google.android.gms.internal.fitness.zzfa;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "GoalsReadRequestCreator")
@Reserved({1000})
public class GoalsReadRequest extends AbstractSafeParcelable {
    public static final Creator<GoalsReadRequest> CREATOR = new zzad();
    @Field(getter = "getDataTypes", id = 2, type = "java.util.List")
    private final List<DataType> zzah;
    @Field(getter = "getActivities", id = 4, type = "java.util.List")
    private final List<Integer> zzdl;
    @Field(getter = "getCallbackBinder", id = 1, type = "android.os.IBinder")
    private final zzbq zzhj;
    @Field(getter = "getObjectiveTypeList", id = 3, type = "java.util.List")
    private final List<Integer> zzhk;

    public static class Builder {
        /* access modifiers changed from: private */
        public final List<DataType> zzah = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Integer> zzdl = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Integer> zzhk = new ArrayList();

        public Builder addDataType(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder addActivity(String str) {
            int zzl = zzfa.zzl(str);
            Preconditions.checkState(zzl != 4, "Attempting to add an unknown activity");
            zzf.zza(Integer.valueOf(zzl), this.zzdl);
            return this;
        }

        public Builder addObjectiveType(int i) {
            boolean z = true;
            if (!(i == 1 || i == 2 || i == 3)) {
                z = false;
            }
            Preconditions.checkState(z, "Attempting to add an invalid objective type");
            if (!this.zzhk.contains(Integer.valueOf(i))) {
                this.zzhk.add(Integer.valueOf(i));
            }
            return this;
        }

        public GoalsReadRequest build() {
            Preconditions.checkState(!this.zzah.isEmpty(), "At least one data type should be specified.");
            return new GoalsReadRequest(this);
        }
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    @Nullable
    public List<Integer> getObjectiveTypes() {
        if (this.zzhk.isEmpty()) {
            return null;
        }
        return this.zzhk;
    }

    @Nullable
    public List<String> getActivityNames() {
        if (this.zzdl.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : this.zzdl) {
            arrayList.add(zzfa.getName(intValue.intValue()));
        }
        return arrayList;
    }

    @Constructor
    GoalsReadRequest(@Param(id = 1) IBinder iBinder, @Param(id = 2) List<DataType> list, @Param(id = 3) List<Integer> list2, @Param(id = 4) List<Integer> list3) {
        zzbq zzbq;
        if (iBinder == null) {
            zzbq = null;
        } else {
            zzbq = zzbr.zzf(iBinder);
        }
        this.zzhj = zzbq;
        this.zzah = list;
        this.zzhk = list2;
        this.zzdl = list3;
    }

    private GoalsReadRequest(Builder builder) {
        this((zzbq) null, builder.zzah, builder.zzhk, builder.zzdl);
    }

    public GoalsReadRequest(GoalsReadRequest goalsReadRequest, zzbq zzbq) {
        this(zzbq, goalsReadRequest.getDataTypes(), goalsReadRequest.zzhk, goalsReadRequest.zzdl);
    }

    private GoalsReadRequest(zzbq zzbq, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this(zzbq == null ? null : zzbq.asBinder(), list, list2, list3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof GoalsReadRequest) {
                GoalsReadRequest goalsReadRequest = (GoalsReadRequest) obj;
                if (Objects.equal(this.zzah, goalsReadRequest.zzah) && Objects.equal(this.zzhk, goalsReadRequest.zzhk) && Objects.equal(this.zzdl, goalsReadRequest.zzdl)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzah, this.zzhk, getActivityNames());
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzah).add("objectiveTypes", this.zzhk).add("activities", getActivityNames()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzhj.asBinder(), false);
        SafeParcelWriter.writeList(parcel, 2, getDataTypes(), false);
        SafeParcelWriter.writeList(parcel, 3, this.zzhk, false);
        SafeParcelWriter.writeList(parcel, 4, this.zzdl, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
