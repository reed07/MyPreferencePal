package com.google.android.gms.internal.icing;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;

@ShowFirstParty
@Class(creator = "FeatureCreator")
@Reserved({1000})
public final class zzn extends AbstractSafeParcelable {
    public static final Creator<zzn> CREATOR = new zzo();
    @Field(id = 1)
    private final int id;
    @Field(id = 2)
    private final Bundle zzu;

    @Constructor
    zzn(@Param(id = 1) int i, @Param(id = 2) Bundle bundle) {
        this.id = i;
        this.zzu = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.id);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int hashCode() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.id));
        Bundle bundle = this.zzu;
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                arrayList.add(str);
                arrayList.add(this.zzu.getString(str));
            }
        }
        return Objects.hashCode(arrayList.toArray(new Object[0]));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzn)) {
            return false;
        }
        zzn zzn = (zzn) obj;
        if (this.id != zzn.id) {
            return false;
        }
        Bundle bundle = this.zzu;
        if (bundle == null) {
            return zzn.zzu == null;
        }
        if (zzn.zzu == null || bundle.size() != zzn.zzu.size()) {
            return false;
        }
        for (String str : this.zzu.keySet()) {
            if (zzn.zzu.containsKey(str)) {
                if (!Objects.equal(this.zzu.getString(str), zzn.zzu.getString(str))) {
                }
            }
            return false;
        }
        return true;
    }
}
