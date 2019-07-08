package com.moat.analytics.mobile.inm;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.a.b.a;
import java.lang.ref.WeakReference;
import java.util.Map;

class n extends MoatFactory {
    n() {
        if (!a()) {
            String str = "Failed to initialize MoatFactory";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(", SDK was not started");
            p.a("[ERROR] ", 3, "Factory", this, sb.toString());
            throw new m(str);
        }
    }

    private NativeDisplayTracker a(View view, final Map<String, String> map) {
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) x.a((a<T>) new a<NativeDisplayTracker>() {
            public a<NativeDisplayTracker> a() {
                View view = (View) weakReference.get();
                StringBuilder sb = new StringBuilder("Attempting to create NativeDisplayTracker for ");
                sb.append(p.a(view));
                p.a("[INFO] ", 3, "Factory", this, sb.toString());
                return a.a(new t(view, map));
            }
        }, NativeDisplayTracker.class);
    }

    private NativeVideoTracker a(final String str) {
        return (NativeVideoTracker) x.a((a<T>) new a<NativeVideoTracker>() {
            public a<NativeVideoTracker> a() {
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create NativeVideoTracker");
                return a.a(new u(str));
            }
        }, NativeVideoTracker.class);
    }

    private WebAdTracker a(ViewGroup viewGroup) {
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) x.a((a<T>) new a<WebAdTracker>() {
            public a<WebAdTracker> a() {
                ViewGroup viewGroup = (ViewGroup) weakReference.get();
                StringBuilder sb = new StringBuilder("Attempting to create WebAdTracker for adContainer ");
                sb.append(p.a((View) viewGroup));
                p.a("[INFO] ", 3, "Factory", this, sb.toString());
                return a.a(new aa(viewGroup));
            }
        }, WebAdTracker.class);
    }

    private WebAdTracker a(WebView webView) {
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) x.a((a<T>) new a<WebAdTracker>() {
            public a<WebAdTracker> a() {
                WebView webView = (WebView) weakReference.get();
                StringBuilder sb = new StringBuilder("Attempting to create WebAdTracker for ");
                sb.append(p.a((View) webView));
                p.a("[INFO] ", 3, "Factory", this, sb.toString());
                return a.a(new aa(webView));
            }
        }, WebAdTracker.class);
    }

    private <T> T a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.a();
    }

    private boolean a() {
        return ((k) k.getInstance()).a();
    }

    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return a(moatPlugin);
        } catch (Exception e) {
            m.a(e);
            return moatPlugin.b();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
        try {
            return a(view, map);
        } catch (Exception e) {
            m.a(e);
            return new c();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return a(str);
        } catch (Exception e) {
            m.a(e);
            return new d();
        }
    }

    public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
        try {
            return a(viewGroup);
        } catch (Exception e) {
            m.a(e);
            return new e();
        }
    }

    public WebAdTracker createWebAdTracker(@NonNull WebView webView) {
        try {
            return a(webView);
        } catch (Exception e) {
            m.a(e);
            return new e();
        }
    }
}
