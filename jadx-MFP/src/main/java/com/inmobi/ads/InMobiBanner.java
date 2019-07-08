package com.inmobi.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.i.e;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.c;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public final class InMobiBanner extends RelativeLayout {
    /* access modifiers changed from: private */
    public static final String a = "InMobiBanner";
    /* access modifiers changed from: private */
    public static ConcurrentHashMap<p, WeakReference<BannerAdRequestListener>> q = new ConcurrentHashMap<>(5, 0.9f, 3);
    /* access modifiers changed from: private */
    @Nullable
    public BannerAdListener b;
    /* access modifiers changed from: private */
    @Nullable
    public BannerAdEventListener c;
    /* access modifiers changed from: private */
    public b d;
    @Nullable
    private p e;
    @Nullable
    private p f;
    /* access modifiers changed from: private */
    @Nullable
    public p g;
    /* access modifiers changed from: private */
    @Nullable
    public p h;
    private boolean i = false;
    private int j;
    private boolean k = true;
    @Nullable
    private q l;
    /* access modifiers changed from: private */
    public int m = 0;
    /* access modifiers changed from: private */
    public int n = 0;
    private AnimationType o = AnimationType.ROTATE_HORIZONTAL_AXIS;
    private long p = 0;
    private j r;
    @Nullable
    private WeakReference<Activity> s;
    @Nullable
    private bi t;
    private boolean u;
    private boolean v = true;
    /* access modifiers changed from: private */
    public final com.inmobi.ads.i.b w = new com.inmobi.ads.i.b() {
        public final void a() {
            try {
                if (InMobiBanner.this.g == null || !InMobiBanner.this.g.P()) {
                    InMobiBanner.a(InMobiBanner.this, (a) new a() {
                        public final void a() {
                            try {
                                InMobiBanner.this.a("AR", "");
                                InMobiBanner.this.d.sendEmptyMessage(1);
                                InMobiBanner.this.b();
                            } catch (Exception e) {
                                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in scheduling refresh for banner ad");
                                InMobiBanner.a;
                                new StringBuilder("InMobiBanner$5.onSuccess() handler threw unexpected error: ").append(e.getMessage());
                            }
                        }
                    });
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in loading banner ad");
                InMobiBanner.a;
                new StringBuilder("InMobiBanner$2.onAdLoadSucceeded() handler threw unexpected error: ").append(e.getMessage());
            }
        }

        public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
            try {
                switch (AnonymousClass5.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiBanner.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                    case 3:
                        InMobiBanner.this.a("ART", "LoadInProgress");
                        break;
                    case 4:
                        InMobiBanner.this.a("ART", "FrequentRequests");
                        break;
                    default:
                        InMobiBanner.this.a("AF", "");
                        break;
                }
                if (!InMobiBanner.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiBanner.this.d.sendMessage(obtain);
                }
                InMobiBanner.this.b();
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in loading banner ad");
                InMobiBanner.a;
                new StringBuilder("InMobiBanner$2.onAdLoadFailed() handler threw unexpected error: ").append(e.getMessage());
            }
        }

        public final void d() {
            InMobiBanner.this.d.sendEmptyMessage(3);
        }

        public final void e() {
            try {
                InMobiBanner.this.b();
                InMobiBanner.this.d.sendEmptyMessage(4);
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in closing banner ad");
                InMobiBanner.a;
                new StringBuilder("InMobiBanner$2.onAdDismissed() handler threw unexpected error: ").append(e.getMessage());
            }
        }

        public final void a(@NonNull Map<Object, Object> map) {
            InMobiBanner.this.a("AVCL", "");
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = map;
            InMobiBanner.this.d.sendMessage(obtain);
        }

        public final void f() {
            InMobiBanner.this.d.sendEmptyMessage(6);
        }

        public final void b(@NonNull Map<Object, Object> map) {
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.obj = map;
            InMobiBanner.this.d.sendMessage(obtain);
        }

        /* access modifiers changed from: 0000 */
        public final void a(byte[] bArr) {
            Message obtain = Message.obtain();
            obtain.what = 8;
            obtain.obj = bArr;
            InMobiBanner.this.d.sendMessage(obtain);
        }

        /* access modifiers changed from: 0000 */
        public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
            Message obtain = Message.obtain();
            obtain.what = 9;
            obtain.obj = inMobiAdRequestStatus;
            InMobiBanner.this.d.sendMessage(obtain);
        }
    };

    /* renamed from: com.inmobi.ads.InMobiBanner$5 reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[StatusCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode[] r0 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.REQUEST_PENDING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.AD_ACTIVE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.EARLY_REFRESH_REQUEST     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.InMobiBanner.AnonymousClass5.<clinit>():void");
        }
    }

    public enum AnimationType {
        ANIMATION_OFF,
        ROTATE_HORIZONTAL_AXIS,
        ANIMATION_ALPHA,
        ROTATE_VERTICAL_AXIS
    }

    public interface BannerAdListener {
        void onAdDismissed(InMobiBanner inMobiBanner);

        void onAdDisplayed(InMobiBanner inMobiBanner);

        void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiBanner inMobiBanner);

        void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onUserLeftApplication(InMobiBanner inMobiBanner);
    }

    public interface BannerAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiBanner inMobiBanner);
    }

    private interface a {
        void a();
    }

    @VisibleForTesting
    static final class b extends Handler {
        private WeakReference<InMobiBanner> a;

        b(InMobiBanner inMobiBanner) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(inMobiBanner);
        }

        public final void handleMessage(Message message) {
            InMobiBanner inMobiBanner = (InMobiBanner) this.a.get();
            if (inMobiBanner != null) {
                try {
                    Map map = null;
                    switch (message.what) {
                        case 1:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdLoadSucceeded(inMobiBanner);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdLoadSucceeded(inMobiBanner);
                                return;
                            }
                            break;
                        case 2:
                            InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                                return;
                            }
                            break;
                        case 3:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdDisplayed(inMobiBanner);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdDisplayed(inMobiBanner);
                                return;
                            }
                            break;
                        case 4:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdDismissed(inMobiBanner);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdDismissed(inMobiBanner);
                                return;
                            }
                            break;
                        case 5:
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdClicked(inMobiBanner, map);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdInteraction(inMobiBanner, map);
                                return;
                            }
                            break;
                        case 6:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onUserLeftApplication(inMobiBanner);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onUserLeftApplication(inMobiBanner);
                                return;
                            }
                            break;
                        case 7:
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRewardsUnlocked(inMobiBanner, map);
                                return;
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdRewardActionCompleted(inMobiBanner, map);
                                return;
                            }
                            break;
                        case 8:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRequestPayloadCreated((byte[]) message.obj);
                                return;
                            }
                            break;
                        case 9:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                                return;
                            }
                            break;
                        default:
                            InMobiBanner.a;
                            break;
                    }
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "Publisher handler caused unexpected error");
                    InMobiBanner.a;
                    new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
                }
            }
        }
    }

    public InMobiBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Banner ad");
            return;
        }
        boolean z = context instanceof Activity;
        if (z) {
            this.s = new WeakReference<>((Activity) context);
        }
        this.d = new b(this);
        String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", AudienceNetworkActivity.PLACEMENT_ID);
        String attributeValue2 = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "refreshInterval");
        if (attributeValue != null) {
            long a2 = a(attributeValue);
            if (a2 != Long.MIN_VALUE) {
                this.t = bi.a(a2, null, "banner", null);
                this.t.f = z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER;
                a(context, this.t);
                this.i = true;
            }
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Placement id value is not supplied in XML layout. Banner creation failed.");
        }
        if (attributeValue2 != null) {
            try {
                setRefreshInterval(Integer.parseInt(attributeValue2.trim()));
            } catch (NumberFormatException unused) {
                Logger.a(InternalLogLevel.ERROR, a, "Refresh interval value supplied in XML layout is not valid. Falling back to default value.");
            }
        }
    }

    private static long a(@NonNull String str) {
        try {
            StringBuilder sb = new StringBuilder(str.trim());
            if ("plid-".equalsIgnoreCase(sb.substring(0, 5))) {
                return Long.parseLong(sb.substring(5, sb.length()).trim());
            }
            InternalLogLevel internalLogLevel = InternalLogLevel.ERROR;
            String str2 = a;
            StringBuilder sb2 = new StringBuilder("Invalid Placement id: ");
            sb2.append(str);
            sb2.append(" Placement id value supplied in XML layout is not valid. Please make sure placement id is in plid-0123456789 format.");
            Logger.a(internalLogLevel, str2, sb2.toString());
            return Long.MIN_VALUE;
        } catch (NumberFormatException unused) {
            InternalLogLevel internalLogLevel2 = InternalLogLevel.ERROR;
            String str3 = a;
            StringBuilder sb3 = new StringBuilder("Invalid Placement id: ");
            sb3.append(str);
            sb3.append(" Placement id value supplied in XML layout is not valid. Banner creation failed.");
            Logger.a(internalLogLevel2, str3, sb3.toString());
        } catch (StringIndexOutOfBoundsException unused2) {
            InternalLogLevel internalLogLevel3 = InternalLogLevel.ERROR;
            String str4 = a;
            StringBuilder sb4 = new StringBuilder("Invalid Placement id: ");
            sb4.append(str);
            sb4.append(" Placement id value supplied in XML layout is not valid. Please make sure placement id is in plid-0123456789 format.");
            Logger.a(internalLogLevel3, str4, sb4.toString());
        }
    }

    public InMobiBanner(Context context, long j2) {
        super(context);
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Banner ad");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to create InMobiBanner ad with null context object.");
        } else {
            boolean z = context instanceof Activity;
            if (z) {
                this.s = new WeakReference<>((Activity) context);
            }
            this.d = new b(this);
            this.t = bi.a(j2, null, "banner", null);
            this.t.f = z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER;
            a(context, this.t);
            this.i = true;
        }
    }

    private boolean b(boolean z) {
        if (!this.i) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner is not initialized. Ignoring your call");
        } else if (!z || this.c != null) {
            return true;
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, Ignoring your call.");
        }
        return false;
    }

    public final void getSignals() {
        if (b(true)) {
            setEnableAutoRefresh(false);
            a("ARR", "");
            p pVar = this.h;
            if (pVar != null) {
                pVar.A = getFrameSizeString();
                p pVar2 = this.h;
                pVar2.y = false;
                pVar2.o();
            }
        }
    }

    public final void load(byte[] bArr) {
        boolean z = !b(false);
        p pVar = this.h;
        if (pVar == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please make sure getSignals is called before calling Load");
            return;
        }
        if (!z) {
            pVar.w = false;
            pVar.a(bArr);
        }
    }

    public final void load() {
        if (b(false)) {
            a(false);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(final boolean z) {
        try {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner is not initialized. Ignoring InMobiBanner.load()");
                return;
            }
            if (this.i) {
                a("ARR", "");
                if (this.g == null || !this.g.P()) {
                    if (!a()) {
                        if (getLayoutParams() == null) {
                            Logger.a(InternalLogLevel.ERROR, a, "The layout params of the banner must be set before calling load or call setBannerSize(int widthInDp, int heightInDp) before load");
                            this.w.a(new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
                            return;
                        }
                        if (getLayoutParams().width != -2) {
                            if (getLayoutParams().height != -2) {
                                g();
                            }
                        }
                        Logger.a(InternalLogLevel.ERROR, a, "The height or width of a Banner ad can't be WRAP_CONTENT or call setBannerSize(int widthInDp, int heightInDp) before load");
                        this.w.a(new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
                        return;
                    }
                    if (!a()) {
                        new Handler().postDelayed(new Runnable() {
                            public final void run() {
                                try {
                                    if (InMobiBanner.this.a()) {
                                        InMobiBanner.this.h();
                                        if (InMobiBanner.this.f() && InMobiBanner.this.h != null) {
                                            InMobiBanner.this.h.A = InMobiBanner.this.getFrameSizeString();
                                            InMobiBanner.this.h.b(z);
                                        }
                                    } else {
                                        Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "The height or width of the banner can not be determined");
                                        com.inmobi.ads.i.b d = InMobiBanner.this.w;
                                        InMobiBanner.this.h;
                                        d.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                                    }
                                } catch (Exception e) {
                                    Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "SDK encountered unexpected error while loading an ad");
                                    InMobiBanner.a;
                                    new StringBuilder("InMobiBanner$4.run() threw unexpected error: ").append(e.getMessage());
                                }
                            }
                        }, 200);
                        return;
                    }
                    h();
                    if (f() && this.h != null) {
                        this.h.A = getFrameSizeString();
                        this.h.b(z);
                    }
                } else {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = new InMobiAdRequestStatus(StatusCode.AD_ACTIVE);
                    a("ART", "LoadInProgress");
                    this.d.sendMessage(obtain);
                    this.g.b("AdActive");
                    Logger.a(InternalLogLevel.ERROR, a, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad.");
                }
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e2.getMessage());
        }
    }

    public final void load(Context context) {
        MonetizationContext monetizationContext;
        if (b(false)) {
            boolean z = context instanceof Activity;
            if (z) {
                this.s = new WeakReference<>((Activity) context);
            } else {
                this.s = null;
            }
            bi biVar = this.t;
            if (biVar != null) {
                if (z) {
                    monetizationContext = MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
                } else {
                    monetizationContext = MonetizationContext.MONETIZATION_CONTEXT_OTHER;
                }
                biVar.f = monetizationContext;
                a(context, this.t);
            }
            a(false);
        }
    }

    /* access modifiers changed from: private */
    public void setMonetizationContext(MonetizationContext monetizationContext) {
        p pVar = this.e;
        if (pVar != null && this.f != null) {
            pVar.a(monetizationContext);
            this.f.a(monetizationContext);
        }
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, BannerAdRequestListener bannerAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null Context. Aborting request");
        } else if (inMobiAdRequest == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non  null InMobiAdRequest. Ignoring request");
        } else if (bannerAdRequestListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null BannerAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest.c > 0 || inMobiAdRequest.d > 0) {
            AnonymousClass2 r0 = new e() {
                public final void a(@NonNull i iVar) {
                    if (iVar instanceof p) {
                        try {
                            WeakReference weakReference = (WeakReference) InMobiBanner.q.get(iVar);
                            if (weakReference != null) {
                                InMobiBanner.q.remove(iVar);
                                BannerAdRequestListener bannerAdRequestListener = (BannerAdRequestListener) weakReference.get();
                                if (bannerAdRequestListener != null) {
                                    InMobiBanner inMobiBanner = new InMobiBanner(iVar.a(), iVar.d);
                                    inMobiBanner.setExtras(iVar.f);
                                    inMobiBanner.setKeywords(iVar.e);
                                    inMobiBanner.setMonetizationContext(iVar.l());
                                    bannerAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiBanner);
                                }
                            }
                        } catch (Exception e) {
                            InMobiBanner.a;
                            new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                        }
                    }
                }

                public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                    try {
                        if (iVar instanceof p) {
                            WeakReference weakReference = (WeakReference) InMobiBanner.q.get(iVar);
                            if (weakReference != null) {
                                InMobiBanner.q.remove(iVar);
                                BannerAdRequestListener bannerAdRequestListener = (BannerAdRequestListener) weakReference.get();
                                if (bannerAdRequestListener != null) {
                                    bannerAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                }
                            }
                        }
                    } catch (Exception e) {
                        InMobiBanner.a;
                        new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                    }
                }
            };
            try {
                bi a2 = bi.a(inMobiAdRequest.a, inMobiAdRequest.f, "banner", inMobiAdRequest.e);
                a2.f = inMobiAdRequest.b;
                p a3 = p.a(context.getApplicationContext(), a2, null, 2);
                a3.f = inMobiAdRequest.f;
                a3.a(inMobiAdRequest.b);
                a3.e = inMobiAdRequest.e;
                StringBuilder sb = new StringBuilder();
                sb.append(inMobiAdRequest.c);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(inMobiAdRequest.d);
                a3.A = sb.toString();
                a3.q = r0;
                a3.n = true;
                q.put(a3, new WeakReference(bannerAdRequestListener));
                a3.q();
            } catch (Exception e2) {
                new StringBuilder("SDK encountered unexpected error in requestAd").append(e2.getMessage());
            }
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Please provide positive width and height for banner. Ignoring request");
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void setTrcCollector(j jVar) {
        this.r = jVar;
    }

    @NonNull
    private j getAdUnitTRCCollector() {
        if (this.r == null) {
            this.r = new k(this.h);
        }
        return this.r;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void a(String str, String str2) {
        getAdUnitTRCCollector().a(this.w, str, str2);
    }

    public final JSONObject getAdMetaInfo() {
        if (this.i) {
            p pVar = this.g;
            if (pVar != null) {
                return pVar.i;
            }
        }
        return new JSONObject();
    }

    /* access modifiers changed from: private */
    public boolean f() {
        p pVar = this.h;
        if (pVar == null) {
            return false;
        }
        if (this.p != 0) {
            int i2 = pVar.g.c;
            if (SystemClock.elapsedRealtime() - this.p < ((long) (i2 * 1000))) {
                p pVar2 = this.h;
                InMobiAdRequestStatus inMobiAdRequestStatus = new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST);
                StringBuilder sb = new StringBuilder("Ad cannot be refreshed before ");
                sb.append(i2);
                sb.append(" seconds");
                pVar2.a(inMobiAdRequestStatus.setCustomMessage(sb.toString()), false);
                InternalLogLevel internalLogLevel = InternalLogLevel.ERROR;
                String str = a;
                StringBuilder sb2 = new StringBuilder("Ad cannot be refreshed before ");
                sb2.append(i2);
                sb2.append(" seconds (Placement Id = ");
                sb2.append(this.h.d);
                sb2.append(")");
                Logger.a(internalLogLevel, str, sb2.toString());
                return false;
            }
        }
        this.p = SystemClock.elapsedRealtime();
        return true;
    }

    public final void setExtras(Map<String, String> map) {
        if (this.i && this.t != null) {
            this.e.f = map;
            this.f.f = map;
        }
    }

    public final void setKeywords(String str) {
        if (this.i && this.t != null) {
            this.e.e = str;
            this.f.e = str;
        }
    }

    @Deprecated
    public final void setListener(BannerAdListener bannerAdListener) {
        if (bannerAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the banner.");
        } else {
            this.b = bannerAdListener;
        }
    }

    public final void setListener(BannerAdEventListener bannerAdEventListener) {
        if (bannerAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the banner.");
        } else {
            this.c = bannerAdEventListener;
        }
    }

    public final void setEnableAutoRefresh(boolean z) {
        try {
            if (this.i && this.k != z) {
                this.k = z;
                if (this.k) {
                    b();
                } else {
                    h();
                }
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to setup auto-refresh on the ad; SDK encountered an unexpected error");
            new StringBuilder("Setting up auto-refresh failed with unexpected error: ").append(e2.getMessage());
        }
    }

    public final void setRefreshInterval(int i2) {
        try {
            if (this.i && this.h != null) {
                if (i2 < this.h.g.c) {
                    i2 = this.h.g.c;
                }
                this.j = i2;
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to set refresh interval for the ad; SDK encountered an unexpected error");
            new StringBuilder("Setting refresh interval failed with unexpected error: ").append(e2.getMessage());
        }
    }

    public final void setAnimationType(AnimationType animationType) {
        if (this.i) {
            this.o = animationType;
        }
    }

    public final void disableHardwareAcceleration() {
        this.u = true;
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        try {
            super.onAttachedToWindow();
            if (this.e != null) {
                com.inmobi.commons.a.a.a(getContext(), (ActivityLifecycleCallbacks) this.e);
            }
            if (this.f != null) {
                com.inmobi.commons.a.a.a(getContext(), (ActivityLifecycleCallbacks) this.f);
            }
            if (this.i) {
                g();
                if (!a()) {
                    getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                        public final void onGlobalLayout() {
                            try {
                                InMobiBanner.this.m = c.b(InMobiBanner.this.getMeasuredWidth());
                                InMobiBanner.this.n = c.b(InMobiBanner.this.getMeasuredHeight());
                                if (InMobiBanner.this.a()) {
                                    if (VERSION.SDK_INT >= 16) {
                                        InMobiBanner.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                        return;
                                    }
                                    InMobiBanner.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                }
                            } catch (Exception e) {
                                Logger.a(InternalLogLevel.ERROR, InMobiBanner.a, "InMobiBanner$1.onGlobalLayout() handler threw unexpected error");
                                InMobiBanner.a;
                                new StringBuilder("InMobiBanner$1.onGlobalLayout() handler threw unexpected error: ").append(e.getMessage());
                            }
                        }
                    });
                }
                b();
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner#onAttachedToWindow() handler threw unexpected error");
            new StringBuilder("InMobiBanner#onAttachedToWindow() handler threw unexpected error: ").append(e2.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
            if (this.i) {
                h();
            }
            if (this.e != null) {
                this.e.S();
            }
            if (this.f != null) {
                this.f.S();
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner.onDetachedFromWindow() handler threw unexpected error");
            new StringBuilder("InMobiBanner.onDetachedFromWindow() handler threw unexpected error: ").append(e2.getMessage());
        }
    }

    private void g() {
        if (getLayoutParams() != null) {
            this.m = c.b(getLayoutParams().width);
            this.n = c.b(getLayoutParams().height);
        }
    }

    public final void setBannerSize(int i2, int i3) {
        if (this.i) {
            this.m = i2;
            this.n = i3;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        return this.m > 0 && this.n > 0;
    }

    /* access modifiers changed from: 0000 */
    public final String getFrameSizeString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.m);
        sb.append(AvidJSONUtil.KEY_X);
        sb.append(this.n);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final void onVisibilityChanged(@NonNull View view, int i2) {
        try {
            super.onVisibilityChanged(view, i2);
            if (this.i) {
                if (i2 == 0) {
                    b();
                    return;
                }
                h();
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner$1.onVisibilityChanged() handler threw unexpected error");
            new StringBuilder("InMobiBanner$1.onVisibilityChanged() handler threw unexpected error: ").append(e2.getMessage());
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
            if (this.i) {
                if (z) {
                    b();
                    return;
                }
                h();
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiBanner$1.onWindowFocusChanged() handler threw unexpected error");
            new StringBuilder("InMobiBanner$1.onWindowFocusChanged() handler threw unexpected error: ").append(e2.getMessage());
        }
    }

    private void a(Context context, @NonNull bi biVar) {
        p pVar = this.e;
        if (pVar == null || this.f == null) {
            this.e = p.a(context, biVar, this.w, 0);
            this.f = p.a(context, biVar, this.w, 0);
            this.h = this.e;
            this.j = this.h.g.d;
        } else {
            pVar.a(context);
            this.f.a(context);
            boolean z = context instanceof Activity;
            this.e.a(z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            this.f.a(z ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER);
        }
        this.l = new q(this);
        p pVar2 = this.e;
        pVar2.n = false;
        this.f.n = false;
        if (this.u) {
            pVar2.O();
            this.f.O();
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void b() {
        if (isShown() && hasWindowFocus() && this.h != null) {
            q qVar = this.l;
            if (qVar != null) {
                qVar.removeMessages(1);
            }
            if (!(this.h.a == 1 || this.h.a == 2)) {
                p pVar = this.g;
                if ((pVar == null || pVar.a != 8) && this.k) {
                    q qVar2 = this.l;
                    if (qVar2 != null) {
                        qVar2.sendEmptyMessageDelayed(1, (long) (this.j * 1000));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        q qVar = this.l;
        if (qVar != null) {
            qVar.removeMessages(1);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void setAnimateAndDisplayAd(boolean z) {
        this.v = z;
    }

    public final void resume() {
        try {
            if (this.g != null && this.s == null) {
                this.g.R();
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not resume ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in resuming ad; ").append(e2.getMessage());
        }
    }

    public final void pause() {
        try {
            if (this.g != null && this.s == null) {
                this.g.Q();
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not pause ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in pausing ad; ").append(e2.getMessage());
        }
    }

    public final String getCreativeId() {
        if (this.i) {
            p pVar = this.g;
            if (pVar != null) {
                return pVar.x;
            }
        }
        return "";
    }

    @VisibleForTesting
    static boolean c() {
        return Message.obtain() == null;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void setClientCallbackHandler(b bVar) {
        this.d = bVar;
    }

    static /* synthetic */ void a(InMobiBanner inMobiBanner, a aVar) {
        p pVar = inMobiBanner.g;
        if (pVar == null) {
            inMobiBanner.g = inMobiBanner.e;
            inMobiBanner.h = inMobiBanner.f;
        } else if (pVar.equals(inMobiBanner.e)) {
            inMobiBanner.g = inMobiBanner.f;
            inMobiBanner.h = inMobiBanner.e;
        } else if (inMobiBanner.g.equals(inMobiBanner.f)) {
            inMobiBanner.g = inMobiBanner.e;
            inMobiBanner.h = inMobiBanner.f;
        }
        try {
            if (inMobiBanner.v) {
                AnimationType animationType = inMobiBanner.o;
                float width = (float) inMobiBanner.getWidth();
                float height = (float) inMobiBanner.getHeight();
                Animation animation = 0;
                if (animationType == AnimationType.ANIMATION_ALPHA) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(BitmapDescriptorFactory.HUE_RED, 0.5f);
                    alphaAnimation.setDuration(1000);
                    alphaAnimation.setFillAfter(false);
                    alphaAnimation.setInterpolator(new DecelerateInterpolator());
                    animation = alphaAnimation;
                } else if (animationType == AnimationType.ROTATE_HORIZONTAL_AXIS) {
                    a aVar2 = new a(width / 2.0f, height / 2.0f);
                    aVar2.setDuration(500);
                    aVar2.setFillAfter(false);
                    aVar2.setInterpolator(new AccelerateInterpolator());
                    animation = aVar2;
                } else if (animationType == AnimationType.ROTATE_VERTICAL_AXIS) {
                    b bVar = new b(width / 2.0f, height / 2.0f);
                    bVar.setDuration(500);
                    bVar.setFillAfter(false);
                    bVar.setInterpolator(new AccelerateInterpolator());
                    animation = bVar;
                }
                if (inMobiBanner.getContext() != null) {
                    if (inMobiBanner.g != null) {
                        RenderView renderView = (RenderView) inMobiBanner.g.j();
                        if (renderView != null) {
                            ca viewableAd = renderView.getViewableAd();
                            if (inMobiBanner.g.z) {
                                renderView.a();
                            }
                            ViewGroup viewGroup = (ViewGroup) renderView.getParent();
                            LayoutParams layoutParams = new LayoutParams(-1, -1);
                            View a2 = viewableAd.a();
                            viewableAd.a(new View[0]);
                            if (inMobiBanner.h != null) {
                                inMobiBanner.h.Q();
                            }
                            if (viewGroup == null) {
                                inMobiBanner.addView(a2, layoutParams);
                            } else {
                                viewGroup.removeAllViews();
                                viewGroup.addView(a2, layoutParams);
                            }
                            inMobiBanner.h.v();
                        }
                    }
                }
                if (animation != 0) {
                    inMobiBanner.startAnimation(animation);
                }
            }
            aVar.a();
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Unexpected error while displaying Banner Ad.");
            new StringBuilder("Unexpected error while displaying Banner Ad : ").append(e2.getMessage());
        }
    }
}
