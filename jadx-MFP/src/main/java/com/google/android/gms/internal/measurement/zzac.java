package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;

@ShowFirstParty
@VisibleForTesting
public final class zzac extends zzi<zzac> {
    private String zzuf;
    public int zzug;
    public int zzuh;
    public int zzui;
    public int zzuj;
    public int zzuk;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("language", this.zzuf);
        hashMap.put("screenColors", Integer.valueOf(this.zzug));
        hashMap.put("screenWidth", Integer.valueOf(this.zzuh));
        hashMap.put("screenHeight", Integer.valueOf(this.zzui));
        hashMap.put("viewportWidth", Integer.valueOf(this.zzuj));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzuk));
        return zza((Object) hashMap);
    }

    public final String getLanguage() {
        return this.zzuf;
    }

    public final void setLanguage(String str) {
        this.zzuf = str;
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzac zzac = (zzac) zzi;
        int i = this.zzug;
        if (i != 0) {
            zzac.zzug = i;
        }
        int i2 = this.zzuh;
        if (i2 != 0) {
            zzac.zzuh = i2;
        }
        int i3 = this.zzui;
        if (i3 != 0) {
            zzac.zzui = i3;
        }
        int i4 = this.zzuj;
        if (i4 != 0) {
            zzac.zzuj = i4;
        }
        int i5 = this.zzuk;
        if (i5 != 0) {
            zzac.zzuk = i5;
        }
        if (!TextUtils.isEmpty(this.zzuf)) {
            zzac.zzuf = this.zzuf;
        }
    }
}
