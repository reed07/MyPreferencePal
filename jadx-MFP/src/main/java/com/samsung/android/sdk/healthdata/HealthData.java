package com.samsung.android.sdk.healthdata;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class HealthData implements Parcelable {
    public static final Creator<HealthData> CREATOR = new Creator<HealthData>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new HealthData[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new HealthData(parcel, 0);
        }
    };
    private String a;
    private String b;
    private final ContentValues c;
    private Map<String, byte[]> d;

    public final int describeContents() {
        return 0;
    }

    /* synthetic */ HealthData(Parcel parcel, byte b2) {
        this(parcel);
    }

    public HealthData() {
        this.c = new ContentValues();
        this.d = new HashMap();
        a();
    }

    private void a() {
        this.a = UUID.randomUUID().toString();
    }

    private HealthData(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = (ContentValues) ContentValues.CREATOR.createFromParcel(parcel);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        this.c.writeToParcel(parcel, 0);
    }

    public final String getUuid() {
        return this.a;
    }

    public final String getSourceDevice() {
        return this.b;
    }

    public final void setSourceDevice(String str) {
        this.b = str;
    }

    public final String getString(String str) {
        return this.c.getAsString(str);
    }

    public final float getFloat(String str) {
        Float asFloat = this.c.getAsFloat(str);
        if (asFloat == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return asFloat.floatValue();
    }

    public final double getDouble(String str) {
        Double asDouble = this.c.getAsDouble(str);
        if (asDouble == null) {
            return 0.0d;
        }
        return asDouble.doubleValue();
    }

    public final long getLong(String str) {
        Long asLong = this.c.getAsLong(str);
        if (asLong == null) {
            return 0;
        }
        return asLong.longValue();
    }

    public final int getInt(String str) {
        Integer asInteger = this.c.getAsInteger(str);
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public final byte[] getBlob(String str) {
        byte[] bArr = (byte[]) this.d.get(str);
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public final void putInt(String str, int i) {
        this.c.put(str, Integer.valueOf(i));
    }

    public final void putLong(String str, long j) {
        this.c.put(str, Long.valueOf(j));
    }

    public final void putFloat(String str, float f) {
        this.c.put(str, Float.valueOf(f));
    }

    public final void putDouble(String str, double d2) {
        this.c.put(str, Double.valueOf(d2));
    }

    public final void putString(String str, String str2) {
        this.c.put(str, str2);
        this.d.remove(str);
    }

    public final void putBlob(String str, byte[] bArr) {
        if (bArr == null) {
            this.c.put(str, null);
        } else {
            this.c.put(str, UUID.randomUUID().toString().getBytes(Charset.forName("UTF-8")));
        }
        this.d.put(str, bArr);
    }

    public final void putNull(String str) {
        this.c.putNull(str);
        this.d.put(str, null);
    }

    public final Object get(String str) {
        return this.c.get(str);
    }

    public final void clear() {
        this.a = null;
        this.b = null;
        this.c.clear();
        this.d.clear();
        a();
    }

    public final Set<String> getKeySet() {
        return this.c.keySet();
    }

    public final Set<String> getBlobKeySet() {
        return this.d.keySet();
    }

    public final void remove(String str) {
        this.c.remove(str);
    }

    public final ContentValues getContentValues() {
        return this.c;
    }
}
