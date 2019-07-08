package com.google.android.gms.internal.icing;

import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@VisibleForTesting
public final class zzr {
    @VisibleForTesting
    static final String[] zzy = {"text1", "text2", InMobiNetworkValues.ICON, "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity", "thing_proto"};
    private static final Map<String, Integer> zzz = new HashMap(zzy.length);

    public static String zza(int i) {
        if (i >= 0) {
            String[] strArr = zzy;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return null;
    }

    public static int zzb(String str) {
        Integer num = (Integer) zzz.get(str);
        if (num != null) {
            return num.intValue();
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 44);
        sb.append("[");
        sb.append(str);
        sb.append("] is not a valid global search section name");
        throw new IllegalArgumentException(sb.toString());
    }

    static {
        int i = 0;
        while (true) {
            String[] strArr = zzy;
            if (i < strArr.length) {
                zzz.put(strArr[i], Integer.valueOf(i));
                i++;
            } else {
                return;
            }
        }
    }
}
