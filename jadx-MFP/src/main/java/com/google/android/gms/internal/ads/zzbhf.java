package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzbv;
import java.io.File;
import java.util.Collections;
import java.util.Map;

@zzark
@TargetApi(11)
public class zzbhf extends zzbgh {
    public zzbhf(zzbgg zzbgg, zzum zzum, boolean z) {
        super(zzbgg, zzum, z);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final WebResourceResponse zza(WebView webView, String str, @Nullable Map<String, String> map) {
        String str2;
        if (!(webView instanceof zzbgg)) {
            zzaxz.zzeo("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzbgg zzbgg = (zzbgg) webView;
        if (this.zzbmx != null) {
            this.zzbmx.zza(str, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            if (map == null) {
                map = Collections.emptyMap();
            }
            return super.zzd(str, map);
        }
        if (zzbgg.zzadl() != null) {
            zzbgg.zzadl().zzvr();
        }
        if (zzbgg.zzadj().zzafb()) {
            str2 = (String) zzwu.zzpz().zzd(zzaan.zzcpt);
        } else if (zzbgg.zzadq()) {
            str2 = (String) zzwu.zzpz().zzd(zzaan.zzcps);
        } else {
            str2 = (String) zzwu.zzpz().zzd(zzaan.zzcpr);
        }
        zzbv.zzlf();
        return zzayh.zze(zzbgg.getContext(), zzbgg.zzabz().zzdp, str2);
    }
}
