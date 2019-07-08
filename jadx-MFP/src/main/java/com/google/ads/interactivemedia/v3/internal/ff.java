package com.google.ads.interactivemedia.v3.internal;

import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.internal.fg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public class ff<T extends fg> implements fd<T> {
    private final ez a;
    private final fg b;
    private final WebView c;
    private final List<e> d = new ArrayList();
    private final String e;
    private final String f;
    private final c g;

    public final int a() {
        return 1;
    }

    public final T c() {
        return null;
    }

    public final ez b() {
        return this.a;
    }

    private ff(fg fgVar, WebView webView, String str, List<e> list, String str2) {
        this.b = fgVar;
        this.c = webView;
        this.e = null;
        this.g = c.HTML;
        this.f = str2;
    }

    public static ff a(fg fgVar, WebView webView, String str) {
        ho.a((Object) fgVar, "Partner is null");
        ho.a((Object) webView, "WebView is null");
        if (str != null) {
            ho.a(str, 256, "CustomReferenceData is greater than 256 characters");
        }
        ff ffVar = new ff(fgVar, webView, null, null, str);
        return ffVar;
    }

    public fg d() {
        return this.b;
    }

    public List<e> e() {
        return Collections.unmodifiableList(this.d);
    }

    public WebView f() {
        return this.c;
    }

    public String g() {
        return this.f;
    }

    public String h() {
        return this.e;
    }

    public c i() {
        return this.g;
    }
}
