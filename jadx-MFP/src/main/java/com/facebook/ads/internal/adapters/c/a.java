package com.facebook.ads.internal.adapters.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.adapters.b.p;
import java.lang.ref.WeakReference;

public final class a {

    /* renamed from: com.facebook.ads.internal.adapters.c.a$a reason: collision with other inner class name */
    private static class C0002a implements com.facebook.ads.internal.h.a {
        final Context a;
        final WeakReference<b> b;
        final com.facebook.ads.internal.h.b c;
        final n d;
        final boolean e;

        private C0002a(Context context, b bVar, com.facebook.ads.internal.h.b bVar2, n nVar, boolean z) {
            this.a = context;
            this.b = new WeakReference<>(bVar);
            this.c = bVar2;
            this.d = nVar;
            this.e = z;
        }

        private void a(boolean z) {
            if (this.b.get() != null) {
                if (this.d.k() == p.WEBVIEW_PRECACHE) {
                    WebView webView = new WebView(this.a);
                    webView.getSettings().setCacheMode(1);
                    webView.setWebViewClient(new c(this.d, this.b, this.e));
                    webView.loadUrl(this.d.a());
                    return;
                }
                String a2 = this.d.a();
                if (z) {
                    a2 = this.d.k() == p.FILE_PRECACHE ? this.c.d(this.d.a()) : this.c.c(this.d.a());
                }
                this.d.a(a2);
                ((b) this.b.get()).a();
            }
        }

        public void a() {
            a(true);
        }

        public void b() {
            if (this.b.get() != null) {
                if (this.e) {
                    ((b) this.b.get()).a(AdError.CACHE_ERROR);
                } else {
                    a(false);
                }
            }
        }
    }

    public interface b {
        void a();

        void a(AdError adError);
    }

    private static class c extends WebViewClient {
        boolean a = false;
        final n b;
        final WeakReference<b> c;
        final boolean d;

        c(n nVar, WeakReference<b> weakReference, boolean z) {
            this.b = nVar;
            this.c = weakReference;
            this.d = z;
        }

        private void a() {
            if (this.c.get() != null) {
                ((b) this.c.get()).a();
            }
        }

        /* access modifiers changed from: private */
        public void a(WebResourceError webResourceError) {
            if (this.c.get() != null) {
                if (this.d) {
                    ((b) this.c.get()).a(AdError.CACHE_ERROR);
                } else {
                    a();
                }
            }
        }

        public void onPageFinished(WebView webView, String str) {
            this.a = true;
            a();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!c.this.a) {
                        c.this.a(null);
                    }
                }
            }, (long) this.b.g());
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            this.a = true;
            a(webResourceError);
        }
    }

    public static void a(Context context, o oVar, boolean z, b bVar) {
        if (com.facebook.ads.internal.r.a.n(context)) {
            bVar.a();
            return;
        }
        n j = oVar.f().j();
        com.facebook.ads.internal.h.b bVar2 = new com.facebook.ads.internal.h.b(context);
        if (j == null) {
            bVar.a(AdError.CACHE_ERROR);
            return;
        }
        switch (j.k()) {
            case PROXY_PRECACHE:
                bVar2.a(j.a());
                break;
            case FILE_PRECACHE:
                bVar2.b(j.a());
                break;
        }
        bVar2.a(oVar.b().b(), -1, -1);
        bVar2.a(j.b(), -1, -1);
        C0002a aVar = new C0002a(context, bVar, bVar2, j, z);
        bVar2.a((com.facebook.ads.internal.h.a) aVar);
    }
}
