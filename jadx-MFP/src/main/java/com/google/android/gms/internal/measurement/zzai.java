package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;

@ShowFirstParty
public final class zzai extends zzi<zzai> {
    public String zzvd;
    public String zzve;
    public String zzvf;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("network", this.zzvd);
        hashMap.put("action", this.zzve);
        hashMap.put("target", this.zzvf);
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzai zzai = (zzai) zzi;
        if (!TextUtils.isEmpty(this.zzvd)) {
            zzai.zzvd = this.zzvd;
        }
        if (!TextUtils.isEmpty(this.zzve)) {
            zzai.zzve = this.zzve;
        }
        if (!TextUtils.isEmpty(this.zzvf)) {
            zzai.zzvf = this.zzvf;
        }
    }
}
