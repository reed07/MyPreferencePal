package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "MapValueCreator")
@Reserved({1000})
public class MapValue extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<MapValue> CREATOR = new zzw();
    @Field(getter = "getFormat", id = 1)
    private final int format;
    @Field(getter = "getValue", id = 2)
    private final float value;

    @Constructor
    public MapValue(@Param(id = 1) int i, @Param(id = 2) float f) {
        this.format = i;
        this.value = f;
    }

    public final float asFloat() {
        Preconditions.checkState(this.format == 2, "Value is not in float format");
        return this.value;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MapValue)) {
            return false;
        }
        MapValue mapValue = (MapValue) obj;
        int i = this.format;
        if (i == mapValue.format) {
            return i != 2 ? this.value == mapValue.value : asFloat() == mapValue.asFloat();
        }
        return false;
    }

    public int hashCode() {
        return (int) this.value;
    }

    public String toString() {
        if (this.format != 2) {
            return "unknown";
        }
        return Float.toString(asFloat());
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.format);
        SafeParcelWriter.writeFloat(parcel, 2, this.value);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
