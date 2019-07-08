package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;

/* compiled from: IMASDK */
final class uh<T> {
    private final Handler a;
    /* access modifiers changed from: private */
    public final T b;
    private boolean c;

    public uh(Handler handler, T t) {
        this.a = handler;
        this.b = t;
    }

    public final void a() {
        this.c = true;
    }

    public final void a(ug<T> ugVar) {
        this.a.post(new ui(this, ugVar));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(ug ugVar) {
        if (!this.c) {
            ugVar.a(this.b);
        }
    }
}
