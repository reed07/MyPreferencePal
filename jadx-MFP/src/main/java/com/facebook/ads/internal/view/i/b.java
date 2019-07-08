package com.facebook.ads.internal.view.i;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.n;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.view.i.b.s;
import com.facebook.ads.internal.view.i.b.t;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.view.i.b.x;
import com.facebook.ads.internal.view.i.b.y;
import com.facebook.ads.internal.view.i.b.z;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class b extends c {
    public int a;
    /* access modifiers changed from: private */
    public final x b;
    /* access modifiers changed from: private */
    public final f<s> c;
    /* access modifiers changed from: private */
    public final f<i> d;
    /* access modifiers changed from: private */
    public final f<k> e;
    /* access modifiers changed from: private */
    public final f<o> f;
    /* access modifiers changed from: private */
    public final f<c> g;
    /* access modifiers changed from: private */
    public final f<q> h;
    /* access modifiers changed from: private */
    public final f<y> i;
    /* access modifiers changed from: private */
    public final f<z> j;
    /* access modifiers changed from: private */
    public final f<t> k;
    /* access modifiers changed from: private */
    public final n l;
    /* access modifiers changed from: private */
    public final a m;
    /* access modifiers changed from: private */
    public boolean n;

    public b(Context context, com.facebook.ads.internal.s.c cVar, a aVar, String str) {
        this(context, cVar, aVar, (List<com.facebook.ads.internal.d.b>) new ArrayList<com.facebook.ads.internal.d.b>(), str);
    }

    public b(Context context, com.facebook.ads.internal.s.c cVar, a aVar, String str, @Nullable Bundle bundle) {
        this(context, cVar, aVar, new ArrayList(), str, bundle, null);
    }

    public b(Context context, com.facebook.ads.internal.s.c cVar, a aVar, String str, @Nullable Map<String, String> map) {
        this(context, cVar, aVar, new ArrayList(), str, null, map);
    }

    public b(Context context, com.facebook.ads.internal.s.c cVar, a aVar, List<com.facebook.ads.internal.d.b> list, String str) {
        super(context, cVar, aVar, list, str);
        this.b = new x() {
            public void a(w wVar) {
                b.this.e();
            }
        };
        this.c = new f<s>() {
            public Class<s> a() {
                return s.class;
            }

            public void a(s sVar) {
                b.this.f();
            }
        };
        this.d = new f<i>() {
            public Class<i> a() {
                return i.class;
            }

            public void a(i iVar) {
                b.this.h();
                b.this.a(iVar.a(), false, ((double) iVar.a()) < 2000.0d);
            }
        };
        this.e = new f<k>() {
            public Class<k> a() {
                return k.class;
            }

            public void a(k kVar) {
                if (!b.this.n) {
                    b.this.n = true;
                } else {
                    b.this.i();
                }
            }
        };
        this.f = new f<o>() {
            public Class<o> a() {
                return o.class;
            }

            public void a(o oVar) {
                int a2 = oVar.a();
                if (b.this.a <= 0 || a2 != b.this.m.getDuration() || b.this.m.getDuration() <= b.this.a) {
                    b.this.a(a2);
                }
            }
        };
        this.g = new f<c>() {
            public Class<c> a() {
                return c.class;
            }

            public void a(c cVar) {
                b bVar;
                int a2 = cVar.a();
                int b = cVar.b();
                if (b.this.a <= 0 || a2 != b || b <= b.this.a) {
                    if (b >= a2 + 500) {
                        bVar = b.this;
                    } else if (b == 0) {
                        bVar = b.this;
                        a2 = bVar.a;
                    } else {
                        b.this.b(b);
                    }
                    bVar.b(a2);
                }
            }
        };
        this.h = new f<q>() {
            public Class<q> a() {
                return q.class;
            }

            public void a(q qVar) {
                b.this.a(qVar.a(), qVar.b());
            }
        };
        this.i = new f<y>() {
            public Class<y> a() {
                return y.class;
            }

            public void a(y yVar) {
                b.this.b();
            }
        };
        this.j = new f<z>() {
            public Class<z> a() {
                return z.class;
            }

            public void a(z zVar) {
                b.this.c();
            }
        };
        this.k = new f<t>() {
            public Class<t> a() {
                return t.class;
            }

            public void a(t tVar) {
                b bVar = b.this;
                bVar.a(bVar.j(), b.this.j());
            }
        };
        this.l = new n() {
            public void a(m mVar) {
                b bVar = b.this;
                bVar.a = bVar.m.getDuration();
            }
        };
        this.n = false;
        this.m = aVar;
        this.m.getEventBus().a((T[]) new f[]{this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.l, this.k});
    }

    public b(Context context, com.facebook.ads.internal.s.c cVar, a aVar, List<com.facebook.ads.internal.d.b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        super(context, cVar, aVar, list, str, bundle, map);
        this.b = new x() {
            public void a(w wVar) {
                b.this.e();
            }
        };
        this.c = new f<s>() {
            public Class<s> a() {
                return s.class;
            }

            public void a(s sVar) {
                b.this.f();
            }
        };
        this.d = new f<i>() {
            public Class<i> a() {
                return i.class;
            }

            public void a(i iVar) {
                b.this.h();
                b.this.a(iVar.a(), false, ((double) iVar.a()) < 2000.0d);
            }
        };
        this.e = new f<k>() {
            public Class<k> a() {
                return k.class;
            }

            public void a(k kVar) {
                if (!b.this.n) {
                    b.this.n = true;
                } else {
                    b.this.i();
                }
            }
        };
        this.f = new f<o>() {
            public Class<o> a() {
                return o.class;
            }

            public void a(o oVar) {
                int a2 = oVar.a();
                if (b.this.a <= 0 || a2 != b.this.m.getDuration() || b.this.m.getDuration() <= b.this.a) {
                    b.this.a(a2);
                }
            }
        };
        this.g = new f<c>() {
            public Class<c> a() {
                return c.class;
            }

            public void a(c cVar) {
                b bVar;
                int a2 = cVar.a();
                int b = cVar.b();
                if (b.this.a <= 0 || a2 != b || b <= b.this.a) {
                    if (b >= a2 + 500) {
                        bVar = b.this;
                    } else if (b == 0) {
                        bVar = b.this;
                        a2 = bVar.a;
                    } else {
                        b.this.b(b);
                    }
                    bVar.b(a2);
                }
            }
        };
        this.h = new f<q>() {
            public Class<q> a() {
                return q.class;
            }

            public void a(q qVar) {
                b.this.a(qVar.a(), qVar.b());
            }
        };
        this.i = new f<y>() {
            public Class<y> a() {
                return y.class;
            }

            public void a(y yVar) {
                b.this.b();
            }
        };
        this.j = new f<z>() {
            public Class<z> a() {
                return z.class;
            }

            public void a(z zVar) {
                b.this.c();
            }
        };
        this.k = new f<t>() {
            public Class<t> a() {
                return t.class;
            }

            public void a(t tVar) {
                b bVar = b.this;
                bVar.a(bVar.j(), b.this.j());
            }
        };
        this.l = new n() {
            public void a(m mVar) {
                b bVar = b.this;
                bVar.a = bVar.m.getDuration();
            }
        };
        this.n = false;
        this.m = aVar;
        this.m.getEventBus().a((T[]) new f[]{this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.k});
    }

    public void a() {
        this.m.getStateHandler().post(new Runnable() {
            public void run() {
                b.this.m.getEventBus().b((T[]) new f[]{b.this.b, b.this.f, b.this.c, b.this.e, b.this.d, b.this.g, b.this.h, b.this.i, b.this.j, b.this.l, b.this.k});
            }
        });
    }
}
