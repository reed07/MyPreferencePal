package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import kotlin.text.Typography;
import org.json.JSONObject;

/* compiled from: IMASDK */
public final class o {
    private static o a = new o();

    private o() {
    }

    public static o a() {
        return a;
    }

    public static boolean a(WebView webView, String str) {
        if (webView == null || TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = "javascript: ";
        String valueOf = String.valueOf(str);
        webView.loadUrl(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        return true;
    }

    public final void a(WebView webView, JSONObject jSONObject) {
        a(webView, "init", jSONObject);
    }

    public final void a(WebView webView, String str, JSONObject jSONObject, JSONObject jSONObject2) {
        a(webView, "startSession", str, jSONObject, jSONObject2);
    }

    public final void a(WebView webView) {
        a(webView, "finishSession", new Object[0]);
    }

    public final void b(WebView webView, String str) {
        a(webView, "setNativeViewHierarchy", str);
    }

    public final void a(WebView webView, float f) {
        a(webView, "setDeviceVolume", Float.valueOf(f));
    }

    /* access modifiers changed from: 0000 */
    public final void a(WebView webView, String str, Object... objArr) {
        if (webView != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("javascript: if(window.omidBridge!==undefined){omidBridge.");
            sb.append(str);
            sb.append("(");
            if (objArr != null && objArr.length > 0) {
                for (Object obj : objArr) {
                    if (obj == null) {
                        sb.append(Typography.quote);
                        sb.append(Typography.quote);
                    } else if (obj instanceof String) {
                        String obj2 = obj.toString();
                        if (obj2.startsWith("{")) {
                            sb.append(obj2);
                        } else {
                            sb.append(Typography.quote);
                            sb.append(obj2);
                            sb.append(Typography.quote);
                        }
                    } else {
                        sb.append(obj);
                    }
                    sb.append(",");
                }
                sb.setLength(sb.length() - 1);
            }
            sb.append(")}");
            String sb2 = sb.toString();
            Handler handler = webView.getHandler();
            if (handler == null || Looper.myLooper() == handler.getLooper()) {
                webView.loadUrl(sb2);
            } else {
                handler.post(new p(webView, sb2));
            }
        } else {
            String valueOf = String.valueOf("The WebView is null for ");
            String valueOf2 = String.valueOf(str);
            if (valueOf2.length() != 0) {
                valueOf.concat(valueOf2);
            } else {
                new String(valueOf);
            }
            ho.e();
        }
    }
}
