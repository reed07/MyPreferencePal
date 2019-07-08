package com.myfitnesspal.feature.premium.model;

import android.content.Context;
import com.myfitnesspal.feature.premium.util.UpsellParseUtils;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.Ln;
import java.util.Map;

public class MfpUpsellFeature {
    private static final String DESCRIPTION_COLOR = "upsell-premium-feature-description-color";
    private static final String DESCRIPTION_SIZE = "upsell-premium-feature-description-size";
    private static final String DESCRIPTION_TEXT = "upsell-%s-description-text";
    private static final String GROUPING = "upsell-%s-grouping";
    private static final String ICON = "upsell-%s-icon";
    private static final String SORT_ORDER = "upsell-%s-sort-order";
    private static final String TITLE_COLOR = "upsell-premium-feature-title-color";
    private static final String TITLE_SIZE = "upsell-premium-feature-title-size";
    private static final String TITLE_TEXT = "upsell-%s-title-text";
    private int descriptionColor;
    private int descriptionSize;
    private String descriptionText;
    private String featureName;
    private int group;
    private String icon;
    private int sortOrder;
    private int titleColor;
    private int titleSize;
    private String titleText;

    private MfpUpsellFeature() {
    }

    public String getFeatureName() {
        return this.featureName;
    }

    public int getDescriptionColor() {
        return this.descriptionColor;
    }

    public int getDescriptionSize() {
        return this.descriptionSize;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public int getTitleSize() {
        return this.titleSize;
    }

    public String getDescriptionText() {
        return this.descriptionText;
    }

    public int getGroupId() {
        return this.group;
    }

    public String getIcon() {
        return this.icon;
    }

    public int getSortOrder() {
        return this.sortOrder;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public static MfpUpsellFeature parse(Context context, CountryService countryService, String str, Map<String, String> map, ConfigService configService) {
        try {
            MfpUpsellFeature mfpUpsellFeature = new MfpUpsellFeature();
            mfpUpsellFeature.featureName = str;
            mfpUpsellFeature.descriptionColor = UpsellParseUtils.parseUpsellColor((String) map.get(DESCRIPTION_COLOR));
            mfpUpsellFeature.descriptionSize = UpsellParseUtils.parseUpsellInteger((String) map.get(DESCRIPTION_SIZE), 1);
            mfpUpsellFeature.titleColor = UpsellParseUtils.parseUpsellColor((String) map.get(TITLE_COLOR));
            mfpUpsellFeature.titleSize = UpsellParseUtils.parseUpsellInteger((String) map.get(TITLE_SIZE), 1);
            mfpUpsellFeature.descriptionText = UpsellParseUtils.parseUpsellString(context, countryService, key(DESCRIPTION_TEXT, str), map, configService);
            mfpUpsellFeature.group = UpsellParseUtils.parseUpsellInteger((String) map.get(key(GROUPING, str)));
            mfpUpsellFeature.icon = UpsellParseUtils.parseUpsellString(context, countryService, key(ICON, str), map, configService);
            mfpUpsellFeature.sortOrder = UpsellParseUtils.parseUpsellInteger((String) map.get(key(SORT_ORDER, str)));
            mfpUpsellFeature.titleText = UpsellParseUtils.parseUpsellString(context, countryService, key(TITLE_TEXT, str), map, configService);
            return mfpUpsellFeature;
        } catch (IllegalArgumentException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("failed to parse feature with name=");
            sb.append(str);
            Ln.e(sb.toString(), new Object[0]);
            throw e;
        }
    }

    private static String key(String str, String str2) {
        return String.format(str, new Object[]{str2});
    }
}
