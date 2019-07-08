package com.inmobi.rendering.a;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.inmobi.a.n;
import com.inmobi.ads.c.e;
import com.inmobi.commons.core.configs.h;
import com.inmobi.commons.core.network.NetworkError;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.WebViewNetworkTask;
import com.inmobi.commons.core.network.WebViewNetworkTask.NetworkTaskWebView;
import com.inmobi.commons.core.utilities.g;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: ClickManager */
public class c implements com.inmobi.commons.core.configs.b.c {
    /* access modifiers changed from: private */
    public static final String a = "c";
    private static c b;
    private static final Object c = new Object();
    private static ExecutorService d;
    private static a e;
    private static HandlerThread f;
    /* access modifiers changed from: private */
    public static List<a> g = new ArrayList();
    /* access modifiers changed from: private */
    public static b h;
    /* access modifiers changed from: private */
    public static AtomicBoolean i = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static e j;
    private static final Object k = new Object();
    /* access modifiers changed from: private */
    public long l = 0;
    /* access modifiers changed from: private */
    public final d m = new d() {
        public final void a(a aVar) {
            if (aVar != null) {
                c.a;
                StringBuilder sb = new StringBuilder("Processing click (");
                sb.append(aVar.b);
                sb.append(") completed");
                c.h;
                b.a(aVar);
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", aVar.b);
                    hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - c.this.l));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "PingLatency", hashMap);
                } catch (Exception e) {
                    c.a;
                    StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                    sb2.append(e.getMessage());
                    sb2.append(")");
                }
            }
        }

        public final void b(a aVar) {
            if (aVar != null) {
                c.a;
                StringBuilder sb = new StringBuilder("Pinging click (");
                sb.append(aVar.b);
                sb.append(") failed! Updating retry counts and timestamps ...");
                c.a(aVar);
                c.this.b();
            }
        }
    };

    /* compiled from: ClickManager */
    final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            Message message2 = message;
            try {
                switch (message2.what) {
                    case 1:
                        h hVar = new h();
                        String str = null;
                        com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) hVar, (com.inmobi.commons.core.configs.b.c) null);
                        if (!hVar.g) {
                            c.h;
                            int i = c.j.e;
                            int i2 = c.j.b;
                            ArrayList arrayList = new ArrayList();
                            com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
                            if (a2.a("click") != 0) {
                                if (-1 != i) {
                                    str = Integer.toString(i);
                                }
                                String str2 = str;
                                StringBuilder sb = new StringBuilder("ts < ");
                                sb.append(System.currentTimeMillis() - ((long) i2));
                                com.inmobi.commons.core.d.b bVar = a2;
                                List<ContentValues> a3 = a2.a("click", b.a, null, null, "ts", sb.toString(), "ts ASC ", str2);
                                bVar.b();
                                for (ContentValues a4 : a3) {
                                    arrayList.add(b.a(a4));
                                }
                            }
                            c.g = arrayList;
                            if (c.g.isEmpty()) {
                                c.h;
                                if (b.a()) {
                                    c.i.set(false);
                                    return;
                                }
                                Message obtain = Message.obtain();
                                obtain.what = 1;
                                sendMessageDelayed(obtain, (long) (c.j.b * 1000));
                                return;
                            }
                            a aVar = (a) c.g.get(0);
                            Message obtain2 = Message.obtain();
                            obtain2.what = aVar.h ? 3 : 2;
                            obtain2.obj = aVar;
                            long currentTimeMillis = System.currentTimeMillis() - aVar.d;
                            if (currentTimeMillis < ((long) (c.j.b * 1000))) {
                                sendMessageDelayed(obtain2, ((long) (c.j.b * 1000)) - currentTimeMillis);
                                return;
                            } else {
                                sendMessage(obtain2);
                                return;
                            }
                        }
                        break;
                    case 2:
                        if (!com.inmobi.commons.core.utilities.d.a()) {
                            c.i.set(false);
                            c.i();
                            return;
                        }
                        a aVar2 = (a) message2.obj;
                        if (aVar2.f == 0) {
                            a(aVar2, 1);
                            return;
                        } else if (aVar2.a(c.j.f)) {
                            a(aVar2, 2);
                            return;
                        } else {
                            int i3 = (c.j.a - aVar2.f) + 1;
                            if (i3 == 0) {
                                c.a;
                                StringBuilder sb2 = new StringBuilder("Pinging click (");
                                sb2.append(aVar2.b);
                                sb2.append(") over HTTP");
                            } else {
                                c.a;
                                StringBuilder sb3 = new StringBuilder("Retry attempt #");
                                sb3.append(i3);
                                sb3.append(" for click (");
                                sb3.append(aVar2.b);
                                sb3.append(") over HTTP");
                            }
                            new C0049c(new d() {
                                public final void a(a aVar) {
                                    a.a(a.this, aVar);
                                }

                                public final void b(a aVar) {
                                    c.a;
                                    StringBuilder sb = new StringBuilder("Pinging click (");
                                    sb.append(aVar.b);
                                    sb.append(") via HTTP failed ...");
                                    c.a(aVar);
                                    a.b(a.this, aVar);
                                }
                            }).a(aVar2);
                            return;
                        }
                    case 3:
                        if (!com.inmobi.commons.core.utilities.d.a()) {
                            c.i.set(false);
                            c.i();
                            return;
                        }
                        a aVar3 = (a) message2.obj;
                        if (aVar3.f == 0) {
                            a(aVar3, 1);
                            return;
                        } else if (aVar3.a(c.j.f)) {
                            a(aVar3, 2);
                            return;
                        } else {
                            int i4 = (c.j.a - aVar3.f) + 1;
                            if (i4 == 0) {
                                c.a;
                                StringBuilder sb4 = new StringBuilder("Pinging click (");
                                sb4.append(aVar3.b);
                                sb4.append(") in WebView");
                            } else {
                                c.a;
                                StringBuilder sb5 = new StringBuilder("Retry attempt #");
                                sb5.append(i4);
                                sb5.append(" for click (");
                                sb5.append(aVar3.b);
                                sb5.append(") using WebView");
                            }
                            new b(new d() {
                                public final void a(a aVar) {
                                    a.a(a.this, aVar);
                                }

                                public final void b(a aVar) {
                                    c.a;
                                    StringBuilder sb = new StringBuilder("Pinging click (");
                                    sb.append(aVar.b);
                                    sb.append(") via WebView failed ...");
                                    c.a(aVar);
                                    a.b(a.this, aVar);
                                }
                            }).a(aVar3);
                            return;
                        }
                    case 4:
                        break;
                    case 5:
                        a aVar4 = (a) message2.obj;
                        HashMap hashMap = new HashMap();
                        hashMap.put("pingUrl", aVar4.b);
                        switch (message2.arg1) {
                            case 1:
                                hashMap.put("errorCode", "MaxRetryCountReached");
                                break;
                            case 2:
                                hashMap.put("errorCode", "ExpiredClick");
                                break;
                        }
                        try {
                            com.inmobi.commons.core.e.b.a();
                            com.inmobi.commons.core.e.b.a("ads", "PingDiscarded", hashMap);
                            break;
                        } catch (Exception e) {
                            c.a;
                            StringBuilder sb6 = new StringBuilder("Error in submitting telemetry event : (");
                            sb6.append(e.getMessage());
                            sb6.append(")");
                            break;
                        }
                        break;
                }
                a aVar5 = (a) message2.obj;
                c.a;
                StringBuilder sb7 = new StringBuilder("Processing click (");
                sb7.append(aVar5.b);
                sb7.append(") completed");
                c.h;
                b.a(aVar5);
                c.g.remove(aVar5);
                if (c.g.isEmpty()) {
                    c.h;
                    if (b.a()) {
                        c.a;
                        c.i.set(false);
                        return;
                    }
                    Message obtain3 = Message.obtain();
                    obtain3.what = 1;
                    sendMessage(obtain3);
                    return;
                }
                a aVar6 = (a) c.g.get(0);
                Message obtain4 = Message.obtain();
                obtain4.what = aVar6.h ? 3 : 2;
                obtain4.obj = aVar6;
                sendMessage(obtain4);
            } catch (Exception e2) {
                c.a;
                new StringBuilder("SDK encountered unexpected error in processing ping; ").append(e2.getMessage());
            }
        }

        private void a(a aVar, int i) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = aVar;
            obtain.arg1 = i;
            sendMessage(obtain);
        }

        static /* synthetic */ void a(a aVar, a aVar2) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            obtain.obj = aVar2;
            aVar.sendMessage(obtain);
        }

        static /* synthetic */ void b(a aVar, a aVar2) {
            int indexOf = c.g.indexOf(aVar2);
            if (-1 != indexOf) {
                a aVar3 = (a) c.g.get(indexOf == c.g.size() + -1 ? 0 : indexOf + 1);
                Message obtain = Message.obtain();
                obtain.what = aVar3.h ? 3 : 2;
                obtain.obj = aVar3;
                if (System.currentTimeMillis() - aVar3.d < ((long) (c.j.b * 1000))) {
                    aVar.sendMessageDelayed(obtain, (long) (c.j.b * 1000));
                    return;
                }
                aVar.sendMessage(obtain);
            }
        }
    }

    /* compiled from: ClickManager */
    static final class b {
        d a;

        public b(d dVar) {
            this.a = dVar;
        }

        public final void a(final a aVar) {
            aVar.g.set(false);
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public final void run() {
                    com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpConstants.METHOD_GET, aVar.b);
                    HashMap b2 = c.c(aVar);
                    if (!b2.isEmpty()) {
                        cVar.a((Map<String, String>) b2);
                    }
                    WebViewNetworkTask webViewNetworkTask = new WebViewNetworkTask(cVar, new WebViewClient() {
                        AtomicBoolean a = new AtomicBoolean(false);
                        boolean b;
                        boolean c;

                        public final void onPageStarted(final WebView webView, String str, Bitmap bitmap) {
                            this.c = true;
                            this.b = false;
                            new Thread(new Runnable() {
                                public final void run() {
                                    try {
                                        Thread.sleep((long) (c.j.c * 1000));
                                    } catch (InterruptedException unused) {
                                    }
                                    if (!AnonymousClass1.this.a.get()) {
                                        c.a;
                                        StringBuilder sb = new StringBuilder("Pinging click (");
                                        sb.append(aVar.b);
                                        sb.append(") via WebView timed out!");
                                        aVar.g.set(true);
                                        handler.post(new Runnable() {
                                            public final void run() {
                                                try {
                                                    NetworkTaskWebView networkTaskWebView = (NetworkTaskWebView) webView;
                                                    if (networkTaskWebView != null && !networkTaskWebView.a) {
                                                        webView.stopLoading();
                                                    }
                                                } catch (Throwable th) {
                                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(th));
                                                }
                                            }
                                        });
                                        b.this.a.b(aVar);
                                    }
                                }
                            }).start();
                        }

                        public final void onPageFinished(WebView webView, String str) {
                            boolean z = true;
                            this.a.set(true);
                            if (this.b || aVar.g.get()) {
                                z = false;
                            } else {
                                b.this.a.a(aVar);
                            }
                            if (!this.c) {
                                String str2 = z ? "PageNotStartedWithSuccess" : "PageNotStartedWithError";
                                try {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("url", str);
                                    com.inmobi.commons.core.e.b.a();
                                    com.inmobi.commons.core.e.b.a("ads", str2, hashMap);
                                } catch (Exception e) {
                                    c.a;
                                    StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                                    sb.append(e.getMessage());
                                    sb.append(")");
                                }
                            }
                        }

                        @TargetApi(22)
                        public final void onReceivedError(WebView webView, int i, String str, String str2) {
                            this.b = true;
                            b.this.a.b(aVar);
                        }

                        @TargetApi(23)
                        public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                            this.b = true;
                            b.this.a.b(aVar);
                        }

                        @TargetApi(23)
                        public final void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                            this.b = true;
                            b.this.a.b(aVar);
                        }

                        public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                            if (VERSION.SDK_INT < 21 || aVar.i || webResourceRequest.getUrl().toString().equals(aVar.b)) {
                                return false;
                            }
                            return true;
                        }

                        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                            return !aVar.i && !str.equals(aVar.b);
                        }
                    });
                    try {
                        webViewNetworkTask.c = new NetworkTaskWebView(com.inmobi.commons.a.a.b());
                        webViewNetworkTask.c.setWebViewClient(webViewNetworkTask.b);
                        webViewNetworkTask.c.getSettings().setJavaScriptEnabled(true);
                        webViewNetworkTask.c.getSettings().setCacheMode(2);
                        webViewNetworkTask.c.loadUrl(webViewNetworkTask.a.e(), webViewNetworkTask.a.d());
                    } catch (Exception e) {
                        new StringBuilder("SDK encountered unexpected error in WebViewNetworkTask.execute() method; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    /* renamed from: com.inmobi.rendering.a.c$c reason: collision with other inner class name */
    /* compiled from: ClickManager */
    static final class C0049c {
        private d a;

        public C0049c(d dVar) {
            this.a = dVar;
        }

        public final void a(a aVar) {
            try {
                com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpConstants.METHOD_GET, aVar.b);
                HashMap b = c.c(aVar);
                if (!b.isEmpty()) {
                    cVar.a((Map<String, String>) b);
                }
                cVar.u = false;
                cVar.b(aVar.c);
                cVar.t = aVar.i;
                cVar.r = c.j.c * 1000;
                cVar.s = c.j.c * 1000;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                com.inmobi.commons.core.network.d a2 = new com.inmobi.commons.core.network.e(cVar).a();
                try {
                    n.a().a(cVar.g());
                    n.a().b(a2.c());
                    n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
                } catch (Exception e) {
                    c.a;
                    new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                }
                if (a2.a()) {
                    ErrorCode errorCode = a2.b.a;
                    if (ErrorCode.GENERIC_HTTP_2XX == errorCode) {
                        this.a.a(aVar);
                    } else if (aVar.i || !(ErrorCode.HTTP_SEE_OTHER == errorCode || ErrorCode.HTTP_MOVED_TEMP == errorCode)) {
                        d dVar = this.a;
                        NetworkError networkError = a2.b;
                        dVar.b(aVar);
                    } else {
                        this.a.a(aVar);
                    }
                } else {
                    this.a.a(aVar);
                }
            } catch (Exception e2) {
                c.a;
                new StringBuilder("SDK encountered unexpected error in executing ping over HTTP; ").append(e2.getMessage());
                d dVar2 = this.a;
                new NetworkError(ErrorCode.UNKNOWN_ERROR, "Unknown error");
                dVar2.b(aVar);
            }
        }
    }

    /* compiled from: ClickManager */
    interface d {
        void a(a aVar);

        void b(a aVar);
    }

    public static c a() {
        c cVar = b;
        if (cVar == null) {
            synchronized (c) {
                cVar = b;
                if (cVar == null) {
                    cVar = new c();
                    b = cVar;
                }
            }
        }
        return cVar;
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        j = ((com.inmobi.ads.c) aVar).h;
    }

    public final void b() {
        try {
            if (com.inmobi.commons.core.utilities.d.a()) {
                synchronized (k) {
                    if (i.compareAndSet(false, true)) {
                        if (f == null) {
                            HandlerThread handlerThread = new HandlerThread("pingHandlerThread");
                            f = handlerThread;
                            handlerThread.start();
                        }
                        if (e == null) {
                            e = new a(f.getLooper());
                        }
                        if (b.a()) {
                            i.set(false);
                            i();
                        } else {
                            Message obtain = Message.obtain();
                            obtain.what = 1;
                            e.sendMessage(obtain);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in starting the ping component; ").append(e2.getMessage());
        }
    }

    public final void a(final String str, final Map<String, String> map) {
        new Thread() {
            final /* synthetic */ boolean c = true;

            public final void run() {
                try {
                    h hVar = new h();
                    com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) hVar, (com.inmobi.commons.core.configs.b.c) null);
                    if (!hVar.g) {
                        a aVar = new a(str, map, this.c, c.j.a + 1);
                        c.a;
                        StringBuilder sb = new StringBuilder("Received click (");
                        sb.append(aVar.b);
                        sb.append(") for pinging over HTTP");
                        c.a(c.this, aVar);
                    }
                } catch (Exception e) {
                    c.a;
                    new StringBuilder("SDK encountered unexpected error in pinging click; ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public static void i() {
        try {
            i.set(false);
            synchronized (k) {
                if (!i.get()) {
                    if (f != null) {
                        f.getLooper().quit();
                        f.interrupt();
                        f = null;
                        e = null;
                    }
                    g.clear();
                }
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in stopping the ping component; ").append(e2.getMessage());
        }
    }

    public c() {
        try {
            d = Executors.newFixedThreadPool(5);
            HandlerThread handlerThread = new HandlerThread("pingHandlerThread");
            f = handlerThread;
            handlerThread.start();
            e = new a(f.getLooper());
            com.inmobi.ads.c cVar = new com.inmobi.ads.c();
            com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) cVar, (com.inmobi.commons.core.configs.b.c) this);
            j = cVar.h;
            h = new b();
            g.a().a((com.inmobi.commons.core.utilities.g.b) new com.inmobi.commons.core.utilities.g.b() {
                public final void a(boolean z) {
                    if (z) {
                        c.this.b();
                    }
                }
            });
            if (VERSION.SDK_INT >= 23) {
                g.a().a("android.os.action.DEVICE_IDLE_MODE_CHANGED", (com.inmobi.commons.core.utilities.g.b) new com.inmobi.commons.core.utilities.g.b() {
                    public final void a(boolean z) {
                        if (!z) {
                            c.this.b();
                        }
                    }
                });
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in initializing the ping component; ").append(e2.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public static HashMap<String, String> c(a aVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            int i2 = (j.a - aVar.f) + 1;
            if (i2 > 0) {
                hashMap.put("X-im-retry-count", String.valueOf(i2));
            }
        } catch (Exception unused) {
        }
        return hashMap;
    }

    static /* synthetic */ void a(c cVar, final a aVar) {
        h.a(aVar, j.d);
        if (!com.inmobi.commons.core.utilities.d.a()) {
            i.set(false);
            i();
            return;
        }
        d.submit(new Runnable() {
            public final void run() {
                c.this.l = SystemClock.elapsedRealtime();
                if (aVar.h) {
                    new b(c.this.m).a(aVar);
                } else {
                    new C0049c(c.this.m).a(aVar);
                }
            }
        });
    }

    static /* synthetic */ void a(a aVar) {
        if (aVar.f > 0) {
            aVar.f--;
            aVar.d = System.currentTimeMillis();
            com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
            a2.b("click", b.b(aVar), "id = ?", new String[]{String.valueOf(aVar.a)});
            a2.b();
        }
    }
}
