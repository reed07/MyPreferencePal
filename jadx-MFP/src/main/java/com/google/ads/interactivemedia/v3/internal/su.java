package com.google.ads.interactivemedia.v3.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: IMASDK */
final class su extends BroadcastReceiver {
    private static su a;
    private final Handler b = new Handler(Looper.getMainLooper());
    private final ArrayList<WeakReference<ss>> c = new ArrayList<>();

    public static synchronized su a(Context context) {
        su suVar;
        synchronized (su.class) {
            if (a == null) {
                a = new su();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                context.registerReceiver(a, intentFilter);
            }
            suVar = a;
        }
        return suVar;
    }

    private su() {
    }

    public final synchronized void a(ss ssVar) {
        a();
        this.c.add(new WeakReference(ssVar));
        this.b.post(new sv(this, ssVar));
    }

    public final synchronized void onReceive(Context context, Intent intent) {
        if (!isInitialStickyBroadcast()) {
            a();
            for (int i = 0; i < this.c.size(); i++) {
                ss ssVar = (ss) ((WeakReference) this.c.get(i)).get();
                if (ssVar != null) {
                    ssVar.d();
                }
            }
        }
    }

    private final void a() {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            if (((ss) ((WeakReference) this.c.get(size)).get()) == null) {
                this.c.remove(size);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(ss ssVar) {
        ssVar.d();
    }
}
