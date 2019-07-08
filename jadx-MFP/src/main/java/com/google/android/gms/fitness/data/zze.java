package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zze implements Creator<Bucket> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new Bucket[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Session session = null;
        List list = null;
        long j = 0;
        long j2 = 0;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 3:
                    session = (Session) SafeParcelReader.createParcelable(parcel2, readHeader, Session.CREATOR);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 5:
                    list = SafeParcelReader.createTypedList(parcel2, readHeader, DataSet.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        Bucket bucket = new Bucket(j, j2, session, i, list, i2, z);
        return bucket;
    }
}
