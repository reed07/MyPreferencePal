package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import java.util.ArrayList;
import java.util.List;

public final class zzc implements Creator<DataReadResult> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataReadResult[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Status status = null;
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    SafeParcelReader.readList(parcel, readHeader, arrayList, getClass().getClassLoader());
                    break;
                case 2:
                    status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
                    break;
                case 3:
                    SafeParcelReader.readList(parcel, readHeader, arrayList2, getClass().getClassLoader());
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 6:
                    list = SafeParcelReader.createTypedList(parcel, readHeader, DataSource.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        DataReadResult dataReadResult = new DataReadResult(arrayList, status, arrayList2, i, list);
        return dataReadResult;
    }
}
