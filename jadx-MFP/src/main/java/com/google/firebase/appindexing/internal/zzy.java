package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzy implements Creator<zzx> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzx[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Thing[] thingArr = null;
        int i = 0;
        String[] strArr = null;
        String[] strArr2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 5) {
                switch (fieldId) {
                    case 1:
                        i = SafeParcelReader.readInt(parcel, readHeader);
                        break;
                    case 2:
                        thingArr = (Thing[]) SafeParcelReader.createTypedArray(parcel, readHeader, Thing.CREATOR);
                        break;
                    case 3:
                        strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
            } else {
                strArr2 = SafeParcelReader.createStringArray(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzx(i, thingArr, strArr, strArr2);
    }
}
