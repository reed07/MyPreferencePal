package com.samsung.accessory.a.a;

import android.content.Context;
import com.samsung.android.sdk.accessory.a;

class b extends a {
    static {
        a.a = new b();
    }

    b() {
    }

    /* access modifiers changed from: protected */
    public final void a(int i) {
        d.b(i);
    }

    /* access modifiers changed from: protected */
    public final void a(Context context) {
        if (!d.a()) {
            d.a(new e(context, "SaBufferPool-SDK", d.a(context) ? 2621440 : 7340032));
        }
    }

    /* access modifiers changed from: protected */
    public final a b(int i) {
        return d.a(i);
    }
}
