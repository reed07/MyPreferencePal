package com.google.android.gms.tagmanager;

import java.util.Map;

final class zzga implements zzb {
    private final /* synthetic */ TagManager zzbgt;

    zzga(TagManager tagManager) {
        this.zzbgt = tagManager;
    }

    public final void zzd(Map<String, Object> map) {
        Object obj = map.get("event");
        if (obj != null) {
            this.zzbgt.zzeb(obj.toString());
        }
    }
}
