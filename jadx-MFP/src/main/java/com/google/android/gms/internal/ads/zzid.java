package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzki.zza;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzid {
    private static final zzko zzahe = new zzie();
    private static final Pattern zzahf = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public int zzzw = -1;
    public int zzzx = -1;

    public final boolean zzb(zzki zzki) {
        for (int i = 0; i < zzki.length(); i++) {
            zza zzao = zzki.zzao(i);
            if (zzao instanceof zzkm) {
                zzkm zzkm = (zzkm) zzao;
                if (zzb(zzkm.description, zzkm.zzavu)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean zzb(String str, String str2) {
        if (!"iTunSMPB".equals(str)) {
            return false;
        }
        Matcher matcher = zzahf.matcher(str2);
        if (matcher.find()) {
            try {
                int parseInt = Integer.parseInt(matcher.group(1), 16);
                int parseInt2 = Integer.parseInt(matcher.group(2), 16);
                if (parseInt > 0 || parseInt2 > 0) {
                    this.zzzw = parseInt;
                    this.zzzx = parseInt2;
                    return true;
                }
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    public final boolean zzea() {
        return (this.zzzw == -1 || this.zzzx == -1) ? false : true;
    }
}
