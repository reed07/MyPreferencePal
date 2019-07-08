package com.facebook.ads.internal.view.component.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.i.c.o;
import com.facebook.ads.internal.w.b.w;

public class e {
    private final Context a;
    private final c b;
    private final C0012a c;
    private final k d;
    private final View e;
    private final com.facebook.ads.internal.x.a f;
    private final w g;
    private final int h;
    private final int i;
    @Nullable
    private final o j;
    @Nullable
    private final View k;

    public static class a {
        /* access modifiers changed from: private */
        public final Context a;
        /* access modifiers changed from: private */
        public final c b;
        /* access modifiers changed from: private */
        public final C0012a c;
        /* access modifiers changed from: private */
        public final k d;
        /* access modifiers changed from: private */
        public final View e;
        /* access modifiers changed from: private */
        public final com.facebook.ads.internal.x.a f;
        /* access modifiers changed from: private */
        public final w g;
        /* access modifiers changed from: private */
        public int h = 0;
        /* access modifiers changed from: private */
        public int i = 1;
        /* access modifiers changed from: private */
        @Nullable
        public o j;
        /* access modifiers changed from: private */
        @Nullable
        public View k;

        public a(Context context, c cVar, C0012a aVar, k kVar, View view, com.facebook.ads.internal.x.a aVar2, w wVar) {
            this.a = context;
            this.b = cVar;
            this.c = aVar;
            this.d = kVar;
            this.e = view;
            this.f = aVar2;
            this.g = wVar;
        }

        public a a(int i2) {
            this.h = i2;
            return this;
        }

        public a a(View view) {
            this.k = view;
            return this;
        }

        public a a(o oVar) {
            this.j = oVar;
            return this;
        }

        public e a() {
            return new e(this);
        }

        public a b(int i2) {
            this.i = i2;
            return this;
        }
    }

    private e(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
    }

    /* access modifiers changed from: 0000 */
    public Context a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public c b() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public C0012a c() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public View d() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public com.facebook.ads.internal.x.a e() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public w f() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public k g() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public o h() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public View i() {
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public int j() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public int k() {
        return this.i;
    }
}
