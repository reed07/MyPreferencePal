package com.myfitnesspal.feature.payments.service;

import com.myfitnesspal.feature.payments.model.MfpProduct;

public interface PaymentAnalyticsHelper {
    void reportFreeTrialActivatedExploreClicked();

    void reportFreeTrialActivatedScreenViewed();

    void reportManageSubscriptionClickedFromFreeTrialActivatedScreen();

    void reportManageSubscriptionClickedFromSubscriptionSettings();

    void reportNoProductsFromMfp(String str);

    void reportPaymentFailure(String str);

    void reportPaymentSuccess(MfpProduct mfpProduct, String str);

    void reportPotentiallyChargedFailure();

    void reportPremiumInterstitialShown();

    void reportPremiumSettingsScreenViewed();

    void reportUpsellBuyButtonPress(MfpProduct mfpProduct, String str);

    void reportUpsellClosed(MfpProduct mfpProduct, String str);

    void reportUpsellLoadFailure();

    void reportUpsellViewed(MfpProduct mfpProduct, String str);

    void reportUpsellWebViewLoadDuration(MfpProduct mfpProduct, String str, boolean z, long j);

    void reportUpsellWebViewLoadFail(MfpProduct mfpProduct, String str);

    void reportUpsellWebViewLoadFinish(MfpProduct mfpProduct, String str);

    void reportUpsellWebViewLoadStart(MfpProduct mfpProduct, String str);
}
