package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzayt implements Callable<Boolean> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ WebSettings zzeln;

    zzayt(zzays zzays, Context context, WebSettings webSettings) {
        this.val$context = context;
        this.zzeln = webSettings;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (this.val$context.getCacheDir() != null) {
            this.zzeln.setAppCachePath(this.val$context.getCacheDir().getAbsolutePath());
            this.zzeln.setAppCacheMaxSize(0);
            this.zzeln.setAppCacheEnabled(true);
        }
        this.zzeln.setDatabasePath(this.val$context.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
        this.zzeln.setDatabaseEnabled(true);
        this.zzeln.setDomStorageEnabled(true);
        this.zzeln.setDisplayZoomControls(false);
        this.zzeln.setBuiltInZoomControls(true);
        this.zzeln.setSupportZoom(true);
        this.zzeln.setAllowContentAccess(false);
        return Boolean.valueOf(true);
    }
}
