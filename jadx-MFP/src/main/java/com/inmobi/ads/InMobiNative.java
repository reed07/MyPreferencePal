package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.i.b;
import com.inmobi.ads.i.e;
import com.inmobi.ads.listeners.NativeAdEventListener;
import com.inmobi.ads.listeners.VideoEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public final class InMobiNative {
    /* access modifiers changed from: private */
    public static final String a = "InMobiNative";
    /* access modifiers changed from: private */
    public static ConcurrentHashMap<aj, WeakReference<NativeAdRequestListener>> j = new ConcurrentHashMap<>(5, 0.9f, 3);
    /* access modifiers changed from: private */
    public a b;
    /* access modifiers changed from: private */
    public NativeAdListener c;
    /* access modifiers changed from: private */
    public NativeAdEventListener d;
    /* access modifiers changed from: private */
    @Nullable
    public VideoEventListener e;
    /* access modifiers changed from: private */
    public aj f;
    private String g;
    private Map<String, String> h;
    private long i;
    private Downloader k;
    private WeakReference<View> l;
    private boolean m;
    private boolean n;
    private WeakReference<Context> o;
    /* access modifiers changed from: private */
    public LockScreenListener p;
    private j q;
    private final b r;

    /* renamed from: com.inmobi.ads.InMobiNative$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[StatusCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
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
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.REPETITIVE_LOAD     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.InMobiNative.AnonymousClass3.<clinit>():void");
        }
    }

    public final class Downloader {
        public static final int STATE_DOWNLOADED = 1;
        public static final int STATE_DOWNLOADING = 0;
        public static final int STATE_ERROR = 2;
        public static final int STATE_INITIALIZING = -1;
        public static final int STATE_UNINITIALIZED = -2;

        public Downloader() {
        }

        public final int getDownloadProgress() {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "InMobiNative is not initialized.Ignoring getDownloadProgress()");
                return 0;
            }
            try {
                if (InMobiNative.this.f != null) {
                    AdContainer j = InMobiNative.this.f.j();
                    return (j == null || j.getApkDownloader() != null) ? 0 : 0;
                }
            } catch (Exception unused) {
                Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "Encountered unexpected error in getting download progress");
            }
            return 0;
        }

        public final int getDownloadStatus() {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "InMobiNative is not initialized.Ignoring getDownloadStatus()");
                return -2;
            }
            try {
                if (InMobiNative.this.f != null) {
                    AdContainer j = InMobiNative.this.f.j();
                    return (j == null || j.getApkDownloader() != null) ? -2 : -2;
                }
            } catch (Exception unused) {
                Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "Encountered unexpected error in getting download progress");
            }
            return -2;
        }
    }

    public interface LockScreenListener {
        void onActionRequired(InMobiNative inMobiNative);
    }

    public interface NativeAdListener {
        void onAdClicked(@NonNull InMobiNative inMobiNative);

        void onAdFullScreenDismissed(InMobiNative inMobiNative);

        void onAdFullScreenDisplayed(InMobiNative inMobiNative);

        void onAdFullScreenWillDisplay(InMobiNative inMobiNative);

        void onAdImpressed(@NonNull InMobiNative inMobiNative);

        void onAdLoadFailed(@NonNull InMobiNative inMobiNative, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(@NonNull InMobiNative inMobiNative);

        void onAdStatusChanged(@NonNull InMobiNative inMobiNative);

        void onMediaPlaybackComplete(@NonNull InMobiNative inMobiNative);

        void onUserSkippedMedia(@NonNull InMobiNative inMobiNative);

        void onUserWillLeaveApplication(InMobiNative inMobiNative);
    }

    public interface NativeAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiNative inMobiNative);
    }

    @VisibleForTesting
    static final class a extends Handler {
        private WeakReference<InMobiNative> a;

        a(InMobiNative inMobiNative) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(inMobiNative);
        }

        public final void handleMessage(Message message) {
            InMobiNative inMobiNative = (InMobiNative) this.a.get();
            if (inMobiNative == null) {
                Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "Lost reference to InMobiNative! callback cannot be given");
                return;
            }
            try {
                switch (message.what) {
                    case 1:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdLoadSucceeded(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdLoadSucceeded(inMobiNative);
                            return;
                        }
                        break;
                    case 2:
                        InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdLoadFailed(inMobiNative, inMobiAdRequestStatus);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdLoadFailed(inMobiNative, inMobiAdRequestStatus);
                            return;
                        }
                        break;
                    case 3:
                        if (inMobiNative.p != null) {
                            inMobiNative.p.onActionRequired(inMobiNative);
                        }
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenWillDisplay(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenWillDisplay(inMobiNative);
                            return;
                        }
                        break;
                    case 4:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenDisplayed(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenDisplayed(inMobiNative);
                            return;
                        }
                        break;
                    case 5:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenDismissed(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenDismissed(inMobiNative);
                            return;
                        }
                        break;
                    case 6:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdImpressed(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdImpressed(inMobiNative);
                            return;
                        }
                        break;
                    case 7:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdClicked(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdClicked(inMobiNative);
                            return;
                        }
                        break;
                    case 8:
                        if (inMobiNative.p != null) {
                            inMobiNative.p.onActionRequired(inMobiNative);
                        }
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onUserWillLeaveApplication(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onUserWillLeaveApplication(inMobiNative);
                            return;
                        }
                        break;
                    case 9:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onVideoCompleted(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onMediaPlaybackComplete(inMobiNative);
                            return;
                        }
                        break;
                    case 10:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdStatusChanged(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdStatusChanged(inMobiNative);
                            return;
                        }
                        break;
                    case 11:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onVideoSkipped(inMobiNative);
                            return;
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onUserSkippedMedia(inMobiNative);
                            return;
                        }
                        break;
                    case 12:
                        byte[] bArr = (byte[]) message.obj;
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onRequestPayloadCreated(bArr);
                            return;
                        }
                        break;
                    case 13:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                            return;
                        }
                        break;
                    case 14:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onAudioStateChanged(inMobiNative, ((Boolean) message.obj).booleanValue());
                            return;
                        }
                        break;
                    default:
                        InMobiNative.a;
                        break;
                }
            } catch (Exception e) {
                Logger.a(InternalLogLevel.ERROR, InMobiNative.a, "Publisher handler caused unexpected error");
                InMobiNative.a;
                new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
            }
        }
    }

    /* synthetic */ InMobiNative(Context context, bi biVar, byte b2) {
        this(context, biVar);
    }

    @Deprecated
    public InMobiNative(Context context, long j2, NativeAdListener nativeAdListener) {
        this.n = true;
        this.r = new b() {
            public final void a() {
                InMobiNative.this.a("AR", "");
                InMobiNative.this.b.sendEmptyMessage(1);
            }

            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiNative.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                    case 3:
                        InMobiNative.this.a("ART", "LoadInProgress");
                        break;
                    case 4:
                        InMobiNative.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiNative.this.a("ART", "MissingRequiredDependencies");
                        break;
                    case 6:
                        InMobiNative.this.a("ART", "ReloadNotPermitted");
                        break;
                    default:
                        InMobiNative.this.a("AF", "");
                        break;
                }
                if (!InMobiNative.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiNative.this.b.sendMessage(obtain);
                }
            }

            public final void b() {
                InMobiNative.a;
            }

            public final void c() {
                InMobiNative.this.b.sendEmptyMessage(3);
            }

            public final void d() {
                InMobiNative.this.a("AVE", "");
                InMobiNative.this.b.sendEmptyMessage(4);
            }

            public final void e() {
                InMobiNative.this.a("AVCO", "");
                InMobiNative.this.b.sendEmptyMessage(5);
            }

            public final void a(@NonNull Map<Object, Object> map) {
                InMobiNative.this.a("AVCL", "");
                InMobiNative.this.b.sendEmptyMessage(7);
            }

            public final void f() {
                InMobiNative.this.b.sendEmptyMessage(8);
            }

            public final void g() {
                InMobiNative.this.b.sendEmptyMessage(6);
            }

            public final void h() {
                InMobiNative.this.b.sendEmptyMessage(9);
            }

            public final void j() {
                InMobiNative.this.b.sendEmptyMessage(11);
            }

            /* access modifiers changed from: 0000 */
            public final void a(byte[] bArr) {
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = bArr;
                InMobiNative.this.b.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(boolean z) {
                Message obtain = Message.obtain();
                obtain.what = 14;
                obtain.obj = Boolean.valueOf(z);
                InMobiNative.this.b.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Native ad");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
        } else if (nativeAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, the Native ad cannot be created.");
        } else {
            this.i = j2;
            this.o = new WeakReference<>(context);
            this.c = nativeAdListener;
            this.k = new Downloader();
            this.b = new a(this);
        }
    }

    public InMobiNative(Context context, long j2, NativeAdEventListener nativeAdEventListener) {
        this.n = true;
        this.r = new b() {
            public final void a() {
                InMobiNative.this.a("AR", "");
                InMobiNative.this.b.sendEmptyMessage(1);
            }

            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiNative.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                    case 3:
                        InMobiNative.this.a("ART", "LoadInProgress");
                        break;
                    case 4:
                        InMobiNative.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiNative.this.a("ART", "MissingRequiredDependencies");
                        break;
                    case 6:
                        InMobiNative.this.a("ART", "ReloadNotPermitted");
                        break;
                    default:
                        InMobiNative.this.a("AF", "");
                        break;
                }
                if (!InMobiNative.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiNative.this.b.sendMessage(obtain);
                }
            }

            public final void b() {
                InMobiNative.a;
            }

            public final void c() {
                InMobiNative.this.b.sendEmptyMessage(3);
            }

            public final void d() {
                InMobiNative.this.a("AVE", "");
                InMobiNative.this.b.sendEmptyMessage(4);
            }

            public final void e() {
                InMobiNative.this.a("AVCO", "");
                InMobiNative.this.b.sendEmptyMessage(5);
            }

            public final void a(@NonNull Map<Object, Object> map) {
                InMobiNative.this.a("AVCL", "");
                InMobiNative.this.b.sendEmptyMessage(7);
            }

            public final void f() {
                InMobiNative.this.b.sendEmptyMessage(8);
            }

            public final void g() {
                InMobiNative.this.b.sendEmptyMessage(6);
            }

            public final void h() {
                InMobiNative.this.b.sendEmptyMessage(9);
            }

            public final void j() {
                InMobiNative.this.b.sendEmptyMessage(11);
            }

            /* access modifiers changed from: 0000 */
            public final void a(byte[] bArr) {
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = bArr;
                InMobiNative.this.b.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(boolean z) {
                Message obtain = Message.obtain();
                obtain.what = 14;
                obtain.obj = Boolean.valueOf(z);
                InMobiNative.this.b.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Native ad");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
        } else if (nativeAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, the Native ad cannot be created.");
        } else {
            this.i = j2;
            this.o = new WeakReference<>(context);
            this.d = nativeAdEventListener;
            this.k = new Downloader();
            this.b = new a(this);
        }
    }

    private InMobiNative(Context context, bi biVar) {
        this.n = true;
        this.r = new b() {
            public final void a() {
                InMobiNative.this.a("AR", "");
                InMobiNative.this.b.sendEmptyMessage(1);
            }

            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiNative.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                    case 3:
                        InMobiNative.this.a("ART", "LoadInProgress");
                        break;
                    case 4:
                        InMobiNative.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiNative.this.a("ART", "MissingRequiredDependencies");
                        break;
                    case 6:
                        InMobiNative.this.a("ART", "ReloadNotPermitted");
                        break;
                    default:
                        InMobiNative.this.a("AF", "");
                        break;
                }
                if (!InMobiNative.c()) {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiNative.this.b.sendMessage(obtain);
                }
            }

            public final void b() {
                InMobiNative.a;
            }

            public final void c() {
                InMobiNative.this.b.sendEmptyMessage(3);
            }

            public final void d() {
                InMobiNative.this.a("AVE", "");
                InMobiNative.this.b.sendEmptyMessage(4);
            }

            public final void e() {
                InMobiNative.this.a("AVCO", "");
                InMobiNative.this.b.sendEmptyMessage(5);
            }

            public final void a(@NonNull Map<Object, Object> map) {
                InMobiNative.this.a("AVCL", "");
                InMobiNative.this.b.sendEmptyMessage(7);
            }

            public final void f() {
                InMobiNative.this.b.sendEmptyMessage(8);
            }

            public final void g() {
                InMobiNative.this.b.sendEmptyMessage(6);
            }

            public final void h() {
                InMobiNative.this.b.sendEmptyMessage(9);
            }

            public final void j() {
                InMobiNative.this.b.sendEmptyMessage(11);
            }

            /* access modifiers changed from: 0000 */
            public final void a(byte[] bArr) {
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = bArr;
                InMobiNative.this.b.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(obtain);
            }

            /* access modifiers changed from: 0000 */
            public final void b(boolean z) {
                Message obtain = Message.obtain();
                obtain.what = 14;
                obtain.obj = Boolean.valueOf(z);
                InMobiNative.this.b.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an InMobiNative ad");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
        } else {
            this.f = aj.a(context, biVar, this.r, 0);
            this.i = biVar.a;
            this.o = new WeakReference<>(context);
            this.b = new a(this);
        }
    }

    @Deprecated
    public final void setNativeAdListener(NativeAdListener nativeAdListener) {
        this.c = nativeAdListener;
    }

    public final void setListener(NativeAdEventListener nativeAdEventListener) {
        if (nativeAdEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the native.");
        } else {
            this.d = nativeAdEventListener;
        }
    }

    public final void setVideoEventListener(VideoEventListener videoEventListener) {
        if (videoEventListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please pass a non-null listener to the native.");
        } else {
            this.e = videoEventListener;
        }
    }

    private boolean a(boolean z) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized, your call is ignored.");
        } else if (!z ? this.d != null : !(this.r == null && this.d == null)) {
            WeakReference<Context> weakReference = this.o;
            if (weakReference != null && weakReference.get() != null) {
                return true;
            }
            Logger.a(InternalLogLevel.ERROR, a, "Context supplied is null, your call is ignored.");
        } else {
            Logger.a(InternalLogLevel.ERROR, a, "Listener supplied is null, your call is ignored.");
        }
        return false;
    }

    public final void getSignals() {
        if (a(false)) {
            d();
            a("ARR", "");
            aj ajVar = this.f;
            if (ajVar != null) {
                ajVar.o();
            }
        }
    }

    public final void load(byte[] bArr) {
        if (a(false)) {
            aj ajVar = this.f;
            if (ajVar == null) {
                Logger.a(InternalLogLevel.ERROR, a, "Either getSignals() is not called or InMobiNative is not initialized, your call is ignored.");
                return;
            }
            ajVar.a(bArr);
        }
    }

    @VisibleForTesting
    private void d() {
        WeakReference<Context> weakReference = this.o;
        Context context = weakReference == null ? null : (Context) weakReference.get();
        if (context != null) {
            aj ajVar = this.f;
            if (ajVar == null) {
                bi a2 = bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g);
                a2.f = context instanceof Activity ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER;
                this.f = aj.a(context, a2, this.r, 0);
            } else {
                ajVar.a(context);
                this.f.a(context instanceof Activity ? MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            }
            aj ajVar2 = this.f;
            ajVar2.n = false;
            ajVar2.e = this.g;
            ajVar2.f = this.h;
        }
    }

    public final void load() {
        try {
            if (a(true)) {
                if (this.m) {
                    a("ARR", "");
                    this.r.a(new InMobiAdRequestStatus(StatusCode.REPETITIVE_LOAD));
                    Logger.a(InternalLogLevel.ERROR, a, "You can call load() on an instance of InMobiNative only once if the ad request has been successful. Ignoring InMobiNative.load()");
                    return;
                }
                d();
                if (this.f != null) {
                    a("ARR", "");
                    bi a2 = bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g);
                    a2.f = this.f.l();
                    this.f.n();
                    com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a(a2);
                }
            }
        } catch (Exception e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not load ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in loading ad; ").append(e2.getMessage());
        }
    }

    public final void load(Context context) {
        if (a(true)) {
            this.o = new WeakReference<>(context);
            load();
        }
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, NativeAdRequestListener nativeAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
        } else if (nativeAdRequestListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null NativeAdRequestListener. Ignoring request");
        } else if (inMobiAdRequest == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null InMobiAdRequest. Ignoring request");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please supply a non null Context. Ignoring request");
        } else {
            try {
                RecyclerView.class.getName();
                Picasso.class.getName();
                final bi a2 = bi.a(inMobiAdRequest.a, inMobiAdRequest.f, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, inMobiAdRequest.e);
                a2.f = inMobiAdRequest.b;
                AnonymousClass1 r2 = new e() {
                    public final void a(@NonNull i iVar) {
                        if (iVar instanceof aj) {
                            try {
                                com.inmobi.ads.c.a.a.remove(a2);
                                WeakReference weakReference = (WeakReference) InMobiNative.j.get(iVar);
                                if (weakReference != null) {
                                    InMobiNative.j.remove(iVar);
                                    NativeAdRequestListener nativeAdRequestListener = (NativeAdRequestListener) weakReference.get();
                                    if (nativeAdRequestListener != null) {
                                        bi a2 = bi.a(iVar.d, iVar.f, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, iVar.e);
                                        a2.f = iVar.l();
                                        InMobiNative inMobiNative = new InMobiNative(iVar.a(), a2, 0);
                                        inMobiNative.setKeywords(iVar.e);
                                        inMobiNative.setExtras(iVar.f);
                                        nativeAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.NO_ERROR), inMobiNative);
                                    }
                                }
                            } catch (Exception e) {
                                InMobiNative.a;
                                new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                            }
                        }
                    }

                    public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                        if (iVar instanceof aj) {
                            try {
                                com.inmobi.ads.c.a.a.remove(a2);
                                WeakReference weakReference = (WeakReference) InMobiNative.j.get(iVar);
                                if (weakReference != null) {
                                    InMobiNative.j.remove(iVar);
                                    NativeAdRequestListener nativeAdRequestListener = (NativeAdRequestListener) weakReference.get();
                                    if (nativeAdRequestListener != null) {
                                        nativeAdRequestListener.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                    }
                                }
                            } catch (Exception e) {
                                InMobiNative.a;
                                new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                            }
                        }
                    }
                };
                try {
                    aj a3 = aj.a(context.getApplicationContext(), a2, null, 2);
                    a3.f = inMobiAdRequest.f;
                    a3.e = inMobiAdRequest.e;
                    a3.q = r2;
                    a3.n = true;
                    j.put(a3, new WeakReference(nativeAdRequestListener));
                    a3.q();
                } catch (Exception e2) {
                    new StringBuilder("SDK encountered unexpected error in requestAd").append(e2.getMessage());
                }
            } catch (NoClassDefFoundError unused) {
                Logger.a(InternalLogLevel.ERROR, a, "Some of the dependency libraries for InMobiNative not found. Ignoring request");
                nativeAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES), null);
            }
        }
    }

    public final void showOnLockScreen(LockScreenListener lockScreenListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling showOnLockScreen.");
        } else if (lockScreenListener == null) {
            Logger.a(InternalLogLevel.ERROR, a, "Please provided non null LockScreenListener. Ignoring showOnLockScreen");
        } else {
            WeakReference<Context> weakReference = this.o;
            if (weakReference == null || weakReference.get() == null) {
                Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Provided context is null. Ignoring showOnLockScreen");
                return;
            }
            try {
                if (this.f == null) {
                    this.f = aj.a((Context) this.o.get(), bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g), this.r, 0);
                }
                this.f.A = true;
                this.p = lockScreenListener;
            } catch (Exception unused) {
                Logger.a(InternalLogLevel.ERROR, a, "SDK encountered unexpected error in showOnLockScreen");
            }
        }
    }

    public final void takeAction() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "Please initialize the SDK before calling takeAction.");
            return;
        }
        try {
            if (this.f != null) {
                aj ajVar = this.f;
                if (ajVar.o != null) {
                    ajVar.o.r();
                }
                return;
            }
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring takeAction");
        } catch (Exception unused) {
            Logger.a(InternalLogLevel.ERROR, a, "SDK encountered unexpected error in takeAction");
        }
    }

    public final void pause() {
        try {
            if (this.f != null) {
                aj ajVar = this.f;
                if (ajVar.a == 5 && !(ajVar.a() instanceof Activity)) {
                    AdContainer j2 = ajVar.j();
                    if (j2 != null) {
                        ((ah) j2).q();
                    }
                }
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not pause ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in pausing ad; ").append(e2.getMessage());
        }
    }

    public final void resume() {
        try {
            if (this.f != null) {
                aj ajVar = this.f;
                if (ajVar.a == 5 && !(ajVar.a() instanceof Activity)) {
                    AdContainer j2 = ajVar.j();
                    if (j2 != null) {
                        ((ah) j2).p();
                    }
                }
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not resume ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in resuming ad; ").append(e2.getMessage());
        }
    }

    public final View getPrimaryViewOfWidth(Context context, View view, ViewGroup viewGroup, int i2) {
        View view2;
        try {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(InternalLogLevel.ERROR, a, "InMobiSdk is not initialized. Ignoring InMobiNative.getPrimaryView()");
                return null;
            } else if (context == null) {
                Logger.a(InternalLogLevel.ERROR, a, "View can not be rendered using null context");
                return null;
            } else if (this.f == null) {
                Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring InMobiNative.getPrimaryView()");
                return null;
            } else {
                this.o = new WeakReference<>(context);
                this.f.a(context);
                aj ajVar = this.f;
                boolean z = this.n;
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    Logger.a(InternalLogLevel.ERROR, InMobiNative.class.getSimpleName(), "Please ensure that you call getPrimaryView() on the UI thread");
                    view2 = null;
                } else if (!com.inmobi.commons.core.utilities.b.e.e()) {
                    ajVar.O();
                    view2 = null;
                } else if (ajVar.P() || ajVar.a == 7) {
                    ah ahVar = ajVar.o;
                    if (ahVar != null) {
                        ahVar.u = ajVar.A;
                        ahVar.s = i2;
                        ahVar.t = z;
                        ca viewableAd = ahVar.getViewableAd();
                        view2 = viewableAd.a(view, viewGroup, true);
                        ajVar.z = new WeakReference<>(view2);
                        if (ajVar.r != 0 || ajVar.t) {
                            viewableAd.a(new View[0]);
                        } else {
                            ajVar.s.post(new Runnable(viewableAd) {
                                final /* synthetic */ ca a;

                                {
                                    this.a = r2;
                                }

                                public final void run() {
                                    this.a.a(new View[0]);
                                }
                            });
                        }
                    } else {
                        view2 = null;
                    }
                } else {
                    Logger.a(InternalLogLevel.ERROR, aj.y, "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling getPrimaryView().");
                    if (ajVar.z != null) {
                        View view3 = (View) ajVar.z.get();
                        if (view3 != null) {
                            View view4 = new View(com.inmobi.commons.a.a.b());
                            view4.setLayoutParams(view3.getLayoutParams());
                            view2 = view4;
                        } else {
                            view2 = null;
                        }
                    } else {
                        view2 = null;
                    }
                }
                this.l = new WeakReference<>(view2);
                View view5 = (View) this.l.get();
                a("AVR", "");
                if (view5 == null) {
                    if (this.f.P()) {
                        a("AVFB", "");
                    } else {
                        a("AVRR", "");
                    }
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "PrimaryViewInflationFailed", new HashMap());
                    return null;
                }
                a("AVD", "");
                this.m = true;
                return view5;
            }
        } catch (Exception e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not pause ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in pausing ad; ").append(e2.getMessage());
            return null;
        }
    }

    @Deprecated
    public final View getPrimaryViewOfWidth(View view, ViewGroup viewGroup, int i2) {
        WeakReference<Context> weakReference = this.o;
        if (weakReference != null && weakReference.get() != null) {
            return getPrimaryViewOfWidth((Context) this.o.get(), view, viewGroup, i2);
        }
        Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized or provided context is null.");
        return null;
    }

    public final JSONObject getCustomAdContent() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setExtras()");
            return null;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ao aoVar = (ao) j2.getDataModel();
                    if (aoVar != null) {
                        return aoVar.i.a;
                    }
                }
                return null;
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not get the ad customJson ; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return null;
    }

    public final String getAdTitle() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdTitle()");
            return null;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ao aoVar = (ao) j2.getDataModel();
                    if (aoVar != null) {
                        return aoVar.i.b.a;
                    }
                }
                return null;
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not get the ad title; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return null;
    }

    public final String getAdDescription() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdDescription()");
            return null;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ao aoVar = (ao) j2.getDataModel();
                    if (aoVar != null) {
                        return aoVar.i.b.b;
                    }
                }
                return null;
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not get the description; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return null;
    }

    public final String getAdIconUrl() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdIconUrl()");
            return null;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ao aoVar = (ao) j2.getDataModel();
                    if (aoVar != null) {
                        return aoVar.i.b.c;
                    }
                }
                return null;
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not get the iconUrl; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return null;
    }

    public final String getAdLandingPageUrl() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdLandingPageUrl()");
            return null;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ao aoVar = (ao) j2.getDataModel();
                    if (aoVar != null) {
                        return aoVar.i.b.f;
                    }
                }
                return null;
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not get the adLandingPageUrl; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return null;
    }

    public final String getAdCtaText() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdCtaText()");
            return null;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ao aoVar = (ao) j2.getDataModel();
                    if (aoVar != null) {
                        return aoVar.i.b.d;
                    }
                }
                return null;
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not get the ctaText; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return null;
    }

    public final float getAdRating() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdRating()");
            return BitmapDescriptorFactory.HUE_RED;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ao aoVar = (ao) j2.getDataModel();
                    if (aoVar != null) {
                        return aoVar.i.b.e;
                    }
                }
                return BitmapDescriptorFactory.HUE_RED;
            }
        } catch (Exception e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not get rating; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in getAdRating(); ").append(e2.getMessage());
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    public final boolean isAppDownload() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.isAppDownload()");
            return false;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ao aoVar = (ao) j2.getDataModel();
                    if (aoVar != null) {
                        return aoVar.i.b.g;
                    }
                }
                return false;
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not get isAppDownload; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return false;
    }

    public final void reportAdClickAndOpenLandingPage() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.reportAdClickAndOpenLandingPage()");
            return;
        }
        try {
            if (this.f != null) {
                AdContainer j2 = this.f.j();
                if (j2 != null) {
                    ah ahVar = (ah) j2;
                    ao h2 = ahVar.h();
                    if (h2 != null) {
                        ahVar.a((View) null, h2.i.c);
                        ahVar.a(h2.i.c, true);
                    }
                }
            }
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "reportAdClickAndOpenLandingPage failed; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    public final boolean isReady() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.isReady()");
            return false;
        }
        aj ajVar = this.f;
        if (ajVar == null || !ajVar.P()) {
            return false;
        }
        return true;
    }

    public final JSONObject getAdMetaInfo() {
        if (com.inmobi.commons.a.a.a()) {
            aj ajVar = this.f;
            if (ajVar != null) {
                return ajVar.i;
            }
        }
        return new JSONObject();
    }

    public final void setExtras(Map<String, String> map) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setExtras()");
            return;
        }
        try {
            if (this.f != null) {
                this.f.f = map;
            }
            this.h = map;
        } catch (Exception e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not set extras; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in setting extras ").append(e2.getMessage());
        }
    }

    public final void setKeywords(String str) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setKeywords()");
            return;
        }
        try {
            if (this.f != null) {
                this.f.e = str;
            }
            this.g = str;
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Could not set keywords on Native ad; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            new StringBuilder("SDK encountered unexpected error in setting keywords; ").append(e2.getMessage());
        }
    }

    public final void destroy() {
        try {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring InMobiNative.destroy()");
            }
            if (this.b != null) {
                this.b.removeMessages(0);
            }
            View view = this.l == null ? null : (View) this.l.get();
            if (view != null) {
                ((ViewGroup) view).removeAllViews();
            }
            if (this.f != null) {
                this.f.O();
            }
            if (this.q != null) {
                this.q = null;
            }
            this.f = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.k = null;
            this.m = false;
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Failed to destroy ad; SDK encountered an unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    public final void setDownloaderEnabled(boolean z) {
        this.n = z;
    }

    public final Downloader getDownloader() {
        try {
            if (com.inmobi.commons.a.a.a()) {
                return this.k;
            }
            Logger.a(InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring InMobiNative.getDownloader()");
            return null;
        } catch (Exception e2) {
            Logger.a(InternalLogLevel.ERROR, a, "Failed to get Downloader; SDK encountered an unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return null;
        }
    }

    public final String getCreativeId() {
        if (com.inmobi.commons.a.a.a()) {
            aj ajVar = this.f;
            if (ajVar != null) {
                return ajVar.x;
            }
        }
        return "";
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.q == null) {
            this.q = new k(this.f);
        }
        this.q.a(this.r, str, str2);
    }

    static /* synthetic */ boolean c() {
        return Message.obtain() == null;
    }
}
