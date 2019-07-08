package com.myfitnesspal.feature.premium.model;

import android.content.Context;
import com.myfitnesspal.feature.premium.util.UpsellParseUtils;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MfpUpsellGroup {
    private static final Comparator<MfpUpsellFeature> FEATURE_SORT_COMPARITOR = new Comparator<MfpUpsellFeature>() {
        public int compare(MfpUpsellFeature mfpUpsellFeature, MfpUpsellFeature mfpUpsellFeature2) {
            return mfpUpsellFeature.getSortOrder() < mfpUpsellFeature2.getSortOrder() ? -1 : 1;
        }
    };
    private static final String HEADLINE_COLOR = "upsell-grouping-headline-color";
    private static final String HEADLINE_TEXT = "upsell-grouping-%d-headline-text";
    private static final String TEXT_SIZE = "upsell-grouping-headline-size";
    private List<MfpUpsellFeature> features;
    private int groupId;
    private int headlineColor;
    private String headlineText = "";
    private int textSize;

    private MfpUpsellGroup() {
    }

    public int getGroupId() {
        return this.groupId;
    }

    public int getHeadlineColor() {
        return this.headlineColor;
    }

    public int getHeadlineTextSize() {
        return this.textSize;
    }

    public String getHeadlineText() {
        return this.headlineText;
    }

    public List<MfpUpsellFeature> getFeatures() {
        return new ArrayList(this.features);
    }

    public static MfpUpsellGroup parse(Context context, CountryService countryService, int i, List<MfpUpsellFeature> list, Map<String, String> map, ConfigService configService) {
        MfpUpsellGroup mfpUpsellGroup = new MfpUpsellGroup();
        mfpUpsellGroup.groupId = i;
        mfpUpsellGroup.headlineColor = UpsellParseUtils.parseUpsellColor((String) map.get(HEADLINE_COLOR));
        mfpUpsellGroup.textSize = UpsellParseUtils.parseUpsellInteger((String) map.get(TEXT_SIZE), 1);
        mfpUpsellGroup.headlineText = UpsellParseUtils.parseUpsellString(context, countryService, key(HEADLINE_TEXT, i), map, configService);
        mfpUpsellGroup.features = new ArrayList();
        for (MfpUpsellFeature mfpUpsellFeature : list) {
            if (mfpUpsellFeature.getGroupId() == i) {
                mfpUpsellGroup.features.add(mfpUpsellFeature);
            }
        }
        Collections.sort(mfpUpsellGroup.features, FEATURE_SORT_COMPARITOR);
        return mfpUpsellGroup;
    }

    private static String key(String str, int i) {
        return String.format(Locale.ENGLISH, str, new Object[]{Integer.valueOf(i)});
    }
}
