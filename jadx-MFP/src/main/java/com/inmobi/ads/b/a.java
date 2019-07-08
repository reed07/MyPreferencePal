package com.inmobi.ads.b;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.a.b;
import com.inmobi.ads.d;
import com.inmobi.ads.f;
import com.inmobi.ads.h;
import com.inmobi.ads.i;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* compiled from: GMAManager */
public final class a {
    @NonNull
    public i a;
    @NonNull
    public String b;
    public long c;
    @Nullable
    public b d;

    public a(@NonNull i iVar, @NonNull String str) {
        this.a = iVar;
        this.b = str;
    }

    public final byte[] a() throws b {
        List list;
        List list2;
        this.a.d("AdCacheImpressionRequested");
        this.a.i();
        h.a();
        h i = this.a.i();
        long j = this.a.d;
        this.a.b();
        String c2 = this.a.c();
        MonetizationContext l = this.a.l();
        String str = this.b;
        h.c();
        if (i.d.e) {
            list = i.b.d(j, c2, l, str);
        } else {
            list = i.b.c(j, c2, l, str);
        }
        com.inmobi.ads.a aVar = list.size() == 0 ? null : (com.inmobi.ads.a) list.get(0);
        f t = this.a.t();
        if (aVar == null) {
            list2 = null;
        } else {
            list2 = Collections.singletonList(aVar);
        }
        this.d = new b(t, list2);
        if (aVar != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("impId", aVar.g);
            this.a.c("AdCacheImpressionOffered", (Map<String, Object>) hashMap);
        }
        if (aVar != null) {
            h i2 = this.a.i();
            String str2 = aVar.g;
            d dVar = i2.b;
            d.b(str2);
        }
        this.a.i().a(this.a.u());
        this.c = System.currentTimeMillis();
        try {
            return this.d.a();
        } catch (JSONException unused) {
            return null;
        }
    }
}
