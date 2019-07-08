package com.myfitnesspal.shared.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public class MfpWebViewClient extends WebViewClient {
    public static final String MFP_HOST = "www.myfitnesspal.com";
    private static final String MFP_INTENT_URI_PATTERN = "intent://myfitnesspal";
    private static final String MFP_URI_SCHEME = "mfp";
    private Activity activity;

    public MfpWebViewClient(Activity activity2) {
        this.activity = activity2;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (Strings.equals(Uri.parse(str).getScheme(), "mfp")) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.putExtra(Extras.DISABLE_NEW_TASK, true);
                this.activity.startActivity(intent);
            } catch (Exception unused) {
                Ln.e("failed to launch intent for url %s", str);
            }
            return true;
        } else if (!Strings.startsWith(str, MFP_INTENT_URI_PATTERN)) {
            return false;
        } else {
            try {
                Intent parseUri = Intent.parseUri(str, 1);
                parseUri.putExtra(Extras.DISABLE_NEW_TASK, true);
                this.activity.startActivity(parseUri);
            } catch (Exception unused2) {
                Ln.e("failed to launch intent for Uri %s", str);
            }
            return true;
        }
    }
}
