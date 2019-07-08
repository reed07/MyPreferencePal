package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzwb;
import com.google.android.gms.internal.ads.zzwf;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.SharedConstants.Facebook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzo {
    public static Object[] zza(String str, zzwb zzwb, String str2, int i, @Nullable zzwf zzwf) {
        HashSet hashSet = new HashSet(Arrays.asList(str.split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        if (hashSet.contains("formatString")) {
            arrayList.add(null);
        }
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains(Facebook.BIRTHDAY)) {
            arrayList.add(Long.valueOf(zzwb.zzcjb));
        }
        if (hashSet.contains("extras")) {
            arrayList.add(zza(zzwb.extras));
        }
        if (hashSet.contains("gender")) {
            arrayList.add(Integer.valueOf(zzwb.zzcjc));
        }
        if (hashSet.contains(Attributes.ACHIEVEMENT_KEYWORDS)) {
            if (zzwb.zzcjd != null) {
                arrayList.add(zzwb.zzcjd.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(zzwb.zzcje));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(zzwb.zzcjf));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(zzwb.zzcjg));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(zzwb.zzcjh);
        }
        if (hashSet.contains("location")) {
            if (zzwb.zzcjj != null) {
                arrayList.add(zzwb.zzcjj.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(zzwb.zzcjk);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(zza(zzwb.zzcjl));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(zza(zzwb.zzcjm));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (zzwb.zzcjn != null) {
                arrayList.add(zzwb.zzcjn.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(zzwb.zzcjo);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(zzwb.zzcjp);
        }
        if (hashSet.contains("isDesignedForFamilies")) {
            arrayList.add(Boolean.valueOf(zzwb.zzcjq));
        }
        return arrayList.toArray();
    }

    private static String zza(@Nullable Bundle bundle) {
        String str;
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            Object obj = bundle.get((String) it.next());
            if (obj == null) {
                str = "null";
            } else if (obj instanceof Bundle) {
                str = zza((Bundle) obj);
            } else {
                str = obj.toString();
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
