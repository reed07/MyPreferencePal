package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@VisibleForTesting
final class zzel extends zzfz {
    private static final String ID = zza.REGEX.toString();
    private static final String zzbeo = zzb.IGNORE_CASE.toString();

    public zzel() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(String str, String str2, Map<String, zzp> map) {
        try {
            return Pattern.compile(str2, zzgj.zzg((zzp) map.get(zzbeo)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }
}
