package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzm extends zzgh {
    private static final String ID = com.google.android.gms.internal.measurement.zza.ARBITRARY_PIXEL.toString();
    private static final String URL = zzb.URL.toString();
    private static final String zzazk = zzb.ADDITIONAL_PARAMS.toString();
    private static final String zzazl = zzb.UNREPEATABLE.toString();
    private static final String zzazm;
    private static final Set<String> zzazn = new HashSet();
    private final zza zzazo;
    private final Context zzri;

    public interface zza {
        zzbx zznl();
    }

    public zzm(Context context) {
        this(context, new zzn(context));
    }

    @VisibleForTesting
    private zzm(Context context, zza zza2) {
        super(ID, URL);
        this.zzazo = zza2;
        this.zzri = context;
    }

    public final void zze(Map<String, zzp> map) {
        String zzc = map.get(zzazl) != null ? zzgj.zzc((zzp) map.get(zzazl)) : null;
        if (zzc == null || !zzdb(zzc)) {
            Builder buildUpon = Uri.parse(zzgj.zzc((zzp) map.get(URL))).buildUpon();
            zzp zzp = (zzp) map.get(zzazk);
            if (zzp != null) {
                Object zzh = zzgj.zzh(zzp);
                if (!(zzh instanceof List)) {
                    String str = "ArbitraryPixel: additional params not a list: not sending partial hit: ";
                    String valueOf = String.valueOf(buildUpon.build().toString());
                    zzdi.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return;
                }
                for (Object next : (List) zzh) {
                    if (!(next instanceof Map)) {
                        String str2 = "ArbitraryPixel: additional params contains non-map: not sending partial hit: ";
                        String valueOf2 = String.valueOf(buildUpon.build().toString());
                        zzdi.e(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
                        return;
                    }
                    for (Entry entry : ((Map) next).entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            String uri = buildUpon.build().toString();
            this.zzazo.zznl().zzdo(uri);
            String str3 = "ArbitraryPixel: url = ";
            String valueOf3 = String.valueOf(uri);
            zzdi.v(valueOf3.length() != 0 ? str3.concat(valueOf3) : new String(str3));
            if (zzc != null) {
                synchronized (zzm.class) {
                    zzazn.add(zzc);
                    zzft.zza(this.zzri, zzazm, zzc, "true");
                }
            }
        }
    }

    private final synchronized boolean zzdb(String str) {
        if (zzazn.contains(str)) {
            return true;
        }
        if (!this.zzri.getSharedPreferences(zzazm, 0).contains(str)) {
            return false;
        }
        zzazn.add(str);
        return true;
    }

    static {
        String str = ID;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 17);
        sb.append("gtm_");
        sb.append(str);
        sb.append("_unrepeatable");
        zzazm = sb.toString();
    }
}
