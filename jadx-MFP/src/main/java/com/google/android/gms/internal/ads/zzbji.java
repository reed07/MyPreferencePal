package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "GassResponseParcelCreator")
public final class zzbji extends AbstractSafeParcelable {
    public static final Creator<zzbji> CREATOR = new zzbjj();
    @VersionField(id = 1)
    private final int versionCode;
    @Field(getter = "getAfmaSignalsAsBytes", id = 2, type = "byte[]")
    private zzbl zzfcw = null;
    private byte[] zzfcx;

    @Constructor
    zzbji(@Param(id = 1) int i, @Param(id = 2) byte[] bArr) {
        this.versionCode = i;
        this.zzfcx = bArr;
        zzafo();
    }

    public final zzbl zzafn() {
        if (!(this.zzfcw != null)) {
            try {
                this.zzfcw = (zzbl) zzbuz.zza(new zzbl(), this.zzfcx);
                this.zzfcx = null;
            } catch (zzbuy e) {
                throw new IllegalStateException(e);
            }
        }
        zzafo();
        return this.zzfcw;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        byte[] bArr = this.zzfcx;
        if (bArr == null) {
            bArr = zzbuz.zzb(this.zzfcw);
        }
        SafeParcelWriter.writeByteArray(parcel, 2, bArr, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private final void zzafo() {
        if (this.zzfcw == null && this.zzfcx != null) {
            return;
        }
        if (this.zzfcw != null && this.zzfcx == null) {
            return;
        }
        if (this.zzfcw != null && this.zzfcx != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (this.zzfcw == null && this.zzfcx == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }
}
