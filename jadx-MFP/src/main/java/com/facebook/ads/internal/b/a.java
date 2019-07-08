package com.facebook.ads.internal.b;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSettings.TestAdType;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.u.b;
import com.facebook.ads.internal.w.b.m;
import com.facebook.ads.internal.w.b.q;
import java.util.EnumSet;

public class a {
    final String a;
    final e b;
    final d c;
    final EnumSet<CacheFlag> d;
    String e;
    boolean f;
    int g;
    @Nullable
    com.facebook.ads.internal.t.d h;
    private final AdPlacementType i;
    private final int j;

    public a(String str, e eVar, AdPlacementType adPlacementType, d dVar, int i2) {
        this(str, eVar, adPlacementType, dVar, i2, EnumSet.of(CacheFlag.NONE));
    }

    public a(String str, e eVar, AdPlacementType adPlacementType, d dVar, int i2, EnumSet<CacheFlag> enumSet) {
        this.a = str;
        this.i = adPlacementType;
        this.c = dVar;
        this.j = i2;
        this.d = enumSet;
        this.b = eVar;
        this.g = -1;
    }

    /* access modifiers changed from: 0000 */
    public AdPlacementType a() {
        AdPlacementType adPlacementType = this.i;
        if (adPlacementType != null) {
            return adPlacementType;
        }
        d dVar = this.c;
        return dVar == null ? AdPlacementType.NATIVE : dVar == d.INTERSTITIAL ? AdPlacementType.INTERSTITIAL : AdPlacementType.BANNER;
    }

    /* access modifiers changed from: 0000 */
    public b a(Context context, g gVar) {
        com.facebook.ads.internal.n.d dVar = new com.facebook.ads.internal.n.d(context, false);
        String str = this.a;
        d dVar2 = this.c;
        b bVar = new b(context, dVar, str, dVar2 != null ? new m(dVar2.b(), this.c.a()) : null, this.b, AdSettings.getTestAdType() != TestAdType.DEFAULT ? AdSettings.getTestAdType().getAdTypeString() : null, this.j, AdSettings.isTestMode(context), AdSettings.isChildDirected(), gVar, q.a(com.facebook.ads.internal.r.a.G(context)), this.e);
        return bVar;
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void a(@Nullable com.facebook.ads.internal.t.d dVar) {
        this.h = dVar;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(boolean z) {
        this.f = z;
    }
}
