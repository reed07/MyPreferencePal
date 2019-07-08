package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import java.util.List;

public final class zzau implements Creator<SessionInsertRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new SessionInsertRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Session session = null;
        List list = null;
        List list2 = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    session = (Session) SafeParcelReader.createParcelable(parcel, readHeader, Session.CREATOR);
                    break;
                case 2:
                    list = SafeParcelReader.createTypedList(parcel, readHeader, DataSet.CREATOR);
                    break;
                case 3:
                    list2 = SafeParcelReader.createTypedList(parcel, readHeader, DataPoint.CREATOR);
                    break;
                case 4:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SessionInsertRequest(session, list, list2, iBinder);
    }
}
