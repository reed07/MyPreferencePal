package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzaba {
    private final Object mLock = new Object();
    @VisibleForTesting
    private boolean zzczj;
    private final List<zzaay> zzczk = new LinkedList();
    private final Map<String, String> zzczl = new LinkedHashMap();
    private String zzczm;
    @Nullable
    private zzaba zzczn;

    public zzaba(boolean z, String str, String str2) {
        this.zzczj = z;
        this.zzczl.put("action", str);
        this.zzczl.put(Attributes.ACHIEVEMENT_AD_FORMAT, str2);
    }

    public final void zzc(@Nullable zzaba zzaba) {
        synchronized (this.mLock) {
            this.zzczn = zzaba;
        }
    }

    public final zzaay zzrg() {
        return zzao(zzbv.zzlm().elapsedRealtime());
    }

    @Nullable
    public final zzaay zzao(long j) {
        if (!this.zzczj) {
            return null;
        }
        return new zzaay(j, null, null);
    }

    public final boolean zza(@Nullable zzaay zzaay, String... strArr) {
        if (!this.zzczj || zzaay == null) {
            return false;
        }
        return zza(zzaay, zzbv.zzlm().elapsedRealtime(), strArr);
    }

    public final boolean zza(zzaay zzaay, long j, String... strArr) {
        synchronized (this.mLock) {
            for (String zzaay2 : strArr) {
                this.zzczk.add(new zzaay(j, zzaay2, zzaay));
            }
        }
        return true;
    }

    public final String zzrh() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        synchronized (this.mLock) {
            for (zzaay zzaay : this.zzczk) {
                long time = zzaay.getTime();
                String zzrd = zzaay.zzrd();
                zzaay zzre = zzaay.zzre();
                if (zzre != null && time > 0) {
                    long time2 = time - zzre.getTime();
                    sb2.append(zzrd);
                    sb2.append('.');
                    sb2.append(time2);
                    sb2.append(',');
                }
            }
            this.zzczk.clear();
            if (!TextUtils.isEmpty(this.zzczm)) {
                sb2.append(this.zzczm);
            } else if (sb2.length() > 0) {
                sb2.setLength(sb2.length() - 1);
            }
            sb = sb2.toString();
        }
        return sb;
    }

    public final void zzbp(String str) {
        if (this.zzczj) {
            synchronized (this.mLock) {
                this.zzczm = str;
            }
        }
    }

    public final void zzg(String str, String str2) {
        if (this.zzczj && !TextUtils.isEmpty(str2)) {
            zzaaq zzyh = zzbv.zzlj().zzyh();
            if (zzyh != null) {
                synchronized (this.mLock) {
                    zzaau zzbn = zzyh.zzbn(str);
                    Map<String, String> map = this.zzczl;
                    map.put(str, zzbn.zzf((String) map.get(str), str2));
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final Map<String, String> zzri() {
        synchronized (this.mLock) {
            zzaaq zzyh = zzbv.zzlj().zzyh();
            if (zzyh != null) {
                if (this.zzczn != null) {
                    Map<String, String> zza = zzyh.zza(this.zzczl, this.zzczn.zzri());
                    return zza;
                }
            }
            Map<String, String> map = this.zzczl;
            return map;
        }
    }

    public final zzaay zzrj() {
        synchronized (this.mLock) {
        }
        return null;
    }
}
