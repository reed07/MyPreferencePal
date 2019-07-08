package com.facebook.ads.internal.view.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a.C0023a;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends com.facebook.ads.internal.w.e.a {
    private static final String a = "a";
    /* access modifiers changed from: private */
    public final WeakReference<b> b;
    private final AtomicBoolean c = new AtomicBoolean();
    private final AtomicBoolean d = new AtomicBoolean(true);
    private final Path e = new Path();
    private final RectF f = new RectF();
    private final AtomicInteger g = new AtomicInteger(5000);
    private final AtomicReference<String> h = new AtomicReference<>();
    @Nullable
    private WeakReference<d> i;
    @Nullable
    private com.facebook.ads.internal.x.a j;
    /* access modifiers changed from: private */
    public w k = new w();
    private C0023a l;
    /* access modifiers changed from: private */
    public boolean m = true;
    private boolean n;
    private float o;

    /* renamed from: com.facebook.ads.internal.view.c.a$a reason: collision with other inner class name */
    static class C0015a {
        private final String a = C0015a.class.getSimpleName();
        private final WeakReference<a> b;
        private final WeakReference<b> c;
        private final WeakReference<com.facebook.ads.internal.x.a> d;
        private final WeakReference<AtomicBoolean> e;
        private final WeakReference<AtomicBoolean> f;
        private final boolean g;

        C0015a(a aVar, b bVar, com.facebook.ads.internal.x.a aVar2, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, boolean z) {
            this.b = new WeakReference<>(aVar);
            this.c = new WeakReference<>(bVar);
            this.d = new WeakReference<>(aVar2);
            this.e = new WeakReference<>(atomicBoolean);
            this.f = new WeakReference<>(atomicBoolean2);
            this.g = z;
        }

        @JavascriptInterface
        public void alert(String str) {
            Log.e(this.a, str);
        }

        @JavascriptInterface
        public String getAnalogInfo() {
            return k.a(com.facebook.ads.internal.l.a.a());
        }

        @JavascriptInterface
        public void onMainAssetLoaded() {
            if (this.b.get() != null && this.e.get() != null && this.f.get() != null && this.g && ((AtomicBoolean) this.f.get()).get()) {
                ((AtomicBoolean) this.e.get()).set(true);
                if (((a) this.b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new e(this.d));
                }
            }
        }

        @JavascriptInterface
        public void onPageInitialized() {
            a aVar = (a) this.b.get();
            if (aVar != null && !aVar.c()) {
                b bVar = (b) this.c.get();
                if (bVar != null) {
                    bVar.a();
                }
                if (!this.g && ((a) this.b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new e(this.d));
                }
            }
        }
    }

    public interface b {
        void a();

        void a(int i);

        void a(int i, @Nullable String str);

        void a(String str, Map<String, String> map);

        void b();
    }

    public static class c implements b {
        public void a() {
        }

        public void a(int i) {
        }

        public void a(int i, @Nullable String str) {
        }

        public void a(String str, Map<String, String> map) {
        }

        public void b() {
        }
    }

    public interface d {
        void b();
    }

    static class e implements Runnable {
        private final WeakReference<com.facebook.ads.internal.x.a> a;

        e(com.facebook.ads.internal.x.a aVar) {
            this.a = new WeakReference<>(aVar);
        }

        e(WeakReference<com.facebook.ads.internal.x.a> weakReference) {
            this.a = weakReference;
        }

        public void run() {
            com.facebook.ads.internal.x.a aVar = (com.facebook.ads.internal.x.a) this.a.get();
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    static class f extends WebChromeClient {
        f() {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        public void onProgressChanged(WebView webView, int i) {
        }
    }

    static class g extends WebViewClient {
        private final Context a;
        private final WeakReference<b> b;
        private final WeakReference<com.facebook.ads.internal.x.a> c;
        private final WeakReference<w> d;
        private final WeakReference<AtomicBoolean> e;
        private final WeakReference<a> f;
        private final AtomicInteger g;
        private final AtomicReference<String> h;
        /* access modifiers changed from: private */
        public boolean i = false;
        private Date j;

        g(Context context, WeakReference<b> weakReference, WeakReference<com.facebook.ads.internal.x.a> weakReference2, WeakReference<w> weakReference3, WeakReference<AtomicBoolean> weakReference4, WeakReference<a> weakReference5, AtomicInteger atomicInteger, AtomicReference<String> atomicReference) {
            this.a = context.getApplicationContext();
            this.b = weakReference;
            this.c = weakReference2;
            this.d = weakReference3;
            this.e = weakReference4;
            this.f = weakReference5;
            this.g = atomicInteger;
            this.h = atomicReference;
        }

        /* access modifiers changed from: private */
        public void a(int i2, @Nullable String str) {
            if (!"net::ERR_EMPTY_RESPONSE".equals(str)) {
                long time = new Date().getTime() - this.j.getTime();
                JSONObject jSONObject = new JSONObject();
                boolean z = str != null;
                try {
                    jSONObject.put(NativeProtocol.BRIDGE_ARG_ERROR_CODE, i2);
                    jSONObject.put("error_description", str);
                    jSONObject.put("is_web_resource_error", z);
                    jSONObject.put("loading_time_in_millis", time);
                    jSONObject.put("request_id", this.h.get());
                } catch (JSONException unused) {
                }
                com.facebook.ads.internal.w.h.a.b(this.a, "web_view", com.facebook.ads.internal.w.h.b.E, new com.facebook.ads.internal.protocol.b(AdErrorType.WEB_VIEW_FAILED_TO_LOAD, jSONObject.toString()));
                if (this.b.get() != null) {
                    ((b) this.b.get()).a(i2, str);
                }
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (!(this.f.get() == null || this.e.get() == null || ((AtomicBoolean) this.e.get()).get())) {
                a.d((a) this.f.get());
            }
            this.i = true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.j = new Date();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!g.this.i) {
                        g.this.a(-1, null);
                    }
                }
            }, (long) this.g.get());
        }

        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            this.i = true;
            a(i2, str);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            this.i = true;
            if (VERSION.SDK_INT >= 23) {
                a(webResourceError.getErrorCode(), webResourceError.getDescription().toString());
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            HashMap hashMap = new HashMap();
            if (this.c.get() != null) {
                ((com.facebook.ads.internal.x.a) this.c.get()).a((Map<String, String>) hashMap);
            }
            if (this.d.get() != null) {
                hashMap.put("touch", k.a(((w) this.d.get()).e()));
            }
            if (this.b.get() != null) {
                ((b) this.b.get()).a(str, (Map<String, String>) hashMap);
            }
            return true;
        }
    }

    public a(Context context, WeakReference<b> weakReference, int i2) {
        super(context);
        this.n = com.facebook.ads.internal.r.a.N(context);
        this.b = weakReference;
        this.l = new C0023a() {
            public void a() {
                if (a.this.m || !a.this.k.b()) {
                    a.this.k.a();
                }
                if (a.this.b.get() != null) {
                    ((b) a.this.b.get()).b();
                }
            }
        };
        this.j = new com.facebook.ads.internal.x.a(this, i2, this.l);
        setWebChromeClient(a());
        setWebViewClient(b());
        getSettings().setSupportZoom(false);
        getSettings().setCacheMode(1);
        C0015a aVar = new C0015a(this, (b) weakReference.get(), this.j, this.c, this.d, this.n);
        addJavascriptInterface(aVar, "AdControl");
    }

    static /* synthetic */ void d(a aVar) {
        aVar.c.set(true);
        new Handler(Looper.getMainLooper()).post(new e(aVar.j));
        WeakReference<d> weakReference = aVar.i;
        if (weakReference != null && weakReference.get() != null) {
            ((d) aVar.i.get()).b();
        }
    }

    /* access modifiers changed from: protected */
    public WebChromeClient a() {
        return new f();
    }

    public void a(int i2, int i3) {
        com.facebook.ads.internal.x.a aVar = this.j;
        if (aVar != null) {
            aVar.a(i2);
            this.j.b(i3);
        }
    }

    /* access modifiers changed from: protected */
    public WebViewClient b() {
        g gVar = new g(getContext(), this.b, new WeakReference(this.j), new WeakReference(this.k), new WeakReference(this.d), new WeakReference(this), this.g, this.h);
        return gVar;
    }

    public void destroy() {
        com.facebook.ads.internal.x.a aVar = this.j;
        if (aVar != null) {
            aVar.c();
            this.j = null;
        }
        x.b(this);
        this.l = null;
        this.k = null;
        com.facebook.ads.internal.w.e.b.a(this);
        super.destroy();
    }

    public Map<String, String> getTouchData() {
        return this.k.e();
    }

    public w getTouchDataRecorder() {
        return this.k;
    }

    public com.facebook.ads.internal.x.a getViewabilityChecker() {
        return this.j;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.o > BitmapDescriptorFactory.HUE_RED) {
            this.f.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getWidth(), (float) getHeight());
            this.e.reset();
            Path path = this.e;
            RectF rectF = this.f;
            float f2 = this.o;
            path.addRoundRect(rectF, f2, f2, Direction.CW);
            canvas.clipPath(this.e);
        }
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.k.a(motionEvent, this, this);
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (this.b.get() != null) {
            ((b) this.b.get()).a(i2);
        }
        if (this.j != null) {
            if (i2 == 0) {
                if (!this.n || this.c.get()) {
                    this.j.a();
                }
            }
            if (i2 == 8) {
                this.j.c();
            }
        }
    }

    public void setCheckAssetsByJavascriptBridge(boolean z) {
        this.d.set(z);
    }

    public void setCornerRadius(float f2) {
        this.o = f2;
        invalidate();
    }

    public void setLogMultipleImpressions(boolean z) {
        this.m = z;
    }

    public void setOnAssetsLoadedListener(d dVar) {
        this.i = new WeakReference<>(dVar);
    }

    public void setRequestId(String str) {
        this.h.set(str);
    }

    public void setWaitForAssetsToLoad(boolean z) {
        this.n = z;
    }

    public void setWebViewTimeoutInMillis(int i2) {
        if (i2 >= 0) {
            this.g.set(i2);
        }
    }
}
