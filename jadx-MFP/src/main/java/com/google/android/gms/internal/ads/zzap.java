package com.google.android.gms.internal.ads;

import com.google.common.net.HttpHeaders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class zzap {
    public static zzc zzb(zzp zzp) {
        boolean z;
        long j;
        long j2;
        long j3;
        long j4;
        zzp zzp2 = zzp;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = zzp2.zzab;
        String str = (String) map.get(HttpHeaders.DATE);
        long zzf = str != null ? zzf(str) : 0;
        String str2 = (String) map.get(HttpHeaders.CACHE_CONTROL);
        int i = 0;
        if (str2 != null) {
            String[] split = str2.split(",", 0);
            j2 = 0;
            int i2 = 0;
            j = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    i2 = 1;
                }
                i++;
            }
            i = i2;
            z = true;
        } else {
            j2 = 0;
            j = 0;
            z = false;
        }
        String str3 = (String) map.get(HttpHeaders.EXPIRES);
        long zzf2 = str3 != null ? zzf(str3) : 0;
        String str4 = (String) map.get(HttpHeaders.LAST_MODIFIED);
        long zzf3 = str4 != null ? zzf(str4) : 0;
        String str5 = (String) map.get(HttpHeaders.ETAG);
        if (z) {
            j4 = currentTimeMillis + (j2 * 1000);
            if (i != 0) {
                j3 = j4;
            } else {
                Long.signum(j);
                j3 = (j * 1000) + j4;
            }
        } else if (zzf <= 0 || zzf2 < zzf) {
            j4 = 0;
            j3 = 0;
        } else {
            j3 = (zzf2 - zzf) + currentTimeMillis;
            j4 = j3;
        }
        zzc zzc = new zzc();
        zzc.data = zzp2.data;
        zzc.zza = str5;
        zzc.zze = j4;
        zzc.zzd = j3;
        zzc.zzb = zzf;
        zzc.zzc = zzf3;
        zzc.zzf = map;
        zzc.zzg = zzp2.allHeaders;
        return zzc;
    }

    private static long zzf(String str) {
        try {
            return zzq().parse(str).getTime();
        } catch (ParseException e) {
            zzaf.zza(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    static String zzb(long j) {
        return zzq().format(new Date(j));
    }

    private static SimpleDateFormat zzq() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
}
