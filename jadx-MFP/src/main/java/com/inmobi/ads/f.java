package com.inmobi.ads;

import android.net.Uri;
import com.google.android.gms.fitness.data.WorkoutExercises;
import com.inmobi.a.b.b;
import com.inmobi.a.m;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.b.a;
import com.inmobi.commons.core.utilities.b.g;
import com.inmobi.commons.core.utilities.uid.d;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.myfitnesspal.shared.db.table.ImagesTable.Columns;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/* compiled from: AdNetworkRequest */
public final class f extends c {
    private static final String B = "f";
    public long a;
    String b = AdType.STATIC_NATIVE;
    public String c;
    int d = 1;
    public String e;
    public String f;
    public Map<String, String> g;
    Map<String, String> h;
    public final String i;
    public MonetizationContext j;
    public final r k;
    boolean l = false;

    public f(String str, long j2, d dVar, String str2) {
        super(HttpConstants.METHOD_POST, str, a(str), dVar, a(str), 0);
        this.a = j2;
        this.o.put("im-plid", String.valueOf(this.a));
        this.o.putAll(g.d());
        this.o.putAll(com.inmobi.commons.core.utilities.b.c.c());
        this.o.put("u-appIS", a.a().a);
        this.o.putAll(m.a().f());
        this.o.putAll(m.a().e());
        Map map = this.o;
        com.inmobi.a.b.a a2 = b.a();
        HashMap hashMap = new HashMap();
        if (a2 != null) {
            hashMap.put("c-ap-bssid", String.valueOf(a2.a));
        }
        map.putAll(hashMap);
        this.o.putAll(com.inmobi.a.a.c.b());
        this.o.putAll(com.inmobi.a.a.c.c());
        this.o.putAll(com.inmobi.a.a.c.a());
        this.i = UUID.randomUUID().toString();
        this.o.put("client-request-id", this.i);
        if (str2 != null) {
            this.o.put("u-appcache", str2);
        }
        this.o.put("sdk-flavor", WorkoutExercises.ROW);
        this.k = new r();
        this.o.put("skdv", this.z.c);
        this.o.put("skdm", this.k.a(this.z.b, this.z.a));
    }

    private static boolean a(String str) {
        if (str == null) {
            return true;
        }
        Uri parse = Uri.parse(str);
        if (Constants.HTTP.equals(parse.getScheme()) || !Constants.HTTPS.equals(parse.getScheme())) {
            return true;
        }
        return false;
    }

    public final void a() {
        super.a();
        this.o.put(Columns.FORMAT, this.b);
        this.o.put("mk-ads", String.valueOf(this.d));
        this.o.put("adtype", this.e);
        if (this.f != null) {
            this.o.put("p-keywords", this.f);
        }
        MonetizationContext monetizationContext = this.j;
        String str = monetizationContext != null ? monetizationContext == MonetizationContext.MONETIZATION_CONTEXT_OTHER ? "M10N_CONTEXT_OTHER" : "M10N_CONTEXT_ACTIVITY" : "M10N_CONTEXT_ACTIVITY";
        this.o.put("m10n_context", str);
        Map<String, String> map = this.g;
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                if (!this.o.containsKey(entry.getKey())) {
                    this.o.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.h != null) {
            this.o.putAll(this.h);
        }
    }

    public final String c() {
        return this.h.containsKey("preload-request") ? (String) this.h.get("preload-request") : "0";
    }

    public final boolean b() {
        return this.l || super.b();
    }
}
