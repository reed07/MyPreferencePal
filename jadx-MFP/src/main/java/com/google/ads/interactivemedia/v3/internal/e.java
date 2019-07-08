package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: IMASDK */
public class e {
    private final ff a;
    private final List<ap> b;
    private ap c;
    private v d;
    private boolean e;
    private boolean f;
    private String g;

    public e() {
    }

    public void a() {
        if (!this.e) {
            this.e = true;
            j.a().b(this);
            this.d.a(g.a().d());
            v vVar = this.d;
            ff ffVar = this.a;
            String f2 = f();
            JSONObject jSONObject = new JSONObject();
            z.a(jSONObject, "environment", "app");
            z.a(jSONObject, "adSessionType", ffVar.i());
            z.a(jSONObject, "deviceInfo", ho.d());
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("clid");
            jSONArray.put("vlid");
            z.a(jSONObject, "supports", jSONArray);
            JSONObject jSONObject2 = new JSONObject();
            z.a(jSONObject2, "partnerName", ffVar.d().a());
            z.a(jSONObject2, "partnerVersion", ffVar.d().b());
            z.a(jSONObject, "omidNativeInfo", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            z.a(jSONObject3, "libraryVersion", "1.2.12-google_20190214");
            z.a(jSONObject3, "appId", n.a().b().getApplicationContext().getPackageName());
            z.a(jSONObject, "app", jSONObject3);
            if (ffVar.g() != null) {
                z.a(jSONObject, "customReferenceData", ffVar.g());
            }
            JSONObject jSONObject4 = new JSONObject();
            Iterator it = ffVar.e().iterator();
            if (!it.hasNext()) {
                o.a().a(vVar.c(), f2, jSONObject, jSONObject4);
            } else {
                it.next();
                throw new NoSuchMethodError();
            }
        }
    }

    public void a(View view) {
        if (!this.f) {
            ho.a((Object) view, "AdView is null");
            if (g() != view) {
                e(view);
                e().e();
                f(view);
            }
        }
    }

    public void b() {
        if (!this.f) {
            this.c.clear();
            c();
            this.f = true;
            o.a().a(e().c());
            j.a().c(this);
            e().b();
            this.d = null;
        }
    }

    public void b(View view) {
        if (!this.f) {
            d(view);
            if (c(view) == null) {
                this.b.add(new ap(view));
            }
        }
    }

    public void c() {
        if (!this.f) {
            this.b.clear();
        }
    }

    public static e a(ax axVar, ff ffVar) {
        ho.g();
        ho.a((Object) axVar, "AdSessionConfiguration is null");
        ho.a((Object) ffVar, "AdSessionContext is null");
        return new e(axVar, ffVar);
    }

    e(ax axVar, ff ffVar) {
        this();
        this.b = new ArrayList();
        this.e = false;
        this.f = false;
        this.a = ffVar;
        this.g = UUID.randomUUID().toString();
        e(null);
        if (ffVar.i() == c.HTML) {
            this.d = new v(ffVar.f());
        } else {
            this.d = new x(ffVar.e(), ffVar.h());
        }
        this.d.a();
        j.a().a(this);
        o.a().a(this.d.c(), axVar.a());
    }

    private ap c(View view) {
        for (ap apVar : this.b) {
            if (apVar.get() == view) {
                return apVar;
            }
        }
        return null;
    }

    public List<ap> d() {
        return this.b;
    }

    private static void d(View view) {
        if (view == null) {
            throw new IllegalArgumentException("FriendlyObstruction is null");
        }
    }

    public v e() {
        return this.d;
    }

    public String f() {
        return this.g;
    }

    public View g() {
        return (View) this.c.get();
    }

    private void e(View view) {
        this.c = new ap(view);
    }

    public boolean h() {
        return this.e && !this.f;
    }

    private void f(View view) {
        Collection<e> b2 = j.a().b();
        if (b2 != null && b2.size() > 0) {
            for (e eVar : b2) {
                if (eVar != this && eVar.g() == view) {
                    eVar.c.clear();
                }
            }
        }
    }
}
