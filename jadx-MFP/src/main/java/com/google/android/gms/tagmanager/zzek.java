package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzek extends zzbq {
    private static final String ID = zza.REGEX_GROUP.toString();
    private static final String zzbem = zzb.ARG0.toString();
    private static final String zzben = zzb.ARG1.toString();
    private static final String zzbeo = zzb.IGNORE_CASE.toString();
    private static final String zzbep = zzb.GROUP.toString();

    public zzek() {
        super(ID, zzbem, zzben);
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        zzp zzp = (zzp) map.get(zzbem);
        zzp zzp2 = (zzp) map.get(zzben);
        if (zzp == null || zzp == zzgj.zzqq() || zzp2 == null || zzp2 == zzgj.zzqq()) {
            return zzgj.zzqq();
        }
        int i = 64;
        if (zzgj.zzg((zzp) map.get(zzbeo)).booleanValue()) {
            i = 66;
        }
        int i2 = 1;
        zzp zzp3 = (zzp) map.get(zzbep);
        if (zzp3 != null) {
            Long zze = zzgj.zze(zzp3);
            if (zze == zzgj.zzql()) {
                return zzgj.zzqq();
            }
            i2 = zze.intValue();
            if (i2 < 0) {
                return zzgj.zzqq();
            }
        }
        try {
            String zzc = zzgj.zzc(zzp);
            String zzc2 = zzgj.zzc(zzp2);
            String str = null;
            Matcher matcher = Pattern.compile(zzc2, i).matcher(zzc);
            if (matcher.find() && matcher.groupCount() >= i2) {
                str = matcher.group(i2);
            }
            return str == null ? zzgj.zzqq() : zzgj.zzj(str);
        } catch (PatternSyntaxException unused) {
            return zzgj.zzqq();
        }
    }
}
