package com.myfitnesspal.feature.settings.model;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import com.myfitnesspal.shared.constants.Constants.ConfigParam;
import com.myfitnesspal.shared.constants.Constants.Settings.Ads;
import com.myfitnesspal.shared.constants.Constants.Settings.Ads.Amazon;
import com.myfitnesspal.shared.constants.Constants.Settings.Ads.Facebook;
import com.myfitnesspal.shared.constants.Constants.Settings.Ads.InMobi;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Named;

public class AdsSettings {
    private final String facebookAppId;
    private final SharedPreferences prefs;
    private final Session session;
    private final Lazy<UacfConfigurationUtil> uacfConfigurationUtil;

    @Inject
    public AdsSettings(@Named("ads-settings") SharedPreferences sharedPreferences, @Named("facebook-app-id") String str, Lazy<UacfConfigurationUtil> lazy, Session session2) {
        this.prefs = sharedPreferences;
        this.facebookAppId = str;
        this.session = session2;
        this.uacfConfigurationUtil = lazy;
    }

    public String getInmobiAppId() {
        return this.prefs.getString(InMobi.APP_ID, InMobi.DEFAULT_APP_ID);
    }

    public void setInmobiAppId(String str) {
        this.prefs.edit().putString(InMobi.APP_ID, str).commit();
    }

    public boolean isTestModeForAds() {
        return this.prefs.getBoolean(Ads.TEST_MODE, false);
    }

    public void setIsTestModeForAds(boolean z) {
        this.prefs.edit().putBoolean(Ads.TEST_MODE, z).commit();
    }

    public String getTestNetworkPathForAds() {
        return this.prefs.getString(Ads.TEST_NETWORK, Ads.DEFAULT_TEST_NETWORK);
    }

    public void setTestNetworkPathForAds(String str) {
        this.prefs.edit().putString(Ads.TEST_NETWORK, str).commit();
    }

    public String getAmazonAppId() {
        return ((UacfConfigurationUtil) this.uacfConfigurationUtil.get()).getString(ConfigParam.AMAZON_AD_APP_ID, Amazon.DEFAULT_APP_ID);
    }

    @NonNull
    public VideoAdsParams getVideoAdParams() {
        UacfConfigurationUtil uacfConfigurationUtil2 = (UacfConfigurationUtil) this.uacfConfigurationUtil.get();
        return new VideoAdsParams(getAmazonAppId(), uacfConfigurationUtil2.getString(ConfigParam.DFP_UNIT_VIDEO_SCREEN), uacfConfigurationUtil2.getString(ConfigParam.VIDEO_ADS_IMA_TAG_TEMPLATE));
    }

    public String getFacebookAppId() {
        return this.prefs.getString(Facebook.APP_ID, this.facebookAppId);
    }

    public void setFacebookAppId(String str) {
        this.prefs.edit().putString(Facebook.APP_ID, str).commit();
    }

    public String getKeywordString() {
        HashMap hashMap = new HashMap();
        hashMap.put("dkw", getAdsGenderString());
        String adsAgeString = getAdsAgeString();
        if (Strings.notEmpty(adsAgeString)) {
            hashMap.put("oex", adsAgeString);
        }
        return Strings.join(hashMap, ":", ",");
    }

    public String getAdsGenderString() {
        return this.session.getUser().getGender() == 0 ? Keywords.GENDER_FEMALE : Keywords.GENDER_MALE;
    }

    public String getAdsAgeString() {
        int ageInYears = DateTimeUtils.getAgeInYears(this.session.getUser().getProfile().getDateOfBirth());
        String str = "";
        if (ageInYears >= 65) {
            return Keywords.AGE_65_PLUS;
        }
        if (ageInYears >= 55) {
            return Keywords.AGE_55_64;
        }
        if (ageInYears >= 45) {
            return Keywords.AGE_45_54;
        }
        if (ageInYears >= 35) {
            return Keywords.AGE_35_44;
        }
        if (ageInYears >= 25) {
            return Keywords.AGE_25_34;
        }
        return ageInYears >= 18 ? Keywords.AGE_18_24 : str;
    }
}
