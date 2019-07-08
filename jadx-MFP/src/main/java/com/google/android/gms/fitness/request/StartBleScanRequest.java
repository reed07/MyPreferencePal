package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
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
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.Collections;
import java.util.List;

@Class(creator = "StartBleScanRequestCreator")
@Reserved({5, 1000})
public class StartBleScanRequest extends AbstractSafeParcelable {
    public static final Creator<StartBleScanRequest> CREATOR = new zzbg();
    @Field(getter = "getDataTypes", id = 1)
    private final List<DataType> zzah;
    @Nullable
    @Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    private final zzcq zzgj;
    @Nullable
    @Field(getter = "getBleScanCallbackBinder", id = 2, type = "android.os.IBinder")
    private final zzae zzik;
    @Field(getter = "getTimeoutSecs", id = 3)
    private final int zzil;
    @Nullable
    private final BleScanCallback zzim;

    public static class Builder {
        private DataType[] zzhf = new DataType[0];
        private int zzil = 10;
        private BleScanCallback zzin;

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zzhf = dataTypeArr;
            return this;
        }

        public Builder setBleScanCallback(BleScanCallback bleScanCallback) {
            this.zzin = bleScanCallback;
            return this;
        }

        public Builder setTimeoutSecs(int i) {
            boolean z = true;
            Preconditions.checkArgument(i > 0, "Stop time must be greater than zero");
            if (i > 60) {
                z = false;
            }
            Preconditions.checkArgument(z, "Stop time must be less than 1 minute");
            this.zzil = i;
            return this;
        }

        public StartBleScanRequest build() {
            Preconditions.checkState(this.zzin != null, "Must set BleScanCallback");
            return new StartBleScanRequest((List) ArrayUtils.toArrayList(this.zzhf), this.zzin, this.zzil);
        }
    }

    @Constructor
    StartBleScanRequest(@Param(id = 1) List<DataType> list, @Param(id = 2) IBinder iBinder, @Param(id = 3) int i, @Param(id = 4) IBinder iBinder2) {
        zzae zzae;
        this.zzah = list;
        if (iBinder == null) {
            zzae = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
            if (queryLocalInterface instanceof zzae) {
                zzae = (zzae) queryLocalInterface;
            } else {
                zzae = new zzag(iBinder);
            }
        }
        this.zzik = zzae;
        this.zzil = i;
        this.zzgj = zzcr.zzj(iBinder2);
        this.zzim = null;
    }

    private StartBleScanRequest(List<DataType> list, BleScanCallback bleScanCallback, int i) {
        this.zzah = list;
        this.zzik = null;
        this.zzil = i;
        this.zzgj = null;
        this.zzim = bleScanCallback;
    }

    public StartBleScanRequest(List<DataType> list, @Nullable zzae zzae, int i, @Nullable zzcq zzcq) {
        this.zzah = list;
        this.zzik = zzae;
        this.zzil = i;
        this.zzgj = zzcq;
        this.zzim = null;
    }

    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzah);
    }

    public int getTimeoutSecs() {
        return this.zzil;
    }

    @Nullable
    public final BleScanCallback zzz() {
        return this.zzim;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzah).add("timeoutSecs", Integer.valueOf(this.zzil)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        zzae zzae = this.zzik;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 2, zzae == null ? null : zzae.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 3, getTimeoutSecs());
        zzcq zzcq = this.zzgj;
        if (zzcq != null) {
            iBinder = zzcq.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 4, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
