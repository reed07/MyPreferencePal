package com.inmobi.ads;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.mopub.common.AdType;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: InterstitialAdUnit */
public class ac extends i {
    /* access modifiers changed from: private */
    public static final String A = InMobiInterstitial.class.getSimpleName();
    /* access modifiers changed from: private */
    public static final String z = "ac";
    private int B;
    /* access modifiers changed from: private */
    public ArrayList<WeakReference<com.inmobi.ads.i.b>> C;
    boolean y;

    /* compiled from: InterstitialAdUnit */
    public static final class a {
        static final Map<bi, ac> a = new HashMap();

        @NonNull
        static ac b(Context context, bi biVar, com.inmobi.ads.i.b bVar) {
            ac acVar = new ac(context, biVar.a, bVar, 0);
            return acVar;
        }

        @NonNull
        public static ac a(Context context, bi biVar, com.inmobi.ads.i.b bVar) {
            long j = biVar.a;
            ac acVar = (ac) a.get(biVar);
            if (acVar != null) {
                if (acVar.h()) {
                    ac.z;
                    StringBuilder sb = new StringBuilder("Found expired adUnit for placement(");
                    sb.append(j);
                    sb.append("), thus clearing it.");
                    acVar.v();
                }
                acVar.a(context);
                if (bVar != null) {
                    acVar.a(bVar);
                }
                return acVar;
            }
            ac acVar2 = new ac(context, j, bVar, 0);
            a.put(biVar, acVar2);
            return acVar2;
        }
    }

    /* compiled from: InterstitialAdUnit */
    private final class b extends Exception {
        public b(String str) {
            super(str);
        }
    }

    /* compiled from: InterstitialAdUnit */
    private final class c extends Exception {
        public c(String str) {
            super(str);
        }
    }

    public final String b() {
        return "int";
    }

    public final String c() {
        return null;
    }

    public final void n() {
    }

    /* synthetic */ ac(Context context, long j, com.inmobi.ads.i.b bVar, byte b2) {
        this(context, j, bVar);
    }

    private ac(Context context, long j, com.inmobi.ads.i.b bVar) {
        super(context, j, bVar);
        this.B = 0;
        this.y = false;
        this.C = new ArrayList<>(1);
        MonetizationContext monetizationContext = MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
        super.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public final RenderView k() {
        RenderView k = super.k();
        if (this.y) {
            k.a();
        }
        return k;
    }

    /* access modifiers changed from: 0000 */
    public final boolean d(com.inmobi.ads.i.b bVar) {
        boolean z2;
        if (m()) {
            a("MissingDependency");
            a(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES), true);
            return false;
        }
        this.w = false;
        if (bVar == null) {
            g();
            return false;
        } else if (-1 == g(bVar)) {
            this.C.add(new WeakReference(bVar));
            if (!d.a()) {
                a(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE), true);
                return false;
            }
            switch (this.a) {
                case 1:
                    InternalLogLevel internalLogLevel = InternalLogLevel.ERROR;
                    String str = A;
                    StringBuilder sb = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: ");
                    sb.append(this.d);
                    Logger.a(internalLogLevel, str, sb.toString());
                    z2 = true;
                    break;
                case 2:
                    if (AdType.HTML.equals(this.m)) {
                        InternalLogLevel internalLogLevel2 = InternalLogLevel.ERROR;
                        String str2 = A;
                        StringBuilder sb2 = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: ");
                        sb2.append(this.d);
                        Logger.a(internalLogLevel2, str2, sb2.toString());
                    } else if (bVar != null) {
                        bVar.a(true);
                    }
                    z2 = true;
                    break;
                case 4:
                    if (bVar != null) {
                        bVar.a(true);
                    }
                    z2 = true;
                    break;
                case 7:
                case 8:
                    InternalLogLevel internalLogLevel3 = InternalLogLevel.ERROR;
                    String str3 = A;
                    StringBuilder sb3 = new StringBuilder("An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: ");
                    sb3.append(this.d);
                    Logger.a(internalLogLevel3, str3, sb3.toString());
                    InMobiAdRequestStatus inMobiAdRequestStatus = new InMobiAdRequestStatus(StatusCode.AD_ACTIVE);
                    a(inMobiAdRequestStatus);
                    int g = g(bVar);
                    if (g != -1) {
                        this.C.remove(g);
                    }
                    if (bVar != null) {
                        bVar.a(inMobiAdRequestStatus);
                    }
                    z2 = true;
                    break;
                default:
                    z2 = false;
                    break;
            }
            if (!z2) {
                return true;
            }
            d("AdLoadRequested");
            return false;
        } else {
            a(bVar, "ART", "LoadInProgress");
            return false;
        }
    }

    @UiThread
    public final void e(com.inmobi.ads.i.b bVar) {
        if (d(bVar)) {
            super.n();
        }
    }

    @UiThread
    private int g(com.inmobi.ads.i.b bVar) {
        int i = -1;
        for (int i2 = 0; i2 < this.C.size(); i2++) {
            WeakReference weakReference = (WeakReference) this.C.get(i2);
            if (weakReference != null) {
                com.inmobi.ads.i.b bVar2 = (com.inmobi.ads.i.b) weakReference.get();
                if (bVar2 != null && bVar2.equals(bVar)) {
                    i = i2;
                }
            }
        }
        return i;
    }

    private void S() {
        this.s.post(new Runnable() {
            public final void run() {
                for (int i = 0; i < ac.this.C.size(); i++) {
                    com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) ac.this.C.get(i)).get();
                    if (bVar == null) {
                        ac.this.g();
                    } else {
                        ac.this.a(bVar, "VAR", "");
                        ac.this.a(bVar, "ARF", "");
                        bVar.a(true);
                        bVar.a();
                        bVar.a((i) ac.this);
                    }
                }
                ac.this.C.clear();
            }
        });
    }

    public final boolean a(a aVar) {
        if (!super.a(aVar)) {
            b(aVar);
            return false;
        }
        if (aVar instanceof bc) {
            bc bcVar = (bc) aVar;
            com.inmobi.ads.cache.d.a();
            com.inmobi.ads.cache.a b2 = com.inmobi.ads.cache.d.b(bcVar.l);
            if (b2 == null || !b2.a()) {
                return false;
            }
            bx bxVar = new bx(b2.e, bcVar.m, bcVar.n, bcVar.h(), bcVar.i(), this.g.m);
            this.j = bxVar;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final void b(a aVar) {
        i().a(aVar);
    }

    public final void v() {
        super.v();
    }

    /* access modifiers changed from: 0000 */
    public final void f(com.inmobi.ads.i.b bVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            a(bVar, "AVRR", "");
            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Please ensure that you call show() on the UI thread");
        } else if (bVar == null) {
            g();
        } else if (!O()) {
            a(bVar, "AVRR", "");
            Logger.a(InternalLogLevel.ERROR, z, "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling show.");
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", "ShowIntBeforeReady");
            c("AdShowFailed", (Map<String, Object>) hashMap);
            bVar.b();
        } else if (!e.e()) {
            super.v();
            bVar.b();
        } else {
            a(bVar);
            this.a = 7;
            if (AdType.HTML.equals(this.m)) {
                AdContainer j = j();
                if (h()) {
                    i(bVar);
                    if (j != null) {
                        j.destroy();
                        return;
                    }
                } else {
                    h(bVar);
                }
                return;
            }
            final WeakReference weakReference = new WeakReference(bVar);
            this.p.execute(new Runnable() {
                public final void run() {
                    final com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) weakReference.get();
                    if (bVar != null) {
                        try {
                            if (ac.this.b(false)) {
                                ac.this.a(bVar, ac.this.h, new Runnable() {
                                    public final void run() {
                                        AdContainer j = ac.this.j();
                                        RenderView renderView = ac.this.u;
                                        if (renderView != null) {
                                            if (j instanceof ah) {
                                                ah ahVar = (ah) j;
                                                ahVar.w = renderView;
                                                ahVar.y = ac.this.r;
                                            } else {
                                                ac.this.i(bVar);
                                            }
                                        }
                                        ac.this.h(bVar);
                                    }
                                }, Looper.getMainLooper());
                            } else {
                                Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to Show Ad, canShowAd Failed");
                                ac.this.i(bVar);
                            }
                        } catch (b e) {
                            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), e.getMessage());
                            ac.this.i(bVar);
                        } catch (c e2) {
                            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), e2.getMessage());
                            ac.this.i(bVar);
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    @UiThread
    public void h(com.inmobi.ads.i.b bVar) {
        d("ShowInt");
        boolean T = T();
        if (bVar == null) {
            g();
        } else if (!T) {
            this.a = 3;
            a(bVar, "AVRR", "");
            bVar.b();
        } else {
            bVar.c();
        }
    }

    private boolean T() {
        try {
            StringBuilder sb = new StringBuilder(">>> Starting ");
            sb.append(InMobiAdActivity.class.getSimpleName());
            sb.append(" to display interstitial ad ...");
            AdContainer j = j();
            if (j != null) {
                if (!"unknown".equals(j.getMarkupType())) {
                    int a2 = InMobiAdActivity.a(j);
                    Intent intent = new Intent(a(), InMobiAdActivity.class);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", a2);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", AdType.HTML.equals(this.m) ? 200 : RequestCodes.EDIT_RECIPE_INGREDIENT);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
                    com.inmobi.commons.a.a.a(a(), intent);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Cannot show ad; SDK encountered an unexpected error");
            new StringBuilder("Encountered unexpected error while showing ad: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final PlacementType d() {
        return PlacementType.PLACEMENT_TYPE_FULLSCREEN;
    }

    public final void a(final long j, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        this.s.post(new Runnable() {
            public final void run() {
                try {
                    if (j == ac.this.d) {
                        InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                        String Q = ac.A;
                        StringBuilder sb = new StringBuilder("Failed to fetch ad for placement id: ");
                        sb.append(j);
                        sb.append(", reason phrase available in onAdLoadFailed callback.");
                        Logger.a(internalLogLevel, Q, sb.toString());
                        for (int i = 0; i < ac.this.C.size(); i++) {
                            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) ac.this.C.get(i)).get();
                            if (bVar == null) {
                                ac.this.g();
                            } else {
                                if (i < ac.this.C.size() - 1) {
                                    ac.this.a(bVar, "VAR", "");
                                }
                                ac.this.a(bVar, "ARN", "");
                            }
                        }
                        ac.this.a(inMobiAdRequestStatus, true);
                    }
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.ERROR, "[InMobi]", "Unable to load Ad; SDK encountered an unexpected error");
                    ac.z;
                    new StringBuilder("onAdFetchFailed with error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    @UiThread
    public final void c(long j, @NonNull a aVar) {
        try {
            super.c(j, aVar);
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = A;
            StringBuilder sb = new StringBuilder("Interstitial ad successfully fetched for placement id: ");
            sb.append(this.d);
            Logger.a(internalLogLevel, str, sb.toString());
            if (j == this.d && this.a == 2) {
                a(true, k());
                try {
                    a(null, this.h, null, null);
                } catch (Exception e) {
                    C();
                    a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to load ad; SDK encountered an internal error");
                    new StringBuilder("Loading ad markup into container encountered an unexpected error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, A, "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Handling ad fetch successful encountered an unexpected error: ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    @UiThread
    public final void a(long j, boolean z2, @NonNull a aVar) {
        try {
            super.a(j, z2, aVar);
            if (j == this.d) {
                if (1 == this.a && z2) {
                    this.a = 2;
                    if (super.a(aVar)) {
                        a(f(), "ARF", "");
                        c(aVar);
                        if (aVar.j) {
                            this.t = true;
                            I();
                            return;
                        }
                        Iterator it = this.C.iterator();
                        while (it.hasNext()) {
                            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
                            if (bVar != null) {
                                bVar.a(true);
                            } else {
                                g();
                            }
                        }
                        return;
                    }
                    Iterator it2 = this.C.iterator();
                    while (it2.hasNext()) {
                        com.inmobi.ads.i.b bVar2 = (com.inmobi.ads.i.b) ((WeakReference) it2.next()).get();
                        if (bVar2 != null) {
                            bVar2.a(false);
                        } else {
                            g();
                        }
                    }
                } else if (4 == this.a || 5 == this.a || 2 == this.a) {
                    this.a = 0;
                    Iterator it3 = this.C.iterator();
                    while (it3.hasNext()) {
                        com.inmobi.ads.i.b bVar3 = (com.inmobi.ads.i.b) ((WeakReference) it3.next()).get();
                        if (bVar3 != null) {
                            bVar3.a(new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE));
                        } else {
                            g();
                        }
                        this.C.clear();
                    }
                }
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.class.getSimpleName(), "Unable to load ad; SDK encountered an internal error");
            new StringBuilder("Handling ad availability change event encountered an unexpected error: ").append(e.getMessage());
        }
    }

    @UiThread
    public final void b(long j, boolean z2) {
        super.b(j, z2);
        if (!z2) {
            if (j == this.d && (2 == this.a || 5 == this.a)) {
                this.a = 0;
                a(new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE), false);
            }
        } else if (j == this.d && 2 == this.a) {
            if (this.t) {
                this.v = true;
                J();
                return;
            }
            K();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void K() {
        F();
        this.a = 5;
        for (int i = 0; i < this.C.size(); i++) {
            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) this.C.get(i)).get();
            if (bVar == null) {
                g();
            } else {
                if (i < this.C.size() - 1) {
                    a(bVar, "VAR", "");
                    a(bVar, "ARF", "");
                }
                bVar.a((i) this);
            }
        }
        this.C.clear();
    }

    public final void x() {
        super.x();
        if (this.a == 4) {
            C();
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = A;
            StringBuilder sb = new StringBuilder("Successfully loaded Interstitial ad markup in the WebView for placement id: ");
            sb.append(this.d);
            Logger.a(internalLogLevel, str, sb.toString());
            s();
            K();
        }
    }

    public final void a(RenderView renderView) {
        super.a(renderView);
        if (this.a == 2) {
            this.a = 4;
            L();
        }
    }

    public final void z() {
        super.z();
        if (this.a == 4) {
            C();
            this.a = 3;
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = A;
            StringBuilder sb = new StringBuilder("Failed to load the Interstitial markup in the WebView for placement id: ");
            sb.append(this.d);
            Logger.a(internalLogLevel, str, sb.toString());
            a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
        }
    }

    public final synchronized void c(RenderView renderView) {
        super.c(renderView);
        b(f());
    }

    public final synchronized void d(RenderView renderView) {
        super.d(renderView);
        c(f());
    }

    public final void E() {
        b("RenderTimeOut");
        if (this.k != null) {
            i().a(this.k);
        }
        if (4 == this.a || 2 == this.a) {
            this.a = 3;
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = z;
            StringBuilder sb = new StringBuilder("Failed to load the Interstitial markup in the webview due to time out for placement id: ");
            sb.append(this.d);
            Logger.a(internalLogLevel, str, sb.toString());
            a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void L() {
        Iterator it = this.C.iterator();
        while (it.hasNext()) {
            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
            if (bVar != null) {
                bVar.a(true);
            } else {
                g();
            }
        }
    }

    /* access modifiers changed from: private */
    public void i(final com.inmobi.ads.i.b bVar) {
        a(bVar, "AVFB", "");
        this.s.post(new Runnable() {
            public final void run() {
                ac acVar = ac.this;
                acVar.a = 0;
                com.inmobi.ads.i.b bVar = bVar;
                if (bVar != null) {
                    bVar.b();
                } else {
                    acVar.g();
                }
            }
        });
    }

    public final void a(MonetizationContext monetizationContext) {
        super.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
    }

    public final MonetizationContext l() {
        return MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
    }

    /* access modifiers changed from: 0000 */
    public final void q() {
        this.s.post(new Runnable() {
            public final void run() {
                try {
                    ac.this.w = false;
                    if (ac.this.p()) {
                        ac.this.c("IllegalState");
                    } else {
                        ac.super.q();
                    }
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.ERROR, ac.A, "Unable to Prefetch ad; SDK encountered an unexpected error");
                    ac.z;
                    new StringBuilder("Prefetch failed with unexpected error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public final int r() {
        if (1 == this.a) {
            InternalLogLevel internalLogLevel = InternalLogLevel.ERROR;
            String str = A;
            StringBuilder sb = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: ");
            sb.append(this.d);
            Logger.a(internalLogLevel, str, sb.toString());
            return 2;
        } else if (5 != this.a) {
            return super.r();
        } else {
            if (R()) {
                return super.r();
            }
            return 1;
        }
    }

    private boolean R() {
        try {
            if (AdType.HTML.equals(this.m)) {
                if (h()) {
                    super.v();
                    return true;
                }
                S();
                return false;
            } else if (!b(true)) {
                return true;
            } else {
                S();
                return false;
            }
        } catch (b unused) {
            return true;
        } catch (c unused2) {
            return true;
        }
    }

    public final boolean O() {
        return this.a == 5;
    }

    /* access modifiers changed from: 0000 */
    @UiThread
    public final void a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z2) {
        if (this.a == 1 && z2) {
            this.a = 3;
        }
        Iterator it = this.C.iterator();
        while (it.hasNext()) {
            com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
            if (bVar != null) {
                bVar.a(inMobiAdRequestStatus);
            } else {
                g();
            }
        }
        this.C.clear();
        a(inMobiAdRequestStatus);
        super.v();
    }

    /* access modifiers changed from: 0000 */
    public final void b(com.inmobi.ads.i.b bVar) {
        if (this.a == 7) {
            this.B++;
            if (this.B == 1) {
                d("AdRendered");
                InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                String str = A;
                StringBuilder sb = new StringBuilder("Successfully displayed Interstitial for placement id: ");
                sb.append(this.d);
                Logger.a(internalLogLevel, str, sb.toString());
                if (bVar != null) {
                    bVar.d();
                } else {
                    g();
                }
            } else {
                this.a = 8;
            }
        } else {
            if (this.a == 8) {
                this.B++;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void c(com.inmobi.ads.i.b bVar) {
        if (this.a == 8) {
            this.B--;
            if (this.B == 1) {
                this.a = 7;
            }
        } else if (this.a == 7) {
            this.B--;
            d("IntClosed");
            super.v();
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str = A;
            StringBuilder sb = new StringBuilder("Interstitial ad dismissed for placement id: ");
            sb.append(this.d);
            Logger.a(internalLogLevel, str, sb.toString());
            if (bVar != null) {
                bVar.e();
                return;
            }
            g();
        }
    }

    /* access modifiers changed from: private */
    public boolean b(boolean z2) throws b, c {
        a aVar;
        String str = this.k;
        if (str == null) {
            return false;
        }
        if (z2) {
            i();
            b.b();
            aVar = d.c(str);
        } else {
            h i = i();
            h.c();
            d dVar = i.b;
            a c2 = d.c(str);
            if (c2 != null) {
                d.a(str);
            }
            i.a(i.c);
            aVar = c2;
        }
        if (aVar == null) {
            throw new b("No Cached Ad found for AdUnit");
        } else if (a(aVar)) {
            return true;
        } else {
            throw new c("No Cached Asset for AdUnit");
        }
    }

    @UiThread
    public final void H() {
        if (1 == this.a) {
            this.a = 9;
            if (this.q != null) {
                this.q.a(this);
            }
            Iterator it = this.C.iterator();
            while (it.hasNext()) {
                com.inmobi.ads.i.b bVar = (com.inmobi.ads.i.b) ((WeakReference) it.next()).get();
                if (bVar != null) {
                    e(bVar);
                    return;
                }
                g();
            }
        }
    }

    @UiThread
    public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a) {
            this.a = 3;
            if (this.q != null) {
                this.q.a(this, inMobiAdRequestStatus);
            }
            if (this.C.size() > 0) {
                a(inMobiAdRequestStatus, false);
            }
        }
    }
}
