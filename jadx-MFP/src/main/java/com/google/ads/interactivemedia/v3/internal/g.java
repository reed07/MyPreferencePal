package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: IMASDK */
public class g implements m {
    private static g a;
    private float b = BitmapDescriptorFactory.HUE_RED;
    private final i c;
    private final f d;
    private h e;
    private j f;

    public void a(float f2) {
        this.b = f2;
        for (e e2 : e().c()) {
            e2.e().a(f2);
        }
    }

    public g(i iVar, f fVar) {
        this.c = iVar;
        this.d = fVar;
    }

    public static g a() {
        if (a == null) {
            a = new g(new i(), new f());
        }
        return a;
    }

    public void a(Context context) {
        this.e = new h(new Handler(), context, new ho(), this);
    }

    public void b() {
        k.a().a((m) this);
        k.a().b();
        if (k.a().d()) {
            ac.a().b();
        }
        this.e.a();
    }

    public void c() {
        ac.a().c();
        k.a().c();
        this.e.b();
    }

    public void a(boolean z) {
        if (z) {
            ac.a().b();
        } else {
            ac.a().d();
        }
    }

    private j e() {
        if (this.f == null) {
            this.f = j.a();
        }
        return this.f;
    }

    public float d() {
        return this.b;
    }
}
