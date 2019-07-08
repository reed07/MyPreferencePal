package com.inmobi.b;

import android.support.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.commons.core.b.d;
import com.inmobi.commons.core.b.e;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.f.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TRCComponent */
public class a implements e, c {
    public static AtomicBoolean b = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static final String e = "a";
    private static final Object f = new Object();
    private static volatile a g;
    public ExecutorService a = Executors.newSingleThreadExecutor();
    public com.inmobi.ads.c c = new com.inmobi.ads.c();
    public String d = this.c.b;
    /* access modifiers changed from: private */
    public com.inmobi.commons.core.f.a h = new com.inmobi.commons.core.f.a();
    /* access modifiers changed from: private */
    public d i;

    public static a a() {
        a aVar = g;
        if (aVar == null) {
            synchronized (f) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a();
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.c = (com.inmobi.ads.c) aVar;
        this.d = this.c.b;
    }

    private void b(final String str) {
        this.a.execute(new Runnable() {
            public final void run() {
                a.this.h;
                if (com.inmobi.commons.core.f.a.c(str)) {
                    a.a(a.this, str);
                }
            }
        });
    }

    public final com.inmobi.commons.core.b.c a(String str) {
        List<b> list;
        com.inmobi.ads.c.a b2 = this.c.b(str);
        if (com.inmobi.commons.core.utilities.b.b.a() != 1) {
            list = com.inmobi.commons.core.f.a.a(b2.f.b, str);
        } else {
            list = com.inmobi.commons.core.f.a.a(b2.g.b, str);
        }
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (b bVar : list) {
                arrayList.add(Integer.valueOf(bVar.a));
            }
            String a2 = a(list);
            if (a2 != null) {
                return new com.inmobi.commons.core.b.c(arrayList, a2, false);
            }
        }
        return null;
    }

    @Nullable
    private static String a(List<b> list) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            hashMap.put("version", "2.0.0");
            hashMap.put("component", "trc");
            hashMap.put("adtype", ((b) list.get(0)).j);
            hashMap.put("mk-version", com.inmobi.commons.a.b.a());
            hashMap.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(hashMap);
            JSONArray jSONArray = new JSONArray();
            for (b bVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("event-id", bVar.b);
                jSONObject2.put("ad-markup-type", bVar.c);
                jSONObject2.put("event-name", bVar.d);
                jSONObject2.put("im-plid", bVar.e);
                jSONObject2.put("request-id", bVar.f);
                jSONObject2.put("event-type", bVar.g);
                jSONObject2.put("d-nettype-raw", bVar.h);
                jSONObject2.put("ts", bVar.i);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("extra-info", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    static /* synthetic */ void a(a aVar, b bVar) {
        com.inmobi.ads.c.a b2 = aVar.c.b(bVar.j);
        aVar.h.b(b2.b, bVar.j);
        if ((aVar.h.a(bVar.j) + 1) - b2.c >= 0) {
            com.inmobi.commons.core.f.a.d(bVar.j);
        }
        com.inmobi.commons.core.f.a.a(bVar);
    }

    static /* synthetic */ void a(a aVar, String str) {
        a aVar2 = aVar;
        String str2 = str;
        if (!b.get()) {
            com.inmobi.ads.c.a b2 = aVar2.c.b(str2);
            int i2 = b2.a;
            long j = b2.b;
            long j2 = b2.d;
            long j3 = b2.e;
            int i3 = b2.g.b;
            int i4 = b2.f.b;
            long j4 = b2.g.a;
            long j5 = b2.f.a;
            com.inmobi.commons.core.b.a aVar3 = r3;
            com.inmobi.commons.core.b.a aVar4 = new com.inmobi.commons.core.b.a(i2, j, j2, j3, i3, i4, j4, j5);
            aVar3.e = aVar2.d;
            aVar3.b = str2;
            d dVar = aVar2.i;
            if (dVar == null) {
                aVar2.i = new d(aVar2.h, aVar2, aVar3);
            } else {
                dVar.a(aVar3);
            }
            aVar2.i.a(str2);
        }
    }

    static /* synthetic */ void a(a aVar) {
        aVar.b("banner");
        aVar.b("int");
        aVar.b(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
    }
}
