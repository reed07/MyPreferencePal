package com.facebook.ads.internal.adapters.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.t.g;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.e;
import com.facebook.ads.internal.view.j;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a.C0023a;
import java.util.List;

public abstract class a extends Adapter<e> {
    private static final int c = ((int) (x.b * 4.0f));
    final List<com.facebook.ads.internal.t.e> a;
    private final int b;
    /* access modifiers changed from: private */
    @Nullable
    public C0001a d;
    /* access modifiers changed from: private */
    public final C0023a e = new C0023a() {
        public void a() {
            if (a.this.d != null) {
                a.this.d.a();
            }
        }
    };

    /* renamed from: com.facebook.ads.internal.adapters.a.a$a reason: collision with other inner class name */
    public interface C0001a {
        void a();
    }

    a(j jVar, List<com.facebook.ads.internal.t.e> list) {
        this.b = jVar.getChildSpacing();
        this.a = list;
    }

    /* access modifiers changed from: 0000 */
    public void a(ImageView imageView, final int i) {
        final com.facebook.ads.internal.t.e eVar = (com.facebook.ads.internal.t.e) this.a.get(i);
        g j = eVar.j();
        if (j != null) {
            d a2 = new d(imageView).a();
            a2.a((com.facebook.ads.internal.view.c.e) new com.facebook.ads.internal.view.c.e() {
                public void a(boolean z) {
                    if (i == 0) {
                        eVar.a(a.this.e);
                    }
                    eVar.a(z, true);
                }
            });
            a2.a(j.a());
        }
    }

    public void a(C0001a aVar) {
        this.d = aVar;
    }

    /* renamed from: a */
    public void onBindViewHolder(e eVar, int i) {
        f a2 = eVar.a();
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.b * 2 : this.b, 0, i >= this.a.size() + -1 ? this.b * 2 : this.b, 0);
        a2.setLayoutParams(marginLayoutParams);
    }

    public int getItemCount() {
        return this.a.size();
    }
}
