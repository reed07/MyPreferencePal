package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.HexDumpUtils;
import com.google.android.gms.internal.fitness.zzfa;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

@Class(creator = "ValueCreator")
@Reserved({1000})
public final class Value extends AbstractSafeParcelable {
    public static final Creator<Value> CREATOR = new zzai();
    @Field(getter = "getFormat", id = 1)
    private final int format;
    @Field(getter = "getValue", id = 3)
    private float value;
    @Field(getter = "isSet", id = 2)
    private boolean zzee;
    @Field(getter = "getStringValue", id = 4)
    private String zzef;
    @Field(getter = "getMapValue", id = 5, type = "android.os.Bundle")
    private Map<String, MapValue> zzeg;
    @Field(getter = "getIntArrayValue", id = 6)
    private int[] zzeh;
    @Field(getter = "getFloatArrayValue", id = 7)
    private float[] zzei;
    @Field(getter = "getBlob", id = 8)
    private byte[] zzej;

    public Value(int i) {
        this(i, false, BitmapDescriptorFactory.HUE_RED, null, null, null, null, null);
    }

    @Constructor
    Value(@Param(id = 1) int i, @Param(id = 2) boolean z, @Param(id = 3) float f, @Param(id = 4) String str, @Param(id = 5) Bundle bundle, @Param(id = 6) int[] iArr, @Param(id = 7) float[] fArr, @Param(id = 8) byte[] bArr) {
        ArrayMap arrayMap;
        this.format = i;
        this.zzee = z;
        this.value = f;
        this.zzef = str;
        if (bundle == null) {
            arrayMap = null;
        } else {
            bundle.setClassLoader(MapValue.class.getClassLoader());
            arrayMap = new ArrayMap(bundle.size());
            for (String str2 : bundle.keySet()) {
                arrayMap.put(str2, (MapValue) bundle.getParcelable(str2));
            }
        }
        this.zzeg = arrayMap;
        this.zzeh = iArr;
        this.zzei = fArr;
        this.zzej = bArr;
    }

    public final void setInt(int i) {
        Preconditions.checkState(this.format == 1, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
        this.zzee = true;
        this.value = Float.intBitsToFloat(i);
    }

    public final void setFloat(float f) {
        Preconditions.checkState(this.format == 2, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
        this.zzee = true;
        this.value = f;
    }

    public final void setString(String str) {
        Preconditions.checkState(this.format == 3, "Attempting to set a string value to a field that is not in STRING format.  Please check the data type definition and use the right format.");
        this.zzee = true;
        this.zzef = str;
    }

    public final void setKeyValue(String str, float f) {
        Preconditions.checkState(this.format == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.zzee = true;
        if (this.zzeg == null) {
            this.zzeg = new HashMap();
        }
        this.zzeg.put(str, new MapValue(2, f));
    }

    public final void clearKey(String str) {
        Preconditions.checkState(this.format == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        Map<String, MapValue> map = this.zzeg;
        if (map != null) {
            map.remove(str);
        }
    }

    public final void setActivity(String str) {
        setInt(zzfa.zzl(str));
    }

    public final boolean isSet() {
        return this.zzee;
    }

    public final int getFormat() {
        return this.format;
    }

    public final int asInt() {
        boolean z = true;
        if (this.format != 1) {
            z = false;
        }
        Preconditions.checkState(z, "Value is not in int format");
        return Float.floatToRawIntBits(this.value);
    }

    public final float asFloat() {
        Preconditions.checkState(this.format == 2, "Value is not in float format");
        return this.value;
    }

    public final String asString() {
        Preconditions.checkState(this.format == 3, "Value is not in string format");
        return this.zzef;
    }

    @Nullable
    public final Float getKeyValue(String str) {
        Preconditions.checkState(this.format == 4, "Value is not in float map format");
        Map<String, MapValue> map = this.zzeg;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return Float.valueOf(((MapValue) this.zzeg.get(str)).asFloat());
    }

    public final String asActivity() {
        return zzfa.getName(asInt());
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return false;
        }
        Value value2 = (Value) obj;
        int i = this.format;
        if (i != value2.format || this.zzee != value2.zzee) {
            return false;
        }
        switch (i) {
            case 1:
                return asInt() == value2.asInt();
            case 2:
                return this.value == value2.value;
            case 3:
                return Objects.equal(this.zzef, value2.zzef);
            case 4:
                return Objects.equal(this.zzeg, value2.zzeg);
            case 5:
                return Arrays.equals(this.zzeh, value2.zzeh);
            case 6:
                return Arrays.equals(this.zzei, value2.zzei);
            case 7:
                return Arrays.equals(this.zzej, value2.zzej);
            default:
                return this.value == value2.value;
        }
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.value), this.zzef, this.zzeg, this.zzeh, this.zzei, this.zzej);
    }

    public final String toString() {
        if (!this.zzee) {
            return "unset";
        }
        switch (this.format) {
            case 1:
                return Integer.toString(asInt());
            case 2:
                return Float.toString(this.value);
            case 3:
                return this.zzef;
            case 4:
                return new TreeMap(this.zzeg).toString();
            case 5:
                return Arrays.toString(this.zzeh);
            case 6:
                return Arrays.toString(this.zzei);
            case 7:
                byte[] bArr = this.zzej;
                return HexDumpUtils.dump(bArr, 0, bArr.length, false);
            default:
                return "unknown";
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getFormat());
        SafeParcelWriter.writeBoolean(parcel, 2, isSet());
        SafeParcelWriter.writeFloat(parcel, 3, this.value);
        SafeParcelWriter.writeString(parcel, 4, this.zzef, false);
        Map<String, MapValue> map = this.zzeg;
        if (map == null) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle(map.size());
            for (Entry entry : this.zzeg.entrySet()) {
                bundle2.putParcelable((String) entry.getKey(), (Parcelable) entry.getValue());
            }
            bundle = bundle2;
        }
        SafeParcelWriter.writeBundle(parcel, 5, bundle, false);
        SafeParcelWriter.writeIntArray(parcel, 6, this.zzeh, false);
        SafeParcelWriter.writeFloatArray(parcel, 7, this.zzei, false);
        SafeParcelWriter.writeByteArray(parcel, 8, this.zzej, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
