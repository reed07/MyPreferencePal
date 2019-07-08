package com.samsung.android.sdk.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class HealthDevice implements Parcelable {
    public static final Creator<HealthDevice> CREATOR = new Creator<HealthDevice>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new HealthDevice[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new HealthDevice(parcel, 0);
        }
    };
    public static final int GROUP_COMPANION = 360003;
    public static final int GROUP_EXTERNAL = 360002;
    public static final int GROUP_MOBILE = 360001;
    public static final int GROUP_UNKNOWN = 0;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final int e;
    private final String f;

    public static class Builder {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public int e;

        public Builder setModel(String str) {
            this.b = str;
            return this;
        }

        public Builder setManufacturer(String str) {
            this.c = str;
            return this;
        }

        public Builder setCustomName(String str) {
            this.a = str;
            return this;
        }

        public Builder setDeviceSeed(String str) {
            this.d = str;
            return this;
        }

        public Builder setGroup(int i) {
            this.e = i;
            return this;
        }

        public HealthDevice build() {
            String str = this.d;
            if (str == null || str.isEmpty()) {
                throw new IllegalStateException("Seed is not specified");
            }
            int i = this.e;
            if (i != 0) {
                switch (i) {
                    case HealthDevice.GROUP_MOBILE /*360001*/:
                    case HealthDevice.GROUP_EXTERNAL /*360002*/:
                    case HealthDevice.GROUP_COMPANION /*360003*/:
                        break;
                    default:
                        throw new IllegalStateException("Device group is not set correctly");
                }
            }
            return new HealthDevice(this, 0);
        }
    }

    public final int describeContents() {
        return 0;
    }

    /* synthetic */ HealthDevice(Parcel parcel, byte b2) {
        this(parcel);
    }

    /* synthetic */ HealthDevice(Builder builder, byte b2) {
        this(builder);
    }

    public HealthDevice(String str, String str2, String str3, String str4, String str5, int i) {
        this.a = str;
        this.f = str2;
        this.d = str3;
        this.c = str4;
        this.b = str5;
        this.e = i;
    }

    private HealthDevice(Builder builder) {
        this.a = null;
        this.b = builder.a;
        this.c = builder.b;
        this.d = builder.c;
        this.e = builder.e;
        this.f = builder.d;
    }

    private HealthDevice(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HealthDevice)) {
            return false;
        }
        HealthDevice healthDevice = (HealthDevice) obj;
        String str = this.f;
        if (str != null) {
            String str2 = healthDevice.f;
            if (str2 != null) {
                return str.equals(str2);
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.f;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final String getCustomName() {
        return this.b;
    }

    public final String getModel() {
        return this.c;
    }

    public final String getManufacturer() {
        return this.d;
    }

    public final String getUuid() {
        return this.a;
    }

    public final int getGroup() {
        return this.e;
    }

    public final String getSeed() {
        return this.f;
    }
}
