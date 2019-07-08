package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

@Deprecated
public final class CustomEventExtras implements NetworkExtras {
    private final HashMap<String, Object> zzfcj = new HashMap<>();

    public final Object getExtra(String str) {
        return this.zzfcj.get(str);
    }

    public final void setExtra(String str, Object obj) {
        this.zzfcj.put(str, obj);
    }
}
