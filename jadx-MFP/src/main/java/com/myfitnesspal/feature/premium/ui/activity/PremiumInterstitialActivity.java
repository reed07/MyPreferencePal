package com.myfitnesspal.feature.premium.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class PremiumInterstitialActivity extends MfpActivity {
    private static final String EXTRA_ANALYTICS_REPORTED = "analytics_reported";
    private static final String FEATURE_NAME = "premium-interstitial";
    private boolean analyticsReported;
    private boolean isNewPremiumInterstitialEnabled;
    @BindView(2131362897)
    TextView learnMore;
    @Nullable
    @BindView(2131363162)
    View notNow;
    @Inject
    Lazy<PaymentAnalyticsHelper> paymentAnalyticsHelperLazy;
    @Inject
    Lazy<ProductService> productService;

    public boolean shouldShowTitle() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, PremiumInterstitialActivity.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.isNewPremiumInterstitialEnabled = ConfigUtils.isNewPremiumInterstitialEnabled(getConfigService());
        ViewUtils.setStatusBarVisible(getWindow(), !this.isNewPremiumInterstitialEnabled);
        setContentView(this.isNewPremiumInterstitialEnabled ? R.layout.premium_interstitial_updated : R.layout.premium_interstitial);
        ButterKnife.bind((Activity) this);
        this.analyticsReported = BundleUtils.getBoolean(bundle, EXTRA_ANALYTICS_REPORTED, false);
        if (!this.analyticsReported) {
            ((PaymentAnalyticsHelper) this.paymentAnalyticsHelperLazy.get()).reportPremiumInterstitialShown();
            this.analyticsReported = true;
        }
        if (isFreeTrialEnabled()) {
            this.learnMore.setText(R.string.try_premium_free);
        }
        this.learnMore.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PremiumInterstitialActivity.this.getNavigationHelper().finishActivityAfterNavigation().withIntent(PremiumUpsellActivity.newStartIntent((Context) PremiumInterstitialActivity.this, PremiumInterstitialActivity.FEATURE_NAME)).startActivity();
            }
        });
        if (this.isNewPremiumInterstitialEnabled) {
            this.notNow.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PremiumInterstitialActivity.this.finish();
                }
            });
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_ANALYTICS_REPORTED, this.analyticsReported);
    }

    public boolean showToolbar() {
        return !this.isNewPremiumInterstitialEnabled;
    }

    private boolean isFreeTrialEnabled() {
        return ((ProductService) this.productService.get()).areFreeTrialsEnabled() && ConfigUtils.isPremiumUpsellWebViewEnabled(getConfigService());
    }
}
