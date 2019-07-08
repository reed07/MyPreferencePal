package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class zzai implements Creator<Value> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new Value[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        Bundle bundle = null;
        int[] iArr = null;
        float[] fArr = null;
        byte[] bArr = null;
        int i = 0;
        boolean z = false;
        float f = BitmapDescriptorFactory.HUE_RED;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 3:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 4:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 6:
                    iArr = SafeParcelReader.createIntArray(parcel, readHeader);
                    break;
                case 7:
                    fArr = SafeParcelReader.createFloatArray(parcel, readHeader);
                    break;
                case 8:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        Value value = new Value(i, z, f, str, bundle, iArr, fArr, bArr);
        return value;
    }
}
