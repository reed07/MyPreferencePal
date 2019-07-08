package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.webkit.WebView;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.util.Iterator;
import java.util.List;

/* compiled from: IMASDK */
public final class x extends v {
    /* access modifiers changed from: private */
    public WebView a;
    private List<e> b;
    private final String c;

    public x(List<e> list, String str) {
        this.b = list;
        this.c = str;
    }

    public final void a() {
        super.a();
        this.a = new WebView(n.a().b());
        this.a.getSettings().setJavaScriptEnabled(true);
        a(this.a);
        o.a();
        o.a(this.a, this.c);
        Iterator it = this.b.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
    }

    public final void b() {
        super.b();
        new Handler().postDelayed(new y(this), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        this.a = null;
    }
}
