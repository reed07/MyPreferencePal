package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSet;

public final class zzl implements Creator<zzk> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzk[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataSet dataSet = null;
        IBinder iBinder = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 4) {
                switch (fieldId) {
                    case 1:
                        dataSet = (DataSet) SafeParcelReader.createParcelable(parcel, readHeader, DataSet.CREATOR);
                        break;
                    case 2:
                        iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
            } else {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzk(dataSet, iBinder, z);
    }
}
