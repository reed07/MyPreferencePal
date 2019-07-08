package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.provider.Settings.Secure;
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
import com.google.android.gms.internal.fitness.zzi;

@Class(creator = "DeviceCreator")
@Reserved({3, 1000})
public final class Device extends AbstractSafeParcelable {
    public static final Creator<Device> CREATOR = new zzo();
    public static final int TYPE_CHEST_STRAP = 4;
    public static final int TYPE_HEAD_MOUNTED = 6;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_SCALE = 5;
    public static final int TYPE_TABLET = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WATCH = 3;
    @Field(getter = "getType", id = 5)
    private final int type;
    @Field(getter = "getManufacturer", id = 1)
    private final String zzcc;
    @Field(getter = "getModel", id = 2)
    private final String zzcd;
    @Field(getter = "getUid", id = 4)
    private final String zzce;
    @Field(getter = "getPlatformType", id = 6)
    private final int zzcf;

    public static Device getLocalDevice(Context context) {
        int zza = zzi.zza(context);
        Device device = new Device(Build.MANUFACTURER, Build.MODEL, VERSION.RELEASE, Secure.getString(context.getContentResolver(), "android_id"), zza, 2);
        return device;
    }

    public Device(String str, String str2, String str3, int i) {
        this(str, str2, str3, i, 0);
    }

    @Constructor
    public Device(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 4) String str3, @Param(id = 5) int i, @Param(id = 6) int i2) {
        this.zzcc = (String) Preconditions.checkNotNull(str);
        this.zzcd = (String) Preconditions.checkNotNull(str2);
        if (str3 != null) {
            this.zzce = str3;
            this.type = i;
            this.zzcf = i2;
            return;
        }
        throw new IllegalStateException("Device UID is null.");
    }

    @Deprecated
    private Device(String str, String str2, String str3, String str4, int i, int i2) {
        this(str, str2, str4, i, 2);
    }

    public final String getManufacturer() {
        return this.zzcc;
    }

    public final String getModel() {
        return this.zzcd;
    }

    public final String getUid() {
        return this.zzce;
    }

    public final int getType() {
        return this.type;
    }

    /* access modifiers changed from: 0000 */
    public final String getStreamIdentifier() {
        return String.format("%s:%s:%s", new Object[]{this.zzcc, this.zzcd, this.zzce});
    }

    public final String toString() {
        return String.format("Device{%s:%s:%s}", new Object[]{getStreamIdentifier(), Integer.valueOf(this.type), Integer.valueOf(this.zzcf)});
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equal(this.zzcc, device.zzcc) && Objects.equal(this.zzcd, device.zzcd) && Objects.equal(this.zzce, device.zzce) && this.type == device.type && this.zzcf == device.zzcf;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcc, this.zzcd, this.zzce, Integer.valueOf(this.type));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getManufacturer(), false);
        SafeParcelWriter.writeString(parcel, 2, getModel(), false);
        SafeParcelWriter.writeString(parcel, 4, getUid(), false);
        SafeParcelWriter.writeInt(parcel, 5, getType());
        SafeParcelWriter.writeInt(parcel, 6, this.zzcf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
