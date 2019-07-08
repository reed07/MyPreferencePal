package com.myfitnesspal.shared.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.util.MfpWebViewChromeClient;
import com.myfitnesspal.shared.util.MfpWebViewClient;
import com.uacf.core.util.Ln;
import javax.inject.Inject;

public class MfpWebView extends WebView {
    @Inject
    UserAgentProvider userAgentProvider;

    public MfpWebView(Context context) {
        super(context);
        init();
    }

    public MfpWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MfpWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        if (!(webViewClient instanceof MfpWebViewClient)) {
            Ln.w("external caller using a non-MfpWebViewClient implementation!.Please consider using MfpWebViewClient as your base class", new Object[0]);
        }
        super.setWebViewClient(webViewClient);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        if (!(webChromeClient instanceof MfpWebViewChromeClient)) {
            Ln.w("external caller using a non-MfpWebViewChromeClient implementation!Please consider using MfpWebViewChromeClient as your base class", new Object[0]);
        }
        super.setWebChromeClient(webChromeClient);
    }

    /* access modifiers changed from: protected */
    public String getUserAgentString(String str) {
        return String.format("%s; %s", new Object[]{str, this.userAgentProvider.get()});
    }

    private void init() {
        MyFitnessPalApp.getInstance().component().inject(this);
        WebSettings settings = getSettings();
        settings.setUserAgentString(getUserAgentString(settings.getUserAgentString()));
        Context context = getContext();
        if (context != null && (context instanceof Activity)) {
            Activity activity = (Activity) context;
            setWebViewClient(new MfpWebViewClient(activity));
            setWebChromeClient(new MfpWebViewChromeClient(activity));
        }
    }
}
