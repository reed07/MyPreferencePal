package com.myfitnesspal.shared.util;

import android.webkit.WebView;
import com.myfitnesspal.shared.event.BusyEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class MfpWebViewChromeClientWithProgress extends MfpWebViewChromeClient {
    private MfpActivity activity;

    public MfpWebViewChromeClientWithProgress(MfpActivity mfpActivity) {
        super(mfpActivity);
        this.activity = mfpActivity;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        this.activity.getMessageBus().post(new BusyEvent(1, i != 100));
    }
}
