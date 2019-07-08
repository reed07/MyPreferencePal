package com.myfitnesspal.feature.premium.util;

import com.myfitnesspal.feature.payments.model.MfpProduct;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.ProductProperties;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellSkus;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class PremiumUpsellUtils {
    public static final String DEFAULT_FREE_TRIAL_MONTHLY_SKU = "mfp_1m_and_999_1m_trial";
    public static final String DEFAULT_FREE_TRIAL_YEARLY_SKU = "mfp_12m_and_4999_1m_trial";
    public static final String DEFAULT_MONTHLY_SKU = "mfp_1m_android_999_v3";
    public static final String DEFAULT_YEARLY_SKU = "mfp_12m_android_4999_v3";
    public static final String EMPTY_STIRING = "";

    public static String getCurrentUpsellSkuRolloutName(ProductService productService, ConfigService configService, LocalSettingsService localSettingsService) {
        return useFreeTrialSku(productService, configService) ? localSettingsService.getTrialRolloutName() : UpsellSkus.NAME_STANDARD;
    }

    private static String getDefaultSkuForYear(ProductService productService, ConfigService configService) {
        return useFreeTrialSku(productService, configService) ? DEFAULT_FREE_TRIAL_YEARLY_SKU : DEFAULT_YEARLY_SKU;
    }

    private static String getDefaultSkuForMonth(ProductService productService, ConfigService configService) {
        return useFreeTrialSku(productService, configService) ? DEFAULT_FREE_TRIAL_MONTHLY_SKU : DEFAULT_MONTHLY_SKU;
    }

    private static boolean useFreeTrialSku(ProductService productService, ConfigService configService) {
        return productService.areFreeTrialsEnabled() && ConfigUtils.isPremiumUpsellWebViewEnabled(configService);
    }

    public static List<MfpProduct> getPremiumUpsellProducts(List<MfpProduct> list, Map<String, String> map, ProductService productService, ConfigService configService) {
        HashSet hashSet = new HashSet(Arrays.asList(new String[]{Strings.toString(map.get(ProductProperties.MONTH_KEY), getDefaultSkuForMonth(productService, configService)), Strings.toString(map.get(ProductProperties.YEAR_KEY), getDefaultSkuForYear(productService, configService))}));
        ArrayList arrayList = new ArrayList();
        for (MfpProduct mfpProduct : list) {
            if (hashSet.contains(mfpProduct.getProductId())) {
                arrayList.add(mfpProduct);
            }
        }
        return arrayList;
    }

    public static boolean hasProductsFromRollout(List<MfpProduct> list, String str, ConfigService configService) {
        Map aBTestProperties = configService.getABTestProperties(str);
        String strings = Strings.toString(aBTestProperties.get(ProductProperties.MONTH_KEY), "");
        String strings2 = Strings.toString(aBTestProperties.get(ProductProperties.YEAR_KEY), "");
        boolean z = false;
        boolean z2 = false;
        for (MfpProduct productId : list) {
            String productId2 = productId.getProductId();
            if (Strings.equals(strings, productId2)) {
                z = true;
            }
            if (Strings.equals(strings2, productId2)) {
                z2 = true;
            }
        }
        if (!z || !z2) {
            return false;
        }
        return true;
    }
}
