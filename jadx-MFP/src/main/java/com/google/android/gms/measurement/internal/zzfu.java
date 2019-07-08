package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "UserAttributeParcelCreator")
public final class zzfu extends AbstractSafeParcelable {
    public static final Creator<zzfu> CREATOR = new zzfv();
    @Field(id = 2)
    public final String name;
    @Field(id = 7)
    public final String origin;
    @Field(id = 1)
    private final int versionCode;
    @Field(id = 6)
    public final String zzamn;
    @Field(id = 3)
    public final long zzaum;
    @Field(id = 4)
    public final Long zzaun;
    @Field(id = 5)
    private final Float zzauo;
    @Field(id = 8)
    public final Double zzaup;

    zzfu(zzfw zzfw) {
        this(zzfw.name, zzfw.zzaum, zzfw.value, zzfw.origin);
    }

    zzfu(String str, long j, Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.versionCode = 2;
        this.name = str;
        this.zzaum = j;
        this.origin = str2;
        if (obj == null) {
            this.zzaun = null;
            this.zzauo = null;
            this.zzaup = null;
            this.zzamn = null;
        } else if (obj instanceof Long) {
            this.zzaun = (Long) obj;
            this.zzauo = null;
            this.zzaup = null;
            this.zzamn = null;
        } else if (obj instanceof String) {
            this.zzaun = null;
            this.zzauo = null;
            this.zzaup = null;
            this.zzamn = (String) obj;
        } else if (obj instanceof Double) {
            this.zzaun = null;
            this.zzauo = null;
            this.zzaup = (Double) obj;
            this.zzamn = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    @Constructor
    zzfu(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) Long l, @Param(id = 5) Float f, @Param(id = 6) String str2, @Param(id = 7) String str3, @Param(id = 8) Double d) {
        this.versionCode = i;
        this.name = str;
        this.zzaum = j;
        this.zzaun = l;
        Double d2 = null;
        this.zzauo = null;
        if (i == 1) {
            if (f != null) {
                d2 = Double.valueOf(f.doubleValue());
            }
            this.zzaup = d2;
        } else {
            this.zzaup = d;
        }
        this.zzamn = str2;
        this.origin = str3;
    }

    public final Object getValue() {
        Long l = this.zzaun;
        if (l != null) {
            return l;
        }
        Double d = this.zzaup;
        if (d != null) {
            return d;
        }
        String str = this.zzamn;
        if (str != null) {
            return str;
        }
        return null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzaum);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zzaun, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, null, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzamn, false);
        SafeParcelWriter.writeString(parcel, 7, this.origin, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, this.zzaup, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
