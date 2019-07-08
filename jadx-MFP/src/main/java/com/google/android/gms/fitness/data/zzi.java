package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzi implements Creator<DataSet> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataSet[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        DataSource dataSource = null;
        List list = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, readHeader, DataSource.CREATOR);
            } else if (fieldId != 1000) {
                switch (fieldId) {
                    case 3:
                        SafeParcelReader.readList(parcel, readHeader, arrayList, getClass().getClassLoader());
                        break;
                    case 4:
                        list = SafeParcelReader.createTypedList(parcel, readHeader, DataSource.CREATOR);
                        break;
                    case 5:
                        z = SafeParcelReader.readBoolean(parcel, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
            } else {
                i = SafeParcelReader.readInt(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        DataSet dataSet = new DataSet(i, dataSource, arrayList, list, z);
        return dataSet;
    }
}
