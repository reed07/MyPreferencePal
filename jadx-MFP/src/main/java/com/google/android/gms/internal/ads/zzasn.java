package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzasn implements Creator<zzasm> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzasm[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        String str = null;
        String str2 = null;
        List list = null;
        List list2 = null;
        List list3 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        zzasy zzasy = null;
        String str7 = null;
        String str8 = null;
        zzawd zzawd = null;
        List list4 = null;
        List list5 = null;
        zzaso zzaso = null;
        String str9 = null;
        List list6 = null;
        String str10 = null;
        zzawo zzawo = null;
        String str11 = null;
        Bundle bundle = null;
        List list7 = null;
        String str12 = null;
        String str13 = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        boolean z13 = false;
        boolean z14 = false;
        boolean z15 = false;
        int i4 = 0;
        boolean z16 = false;
        boolean z17 = false;
        boolean z18 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    list = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 5:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 6:
                    list2 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 7:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 9:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 10:
                    list3 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 11:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 13:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 14:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 15:
                    str4 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 18:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 19:
                    str5 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 21:
                    str6 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 22:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 23:
                    z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 24:
                    z5 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 25:
                    z6 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 26:
                    z7 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 28:
                    zzasy = (zzasy) SafeParcelReader.createParcelable(parcel2, readHeader, zzasy.CREATOR);
                    break;
                case 29:
                    str7 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 30:
                    str8 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 31:
                    z8 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 32:
                    z9 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 33:
                    zzawd = (zzawd) SafeParcelReader.createParcelable(parcel2, readHeader, zzawd.CREATOR);
                    break;
                case 34:
                    list4 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 35:
                    list5 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 36:
                    z10 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 37:
                    zzaso = (zzaso) SafeParcelReader.createParcelable(parcel2, readHeader, zzaso.CREATOR);
                    break;
                case 38:
                    z11 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 39:
                    str9 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 40:
                    list6 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 42:
                    z12 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 43:
                    str10 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 44:
                    zzawo = (zzawo) SafeParcelReader.createParcelable(parcel2, readHeader, zzawo.CREATOR);
                    break;
                case 45:
                    str11 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 46:
                    z13 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 47:
                    z14 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 48:
                    bundle = SafeParcelReader.createBundle(parcel2, readHeader);
                    break;
                case 49:
                    z15 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 50:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 51:
                    z16 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 52:
                    list7 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 53:
                    z17 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 54:
                    str12 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 55:
                    str13 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 56:
                    z18 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        zzasm zzasm = new zzasm(i, str, str2, list, i2, list2, j, z, j2, list3, j3, i3, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, zzasy, str7, str8, z8, z9, zzawd, list4, list5, z10, zzaso, z11, str9, list6, z12, str10, zzawo, str11, z13, z14, bundle, z15, i4, z16, list7, z17, str12, str13, z18);
        return zzasm;
    }
}
