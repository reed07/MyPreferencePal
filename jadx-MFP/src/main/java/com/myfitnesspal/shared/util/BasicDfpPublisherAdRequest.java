package com.myfitnesspal.shared.util;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.mopub.common.MoPub;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.privacy.PersonalInfoManager;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.constants.Constants.ABTest.BannerAdsDfpAndroid;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import com.myfitnesspal.shared.model.UserLocation;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.util.BasicDfpPublisherAdRequest.Builder;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.Map;
import java.util.Map.Entry;

public class BasicDfpPublisherAdRequest {

    public static class Builder {
        /* access modifiers changed from: private */
        public final Lazy<AdsSettings> adsSettings;
        /* access modifiers changed from: private */
        public final Lazy<ConfigService> configService;
        /* access modifiers changed from: private */
        public Map<String, String> customTargeting;
        /* access modifiers changed from: private */
        public boolean isCountryUS;
        /* access modifiers changed from: private */
        public final Lazy<LocalSettingsService> localSettingsService;
        /* access modifiers changed from: private */
        public final Lazy<LocationService> locationService;

        public Builder(Lazy<AdsSettings> lazy, Lazy<ConfigService> lazy2, Lazy<LocalSettingsService> lazy3, Lazy<LocationService> lazy4, boolean z) {
            this.adsSettings = lazy;
            this.configService = lazy2;
            this.localSettingsService = lazy3;
            this.locationService = lazy4;
            this.isCountryUS = z;
            if (MoPub.isSdkInitialized()) {
                setGDPRAppliesAndConsentOnMoPub();
            } else {
                AdsUtil.initMoPub((LocalSettingsService) lazy3.get(), MyFitnessPalApp.getInstance(), new SdkInitializationListener() {
                    public final void onInitializationFinished() {
                        Builder.this.setGDPRAppliesAndConsentOnMoPub();
                    }
                });
            }
        }

        public Builder setCustomTargeting(Map<String, String> map) {
            this.customTargeting = map;
            return this;
        }

        public PublisherAdRequest getRequest() {
            Bundle nonPersonalizedAdsBundle = AdsUtil.getNonPersonalizedAdsBundle((LocalSettingsService) this.localSettingsService.get());
            if (nonPersonalizedAdsBundle != null) {
                return new BasicDfpPublisherAdRequest().getRequest(this).addNetworkExtrasBundle(AdMobAdapter.class, nonPersonalizedAdsBundle).build();
            }
            return new BasicDfpPublisherAdRequest().getRequest(this).build();
        }

        /* access modifiers changed from: private */
        public void setGDPRAppliesAndConsentOnMoPub() {
            PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
            if (personalInformationManager != null) {
                if (((LocalSettingsService) this.localSettingsService.get()).getIsUserSubjectToPersonalizedAds() && (personalInformationManager.gdprApplies() == null || !personalInformationManager.gdprApplies().booleanValue())) {
                    personalInformationManager.forceGdprApplies();
                }
                if (personalInformationManager.gdprApplies() != null && personalInformationManager.gdprApplies().booleanValue()) {
                    if (((LocalSettingsService) this.localSettingsService.get()).getIsPersonalizedAdConsentAccepted()) {
                        personalInformationManager.grantConsent();
                    } else {
                        personalInformationManager.revokeConsent();
                    }
                }
            }
        }
    }

    public com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder getRequest(Builder builder) {
        com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder builder2 = new com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder();
        String adsGenderString = ((AdsSettings) builder.adsSettings.get()).getAdsGenderString();
        String adsAgeString = ((AdsSettings) builder.adsSettings.get()).getAdsAgeString();
        builder2.addCustomTargeting("dkw", adsGenderString);
        builder2.addCustomTargeting("oex", adsAgeString);
        builder2.addCustomTargeting("kuid", ((LocalSettingsService) builder.localSettingsService.get()).getGAID());
        builder2.addCustomTargeting(Keywords.LIMIT_AD_TRACKING, ((LocalSettingsService) builder.localSettingsService.get()).isAdTrackingEnabled() ? "n" : "y");
        if (CollectionUtils.notEmpty(builder.customTargeting)) {
            for (Entry entry : builder.customTargeting.entrySet()) {
                builder2.addCustomTargeting((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (((ConfigService) builder.configService.get()).isVariantEnabled(BannerAdsDfpAndroid.NAME) && builder.isCountryUS) {
            UserLocation userLocation = ((LocationService) builder.locationService.get()).getUserLocation();
            if (userLocation.isLocationValid()) {
                builder2.addCustomTargeting(Keywords.LOCATION_LAT, userLocation.getLatitude());
                builder2.addCustomTargeting(Keywords.LOCATION_LONG, userLocation.getLongitude());
            }
        }
        String str = "DFP BasicDfpPublisherAdRequest.getRequest dkw(gender)=%s oex(age)=%s kuid=%s ladtr=%s";
        Object[] objArr = new Object[4];
        objArr[0] = adsGenderString;
        objArr[1] = adsAgeString;
        objArr[2] = ((LocalSettingsService) builder.localSettingsService.get()).getGAID();
        objArr[3] = ((LocalSettingsService) builder.localSettingsService.get()).isAdTrackingEnabled() ? "n" : "y";
        Ln.d(str, objArr);
        return builder2;
    }
}
