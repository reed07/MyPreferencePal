package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.GraphRequest;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.fitness.zzbn;
import com.google.android.gms.internal.fitness.zzbo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "DataTypeCreateRequestCreator")
@Reserved({4, 1000})
public class DataTypeCreateRequest extends AbstractSafeParcelable {
    public static final Creator<DataTypeCreateRequest> CREATOR = new zzr();
    @Field(getter = "getName", id = 1)
    private final String name;
    @Field(getter = "getFields", id = 2)
    private final List<com.google.android.gms.fitness.data.Field> zzbs;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    private final zzbn zzhh;

    public static class Builder {
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public List<com.google.android.gms.fitness.data.Field> zzbs = new ArrayList();

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder addField(com.google.android.gms.fitness.data.Field field) {
            if (!this.zzbs.contains(field)) {
                this.zzbs.add(field);
            }
            return this;
        }

        public Builder addField(String str, int i) {
            Preconditions.checkArgument(str != null && !str.isEmpty(), "Invalid name specified");
            return addField(com.google.android.gms.fitness.data.Field.zza(str, i));
        }

        public DataTypeCreateRequest build() {
            Preconditions.checkState(this.name != null, "Must set the name");
            Preconditions.checkState(!this.zzbs.isEmpty(), "Must specify the data fields");
            return new DataTypeCreateRequest(this);
        }
    }

    @Constructor
    DataTypeCreateRequest(@Param(id = 1) String str, @Param(id = 2) List<com.google.android.gms.fitness.data.Field> list, @Param(id = 3) IBinder iBinder) {
        this.name = str;
        this.zzbs = Collections.unmodifiableList(list);
        this.zzhh = zzbo.zze(iBinder);
    }

    private DataTypeCreateRequest(Builder builder) {
        this(builder.name, builder.zzbs, (zzbn) null);
    }

    public DataTypeCreateRequest(DataTypeCreateRequest dataTypeCreateRequest, zzbn zzbn) {
        this(dataTypeCreateRequest.name, dataTypeCreateRequest.zzbs, zzbn);
    }

    private DataTypeCreateRequest(String str, List<com.google.android.gms.fitness.data.Field> list, @Nullable zzbn zzbn) {
        this.name = str;
        this.zzbs = Collections.unmodifiableList(list);
        this.zzhh = zzbn;
    }

    public String getName() {
        return this.name;
    }

    public List<com.google.android.gms.fitness.data.Field> getFields() {
        return this.zzbs;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != this) {
            if (obj instanceof DataTypeCreateRequest) {
                DataTypeCreateRequest dataTypeCreateRequest = (DataTypeCreateRequest) obj;
                if (Objects.equal(this.name, dataTypeCreateRequest.name) && Objects.equal(this.zzbs, dataTypeCreateRequest.zzbs)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.zzbs);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add(GraphRequest.FIELDS_PARAM, this.zzbs).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        zzbn zzbn = this.zzhh;
        SafeParcelWriter.writeIBinder(parcel, 3, zzbn == null ? null : zzbn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
