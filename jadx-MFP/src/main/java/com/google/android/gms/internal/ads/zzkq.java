package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzkq extends zzkp {
    public static final Creator<zzkq> CREATOR = new zzkr();
    private final String description;
    private final String value;

    public zzkq(String str, String str2, String str3) {
        super(str);
        this.description = null;
        this.value = str3;
    }

    zzkq(Parcel parcel) {
        super(parcel.readString());
        this.description = parcel.readString();
        this.value = parcel.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzkq zzkq = (zzkq) obj;
        return this.zzze.equals(zzkq.zzze) && zzqe.zza((Object) this.description, (Object) zzkq.description) && zzqe.zza((Object) this.value, (Object) zzkq.value);
    }

    public final int hashCode() {
        int hashCode = (this.zzze.hashCode() + 527) * 31;
        String str = this.description;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.value;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzze);
        parcel.writeString(this.description);
        parcel.writeString(this.value);
    }
}
