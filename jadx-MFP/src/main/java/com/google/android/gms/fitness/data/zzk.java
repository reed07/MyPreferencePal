package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzk implements Creator<DataSource> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataSource[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataType dataType = null;
        String str = null;
        Device device = null;
        zzb zzb = null;
        String str2 = null;
        int[] iArr = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    dataType = (DataType) SafeParcelReader.createParcelable(parcel, readHeader, DataType.CREATOR);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    device = (Device) SafeParcelReader.createParcelable(parcel, readHeader, Device.CREATOR);
                    break;
                case 5:
                    zzb = (zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzb.CREATOR);
                    break;
                case 6:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 8:
                    iArr = SafeParcelReader.createIntArray(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        DataSource dataSource = new DataSource(dataType, str, i, device, zzb, str2, iArr);
        return dataSource;
    }
}
