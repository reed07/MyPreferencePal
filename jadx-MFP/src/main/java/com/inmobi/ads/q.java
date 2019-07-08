package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;

/* compiled from: BannerRefreshHandler */
final class q extends Handler {
    private InMobiBanner a;

    q(InMobiBanner inMobiBanner) {
        this.a = inMobiBanner;
    }

    public final void handleMessage(Message message) {
        if (message.what == 1) {
            this.a.a(true);
        }
    }
}
