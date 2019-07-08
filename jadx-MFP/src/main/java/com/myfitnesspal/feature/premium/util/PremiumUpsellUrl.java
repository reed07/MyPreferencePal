package com.myfitnesspal.feature.premium.util;

import android.net.Uri;
import android.net.Uri.Builder;
import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails;
import com.myfitnesspal.feature.payments.model.MfpSubscriptionDetails.FrequencyUnit;
import com.myfitnesspal.feature.payments.model.MfpTrialDetails;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumUpsellWebviewPromo;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class PremiumUpsellUrl {
    private static final String DUMMY_URL = "http://www.dummy.com";
    private static final String HTML_PAGE_SUFFIX = ".html";
    private static final String MFP_HOST_INTEG = "webview-integ.myfitnesspal.com";
    private static final String MFP_HOST_PRODUCTION = "webview.myfitnesspal.com";
    private static final String MFP_HOST_QA = "webview-qa.myfitnesspal.com";
    private static final String MONTHLY_FORMATTED_COST_UPSELL_PARAM = "monthly_formatted_cost";
    private static final String MONTHLY_PRODUCT_ID_UPSELL_PARAM = "monthly_product_id";
    private static final String PARAM_TRIAL_DURATION = "trial_duration";
    private static final String PARAM_TRIAL_UNIT = "trial_unit";
    private static final String PATH_FREE_TRIAL = "free-trial";
    private static final String PATH_UPSELL = "upsell";
    private static final String PREMIUM_UPSELL_URI_SCHEME_HTTP = "http";
    private static final String PROMO_PARAM = "promo";
    private static final String URL_ROLLOUT_PROPERTY_KEY = "url";
    private static final String USER_ID_UPSELL_PARAM = "user_id";
    private static final String YEARLY_FORMATTED_COST_UPSELL_PARAM = "yearly_formatted_cost";
    private static final String YEARLY_PRODUCT_ID_UPSELL_PARAM = "yearly_product_id";

    public static String getPreCacheUrl(Lazy<ConfigService> lazy, MfpApiSettings mfpApiSettings, String str, List<MfpProduct> list, boolean z) {
        return getLiveUrl(lazy, mfpApiSettings, str, list, null, z);
    }

    public static String getLiveUrl(Lazy<ConfigService> lazy, MfpApiSettings mfpApiSettings, String str, List<MfpProduct> list, Map<String, String> map, boolean z) {
        Builder builder = new Builder();
        Map propertiesForPreiumUpsellWebViewTests = ConfigUtils.getPropertiesForPreiumUpsellWebViewTests((ConfigService) lazy.get());
        if (propertiesForPreiumUpsellWebViewTests.containsKey("url")) {
            String str2 = (String) propertiesForPreiumUpsellWebViewTests.get("url");
            if (str2.contains("#!")) {
                str2 = str2.substring(0, str2.indexOf("#"));
            }
            builder = Uri.parse(str2).buildUpon();
        } else {
            String str3 = MFP_HOST_PRODUCTION;
            String websiteEndpoint = mfpApiSettings.getWebsiteEndpoint();
            if (websiteEndpoint.startsWith("https://integ.myfitnesspal.com")) {
                str3 = MFP_HOST_INTEG;
            } else if (websiteEndpoint.startsWith("https://qa.myfitnesspal.com")) {
                str3 = MFP_HOST_QA;
            }
            builder.scheme("http");
            builder.authority(str3);
        }
        String[] split = Locale.getDefault().toString().replace("-", "_").split("_");
        if (split.length >= 2) {
            builder.appendPath(split[0]);
            StringBuilder sb = new StringBuilder();
            sb.append(split[0]);
            sb.append("-");
            sb.append(split[1].toUpperCase());
            builder.appendPath(sb.toString());
        } else {
            builder.appendPath("en");
            builder.appendPath("en_US");
        }
        builder.appendPath(z ? PATH_FREE_TRIAL : PATH_UPSELL);
        if (propertiesForPreiumUpsellWebViewTests.containsKey("variant")) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append((String) propertiesForPreiumUpsellWebViewTests.get("variant"));
            sb2.append(HTML_PAGE_SUFFIX);
            builder.appendPath(sb2.toString());
        }
        for (Entry entry : propertiesForPreiumUpsellWebViewTests.entrySet()) {
            if (!"url".equals(entry.getKey()) && !"variant".equals(entry.getKey())) {
                builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }
        appendCommonQueryParameters(builder, (ConfigService) lazy.get(), str);
        appendProductDetailsToUrl(list, map, builder, z);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Premium upsell URL: ");
        sb3.append(builder.build().toString());
        Ln.d(sb3.toString(), new Object[0]);
        PremiumSupportedFeatures.appendSupportedProductsToUrl(builder);
        return builder.build().toString();
    }

    private static void appendCommonQueryParameters(Builder builder, ConfigService configService, String str) {
        builder.appendQueryParameter("user_id", str);
        builder.appendQueryParameter(Http.PLATFORM, "android");
        String variantIfCountrySupported = configService.getVariantIfCountrySupported(PremiumUpsellWebviewPromo.NAME);
        if (variantIfCountrySupported != null && !Strings.equals("control", variantIfCountrySupported) && !Strings.equals("false", variantIfCountrySupported)) {
            builder.appendQueryParameter("promo", variantIfCountrySupported);
        }
    }

    public static String getParamsForCachedUrl(ConfigService configService, List<MfpProduct> list, Map<String, String> map, String str, boolean z) {
        Builder buildUpon = Uri.parse(DUMMY_URL).buildUpon();
        appendCommonQueryParameters(buildUpon, configService, str);
        appendProductDetailsToUrl(list, map, buildUpon, z);
        PremiumSupportedFeatures.appendSupportedProductsToUrl(buildUpon);
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        sb.append(buildUpon.build().getEncodedQuery());
        return sb.toString();
    }

    private static void appendProductDetailsToUrl(List<MfpProduct> list, Map<String, String> map, Builder builder, boolean z) {
        MfpProduct mfpProduct;
        MfpProduct mfpProduct2;
        if (list != null && !list.isEmpty()) {
            if (yearly((MfpProduct) list.get(0))) {
                mfpProduct2 = (MfpProduct) list.get(0);
                mfpProduct = (MfpProduct) list.get(1);
            } else {
                MfpProduct mfpProduct3 = (MfpProduct) list.get(1);
                mfpProduct = (MfpProduct) list.get(0);
                mfpProduct2 = mfpProduct3;
            }
            if (map != null) {
                String str = (String) map.get(mfpProduct.getProductId());
                String str2 = (String) map.get(mfpProduct2.getProductId());
                if (Strings.notEmpty(str) && Strings.notEmpty(str2)) {
                    builder.appendQueryParameter(MONTHLY_PRODUCT_ID_UPSELL_PARAM, mfpProduct.getProductId());
                    builder.appendQueryParameter(YEARLY_PRODUCT_ID_UPSELL_PARAM, mfpProduct2.getProductId());
                    builder.appendQueryParameter(MONTHLY_FORMATTED_COST_UPSELL_PARAM, str);
                    builder.appendQueryParameter(YEARLY_FORMATTED_COST_UPSELL_PARAM, str2);
                }
            }
            if (z) {
                MfpTrialDetails mfpTrialDetailsFromProduct = getMfpTrialDetailsFromProduct(mfpProduct);
                MfpTrialDetails mfpTrialDetailsFromProduct2 = getMfpTrialDetailsFromProduct(mfpProduct2);
                if (mfpTrialDetailsFromProduct != null) {
                    builder.appendQueryParameter(PARAM_TRIAL_DURATION, Strings.toString(Integer.valueOf(mfpTrialDetailsFromProduct.getDurationInterval())));
                    builder.appendQueryParameter(PARAM_TRIAL_UNIT, mfpTrialDetailsFromProduct.getDurationUnit().value());
                } else if (mfpTrialDetailsFromProduct2 != null) {
                    builder.appendQueryParameter(PARAM_TRIAL_DURATION, Strings.toString(Integer.valueOf(mfpTrialDetailsFromProduct2.getDurationInterval())));
                    builder.appendQueryParameter(PARAM_TRIAL_UNIT, mfpTrialDetailsFromProduct2.getDurationUnit().value());
                }
            }
        }
    }

    private static MfpTrialDetails getMfpTrialDetailsFromProduct(MfpProduct mfpProduct) {
        MfpSubscriptionDetails subscriptionDetails = mfpProduct.getSubscriptionDetails();
        if (subscriptionDetails == null) {
            return null;
        }
        return subscriptionDetails.getTrialDetails();
    }

    private static boolean yearly(MfpProduct mfpProduct) {
        return Strings.equals((Object) mfpProduct.getSubscriptionDetails().getFrequencyUnit(), (Object) FrequencyUnit.YEAR);
    }
}
