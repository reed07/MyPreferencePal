package com.samsung.android.sdk.accessory;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

public class SAPeerAccessory implements Parcelable {
    public static final Creator<SAPeerAccessory> CREATOR = new Creator<SAPeerAccessory>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new SAPeerAccessory(parcel, 0);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new SAPeerAccessory[i];
        }
    };
    public static final int TRANSPORT_BLE = 4;
    public static final int TRANSPORT_BT = 2;
    public static final int TRANSPORT_MOBILE = 16;
    public static final int TRANSPORT_USB = 8;
    public static final int TRANSPORT_WIFI = 1;
    private long a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private String k;

    private SAPeerAccessory(Parcel parcel) {
        int readInt = parcel.readInt();
        this.a = parcel.readLong();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.h = parcel.readInt();
        this.k = parcel.readString();
        if (readInt >= 8) {
            this.i = parcel.readInt();
        }
        this.g = parcel.readInt();
        this.j = parcel.readInt();
    }

    /* synthetic */ SAPeerAccessory(Parcel parcel, byte b2) {
        this(parcel);
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public final int b() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public final int c() {
        return this.i;
    }

    /* access modifiers changed from: 0000 */
    public final int d() {
        return this.j;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccessoryId() {
        return this.k;
    }

    public String getAddress() {
        return this.b;
    }

    public long getId() {
        return this.a;
    }

    public String getName() {
        return this.c;
    }

    public String getProductId() {
        return this.e;
    }

    public int getTransportType() {
        return this.d;
    }

    public String getVendorId() {
        return this.f;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PeerAccessory - ");
        StringBuilder sb = new StringBuilder("Id:");
        sb.append(this.a);
        stringBuffer.append(sb.toString());
        StringBuilder sb2 = new StringBuilder(" Name:");
        sb2.append(this.c);
        stringBuffer.append(sb2.toString());
        StringBuilder sb3 = new StringBuilder(" Address:");
        sb3.append(this.b);
        sb3.append(" ");
        stringBuffer.append(sb3.toString());
        StringBuilder sb4 = new StringBuilder(" TransportType:");
        sb4.append(this.d);
        stringBuffer.append(sb4.toString());
        StringBuilder sb5 = new StringBuilder(" ProductId:");
        sb5.append(this.e);
        stringBuffer.append(sb5.toString());
        StringBuilder sb6 = new StringBuilder(" VendorId:");
        sb6.append(this.f);
        stringBuffer.append(sb6.toString());
        StringBuilder sb7 = new StringBuilder(" APDU:");
        sb7.append(this.g);
        stringBuffer.append(sb7.toString());
        StringBuilder sb8 = new StringBuilder(" SSDU:");
        sb8.append(this.h);
        stringBuffer.append(sb8.toString());
        StringBuilder sb9 = new StringBuilder(" Accessory ID:");
        sb9.append(this.k);
        stringBuffer.append(sb9.toString());
        StringBuilder sb10 = new StringBuilder(" MXDU:");
        sb10.append(this.i);
        stringBuffer.append(sb10.toString());
        StringBuilder sb11 = new StringBuilder(" Encryption padding:");
        sb11.append(this.j);
        stringBuffer.append(sb11.toString());
        return stringBuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(9);
        parcel.writeLong(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.h);
        parcel.writeString(this.k);
        Log.v("[SA_SDK]SAPeerAccesssosry", "mCompatibilityVersion = 0");
        if (i.i()) {
            parcel.writeInt(this.i);
        }
        parcel.writeInt(this.g);
        parcel.writeInt(this.j);
    }
}
