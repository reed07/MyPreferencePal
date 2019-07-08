package com.inmobi.commons.core.a;

import android.support.annotation.Nullable;
import com.google.android.gms.measurement.AppMeasurement;
import com.inmobi.commons.core.b.d;
import com.inmobi.commons.core.b.e;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.e.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CrashComponent */
public class a implements e, c {
    public static AtomicBoolean b = new AtomicBoolean(false);
    private static final String e = "a";
    private static final Object f = new Object();
    private static volatile a g;
    public ExecutorService a;
    public b c = new b();
    public String d;
    /* access modifiers changed from: private */
    public c h;
    private d i;

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
        Thread.setDefaultUncaughtExceptionHandler(new e(Thread.getDefaultUncaughtExceptionHandler()));
        b.a().a("crashReporting", this.c.i);
        b.a().a("catchReporting", this.c.j);
        this.d = this.c.a;
        this.h = new c();
        this.a = Executors.newSingleThreadExecutor();
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.c = (b) aVar;
        this.d = this.c.a;
        b.a().a("crashReporting", this.c.i);
        b.a().a("catchReporting", this.c.j);
    }

    public final void a(final com.inmobi.commons.core.e.a aVar) {
        if (this.c.h) {
            b.a().a(new f("catchReporting", "CatchEventOccurred"));
            this.a.execute(new Runnable() {
                public final void run() {
                    a.this.a((d) aVar);
                    a.a(a.this);
                }
            });
        }
    }

    public final void a(d dVar) {
        if (!(dVar instanceof com.inmobi.commons.core.e.a)) {
            if (this.c.g) {
                b.a().b(new f("crashReporting", "CrashEventOccurred"));
            } else {
                return;
            }
        }
        this.h.b(this.c.e, "default");
        if ((this.h.a("default") + 1) - this.c.d >= 0) {
            c.a();
        }
        c.a(dVar);
    }

    public final com.inmobi.commons.core.b.c a(String str) {
        List<d> list;
        if (com.inmobi.commons.core.utilities.b.b.a() != 1) {
            list = c.a(this.c.k.b);
        } else {
            list = c.a(this.c.l.b);
        }
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (d dVar : list) {
                arrayList.add(Integer.valueOf(dVar.a));
            }
            String a2 = a(list);
            if (a2 != null) {
                return new com.inmobi.commons.core.b.c(arrayList, a2, false);
            }
        }
        return null;
    }

    @Nullable
    private static String a(List<d> list) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            hashMap.put("version", "2.0.0");
            hashMap.put("component", AppMeasurement.CRASH_ORIGIN);
            hashMap.put("mk-version", com.inmobi.commons.a.b.a());
            hashMap.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(hashMap);
            JSONArray jSONArray = new JSONArray();
            for (d dVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("eventId", dVar.b);
                jSONObject2.put("eventType", dVar.c);
                if (!dVar.a().trim().isEmpty()) {
                    jSONObject2.put("crash_report", dVar.a());
                }
                jSONObject2.put("ts", dVar.e);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put(AppMeasurement.CRASH_ORIGIN, jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    static /* synthetic */ void a(a aVar) {
        a aVar2 = aVar;
        if (!b.get()) {
            b bVar = aVar2.c;
            int i2 = bVar.c;
            long j = bVar.e;
            long j2 = bVar.b;
            long j3 = bVar.f;
            int i3 = bVar.l.b;
            int i4 = bVar.k.b;
            long j4 = bVar.l.a;
            long j5 = bVar.k.a;
            com.inmobi.commons.core.b.a aVar3 = r2;
            com.inmobi.commons.core.b.a aVar4 = new com.inmobi.commons.core.b.a(i2, j, j2, j3, i3, i4, j4, j5);
            aVar3.e = aVar2.d;
            aVar3.b = "default";
            d dVar = aVar2.i;
            if (dVar == null) {
                aVar2.i = new d(aVar2.h, aVar2, aVar3);
            } else {
                dVar.a(aVar3);
            }
            aVar2.i.a("default");
        }
    }
}
