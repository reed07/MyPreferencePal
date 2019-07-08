package com.myfitnesspal.feature.premium.ui.activity;

import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellNavigator;
import com.myfitnesspal.feature.premium.ui.viewmodel.PremiumUpsellViewModel;
import com.myfitnesspal.feature.premium.ui.viewmodel.PremiumUpsellViewModelFactory;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Premium;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class PremiumUpsellActivity extends MfpActivity {
    private String eventToReport;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<PremiumUpsellNavigator> premiumUpsellNavigator;
    @Inject
    Lazy<ProductService> productService;
    @Inject
    Lazy<SubscriptionService> subscriptionService;
    private PremiumUpsellViewModel viewModel;
    @Inject
    Lazy<PremiumUpsellViewModelFactory> vmFactory;

    public String getAnalyticsScreenTag() {
        return Screens.PREMIUM_UPSELL;
    }

    public static Intent newStartIntent(@NonNull Context context) {
        return newStartIntent(context, (PremiumFeature) null);
    }

    public static Intent newStartIntent(@NonNull Context context, @Nullable PremiumFeature premiumFeature) {
        return newStartIntent(context, premiumFeature != null ? premiumFeature.getFeatureId() : null);
    }

    public static Intent newStartIntent(@NonNull Context context, @Nullable String str) {
        return newStartIntent(context, str, UpsellDisplayMode.Normal, null);
    }

    public static Intent newStartIntent(@NonNull Context context, @Nullable String str, @Nullable String str2) {
        return newStartIntent(context, str, UpsellDisplayMode.Normal, str2);
    }

    public static Intent newStartIntent(@NonNull Context context, @Nullable String str, @NonNull UpsellDisplayMode upsellDisplayMode) {
        return newStartIntent(context, str, upsellDisplayMode, null);
    }

    public static Intent newStartIntent(@NonNull Context context, @Nullable String str, @NonNull UpsellDisplayMode upsellDisplayMode, @Nullable String str2) {
        Intent intent = new Intent(context, PremiumUpsellActivity.class);
        intent.putExtra(Extras.EVENT_TO_REPORT_ON_LOAD, str2);
        intent.putExtra(Extras.EXTRA_PROMOTED_FEATURE, str);
        intent.putExtra(Premium.PREMIUM_UPSELL_DISPLAY_MODE, upsellDisplayMode);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_premium_upsell);
        component().inject(this);
        if (!ConfigUtils.isPremiumUpsellWebViewEnabledAndPremiumNotSubscribed(getConfigService(), (PremiumService) this.premiumService.get()) || !ConnectivityUtil.isOnline()) {
            MaterialUtils.enableAppBarScrollIfLollipop(this);
        }
        this.eventToReport = ExtrasUtils.getString(getIntent(), Extras.EVENT_TO_REPORT_ON_LOAD);
        this.viewModel = (PremiumUpsellViewModel) ViewModelProviders.of((FragmentActivity) this, (Factory) this.vmFactory.get()).get(PremiumUpsellViewModel.class);
        this.viewModel.setDisplayMode((UpsellDisplayMode) ExtrasUtils.get(getIntent(), Premium.PREMIUM_UPSELL_DISPLAY_MODE));
        this.viewModel.setPromotedFeature(ExtrasUtils.getString(getIntent(), Extras.EXTRA_PROMOTED_FEATURE));
        ((PremiumUpsellNavigator) this.premiumUpsellNavigator.get()).register(this, R.id.premium_upsell_container);
        if (bundle == null) {
            this.viewModel.showPremiumUpsell();
        }
    }

    public Map<String, String> getAnalyticsScreenAttributes() {
        if (!Strings.notEmpty(this.eventToReport)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("utm_source", this.eventToReport);
        return hashMap;
    }
}
