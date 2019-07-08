package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.impl.data.ab;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: IMASDK */
public final class aee implements AdErrorListener, AdEventListener, acu, adx {
    private final aeb a;
    private final aef b;
    private final Context c;
    private View d;
    private String e;
    private final Set<View> f;
    private boolean g;
    private boolean h;
    private e i;

    aee(aeb aeb, Context context) {
        this(aeb, context, new aef());
    }

    private aee(aeb aeb, Context context, aef aef) {
        this.g = false;
        this.h = false;
        this.a = aeb;
        this.c = context;
        this.b = aef;
        this.f = new HashSet();
    }

    public final void b() {
        a.a();
        a.a(this.c);
        this.g = true;
    }

    public final void c() {
        this.g = false;
    }

    public final void a(boolean z) {
        this.h = true;
    }

    public final void b(View view) {
        this.d = view;
    }

    public final void a(String str) {
        this.e = str;
    }

    public final boolean d() {
        if (this.g) {
            e eVar = this.i;
            if (eVar != null) {
                eVar.b();
                this.i = null;
                return true;
            }
        }
        return false;
    }

    public final void onAdError(AdErrorEvent adErrorEvent) {
        if (this.g) {
            e eVar = this.i;
            if (eVar != null) {
                eVar.b();
                this.i = null;
            }
        }
    }

    public final void onAdEvent(AdEvent adEvent) {
        if (this.g) {
            int ordinal = adEvent.getType().ordinal();
            if (ordinal != 2) {
                if (ordinal != 18) {
                    switch (ordinal) {
                        case 13:
                            break;
                        case 14:
                            break;
                    }
                }
                if (this.g && this.i == null && this.d != null) {
                    ax a2 = ax.a(d.JAVASCRIPT, d.JAVASCRIPT, true);
                    fg a3 = fg.a("Google1", "3.11.2");
                    WebView b2 = this.a.b();
                    String str = this.h ? "true" : "false";
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 7);
                    sb.append("{ssai:");
                    sb.append(str);
                    sb.append("}");
                    this.i = e.a(a2, ff.a(a3, b2, sb.toString()));
                    this.i.a(this.d);
                    for (View b3 : this.f) {
                        this.i.b(b3);
                    }
                    if (!this.f.isEmpty()) {
                        a((List<View>) new ArrayList<View>(this.f));
                    }
                    this.i.a();
                    return;
                }
            }
            d();
        }
    }

    public final void c(View view) {
        if (!this.f.contains(view)) {
            this.f.add(view);
            e eVar = this.i;
            if (eVar != null) {
                eVar.b(view);
                a(Arrays.asList(new View[]{view}));
            }
        }
    }

    public final void a(View view) {
        c(view);
    }

    public final void a() {
        this.f.clear();
        e eVar = this.i;
        if (eVar != null) {
            eVar.c();
            a(null);
        }
    }

    private final void a(List<View> list) {
        this.a.b(new ado(adq.omid, adr.registerFriendlyObstructions, this.e, list != null ? ab.builder().views(list).build() : null));
    }
}
