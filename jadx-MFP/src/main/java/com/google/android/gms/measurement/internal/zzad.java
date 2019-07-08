package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Iterator;

@Class(creator = "EventParamsCreator")
@Reserved({1})
public final class zzad extends AbstractSafeParcelable implements Iterable<String> {
    public static final Creator<zzad> CREATOR = new zzaf();
    /* access modifiers changed from: private */
    @Field(getter = "z", id = 2)
    public final Bundle zzaid;

    @Constructor
    zzad(@Param(id = 2) Bundle bundle) {
        this.zzaid = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zziy(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: 0000 */
    public final Object get(String str) {
        return this.zzaid.get(str);
    }

    /* access modifiers changed from: 0000 */
    public final Long getLong(String str) {
        return Long.valueOf(this.zzaid.getLong(str));
    }

    /* access modifiers changed from: 0000 */
    public final Double zzbr(String str) {
        return Double.valueOf(this.zzaid.getDouble(str));
    }

    /* access modifiers changed from: 0000 */
    public final String getString(String str) {
        return this.zzaid.getString(str);
    }

    public final int size() {
        return this.zzaid.size();
    }

    public final String toString() {
        return this.zzaid.toString();
    }

    public final Bundle zziy() {
        return new Bundle(this.zzaid);
    }

    public final Iterator<String> iterator() {
        return new zzae(this);
    }
}
