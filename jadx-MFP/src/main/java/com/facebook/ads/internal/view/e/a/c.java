package com.facebook.ads.internal.view.e.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.w.b.w;
import java.util.List;

public class c extends Adapter<f> {
    private final com.facebook.ads.internal.s.c a;
    @Nullable
    private final b b;
    private final com.facebook.ads.internal.x.a c;
    private final w d;
    private final h e;
    @Nullable
    private C0012a f;
    private int g;
    private int h;
    private String i;
    private int j;
    private int k;
    private List<b> l;
    private final a m;
    private final SparseBooleanArray n = new SparseBooleanArray();

    public interface a {
        void a(int i);
    }

    c(List<b> list, com.facebook.ads.internal.s.c cVar, b bVar, com.facebook.ads.internal.x.a aVar, w wVar, C0012a aVar2, h hVar, String str, int i2, int i3, int i4, int i5, a aVar3) {
        this.a = cVar;
        this.b = bVar;
        this.c = aVar;
        this.d = wVar;
        this.f = aVar2;
        this.l = list;
        this.h = i2;
        this.e = hVar;
        this.j = i5;
        this.i = str;
        this.g = i4;
        this.k = i3;
        this.m = aVar3;
    }

    /* renamed from: a */
    public f onCreateViewHolder(ViewGroup viewGroup, int i2) {
        com.facebook.ads.internal.view.component.a.e.a aVar = new com.facebook.ads.internal.view.component.a.e.a(viewGroup.getContext(), this.a, this.f, null, null, this.c, this.d);
        f fVar = new f(com.facebook.ads.internal.view.component.a.a.c.a(aVar.a(), this.j, this.e, this.i, this.m), this.n, this.c, this.h, this.g, this.k, this.l.size());
        return fVar;
    }

    /* renamed from: a */
    public void onBindViewHolder(f fVar, int i2) {
        fVar.a((b) this.l.get(i2), this.a, this.b, this.d, this.i);
    }

    public int getItemCount() {
        return this.l.size();
    }
}
