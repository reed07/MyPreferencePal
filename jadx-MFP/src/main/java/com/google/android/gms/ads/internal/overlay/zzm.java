package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.zzaq;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.ads.zzbbi;

public final class zzm implements Creator<AdOverlayInfoParcel> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new AdOverlayInfoParcel[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzc zzc = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        String str = null;
        String str2 = null;
        IBinder iBinder5 = null;
        String str3 = null;
        zzbbi zzbbi = null;
        String str4 = null;
        zzaq zzaq = null;
        IBinder iBinder6 = null;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    zzc = (zzc) SafeParcelReader.createParcelable(parcel2, readHeader, zzc.CREATOR);
                    break;
                case 3:
                    iBinder = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 4:
                    iBinder2 = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 5:
                    iBinder3 = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 6:
                    iBinder4 = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 7:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 9:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 10:
                    iBinder5 = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 11:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 12:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 13:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 14:
                    zzbbi = (zzbbi) SafeParcelReader.createParcelable(parcel2, readHeader, zzbbi.CREATOR);
                    break;
                case 16:
                    str4 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 17:
                    zzaq = (zzaq) SafeParcelReader.createParcelable(parcel2, readHeader, zzaq.CREATOR);
                    break;
                case 18:
                    iBinder6 = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(zzc, iBinder, iBinder2, iBinder3, iBinder4, str, z, str2, iBinder5, i, i2, str3, zzbbi, str4, zzaq, iBinder6);
        return adOverlayInfoParcel;
    }
}
