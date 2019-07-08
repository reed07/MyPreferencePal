package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebView;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@zzark
final class zzbii {
    @GuardedBy("InvokeJavascriptWorkaround.class")
    @VisibleForTesting
    private static Boolean zzfbi;

    private zzbii() {
    }

    @TargetApi(19)
    private static boolean zzb(WebView webView) {
        boolean booleanValue;
        synchronized (zzbii.class) {
            if (zzfbi == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", null);
                    zzfbi = Boolean.valueOf(true);
                } catch (IllegalStateException unused) {
                    zzfbi = Boolean.valueOf(false);
                }
            }
            booleanValue = zzfbi.booleanValue();
        }
        return booleanValue;
    }

    @TargetApi(19)
    static void zza(WebView webView, String str) {
        if (!PlatformVersion.isAtLeastKitKat() || !zzb(webView)) {
            String str2 = "javascript:";
            String valueOf = String.valueOf(str);
            webView.loadUrl(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        webView.evaluateJavascript(str, null);
    }
}
