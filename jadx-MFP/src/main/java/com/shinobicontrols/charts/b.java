package com.shinobicontrols.charts;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

class b implements Runnable {
    private Animation a;
    private a b;
    private final Handler c = new Handler(Looper.getMainLooper());
    private long d;
    private boolean e = false;

    interface a {
        void a();

        void a(Animation animation);

        void b();

        void c();
    }

    public void run() {
        if (this.e && this.b != null) {
            long uptimeMillis = SystemClock.uptimeMillis();
            float f = ((float) (uptimeMillis - this.d)) / 1000.0f;
            this.d = uptimeMillis;
            this.a.a(f);
            this.b.a(this.a);
            if (this.a.c()) {
                this.e = false;
                this.b.b();
            } else {
                this.c.post(this);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Animation animation) {
        this.a = animation;
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar) {
        this.b = aVar;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        if (this.b != null) {
            this.d = SystemClock.uptimeMillis() - 17;
            this.b.a();
            this.e = true;
            run();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.c.removeCallbacks(this);
        if (this.e) {
            this.e = false;
            this.b.c();
        }
    }
}
