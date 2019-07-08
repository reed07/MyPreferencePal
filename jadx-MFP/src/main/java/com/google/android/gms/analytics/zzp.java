package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzak;
import com.google.android.gms.internal.measurement.zzav;
import com.google.android.gms.internal.measurement.zzaz;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzx;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.util.HashMap;
import java.util.Map;

final class zzp implements Runnable {
    private final /* synthetic */ Map zzte;
    private final /* synthetic */ boolean zztf;
    private final /* synthetic */ String zztg;
    private final /* synthetic */ long zzth;
    private final /* synthetic */ boolean zzti;
    private final /* synthetic */ boolean zztj;
    private final /* synthetic */ String zztk;
    private final /* synthetic */ Tracker zztl;

    zzp(Tracker tracker, Map map, boolean z, String str, long j, boolean z2, boolean z3, String str2) {
        this.zztl = tracker;
        this.zzte = map;
        this.zztf = z;
        this.zztg = str;
        this.zzth = j;
        this.zzti = z2;
        this.zztj = z3;
        this.zztk = str2;
    }

    public final void run() {
        if (this.zztl.zztb.zzah()) {
            this.zzte.put("sc", TtmlNode.START);
        }
        GoogleAnalytics zzcb = this.zztl.zzcb();
        Preconditions.checkNotMainThread("getClientId can not be called from the main thread");
        zzdg.zzc(this.zzte, "cid", zzcb.zzl().zzcr().zzdr());
        String str = (String) this.zzte.get("sf");
        if (str != null) {
            double zza = zzdg.zza(str, 100.0d);
            if (zzdg.zza(zza, (String) this.zzte.get("cid"))) {
                this.zztl.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(zza));
                return;
            }
        }
        zzak zzb = this.zztl.zzch();
        if (this.zztf) {
            zzdg.zzb(this.zzte, "ate", zzb.zzbg());
            zzdg.zzb(this.zzte, "adid", zzb.zzbn());
        } else {
            this.zzte.remove("ate");
            this.zzte.remove("adid");
        }
        zzx zzdf = this.zztl.zzci().zzdf();
        zzdg.zzb(this.zzte, "an", zzdf.zzaj());
        zzdg.zzb(this.zzte, "av", zzdf.zzak());
        zzdg.zzb(this.zzte, "aid", zzdf.zzal());
        zzdg.zzb(this.zzte, "aiid", zzdf.zzam());
        this.zzte.put("v", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.zzte.put("_v", zzav.zzwa);
        zzdg.zzb(this.zzte, "ul", this.zztl.zzcj().zzek().getLanguage());
        zzdg.zzb(this.zzte, "sr", this.zztl.zzcj().zzel());
        if ((this.zztg.equals("transaction") || this.zztg.equals(Attributes.ITEM)) || this.zztl.zzta.zzew()) {
            long zzaf = zzdg.zzaf((String) this.zzte.get("ht"));
            long j = zzaf == 0 ? this.zzth : zzaf;
            if (this.zzti) {
                zzck zzck = new zzck(this.zztl, this.zzte, j, this.zztj);
                this.zztl.zzby().zzc("Dry run enabled. Would have sent hit", zzck);
                return;
            }
            String str2 = (String) this.zzte.get("cid");
            HashMap hashMap = new HashMap();
            zzdg.zza((Map<String, String>) hashMap, "uid", this.zzte);
            zzdg.zza((Map<String, String>) hashMap, "an", this.zzte);
            zzdg.zza((Map<String, String>) hashMap, "aid", this.zzte);
            zzdg.zza((Map<String, String>) hashMap, "av", this.zzte);
            zzdg.zza((Map<String, String>) hashMap, "aiid", this.zzte);
            zzaz zzaz = new zzaz(0, str2, this.zztk, !TextUtils.isEmpty((CharSequence) this.zzte.get("adid")), 0, hashMap);
            this.zzte.put("_s", String.valueOf(this.zztl.zzcc().zza(zzaz)));
            zzck zzck2 = new zzck(this.zztl, this.zzte, j, this.zztj);
            this.zztl.zzcc().zza(zzck2);
            return;
        }
        this.zztl.zzby().zza(this.zzte, "Too many hits sent too quickly, rate limiting invoked");
    }
}
