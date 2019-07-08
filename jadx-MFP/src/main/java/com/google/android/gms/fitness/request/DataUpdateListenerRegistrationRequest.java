package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

@Class(creator = "DataUpdateListenerRegistrationRequestCreator")
@Reserved({1000})
public class DataUpdateListenerRegistrationRequest extends AbstractSafeParcelable {
    public static final Creator<DataUpdateListenerRegistrationRequest> CREATOR = new zzv();
    @Nullable
    @Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Field(getter = "getIntent", id = 3)
    private final PendingIntent zzhi;
    @Nullable
    @Field(getter = "getDataType", id = 2)
    private DataType zzq;
    @Nullable
    @Field(getter = "getDataSource", id = 1)
    private DataSource zzr;

    public static class Builder {
        /* access modifiers changed from: private */
        public PendingIntent zzhi;
        /* access modifiers changed from: private */
        public DataType zzq;
        /* access modifiers changed from: private */
        public DataSource zzr;

        public Builder setDataSource(DataSource dataSource) throws NullPointerException {
            Preconditions.checkNotNull(dataSource);
            this.zzr = dataSource;
            return this;
        }

        public Builder setDataType(DataType dataType) {
            Preconditions.checkNotNull(dataType);
            this.zzq = dataType;
            return this;
        }

        public Builder setPendingIntent(PendingIntent pendingIntent) {
            Preconditions.checkNotNull(pendingIntent);
            this.zzhi = pendingIntent;
            return this;
        }

        public DataUpdateListenerRegistrationRequest build() {
            Preconditions.checkState((this.zzr == null && this.zzq == null) ? false : true, "Set either dataSource or dataTYpe");
            Preconditions.checkNotNull(this.zzhi, "pendingIntent must be set");
            return new DataUpdateListenerRegistrationRequest(this);
        }
    }

    @Constructor
    public DataUpdateListenerRegistrationRequest(@Param(id = 1) DataSource dataSource, @Param(id = 2) DataType dataType, @Param(id = 3) PendingIntent pendingIntent, @Param(id = 4) IBinder iBinder) {
        this.zzr = dataSource;
        this.zzq = dataType;
        this.zzhi = pendingIntent;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public DataUpdateListenerRegistrationRequest(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest, IBinder iBinder) {
        this(dataUpdateListenerRegistrationRequest.zzr, dataUpdateListenerRegistrationRequest.zzq, dataUpdateListenerRegistrationRequest.zzhi, iBinder);
    }

    private DataUpdateListenerRegistrationRequest(Builder builder) {
        this(builder.zzr, builder.zzq, builder.zzhi, null);
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    @Nullable
    public PendingIntent getIntent() {
        return this.zzhi;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzr).add("dataType", this.zzq).add(BaseGmsClient.KEY_PENDING_INTENT, this.zzhi).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getIntent(), i, false);
        zzcq zzcq = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcq == null ? null : zzcq.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DataUpdateListenerRegistrationRequest) {
                DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest = (DataUpdateListenerRegistrationRequest) obj;
                if (Objects.equal(this.zzr, dataUpdateListenerRegistrationRequest.zzr) && Objects.equal(this.zzq, dataUpdateListenerRegistrationRequest.zzq) && Objects.equal(this.zzhi, dataUpdateListenerRegistrationRequest.zzhi)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq, this.zzhi);
    }
}
