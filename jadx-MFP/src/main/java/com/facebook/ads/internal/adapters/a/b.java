package com.facebook.ads.internal.adapters.a;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.view.c;
import com.facebook.ads.internal.view.j;
import java.util.ArrayList;
import java.util.List;

public class b extends a {
    public b(j jVar, List<e> list) {
        super(jVar, list);
    }

    /* renamed from: a */
    public com.facebook.ads.internal.view.e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new com.facebook.ads.internal.view.e(new c(viewGroup.getContext()));
    }

    /* renamed from: a */
    public void onBindViewHolder(com.facebook.ads.internal.view.e eVar, int i) {
        super.onBindViewHolder(eVar, i);
        c cVar = (c) eVar.a();
        a(cVar.getImageCardView(), i);
        cVar.setTitle(((e) this.a.get(i)).a("headline"));
        cVar.setSubtitle(((e) this.a.get(i)).a("link_description"));
        cVar.setButtonText(((e) this.a.get(i)).a("call_to_action"));
        e eVar2 = (e) this.a.get(i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cVar);
        eVar2.a((View) cVar, (f) cVar, (List<View>) arrayList);
    }
}
