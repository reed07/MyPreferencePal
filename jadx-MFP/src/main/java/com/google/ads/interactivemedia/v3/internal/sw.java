package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class sw implements sn {
    private final Context a;
    private final List<tz> b = new ArrayList();
    private final sn c;
    private sn d;
    private sn e;
    private sn f;
    private sn g;
    private sn h;
    private sn i;
    private sn j;

    public sw(Context context, sn snVar) {
        this.a = context.getApplicationContext();
        this.c = (sn) qi.a(snVar);
    }

    public final void a(tz tzVar) {
        this.c.a(tzVar);
        this.b.add(tzVar);
        a(this.d, tzVar);
        a(this.e, tzVar);
        a(this.f, tzVar);
        a(this.g, tzVar);
        a(this.h, tzVar);
        a(this.i, tzVar);
    }

    public final long a(sr srVar) throws IOException {
        qi.c(this.j == null);
        String scheme = srVar.a.getScheme();
        if (vf.a(srVar.a)) {
            String path = srVar.a.getPath();
            if (path == null || !path.startsWith("/android_asset/")) {
                if (this.d == null) {
                    this.d = new sz();
                    a(this.d);
                }
                this.j = this.d;
            } else {
                this.j = d();
            }
        } else if ("asset".equals(scheme)) {
            this.j = d();
        } else if (Param.CONTENT.equals(scheme)) {
            if (this.f == null) {
                this.f = new sk(this.a);
                a(this.f);
            }
            this.j = this.f;
        } else if ("rtmp".equals(scheme)) {
            this.j = e();
        } else if ("data".equals(scheme)) {
            if (this.h == null) {
                this.h = new sm();
                a(this.h);
            }
            this.j = this.h;
        } else if (RawResourceDataSource.RAW_RESOURCE_SCHEME.equals(scheme)) {
            if (this.i == null) {
                this.i = new tw(this.a);
                a(this.i);
            }
            this.j = this.i;
        } else {
            this.j = this.c;
        }
        return this.j.a(srVar);
    }

    public final int a(byte[] bArr, int i2, int i3) throws IOException {
        return ((sn) qi.a(this.j)).a(bArr, i2, i3);
    }

    public final Uri a() {
        sn snVar = this.j;
        if (snVar == null) {
            return null;
        }
        return snVar.a();
    }

    public final Map<String, List<String>> b() {
        sn snVar = this.j;
        return snVar == null ? Collections.emptyMap() : snVar.b();
    }

    public final void c() throws IOException {
        sn snVar = this.j;
        if (snVar != null) {
            try {
                snVar.c();
            } finally {
                this.j = null;
            }
        }
    }

    private final sn d() {
        if (this.e == null) {
            this.e = new sg(this.a);
            a(this.e);
        }
        return this.e;
    }

    private final sn e() {
        if (this.g == null) {
            try {
                this.g = (sn) Class.forName("com.google.ads.interactivemedia.v3.exoplayer2.ext.rtmp.RtmpDataSource").getConstructor(new Class[0]).newInstance(new Object[0]);
                a(this.g);
            } catch (ClassNotFoundException unused) {
                Log.w("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
            } catch (Exception e2) {
                throw new RuntimeException("Error instantiating RTMP extension", e2);
            }
            if (this.g == null) {
                this.g = this.c;
            }
        }
        return this.g;
    }

    private final void a(sn snVar) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            snVar.a((tz) this.b.get(i2));
        }
    }

    private static void a(sn snVar, tz tzVar) {
        if (snVar != null) {
            snVar.a(tzVar);
        }
    }
}
