package com.google.android.gms.internal.ads;

import com.facebook.ads.AudienceNetworkActivity;

final class zzaio implements Runnable {
    private final /* synthetic */ String zzdir;
    private final /* synthetic */ zzaik zzdis;

    zzaio(zzaik zzaik, String str) {
        this.zzdis = zzaik;
        this.zzdir = str;
    }

    public final void run() {
        this.zzdis.zzdin.loadData(this.zzdir, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
    }
}
