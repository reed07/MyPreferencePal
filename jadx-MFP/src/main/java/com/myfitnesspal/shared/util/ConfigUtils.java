package com.myfitnesspal.shared.util;

import android.support.annotation.NonNull;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.constants.Constants.ABTest.AccountDelete;
import com.myfitnesspal.shared.constants.Constants.ABTest.ActivityLevelQuetionUpdate;
import com.myfitnesspal.shared.constants.Constants.ABTest.AdConsentsInterstitial;
import com.myfitnesspal.shared.constants.Constants.ABTest.AdConsentsSettings;
import com.myfitnesspal.shared.constants.Constants.ABTest.AddFoodSummaryViewRefactor;
import com.myfitnesspal.shared.constants.Constants.ABTest.AlexaInterstitial;
import com.myfitnesspal.shared.constants.Constants.ABTest.AppNavUpdates;
import com.myfitnesspal.shared.constants.Constants.ABTest.BannerAdsDfpAndroid;
import com.myfitnesspal.shared.constants.Constants.ABTest.BetaProgramSignup201602;
import com.myfitnesspal.shared.constants.Constants.ABTest.BlogInNewsfeedV2201603;
import com.myfitnesspal.shared.constants.Constants.ABTest.ChangePassword;
import com.myfitnesspal.shared.constants.Constants.ABTest.ChangePasswordSettings;
import com.myfitnesspal.shared.constants.Constants.ABTest.CompleteDiaryAdAndroid201603;
import com.myfitnesspal.shared.constants.Constants.ABTest.DiaryCopyUpdate201612;
import com.myfitnesspal.shared.constants.Constants.ABTest.DisableGoogleAnalyticsScreenView;
import com.myfitnesspal.shared.constants.Constants.ABTest.EnableAmplitude;
import com.myfitnesspal.shared.constants.Constants.ABTest.ExploreActivityCards;
import com.myfitnesspal.shared.constants.Constants.ABTest.FileExportFeaturePreview;
import com.myfitnesspal.shared.constants.Constants.ABTest.FoodDetailsScreenUpdate;
import com.myfitnesspal.shared.constants.Constants.ABTest.FoodFeedback;
import com.myfitnesspal.shared.constants.Constants.ABTest.FoodInfoExpandCaret201510;
import com.myfitnesspal.shared.constants.Constants.ABTest.FoodListWithExamples201610;
import com.myfitnesspal.shared.constants.Constants.ABTest.FoodSearchAdsV1;
import com.myfitnesspal.shared.constants.Constants.ABTest.FoodSearchImprovementsPhase1;
import com.myfitnesspal.shared.constants.Constants.ABTest.GenericWebViewInterstitial;
import com.myfitnesspal.shared.constants.Constants.ABTest.HideBannerAdsDiary;
import com.myfitnesspal.shared.constants.Constants.ABTest.MacrosByMealGoalScreenSetting;
import com.myfitnesspal.shared.constants.Constants.ABTest.MealImprovementsSharing;
import com.myfitnesspal.shared.constants.Constants.ABTest.MealNotes;
import com.myfitnesspal.shared.constants.Constants.ABTest.NewNutrientsAndOrderingForUS;
import com.myfitnesspal.shared.constants.Constants.ABTest.NewsFeedVideo;
import com.myfitnesspal.shared.constants.Constants.ABTest.NewsfeedVideosSetting;
import com.myfitnesspal.shared.constants.Constants.ABTest.NutrientDashboardIcons;
import com.myfitnesspal.shared.constants.Constants.ABTest.PostImageToStatus;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.PremiumCrownHeader;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.PremiumOnboarding;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumFreeTrialContinuingUsers;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumFreeTrialNewUsers;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumFreeTrialReactivatingUsers;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumInterstitialFixes201603;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumUpsellCopyOrderingTest201610;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumUpsellWebView;
import com.myfitnesspal.shared.constants.Constants.ABTest.PremiumUpsellWebViewTests;
import com.myfitnesspal.shared.constants.Constants.ABTest.ProfileScreenPremiumCta;
import com.myfitnesspal.shared.constants.Constants.ABTest.ProgressPhotosNewsfeed;
import com.myfitnesspal.shared.constants.Constants.ABTest.ReactivationMarketingOptinOptimization201609;
import com.myfitnesspal.shared.constants.Constants.ABTest.RestaurantLogging201602;
import com.myfitnesspal.shared.constants.Constants.ABTest.RestaurantLoggingSearchIntegration;
import com.myfitnesspal.shared.constants.Constants.ABTest.SamsungGear;
import com.myfitnesspal.shared.constants.Constants.ABTest.SamsungGearDeviceActivation;
import com.myfitnesspal.shared.constants.Constants.ABTest.SamsungSHealth;
import com.myfitnesspal.shared.constants.Constants.ABTest.SamsungSHealthAppGallery;
import com.myfitnesspal.shared.constants.Constants.ABTest.SearchWalkthroughExistingUser;
import com.myfitnesspal.shared.constants.Constants.ABTest.SettingsScreenPremiumCta;
import com.myfitnesspal.shared.constants.Constants.ABTest.ShareCongratulations;
import com.myfitnesspal.shared.constants.Constants.ABTest.SmartWaterPhase1;
import com.myfitnesspal.shared.constants.Constants.ABTest.StartingWeightAndroid201603;
import com.myfitnesspal.shared.constants.Constants.ABTest.StreakAdInterstitial;
import com.myfitnesspal.shared.constants.Constants.ABTest.UaAccountLogInRushWeekChanges;
import com.myfitnesspal.shared.constants.Constants.ABTest.WaterLoggingSponsorship;
import com.myfitnesspal.shared.constants.Constants.ABTest.WeeklyDigest201608;
import com.myfitnesspal.shared.constants.Constants.ABTest.XPromoInterstitial201506;
import com.myfitnesspal.shared.constants.Constants.ABTest.XPromoOurApps201506;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.uacf.core.util.Strings;
import java.util.Map;

public final class ConfigUtils {
    public static boolean showFoodInfoExpandCaret(ConfigService configService) {
        return Strings.equals("control", configService.getVariant(FoodInfoExpandCaret201510.NAME, "false"));
    }

    public static boolean isCompleteDairyAdEnabled(ConfigService configService) {
        return configService.isVariantEnabled(CompleteDiaryAdAndroid201603.NAME);
    }

    public static boolean showRLFlow(ConfigService configService) {
        return configService.isVariantOnAndCountrySupported(RestaurantLogging201602.NAME);
    }

    public static boolean isBetaSignupProgramEnabled(ConfigService configService) {
        return configService.isVariantEnabled(BetaProgramSignup201602.NAME);
    }

    public static boolean showBlogsV2(ConfigService configService) {
        return configService.isVariantEnabled(BlogInNewsfeedV2201603.NAME);
    }

    public static boolean isNewPremiumInterstitialEnabled(ConfigService configService) {
        return configService.isVariantEnabled(PremiumInterstitialFixes201603.NAME);
    }

    public static boolean isNewStartingWeightOn(ConfigService configService) {
        return configService.isVariantEnabled(StartingWeightAndroid201603.NAME);
    }

    public static boolean isRLSearchIntegrationEnabled(ConfigService configService) {
        return showRLFlow(configService) && configService.isVariantEnabled(RestaurantLoggingSearchIntegration.NAME);
    }

    public static boolean isGoogleAnalyticsScreenViewDisabled(ConfigService configService) {
        return configService.isVariantEnabled(DisableGoogleAnalyticsScreenView.NAME);
    }

    public static boolean isXPromoInterstitialEnabled(ConfigService configService) {
        return configService.isVariantEnabled(XPromoInterstitial201506.NAME);
    }

    public static boolean isXPromoOurAppsEnabled(ConfigService configService) {
        return configService.isVariantEnabled(XPromoOurApps201506.NAME);
    }

    public static boolean isProgressPhotosNewsfeedOn(ConfigService configService) {
        return configService.isVariantEnabled(ProgressPhotosNewsfeed.NAME);
    }

    public static boolean isProgressShareCongratsOn(ConfigService configService) {
        return configService.isVariantEnabled(ShareCongratulations.NAME);
    }

    public static boolean isAmplitudeEnabled(ConfigService configService) {
        return configService.isVariantEnabled(EnableAmplitude.NAME);
    }

    public static boolean isSponsoredWaterEnabled(ConfigService configService) {
        return Strings.equals(configService.getVariant(SmartWaterPhase1.NAME), SmartWaterPhase1.SPONSORED_WATER_ON_VARIANT) || Strings.equals(configService.getVariant(SmartWaterPhase1.NAME), SmartWaterPhase1.SMART_WATER_BRANDING_ON_VARIANT);
    }

    public static boolean isSponsoredWaterAnalyticsEnabled(ConfigService configService) {
        return Strings.equals(configService.getVariant(SmartWaterPhase1.NAME), SmartWaterPhase1.SPONSORED_WATER_ON_VARIANT) || Strings.equals(configService.getVariant(SmartWaterPhase1.NAME), SmartWaterPhase1.SMART_WATER_BRANDING_ON_VARIANT) || Strings.equals(configService.getVariant(SmartWaterPhase1.NAME), "control");
    }

    public static boolean isBannerAdsDfpRolloutOn(ConfigService configService) {
        return configService.isVariantEnabled(BannerAdsDfpAndroid.NAME);
    }

    public static boolean isReactivationMarketingOptinOptimizationEnabled(ConfigService configService) {
        return configService.isVariantEnabled(ReactivationMarketingOptinOptimization201609.NAME);
    }

    public static boolean isWeeklyDigestSettingOn(ConfigService configService) {
        return configService.isVariantOnAndCountryAndLanguageSupported(WeeklyDigest201608.NAME);
    }

    public static boolean showProfileScreenPremiumCta(ConfigService configService) {
        return configService.isVariantEnabled(ProfileScreenPremiumCta.NAME);
    }

    public static boolean isDiaryCopyUpdateOn(ConfigService configService) {
        return configService.isVariantEnabled(DiaryCopyUpdate201612.NAME);
    }

    public static boolean showMacrosByMealGoalScreenSetting(ConfigService configService) {
        String variant = configService.getVariant(MacrosByMealGoalScreenSetting.NAME);
        return Strings.equals(variant, "a") || Strings.equals(variant, "b");
    }

    public static boolean showUpdatedMacrosByMealGoalStrings(ConfigService configService) {
        return Strings.equals(configService.getVariant(MacrosByMealGoalScreenSetting.NAME), "b");
    }

    public static boolean showExamplesInPremiumUpsellFoodList(ConfigService configService) {
        return configService.isVariantEnabled(FoodListWithExamples201610.NAME);
    }

    public static boolean showSettingsScreenPremiumCta(ConfigService configService, PremiumService premiumService) {
        return configService.isVariantEnabled(SettingsScreenPremiumCta.NAME) && premiumService.isPremiumAvailable() && !premiumService.isPremiumSubscribed();
    }

    public static boolean showUpdatedUpsellFeatureListOrder(ConfigService configService) {
        return configService.isVariantEnabled(PremiumUpsellCopyOrderingTest201610.NAME);
    }

    public static boolean isSHealthEnabled(ConfigService configService) {
        return configService.isVariantEnabled(SamsungSHealth.NAME);
    }

    public static boolean isSHealthAppGalleryHackEnabled(ConfigService configService) {
        return configService.isVariantEnabled(SamsungSHealthAppGallery.NAME);
    }

    public static boolean isSamsungGearEnabled(ConfigService configService) {
        return configService.isVariantEnabled(SamsungGear.NAME);
    }

    public static boolean isSamsungGearDeviceActivationEnabled(ConfigService configService) {
        return configService.isVariantEnabled(SamsungGearDeviceActivation.NAME);
    }

    public static boolean isPremiumUpsellWebViewEnabled(ConfigService configService) {
        return configService.isVariantEnabled(PremiumUpsellWebView.NAME);
    }

    public static boolean isPremiumUpsellWebViewEnabledAndPremiumNotSubscribed(ConfigService configService, PremiumService premiumService) {
        return isPremiumUpsellWebViewEnabled(configService) && !premiumService.isPremiumSubscribed();
    }

    public static Map<String, String> getPropertiesForPreiumUpsellWebViewTests(ConfigService configService) {
        Map<String, String> aBTestProperties = configService.getABTestProperties(PremiumUpsellWebViewTests.NAME);
        aBTestProperties.put("variant", configService.getVariant(PremiumUpsellWebViewTests.NAME));
        return aBTestProperties;
    }

    public static boolean isNewsFeedVideoEnabled(@NonNull ConfigService configService) {
        String variantIfLanguageSupported = configService.getVariantIfLanguageSupported(NewsFeedVideo.NAME);
        return Strings.equals(variantIfLanguageSupported, NewsFeedVideo.CLICK_TO_PLAY) || Strings.equals(variantIfLanguageSupported, NewsFeedVideo.AUTO_PLAY);
    }

    public static boolean isMealSharingEnabled(ConfigService configService) {
        return configService.isVariantEnabled(MealImprovementsSharing.NAME);
    }

    public static boolean showFileExportFeaturePreview(ConfigService configService) {
        return configService.isVariantEnabled(FileExportFeaturePreview.NAME);
    }

    public static boolean isNutrientDashboardIconsEnabled(ConfigService configService) {
        return configService.isVariantEnabled(NutrientDashboardIcons.NAME);
    }

    public static boolean showFileExportNewUpdatePreview(ConfigService configService) {
        return Strings.equals(configService.getVariant(FileExportFeaturePreview.NAME), FileExportFeaturePreview.NEW_DESIGN);
    }

    public static boolean isSponsoredWaterLoggingOn(ConfigService configService) {
        return configService.isVariantOnAndCountrySupported(WaterLoggingSponsorship.NAME);
    }

    public static Map<String, String> getPropertiesForSponsoredWaterLogging(ConfigService configService) {
        return configService.getABTestProperties(WaterLoggingSponsorship.NAME);
    }

    public static boolean isNewAddFoodFlowOn(ConfigService configService) {
        return configService.isVariantEnabled(AddFoodSummaryViewRefactor.NAME);
    }

    public static boolean isPostImageToStatusOn(ConfigService configService) {
        return configService.isVariantEnabled(PostImageToStatus.NAME);
    }

    public static boolean isFoodDetailsScreenUpdateOn(ConfigService configService) {
        return configService.isVariantEnabled(FoodDetailsScreenUpdate.NAME);
    }

    public static boolean isMealNotesFoodEditorTipEnabled(ConfigService configService) {
        return configService.isVariantEnabled(MealNotes.FOOD_EDITOR_TIP);
    }

    public static boolean isMealNotesShareTipEnabled(ConfigService configService) {
        return configService.isVariantEnabled(MealNotes.SHARING_TIP);
    }

    public static boolean showDeleteAccount(ConfigService configService) {
        return configService.isVariantEnabled(AccountDelete.NAME);
    }

    public static boolean isPremiumTrialForReactivatingUsersEnabled(ConfigService configService) {
        return configService.isVariantEnabled(PremiumFreeTrialReactivatingUsers.NAME);
    }

    public static boolean isPremiumTrialForNewUsersEnabled(ConfigService configService) {
        return configService.isVariantEnabled(PremiumFreeTrialNewUsers.NAME);
    }

    public static boolean isPremiumTrialForContinuingUsersEnabled(ConfigService configService) {
        return (configService.getVariant(PremiumFreeTrialContinuingUsers.NAME) == "control" || configService.getVariant(PremiumFreeTrialContinuingUsers.NAME) == "false") ? false : true;
    }

    public static boolean isAppNavUpdateDiaryEnabled(ConfigService configService) {
        return configService.isVariantEnabled(AppNavUpdates.DIARY);
    }

    public static boolean isAppNavUpdateHomeEnabled(ConfigService configService) {
        return configService.isVariantEnabled(AppNavUpdates.HOME);
    }

    public static boolean isAppNavBottomBarEnabled(ConfigService configService) {
        return configService.isVariantEnabled(AppNavUpdates.BOTTOM_BAR);
    }

    public static boolean isAppNavMeEnabled(ConfigService configService) {
        return configService.isVariantEnabled(AppNavUpdates.ME);
    }

    public static boolean isAppNavExporeEnabled(ConfigService configService) {
        return configService.isVariantEnabled(AppNavUpdates.EXPLORE);
    }

    public static boolean isLeftDrawerHidden(ConfigService configService) {
        return isAppNavBottomBarEnabled(configService) && isAppNavExporeEnabled(configService) && configService.isVariantEnabled(AppNavUpdates.HIDE_DRAWER_MENU);
    }

    public static boolean isRushWeekLogInChangeEnabled(ConfigService configService) {
        return configService.isVariantEnabled(UaAccountLogInRushWeekChanges.LOG_IN);
    }

    public static boolean isRushWeekSignUpChangeEnabled(ConfigService configService) {
        return configService.isVariantEnabled(UaAccountLogInRushWeekChanges.SIGN_UP);
    }

    public static boolean shouldUseNewActivityLevelQuestion(ConfigService configService) {
        return configService.isVariantEnabled(ActivityLevelQuetionUpdate.NAME);
    }

    public static boolean isNearbyVenuesExploreCardEnabled(ConfigService configService) {
        return configService.isVariantEnabled(ExploreActivityCards.SHOW_NEARBY_VENUES_CARD);
    }

    public static boolean isNewsfeedVideosSettingOn(ConfigService configService) {
        return configService.isVariantEnabled(NewsfeedVideosSetting.NAME);
    }

    public static boolean isHideBannerAdsDiaryOn(ConfigService configService) {
        return configService.isVariantEnabled(HideBannerAdsDiary.NAME);
    }

    public static boolean isFoodSearchAdV1Enabled(ConfigService configService) {
        return configService.isVariantOnAndCountryAndLanguageSupported(FoodSearchAdsV1.NAME);
    }

    public static boolean isGenericWebViewEnabled(ConfigService configService) {
        return configService.isVariantEnabled(GenericWebViewInterstitial.NAME, configService.getVariantIfCountrySupported(GenericWebViewInterstitial.NAME));
    }

    public static Map<String, String> getPropertiesForGenericWebView(ConfigService configService) {
        return configService.getABTestProperties(GenericWebViewInterstitial.NAME);
    }

    public static boolean isNewNutrientsForUsEnabled(ConfigService configService) {
        return configService.isVariantOnAndCountrySupported(NewNutrientsAndOrderingForUS.NAME);
    }

    public static boolean isFoodFeedbackEnabled(ConfigService configService) {
        return configService.isVariantEnabled(FoodFeedback.NAME);
    }

    public static boolean isChangePasswordEnabled(ConfigService configService) {
        return configService.isVariantEnabled(ChangePassword.NAME);
    }

    public static boolean isChangePasswordSettingsEnabled(ConfigService configService) {
        return configService.isVariantEnabled(ChangePasswordSettings.NAME);
    }

    public static boolean isFoodSearchPhase1Enabled(ConfigService configService) {
        return configService.isVariantEnabled(FoodSearchImprovementsPhase1.NAME);
    }

    public static boolean isSearchWalkthroughForExistingUserEnabled(ConfigService configService) {
        return configService.isVariantEnabled(SearchWalkthroughExistingUser.NAME);
    }

    public static boolean isAdConsentsInterstitialEnabled(ConfigService configService) {
        return configService.isVariantEnabled(AdConsentsInterstitial.NAME);
    }

    public static boolean isAdConsentsSettingsEnabled(ConfigService configService) {
        return configService.isVariantEnabled(AdConsentsSettings.NAME);
    }

    public static boolean isPremiumCrownHeaderEnabled(ConfigService configService) {
        return configService.isVariantEnabled(PremiumCrownHeader.NAME);
    }

    public static boolean isPremiumOnboardingEnabled(ConfigService configService) {
        return configService.isVariantEnabled(PremiumOnboarding.NAME);
    }

    public static boolean isStreakAdInterstitialEnabled(@NonNull ConfigService configService) {
        return configService.isVariantOnAndCountryAndLanguageSupported(StreakAdInterstitial.NAME);
    }

    public static String getPremiumUpsellScreenType(ConfigService configService) {
        return configService.getVariant(UpsellOptimizations.NAME);
    }

    public static boolean isAlexaInterstitialEnabled(@NonNull ConfigService configService) {
        return configService.isVariantOnAndCountryAndLanguageSupported(AlexaInterstitial.NAME);
    }
}
