package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.List;

public final class zzad implements Creator<GoalsReadRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoalsReadRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 2:
                    SafeParcelReader.readList(parcel, readHeader, arrayList, getClass().getClassLoader());
                    break;
                case 3:
                    SafeParcelReader.readList(parcel, readHeader, arrayList2, getClass().getClassLoader());
                    break;
                case 4:
                    SafeParcelReader.readList(parcel, readHeader, arrayList3, getClass().getClassLoader());
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GoalsReadRequest(iBinder, (List<DataType>) arrayList, (List<Integer>) arrayList2, (List<Integer>) arrayList3);
    }
}
