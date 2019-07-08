package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "SessionDataSetCreator")
@Reserved({1000})
public final class zzae extends AbstractSafeParcelable {
    public static final Creator<zzae> CREATOR = new zzaf();
    @Field(getter = "getDataSet", id = 2)
    private final DataSet zzeb;
    @Field(getter = "getSession", id = 1)
    private final Session zzz;

    @Constructor
    public zzae(@Param(id = 1) Session session, @Param(id = 2) DataSet dataSet) {
        this.zzz = session;
        this.zzeb = dataSet;
    }

    public final Session getSession() {
        return this.zzz;
    }

    public final DataSet getDataSet() {
        return this.zzeb;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzae)) {
            return false;
        }
        zzae zzae = (zzae) obj;
        return Objects.equal(this.zzz, zzae.zzz) && Objects.equal(this.zzeb, zzae.zzeb);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzz, this.zzeb);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.zzz).add("dataSet", this.zzeb).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzz, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzeb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
