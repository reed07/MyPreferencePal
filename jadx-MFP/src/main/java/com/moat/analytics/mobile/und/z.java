package com.moat.analytics.mobile.und;

import android.support.annotation.Nullable;
import android.webkit.WebView;

class z extends b implements WebAdTracker {
    z(@Nullable WebView webView) {
        super(webView, false, false);
        p.a(3, "WebAdTracker", (Object) this, "In initialization method.");
        super.a(webView);
        StringBuilder sb = new StringBuilder();
        sb.append(a());
        sb.append(" created for ");
        sb.append(e());
        p.a("[SUCCESS] ", sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        return "WebAdTracker";
    }
}
