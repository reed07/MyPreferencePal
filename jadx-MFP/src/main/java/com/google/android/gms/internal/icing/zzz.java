package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzz implements Creator<zzx> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzx[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzj zzj = null;
        String str = null;
        zzg zzg = null;
        String str2 = null;
        long j = 0;
        int i = 0;
        boolean z = false;
        int i2 = -1;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzj = (zzj) SafeParcelReader.createParcelable(parcel2, readHeader, zzj.CREATOR);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 4:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 5:
                    zzg = (zzg) SafeParcelReader.createParcelable(parcel2, readHeader, zzg.CREATOR);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 7:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 8:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 9:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        zzx zzx = new zzx(zzj, j, i, str, zzg, z, i2, i3, str2);
        return zzx;
    }
}
