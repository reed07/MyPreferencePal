package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.AdError;

public class o extends BroadcastReceiver {
    private String a;
    private Context b;
    private InterstitialAdapterListener c;
    private g d;

    public o(Context context, String str, g gVar, InterstitialAdapterListener interstitialAdapterListener) {
        this.b = context;
        this.a = str;
        this.c = interstitialAdapterListener;
        this.d = gVar;
    }

    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        StringBuilder sb = new StringBuilder();
        sb.append("com.facebook.ads.interstitial.impression.logged:");
        sb.append(this.a);
        intentFilter.addAction(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("com.facebook.ads.interstitial.displayed:");
        sb2.append(this.a);
        intentFilter.addAction(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("com.facebook.ads.interstitial.dismissed:");
        sb3.append(this.a);
        intentFilter.addAction(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append("com.facebook.ads.interstitial.clicked:");
        sb4.append(this.a);
        intentFilter.addAction(sb4.toString());
        StringBuilder sb5 = new StringBuilder();
        sb5.append("com.facebook.ads.interstitial.error:");
        sb5.append(this.a);
        intentFilter.addAction(sb5.toString());
        StringBuilder sb6 = new StringBuilder();
        sb6.append("com.facebook.ads.interstitial.activity_destroyed:");
        sb6.append(this.a);
        intentFilter.addAction(sb6.toString());
        LocalBroadcastManager.getInstance(this.b).registerReceiver(this, intentFilter);
    }

    public void b() {
        try {
            LocalBroadcastManager.getInstance(this.b).unregisterReceiver(this);
        } catch (Exception unused) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        String str = intent.getAction().split(":")[0];
        if (this.c != null && str != null) {
            if ("com.facebook.ads.interstitial.clicked".equals(str)) {
                this.c.onInterstitialAdClicked(this.d, null, true);
            } else if ("com.facebook.ads.interstitial.dismissed".equals(str)) {
                this.c.onInterstitialAdDismissed(this.d);
            } else if ("com.facebook.ads.interstitial.displayed".equals(str)) {
                this.c.onInterstitialAdDisplayed(this.d);
            } else if ("com.facebook.ads.interstitial.impression.logged".equals(str)) {
                this.c.onInterstitialLoggingImpression(this.d);
            } else if ("com.facebook.ads.interstitial.error".equals(str)) {
                this.c.onInterstitialError(this.d, AdError.INTERNAL_ERROR);
            } else if ("com.facebook.ads.interstitial.activity_destroyed".equals(str)) {
                this.c.onInterstitialActivityDestroyed();
            }
        }
    }
}
