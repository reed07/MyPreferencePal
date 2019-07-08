package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.appindexing.internal.Thing.zza;

public final class zzad implements Creator<Thing> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new Thing[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundle = null;
        zza zza = null;
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1000) {
                switch (fieldId) {
                    case 1:
                        bundle = SafeParcelReader.createBundle(parcel, readHeader);
                        break;
                    case 2:
                        zza = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                        break;
                    case 3:
                        str = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 4:
                        str2 = SafeParcelReader.createString(parcel, readHeader);
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
        Thing thing = new Thing(i, bundle, zza, str, str2);
        return thing;
    }
}
