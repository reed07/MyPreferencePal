package com.myfitnesspal.feature.payments.service;

import android.content.Context;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.MfpTrialDetails;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.util.PremiumUpsellUtils;
import com.myfitnesspal.feature.premium.util.ProductUtils;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.ProductProperties;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.Upsell;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Payments.Extras;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.model.v2.MfpLocalizedText;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.install.UtmInformation;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PaymentAnalyticsHelperImpl implements PaymentAnalyticsHelper {
    public static final String ANALYTICS_DEEP_LINK = "deep-link";
    private static final String ANALYTICS_DEFAULT_CURRENCY = "?";
    private static final String ANALYTICS_DEFAULT_FEATURE = "no-feature";
    private static final String ANALYTICS_DEFAULT_TRIAL = "0 day";
    public static final String ANALYTICS_MENU_DRAWER = "menu-drawer";
    private static final String ANLT_ATTR_PREMIUM_TRIALS_ENABLED = "premium_trials_enabled";
    private static final String ATTRIBUTE_KEY_CACHED = "cached";
    private static final String ATTRIBUTE_KEY_CURRENCY = "currency";
    private static final String ATTRIBUTE_KEY_FEATURE = "feature";
    private static final String ATTRIBUTE_KEY_LANGUAGE = "language";
    private static final String ATTRIBUTE_KEY_PAYMENT_METHOD = "payment_method";
    private static final String ATTRIBUTE_KEY_PRODUCT_DESCRIPTION = "product_description";
    private static final String ATTRIBUTE_KEY_PRODUCT_ID = "product_id";
    private static final String ATTRIBUTE_KEY_REASON = "reason";
    private static final String ATTRIBUTE_KEY_SKU_MONTH = "sku_month";
    private static final String ATTRIBUTE_KEY_SKU_VARIANT = "sku_variant";
    private static final String ATTRIBUTE_KEY_SKU_YEAR = "sku_year";
    private static final String ATTRIBUTE_KEY_SOURCE = "source";
    private static final String ATTRIBUTE_KEY_TIME = "time";
    private static final String ATTRIBUTE_KEY_TRIAL = "trial";
    private static final String ATTRIBUTE_KEY_UPSELL_VARIANT = "upsell_variant";
    private static final String ATTRIBUTE_VALUE_FREE_TRIAL_ACTIVATED = "free_trial_activated";
    private static final String ATTRIBUTE_VALUE_SUBSCRIPTION_SETTINGS = "subscription_settings";
    private static final String EVENT_FREE_TRIAL_ACTIVATED_EXPLORE_CLICKED = "free_trial_activated_explore_clicked";
    private static final String EVENT_FREE_TRIAL_ACTIVATED_SCREEN_VIEWED = "free_trial_activated_screen_viewed";
    private static final String EVENT_MANAGE_SUBSCRIPTION_GOOGLE_PLAY_LINK_CLICKED = "manage_subscription_google_play_link_clicked";
    private static final String EVENT_PREMIUM_INTERSTITIAL_SHOWN = "premium_interstitial_shown";
    private static final String EVENT_PREMIUM_SETTINGS_SCREEN_VIEWED = "premium_settings_screen_viewed";
    private static final String EVENT_UPSELL_CONNECTION_FAILED = "premium_upsell_connection_did_fail";
    private static final String EVENT_UPSELL_RECEIVED_NO_PRODUCTS_FROM_MFP = "premium_upsell_received_no_products_from_subscription_manager";
    private static final String EVENT_UPSELL_VIEW_LOADED = "premium_upsell_view_did_load";
    private static final String EVENT_UPSELL_VIEW_WILL_DISAPPEAR = "payment_upsell_view_will_disappear";
    private static final String EVENT_UPSELL_WEBVIEW_LOAD_FAIL = "premium_upsell_webview_did_fail_load";
    private static final String EVENT_UPSELL_WEBVIEW_LOAD_FINISH = "premium_upsell_webview_did_finish_load";
    private static final String EVENT_UPSELL_WEBVIEW_LOAD_START = "premium_upsell_webview_did_start_load";
    private static final String EVENT_UPSELL_WEB_VIEW_REQUEST_TIME = "webview_request_time";
    private final Lazy<AnalyticsService> analytics;
    private final Lazy<AppSettings> appSettings;
    private final Lazy<ConfigService> configService;
    private final Context context;
    private final Lazy<CountryService> countryService;
    private final Lazy<GeoLocationService> geoLocationService;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<PaymentService> paymentService;
    private final Lazy<ProductService> productService;

    public PaymentAnalyticsHelperImpl(Context context2, Lazy<ConfigService> lazy, Lazy<AppSettings> lazy2, Lazy<AnalyticsService> lazy3, Lazy<GeoLocationService> lazy4, Lazy<CountryService> lazy5, Lazy<PaymentService> lazy6, Lazy<ProductService> lazy7, Lazy<LocalSettingsService> lazy8) {
        this.context = context2.getApplicationContext();
        this.configService = lazy;
        this.appSettings = lazy2;
        this.analytics = lazy3;
        this.geoLocationService = lazy4;
        this.countryService = lazy5;
        this.paymentService = lazy6;
        this.productService = lazy7;
        this.localSettingsService = lazy8;
    }

    public void reportUpsellViewed(MfpProduct mfpProduct, String str) {
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.SHOW_UPSELL, addCommonParameters(MapUtil.createMap("feature", getFeatureForAnalytics(str), ATTRIBUTE_KEY_LANGUAGE, ((CountryService) this.countryService.get()).getCurrentLanguage(), "currency", getCurrencyForAnalytics(mfpProduct))));
    }

    public void reportUpsellWebViewLoadStart(MfpProduct mfpProduct, String str) {
        reportDetailedAnalytics(EVENT_UPSELL_VIEW_LOADED, mfpProduct, str);
        reportDetailedAnalytics(EVENT_UPSELL_WEBVIEW_LOAD_START, mfpProduct, str);
    }

    public void reportUpsellWebViewLoadFinish(MfpProduct mfpProduct, String str) {
        reportDetailedAnalytics(EVENT_UPSELL_WEBVIEW_LOAD_FINISH, mfpProduct, str);
    }

    public void reportUpsellWebViewLoadFail(MfpProduct mfpProduct, String str) {
        reportDetailedAnalytics(EVENT_UPSELL_WEBVIEW_LOAD_FAIL, mfpProduct, str);
    }

    public void reportUpsellWebViewLoadDuration(MfpProduct mfpProduct, String str, boolean z, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("time", Strings.toString(Double.valueOf(((double) ((float) j)) / 1000.0d)));
        hashMap.put(ATTRIBUTE_KEY_CACHED, Strings.toString(Boolean.valueOf(z)));
        reportDetailedAnalytics(EVENT_UPSELL_WEB_VIEW_REQUEST_TIME, mfpProduct, str, hashMap);
    }

    public void reportUpsellClosed(MfpProduct mfpProduct, String str) {
        reportDetailedAnalytics(EVENT_UPSELL_VIEW_WILL_DISAPPEAR, mfpProduct, str);
    }

    public void reportUpsellBuyButtonPress(MfpProduct mfpProduct, String str) {
        reportDetailedAnalytics(Events.PAYMENT_INITIATE, mfpProduct, str);
    }

    public void reportUpsellLoadFailure() {
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.PAYMENT_UPSELL_LOAD_FAILURE);
    }

    public void reportNoProductsFromMfp(String str) {
        reportDetailedAnalytics(EVENT_UPSELL_RECEIVED_NO_PRODUCTS_FROM_MFP, null, str);
    }

    public void reportPaymentSuccess(MfpProduct mfpProduct, String str) {
        reportDetailedAnalytics(Events.PAYMENT_SUCCESS, mfpProduct, str, CollectionUtils.nameValuePairsToMap(ANLT_ATTR_PREMIUM_TRIALS_ENABLED, Boolean.valueOf(((ProductService) this.productService.get()).areFreeTrialsEnabled())));
    }

    public void reportPaymentFailure(String str) {
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.PAYMENT_FAILURE, addCommonParameters(MapUtil.createMap("reason", str)));
    }

    public void reportPotentiallyChargedFailure() {
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.PAYMENT_POTENTIALLY_CHARGED);
    }

    public void reportPremiumInterstitialShown() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_PREMIUM_INTERSTITIAL_SHOWN, addSkuParams(new HashMap()));
    }

    public void reportPremiumSettingsScreenViewed() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_PREMIUM_SETTINGS_SCREEN_VIEWED);
    }

    public void reportFreeTrialActivatedScreenViewed() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_FREE_TRIAL_ACTIVATED_SCREEN_VIEWED, addSkuParams(new HashMap()));
    }

    public void reportFreeTrialActivatedExploreClicked() {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_FREE_TRIAL_ACTIVATED_EXPLORE_CLICKED, addSkuParams(new HashMap()));
    }

    public void reportManageSubscriptionClickedFromFreeTrialActivatedScreen() {
        reportManageSubscriptionClicked(ATTRIBUTE_VALUE_FREE_TRIAL_ACTIVATED);
    }

    public void reportManageSubscriptionClickedFromSubscriptionSettings() {
        reportManageSubscriptionClicked(ATTRIBUTE_VALUE_SUBSCRIPTION_SETTINGS);
    }

    private void reportManageSubscriptionClicked(String str) {
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_MANAGE_SUBSCRIPTION_GOOGLE_PLAY_LINK_CLICKED, addSkuParams(MapUtil.createMap("source", str)));
    }

    private void reportDetailedAnalytics(String str, MfpProduct mfpProduct, String str2) {
        reportDetailedAnalytics(str, mfpProduct, str2, new HashMap());
    }

    private void reportDetailedAnalytics(String str, MfpProduct mfpProduct, String str2, Map<String, String> map) {
        if (mfpProduct != null) {
            String str3 = ANALYTICS_DEFAULT_TRIAL;
            if (mfpProduct.getSubscriptionDetails() != null) {
                MfpTrialDetails trialDetails = mfpProduct.getSubscriptionDetails().getTrialDetails();
                if (trialDetails != null) {
                    str3 = String.format(Locale.ENGLISH, "%d %s", new Object[]{Integer.valueOf(trialDetails.getDurationInterval()), trialDetails.getDurationUnit()});
                }
            }
            StringBuilder sb = new StringBuilder();
            Enumerable.forEach((Collection<T>) mfpProduct.getProductDescriptions(), (Function1<T>) new Function1(sb) {
                private final /* synthetic */ StringBuilder f$0;

                {
                    this.f$0 = r1;
                }

                public final void execute(Object obj) {
                    this.f$0.append(((MfpLocalizedText) obj).getText());
                }
            });
            String stringExtra = ((PaymentService) this.paymentService.get()).getStartIntent(this.context, mfpProduct).getStringExtra(Extras.PROVIDER);
            map.put("product_id", mfpProduct.getProductId());
            map.put(ATTRIBUTE_KEY_PRODUCT_DESCRIPTION, sb.toString());
            map.put(ATTRIBUTE_KEY_PAYMENT_METHOD, stringExtra);
            map.put(ATTRIBUTE_KEY_TRIAL, str3);
            map.put("feature", getFeatureForAnalytics(str2));
            map.put(ATTRIBUTE_KEY_LANGUAGE, ((CountryService) this.countryService.get()).getCurrentLanguage());
            map.put("currency", getCurrencyForAnalytics(mfpProduct));
            ((AnalyticsService) this.analytics.get()).reportEvent(str, addCommonParameters(map));
        }
    }

    private String getFeatureForAnalytics(String str) {
        return Strings.isEmpty(str) ? ANALYTICS_DEFAULT_FEATURE : str;
    }

    private String getCurrencyForAnalytics(MfpProduct mfpProduct) {
        return ProductUtils.getCurrencyForProduct(mfpProduct, (GeoLocationService) this.geoLocationService.get(), ANALYTICS_DEFAULT_CURRENCY);
    }

    private Map<String, String> addCommonParameters(Map<String, String> map) {
        UtmInformation mostRecentUtmInformation = ((AppSettings) this.appSettings.get()).getMostRecentUtmInformation();
        if (mostRecentUtmInformation != null && mostRecentUtmInformation.isValid()) {
            mostRecentUtmInformation.addToMap(map);
        }
        map.put(ATTRIBUTE_KEY_UPSELL_VARIANT, ((ConfigService) this.configService.get()).getVariant(Upsell.NAME));
        addSkuParams(map);
        return map;
    }

    private Map<String, String> addSkuParams(Map<String, String> map) {
        String currentUpsellSkuRolloutName = PremiumUpsellUtils.getCurrentUpsellSkuRolloutName((ProductService) this.productService.get(), (ConfigService) this.configService.get(), (LocalSettingsService) this.localSettingsService.get());
        map.put(ATTRIBUTE_KEY_SKU_VARIANT, ((ConfigService) this.configService.get()).getVariant(currentUpsellSkuRolloutName));
        Map aBTestProperties = ((ConfigService) this.configService.get()).getABTestProperties(currentUpsellSkuRolloutName);
        if (aBTestProperties != null) {
            map.put(ATTRIBUTE_KEY_SKU_MONTH, aBTestProperties.get(ProductProperties.MONTH_KEY));
            map.put(ATTRIBUTE_KEY_SKU_YEAR, aBTestProperties.get(ProductProperties.YEAR_KEY));
        }
        return map;
    }
}
