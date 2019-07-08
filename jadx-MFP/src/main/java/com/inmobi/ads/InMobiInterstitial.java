package com.inmobi.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.ac;
import com.inmobi.ads.bi;
import com.inmobi.ads.i.b;
import com.inmobi.ads.i.e;
import com.inmobi.ads.listeners.InterstitialAdEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.myfitnesspal.shared.model.v2.MfpImage.Status;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public final class InMobiInterstitial {
    /* access modifiers changed from: private */
    public static final String a = "InMobiInterstitial";
    /* access modifiers changed from: private */
    public static ConcurrentHashMap<ac, ArrayList<WeakReference<InterstitialAdRequestListener>>> m = new ConcurrentHashMap<>(2, 0.9f, 3);
    @Nullable
    private ac b;
    /* access modifiers changed from: private */
    public a c;
    /* access modifiers changed from: private */
    public InterstitialAdListener2 d;
    /* access modifiers changed from: private */
    public InterstitialAdEventListener e;
    private Context f;
    /* access modifiers changed from: private */
    public long g;
    private boolean h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public Map<String, String> j;
    private boolean k;
    private boolean l;
    /* access modifiers changed from: private */
    public String n;
    private j o;
    /* access modifiers changed from: private */
    public JSONObject p;
    @NonNull
    private final b q;

    /* renamed from: com.inmobi.ads.InMobiInterstitial$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[StatusCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
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
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.MISSING_REQUIRED_DEPENDENCIES     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.InMobiInterstitial.AnonymousClass3.<clinit>():void");
        }
    }

    public interface InterstitialAdListener2 {
        void onAdDismissed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayed(InMobiInterstitial inMobiInterstitial);

        void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial);

        void onAdReceived(InMobiInterstitial inMobiInterstitial);

        void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdWillDisplay(InMobiInterstitial inMobiInterstitial);

        void onUserLeftApplication(InMobiInterstitial inMobiInterstitial);
    }

    public interface InterstitialAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiInterstitial inMobiInterstitial);
    }

    @VisibleForTesting
    static final class a extends Handler {
        private WeakReference<InMobiInterstitial> a;

        a(InMobiInterstitial inMobiInterstitial) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(inMobiInterstitial);
        }

        public final void handleMessage(Message message) {
            InMobiInterstitial inMobiInterstitial = (InMobiInterstitial) this.a.get();
            if (inMobiInterstitial != null) {
                try {
                    Map map = null;
                    switch (message.what) {
                        case 1:
                            InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                return;
                            }
                            break;
                        case 2:
                            if (message.getData().getBoolean(Status.AVAILABLE)) {
                                if (inMobiInterstitial.e != null) {
                                    inMobiInterstitial.e.onAdReceived(inMobiInterstitial);
                                }
                                if (inMobiInterstitial.d != null) {
                                    inMobiInterstitial.d.onAdReceived(inMobiInterstitial);
                                    return;
                                }
                            }
                            break;
                        case 3:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdLoadSucceeded(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdLoadSucceeded(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 4:
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRewardsUnlocked(inMobiInterstitial, map);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdRewardActionCompleted(inMobiInterstitial, map);
                                return;
                            }
                            break;
                        case 5:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDisplayFailed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDisplayFailed(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 6:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdWillDisplay(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdWillDisplay(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 7:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDisplayed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDisplayed(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 9:
                            if (message.obj != null) {
                                map = (Map) message.obj;
                            }
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdClicked(inMobiInterstitial, map);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdInteraction(inMobiInterstitial, map);
                                return;
                            }
                            break;
                        case 10:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDismissed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDismissed(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 11:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onUserLeftApplication(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onUserLeftApplication(inMobiInterstitial);
                                return;
                            }
                            break;
                        case 12:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRequestPayloadCreated((byte[]) message.obj);
                                return;
                            }
                            break;
                        case 13:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                                return;
                            }
                            break;
                        default:
                            InMobiInterstitial.a;
                            break;
                    }
                } catch (Exception e) {
                    Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                    InMobiInterstitial.a;
                    new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
                }
            }
        }
    }

    /* synthetic */ InMobiInterstitial(Context context, long j2, byte b2) {
        this(context, j2);
    }

    @Deprecated
    public InMobiInterstitial(Context context, long j2, InterstitialAdListener2 interstitialAdListener2) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = new b() {
            public final void a(boolean z) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                Bundle bundle = new Bundle();
                bundle.putBoolean(Status.AVAILABLE, z);
                obtain.setData(bundle);
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            public final void a(i iVar) {
                InMobiInterstitial.this.a("AR", "");
                InMobiInterstitial.this.n = iVar.x;
                InMobiInterstitial.this.p = iVar.i;
                InMobiInterstitial.this.c.sendEmptyMessage(3);
            }

            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiInterstitial.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                        InMobiInterstitial.this.a("ART", "LoadInProgress");
                        break;
                    case 3:
                        InMobiInterstitial.this.a("ART", "ReloadNotPermitted");
                        break;
                    case 4:
                        InMobiInterstitial.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiInterstitial.this.a("ART", "MissingRequiredDependencies");
                        break;
                    default:
                        InMobiInterstitial.this.a("AF", "");
                        break;
                }
                if (!InMobiInterstitial.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiInterstitial.this.c.sendMessage(obtain);
                }
            }

            public final void b() {
                InMobiInterstitial.this.c.sendEmptyMessage(5);
            }

            public final void c() {
                InMobiInterstitial.this.c.sendEmptyMessage(6);
            }

            public final void d() {
                InMobiInterstitial.this.a("AVD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(7);
            }

            public final void e() {
                InMobiInterstitial.this.a("AVCD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(10);
                com.inmobi.ads.c.b d = com.inmobi.ads.c.b.d();
                bi a2 = bi.a(InMobiInterstitial.this.g, InMobiInterstitial.this.j, "int", InMobiInterstitial.this.i);
                if (com.inmobi.ads.c.b.b.c(d.c).a) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(a2) {
                        final /* synthetic */ bi a;

                        {
                            this.a = r2;
                        }

                        public final void run() {
                            try {
                                b.a(b.this);
                                if (!a.a.containsKey(this.a)) {
                                    b.d;
                                    StringBuilder sb = new StringBuilder("preLoadAdUnit. pid:");
                                    sb.append(this.a.a);
                                    sb.append(" tp:");
                                    sb.append(this.a.b);
                                    if (this.a.c == null && this.a.b != null) {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("tp", this.a.b);
                                        this.a.c = hashMap;
                                    }
                                    a aVar = new a(this.a);
                                    b.g.add(aVar);
                                    ac a2 = com.inmobi.ads.ac.a.a(com.inmobi.commons.a.a.b(), this.a, aVar);
                                    a2.e = this.a.d;
                                    a2.f = this.a.c;
                                    a2.n = true;
                                    a.a.put(this.a, a2);
                                    a2.e(aVar);
                                }
                            } catch (Exception e) {
                                b.d;
                                new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                }
            }

            public final void a(@NonNull Map<Object, Object> map) {
                InMobiInterstitial.this.a("AVCL", "");
                Message obtain = Message.obtain();
                obtain.what = 9;
                obtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            public final void f() {
                InMobiInterstitial.this.c.sendEmptyMessage(11);
            }

            public final void b(@NonNull Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void a(byte[] bArr) {
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = bArr;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an Interstitial ad");
        } else if (interstitialAdListener2 == null) {
            Logger.a(InternalLogLevel.ERROR, a, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to create Interstitial ad with null context object.");
        } else {
            this.h = true;
            this.f = context.getApplicationContext();
            this.g = j2;
            this.d = interstitialAdListener2;
            this.c = new a(this);
        }
    }

    public InMobiInterstitial(Context context, long j2, InterstitialAdEventListener interstitialAdEventListener) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = new b() {
            public final void a(boolean z) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                Bundle bundle = new Bundle();
                bundle.putBoolean(Status.AVAILABLE, z);
                obtain.setData(bundle);
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            public final void a(i iVar) {
                InMobiInterstitial.this.a("AR", "");
                InMobiInterstitial.this.n = iVar.x;
                InMobiInterstitial.this.p = iVar.i;
                InMobiInterstitial.this.c.sendEmptyMessage(3);
            }

            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiInterstitial.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                        InMobiInterstitial.this.a("ART", "LoadInProgress");
                        break;
                    case 3:
                        InMobiInterstitial.this.a("ART", "ReloadNotPermitted");
                        break;
                    case 4:
                        InMobiInterstitial.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiInterstitial.this.a("ART", "MissingRequiredDependencies");
                        break;
                    default:
                        InMobiInterstitial.this.a("AF", "");
                        break;
                }
                if (!InMobiInterstitial.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiInterstitial.this.c.sendMessage(obtain);
                }
            }

            public final void b() {
                InMobiInterstitial.this.c.sendEmptyMessage(5);
            }

            public final void c() {
                InMobiInterstitial.this.c.sendEmptyMessage(6);
            }

            public final void d() {
                InMobiInterstitial.this.a("AVD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(7);
            }

            public final void e() {
                InMobiInterstitial.this.a("AVCD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(10);
                com.inmobi.ads.c.b d = com.inmobi.ads.c.b.d();
                bi a2 = bi.a(InMobiInterstitial.this.g, InMobiInterstitial.this.j, "int", InMobiInterstitial.this.i);
                if (com.inmobi.ads.c.b.b.c(d.c).a) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(a2) {
                        final /* synthetic */ bi a;

                        {
                            this.a = r2;
                        }

                        public final void run() {
                            try {
                                b.a(b.this);
                                if (!a.a.containsKey(this.a)) {
                                    b.d;
                                    StringBuilder sb = new StringBuilder("preLoadAdUnit. pid:");
                                    sb.append(this.a.a);
                                    sb.append(" tp:");
                                    sb.append(this.a.b);
                                    if (this.a.c == null && this.a.b != null) {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("tp", this.a.b);
                                        this.a.c = hashMap;
                                    }
                                    a aVar = new a(this.a);
                                    b.g.add(aVar);
                                    ac a2 = com.inmobi.ads.ac.a.a(com.inmobi.commons.a.a.b(), this.a, aVar);
                                    a2.e = this.a.d;
                                    a2.f = this.a.c;
                                    a2.n = true;
                                    a.a.put(this.a, a2);
                                    a2.e(aVar);
                                }
                            } catch (Exception e) {
                                b.d;
                                new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                }
            }

            public final void a(@NonNull Map<Object, Object> map) {
                InMobiInterstitial.this.a("AVCL", "");
                Message obtain = Message.obtain();
                obtain.what = 9;
                obtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            public final void f() {
                InMobiInterstitial.this.c.sendEmptyMessage(11);
            }

            public final void b(@NonNull Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void a(byte[] bArr) {
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = bArr;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an Interstitial ad");
        } else if (interstitialAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to create Interstitial ad with null context object.");
        } else {
            this.h = true;
            this.f = context.getApplicationContext();
            this.g = j2;
            this.e = interstitialAdEventListener;
            this.c = new a(this);
        }
    }

    private InMobiInterstitial(Context context, long j2) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = new b() {
            public final void a(boolean z) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                Bundle bundle = new Bundle();
                bundle.putBoolean(Status.AVAILABLE, z);
                obtain.setData(bundle);
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            public final void a(i iVar) {
                InMobiInterstitial.this.a("AR", "");
                InMobiInterstitial.this.n = iVar.x;
                InMobiInterstitial.this.p = iVar.i;
                InMobiInterstitial.this.c.sendEmptyMessage(3);
            }

            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiInterstitial.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                        InMobiInterstitial.this.a("ART", "LoadInProgress");
                        break;
                    case 3:
                        InMobiInterstitial.this.a("ART", "ReloadNotPermitted");
                        break;
                    case 4:
                        InMobiInterstitial.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiInterstitial.this.a("ART", "MissingRequiredDependencies");
                        break;
                    default:
                        InMobiInterstitial.this.a("AF", "");
                        break;
                }
                if (!InMobiInterstitial.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiInterstitial.this.c.sendMessage(obtain);
                }
            }

            public final void b() {
                InMobiInterstitial.this.c.sendEmptyMessage(5);
            }

            public final void c() {
                InMobiInterstitial.this.c.sendEmptyMessage(6);
            }

            public final void d() {
                InMobiInterstitial.this.a("AVD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(7);
            }

            public final void e() {
                InMobiInterstitial.this.a("AVCD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(10);
                com.inmobi.ads.c.b d = com.inmobi.ads.c.b.d();
                bi a2 = bi.a(InMobiInterstitial.this.g, InMobiInterstitial.this.j, "int", InMobiInterstitial.this.i);
                if (com.inmobi.ads.c.b.b.c(d.c).a) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(a2) {
                        final /* synthetic */ bi a;

                        {
                            this.a = r2;
                        }

                        public final void run() {
                            try {
                                b.a(b.this);
                                if (!a.a.containsKey(this.a)) {
                                    b.d;
                                    StringBuilder sb = new StringBuilder("preLoadAdUnit. pid:");
                                    sb.append(this.a.a);
                                    sb.append(" tp:");
                                    sb.append(this.a.b);
                                    if (this.a.c == null && this.a.b != null) {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("tp", this.a.b);
                                        this.a.c = hashMap;
                                    }
                                    a aVar = new a(this.a);
                                    b.g.add(aVar);
                                    ac a2 = com.inmobi.ads.ac.a.a(com.inmobi.commons.a.a.b(), this.a, aVar);
                                    a2.e = this.a.d;
                                    a2.f = this.a.c;
                                    a2.n = true;
                                    a.a.put(this.a, a2);
                                    a2.e(aVar);
                                }
                            } catch (Exception e) {
                                b.d;
                                new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                }
            }

            public final void a(@NonNull Map<Object, Object> map) {
                InMobiInterstitial.this.a("AVCL", "");
                Message obtain = Message.obtain();
                obtain.what = 9;
                obtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            public final void f() {
                InMobiInterstitial.this.c.sendEmptyMessage(11);
            }

            public final void b(@NonNull Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void a(byte[] bArr) {
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = bArr;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(obtain);
            }
        };
        this.h = true;
        this.f = context;
        this.g = j2;
        this.c = new a(this);
    }

    @Deprecated
    public final void setInterstitialAdListener(InterstitialAdListener2 interstitialAdListener2) {
        this.d = interstitialAdListener2;
    }

    public final void setListener(InterstitialAdEventListener interstitialAdEventListener) {
        if (interstitialAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the interstitial.");
        } else {
            this.e = interstitialAdEventListener;
        }
    }

    public final void setKeywords(String str) {
        if (this.h) {
            this.i = str;
        }
    }

    private boolean a(boolean z) {
        if (!this.h) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiInterstitial is not initialized, your call is ignored.");
        } else if (!z ? this.e == null : this.d == null && this.e == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, your call is ignored.");
        } else if (this.f != null) {
            return true;
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Context supplied is null, your call is ignored.");
        }
        return false;
    }

    public final void getSignals() {
        if (a(false)) {
            if (this.b == null) {
                this.b = com.inmobi.ads.ac.a.b(this.f, bi.a(this.g, this.j, "int", this.i), this.q);
            }
            a("ARR", "");
            a(this.b);
            this.b.o();
        }
    }

    public final void load(byte[] bArr) {
        if (a(false)) {
            ac acVar = this.b;
            if (acVar == null) {
                Logger.a(InternalLogLevel.ERROR, a, "Either getSignals() is not called or InMobiInterstitial is not initialized, your call is ignored.");
                return;
            }
            this.l = true;
            if (acVar.d(this.q)) {
                acVar.a(bArr);
            }
        }
    }

    public final void load() {
        try {
            if (a(true)) {
                bi a2 = bi.a(this.g, this.j, "int", this.i);
                com.inmobi.ads.c.b d2 = com.inmobi.ads.c.b.d();
                i iVar = null;
                if (!com.inmobi.ads.c.b.b.c(d2.c).a) {
                    StringBuilder sb = new StringBuilder("No cached ad unit found as config is disabled. pid:");
                    sb.append(a2.a);
                    sb.append(" tp:");
                    sb.append(a2.b);
                } else {
                    d2.a(a2);
                    i iVar2 = (i) com.inmobi.ads.c.b.a.get(a2);
                    if (iVar2 == null) {
                        StringBuilder sb2 = new StringBuilder("No cached ad unit found for pid:");
                        sb2.append(a2.a);
                        sb2.append(" tp:");
                        sb2.append(a2.b);
                    } else if (iVar2.h()) {
                        StringBuilder sb3 = new StringBuilder("Expired cached ad unit found for pid:");
                        sb3.append(a2.a);
                        sb3.append(" tp:");
                        sb3.append(a2.b);
                        iVar2.v();
                        com.inmobi.ads.c.b.a.remove(a2);
                        com.inmobi.ads.c.b.a("AdUnitExpired", iVar2);
                    } else {
                        StringBuilder sb4 = new StringBuilder("Cached ad unit found for pid:");
                        sb4.append(a2.a);
                        sb4.append(" tp:");
                        sb4.append(a2.b);
                        com.inmobi.ads.c.b.a((i) com.inmobi.ads.c.b.a.remove(a2));
                        iVar = iVar2;
                    }
                }
                this.l = true;
                if (iVar != null) {
                    this.b = (ac) iVar;
                } else {
                    this.b = com.inmobi.ads.ac.a.a(this.f, a2, this.q);
                }
                a("ARR", "");
                a(this.b);
                ac acVar = this.b;
                InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                String str = a;
                StringBuilder sb5 = new StringBuilder("Fetching an Interstitial ad for placement id: ");
                sb5.append(acVar.d);
                Logger.a(internalLogLevel, str, sb5.toString());
                this.n = "";
                this.p = this.b.b;
                acVar.a(this.q);
                acVar.e(this.q);
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, InterstitialAdRequestListener interstitialAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (interstitialAdRequestListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null InterstitialAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null InMobiAdRequest. Ignoring request");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null Context. Ignoring request");
        } else {
            ac acVar = null;
            try {
                RecyclerView.class.getName();
                Picasso.class.getName();
                AnonymousClass1 r1 = new e() {
                    public final void a(@NonNull i iVar) {
                        try {
                            if (iVar instanceof ac) {
                                ArrayList arrayList = (ArrayList) InMobiInterstitial.m.get(iVar);
                                if (arrayList != null) {
                                    InMobiInterstitial.m.remove(iVar);
                                    Handler handler = new Handler(Looper.getMainLooper());
                                    Iterator it = arrayList.iterator();
                                    while (it.hasNext()) {
                                        WeakReference weakReference = (WeakReference) it.next();
                                        if (weakReference != null) {
                                            final InterstitialAdRequestListener interstitialAdRequestListener = (InterstitialAdRequestListener) weakReference.get();
                                            if (interstitialAdRequestListener != null) {
                                                final InMobiInterstitial inMobiInterstitial = new InMobiInterstitial(iVar.a(), iVar.d, 0);
                                                inMobiInterstitial.setKeywords(iVar.e);
                                                inMobiInterstitial.setExtras(iVar.f);
                                                handler.post(new Runnable() {
                                                    public final void run() {
                                                        try {
                                                            interstitialAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiInterstitial);
                                                        } catch (Exception unused) {
                                                            Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            InMobiInterstitial.a;
                            new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                        }
                    }

                    public final void a(@NonNull i iVar, @NonNull final InMobiAdRequestStatus inMobiAdRequestStatus) {
                        try {
                            if (iVar instanceof ac) {
                                ArrayList arrayList = (ArrayList) InMobiInterstitial.m.get(iVar);
                                if (arrayList != null && arrayList.size() > 0) {
                                    WeakReference weakReference = (WeakReference) arrayList.get(arrayList.size() - 1);
                                    if (weakReference != null) {
                                        arrayList.remove(weakReference);
                                        if (arrayList.size() == 0) {
                                            InMobiInterstitial.m.remove(iVar);
                                        }
                                        final InterstitialAdRequestListener interstitialAdRequestListener = (InterstitialAdRequestListener) weakReference.get();
                                        if (interstitialAdRequestListener != null) {
                                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                public final void run() {
                                                    try {
                                                        interstitialAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                                    } catch (Exception unused) {
                                                        Logger.a(InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            InMobiInterstitial.a;
                            new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                        }
                    }
                };
                try {
                    Iterator it = m.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ac acVar2 = (ac) ((Entry) it.next()).getKey();
                        if (acVar2 != null && acVar2.d == inMobiAdRequest.a) {
                            acVar = acVar2;
                            break;
                        }
                    }
                    if (acVar != null) {
                        ArrayList arrayList = (ArrayList) m.get(acVar);
                        arrayList.add(new WeakReference(interstitialAdRequestListener));
                        ac a2 = a(context, inMobiAdRequest, (e) r1);
                        m.put(a2, arrayList);
                        a2.q();
                        return;
                    }
                    ac a3 = a(context, inMobiAdRequest, (e) r1);
                    a3.q = r1;
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new WeakReference(interstitialAdRequestListener));
                    m.put(a3, arrayList2);
                    a3.q();
                } catch (Exception e2) {
                    new StringBuilder("SDK encountered unexpected error in requestAd").append(e2.getMessage());
                }
            } catch (NoClassDefFoundError unused) {
                Logger.a(InternalLogLevel.ERROR, a, "Some of the dependency libraries for Interstitial not found. Ignoring request");
                interstitialAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES), null);
            }
        }
    }

    private static ac a(Context context, InMobiAdRequest inMobiAdRequest, e eVar) {
        ac a2 = com.inmobi.ads.ac.a.a(context.getApplicationContext(), bi.a(inMobiAdRequest.a, inMobiAdRequest.f, "int", inMobiAdRequest.e), null);
        a2.f = inMobiAdRequest.f;
        a2.e = inMobiAdRequest.e;
        a2.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        a2.n = true;
        a2.q = eVar;
        return a2;
    }

    @VisibleForTesting
    private void a(@NonNull ac acVar) {
        acVar.a(this.f);
        acVar.f = this.j;
        acVar.e = this.i;
        acVar.a(MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        if (this.k) {
            AdContainer j2 = acVar.j();
            if (j2 != null) {
                acVar.y = true;
                j2.a();
            }
        }
        acVar.n = false;
    }

    public final void show() {
        try {
            if (!this.l) {
                Logger.a(InternalLogLevel.ERROR, a, "load() must be called before trying to show the ad");
                return;
            }
            if (this.h && this.b != null) {
                a("AVR", "");
                this.b.f(this.q);
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Unable to show ad; SDK encountered an unexpected error");
            new StringBuilder("Show failed with unexpected error: ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    @Deprecated
    public final void show(int i2, int i3) {
        show();
    }

    public final boolean isReady() {
        if (this.h) {
            ac acVar = this.b;
            if (acVar != null && acVar.O()) {
                return true;
            }
        }
        return false;
    }

    public final JSONObject getAdMetaInfo() {
        JSONObject jSONObject = this.p;
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    public final String getCreativeId() {
        return this.n;
    }

    public final void setExtras(Map<String, String> map) {
        if (this.h) {
            this.j = map;
        }
    }

    public final void disableHardwareAcceleration() {
        if (this.h) {
            this.k = true;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.o == null) {
            this.o = new k(this.b);
        }
        this.o.a(this.q, str, str2);
    }

    static /* synthetic */ boolean c() {
        return Message.obtain() == null;
    }
}
