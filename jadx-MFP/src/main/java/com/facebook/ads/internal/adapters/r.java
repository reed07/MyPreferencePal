package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.x.a;
import java.util.Map;

public class r extends b {
    private final c c;
    private final w d;
    private q e;

    public r(Context context, c cVar, a aVar, w wVar, c cVar2) {
        super(context, cVar2, aVar);
        this.c = cVar;
        this.d = wVar;
    }

    public void a(q qVar) {
        this.e = qVar;
    }

    /* access modifiers changed from: protected */
    public void a(Map<String, String> map) {
        q qVar = this.e;
        if (qVar != null && !TextUtils.isEmpty(qVar.a())) {
            map.put("touch", k.a(this.d.e()));
            this.c.a(this.e.a(), map);
        }
    }
}
