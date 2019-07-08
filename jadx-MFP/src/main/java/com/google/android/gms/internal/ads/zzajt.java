package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@zzark
public final class zzajt implements zzaic, zzajs {
    private final zzajr zzdjy;
    private final HashSet<SimpleEntry<String, zzu<? super zzajr>>> zzdjz = new HashSet<>();

    public zzajt(zzajr zzajr) {
        this.zzdjy = zzajr;
    }

    public final void zza(String str, Map map) {
        zzaid.zza((zzaic) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzaid.zzb(this, str, jSONObject);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzaid.zza((zzaic) this, str, jSONObject);
    }

    public final void zzh(String str, String str2) {
        zzaid.zza((zzaic) this, str, str2);
    }

    public final void zzcg(String str) {
        this.zzdjy.zzcg(str);
    }

    public final void zza(String str, zzu<? super zzajr> zzu) {
        this.zzdjy.zza(str, zzu);
        this.zzdjz.add(new SimpleEntry(str, zzu));
    }

    public final void zzb(String str, zzu<? super zzajr> zzu) {
        this.zzdjy.zzb(str, zzu);
        this.zzdjz.remove(new SimpleEntry(str, zzu));
    }

    public final void zzuh() {
        Iterator it = this.zzdjz.iterator();
        while (it.hasNext()) {
            SimpleEntry simpleEntry = (SimpleEntry) it.next();
            String str = "Unregistering eventhandler: ";
            String valueOf = String.valueOf(((zzu) simpleEntry.getValue()).toString());
            zzaxz.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzdjy.zzb((String) simpleEntry.getKey(), (zzu) simpleEntry.getValue());
        }
        this.zzdjz.clear();
    }
}
