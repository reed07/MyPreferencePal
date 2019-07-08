package com.myfitnesspal.feature.premium.model;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myfitnesspal.feature.premium.util.UpsellParseUtils;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.FileUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MfpUpsellConfig {
    private static final String FEATURE_NAME_PREFIX = "upsell-";
    private static final String FEATURE_NAME_SUFFIX = "-grouping";
    private static final String GROUPING_ORDER = "upsell-grouping-order";
    private static final String PURCHASE_BUTTON_BACKGROUND_COLOR = "upsell-purchase-button-bgcolor";
    private static final String TITLE_TEXT = "upsell-title-text";
    private List<MfpUpsellGroup> groups = new ArrayList();
    private MfpUpsellHero hero;
    private int purchaseButtonBackgroundColor;
    private String titleText = "";

    private MfpUpsellConfig() {
    }

    public List<MfpUpsellGroup> getGroups() {
        return new ArrayList(this.groups);
    }

    public MfpUpsellHero getHero() {
        return this.hero;
    }

    public int getPurchaseButtonBackgroundColor() {
        return this.purchaseButtonBackgroundColor;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public static MfpUpsellConfig parse(Context context, CountryService countryService, Map<String, String> map, ConfigService configService) {
        if (map != null) {
            MfpUpsellConfig mfpUpsellConfig = new MfpUpsellConfig();
            mfpUpsellConfig.parseTopLevelMetadata(context, countryService, map, configService);
            mfpUpsellConfig.hero = MfpUpsellHero.parse(context, countryService, map, configService);
            ArrayList<String> arrayList = new ArrayList<>();
            for (String str : map.keySet()) {
                if (str.startsWith(FEATURE_NAME_PREFIX) && str.endsWith(FEATURE_NAME_SUFFIX)) {
                    arrayList.add(str.substring(7, str.length() - 9));
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (String parse : arrayList) {
                arrayList2.add(MfpUpsellFeature.parse(context, countryService, parse, map, configService));
            }
            for (int parse2 : UpsellParseUtils.parseUpsellIntegerList((String) map.get(GROUPING_ORDER))) {
                mfpUpsellConfig.groups.add(MfpUpsellGroup.parse(context, countryService, parse2, arrayList2, map, configService));
            }
            return mfpUpsellConfig;
        }
        throw new NullPointerException("input property bag is null!");
    }

    public static MfpUpsellConfig getDefaultConfig(Context context, CountryService countryService, ConfigService configService) {
        try {
            return parse(context, countryService, (Map) new Gson().fromJson(FileUtils.readAllLinesFromAsset(context, "config/upsell.json"), new TypeToken<HashMap<String, String>>() {
            }.getType()), configService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void parseTopLevelMetadata(Context context, CountryService countryService, Map<String, String> map, ConfigService configService) {
        this.purchaseButtonBackgroundColor = UpsellParseUtils.parseUpsellColor((String) map.get(PURCHASE_BUTTON_BACKGROUND_COLOR));
        this.titleText = UpsellParseUtils.parseUpsellString(context, countryService, TITLE_TEXT, map, configService);
    }
}
