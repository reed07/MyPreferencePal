package com.myfitnesspal.feature.premium.model;

import android.content.Context;
import com.myfitnesspal.feature.premium.util.UpsellParseUtils;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import java.util.Map;

public class MfpUpsellHero {
    private static final String BACKGROUND_COLOR = "upsell-hero-background-color";
    private static final String IMAGE_URL = "upsell-hero-image-url";
    private static final String SIGNUP_SUBTITLE_TEXT = "upsell-registration-cta2-text";
    private static final String SIGNUP_SUBTITLE_TEXT_COLOR = "upsell-registration-cta2-color";
    private static final String SIGNUP_SUBTITLE_TEXT_SIZE = "upsell-registration-cta2-size";
    private static final String SIGNUP_TITLE_TEXT = "upsell-registration-cta-text";
    private static final String SIGNUP_TITLE_TEXT_COLOR = "upsell-registration-cta-color";
    private static final String SIGNUP_TITLE_TEXT_SIZE = "upsell-registration-cta-size";
    private static final String SUBTITLE_TEXT = "upsell-hero-cta2-text";
    private static final String SUBTITLE_TEXT_COLOR = "upsell-hero-cta2-color";
    private static final String SUBTITLE_TEXT_SIZE = "upsell-hero-cta2-size";
    private static final String TITLE_TEXT = "upsell-hero-cta-text";
    private static final String TITLE_TEXT_COLOR = "upsell-hero-cta-color";
    private static final String TITLE_TEXT_SIZE = "upsell-hero-cta-size";
    private int backgroundColor = 0;
    private String imageUrl = "";
    private String signupSubtitleText = "";
    private int signupSubtitleTextColor = 0;
    private int signupSubtitleTextSize = 0;
    private String signupTitleText = "";
    private int signupTitleTextColor = 0;
    private int signupTitleTextSize = 0;
    private String subtitleText = "";
    private int subtitleTextColor = 0;
    private int subtitleTextSize = 0;
    private String titleText = "";
    private int titleTextColor = 0;
    private int titleTextSize = 0;

    private MfpUpsellHero() {
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public int getTitleTextColor() {
        return this.titleTextColor;
    }

    public int getTitleTextSize() {
        return this.titleTextSize;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public int getSubtitleTextColor() {
        return this.subtitleTextColor;
    }

    public int getSubtitleTextSize() {
        return this.subtitleTextSize;
    }

    public String getSubtitleText() {
        return this.subtitleText;
    }

    public int getSignupTitleTextColor() {
        return this.signupTitleTextColor;
    }

    public int getSignupSubtitleTextColor() {
        return this.signupSubtitleTextColor;
    }

    public int getSignupTitleTextSize() {
        return this.signupTitleTextSize;
    }

    public int getSignupSubtitleTextSize() {
        return this.signupSubtitleTextSize;
    }

    public String getSignupTitleText() {
        return this.signupTitleText;
    }

    public String getSignupSubtitleText() {
        return this.signupSubtitleText;
    }

    public static MfpUpsellHero parse(Context context, CountryService countryService, Map<String, String> map, ConfigService configService) {
        MfpUpsellHero mfpUpsellHero = new MfpUpsellHero();
        mfpUpsellHero.backgroundColor = UpsellParseUtils.parseUpsellColor((String) map.get(BACKGROUND_COLOR));
        mfpUpsellHero.imageUrl = UpsellParseUtils.parseUpsellString(context, countryService, IMAGE_URL, map, configService);
        mfpUpsellHero.titleTextColor = UpsellParseUtils.parseUpsellColor((String) map.get(TITLE_TEXT_COLOR));
        mfpUpsellHero.titleTextSize = UpsellParseUtils.parseUpsellInteger((String) map.get(TITLE_TEXT_SIZE), 1);
        mfpUpsellHero.titleText = UpsellParseUtils.parseUpsellString(context, countryService, TITLE_TEXT, map, configService);
        mfpUpsellHero.subtitleTextColor = UpsellParseUtils.parseUpsellColor((String) map.get(SUBTITLE_TEXT_COLOR));
        mfpUpsellHero.subtitleTextSize = UpsellParseUtils.parseUpsellInteger((String) map.get(SUBTITLE_TEXT_SIZE), 1);
        mfpUpsellHero.subtitleText = UpsellParseUtils.parseUpsellString(context, countryService, SUBTITLE_TEXT, map, configService);
        mfpUpsellHero.signupTitleTextColor = UpsellParseUtils.parseUpsellColor((String) map.get(SIGNUP_TITLE_TEXT_COLOR));
        mfpUpsellHero.signupTitleTextSize = UpsellParseUtils.parseUpsellInteger((String) map.get(SIGNUP_TITLE_TEXT_SIZE), 1);
        mfpUpsellHero.signupTitleText = UpsellParseUtils.parseUpsellString(context, countryService, SIGNUP_TITLE_TEXT, map, configService);
        mfpUpsellHero.signupSubtitleTextColor = UpsellParseUtils.parseUpsellColor((String) map.get(SIGNUP_SUBTITLE_TEXT_COLOR));
        mfpUpsellHero.signupSubtitleTextSize = UpsellParseUtils.parseUpsellInteger((String) map.get(SIGNUP_SUBTITLE_TEXT_SIZE), 1);
        mfpUpsellHero.signupSubtitleText = UpsellParseUtils.parseUpsellString(context, countryService, SIGNUP_SUBTITLE_TEXT, map, configService);
        return mfpUpsellHero;
    }
}
