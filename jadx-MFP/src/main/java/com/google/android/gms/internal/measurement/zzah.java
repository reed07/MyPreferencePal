package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.UUID;

@ShowFirstParty
@VisibleForTesting
public final class zzah extends zzi<zzah> {
    private String zzuw;
    private int zzux;
    private int zzuy;
    private String zzuz;
    private String zzva;
    private boolean zzvb;
    private boolean zzvc;

    public zzah() {
        this(false);
    }

    private zzah(boolean z) {
        UUID randomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (randomUUID.getLeastSignificantBits() & 2147483647L);
        if (leastSignificantBits == 0) {
            leastSignificantBits = (int) (randomUUID.getMostSignificantBits() & 2147483647L);
            if (leastSignificantBits == 0) {
                Log.e("GAv4", "UUID.randomUUID() returned 0.");
                leastSignificantBits = Integer.MAX_VALUE;
            }
        }
        this(false, leastSignificantBits);
    }

    @ShowFirstParty
    @VisibleForTesting
    private zzah(boolean z, int i) {
        Preconditions.checkNotZero(i);
        this.zzux = i;
        this.zzvc = false;
    }

    public final String zzbk() {
        return this.zzuw;
    }

    public final int zzbl() {
        return this.zzux;
    }

    public final String zzbm() {
        return this.zzva;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("screenName", this.zzuw);
        hashMap.put("interstitial", Boolean.valueOf(this.zzvb));
        hashMap.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC, Boolean.valueOf(this.zzvc));
        hashMap.put("screenId", Integer.valueOf(this.zzux));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzuy));
        hashMap.put("referrerScreenName", this.zzuz);
        hashMap.put("referrerUri", this.zzva);
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzah zzah = (zzah) zzi;
        if (!TextUtils.isEmpty(this.zzuw)) {
            zzah.zzuw = this.zzuw;
        }
        int i = this.zzux;
        if (i != 0) {
            zzah.zzux = i;
        }
        int i2 = this.zzuy;
        if (i2 != 0) {
            zzah.zzuy = i2;
        }
        if (!TextUtils.isEmpty(this.zzuz)) {
            zzah.zzuz = this.zzuz;
        }
        if (!TextUtils.isEmpty(this.zzva)) {
            String str = this.zzva;
            if (TextUtils.isEmpty(str)) {
                zzah.zzva = null;
            } else {
                zzah.zzva = str;
            }
        }
        boolean z = this.zzvb;
        if (z) {
            zzah.zzvb = z;
        }
        boolean z2 = this.zzvc;
        if (z2) {
            zzah.zzvc = z2;
        }
    }
}
