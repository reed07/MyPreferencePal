package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.internal.view.i.a.a;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.g;
import com.facebook.ads.internal.view.i.b.h;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.view.p;
import java.io.Serializable;

public class v extends BroadcastReceiver {
    private Context a;
    private p b;
    private boolean c = false;

    public v(p pVar, Context context) {
        this.b = pVar;
        this.a = context.getApplicationContext();
    }

    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        StringBuilder sb = new StringBuilder();
        sb.append("com.facebook.ads.interstitial.displayed:");
        sb.append(this.b.getUniqueId());
        intentFilter.addAction(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("videoInterstitalEvent:");
        sb2.append(this.b.getUniqueId());
        intentFilter.addAction(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("performCtaClick:");
        sb3.append(this.b.getUniqueId());
        intentFilter.addAction(sb3.toString());
        LocalBroadcastManager.getInstance(this.a).registerReceiver(this, intentFilter);
    }

    public void b() {
        try {
            LocalBroadcastManager.getInstance(this.a).unregisterReceiver(this);
        } catch (Exception unused) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        String[] split = intent.getAction().split(":");
        if (split.length == 2 && split[1].equals(this.b.getUniqueId())) {
            if (split[0].equals("com.facebook.ads.interstitial.displayed")) {
                if (this.b.getListener() != null) {
                    this.b.getListener().g();
                    this.b.getListener().a();
                }
            } else if (split[0].equals("videoInterstitalEvent")) {
                Serializable serializableExtra = intent.getSerializableExtra("event");
                if (serializableExtra instanceof q) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().f();
                        this.b.getListener().a();
                    }
                    if (this.c) {
                        this.b.a(1);
                    } else {
                        this.b.a(((q) serializableExtra).b());
                    }
                    this.b.setVisibility(0);
                    this.b.a(a.USER_STARTED);
                } else if (serializableExtra instanceof g) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().d();
                    }
                } else if (serializableExtra instanceof h) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().e();
                    }
                } else if (serializableExtra instanceof c) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().h();
                    }
                    this.c = true;
                } else if (serializableExtra instanceof k) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().c();
                    }
                    this.c = false;
                } else if ((serializableExtra instanceof i) && this.b.getListener() != null) {
                    this.b.getListener().b();
                }
            } else if (split[0].equals("performCtaClick")) {
                this.b.b();
            }
        }
    }
}
