package com.facebook.ads.internal.adapters.a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.view.j;
import com.facebook.ads.internal.view.k;
import com.facebook.ads.internal.view.x;
import java.util.List;

public class c extends a {
    public c(j jVar, List<e> list) {
        super(jVar, list);
    }

    /* renamed from: a */
    public com.facebook.ads.internal.view.e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new com.facebook.ads.internal.view.e(new k(viewGroup.getContext()));
    }

    /* renamed from: a */
    public void onBindViewHolder(com.facebook.ads.internal.view.e eVar, int i) {
        super.onBindViewHolder(eVar, i);
        k kVar = (k) eVar.a();
        x xVar = (x) kVar.getImageCardView();
        xVar.setImageDrawable(null);
        a((ImageView) xVar, i);
        ((e) this.a.get(i)).a((View) kVar, (f) kVar);
    }
}
