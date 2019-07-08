package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzwu;

final class zzbq extends WebViewClient {
    private final /* synthetic */ zzbp zzbra;

    zzbq(zzbp zzbp) {
        this.zzbra = zzbp;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(this.zzbra.zzkx())) {
            return false;
        }
        if (str.startsWith((String) zzwu.zzpz().zzd(zzaan.zzcvd))) {
            if (this.zzbra.zzbnn != null) {
                try {
                    this.zzbra.zzbnn.onAdFailedToLoad(3);
                } catch (RemoteException e) {
                    zzaxz.zzd("#007 Could not call remote method.", e);
                }
            }
            this.zzbra.zzbt(0);
            return true;
        }
        if (str.startsWith((String) zzwu.zzpz().zzd(zzaan.zzcve))) {
            if (this.zzbra.zzbnn != null) {
                try {
                    this.zzbra.zzbnn.onAdFailedToLoad(0);
                } catch (RemoteException e2) {
                    zzaxz.zzd("#007 Could not call remote method.", e2);
                }
            }
            this.zzbra.zzbt(0);
            return true;
        }
        if (str.startsWith((String) zzwu.zzpz().zzd(zzaan.zzcvf))) {
            if (this.zzbra.zzbnn != null) {
                try {
                    this.zzbra.zzbnn.onAdLoaded();
                } catch (RemoteException e3) {
                    zzaxz.zzd("#007 Could not call remote method.", e3);
                }
            }
            this.zzbra.zzbt(this.zzbra.zzav(str));
            return true;
        } else if (str.startsWith("gmsg://")) {
            return true;
        } else {
            if (this.zzbra.zzbnn != null) {
                try {
                    this.zzbra.zzbnn.onAdLeftApplication();
                } catch (RemoteException e4) {
                    zzaxz.zzd("#007 Could not call remote method.", e4);
                }
            }
            this.zzbra.zzax(this.zzbra.zzaw(str));
            return true;
        }
    }

    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (this.zzbra.zzbnn != null) {
            try {
                this.zzbra.zzbnn.onAdFailedToLoad(0);
            } catch (RemoteException e) {
                zzaxz.zzd("#007 Could not call remote method.", e);
            }
        }
    }
}
