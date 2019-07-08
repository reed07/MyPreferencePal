package com.facebook.ads.internal.t;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.adapters.p;
import com.facebook.ads.internal.adapters.q;
import com.facebook.ads.internal.b.g;
import com.facebook.ads.internal.m.d;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.view.ab;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.x.a.C0023a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class e {
    private static final String b = "e";
    private static WeakHashMap<View, WeakReference<e>> c = new WeakHashMap<>();
    private static com.facebook.ads.internal.h.b h;
    /* access modifiers changed from: private */
    public k A;
    /* access modifiers changed from: private */
    public boolean B;
    /* access modifiers changed from: private */
    public boolean C;
    /* access modifiers changed from: private */
    public boolean D;
    /* access modifiers changed from: private */
    public boolean E;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.c.c F;
    /* access modifiers changed from: private */
    public d G;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.adapters.p.a H;
    /* access modifiers changed from: private */
    public String I;
    private View J;
    @Nullable
    protected i a;
    /* access modifiers changed from: private */
    public final Context d;
    private final String e;
    private final String f;
    private final com.facebook.ads.internal.h.b g;
    /* access modifiers changed from: private */
    @Nullable
    public h i;
    private final c j;
    /* access modifiers changed from: private */
    public g k;
    private volatile boolean l;
    @Nullable
    private d m;
    private com.facebook.ads.internal.protocol.e n;
    /* access modifiers changed from: private */
    @Nullable
    public View o;
    /* access modifiers changed from: private */
    @Nullable
    public NativeAdLayout p;
    /* access modifiers changed from: private */
    @Nullable
    public f q;
    private final List<View> r;
    /* access modifiers changed from: private */
    public OnTouchListener s;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.x.a t;
    private C0023a u;
    /* access modifiers changed from: private */
    public WeakReference<C0023a> v;
    /* access modifiers changed from: private */
    public final w w;
    /* access modifiers changed from: private */
    @Nullable
    public p x;
    private a y;
    private ab z;

    private class a implements OnClickListener, OnLongClickListener, OnTouchListener {
        private a() {
        }

        /* access modifiers changed from: private */
        public Map a() {
            HashMap hashMap = new HashMap();
            hashMap.put("touch", k.a(e.this.w.e()));
            if (e.this.A != null) {
                hashMap.put("nti", String.valueOf(e.this.A.c()));
            }
            if (e.this.B) {
                hashMap.put("nhs", String.valueOf(e.this.B));
            }
            e.this.t.a((Map<String, String>) hashMap);
            return hashMap;
        }

        /* access modifiers changed from: private */
        public void a(Map<String, String> map) {
            if (e.this.a != null) {
                e.this.a.e(map);
            }
        }

        public void onClick(View view) {
            String str;
            String str2;
            if (!e.this.w.d()) {
                Log.e("FBAudienceNetworkLog", "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.");
            }
            int F = com.facebook.ads.internal.r.a.F(e.this.d);
            if (F >= 0 && e.this.w.c() < ((long) F)) {
                if (!e.this.w.b()) {
                    str = "FBAudienceNetworkLog";
                    str2 = "Ad cannot be clicked before it is viewed.";
                } else {
                    str = "FBAudienceNetworkLog";
                    str2 = "Clicks happened too fast.";
                }
                Log.e(str, str2);
            } else if (e.this.w.a(e.this.d)) {
                if (e.this.a != null) {
                    e.this.a.d(a());
                }
            } else if (com.facebook.ads.internal.r.a.e(e.this.d)) {
                if (e.this.a != null) {
                    e.this.a.c(a());
                }
                com.facebook.ads.internal.w.b.g.a(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Map a2 = a.this.a();
                        a2.put("is_two_step", "true");
                        a.this.a(a2);
                    }
                }, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (e.this.a != null) {
                            e.this.a.b(a.this.a());
                        }
                    }
                }, com.facebook.ads.internal.w.a.b.a());
            } else {
                a(a());
            }
        }

        public boolean onLongClick(View view) {
            if (e.this.o == null || e.this.F == null) {
                return false;
            }
            e.this.F.setBounds(0, 0, e.this.o.getWidth(), e.this.o.getHeight());
            e.this.F.a(!e.this.F.a());
            return true;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            e.this.w.a(motionEvent, e.this.o, view);
            return e.this.s != null && e.this.s.onTouch(view, motionEvent);
        }
    }

    private class b extends com.facebook.ads.internal.adapters.c {
        private b() {
        }

        public void a() {
            if (e.this.i != null) {
                e.this.i.d();
            }
        }

        public void b() {
        }
    }

    public interface c {
        boolean a(View view);
    }

    public e(Context context, i iVar, d dVar, c cVar) {
        this(context, null, cVar);
        this.a = iVar;
        this.m = dVar;
        this.l = true;
        this.J = new View(context);
    }

    public e(Context context, String str, c cVar) {
        this.f = UUID.randomUUID().toString();
        this.n = com.facebook.ads.internal.protocol.e.NATIVE_UNKNOWN;
        this.r = new ArrayList();
        this.w = new w();
        this.C = false;
        this.D = false;
        this.G = d.ALL;
        this.H = com.facebook.ads.internal.adapters.p.a.ALL;
        this.d = context;
        this.e = str;
        this.j = cVar;
        com.facebook.ads.internal.h.b bVar = h;
        if (bVar == null) {
            bVar = new com.facebook.ads.internal.h.b(context);
        }
        this.g = bVar;
        this.J = new View(context);
    }

    public e(e eVar) {
        this(eVar.d, null, eVar.j);
        this.m = eVar.m;
        this.a = eVar.a;
        this.l = true;
        this.J = new View(this.d);
    }

    /* access modifiers changed from: private */
    public boolean A() {
        i iVar = this.a;
        return iVar != null && iVar.B();
    }

    private void B() {
        if (!TextUtils.isEmpty(p())) {
            com.facebook.ads.internal.w.e.g.a(new com.facebook.ads.internal.w.e.g(), this.d, Uri.parse(p()), v());
        }
    }

    private void C() {
        for (View view : this.r) {
            view.setOnClickListener(null);
            view.setOnTouchListener(null);
            view.setOnLongClickListener(null);
        }
        this.r.clear();
    }

    /* access modifiers changed from: private */
    public void a(@Nullable final i iVar, final boolean z2) {
        if (iVar != null) {
            if (this.G.equals(d.ALL)) {
                if (iVar.l() != null) {
                    this.g.a(iVar.l().a(), iVar.l().c(), iVar.l().b());
                }
                if (!this.n.equals(com.facebook.ads.internal.protocol.e.NATIVE_BANNER)) {
                    if (iVar.m() != null) {
                        this.g.a(iVar.m().a(), iVar.m().c(), iVar.m().b());
                    }
                    if (iVar.x() != null) {
                        for (e eVar : iVar.x()) {
                            if (eVar.j() != null) {
                                this.g.a(eVar.j().a(), eVar.j().c(), eVar.j().b());
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(iVar.t())) {
                        this.g.a(iVar.t());
                    }
                }
            }
            this.g.a((com.facebook.ads.internal.h.a) new com.facebook.ads.internal.h.a() {
                public void a() {
                    e eVar = e.this;
                    eVar.a = iVar;
                    if (eVar.i != null) {
                        if (e.this.G.equals(d.ALL) && !e.this.A()) {
                            e.this.i.a();
                        }
                        if (z2) {
                            e.this.i.b();
                        }
                    }
                }

                public void b() {
                    if (e.this.a != null) {
                        e.this.a.c();
                        e.this.a = null;
                    }
                    if (e.this.i != null) {
                        e.this.i.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.CACHE_FAILURE_ERROR, "Failed to download a media."));
                    }
                }
            });
        }
    }

    public static void a(g gVar, ImageView imageView) {
        if (gVar != null && imageView != null) {
            new com.facebook.ads.internal.view.c.d(imageView).a(gVar.c(), gVar.b()).a(gVar.a());
        }
    }

    private void a(List<View> list, View view) {
        c cVar = this.j;
        if (cVar == null || !cVar.a(view)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    a(list, viewGroup.getChildAt(i2));
                }
            } else {
                list.add(view);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(android.view.View r10, com.facebook.ads.internal.t.f r11, java.util.List<android.view.View> r12) {
        /*
            r9 = this;
            if (r10 == 0) goto L_0x0264
            if (r12 == 0) goto L_0x025c
            int r0 = r12.size()
            if (r0 == 0) goto L_0x025c
            boolean r0 = r9.f()
            if (r0 != 0) goto L_0x0018
            java.lang.String r10 = b
            java.lang.String r11 = "Ad not loaded"
            android.util.Log.e(r10, r11)
            return
        L_0x0018:
            com.facebook.ads.NativeAdLayout r0 = r9.p
            if (r0 == 0) goto L_0x001f
            r0.clearAdReportingLayout()
        L_0x001f:
            if (r11 != 0) goto L_0x0060
            com.facebook.ads.internal.protocol.e r10 = r9.n
            com.facebook.ads.internal.protocol.e r11 = com.facebook.ads.internal.protocol.e.NATIVE_UNKNOWN
            if (r10 != r11) goto L_0x0042
            com.facebook.ads.internal.t.h r10 = r9.i
            if (r10 == 0) goto L_0x0037
            com.facebook.ads.internal.protocol.a r11 = new com.facebook.ads.internal.protocol.a
            com.facebook.ads.internal.protocol.AdErrorType r12 = com.facebook.ads.internal.protocol.AdErrorType.NO_MEDIAVIEW_IN_NATIVEAD
            java.lang.String r0 = "MediaView is missing."
            r11.<init>(r12, r0)
            r10.a(r11)
        L_0x0037:
            boolean r10 = com.facebook.ads.internal.settings.AdInternalSettings.isDebugBuild()
            if (r10 == 0) goto L_0x005f
            java.lang.String r10 = b
            java.lang.String r11 = "MediaView is missing."
            goto L_0x005c
        L_0x0042:
            com.facebook.ads.internal.t.h r10 = r9.i
            if (r10 == 0) goto L_0x0052
            com.facebook.ads.internal.protocol.a r11 = new com.facebook.ads.internal.protocol.a
            com.facebook.ads.internal.protocol.AdErrorType r12 = com.facebook.ads.internal.protocol.AdErrorType.NO_ICONVIEW_IN_NATIVEBANNERAD
            java.lang.String r0 = "AdIconView is missing."
            r11.<init>(r12, r0)
            r10.a(r11)
        L_0x0052:
            boolean r10 = com.facebook.ads.internal.settings.AdInternalSettings.isDebugBuild()
            if (r10 == 0) goto L_0x005f
            java.lang.String r10 = b
            java.lang.String r11 = "AdIconView is missing."
        L_0x005c:
            android.util.Log.e(r10, r11)
        L_0x005f:
            return
        L_0x0060:
            android.view.View r0 = r11.getAdContentsView()
            if (r0 != 0) goto L_0x0077
            com.facebook.ads.internal.t.h r10 = r9.i
            if (r10 == 0) goto L_0x0076
            com.facebook.ads.internal.protocol.a r11 = new com.facebook.ads.internal.protocol.a
            com.facebook.ads.internal.protocol.AdErrorType r12 = com.facebook.ads.internal.protocol.AdErrorType.UNSUPPORTED_AD_ASSET_NATIVEAD
            java.lang.String r0 = "ad media type is not supported."
            r11.<init>(r12, r0)
            r10.a(r11)
        L_0x0076:
            return
        L_0x0077:
            android.view.View r0 = r9.o
            if (r0 == 0) goto L_0x0085
            java.lang.String r0 = b
            java.lang.String r1 = "Native Ad was already registered with a View. Auto unregistering and proceeding."
            android.util.Log.w(r0, r1)
            r9.z()
        L_0x0085:
            java.util.WeakHashMap<android.view.View, java.lang.ref.WeakReference<com.facebook.ads.internal.t.e>> r0 = c
            boolean r0 = r0.containsKey(r10)
            if (r0 == 0) goto L_0x00b3
            java.util.WeakHashMap<android.view.View, java.lang.ref.WeakReference<com.facebook.ads.internal.t.e>> r0 = c
            java.lang.Object r0 = r0.get(r10)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L_0x00b3
            java.lang.String r0 = b
            java.lang.String r1 = "View already registered with a NativeAd. Auto unregistering and proceeding."
            android.util.Log.w(r0, r1)
            java.util.WeakHashMap<android.view.View, java.lang.ref.WeakReference<com.facebook.ads.internal.t.e>> r0 = c
            java.lang.Object r0 = r0.get(r10)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            java.lang.Object r0 = r0.get()
            com.facebook.ads.internal.t.e r0 = (com.facebook.ads.internal.t.e) r0
            r0.z()
        L_0x00b3:
            com.facebook.ads.internal.t.e$a r0 = new com.facebook.ads.internal.t.e$a
            r1 = 0
            r0.<init>()
            r9.y = r0
            r9.o = r10
            r9.q = r11
            boolean r0 = r10 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x00db
            com.facebook.ads.internal.view.ab r0 = new com.facebook.ads.internal.view.ab
            android.content.Context r2 = r10.getContext()
            com.facebook.ads.internal.t.e$3 r3 = new com.facebook.ads.internal.t.e$3
            r3.<init>()
            r0.<init>(r2, r3)
            r9.z = r0
            r0 = r10
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            com.facebook.ads.internal.view.ab r2 = r9.z
            r0.addView(r2)
        L_0x00db:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r12)
            android.view.View r12 = r9.J
            if (r12 == 0) goto L_0x00e7
            r0.add(r12)
        L_0x00e7:
            java.util.Iterator r12 = r0.iterator()
        L_0x00eb:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x0116
            java.lang.Object r2 = r12.next()
            android.view.View r2 = (android.view.View) r2
            java.util.List<android.view.View> r3 = r9.r
            r3.add(r2)
            com.facebook.ads.internal.t.e$a r3 = r9.y
            r2.setOnClickListener(r3)
            com.facebook.ads.internal.t.e$a r3 = r9.y
            r2.setOnTouchListener(r3)
            android.content.Context r3 = r2.getContext()
            boolean r3 = com.facebook.ads.internal.r.a.b(r3)
            if (r3 == 0) goto L_0x00eb
            com.facebook.ads.internal.t.e$a r3 = r9.y
            r2.setOnLongClickListener(r3)
            goto L_0x00eb
        L_0x0116:
            com.facebook.ads.internal.adapters.i r12 = r9.a
            r12.a(r10, r0)
            r12 = 1
            com.facebook.ads.internal.m.d r2 = r9.m
            if (r2 == 0) goto L_0x0126
            int r12 = r2.f()
        L_0x0124:
            r4 = r12
            goto L_0x013c
        L_0x0126:
            com.facebook.ads.internal.b.g r2 = r9.k
            if (r2 == 0) goto L_0x013b
            com.facebook.ads.internal.m.d r2 = r2.b()
            if (r2 == 0) goto L_0x013b
            com.facebook.ads.internal.b.g r12 = r9.k
            com.facebook.ads.internal.m.d r12 = r12.b()
            int r12 = r12.f()
            goto L_0x0124
        L_0x013b:
            r4 = 1
        L_0x013c:
            com.facebook.ads.internal.t.e$4 r12 = new com.facebook.ads.internal.t.e$4
            r12.<init>()
            r9.u = r12
            com.facebook.ads.internal.x.a r12 = new com.facebook.ads.internal.x.a
            if (r11 == 0) goto L_0x014c
            android.view.View r11 = r11.getAdContentsView()
            goto L_0x014e
        L_0x014c:
            android.view.View r11 = r9.o
        L_0x014e:
            r3 = r11
            com.facebook.ads.internal.m.d r11 = r9.m
            r8 = 0
            if (r11 == 0) goto L_0x015a
        L_0x0154:
            int r11 = r11.g()
            r5 = r11
            goto L_0x016c
        L_0x015a:
            com.facebook.ads.internal.b.g r11 = r9.k
            if (r11 == 0) goto L_0x016b
            com.facebook.ads.internal.m.d r11 = r11.b()
            if (r11 == 0) goto L_0x016b
            com.facebook.ads.internal.b.g r11 = r9.k
            com.facebook.ads.internal.m.d r11 = r11.b()
            goto L_0x0154
        L_0x016b:
            r5 = 0
        L_0x016c:
            r6 = 1
            com.facebook.ads.internal.x.a$a r7 = r9.u
            r2 = r12
            r2.<init>(r3, r4, r5, r6, r7)
            r9.t = r12
            com.facebook.ads.internal.x.a r11 = r9.t
            com.facebook.ads.internal.m.d r12 = r9.m
            if (r12 == 0) goto L_0x0180
        L_0x017b:
            int r8 = r12.h()
            goto L_0x019a
        L_0x0180:
            com.facebook.ads.internal.adapters.i r12 = r9.a
            if (r12 == 0) goto L_0x0189
            int r8 = r12.j()
            goto L_0x019a
        L_0x0189:
            com.facebook.ads.internal.b.g r12 = r9.k
            if (r12 == 0) goto L_0x019a
            com.facebook.ads.internal.m.d r12 = r12.b()
            if (r12 == 0) goto L_0x019a
            com.facebook.ads.internal.b.g r12 = r9.k
            com.facebook.ads.internal.m.d r12 = r12.b()
            goto L_0x017b
        L_0x019a:
            r11.a(r8)
            com.facebook.ads.internal.x.a r11 = r9.t
            com.facebook.ads.internal.m.d r12 = r9.m
            if (r12 == 0) goto L_0x01a8
        L_0x01a3:
            int r12 = r12.i()
            goto L_0x01c4
        L_0x01a8:
            com.facebook.ads.internal.adapters.i r12 = r9.a
            if (r12 == 0) goto L_0x01b1
            int r12 = r12.k()
            goto L_0x01c4
        L_0x01b1:
            com.facebook.ads.internal.b.g r12 = r9.k
            if (r12 == 0) goto L_0x01c2
            com.facebook.ads.internal.m.d r12 = r12.b()
            if (r12 == 0) goto L_0x01c2
            com.facebook.ads.internal.b.g r12 = r9.k
            com.facebook.ads.internal.m.d r12 = r12.b()
            goto L_0x01a3
        L_0x01c2:
            r12 = 1000(0x3e8, float:1.401E-42)
        L_0x01c4:
            r11.b(r12)
            com.facebook.ads.internal.adapters.p r11 = new com.facebook.ads.internal.adapters.p
            android.content.Context r12 = r9.d
            com.facebook.ads.internal.t.e$b r2 = new com.facebook.ads.internal.t.e$b
            r2.<init>()
            com.facebook.ads.internal.x.a r1 = r9.t
            com.facebook.ads.internal.adapters.i r3 = r9.a
            r11.<init>(r12, r2, r1, r3)
            r9.x = r11
            com.facebook.ads.internal.adapters.p r11 = r9.x
            r11.a(r0)
            java.util.WeakHashMap<android.view.View, java.lang.ref.WeakReference<com.facebook.ads.internal.t.e>> r11 = c
            java.lang.ref.WeakReference r12 = new java.lang.ref.WeakReference
            r12.<init>(r9)
            r11.put(r10, r12)
            android.content.Context r10 = r9.d
            boolean r10 = com.facebook.ads.internal.r.a.b(r10)
            if (r10 == 0) goto L_0x025b
            com.facebook.ads.internal.view.c.c r10 = new com.facebook.ads.internal.view.c.c
            r10.<init>()
            r9.F = r10
            com.facebook.ads.internal.view.c.c r10 = r9.F
            java.lang.String r11 = r9.e
            r10.a(r11)
            com.facebook.ads.internal.view.c.c r10 = r9.F
            android.content.Context r11 = r9.d
            java.lang.String r11 = r11.getPackageName()
            r10.b(r11)
            com.facebook.ads.internal.view.c.c r10 = r9.F
            com.facebook.ads.internal.x.a r11 = r9.t
            r10.a(r11)
            com.facebook.ads.internal.adapters.i r10 = r9.a
            int r10 = r10.z()
            if (r10 <= 0) goto L_0x0229
            com.facebook.ads.internal.view.c.c r10 = r9.F
            com.facebook.ads.internal.adapters.i r11 = r9.a
            int r11 = r11.z()
            com.facebook.ads.internal.adapters.i r12 = r9.a
            int r12 = r12.y()
            r10.a(r11, r12)
        L_0x0229:
            com.facebook.ads.internal.m.d r10 = r9.m
            if (r10 == 0) goto L_0x0237
            com.facebook.ads.internal.view.c.c r11 = r9.F
            long r0 = r10.a()
            r11.a(r0)
            goto L_0x0250
        L_0x0237:
            com.facebook.ads.internal.b.g r10 = r9.k
            if (r10 == 0) goto L_0x0250
            com.facebook.ads.internal.m.d r10 = r10.b()
            if (r10 == 0) goto L_0x0250
            com.facebook.ads.internal.view.c.c r10 = r9.F
            com.facebook.ads.internal.b.g r11 = r9.k
            com.facebook.ads.internal.m.d r11 = r11.b()
            long r11 = r11.a()
            r10.a(r11)
        L_0x0250:
            android.view.View r10 = r9.o
            android.view.ViewOverlay r10 = r10.getOverlay()
            com.facebook.ads.internal.view.c.c r11 = r9.F
            r10.add(r11)
        L_0x025b:
            return
        L_0x025c:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Invalid set of clickable views"
            r10.<init>(r11)
            throw r10
        L_0x0264:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Must provide a View"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.t.e.b(android.view.View, com.facebook.ads.internal.t.f, java.util.List):void");
    }

    static /* synthetic */ boolean o(e eVar) {
        return eVar.t() == l.ON;
    }

    @Nullable
    public i a() {
        return this.a;
    }

    @Nullable
    public String a(String str) {
        if (!f()) {
            return null;
        }
        return this.a.a(str);
    }

    public void a(OnTouchListener onTouchListener) {
        this.s = onTouchListener;
    }

    public void a(View view, f fVar) {
        ArrayList arrayList = new ArrayList();
        a((List<View>) arrayList, view);
        b(view, fVar, arrayList);
    }

    public void a(View view, f fVar, List<View> list) {
        b(view, fVar, list);
    }

    public void a(@Nullable NativeAdLayout nativeAdLayout) {
        this.p = nativeAdLayout;
    }

    public void a(q qVar) {
        i iVar = this.a;
        if (iVar != null) {
            iVar.a(qVar);
        }
    }

    public void a(com.facebook.ads.internal.protocol.e eVar) {
        this.n = eVar;
    }

    public void a(d dVar, String str) {
        if (!this.l) {
            this.l = true;
            this.G = dVar;
            if (dVar.equals(d.NONE)) {
                this.H = com.facebook.ads.internal.adapters.p.a.NONE;
            }
            String str2 = this.e;
            com.facebook.ads.internal.protocol.e eVar = this.n;
            com.facebook.ads.internal.b.a aVar = new com.facebook.ads.internal.b.a(str2, eVar, eVar == com.facebook.ads.internal.protocol.e.NATIVE_UNKNOWN ? AdPlacementType.NATIVE : AdPlacementType.NATIVE_BANNER, null, 1);
            aVar.a(dVar);
            aVar.a(this.I);
            this.k = new g(this.d, aVar);
            this.k.a((com.facebook.ads.internal.adapters.a) new com.facebook.ads.internal.adapters.a() {
                public void a() {
                    if (e.this.i != null) {
                        e.this.i.c();
                    }
                }

                public void a(AdAdapter adAdapter) {
                    if (e.this.k != null) {
                        e.this.k.e();
                    }
                }

                public void a(i iVar) {
                    e.this.a(iVar, true);
                    if (e.this.i != null && iVar.x() != null) {
                        AnonymousClass1 r0 = new q() {
                            public void a(i iVar) {
                            }

                            public void a(i iVar, com.facebook.ads.internal.protocol.a aVar) {
                            }

                            public void b(i iVar) {
                            }

                            public void c(i iVar) {
                                if (e.this.i != null) {
                                    e.this.i.c();
                                }
                            }
                        };
                        for (e a2 : iVar.x()) {
                            a2.a((q) r0);
                        }
                    }
                }

                public void a(com.facebook.ads.internal.protocol.a aVar) {
                    if (e.this.i != null) {
                        e.this.i.a(aVar);
                    }
                }

                public void b() {
                    throw new IllegalStateException("Native ads manager their own impressions.");
                }
            });
            this.k.b(str);
            return;
        }
        throw new IllegalStateException("loadAd cannot be called more than once");
    }

    public void a(h hVar) {
        this.i = hVar;
    }

    public void a(k kVar) {
        this.A = kVar;
    }

    public void a(C0023a aVar) {
        this.v = new WeakReference<>(aVar);
    }

    public void a(boolean z2) {
        this.B = z2;
    }

    public void a(boolean z2, boolean z3) {
        if (z2) {
            if (this.G.equals(d.NONE) && !A()) {
                h hVar = this.i;
                if (hVar != null) {
                    hVar.a();
                }
            }
            com.facebook.ads.internal.x.a aVar = this.t;
            if (aVar != null) {
                aVar.a();
                return;
            }
            return;
        }
        com.facebook.ads.internal.x.a aVar2 = this.t;
        if (aVar2 != null) {
            aVar2.c();
        }
        h hVar2 = this.i;
        if (hVar2 != null && z3) {
            hVar2.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.BROKEN_MEDIA_ERROR, "Failed to load Media."));
        }
    }

    public void b(String str) {
        this.I = str;
    }

    public void b(boolean z2) {
        this.E = z2;
    }

    public boolean b() {
        g gVar = this.k;
        return gVar == null || gVar.g();
    }

    public void c() {
        if (this.G.equals(d.NONE)) {
            this.H = com.facebook.ads.internal.adapters.p.a.MANUAL;
        }
        this.G = d.ALL;
        a(this.a, false);
    }

    public void c(boolean z2) {
        this.C = z2;
    }

    public void d() {
        g gVar = this.k;
        if (gVar != null) {
            gVar.a(true);
            this.k = null;
        }
    }

    public void d(boolean z2) {
        this.D = z2;
    }

    public String e() {
        return this.e;
    }

    public boolean f() {
        i iVar = this.a;
        return iVar != null && iVar.A();
    }

    public boolean g() {
        return f() && this.a.e();
    }

    public boolean h() {
        i iVar = this.a;
        return iVar != null && iVar.f();
    }

    @Nullable
    public g i() {
        if (!f()) {
            return null;
        }
        return this.a.l();
    }

    @Nullable
    public g j() {
        if (!f()) {
            return null;
        }
        return this.a.m();
    }

    @Nullable
    public j k() {
        if (!f()) {
            return null;
        }
        return this.a.n();
    }

    @Nullable
    public String l() {
        if (!f()) {
            return null;
        }
        return this.a.o();
    }

    @Nullable
    public i m() {
        if (!f()) {
            return null;
        }
        return this.a.p();
    }

    @Nullable
    public String n() {
        if (!f()) {
            return null;
        }
        return this.f;
    }

    @Nullable
    public g o() {
        if (!f()) {
            return null;
        }
        return this.a.q();
    }

    @Nullable
    public String p() {
        if (!f()) {
            return null;
        }
        return this.a.r();
    }

    @Nullable
    public String q() {
        if (!f()) {
            return null;
        }
        return this.a.s();
    }

    @Nullable
    public String r() {
        if (!f() || TextUtils.isEmpty(this.a.t())) {
            return null;
        }
        return this.g.c(this.a.t());
    }

    @Nullable
    public String s() {
        if (!f()) {
            return null;
        }
        return this.a.u();
    }

    @Nullable
    public l t() {
        return !f() ? l.DEFAULT : this.a.v();
    }

    @Nullable
    public List<e> u() {
        if (!f()) {
            return null;
        }
        return this.a.x();
    }

    @Nullable
    public String v() {
        if (!f()) {
            return null;
        }
        return this.a.getClientToken();
    }

    public void w() {
        this.J.performClick();
    }

    public k x() {
        return this.A;
    }

    public void y() {
        if (!com.facebook.ads.internal.f.a.a(this.d, false)) {
            B();
            return;
        }
        Context context = this.d;
        com.facebook.ads.internal.view.a.c a2 = com.facebook.ads.internal.view.a.d.a(context, com.facebook.ads.internal.s.d.a(context), v(), this.p);
        if (a2 == null) {
            B();
            return;
        }
        this.p.setAdReportingLayout(a2);
        a2.a();
    }

    public void z() {
        View view = this.o;
        if (view != null && this.q != null) {
            if (!c.containsKey(view) || ((WeakReference) c.get(this.o)).get() != this) {
                throw new IllegalStateException("View not registered with this NativeAd");
            }
            View view2 = this.o;
            if (view2 instanceof ViewGroup) {
                ab abVar = this.z;
                if (abVar != null) {
                    ((ViewGroup) view2).removeView(abVar);
                    this.z = null;
                }
            }
            i iVar = this.a;
            if (iVar != null) {
                iVar.c();
            }
            if (this.F != null && com.facebook.ads.internal.r.a.b(this.d)) {
                this.F.b();
                this.o.getOverlay().remove(this.F);
            }
            c.remove(this.o);
            C();
            this.o = null;
            this.q = null;
            com.facebook.ads.internal.x.a aVar = this.t;
            if (aVar != null) {
                aVar.c();
                this.t = null;
            }
            this.x = null;
        }
    }
}
