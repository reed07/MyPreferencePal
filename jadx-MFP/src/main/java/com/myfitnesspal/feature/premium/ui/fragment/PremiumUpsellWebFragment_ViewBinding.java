package com.myfitnesspal.feature.premium.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpWebView;

public class PremiumUpsellWebFragment_ViewBinding implements Unbinder {
    private PremiumUpsellWebFragment target;

    @UiThread
    public PremiumUpsellWebFragment_ViewBinding(PremiumUpsellWebFragment premiumUpsellWebFragment, View view) {
        this.target = premiumUpsellWebFragment;
        premiumUpsellWebFragment.webView = (MfpWebView) Utils.findRequiredViewAsType(view, R.id.webview, "field 'webView'", MfpWebView.class);
    }

    @CallSuper
    public void unbind() {
        PremiumUpsellWebFragment premiumUpsellWebFragment = this.target;
        if (premiumUpsellWebFragment != null) {
            this.target = null;
            premiumUpsellWebFragment.webView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
