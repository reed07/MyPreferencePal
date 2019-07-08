package com.moat.analytics.mobile.inm;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.WebView;

class aa extends b implements WebAdTracker {
    aa(@Nullable ViewGroup viewGroup) {
        this((WebView) ab.a(viewGroup, false).c(null));
        if (viewGroup == null) {
            String str = "Target ViewGroup is null";
            StringBuilder sb = new StringBuilder("WebAdTracker initialization not successful, ");
            sb.append(str);
            p.a("[ERROR] ", 3, "WebAdTracker", this, sb.toString());
            this.a = new m(str);
        }
        if (this.b == null) {
            String str2 = "No WebView to track inside of ad container";
            StringBuilder sb2 = new StringBuilder("WebAdTracker initialization not successful, ");
            sb2.append(str2);
            p.a("[ERROR] ", 3, "WebAdTracker", this, sb2.toString());
            this.a = new m(str2);
        }
    }

    aa(@Nullable WebView webView) {
        super(webView, false, false);
        p.a(3, "WebAdTracker", (Object) this, "Initializing.");
        if (webView == null) {
            String str = "WebView is null";
            StringBuilder sb = new StringBuilder("WebAdTracker initialization not successful, ");
            sb.append(str);
            p.a("[ERROR] ", 3, "WebAdTracker", this, sb.toString());
            this.a = new m(str);
            return;
        }
        try {
            super.a(webView);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(a());
            sb2.append(" created for ");
            sb2.append(g());
            p.a("[SUCCESS] ", sb2.toString());
        } catch (m e) {
            this.a = e;
        }
    }

    /* access modifiers changed from: 0000 */
    public String a() {
        return "WebAdTracker";
    }
}
