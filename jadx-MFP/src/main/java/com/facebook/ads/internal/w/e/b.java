package com.facebook.ads.internal.w.e;

import android.text.TextUtils;
import android.webkit.WebView;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.Locale;

public class b {
    public static String a() {
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        if (TextUtils.isEmpty(urlPrefix)) {
            return "https://www.facebook.com/";
        }
        return String.format(Locale.US, "https://www.%s.facebook.com", new Object[]{urlPrefix});
    }

    public static void a(WebView webView) {
        webView.loadUrl("about:blank");
        webView.clearCache(true);
    }
}
