package com.myfitnesspal.feature.premium.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class PremiumInterstitialActivity_ViewBinding implements Unbinder {
    private PremiumInterstitialActivity target;

    @UiThread
    public PremiumInterstitialActivity_ViewBinding(PremiumInterstitialActivity premiumInterstitialActivity) {
        this(premiumInterstitialActivity, premiumInterstitialActivity.getWindow().getDecorView());
    }

    @UiThread
    public PremiumInterstitialActivity_ViewBinding(PremiumInterstitialActivity premiumInterstitialActivity, View view) {
        this.target = premiumInterstitialActivity;
        premiumInterstitialActivity.learnMore = (TextView) Utils.findRequiredViewAsType(view, R.id.learn_more, "field 'learnMore'", TextView.class);
        premiumInterstitialActivity.notNow = view.findViewById(R.id.not_now);
    }

    @CallSuper
    public void unbind() {
        PremiumInterstitialActivity premiumInterstitialActivity = this.target;
        if (premiumInterstitialActivity != null) {
            this.target = null;
            premiumInterstitialActivity.learnMore = null;
            premiumInterstitialActivity.notNow = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
