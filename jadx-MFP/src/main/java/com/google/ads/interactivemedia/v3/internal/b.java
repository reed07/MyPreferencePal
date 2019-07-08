package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;

/* compiled from: IMASDK */
public final class b {
    private boolean a;

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final void a(Context context) {
        ho.a((Object) context, "Application Context cannot be null");
        if (!this.a) {
            this.a = true;
            g.a().a(context);
            k.a().a(context);
            z.a(context);
            n.a().a(context);
        }
    }
}
