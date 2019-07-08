package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSet;

public final class zzb implements Creator<DailyTotalResult> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DailyTotalResult[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Status status = null;
        DataSet dataSet = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
                    break;
                case 2:
                    dataSet = (DataSet) SafeParcelReader.createParcelable(parcel, readHeader, DataSet.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DailyTotalResult(status, dataSet);
    }
}
