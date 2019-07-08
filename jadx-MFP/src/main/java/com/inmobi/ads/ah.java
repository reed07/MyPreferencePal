package com.inmobi.ads;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.mopub.common.MoPubBrowser;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeAdContainer */
public class ah implements ActivityLifecycleCallbacks, AdContainer {
    /* access modifiers changed from: private */
    public static final String B = "ah";
    com.inmobi.ads.ai.a A = new com.inmobi.ads.ai.a() {
        public final void a(View view, boolean z) {
            ah.this.a(z);
        }
    };
    @NonNull
    private Set<Integer> C = new HashSet();
    @NonNull
    private List<ak> D = new ArrayList();
    private au E;
    private int F = -1;
    private o G;
    /* access modifiers changed from: private */
    public ah H;
    private ak I = null;
    private String J = null;
    /* access modifiers changed from: private */
    public ah K;
    private com.inmobi.rendering.RenderView.a L;
    private final com.inmobi.ads.AdContainer.a M = new com.inmobi.ads.AdContainer.a() {
        public final void a() {
            ah.B;
            c e = ah.this.e();
            if (e != null) {
                e.a();
            }
        }

        public final void a(Object obj) {
            if (ah.this.l() != null) {
                c e = ah.this.e();
                if (e != null) {
                    e.b();
                }
            }
        }

        public final void b(Object obj) {
            c e = ah.this.e();
            if (e != null) {
                e.f();
            }
        }
    };
    private ExecutorService N;
    private Runnable O = new Runnable() {
        public final void run() {
            if (!ah.this.l && PlacementType.PLACEMENT_TYPE_INLINE == ah.this.b.a && ah.this.a.c) {
                ah.B;
                ah.a(ah.this);
            }
        }
    };
    protected ao a;
    @NonNull
    public RenderingProperties b;
    @NonNull
    c c;
    @NonNull
    final String d;
    /* access modifiers changed from: 0000 */
    @NonNull
    public final long e;
    /* access modifiers changed from: 0000 */
    public final String f;
    /* access modifiers changed from: 0000 */
    @NonNull
    public final boolean g;
    @NonNull
    final String h;
    @Nullable
    protected Set<bq> i;
    protected ca j;
    protected boolean k;
    public boolean l = false;
    protected boolean m;
    @NonNull
    ah n;
    @Nullable
    protected c o;
    @NonNull
    protected WeakReference<Context> p = new WeakReference<>(null);
    @Nullable
    WeakReference<Activity> q;
    boolean r = false;
    int s = 0;
    boolean t = true;
    boolean u = false;
    Intent v = null;
    RenderView w;
    RenderView x;
    int y;
    @Nullable
    public List<RenderView> z;

    /* compiled from: NativeAdContainer */
    final class a extends Thread {
        private WeakReference<ah> b;

        a(ah ahVar) {
            this.b = new WeakReference<>(ahVar);
        }

        public final void run() {
            if (ah.this.l() == null) {
                ah.B;
                return;
            }
            ah ahVar = (ah) this.b.get();
            if (ahVar != null && !ahVar.l) {
                try {
                    ao h = ahVar.h();
                    if (ah.this.l() != null) {
                        if (h.e.length() != 0) {
                            ah.B;
                            JSONObject a2 = h.a();
                            if (a2 != null) {
                                ao aoVar = new ao(ah.this.b.a, a2, h, ah.this.b.a == PlacementType.PLACEMENT_TYPE_INLINE, ah.this.c, null);
                                if (aoVar.c()) {
                                    ah a3 = b.a(ah.this.l(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), aoVar, ah.this.d, ah.this.h, null, ah.this.c, ah.this.e, ah.this.g, ah.this.f);
                                    ah.B;
                                    a3.a((AdContainer) ahVar);
                                    a3.w = ahVar.w;
                                    ahVar.K = a3;
                                    return;
                                }
                                ah.B;
                                return;
                            }
                            return;
                        }
                    }
                    ah.B;
                } catch (Exception e) {
                    ah.B;
                    new StringBuilder("Encountered unexpected error in EndCardBuilder: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        }
    }

    /* compiled from: NativeAdContainer */
    static final class b {
        static ah a(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull c cVar, long j, boolean z, String str3) {
            if (new ArrayList(aoVar.h.keySet()).contains(ShareConstants.VIDEO_URL)) {
                bd bdVar = new bd(context, renderingProperties, aoVar, str, str2, set, cVar, j, z, str3);
                return bdVar;
            }
            ah ahVar = new ah(context, renderingProperties, aoVar, str, str2, set, cVar, j, z, str3);
            return ahVar;
        }
    }

    /* compiled from: NativeAdContainer */
    public interface c {
        void a();

        void a(String str, Map<String, Object> map);

        void a(Map<String, String> map);

        void a(boolean z);

        void b();

        void c();

        void d();

        void e();

        void f();

        void g();

        void h();

        void i();
    }

    public final void a() {
    }

    public String getMarkupType() {
        return "inmobiJson";
    }

    @Nullable
    public View getVideoContainerView() {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public boolean n() {
        return false;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    ah(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull c cVar, long j2, boolean z2, String str3) {
        this.b = renderingProperties;
        this.a = aoVar;
        this.d = str;
        this.e = j2;
        this.g = z2;
        this.f = str3;
        this.h = str2;
        a((AdContainer) this);
        this.k = false;
        this.l = false;
        this.c = cVar;
        this.G = new o();
        if (set != null) {
            this.i = new HashSet(set);
        }
        this.a.d.z = System.currentTimeMillis();
        a(context);
        this.y = -1;
        this.N = Executors.newSingleThreadExecutor();
        this.N.submit(this.O);
    }

    public void setRequestedScreenOrientation() {
        Activity l2 = l();
        if (l2 != null && !this.l) {
            switch (this.a.a) {
                case 1:
                    l2.setRequestedOrientation(1);
                    return;
                case 2:
                    l2.setRequestedOrientation(0);
                    return;
                default:
                    l2.setRequestedOrientation(l2.getRequestedOrientation());
                    break;
            }
        }
    }

    public RenderingProperties getRenderingProperties() {
        return this.b;
    }

    @Nullable
    public com.inmobi.ads.AdContainer.a getFullScreenEventsListener() {
        return this.M;
    }

    /* access modifiers changed from: 0000 */
    public final void a(Context context) {
        B();
        this.p = new WeakReference<>(context);
        com.inmobi.commons.a.a.a(context, (ActivityLifecycleCallbacks) this);
    }

    @Nullable
    public final Context d() {
        return (Context) this.p.get();
    }

    public final void a(@NonNull AdContainer adContainer) {
        if (adContainer instanceof ah) {
            this.n = (ah) adContainer;
        }
    }

    @Nullable
    public final c e() {
        return this.o;
    }

    public final void a(@NonNull c cVar) {
        this.o = cVar;
    }

    @Nullable
    public final View f() {
        ca caVar = this.j;
        if (caVar == null) {
            return null;
        }
        return caVar.b();
    }

    /* access modifiers changed from: 0000 */
    public final void g() {
        Map a2 = a((ak) this.a.d);
        a(1, a2);
        a(2, a2);
    }

    @SuppressLint({"SwitchIntDef"})
    public ca getViewableAd() {
        Context j2 = j();
        if (this.j == null && j2 != null) {
            g();
            this.j = new aa(j2, this, new cc(this, this.w));
            Set<bq> set = this.i;
            if (set != null) {
                if (j2 instanceof Activity) {
                    try {
                        Activity activity = (Activity) j2;
                        for (bq bqVar : set) {
                            int i2 = bqVar.a;
                            if (i2 != 1) {
                                if (i2 != 3) {
                                    if (i2 == 6) {
                                        if (((List) bqVar.b.get("trackerUrls")) != null) {
                                            this.j = new com.inmobi.ads.e.a.a(this.j);
                                        }
                                    }
                                } else if (this.y == 0) {
                                    AbstractAvidAdSession abstractAvidAdSession = (AbstractAvidAdSession) bqVar.b.get("avidAdSession");
                                    boolean z2 = bqVar.b.containsKey("deferred") && ((Boolean) bqVar.b.get("deferred")).booleanValue();
                                    if (abstractAvidAdSession != null) {
                                        v vVar = new v(this, activity, this.j, abstractAvidAdSession, z2);
                                        this.j = vVar;
                                    }
                                }
                            } else if (this.y == 0) {
                                this.j = new ad(this, activity, this.j, bqVar.b);
                            } else {
                                bqVar.b.put("zMoatIID", UUID.randomUUID().toString());
                                this.j = new ae(activity, this.j, bqVar.b);
                            }
                        }
                    } catch (Exception e2) {
                        new StringBuilder("Exception occurred while creating the Display viewable ad : ").append(e2.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                    }
                } else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    hashMap.put("impId", this.d);
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "TrackersForService", hashMap);
                }
            }
        }
        return this.j;
    }

    @NonNull
    public final ao h() {
        return this.a;
    }

    public final boolean c() {
        return this.l;
    }

    public void destroy() {
        if (!this.l) {
            this.l = true;
            this.F = -1;
            ah ahVar = this.H;
            if (ahVar != null) {
                ahVar.b();
            }
            this.l = true;
            this.o = null;
            au A2 = A();
            if (A2 != null) {
                n nVar = A2.c;
                for (a aVar : nVar.a) {
                    aVar.a.cancel();
                }
                nVar.a.clear();
                A2.b();
            }
            this.D.clear();
            ca caVar = this.j;
            if (caVar != null) {
                caVar.d();
                this.j.e();
            }
            B();
            this.p.clear();
            WeakReference<Activity> weakReference = this.q;
            if (weakReference != null) {
                weakReference.clear();
            }
            List<RenderView> list = this.z;
            if (list != null) {
                list.clear();
            }
            this.a = null;
            this.w = null;
            ah ahVar2 = this.K;
            if (ahVar2 != null) {
                ahVar2.destroy();
                this.K = null;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean i() {
        return PlacementType.PLACEMENT_TYPE_INLINE == this.b.a && l() != null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Context j() {
        return (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a || i()) ? l() : (Context) this.p.get();
    }

    /* access modifiers changed from: protected */
    public final boolean k() {
        return this.k;
    }

    private ak a(@NonNull ak akVar, @NonNull ao aoVar, @NonNull String str) {
        if (com.inmobi.commons.core.utilities.b.a((Context) this.p.get(), str)) {
            return akVar;
        }
        String[] split = str.split("\\|");
        ak b2 = aoVar.b(split[0]);
        if (b2 == null) {
            return b(aoVar.f, akVar);
        }
        if (b2.equals(akVar)) {
            return null;
        }
        if (1 == split.length || 2 == split.length) {
            b2.m = 1;
            return b2;
        }
        if (split.length > 2) {
            b2.m = ao.a(split[2]);
        }
        return b2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0083 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0084 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.String r7) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r7 = r7.toLowerCase(r0)
            java.lang.String r7 = r7.trim()
            int r0 = r7.hashCode()
            r1 = -934641255(0xffffffffc84a8199, float:-207366.39)
            r2 = 5
            r3 = 4
            r4 = 1
            r5 = 3
            r6 = 2
            if (r0 == r1) goto L_0x0070
            r1 = -934524953(0xffffffffc84c47e7, float:-209183.61)
            if (r0 == r1) goto L_0x0066
            if (r0 == 0) goto L_0x005c
            r1 = 3127582(0x2fb91e, float:4.382676E-39)
            if (r0 == r1) goto L_0x0052
            r1 = 3443508(0x348b34, float:4.825382E-39)
            if (r0 == r1) goto L_0x0048
            r1 = 3532159(0x35e57f, float:4.949609E-39)
            if (r0 == r1) goto L_0x003e
            r1 = 110066619(0x68f7bbb, float:5.3972427E-35)
            if (r0 == r1) goto L_0x0034
            goto L_0x007a
        L_0x0034:
            java.lang.String r0 = "fullscreen"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x007a
            r7 = 6
            goto L_0x007b
        L_0x003e:
            java.lang.String r0 = "skip"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x007a
            r7 = 2
            goto L_0x007b
        L_0x0048:
            java.lang.String r0 = "play"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x007a
            r7 = 7
            goto L_0x007b
        L_0x0052:
            java.lang.String r0 = "exit"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x007a
            r7 = 5
            goto L_0x007b
        L_0x005c:
            java.lang.String r0 = ""
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x007a
            r7 = 1
            goto L_0x007b
        L_0x0066:
            java.lang.String r0 = "replay"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x007a
            r7 = 4
            goto L_0x007b
        L_0x0070:
            java.lang.String r0 = "reload"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x007a
            r7 = 3
            goto L_0x007b
        L_0x007a:
            r7 = -1
        L_0x007b:
            switch(r7) {
                case 2: goto L_0x0084;
                case 3: goto L_0x0083;
                case 4: goto L_0x0083;
                case 5: goto L_0x0082;
                case 6: goto L_0x0081;
                case 7: goto L_0x0080;
                default: goto L_0x007e;
            }
        L_0x007e:
            r7 = 0
            return r7
        L_0x0080:
            return r2
        L_0x0081:
            return r3
        L_0x0082:
            return r4
        L_0x0083:
            return r5
        L_0x0084:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ah.a(java.lang.String):int");
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull ak akVar, boolean z2) {
        if (this.a.j && !this.l) {
            ak b2 = b(this.a, akVar);
            if (b2 != null) {
                Map a2 = a(b2);
                b2.i = akVar.i;
                if (ShareConstants.VIDEO_URL.equals(b2.b) || b2.h) {
                    ca caVar = this.j;
                    if (caVar != null) {
                        caVar.a(4);
                    }
                    int i2 = b2.i;
                    if (i2 != 0) {
                        String str = b2.r;
                        if (!this.t || 4 != i2) {
                            if (2 == b2.m) {
                                bt f2 = ((be) b2).b().f();
                                if (!(f2 == null || f2.e == null || f2.e.trim().isEmpty())) {
                                    str = f2.e;
                                }
                            }
                            if (!com.inmobi.commons.core.utilities.b.a(C(), str)) {
                                StringBuilder sb = new StringBuilder("Invalid url:");
                                sb.append(str);
                                sb.append(" will use fallback");
                                a("DeeplinkFailed", str);
                                str = b2.s;
                                if (!com.inmobi.commons.core.utilities.b.a(C(), str)) {
                                    a("DeeplinkFallbackFailed", str);
                                    return;
                                }
                            }
                            String a3 = d.a(str, a2);
                            if (!this.u || z2) {
                                a(b2, i2, a3);
                            } else {
                                ah f3 = f(this);
                                if (f3 != null) {
                                    c cVar = f3.o;
                                    if (cVar != null) {
                                        if (1 != i2 || !com.inmobi.commons.core.utilities.b.a(a3)) {
                                            cVar.g();
                                        } else {
                                            cVar.c();
                                        }
                                    }
                                    this.I = b2;
                                    this.J = a3;
                                    return;
                                }
                            }
                        }
                    }
                }
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("url", akVar.r);
            a("DeeplinkFailed", (Map<String, Object>) hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("url", akVar.s);
            a("DeeplinkFallbackFailed", (Map<String, Object>) hashMap2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2, ak akVar) {
        if (!this.C.contains(Integer.valueOf(i2)) && !this.l) {
            w();
            a(i2, (am) akVar);
        }
    }

    public void setFullScreenActivityContext(Activity activity) {
        this.q = new WeakReference<>(activity);
    }

    @Nullable
    public final Activity l() {
        WeakReference<Activity> weakReference = this.q;
        if (weakReference == null) {
            return null;
        }
        return (Activity) weakReference.get();
    }

    private void w() {
        am a2 = this.a.a(0);
        if (!this.C.contains(Integer.valueOf(0)) && a2 != null) {
            a(0, a2);
        }
    }

    private void a(@NonNull ak akVar, @Nullable Map<String, String> map) {
        a("ReportClick", (Map<String, Object>) new HashMap<String,Object>());
        if (2 == akVar.m) {
            bt f2 = ((be) akVar).b().f();
            if (f2 == null || (f2.e == null && akVar.r != null)) {
                akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK, map);
            } else if (f2.d.size() > 0) {
                for (NativeTracker a2 : f2.a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK)) {
                    ak.a(a2, map);
                }
                return;
            }
            return;
        }
        akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK, map);
    }

    /* access modifiers changed from: 0000 */
    public final Map<String, String> a(@NonNull ak akVar) {
        HashMap hashMap = new HashMap(3);
        if (!this.l) {
            ao aoVar = this.a;
            if (aoVar != null) {
                hashMap.put("$LTS", String.valueOf(aoVar.d.z));
                am a2 = ao.a(akVar);
                long currentTimeMillis = System.currentTimeMillis();
                if (!(a2 == null || 0 == a2.z)) {
                    currentTimeMillis = a2.z;
                }
                hashMap.put("$STS", String.valueOf(currentTimeMillis));
                hashMap.put("$TS", String.valueOf(System.currentTimeMillis()));
                return hashMap;
            }
        }
        return hashMap;
    }

    private void b(@Nullable ak akVar, @Nullable Map<String, String> map) {
        if (akVar != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", akVar.g);
                jSONObject.put("asset", akVar.f);
            } catch (JSONException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
            HashMap hashMap = new HashMap();
            hashMap.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            hashMap.put("impId", this.d);
            hashMap.put("pageJson", jSONObject);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "PageRendered", hashMap);
            akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW, map);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(boolean z2) {
        if (z2) {
            x();
        } else {
            y();
        }
    }

    static NativeTimerView b(View view) {
        if (view != null) {
            return (NativeTimerView) view.findViewWithTag("timerView");
        }
        return null;
    }

    protected static void c(View view) {
        NativeTimerView b2 = b(view);
        if (b2 != null && b2.c != null && b2.c.isRunning()) {
            b2.b = b2.c.getCurrentPlayTime();
            b2.c.cancel();
        }
    }

    protected static void d(View view) {
        NativeTimerView b2 = b(view);
        if (b2 != null && b2.c != null && !b2.c.isRunning()) {
            b2.c.setCurrentPlayTime(b2.b);
            b2.c.start();
        }
    }

    private void x() {
        au A2 = A();
        if (A2 != null) {
            n nVar = A2.c;
            if (!nVar.b) {
                nVar.b = true;
                nVar.a(nVar.a);
            }
        }
    }

    private void y() {
        au A2 = A();
        if (A2 != null) {
            n nVar = A2.c;
            if (nVar.b) {
                nVar.b = false;
                for (a aVar : nVar.a) {
                    ValueAnimator valueAnimator = (ValueAnimator) aVar.a;
                    aVar.b = valueAnimator.getCurrentPlayTime();
                    if (((double) valueAnimator.getAnimatedFraction()) == 1.0d) {
                        aVar.c = true;
                    }
                    valueAnimator.cancel();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void m() {
        ah f2 = f(this);
        if (f2 != null) {
            c cVar = f2.o;
            if (cVar != null) {
                cVar.c();
            }
            this.N.submit(new Runnable() {
                public final void run() {
                    if (ah.this.H == null) {
                        ah.a(ah.this);
                    }
                    int a2 = InMobiAdActivity.a((AdContainer) ah.this.H);
                    Intent intent = new Intent((Context) ah.this.p.get(), InMobiAdActivity.class);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", a2);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", RequestCodes.EDIT_RECIPE_INGREDIENT);
                    if (ah.this.u) {
                        ah.this.v = intent;
                    } else {
                        com.inmobi.commons.a.a.a((Context) ah.this.p.get(), intent);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public final void o() {
        if (n()) {
            this.r = true;
            c cVar = this.o;
            if (cVar != null && this.a.g != null) {
                cVar.a(this.a.g);
            }
        }
    }

    private void a(be beVar, ah ahVar) {
        bt f2 = beVar.b().f();
        if (f2 != null && f2.g) {
            for (NativeTracker a2 : f2.a(TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE)) {
                be.a(a2, a((ak) beVar));
            }
            f2.g = false;
            ahVar.a("EndCardClosed", ahVar.z());
        }
    }

    private Map<String, Object> z() {
        List c2 = this.K.a.c("WEBVIEW");
        bf bfVar = c2.size() > 0 ? (bf) c2.get(0) : null;
        String str = bfVar == null ? "Static" : "Rich";
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put("dataType", bfVar == null ? MoPubBrowser.DESTINATION_URL_KEY : bfVar.z);
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, Map<String, Object> map) {
        ah f2 = f(this);
        if (f2 != null) {
            c cVar = f2.o;
            if (cVar != null) {
                cVar.a(str, map);
                return;
            }
            StringBuilder sb = new StringBuilder("InteractionCallback is null. Discarding telemetry event : [");
            sb.append(str);
            sb.append(" ]");
            return;
        }
        StringBuilder sb2 = new StringBuilder("Target container is null. Discarding telemetry event : [");
        sb2.append(str);
        sb2.append(" ]");
    }

    private static ah f(@Nullable ah ahVar) {
        while (ahVar != null) {
            if (ahVar.l() == null) {
                ah ahVar2 = ahVar.n;
                if (ahVar != ahVar2) {
                    ahVar = ahVar2;
                }
            }
            return ahVar;
        }
        return null;
    }

    @Nullable
    private au A() {
        at atVar;
        ca caVar = this.j;
        if (caVar == null) {
            atVar = null;
        } else {
            atVar = (at) caVar.f();
        }
        if (atVar != null) {
            this.E = atVar.a;
        }
        return this.E;
    }

    public o getApkDownloader() {
        return this.G;
    }

    public final void a(@NonNull RenderView renderView) {
        if (this.z == null) {
            this.z = new LinkedList();
        }
        if (!this.z.contains(renderView)) {
            this.z.add(renderView);
        }
    }

    private void a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str2);
        a(str, (Map<String, Object>) hashMap);
    }

    private void a(@NonNull ak akVar, int i2, String str) {
        if (1 != i2) {
            a(str, akVar.s, akVar);
        } else if (com.inmobi.commons.core.utilities.b.a(str)) {
            Context context = (Context) this.p.get();
            if (context != null) {
                if (l() == null) {
                    c cVar = this.o;
                    if (cVar != null) {
                        cVar.c();
                    }
                }
                InMobiAdActivity.a((RenderView) null);
                InMobiAdActivity.a(u());
                Intent intent = new Intent(context, InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 100);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str);
                intent.putExtra(AudienceNetworkActivity.PLACEMENT_ID, this.e);
                intent.putExtra("creativeId", this.f);
                intent.putExtra("impressionId", this.d);
                intent.putExtra("allowAutoRedirection", this.g);
                com.inmobi.commons.a.a.a(context, intent);
            }
        } else {
            a(str, (String) null, akVar);
        }
    }

    private void a(@NonNull String str, @Nullable String str2, @NonNull ak akVar) {
        if (this.p.get() != null) {
            String a2 = com.inmobi.commons.core.utilities.b.a((Context) this.p.get(), str, str2);
            if (a2 != null) {
                ah f2 = f(this);
                if (f2 != null) {
                    c cVar = f2.o;
                    if (cVar != null && !this.u) {
                        cVar.g();
                    }
                    if (a2.equals(str2)) {
                        akVar.a(TrackerEventType.TRACKER_EVENT_TYPE_FALLBACK_URL, a(akVar));
                    }
                }
            }
        }
    }

    private void B() {
        Context context = (Context) this.p.get();
        if (context instanceof Activity) {
            ((Activity) context).getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    public void onActivityStarted(Activity activity) {
        Context C2 = C();
        if (C2 != null && C2.equals(activity)) {
            p();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void p() {
        this.m = false;
        d(f());
        x();
        ca caVar = this.j;
        if (caVar != null) {
            caVar.a(C(), 0);
        }
    }

    public void onActivityStopped(Activity activity) {
        Context C2 = C();
        if (C2 != null && C2.equals(activity)) {
            q();
        }
    }

    private Context C() {
        Activity l2 = l();
        return l2 == null ? (Context) this.p.get() : l2;
    }

    /* access modifiers changed from: 0000 */
    public void q() {
        this.m = true;
        c(f());
        y();
        ca caVar = this.j;
        if (caVar != null) {
            caVar.a(C(), 1);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        ca caVar = this.j;
        if (caVar != null) {
            caVar.a(activity, 2);
        }
        B();
    }

    /* access modifiers changed from: 0000 */
    public final void r() {
        ak akVar = this.I;
        if (akVar == null || this.J == null) {
            if (!(this.v == null || this.p.get() == null)) {
                com.inmobi.commons.a.a.a((Context) this.p.get(), this.v);
            }
            return;
        }
        a(akVar, akVar.i, this.J);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final RenderView s() {
        RenderView renderView = this.w;
        return renderView == null ? this.x : renderView;
    }

    /* access modifiers changed from: 0000 */
    public final void t() {
        new a(this).start();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final com.inmobi.rendering.RenderView.a u() {
        if (this.L == null) {
            this.L = new com.inmobi.rendering.RenderView.a() {
                public final void a(RenderView renderView) {
                }

                public final void a(HashMap<Object, Object> hashMap) {
                }

                public final void b(RenderView renderView) {
                }

                public final void w() {
                }

                public final void y() {
                }

                public final void A() {
                    c e = ah.this.e();
                    if (e != null) {
                        e.a();
                    }
                }

                public final void c(RenderView renderView) {
                    c e = ah.this.e();
                    if (e != null) {
                        e.b();
                    }
                }

                public final void d(RenderView renderView) {
                    c e = ah.this.e();
                    if (e != null) {
                        e.f();
                    }
                }

                public final void b(HashMap<Object, Object> hashMap) {
                    c e = ah.this.e();
                    if (e != null) {
                        e.e();
                    }
                }

                public final void B() {
                    c e = ah.this.e();
                    if (e != null) {
                        e.g();
                    }
                }

                public final void b(String str, Map<String, Object> map) {
                    ah.this.a(str, map);
                }

                public final void G() {
                    c e = ah.this.e();
                    if (e != null && PlacementType.PLACEMENT_TYPE_INLINE == ah.this.b.a) {
                        e.c();
                    }
                }
            };
        }
        return this.L;
    }

    public final void a(int i2, Map<String, String> map) {
        if (!this.l) {
            switch (i2) {
                case 1:
                    this.a.d.a(TrackerEventType.TRACKER_EVENT_TYPE_LOAD, map);
                    return;
                case 2:
                    this.a.d.a(TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL, map);
                    return;
                case 3:
                    return;
                default:
                    return;
            }
        }
    }

    private void a(int i2, @NonNull am amVar) {
        if (!this.l) {
            this.C.add(Integer.valueOf(i2));
            amVar.z = System.currentTimeMillis();
            if (this.k) {
                b((ak) amVar, a((ak) amVar));
            } else {
                this.D.add(amVar);
            }
        }
    }

    @Nullable
    private ak b(@Nullable ao aoVar, @NonNull ak akVar) {
        ak akVar2 = null;
        if (aoVar == null) {
            return null;
        }
        String str = akVar.r;
        String str2 = akVar.s;
        if (str != null) {
            akVar2 = a(akVar, aoVar, str);
        }
        if (akVar2 == null && str2 != null) {
            akVar2 = a(akVar, aoVar, str2);
        }
        if (akVar2 != null) {
            StringBuilder sb = new StringBuilder("Referenced asset (");
            sb.append(akVar2.d);
            sb.append(")");
        }
        return akVar2;
    }

    @Nullable
    protected static ak a(@Nullable ao aoVar, @NonNull ak akVar) {
        while (aoVar != null) {
            String str = akVar.j;
            if (str == null || str.length() == 0) {
                akVar.l = 0;
                return akVar;
            }
            String[] split = str.split("\\|");
            if (1 == split.length) {
                akVar.l = a(split[0]);
                return akVar;
            }
            ak b2 = aoVar.b(split[0]);
            if (b2 == null) {
                aoVar = aoVar.f;
            } else if (b2.equals(akVar)) {
                return null;
            } else {
                b2.l = a(split[1]);
                StringBuilder sb = new StringBuilder("Referenced asset (");
                sb.append(b2.d);
                sb.append(")");
                return b2;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final void a(@Nullable View view, @NonNull ak akVar) {
        if (!this.l) {
            w();
            ak b2 = b(this.a, akVar);
            if (b2 != null) {
                Map a2 = a(b2);
                a(b2, a2);
                if (!b2.equals(akVar)) {
                    a(akVar, a2);
                }
            } else {
                a(akVar, a(akVar));
            }
            ah f2 = f(this);
            if (f2 != null) {
                if (!akVar.r.trim().isEmpty()) {
                    c cVar = f2.o;
                    if (cVar != null) {
                        cVar.e();
                    }
                }
                ak a3 = a(this.a, akVar);
                if (a3 != null) {
                    if (view != null && ShareConstants.VIDEO_URL.equals(a3.b) && 5 == a3.l) {
                        view.setVisibility(4);
                        akVar.x = 4;
                    }
                    b(a3);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(View view) {
        if (!this.k && !this.l) {
            this.k = true;
            this.a.d.a(TrackerEventType.TRACKER_EVENT_TYPE_RENDER, a((ak) this.a.d));
            HashMap hashMap = new HashMap();
            hashMap.put("type", PlacementType.PLACEMENT_TYPE_FULLSCREEN == getRenderingProperties().a ? "int" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            hashMap.put("clientRequestId", this.h);
            hashMap.put("impId", this.d);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "AdRendered", hashMap);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "ViewableBeaconFired", hashMap);
            w();
            for (ak akVar : this.D) {
                b(akVar, a(akVar));
            }
            this.D.clear();
            ah f2 = f(this);
            if (f2 != null) {
                c cVar = f2.o;
                if (cVar != null) {
                    cVar.d();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @TargetApi(15)
    public void b(@NonNull ak akVar) {
        switch (akVar.l) {
            case 0:
                return;
            case 1:
                try {
                    if (this.w != null) {
                        this.w.d("window.imraid.broadcastEvent('close');");
                    }
                    b();
                    return;
                } catch (Exception e2) {
                    new StringBuilder("Encountered unexpected error in handling exit action on video: ").append(e2.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in exiting video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                    return;
                }
            case 3:
                try {
                    if (this.w != null) {
                        this.w.d("window.imraid.broadcastEvent('replay');");
                    }
                    if (f() != null) {
                        View f2 = f();
                        ViewGroup viewGroup = (ViewGroup) f2.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeView(f2);
                        }
                    }
                    ah ahVar = this.n;
                    NativeTimerView b2 = b(ahVar.f());
                    if (!(b2 == null || b2.c == null || !b2.c.isRunning())) {
                        b2.c.setCurrentPlayTime(b2.a * 1000);
                        b2.a(1.0f);
                    }
                    if (!ShareConstants.VIDEO_URL.equals(akVar.b)) {
                        new StringBuilder("Action 3 not valid for asset of type: ").append(akVar.b);
                        return;
                    }
                    if (ahVar instanceof bd) {
                        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) ahVar.getVideoContainerView();
                        if (nativeVideoWrapper != null) {
                            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                            be beVar = (be) videoView.getTag();
                            if (beVar != null) {
                                if (beVar.a()) {
                                    videoView.e();
                                } else {
                                    videoView.d();
                                }
                            } else if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a) {
                                videoView.e();
                            } else {
                                videoView.d();
                            }
                            a(beVar, ahVar);
                            videoView.start();
                        }
                    }
                    return;
                } catch (Exception e3) {
                    new StringBuilder("Encountered unexpected error in handling replay action on video: ").append(e3.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in replaying video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                    return;
                }
            case 4:
                try {
                    if (PlacementType.PLACEMENT_TYPE_INLINE == this.b.a) {
                        m();
                    }
                    return;
                } catch (Exception e4) {
                    new StringBuilder("Encountered unexpected error in handling fullscreen action ").append(e4.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in launching fullscreen ad");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
                    return;
                }
            case 5:
                return;
            default:
                this.r = true;
                RenderView renderView = this.w;
                if (!(renderView == null || renderView == null)) {
                    renderView.d("window.imraid.broadcastEvent('skip');");
                }
                c(f());
                c(akVar);
                return;
        }
    }

    public final void b() {
        Activity activity;
        try {
            if (!this.l) {
                ah f2 = f(this);
                if (f2 != null) {
                    f2.o();
                    InMobiAdActivity.a((Object) f2);
                    if (f2 instanceof bd) {
                        bd bdVar = (bd) f2;
                        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) bdVar.getVideoContainerView();
                        if (nativeVideoWrapper != null) {
                            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                            be beVar = (be) videoView.getTag();
                            beVar.v.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(videoView.getCurrentPosition()));
                            beVar.v.put("lastMediaVolume", Integer.valueOf(videoView.getVolume()));
                            if (beVar.y != null) {
                                ((be) beVar.y).a(beVar);
                            }
                            a(beVar, (ah) bdVar);
                        }
                    }
                    if (f2.q == null) {
                        activity = null;
                    } else {
                        activity = (Activity) f2.q.get();
                    }
                    if (activity != null && (activity instanceof InMobiAdActivity)) {
                        ((InMobiAdActivity) activity).a = true;
                        activity.finish();
                        if (this.F != -1) {
                            activity.overridePendingTransition(0, this.F);
                        }
                    }
                    this.n.H = null;
                    this.n.N.submit(this.O);
                }
            }
        } catch (Exception e2) {
            new StringBuilder("Encountered unexpected error in handling exit action on video: ").append(e2.getMessage());
            Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in exiting video");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    @UiThread
    public final void c(@Nullable ak akVar) {
        ah ahVar = this.K;
        if (ahVar == null || f() == null) {
            Logger.a(InternalLogLevel.DEBUG, "InMobi", "Failed to show end card");
            b();
        } else {
            try {
                a("EndCardRequested", z());
                ViewGroup viewGroup = (ViewGroup) f();
                View a2 = ahVar.getViewableAd().a(null, viewGroup, false);
                if (a2 != null) {
                    viewGroup.addView(a2);
                    a2.setClickable(true);
                    ahVar.x();
                    a("EndCardDisplayed", z());
                    if (akVar instanceof be) {
                        bt f2 = ((be) akVar).b().f();
                        if (f2 != null) {
                            f2.g = true;
                        }
                    }
                } else {
                    b();
                }
            } catch (Exception e2) {
                b();
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }
    }

    @NonNull
    public /* bridge */ /* synthetic */ Object getDataModel() {
        return this.a;
    }

    static /* synthetic */ void a(ah ahVar) {
        ah ahVar2 = ahVar;
        ao aoVar = ahVar2.a;
        if (aoVar.e.length() != 0) {
            JSONObject a2 = aoVar.a();
            if (a2 != null) {
                ao aoVar2 = new ao(ahVar2.b.a, a2, aoVar, ahVar2.b.a == PlacementType.PLACEMENT_TYPE_INLINE, ahVar2.c, null);
                aoVar2.c = aoVar.c;
                aoVar2.j = aoVar.j;
                Context context = (Context) ahVar2.p.get();
                if (aoVar2.c() && context != null) {
                    ahVar2.H = b.a(context, new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), aoVar2, ahVar2.d, ahVar2.h, ahVar2.i, ahVar2.c, ahVar2.e, ahVar2.g, ahVar2.f);
                    ahVar2.H.a((AdContainer) ahVar2);
                    c cVar = ahVar2.o;
                    if (cVar != null) {
                        ahVar2.H.o = cVar;
                    }
                    if (aoVar.c) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public final void run() {
                                ah.this.H.getViewableAd().a(null, new RelativeLayout(ah.this.j()), false);
                            }
                        });
                    }
                }
            }
        }
    }
}
