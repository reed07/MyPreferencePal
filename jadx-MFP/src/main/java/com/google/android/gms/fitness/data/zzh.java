package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzh implements Creator<DataPoint> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataPoint[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataSource dataSource = null;
        Value[] valueArr = null;
        DataSource dataSource2 = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1) {
                switch (fieldId) {
                    case 3:
                        j = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    case 4:
                        j2 = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    case 5:
                        valueArr = (Value[]) SafeParcelReader.createTypedArray(parcel2, readHeader, Value.CREATOR);
                        break;
                    case 6:
                        dataSource2 = (DataSource) SafeParcelReader.createParcelable(parcel2, readHeader, DataSource.CREATOR);
                        break;
                    case 7:
                        j3 = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    case 8:
                        j4 = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel2, readHeader);
                        break;
                }
            } else {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel2, readHeader, DataSource.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        DataPoint dataPoint = new DataPoint(dataSource, j, j2, valueArr, dataSource2, j3, j4);
        return dataPoint;
    }
}
