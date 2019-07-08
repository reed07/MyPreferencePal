package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@zzark
public final class zzamv {
    public static int zza(ErrorCode errorCode) {
        switch (zzamw.zzdol[errorCode.ordinal()]) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    public static MediationAdRequest zza(zzwb zzwb, boolean z) {
        Gender gender;
        Set hashSet = zzwb.zzcjd != null ? new HashSet(zzwb.zzcjd) : null;
        Date date = new Date(zzwb.zzcjb);
        switch (zzwb.zzcjc) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            default:
                gender = Gender.UNKNOWN;
                break;
        }
        MediationAdRequest mediationAdRequest = new MediationAdRequest(date, gender, hashSet, z, zzwb.zzcjj);
        return mediationAdRequest;
    }
}
