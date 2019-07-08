package com.myfitnesspal.feature.addentry.util;

import android.content.Context;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.Strings;
import java.util.Map;

public class WaterSponsorshipUtil {
    private static final String ASSET_HDPI = "h";
    private static final String ASSET_MDPI = "m";
    private static final String ASSET_XHDPI = "x";
    private static final String ASSET_XXHDPI = "xx";
    private static final String BROUGHT_TO_YOU_BY = "brought_to_you_by_";
    private static final String DIARY_BOTTLE = "diary_bottle_";
    private static final String KEY_SPONSOR = "sponsor";
    private static final String WATER_ENTRY_BOTTLE = "water_entry_bottle_";

    public enum WaterLoggingAsset {
        WaterEntryBottles,
        DiaryBottles,
        BroughtToYouBy
    }

    public static boolean shouldShowWaterSponsorship(ConfigService configService, Session session, PremiumService premiumService) {
        return ConfigUtils.isSponsoredWaterLoggingOn(configService) && Strings.equals(session.getUser().getProfile().getCountryName(), Country.UNITED_STATES_LONG) && !premiumService.isFeatureSubscribed(PremiumFeature.AdFree);
    }

    public static String getAssetUrlForWaterEntry(Context context, ConfigService configService, WaterLoggingAsset waterLoggingAsset) {
        Map propertiesForSponsoredWaterLogging = ConfigUtils.getPropertiesForSponsoredWaterLogging(configService);
        StringBuilder sb = new StringBuilder();
        sb.append(getAssetType(waterLoggingAsset));
        sb.append(getAssetSize(context));
        return (String) propertiesForSponsoredWaterLogging.get(sb.toString());
    }

    public static String getSponsorName(ConfigService configService) {
        return (String) ConfigUtils.getPropertiesForSponsoredWaterLogging(configService).get("sponsor");
    }

    private static String getAssetType(WaterLoggingAsset waterLoggingAsset) {
        switch (waterLoggingAsset) {
            case WaterEntryBottles:
                return WATER_ENTRY_BOTTLE;
            case DiaryBottles:
                return DIARY_BOTTLE;
            default:
                return BROUGHT_TO_YOU_BY;
        }
    }

    private static String getAssetSize(Context context) {
        int i = context.getResources().getDisplayMetrics().densityDpi;
        if (i == 160) {
            return ASSET_MDPI;
        }
        if (i != 240) {
            return i != 320 ? ASSET_XXHDPI : "x";
        }
        return ASSET_HDPI;
    }
}
