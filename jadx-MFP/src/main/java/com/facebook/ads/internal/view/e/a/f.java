package com.facebook.ads.internal.view.e.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ViewGroup.MarginLayoutParams;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.component.a.a.b;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.x.a;
import com.facebook.ads.internal.x.a.C0023a;
import java.util.Map;

class f extends ViewHolder {
    private final b a;
    /* access modifiers changed from: private */
    public final SparseBooleanArray b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    /* access modifiers changed from: private */
    @Nullable
    public a g;
    private C0023a h;
    /* access modifiers changed from: private */
    public a i;

    f(b bVar, SparseBooleanArray sparseBooleanArray, a aVar, int i2, int i3, int i4, int i5) {
        super(bVar);
        this.a = bVar;
        this.b = sparseBooleanArray;
        this.i = aVar;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
    }

    /* access modifiers changed from: 0000 */
    public void a(final b bVar, c cVar, com.facebook.ads.internal.h.b bVar2, w wVar, String str) {
        int b2 = bVar.b();
        this.a.setTag(-1593835536, Integer.valueOf(b2));
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(this.c, -2);
        marginLayoutParams.setMargins(b2 == 0 ? this.d : this.e, 0, b2 >= this.f + -1 ? this.d : this.e, 0);
        String g2 = bVar.c().c().g();
        String a2 = bVar.c().c().a();
        this.a.setIsVideo(!TextUtils.isEmpty(a2));
        if (this.a.f()) {
            this.a.setVideoPlaceholderUrl(g2);
            b bVar3 = this.a;
            String c2 = (bVar2 == null || a2 == null) ? "" : bVar2.c(a2);
            if (TextUtils.isEmpty(c2)) {
                c2 = a2;
            }
            bVar3.setVideoUrl(c2);
        } else {
            this.a.setImageUrl(g2);
        }
        this.a.setLayoutParams(marginLayoutParams);
        this.a.a(bVar.c().a().a(), bVar.c().a().c());
        this.a.a(bVar.c().b(), bVar.a());
        this.a.a(bVar.a());
        if (!this.b.get(bVar.b())) {
            a aVar = this.g;
            if (aVar != null) {
                aVar.c();
                this.g = null;
            }
            final Map a3 = bVar.a();
            final String str2 = str;
            final b bVar4 = bVar;
            final w wVar2 = wVar;
            final c cVar2 = cVar;
            AnonymousClass1 r0 = new C0023a() {
                public void a() {
                    if (!f.this.i.b() && !TextUtils.isEmpty(str2) && !f.this.b.get(bVar4.b())) {
                        if (f.this.g != null) {
                            f.this.g.a(a3);
                        }
                        a3.put("touch", k.a(wVar2.e()));
                        cVar2.a(str2, a3);
                        f.this.b.put(bVar4.b(), true);
                    }
                }
            };
            this.h = r0;
            this.g = new a(this.a, 10, this.h);
            this.g.a(100);
            this.g.b(100);
            this.a.setOnAssetsLoadedListener(new b.a() {
                public void a() {
                    if (bVar.b() == 0) {
                        f.this.i.a();
                    }
                    f.this.g.a();
                }
            });
        }
    }
}
