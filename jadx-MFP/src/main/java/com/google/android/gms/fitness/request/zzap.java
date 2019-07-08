package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public final class zzap implements Creator<zzao> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzao[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        DataSource dataSource = null;
        DataType dataType = null;
        IBinder iBinder = null;
        PendingIntent pendingIntent = null;
        List list = null;
        IBinder iBinder2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    dataSource = (DataSource) SafeParcelReader.createParcelable(parcel2, readHeader, DataSource.CREATOR);
                    break;
                case 2:
                    dataType = (DataType) SafeParcelReader.createParcelable(parcel2, readHeader, DataType.CREATOR);
                    break;
                case 3:
                    iBinder = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 5:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 6:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 7:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 8:
                    pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel2, readHeader, PendingIntent.CREATOR);
                    break;
                case 9:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 10:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 11:
                    list = SafeParcelReader.createTypedList(parcel2, readHeader, LocationRequest.CREATOR);
                    break;
                case 12:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 13:
                    iBinder2 = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        zzao zzao = new zzao(dataSource, dataType, iBinder, i, i2, j, j2, pendingIntent, j3, i3, list, j4, iBinder2);
        return zzao;
    }
}
