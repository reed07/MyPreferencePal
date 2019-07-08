package com.facebook.ads.internal.w.b;

import android.os.Handler;
import android.support.annotation.VisibleForTesting;

public class e {
    /* access modifiers changed from: private */
    public final Handler a;
    private final a b;
    private int c;
    private boolean d;
    private boolean e;

    public interface a {
        void a();

        void a(int i);
    }

    public e(int i, a aVar) {
        this(i, aVar, new Handler());
    }

    @VisibleForTesting
    e(int i, a aVar, Handler handler) {
        this.d = false;
        this.c = i;
        this.b = aVar;
        this.a = handler;
    }

    static /* synthetic */ void a(e eVar) {
        eVar.c--;
        eVar.b.a(eVar.c);
        if (eVar.c == 0 && !eVar.e) {
            eVar.e = true;
            eVar.b.a();
            eVar.d = false;
        }
    }

    public boolean a() {
        if (d() && !this.e) {
            this.b.a();
        }
        if (d() || c()) {
            return false;
        }
        this.d = true;
        this.b.a(this.c);
        this.a.postDelayed(new Runnable() {
            public void run() {
                if (e.this.c()) {
                    e.a(e.this);
                    e.this.a.postDelayed(this, 1000);
                }
            }
        }, 1000);
        return true;
    }

    public boolean b() {
        if (!c()) {
            return false;
        }
        this.d = false;
        return true;
    }

    public boolean c() {
        return this.d;
    }

    public boolean d() {
        return this.c <= 0;
    }

    public int e() {
        return this.c;
    }
}
