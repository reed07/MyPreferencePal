package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

@Class(creator = "DataSourceCreator")
@Reserved({7, 1000})
public class DataSource extends AbstractSafeParcelable {
    public static final Creator<DataSource> CREATOR = new zzk();
    public static final int DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003 = 8;
    public static final int DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013 = 9;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_AAMI = 3;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A = 4;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B = 5;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A = 6;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B = 7;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_ESH2002 = 1;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_ESH2010 = 2;
    public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
    public static final int TYPE_DERIVED = 1;
    public static final int TYPE_RAW = 0;
    private static final int[] zzaw = new int[0];
    @Nullable
    @Field(getter = "getName", id = 2)
    private final String name;
    @Field(getter = "getType", id = 3)
    private final int type;
    @Nullable
    @Field(getter = "getDevice", id = 4)
    private final Device zzax;
    @Nullable
    @Field(getter = "getApplication", id = 5)
    private final zzb zzay;
    @Field(getter = "getStreamName", id = 6)
    private final String zzaz;
    @Field(getter = "getDataQualityStandards", id = 8)
    private final int[] zzba;
    private final String zzbb;
    @Field(getter = "getDataType", id = 1)
    private final DataType zzq;

    public static final class Builder {
        /* access modifiers changed from: private */
        @Nullable
        public String name;
        /* access modifiers changed from: private */
        public int type = -1;
        /* access modifiers changed from: private */
        public Device zzax;
        /* access modifiers changed from: private */
        public zzb zzay;
        /* access modifiers changed from: private */
        public String zzaz = "";
        /* access modifiers changed from: private */
        public int[] zzba;
        /* access modifiers changed from: private */
        public DataType zzq;

        public final Builder setDataType(DataType dataType) {
            this.zzq = dataType;
            return this;
        }

        public final Builder setType(int i) {
            this.type = i;
            return this;
        }

        public final Builder setName(@Nullable String str) {
            this.name = str;
            return this;
        }

        public final Builder setDevice(Device device) {
            this.zzax = device;
            return this;
        }

        public final Builder setAppPackageName(String str) {
            this.zzay = zzb.zza(str);
            return this;
        }

        public final Builder setAppPackageName(Context context) {
            return setAppPackageName(context.getPackageName());
        }

        public final Builder setStreamName(String str) {
            Preconditions.checkArgument(str != null, "Must specify a valid stream name");
            this.zzaz = str;
            return this;
        }

        public final Builder setDataQualityStandards(int... iArr) {
            this.zzba = iArr;
            return this;
        }

        public final DataSource build() {
            boolean z = true;
            Preconditions.checkState(this.zzq != null, "Must set data type");
            if (this.type < 0) {
                z = false;
            }
            Preconditions.checkState(z, "Must set data source type");
            return new DataSource(this);
        }
    }

    @Constructor
    public DataSource(@Param(id = 1) DataType dataType, @Nullable @Param(id = 2) String str, @Param(id = 3) int i, @Nullable @Param(id = 4) Device device, @Nullable @Param(id = 5) zzb zzb, @Param(id = 6) String str2, @Nullable @Param(id = 8) int[] iArr) {
        this.zzq = dataType;
        this.type = i;
        this.name = str;
        this.zzax = device;
        this.zzay = zzb;
        this.zzaz = str2;
        this.zzbb = zzj();
        if (iArr == null) {
            iArr = zzaw;
        }
        this.zzba = iArr;
    }

    public static String zzd(int i) {
        switch (i) {
            case 1:
                return "blood_pressure_esh2002";
            case 2:
                return "blood_pressure_esh2010";
            case 3:
                return "blood_pressure_aami";
            case 4:
                return "blood_pressure_bhs_a_a";
            case 5:
                return "blood_pressure_bhs_a_b";
            case 6:
                return "blood_pressure_bhs_b_a";
            case 7:
                return "blood_pressure_bhs_b_b";
            case 8:
                return "blood_glucose_iso151972003";
            case 9:
                return "blood_glucose_iso151972013";
            default:
                return "unknown";
        }
    }

    private DataSource(Builder builder) {
        this.zzq = builder.zzq;
        this.type = builder.type;
        this.name = builder.name;
        this.zzax = builder.zzax;
        this.zzay = builder.zzay;
        this.zzaz = builder.zzaz;
        this.zzbb = zzj();
        this.zzba = builder.zzba;
    }

    @Nullable
    public static DataSource extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataSource) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_SOURCE, CREATOR);
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public int getType() {
        return this.type;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    @Nullable
    public String getAppPackageName() {
        zzb zzb = this.zzay;
        if (zzb == null) {
            return null;
        }
        return zzb.getPackageName();
    }

    @Nullable
    public final zzb zzi() {
        return this.zzay;
    }

    @Nullable
    public Device getDevice() {
        return this.zzax;
    }

    public String getStreamName() {
        return this.zzaz;
    }

    public int[] getDataQualityStandards() {
        return this.zzba;
    }

    public String getStreamIdentifier() {
        return this.zzbb;
    }

    private final String zzj() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTypeString());
        sb.append(":");
        sb.append(this.zzq.getName());
        if (this.zzay != null) {
            sb.append(":");
            sb.append(this.zzay.getPackageName());
        }
        if (this.zzax != null) {
            sb.append(":");
            sb.append(this.zzax.getStreamIdentifier());
        }
        if (this.zzaz != null) {
            sb.append(":");
            sb.append(this.zzaz);
        }
        return sb.toString();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataSource)) {
            return false;
        }
        return this.zzbb.equals(((DataSource) obj).zzbb);
    }

    public int hashCode() {
        return this.zzbb.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(getTypeString());
        if (this.name != null) {
            sb.append(":");
            sb.append(this.name);
        }
        if (this.zzay != null) {
            sb.append(":");
            sb.append(this.zzay);
        }
        if (this.zzax != null) {
            sb.append(":");
            sb.append(this.zzax);
        }
        if (this.zzaz != null) {
            sb.append(":");
            sb.append(this.zzaz);
        }
        sb.append(":");
        sb.append(this.zzq);
        sb.append("}");
        return sb.toString();
    }

    public final String toDebugString() {
        String str;
        String str2;
        String str3;
        String str4;
        switch (this.type) {
            case 0:
                str = "r";
                break;
            case 1:
                str = "d";
                break;
            case 2:
                str = "c";
                break;
            case 3:
                str = "v";
                break;
            default:
                str = "?";
                break;
        }
        String zzm = this.zzq.zzm();
        zzb zzb = this.zzay;
        if (zzb == null) {
            str2 = "";
        } else if (zzb.equals(zzb.zzad)) {
            str2 = ":gms";
        } else {
            String str5 = ":";
            String valueOf = String.valueOf(this.zzay.getPackageName());
            str2 = valueOf.length() != 0 ? str5.concat(valueOf) : new String(str5);
        }
        Device device = this.zzax;
        if (device != null) {
            String model = device.getModel();
            String uid = this.zzax.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(model).length() + 2 + String.valueOf(uid).length());
            sb.append(":");
            sb.append(model);
            sb.append(":");
            sb.append(uid);
            str3 = sb.toString();
        } else {
            str3 = "";
        }
        String str6 = this.zzaz;
        if (str6 != null) {
            String str7 = ":";
            String valueOf2 = String.valueOf(str6);
            str4 = valueOf2.length() != 0 ? str7.concat(valueOf2) : new String(str7);
        } else {
            str4 = "";
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(zzm).length() + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
        sb2.append(str);
        sb2.append(":");
        sb2.append(zzm);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
        return sb2.toString();
    }

    private final String getTypeString() {
        switch (this.type) {
            case 0:
                return "raw";
            case 1:
                return "derived";
            case 2:
                return "cleaned";
            case 3:
                return "converted";
            default:
                return "derived";
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataType(), i, false);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDevice(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzay, i, false);
        SafeParcelWriter.writeString(parcel, 6, getStreamName(), false);
        SafeParcelWriter.writeIntArray(parcel, 8, getDataQualityStandards(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
