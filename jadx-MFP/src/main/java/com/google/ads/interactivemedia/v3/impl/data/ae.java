package com.google.ads.interactivemedia.v3.impl.data;

import android.support.v4.view.ViewCompat;
import android.view.View;

/* compiled from: IMASDK */
public abstract class ae {
    public abstract ae attached(boolean z);

    public abstract ae bounds(v vVar);

    public abstract ad build();

    public abstract ae hidden(boolean z);

    public abstract ae type(String str);

    /* access modifiers changed from: 0000 */
    public ae view(View view) {
        return attached(ViewCompat.isAttachedToWindow(view)).bounds(v.builder().locationOnScreenOfView(view).build()).hidden(!view.isShown()).type(view.getClass().getCanonicalName());
    }
}
