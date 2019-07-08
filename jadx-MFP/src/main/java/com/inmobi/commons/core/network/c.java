package com.inmobi.commons.core.network;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.configs.b;
import com.inmobi.commons.core.configs.g;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.b.f;
import com.inmobi.commons.core.utilities.uid.d;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONObject;

/* compiled from: NetworkRequest */
public class c {
    private static final String a = "c";
    public boolean A;
    private d b;
    private boolean c;
    private byte[] d;
    private byte[] e;
    private boolean f;
    protected Map<String, String> m;
    protected Map<String, String> n;
    public Map<String, String> o;
    String p;
    public String q;
    public int r;
    public int s;
    public boolean t;
    public boolean u;
    public long v;
    boolean w;
    public int x;
    public boolean y;
    public g z;

    public c(String str, String str2, boolean z2, d dVar) {
        this(str, str2, z2, dVar, false, 0);
    }

    public c(String str, String str2) {
        this(str, str2, false, null, false, 0);
        this.f = false;
    }

    public c(String str, String str2, d dVar, int i) {
        this(str, str2, true, dVar, false, i);
    }

    public c(String str, String str2, boolean z2, d dVar, boolean z3, int i) {
        this.m = new HashMap();
        this.r = 60000;
        this.s = 60000;
        this.t = true;
        this.u = true;
        this.v = -1;
        this.x = 0;
        this.f = true;
        this.y = false;
        this.A = true;
        this.p = str;
        this.q = str2;
        this.c = z2;
        this.b = dVar;
        this.m.put("User-Agent", a.f());
        this.w = z3;
        this.x = i;
        if (HttpConstants.METHOD_GET.equals(str)) {
            this.n = new HashMap();
        } else if (HttpConstants.METHOD_POST.equals(str)) {
            this.o = new HashMap();
        }
        this.z = new g();
        b.a().a((com.inmobi.commons.core.configs.a) this.z, (com.inmobi.commons.core.configs.b.c) null);
    }

    public final void a(Map<String, String> map) {
        if (map != null) {
            this.m.putAll(map);
        }
    }

    public final void b(Map<String, String> map) {
        if (map != null) {
            this.n.putAll(map);
        }
    }

    public final void c(Map<String, String> map) {
        this.o.putAll(map);
    }

    public final Map<String, String> d() {
        com.inmobi.commons.core.utilities.d.a(this.m);
        return this.m;
    }

    public final String e() {
        String str = this.q;
        if (this.n == null) {
            return str;
        }
        String c2 = c();
        if (c2 == null || c2.trim().length() == 0) {
            return str;
        }
        if (!str.contains("?")) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("?");
            str = sb.toString();
        }
        if (!str.endsWith("&") && !str.endsWith("?")) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("&");
            str = sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(c2);
        return sb3.toString();
    }

    @CallSuper
    public void a() {
        e.c();
        boolean z2 = true;
        if (this.x != 1) {
            z2 = false;
        }
        this.x = e.a(z2);
        if (this.u) {
            if (HttpConstants.METHOD_GET.equals(this.p)) {
                d(this.n);
            } else if (HttpConstants.METHOD_POST.equals(this.p)) {
                d(this.o);
            }
        }
        if (this.f) {
            JSONObject a2 = e.a();
            if (a2 != null) {
                if (HttpConstants.METHOD_GET.equals(this.p)) {
                    this.n.put("consentObject", a2.toString());
                } else if (HttpConstants.METHOD_POST.equals(this.p)) {
                    this.o.put("consentObject", a2.toString());
                }
            }
        }
        if (this.A) {
            if (HttpConstants.METHOD_GET.equals(this.p)) {
                this.n.put("u-appsecure", Integer.toString(com.inmobi.commons.core.utilities.b.a.a().c));
            } else if (HttpConstants.METHOD_POST.equals(this.p)) {
                this.o.put("u-appsecure", Integer.toString(com.inmobi.commons.core.utilities.b.a.a().c));
            }
        }
    }

    private String c() {
        com.inmobi.commons.core.utilities.d.a(this.n);
        return com.inmobi.commons.core.utilities.d.a(this.n, "&");
    }

    public final String f() {
        com.inmobi.commons.core.utilities.d.a(this.o);
        String a2 = com.inmobi.commons.core.utilities.d.a(this.o, "&");
        new StringBuilder("Post body url: ").append(this.q);
        if (!b()) {
            return a2;
        }
        this.d = com.inmobi.commons.core.utilities.a.b.a(16);
        this.e = com.inmobi.commons.core.utilities.a.b.a();
        byte[] bArr = this.d;
        byte[] bArr2 = this.e;
        g gVar = this.z;
        byte[] a3 = com.inmobi.commons.core.utilities.a.b.a(8);
        HashMap hashMap = new HashMap();
        hashMap.put("sm", com.inmobi.commons.core.utilities.a.b.a(a2, bArr2, bArr, a3, gVar.b, gVar.a));
        hashMap.put("sn", gVar.c);
        return com.inmobi.commons.core.utilities.d.a((Map<String, String>) hashMap, "&");
    }

    public boolean b() {
        return this.c;
    }

    private void d(Map<String, String> map) {
        map.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
        map.putAll(com.inmobi.commons.core.utilities.b.b.a(this.y));
        map.putAll(f.a());
        if (this.b != null) {
            if (b()) {
                d dVar = this.b;
                HashMap hashMap = new HashMap();
                hashMap.put("u-id-map", new JSONObject(dVar.a((String) null, false)).toString());
                map.putAll(hashMap);
                return;
            }
            d dVar2 = this.b;
            String num = Integer.toString(new Random().nextInt());
            String a2 = com.inmobi.commons.core.utilities.a.c.a(new JSONObject(dVar2.a(num, true)).toString());
            HashMap hashMap2 = new HashMap();
            hashMap2.put("u-id-map", a2);
            hashMap2.put("u-id-key", num);
            com.inmobi.commons.core.utilities.uid.c.a();
            hashMap2.put("u-key-ver", com.inmobi.commons.core.utilities.uid.c.d());
            map.putAll(hashMap2);
        }
    }

    @Nullable
    public final byte[] a(byte[] bArr) {
        try {
            return com.inmobi.commons.core.utilities.a.b.a(Base64.decode(bArr, 0), this.e, this.d);
        } catch (IllegalArgumentException e2) {
            new StringBuilder("Msg : ").append(e2.getMessage());
            return null;
        }
    }

    public final long g() {
        try {
            if (HttpConstants.METHOD_GET.equals(this.p)) {
                return 0 + ((long) c().length());
            }
            if (HttpConstants.METHOD_POST.equals(this.p)) {
                return ((long) f().length()) + 0;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }
}
