package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/* compiled from: IMASDK */
public final class k {
    @SuppressLint({"StaticFieldLeak"})
    private static k a = new k();
    private Context b;
    private BroadcastReceiver c;
    private boolean d;
    private boolean e;
    private m f;

    private k() {
    }

    public static k a() {
        return a;
    }

    public final void a(Context context) {
        this.b = context.getApplicationContext();
    }

    public final void b() {
        this.c = new l(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.b.registerReceiver(this.c, intentFilter);
        this.d = true;
        e();
    }

    public final void c() {
        Context context = this.b;
        if (context != null) {
            BroadcastReceiver broadcastReceiver = this.c;
            if (broadcastReceiver != null) {
                context.unregisterReceiver(broadcastReceiver);
                this.c = null;
            }
        }
        this.d = false;
        this.e = false;
        this.f = null;
    }

    public final boolean d() {
        return !this.e;
    }

    public final void a(m mVar) {
        this.f = mVar;
    }

    /* access modifiers changed from: private */
    public final void a(boolean z) {
        if (this.e != z) {
            this.e = z;
            if (this.d) {
                e();
                m mVar = this.f;
                if (mVar != null) {
                    mVar.a(d());
                }
            }
        }
    }

    private final void e() {
        boolean z = !this.e;
        for (e e2 : j.a().b()) {
            v e3 = e2.e();
            if (e3.d()) {
                o.a().a(e3.c(), "setState", z ? "foregrounded" : "backgrounded");
            }
        }
    }
}
