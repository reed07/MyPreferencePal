package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;

final class zzsz implements Runnable {
    final /* synthetic */ zzsx zzbyk;
    private ValueCallback<String> zzbyl = new zzta(this);
    final /* synthetic */ zzsr zzbym;
    final /* synthetic */ WebView zzbyn;
    final /* synthetic */ boolean zzbyo;

    zzsz(zzsx zzsx, zzsr zzsr, WebView webView, boolean z) {
        this.zzbyk = zzsx;
        this.zzbym = zzsr;
        this.zzbyn = webView;
        this.zzbyo = z;
    }

    public final void run() {
        if (this.zzbyn.getSettings().getJavaScriptEnabled()) {
            try {
                this.zzbyn.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzbyl);
            } catch (Throwable unused) {
                this.zzbyl.onReceiveValue("");
            }
        }
    }
}
