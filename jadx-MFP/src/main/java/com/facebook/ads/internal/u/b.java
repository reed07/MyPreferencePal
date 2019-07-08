package com.facebook.ads.internal.u;

import android.content.Context;
import com.facebook.ads.internal.f.a;
import com.facebook.ads.internal.n.d;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.c;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.w.b.m;
import com.facebook.ads.internal.w.b.v;
import com.facebook.ads.internal.w.b.z;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class b {
    private String a;
    private c b;
    private final AdPlacementType c = this.b.a();
    private final String d;
    private Context e;
    private e f;
    private boolean g;
    private boolean h;
    private int i;
    private m j;
    private final Map<String, String> k;
    private final g l;
    private String m;
    private String n;

    public b(Context context, d dVar, String str, m mVar, e eVar, String str2, int i2, boolean z, boolean z2, g gVar, String str3, String str4) {
        this.e = context;
        this.k = dVar.b();
        this.a = str;
        this.j = mVar;
        this.f = eVar;
        this.d = str2;
        this.i = i2;
        this.g = z;
        this.h = z2;
        this.l = gVar;
        this.b = c.a(eVar);
        this.m = str3;
        this.n = str4;
    }

    private void a(Map<String, String> map, String str, String str2) {
        map.put(str, str2);
    }

    public e a() {
        return this.f;
    }

    public String b() {
        return this.a;
    }

    public c c() {
        return this.b;
    }

    public m d() {
        return this.j;
    }

    public int e() {
        return this.i;
    }

    public g f() {
        return this.l;
    }

    public Map<String, String> g() {
        HashMap hashMap = new HashMap(this.k);
        a(hashMap, "IDFA", com.facebook.ads.internal.g.b.b);
        a(hashMap, "IDFA_FLAG", com.facebook.ads.internal.g.b.c ? "0" : AppEventsConstants.EVENT_PARAM_VALUE_YES);
        a(hashMap, "COPPA", String.valueOf(this.h));
        a(hashMap, "PLACEMENT_ID", this.a);
        if (this.c != AdPlacementType.UNKNOWN) {
            a(hashMap, "PLACEMENT_TYPE", this.c.toString().toLowerCase());
        }
        m mVar = this.j;
        if (mVar != null) {
            a(hashMap, "WIDTH", String.valueOf(mVar.b()));
            a(hashMap, "HEIGHT", String.valueOf(this.j.a()));
        }
        e eVar = this.f;
        if (eVar != null) {
            a(hashMap, "TEMPLATE_ID", String.valueOf(eVar.a()));
        }
        if (this.g) {
            a(hashMap, "TEST_MODE", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        String str = this.d;
        if (str != null) {
            a(hashMap, "DEMO_AD_ID", str);
        }
        int i2 = this.i;
        if (i2 != 0) {
            a(hashMap, "NUM_ADS_REQUESTED", String.valueOf(i2));
        }
        a(hashMap, "CLIENT_EVENTS", com.facebook.ads.internal.o.b.a());
        a(hashMap, "KG_RESTRICTED", String.valueOf(z.a(this.e)));
        a(hashMap, "REQUEST_TIME", v.b(System.currentTimeMillis()));
        if (this.l.c()) {
            a(hashMap, "BID_ID", this.l.d());
        }
        String str2 = this.m;
        if (str2 != null) {
            a(hashMap, "STACK_TRACE", str2);
        }
        a(hashMap, "CLIENT_REQUEST_ID", UUID.randomUUID().toString());
        a(hashMap, "AD_REPORTING_CONFIG_LAST_UPDATE_TIME", v.a(a.a(this.e)));
        String str3 = this.n;
        if (str3 != null) {
            a(hashMap, "EXTRA_HINTS", str3);
        }
        return hashMap;
    }
}
