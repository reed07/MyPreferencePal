package com.facebook.ads.internal.b;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.k;
import com.facebook.ads.internal.adapters.s;
import com.facebook.ads.internal.adapters.t;
import com.facebook.ads.internal.m.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.Map;

public class h extends c {
    public h(Context context, a aVar) {
        super(context, aVar);
    }

    /* access modifiers changed from: protected */
    public void a() {
        s sVar = (s) this.f;
        sVar.a(this.h.g);
        sVar.b();
    }

    public void a(RewardData rewardData) {
        if (this.f == null) {
            throw new IllegalStateException("no adapter ready to set reward on");
        } else if (this.f.getPlacementType() == AdPlacementType.REWARDED_VIDEO) {
            ((s) this.f).a(rewardData);
        } else {
            throw new IllegalStateException("can only set on rewarded video ads");
        }
    }

    /* access modifiers changed from: protected */
    public void a(AdAdapter adAdapter, c cVar, a aVar, Map<String, Object> map) {
        ((k) adAdapter).a(this.b, new t() {
            public void a() {
                h.this.c.h();
            }

            public void a(s sVar) {
                h hVar = h.this;
                hVar.f = sVar;
                hVar.c.a((AdAdapter) sVar);
            }

            public void a(s sVar, AdError adError) {
                h.this.c.a(new com.facebook.ads.internal.protocol.a(AdErrorType.INTERNAL_ERROR, (String) null));
                h.this.a((AdAdapter) sVar);
                h.this.i();
            }

            public void b() {
                h.this.c.k();
            }

            public void b(s sVar) {
                h.this.c.a();
            }

            public void c(s sVar) {
                h.this.c.b();
            }

            public void d(s sVar) {
                h.this.c.g();
            }

            public void e(s sVar) {
                h.this.c.i();
            }

            public void f(s sVar) {
                h.this.c.j();
            }
        }, map, this.h.f, this.h.e);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public com.facebook.ads.internal.protocol.a c() {
        if (!this.h.f || d()) {
            return null;
        }
        return new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }
}
