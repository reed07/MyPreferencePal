package com.facebook.ads.internal.view.b;

import android.text.TextUtils;

public class d {
    private final f a;
    private boolean b = true;

    public d(f fVar) {
        this.a = fVar;
    }

    private static long a(String str, String str2) {
        String substring = str.substring(str2.length());
        long j = -1;
        if (TextUtils.isEmpty(substring)) {
            return -1;
        }
        try {
            Long valueOf = Long.valueOf(Long.parseLong(substring));
            if (valueOf.longValue() >= 0) {
                j = valueOf.longValue();
            }
        } catch (NumberFormatException unused) {
        }
        return j;
    }

    public void a() {
        if (this.b) {
            if (this.a.canGoBack() || this.a.canGoForward()) {
                this.b = false;
            } else {
                this.a.a("void((function() {try {  if (!window.performance || !window.performance.timing || !document ||       !document.body || document.body.scrollHeight <= 0 ||       !document.body.children || document.body.children.length < 1) {    return;  }  var nvtiming__an_t = window.performance.timing;  if (nvtiming__an_t.responseEnd > 0) {    console.log('ANNavResponseEnd:'+nvtiming__an_t.responseEnd);  }  if (nvtiming__an_t.domContentLoadedEventStart > 0) {    console.log('ANNavDomContentLoaded:' + nvtiming__an_t.domContentLoadedEventStart);  }  if (nvtiming__an_t.loadEventEnd > 0) {    console.log('ANNavLoadEventEnd:' + nvtiming__an_t.loadEventEnd);  }} catch(err) {  console.log('an_navigation_timing_error:' + err.message);}})());");
            }
        }
    }

    public void a(String str) {
        if (this.b) {
            if (str.startsWith("ANNavResponseEnd:")) {
                this.a.a(a(str, "ANNavResponseEnd:"));
            } else if (str.startsWith("ANNavDomContentLoaded:")) {
                this.a.b(a(str, "ANNavDomContentLoaded:"));
            } else if (str.startsWith("ANNavLoadEventEnd:")) {
                this.a.c(a(str, "ANNavLoadEventEnd:"));
            }
        }
    }

    public void a(boolean z) {
        this.b = z;
    }
}
