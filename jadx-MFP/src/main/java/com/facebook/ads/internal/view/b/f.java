package com.facebook.ads.internal.view.b;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.Constants;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

@TargetApi(19)
public class f extends com.facebook.ads.internal.w.e.a {
    /* access modifiers changed from: private */
    public static final String a = "f";
    /* access modifiers changed from: private */
    public static final Set<String> b = new HashSet(2);
    private a c;
    private d d;
    private long e = -1;
    private long f = -1;
    private long g = -1;
    private long h = -1;

    public interface a {
        void a(int i);

        void a(String str);

        void b(String str);

        void c(String str);
    }

    static class b extends WebChromeClient {
        private final WeakReference<a> a;
        private final WeakReference<d> b;

        b(WeakReference<a> weakReference, WeakReference<d> weakReference2) {
            this.a = weakReference;
            this.b = weakReference2;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (!TextUtils.isEmpty(message) && consoleMessage.messageLevel() == MessageLevel.LOG && this.b.get() != null) {
                ((d) this.b.get()).a(message);
            }
            return true;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (this.b.get() != null) {
                ((d) this.b.get()).a();
            }
            if (this.a.get() != null) {
                ((a) this.a.get()).a(i);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (this.a.get() != null) {
                ((a) this.a.get()).b(str);
            }
        }
    }

    static class c extends WebViewClient {
        private final WeakReference<a> a;
        private final WeakReference<Context> b;

        c(WeakReference<a> weakReference, WeakReference<Context> weakReference2) {
            this.a = weakReference;
            this.b = weakReference2;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.a.get() != null) {
                ((a) this.a.get()).c(str);
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.a.get() != null) {
                ((a) this.a.get()).a(str);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            if (!f.b.contains(parse.getScheme()) && this.b.get() != null) {
                try {
                    ((Context) this.b.get()).startActivity(new Intent("android.intent.action.VIEW", parse));
                    return true;
                } catch (ActivityNotFoundException e) {
                    Log.w(f.a, "Activity not found to handle URI.", e);
                } catch (Exception e2) {
                    Log.e(f.a, "Unknown exception occurred when trying to handle URI.", e2);
                }
            }
            return false;
        }
    }

    static {
        b.add(Constants.HTTP);
        b.add(Constants.HTTPS);
    }

    public f(Context context) {
        super(context);
        f();
    }

    public f(Context context, a aVar) {
        super(context);
        this.c = aVar;
        setWebChromeClient(a());
        setWebViewClient(b());
        f();
    }

    private void f() {
        WebSettings settings = getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
        this.d = new d(this);
    }

    private void g() {
        if (this.f > -1 && this.g > -1 && this.h > -1) {
            this.d.a(false);
        }
    }

    /* access modifiers changed from: protected */
    public WebChromeClient a() {
        return new b(new WeakReference(this.c), new WeakReference(this.d));
    }

    public void a(long j) {
        if (this.e < 0) {
            this.e = j;
        }
    }

    public void a(String str) {
        try {
            evaluateJavascript(str, null);
        } catch (IllegalStateException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("javascript:");
            sb.append(str);
            loadUrl(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public WebViewClient b() {
        return new c(new WeakReference(this.c), new WeakReference(getContext()));
    }

    public void b(long j) {
        if (this.f < 0) {
            this.f = j;
        }
        g();
    }

    public void c(long j) {
        if (this.h < 0) {
            this.h = j;
        }
        g();
    }

    public void destroy() {
        this.c = null;
        com.facebook.ads.internal.w.e.b.a(this);
        super.destroy();
    }

    public long getDomContentLoadedMs() {
        return this.f;
    }

    public String getFirstUrl() {
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        return copyBackForwardList.getSize() > 0 ? copyBackForwardList.getItemAtIndex(0).getUrl() : getUrl();
    }

    public long getLoadFinishMs() {
        return this.h;
    }

    public long getResponseEndMs() {
        return this.e;
    }

    public long getScrollReadyMs() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.g < 0 && computeVerticalScrollRange() > getHeight()) {
            this.g = System.currentTimeMillis();
            g();
        }
    }

    public void setListener(a aVar) {
        this.c = aVar;
    }
}
