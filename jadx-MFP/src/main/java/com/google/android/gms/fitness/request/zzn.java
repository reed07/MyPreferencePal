package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import java.util.List;

public final class zzn implements Creator<DataReadRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataReadRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        List list = null;
        List list2 = null;
        List list3 = null;
        List list4 = null;
        DataSource dataSource = null;
        IBinder iBinder = null;
        List list5 = null;
        List list6 = null;
        List list7 = null;
        List list8 = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    list = SafeParcelReader.createTypedList(parcel2, readHeader, DataType.CREATOR);
                    break;
                case 2:
                    list2 = SafeParcelReader.createTypedList(parcel2, readHeader, DataSource.CREATOR);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 4:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 5:
                    list3 = SafeParcelReader.createTypedList(parcel2, readHeader, DataType.CREATOR);
                    break;
                case 6:
                    list4 = SafeParcelReader.createTypedList(parcel2, readHeader, DataSource.CREATOR);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 8:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 9:
                    dataSource = (DataSource) SafeParcelReader.createParcelable(parcel2, readHeader, DataSource.CREATOR);
                    break;
                case 10:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 12:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 13:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 14:
                    iBinder = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 16:
                    list5 = SafeParcelReader.createTypedList(parcel2, readHeader, Device.CREATOR);
                    break;
                case 17:
                    list6 = SafeParcelReader.createIntegerList(parcel2, readHeader);
                    break;
                case 18:
                    list7 = SafeParcelReader.createLongList(parcel2, readHeader);
                    break;
                case 19:
                    list8 = SafeParcelReader.createLongList(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        DataReadRequest dataReadRequest = new DataReadRequest(list, list2, j, j2, list3, list4, i, j3, dataSource, i2, z, z2, iBinder, list5, list6, list7, list8);
        return dataReadRequest;
    }
}
