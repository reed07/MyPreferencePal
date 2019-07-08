package com.mopub.mraid;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.mobileads.resource.MraidJavascript;
import java.io.ByteArrayInputStream;
import java.util.Locale;

public class MraidWebViewClient extends WebViewClient {
    private static final String MRAID_INJECTION_JAVASCRIPT;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:");
        sb.append(MraidJavascript.JAVASCRIPT_SOURCE);
        MRAID_INJECTION_JAVASCRIPT = sb.toString();
    }

    public WebResourceResponse shouldInterceptRequest(@NonNull WebView webView, @NonNull String str) {
        if (matchesInjectionUrl(str)) {
            return createMraidInjectionResponse();
        }
        return super.shouldInterceptRequest(webView, str);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean matchesInjectionUrl(@NonNull String str) {
        return "mraid.js".equals(Uri.parse(str.toLowerCase(Locale.US)).getLastPathSegment());
    }

    private WebResourceResponse createMraidInjectionResponse() {
        return new WebResourceResponse("text/javascript", "UTF-8", new ByteArrayInputStream(MRAID_INJECTION_JAVASCRIPT.getBytes()));
    }
}
