package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.view.i.b.aa;

public class u extends BroadcastReceiver {
    private String a;
    private t b;
    private s c;

    public u(String str, s sVar, t tVar) {
        this.c = sVar;
        this.b = tVar;
        this.a = str;
    }

    public IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(aa.REWARDED_VIDEO_COMPLETE.a(this.a));
        intentFilter.addAction(aa.REWARDED_VIDEO_ERROR.a(this.a));
        intentFilter.addAction(aa.REWARDED_VIDEO_AD_CLICK.a(this.a));
        intentFilter.addAction(aa.REWARDED_VIDEO_IMPRESSION.a(this.a));
        intentFilter.addAction(aa.REWARDED_VIDEO_CLOSED.a(this.a));
        intentFilter.addAction(aa.REWARD_SERVER_SUCCESS.a(this.a));
        intentFilter.addAction(aa.REWARD_SERVER_FAILED.a(this.a));
        intentFilter.addAction(aa.REWARDED_VIDEO_ACTIVITY_DESTROYED.a(this.a));
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (aa.REWARDED_VIDEO_COMPLETE.a(this.a).equals(action)) {
            this.b.d(this.c);
        } else if (aa.REWARDED_VIDEO_ERROR.a(this.a).equals(action)) {
            this.b.a(this.c, AdError.INTERNAL_ERROR);
        } else if (aa.REWARDED_VIDEO_AD_CLICK.a(this.a).equals(action)) {
            this.b.b(this.c);
        } else if (aa.REWARDED_VIDEO_IMPRESSION.a(this.a).equals(action)) {
            this.b.c(this.c);
        } else if (aa.REWARDED_VIDEO_CLOSED.a(this.a).equals(action)) {
            this.b.a();
        } else if (aa.REWARD_SERVER_FAILED.a(this.a).equals(action)) {
            this.b.e(this.c);
        } else if (aa.REWARD_SERVER_SUCCESS.a(this.a).equals(action)) {
            this.b.f(this.c);
        } else if (aa.REWARDED_VIDEO_ACTIVITY_DESTROYED.a(this.a).equals(action)) {
            this.b.b();
        }
    }
}
