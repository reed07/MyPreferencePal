package com.myfitnesspal.shared.constants;

import com.myfitnesspal.android.R;
import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public final class Constants {
    public static final String TWO_HYPHENS = "--";

    public static final class ABTest {
        public static final String VARIANT = "variant";
        public static final String VARIANT_CONTROL = "control";
        public static final String VARIANT_DEFAULT = "false";
        public static final String VARIANT_NO_OVERRIDE = "__use__server__";
        public static final String VARIANT_ON = "on";

        public static final class AccountDelete {
            public static final String NAME = "account-delete-android-2017-02";
        }

        public static final class ActivityLevelQuetionUpdate {
            public static final String NAME = "rush-week-exercise-goal-android-2017-11";
        }

        public static final class AdConsentsInterstitial {
            public static final int DEFAULT_MIN_AGE = 7;
            public static final int DEFAULT_REQUEST_INTERVAL = 60;
            public static final String NAME = "optional-ad-consents-interstitial-2018-Q4-android";
            public static final String PROPERTY_MIN_AGE = "minimum_account_age_in_days";
            public static final String PROPERTY_REQUEST_INTERVAL = "minimum_days_between_requests";
        }

        public static final class AdConsentsSettings {
            public static final String NAME = "optional-ad-consents-settings-2018-Q4-android";
        }

        public static final class AddFoodSummaryViewRefactor {
            public static final String NAME = "add-food-summary-android-2017-04";
        }

        public static final class AlexaInterstitial {
            public static final String NAME = "alexa-interstitial-android-2019-04";
        }

        public static final class AppIndexingQuickSearch201508 {
            public static final String NAME = "app-indexing-quicksearch-android-2015-08";
        }

        public static final class AppNavUpdates {
            public static final String BOTTOM_BAR = "nav-update-bottom-nav-1-2017-10";
            public static final String DIARY = "nav-update-diary-2017-08";
            public static final String EXPLORE = "nav-update-bottom-nav-2-2017-10-v2";
            public static final String HIDE_DRAWER_MENU = "nav-update-menu-2017-10";
            public static final String HOME = "nav-update-home-2017-09";
            public static final String ME = "nav-update-me-2017-10";
        }

        public static final class BannerAdCount201602 {
            public static final String NAME = "banner-ad-count-android-2016-02";
        }

        public static final class BannerAdsDfpAndroid {
            public static final String NAME = "banner-ads-dfp-android-2016-06";
        }

        public static final class BarcodeManualEntry {
            public static final String NAME = "barcode-manual-entry-android-2017-04v2";
        }

        public static final class BetaProgramSignup201602 {
            public static final String NAME = "beta-signup-android-2016-02";
        }

        public static final class BlogInNewsfeedV2201603 {
            public static final String NAME = "blog-in-newsfeed-v2-android-2016-03";
        }

        public static final class ChallengesAndroid201510 {
            public static final String NAME = "challenges-android-2015-10";
        }

        public static final class ChallengesEmailPrefAndroid201510 {
            public static final String NAME = "challenges-email-preferences-android-2015-10";
        }

        public static final class ChallengesNewsFeedCardsAndroid201510 {
            public static final String NAME = "challenges-newsfeed-cards-android-2015-10";
        }

        public static final class ChallengesShareEmail {
            public static final String NAME = "challenges-share-my-email-test-android-2017-05";
        }

        public static final class ChangePassword {
            public static final String NAME = "change-password-android-2018-09";
        }

        public static final class ChangePasswordSettings {
            public static final String NAME = "change-password-setting-android-2018-09";
        }

        public static final class CommunityInternational {
            public static final String NAME = "community-international-android-2017-04";
        }

        public static final class CompleteDiaryAdAndroid201603 {
            public static final String NAME = "complete-diary-ad-android-2016-03";
        }

        public static final class DiaryCopyUpdate201612 {
            public static final String NAME = "diary-copy-update-android-2016-12";
        }

        public static final class DisableGoogleAnalyticsScreenView {
            public static final String NAME = "disable-google-analytics-screen-view-2016-05";
        }

        public static final class EnableAmplitude {
            public static final String NAME = "amplitude-android-2016-06";
        }

        public static final class EnableImageDeletions {
            public static final String NAME = "enable-image-deletion-2017-02";
        }

        public static final class ExploreActivityCards {
            public static final String SHOW_NEARBY_VENUES_CARD = "explore-rl-android-2017-11";
        }

        public static final class ExplorePremiumCopyTest201610 {
            public static final String GO = "go";
            public static final String NAME = "explore-premium-copy-test-android-2016-10";
            public static final String UPGRADE = "upgrade";
        }

        public static final class FileExportFeaturePreview {
            public static final String NAME = "file-export-feature-preview-android-2017-01";
            public static final String NEW_DESIGN = "new_design";
        }

        public static final class FoodDetailsScreenUpdate {
            public static final String NAME = "food-details-screen-update-android-2017-04";
        }

        public static final class FoodFeedback {
            public static final String NAME = "food-feedback-android-2018-07";
        }

        public static final class FoodInfoExpandCaret201510 {
            public static final String NAME = "food-info-expand-caret-android-2015-10";
        }

        public static final class FoodListWithExamples201610 {
            public static final String NAME = "food-list-with-examples-android-2016-10";
        }

        public static final class FoodSearchAdsV1 {
            public static final String NAME = "food_search_ads_v1.1_android_2018_04";
        }

        public static final class FoodSearchImprovementsPhase1 {
            public static final String NAME = "search-redesign-v1-android-2018-07";
        }

        public static final class FriendJoinedNotification201510 {
            public static final String NAME = "friend-joined-notification-android-2015-10";
        }

        public static final class FriendLoggedExerciseNotification201510 {
            public static final String NAME = "friend-logged-exercise-notification-android-2015-10";
        }

        public static final class FriendLoginStreakNotification201510 {
            public static final String NAME = "friend-login-streak-notification-android-2015-10";
        }

        public static final class GenericWebViewInterstitial {
            public static final String NAME = "generic-webview-interstitial-android";
        }

        public static final class GoalsScreenPremiumCta {
            public static final String NAME = "goals-screen-premium-cta-android-2016-10";
        }

        public static final class GoogleFit201511 {
            public static final String NAME = "google-fit-android-2015-11";
        }

        public static final class GoogleFitActivity201511 {
            public static final String NAME = "google-fit-activity-android-2015-11";
        }

        public static final class GoogleFitNutrition201511 {
            public static final String NAME = "google-fit-nutrition-android-2015-11";
        }

        public static final class GoogleFitSteps201511 {
            public static final String NAME = "google-fit-steps-android-2015-11";
        }

        public static final class GoogleFitWeight201511 {
            public static final String CONTROL = "control";
            public static final String NAME = "google-fit-weight-android-2015-11";
            public static final String VARIANT_A = "variantA";
            public static final String VARIANT_B = "variantB";
            public static final String VARIANT_C = "variantC";
        }

        public static final class HideBannerAdsDiary {
            public static final String NAME = "hide-banner-ads-diary-android-2018-01";
        }

        public static final class MacrosByMealGoalScreenSetting {
            public static final String NAME = "macros-by-meal-goalscreen-setting-android-2016-10";
            public static final String VARIANT_A = "a";
            public static final String VARIANT_B = "b";
        }

        public static final class MaterialAccidentalDelete201603 {
            public static final String NAME = "material-accidental-delete-android-2016-03";
        }

        public static final class MealImprovementsSharing {
            public static final String NAME = "meal-improvements-sharing-android-2016-11";
        }

        public static final class MealInNewsfeedViewButton {
            public static final String NAME = "meal-in-newsfeed-view-button-android-2017-01";
        }

        public static final class MealNotes {
            public static final String FOOD_EDITOR_TIP = "meal-directions-tip-android-2017-05";
            public static final String SHARING_TIP = "meal-sharing-status-tips-android-2017-05";
        }

        public static final class MealShowShareToolTip {
            public static final String NAME = "meal-show-share-tooltip-android-2017-01";
        }

        public static final class MoatSDKRefreshRateAndroid {
            public static final String NAME = "moat-sdk-android-refresh-rate-2017-07";
        }

        public static final class MoatSdkIntegration {
            public static final String NAME = "moat-sdk-android-2017-07";
        }

        public static final class NewNutrientsAndOrderingForUS {
            public static final String NAME = "new-nutrients-copy-order-android-2018-07";
        }

        public static final class NewProductsCatalog {
            public static final String NAME = "new-products-catalog-android-2017-06";
        }

        public static final class NewUserBannerAdsAndroid201507 {
            public static final String NAME = "new-user-banner-ads-android-2015-07";
        }

        public static final class NewsFeedVideo {
            public static final String AUTO_PLAY = "auto-play";
            public static final String CLICK_TO_PLAY = "click-to-play";
            public static final String NAME = "videos-in-newsfeed-android-2017";
        }

        public static final class NewsfeedVideosSetting {
            public static final String NAME = "newsfeed-videos-setting-android-2017-11";
        }

        public static final class NutrientDashboardDiarySetting {
            public static final String NAME = "nutrient-dashboard-diary-setting-android-2017-03";
        }

        public static final class NutrientDashboardIcons {
            public static final String NAME = "nutrient-dashboard-icons-android-2017-03";
        }

        public static final class PairedFoodsAndroid201508 {
            public static final String NAME = "paired-foods-android-2015-08";
        }

        public static final class PostImageToStatus {
            public static final String NAME = "post-image-to-status-android-2017-04";
        }

        public static final class Premium {

            public static final class AdFree {
                public static final String NAME = "premium-ad-free-android-v1a";
            }

            public static final class AssignExerciseCalories {
                public static final String NAME = "premium-assign-exercise-android-v1a";
            }

            public static final class Content {
                public static final String NAME = "premium-content-android-v1a";
            }

            public static final class CustomDailyGoals {
                public static final String NAME = "premium-custom-daily-goals-android-v1a";
            }

            public static final class DisableReceiptPostToServer {
                public static final String NAME = "premium-disable-receipt-post-to-server-v1a";
            }

            public static final class DisableReceiptRetrieval {
                public static final String NAME = "premium-disable-receipt-retrieval-android-v1a";
            }

            public static final class EnableGooglePlayPurchaseRecovery {
                public static final String NAME = "premium-enable-google-play-purchase-recovery-android-2017-06";
            }

            static final class EntryPoints {
                public static final String NAME = "premium-entry-points-android-v1a";

                EntryPoints() {
                }
            }

            public static final class FileExport {
                public static final String NAME = "premium-1.5-file-export-android-2016-04";
            }

            public static final class FoodList {
                public static final String NAME = "premium-food-list-android-v1a";
            }

            public static final class FoodTimestamps {
                public static final String NAME = "food-timestamps-android-2018-01";
            }

            public static final class Graphs {
                public static final String NAME = "premium-graphs-android-v1a";
            }

            public static final class MealGoals {
                public static final String NAME = "premium-1.5-meal-goals-android-2016-04";
            }

            public static final class MealMacros {
                public static final String NAME = "premium-1.5-meal-macros-android-2016-04";
            }

            public static final class Menu {
                public static final String NAME = "premium-entry-points-android-v1a";
            }

            public static final class NutrientDashboard {
                public static final String NAME = "premium-nutrient-dashboard-android-v1a";
            }

            public static final class PremiumCrownHeader {
                public static final String NAME = "premium-crown-header-android-2019-04";
            }

            public static final class PremiumOnboarding {
                public static final String NAME = "premium-onboarding-android-2019-04";
            }

            public static final class PrioritySupport {
                public static final String NAME = "premium-entry-points-android-v1a";
            }

            public static final class ProductProperties {
                public static final String MONTH_KEY = "sku-month";
                public static final String YEAR_KEY = "sku-year";
            }

            public static final class QuickAddMacros {
                public static final String NAME = "premium-quick-add-android-v1";
            }

            public static final class SignUpUpsell {
                public static final String NAME = "premium-signup-android-v1a";
            }

            public static final class TrackMacrosByGram {
                public static final String NAME = "premium-track-macros-android-v1a";
            }

            public static final class Upsell {
                public static final String NAME = "premium-upsell-android-v1a";
            }

            public static final class UpsellOptimizations {
                public static final String NAME = "premium-upsell-droid-2019-05";
                public static final String VARIANT_BENEFIT = "benefit";
                public static final String VARIANT_BENEFIT_FEATURES = "benefit_features";
                public static final String VARIANT_BENEFIT_PRICE = "benefit_price";
                public static final String VARIANT_FEATURES = "features";
                public static final String VARIANT_FEATURES_BENEFIT = "features_benefit";
                public static final String VARIANT_FEATURES_PRICE = "features_price";
                public static final String VARIANT_PRICE = "price";
                public static final String VARIANT_PRICE_BENEFIT = "price_benefit";
                public static final String VARIANT_PRICE_FEATURES = "price_features";
            }

            public static final class UpsellSkus {
                public static final String NAME_FREE_TRIAL = "premium-free-trial-skus-android-v1";
                public static final String NAME_STANDARD = "premium-skus-android-v1a";
            }

            public static final class VerifiedFoods {
                public static final String NAME = "premium-verified-foods-android-v1a";
            }

            public static final class WeeklyDigestFeature {
                public static final String NAME = "weekly-digest-premium-features-android-2017-01";
            }

            public static final class WeeklyDigestUpsell {
                public static final String NAME = "weekly-digest-premium-upsell-android-2017-01";
            }
        }

        public static final class PremiumFeatureListClickEffect {
            public static final String NAME = "premium-feature-list-ripple-effect-android-2016-10";
        }

        public static final class PremiumFeatureListOrder201802 {
            public static final String NAME = "premium-feature-list-order-android-2018-02";
        }

        public static final class PremiumFreeTrialContinuingUsers {
            public static final String NAME = "premium-free-trial-continuing-users-android-2017-10";
            public static final String VARIANT_14_DAY_TRIAL = "14_day_trial";
            public static final String VARIANT_30_DAY_TRIAL = "1_month_trial";
            public static final String VARIANT_7_DAY_TRIAL = "7_day_trial";
        }

        public static final class PremiumFreeTrialNewUsers {
            public static final String NAME = "premium-free-trial-new-users-android-2017-03";
        }

        public static final class PremiumFreeTrialReactivatingUsers {
            public static final String NAME = "premium-free-trial-reactivating-users-android-2017-03";
        }

        public static final class PremiumInterstitialFixes201603 {
            public static final String NAME = "premium-interstitial-fixes-android-2016-03";
        }

        public static final class PremiumUpsellCopyOrderingTest201610 {
            public static final String NAME = "premium-upsell-copy-ordering-test-android-2016-10";
        }

        public static final class PremiumUpsellWebView {
            public static final String NAME = "show-premium-webview-from-features-android-2017-03";
        }

        public static final class PremiumUpsellWebViewTests {
            public static final String NAME = "webview-upsell-tests-android-2017-03";
        }

        public static final class PremiumUpsellWebviewPromo {
            public static final String NAME = "premium-upsell-webview-promo-android-2016-06";
        }

        public static final class ProfileScreenPremiumCta {
            public static final String NAME = "profile-screen-premium-cta-android-2016-10";
        }

        public static final class ProgressPhotosNewsfeed {
            public static final String NAME = "progress-photos-newsfeed-android-2016-03";
        }

        public static final class ReactivatingUserBannerAdsAndroid201507 {
            public static final String NAME = "reactivating_user_banner_ads-android-2015-07";
        }

        public static final class ReactivationMarketingOptinOptimization201609 {
            public static final String NAME = "reactivation-marketing-optin-optimization-android-2016-09";
        }

        public static final class ReactivatorAdjustGoalAndroid201412 {
            public static final String NAME = "reactivator-adjust-goal-android-2014-12";
        }

        public static final class RecipeRedesign {
            public static final String NAME = "recipe-redesign-android-2017-06";
        }

        public static final class RestaurantLogging201602 {
            public static final String NAME = "restaurant-logging-android-2016-02";
        }

        public static final class RestaurantLoggingSearchIntegration {
            public static final String NAME = "restaurant-logging-search-integration-android-2016-04";
        }

        public static final class SamsungGear {
            public static final String NAME = "samsung-gear-integration-2016-12";
        }

        public static final class SamsungGearDeviceActivation {
            public static final String NAME = "samsung-gear-device-activation-2016-12";
        }

        public static final class SamsungSHealth {
            public static final String NAME = "samsung-shealth-android-2016-12";
        }

        public static final class SamsungSHealthAppGallery {
            public static final String NAME = "samsung-shealth-app-gallery-android-2016-12";
        }

        public static final class SearchWalkthroughExistingUser {
            public static final String NAME = "search-existing-user-walkthrough-v1-android-2018-07";
        }

        public static final class SettingsScreenPremiumCta {
            public static final String NAME = "settings-screen-premium-cta-android-2016-10";
        }

        public static final class ShareCongratulations {
            public static final String NAME = "share-congratulations-android-2016-03";
        }

        public static final class SharethroughTest201608 {
            public static final String DISPLAY_INTERVAL_PROPERTY = "display_interval";
            public static final String NAME = "sharethrough-test-android-2016-08";
            public static final String START_INDEX_PROPERTY = "start_index";
        }

        public static final class SmartWaterPhase1 {
            public static final String NAME = "smart-water-phase-1-android-2016-07";
            public static final String SMART_WATER_BRANDING_ON_VARIANT = "A";
            public static final String SPONSORED_WATER_CONTROL_VARIANT = "control";
            public static final String SPONSORED_WATER_ON_VARIANT = "B";
        }

        public static final class StartingWeightAndroid201603 {
            public static final String NAME = "starting-weight-android-2016-03";
        }

        public static final class StreakAdInterstitial {
            public static final String NAME = "streak-interstitial-android-2019-03";
        }

        public static final class TrackWeightNotifications201510 {
            public static final String NAME = "track-weight-notifications-android-2015-10";
        }

        public static final class UAShopAndroid201511 {
            public static final String ATF = "atf";
            public static final String BTF = "btf";
            public static final String NAME = "ua-shop-android-11-2015";
        }

        public static final class UaAccountLogInRushWeekChanges {
            public static final String LOG_IN = "rush-week-ua-account-description-login-android-2017-11";
            public static final String SIGN_UP = "rush-week-ua-account-description-signup-android-2017-11";
        }

        public static final class WaterLoggingSponsorship {
            public static final String NAME = "water-logging-sponsorship-android-2017-03";
        }

        public static final class WeeklyDigest201608 {
            public static final String NAME = "weekly_digest_android_2016_08";
        }

        public static final class XPromoInterstitial201506 {
            public static final String NAME = "xpromo-interstitial-android-2015-06";
        }

        public static final class XPromoOurApps201506 {
            public static final String NAME = "xpromo-our-apps-android-2015-06";
        }
    }

    public static final class Ads {

        public static final class Attributes {
            public static final String N = "n";
            public static final String Y = "y";
        }

        public static final class Keywords {
            public static final String AGE = "oex";
            public static final String AGE_18_24 = "bvo";
            public static final String AGE_25_34 = "wdl";
            public static final String AGE_35_44 = "qdc";
            public static final String AGE_45_54 = "cua";
            public static final String AGE_55_64 = "hzk";
            public static final String AGE_65_PLUS = "syr";
            public static final String AUTO_PLAY = "ap";
            public static final String AUTO_PLAY_OFF = "n";
            public static final String CALORIE_GOAL = "abv_cal";
            public static final String CALORIE_GOAL_ABOVE = "b";
            public static final String CALORIE_GOAL_BELOW_MET = "a";
            public static final String DID = "did";
            public static final String GENDER = "dkw";
            public static final String GENDER_FEMALE = "nma";
            public static final String GENDER_MALE = "gep";
            public static final String LIMIT_AD_TRACKING = "ladtr";
            public static final String LOCATION_LAT = "lat";
            public static final String LOCATION_LONG = "lng";
            public static final String POSITION = "pos";
            public static final String POSITION_BOTTOM = "bottom";
            public static final String POSITION_TOP = "top";
            public static final String USER_ID = "user_id";
            public static final String USER_KUID = "kuid";
        }
    }

    public static final class Analytics {
        public static final String UNKNOWN = "UNKNOWN";

        public static final class Attributes {
            public static final String ABOVE_DIALY_CALORIES_GOAL = "above_daily_calorie_goal";
            public static final String ACHIEVEMENT_AD_FORMAT = "ad_format";
            public static final String ACHIEVEMENT_AD_NETWORK = "ad_network";
            public static final String ACHIEVEMENT_AD_NOT_LOADED_MESSAGE = "error_message";
            public static final String ACHIEVEMENT_AD_UNIT_ID = "ad_unit_id";
            public static final String ACHIEVEMENT_CLICK = "click";
            public static final String ACHIEVEMENT_CLIENT_ID = "cliend_id";
            public static final String ACHIEVEMENT_DISPLAY_TIME = "display_time";
            public static final String ACHIEVEMENT_KEYWORDS = "keywords";
            public static final String ACHIEVEMENT_SCREEN = "screen";
            public static final String ACHIEVEMENT_STREAK_VALUE = "streak_value";
            public static final String ACHIEVEMENT_TITLE = "achievement_title";
            public static final String ACHIEVEMENT_USER_ID = "user_id";
            public static final String ACTION_TYPE = "action_type";
            public static final String ACTIVE_CHALLENGE_COUNT = "active_challenge_count";
            public static final String ADD_DETAILS = "add_details";
            public static final String ADD_VIEW = "add_view";
            public static final String ADVERTISING_ID = "advertising_id";
            public static final String AD_SETTING = "setting";
            public static final String AD_UNIT_ID = "ad_unit_id";
            public static final String AD_URL = "ad_url";
            public static final String AD_VIEW_TIME_DELTA = "ad_view_time_delta";
            public static final String APP_NAME = "app name";
            public static final String APP_VERSION = "app_version";
            public static final String AUTH_TYPE = "auth_type";
            public static final String BADGE_COUNT = "badge_count";
            public static final String BARCODE = "barcode";
            public static final String BARCODE_MISS = "barcode_miss";
            public static final String BARCODE_SCAN = "scan";
            public static final String BELOW_GENDER_MINIMUM = "below_gender_minimum";
            public static final String BOTTOM_BUTTON = "bottom_button";
            public static final String BOX_ONLY = "box_only";
            public static final String BREADCRUMB = "breadcrumb";
            public static final String BULK = "bulk";
            public static final String CAL = "kCal";
            public static final String CAMPAIGN = "campaign";
            public static final String CARB_CHANGE = "carb_change";
            public static final String CARB_SAVED = "carb_saved";
            public static final String CARRIER = "carrier";
            public static final String CATEGORY = "category";
            public static final String CATEGORY_NAME = "category name";
            public static final String CHALLENGE_ID = "challenge_id";
            public static final String CHALLENGE_NAME = "challenge_name";
            public static final String CHALLENGE_STATUS = "status";
            public static final String CHANGED_STATUS = "changed_status";
            public static final String CHANNEL = "channel";
            public static final String CLICK_SOURCE = "click_source";
            public static final String CLIENT_ID = "clientId";
            public static final String COMPLETED = "completed";
            public static final String CONSENT_TYPE = "consent_type";
            public static final String CONTAINS_FOOD_AD = "contains_food_ad";
            public static final String CONTEXT = "newsfeed";
            public static final String CONTINUE = "continue";
            public static final String CORRECTED = "corrected";
            public static final String COUNT = "count";
            public static final String COUNTRY = "country";
            public static final String DASH_SETTING = "setting";
            public static final String DASH_SETTING_CUSTOM = "custom";
            public static final String DASH_SETTING_DEFAULT = "default";
            public static final String DASH_SETTING_HEART_HEALTHY = "heart_healthy";
            public static final String DASH_SETTING_LOW_CARB = "low_carb";
            public static final String DASH_SETTING_MACROS_REM = "macros_remaining";
            public static final String DATA_SOURCE = "data_source";
            public static final String DATA_TYPE = "data_type";
            public static final String DATE = "date";
            public static final String DATE_CHANGED = "date_changed";
            public static final String DAYS_FROM_CURRENT_DAY = "days_from_current_day";
            public static final String DAY_DIFF = "day_diff";
            public static final String DAY_OF_THE_WEEK = "day_of_the_week";
            public static final String DEFAULT_ORIENTATION = "default_orientation";
            public static final String DELETE = "delete";
            public static final String DELETED = "deleted";
            public static final String DETAILS_URL = "details_url";
            public static final String DEVICE = "device";
            public static final String DEVICE_ID = "device_id";
            public static final String DIARY_DATE = "diary_date";
            public static final String DIARY_EDIT = "diary_edit";
            public static final String DISABLED = "disabled";
            public static final String DISPLAY_TIME = "display_time";
            public static final String DONT_REMIND_ME_AGAIN_CHECKED = "dont_remind_me_again_checked";
            public static final String DURATION = "duration";
            public static final String ENABLED = "enabled";
            public static final String ENERGY_CHANGE = "energy_change";
            public static final String ENERGY_SAVED = "energy_saved";
            public static final String ENERGY_UNIT = "energy_unit";
            public static final String ENTRY_POINT = "entry_point";
            public static final String EVENT = "event";
            public static final String EXERCISE_ALLOCATION = "allocation";
            public static final String EXERCISE_ALLOCATION_CUSTOM = "custom";
            public static final String EXERCISE_ALLOCATION_DEFAULT = "default";
            public static final String EXERCISE_CUSTOM_CARB = "custom_carb";
            public static final String EXERCISE_CUSTOM_FAT = "custom_fat";
            public static final String EXERCISE_CUSTOM_PROTEIN = "custom_protein";
            public static final String EXERCISE_DATE = "exercise_date";
            public static final String EXERCISE_ID = "exercise_id";
            public static final String EXERCISE_READ = "exercise_read";
            public static final String EXERCISE_SEARCH_BREADCRUMB = "exercise_search_breadcrumb";
            public static final String EXERCISE_SETTING = "setting";
            public static final String EXERCISE_SOURCE = "exercise_source";
            public static final String EXERCISE_TYPE = "exercise_type";
            public static final String EXPERIMENT_NAME = "experiment_name";
            public static final String EXTERNAL = "external";
            public static final String FAT_CHANGE = "fat_change";
            public static final String FAT_SAVED = "fat_saved";
            public static final String FEATURE = "feature";
            public static final String FIRST_SELECTION_INDEX = "first_selection_index";
            public static final String FLOW_ID = "flow_id";
            public static final String FOOD = "food";
            public static final String FOODS = "foods";
            public static final String FOOD_BASIC_INFO = "food_basic_info";
            public static final String FOOD_DESCRIPTION = "food_description";
            public static final String FOOD_ENTRY_ID = "food_entry_id";
            public static final String FOOD_ENTRY_MASTER_ID = "food_entry_master_id";
            public static final String FOOD_ID = "food_id";
            public static final String FOOD_MASTER_ID = "food_master_id";
            public static final String FOOD_SEARCH = "food_search";
            public static final String FOOD_SEARCH_BREADCRUMB = "food_search_breadcrumb";
            public static final String FOOD_VERSION_ID = "food_version_id";
            public static final String FRAME_TIME = "frame_time";
            public static final String FRIDAY = "F";
            public static final String FRIEND_PROFILE = "friend_profile";
            public static final String HAS_AD = "has_ad";
            public static final String HAS_CUSTOM_GOALS_BY_DAY = "has_custom_goals_by_day";
            public static final String IMAGE_TYPE = "image_type";
            public static final String INDEX = "index";
            public static final String INGREDIENT_ADD_COUNT = "ingredient_add_count";
            public static final String INGREDIENT_COUNT = "ingredient_count";
            public static final String INGREDIENT_DELETE_COUNT = "ingredient_delete_count";
            public static final String INGREDIENT_EDIT_COUNT = "ingredient_edit_count";
            public static final String INVITATION_METHOD = "invitation_method";
            public static final String IS_LAST_PRESSED_SEARCH = "is_last_pressed_search";
            public static final String ITEM = "item";
            public static final String ITEM_CODE = "item_code";
            public static final String ITEM_COUNT = "item_count";
            public static final String ITEM_TYPE = "item_type";
            public static final String KJ = "kJ";
            public static final String LAST_SELECTION_INDEX = "last_selection_index";
            public static final String LINK_INDEX = "link_index";
            public static final String LINK_NAME = "link_name";
            public static final String LIST_TYPE = "list_type";
            public static final String LOCALE = "locale";
            public static final String LOCAL_SEARCH = "local_search";
            public static final String LOCAL_TIME = "local_time";
            public static final String LOGGED = "logged";
            public static final String LOGGED_NO = "no";
            public static final String LOGGED_YES = "yes";
            public static final String MACROS = "macros";
            public static final String MACRO_SETTING_CHANGE = "setting_change";
            public static final String MACRO_SETTING_SAVED = "setting_saved";
            public static final String MANUAL = "manual";
            public static final String MANUFACTURER = "manufacturer";
            public static final String MATCHED = "matched";
            public static final String MEAL = "meal";
            public static final String MEAL_COUNT = "meal_count";
            public static final String MEAL_NAME = "meal_name";
            public static final String MEAL_WRITE = "meal_write";
            public static final String MEDIUM = "medium";
            public static final String METADATA_EDITED = "metadata_edited";
            public static final String MICROS = "micros";
            public static final String MINUTES_LOGGED = "minutes_logged";
            public static final String MONDAY = "M";
            public static final String MORE_HELP = "more_help";
            public static final String NEWSFEED_VIDEO_SETTING = "newsfeed_video_setting";
            public static final String NEW_CHALLENGE_COUNT = "new_challenge_count";
            public static final String NONE = "none";
            public static final String NO_THANKS = "no_thanks";
            public static final String NUMBER_FOODS_LOGGED = "num_foods_logged";
            public static final String NUMBER_OF_FOODS_LOGGED = "number_of_foods_logged";
            public static final String NUMBER_OF_FRIENDS = "number_of_friends";
            public static final String NUMBER_OF_MACROS = "num_macros";
            public static final String NUMBER_OF_MICROS = "num_micros";
            public static final String NUM_DELETED = "num_deleted";
            public static final String OFF = "off";
            public static final String ON = "on";
            public static final String ONLINE_SEARCH = "online_search";
            public static final String OS_VERSION = "os_version";
            public static final String PAGEVIEWS = "pageviews";
            public static final String PAGE_TYPE = "page_type";
            public static final String PAGE_VISITS = "page_visits";
            public static final String PARTNER_NAME = "partner name";
            public static final String PERCENT = "percent";
            public static final String PREMIUM_FEATURES = "premium_features";
            public static final String PRODUCT_NAME = "product name";
            public static final String PROTEIN_CHANGE = "protein_change";
            public static final String PROTEIN_SAVED = "protein_saved";
            public static final String QUICK_ADD_ENERGY_UNIT = "energy_unit";
            public static final String QUICK_CARB_VALUE = "carb_value";
            public static final String QUICK_ENERGY_VALUE = "energy_value";
            public static final String QUICK_FAT_VALUE = "fat_value";
            public static final String QUICK_PROTEIN_VALUE = "protein_value";
            public static final String REASON = "reason";
            public static final String REASON_CODE = "reason_code";
            public static final String REASON_MESSAGE = "reason_message";
            public static final String RECIPE = "recipe";
            public static final String RECIPE_COUNT = "recipe_count";
            public static final String REFERRER = "referrer";
            public static final String REQUEST_ID = "request_id";
            public static final String RESOURCE_TYPE = "resource_type";
            public static final String RESULT_COUNT = "result_count";
            public static final String RESULT_COUNT_0 = "0";
            public static final String RESULT_COUNT_10_24 = "10-24";
            public static final String RESULT_COUNT_1_4 = "1-4";
            public static final String RESULT_COUNT_25 = "25+";
            public static final String RESULT_COUNT_5_9 = "5-9";
            public static final String SATURDAY = "Sa";
            public static final String SCREEN = "screen";
            public static final String SEARCH_EVENT = "search_event";
            public static final String SEARCH_FLOWID = "search_flow_id";
            public static final String SEARCH_TERM = "search_term";
            public static final String SEARCH_VERSION = "version";
            public static final String SEARCH_VIEW = "search_view";
            public static final String SEGMENT = "segment";
            public static final String SELECT = "select";
            public static final String SELECTED_COUNT = "selected_count";
            public static final String SERVING_SIZE_INDEX = "serving_size_index";
            public static final String SERVING_SIZE_NAME = "serving_size_name";
            public static final String SESSION_ID = "session_id";
            public static final String SETTINGS = "settings";
            public static final String SETTING_CHANGE_NO = "no";
            public static final String SETTING_CHANGE_YES = "yes";
            public static final String SETTING_CUSTOM_1 = "custom_1";
            public static final String SETTING_CUSTOM_2 = "custom_2";
            public static final String SETTING_CUSTOM_3 = "custom_3";
            public static final String SETTING_SAVED_GRAM = "grams";
            public static final String SETTING_SAVED_PERCENT = "percent";
            public static final String SHARE_MY_EMAIL = "share_my_email";
            public static final String SKU = "sku";
            public static final String SORT_ORDER = "sort_order";
            public static final String SOURCE = "source";
            public static final String SOURCE_ONLINE_SEARCH = "online_search";
            public static final String SOURCE_SCAN = "scan";
            public static final String SOURCE_URL = "source_url";
            public static final String SPONSOR = "sponsor";
            public static final String STARTING_WEIGHT_DIFFERENCE = "starting_weight_difference";
            public static final String START_TIME = "start_time";
            public static final String STATE = "state";
            public static final String STATUS = "status";
            public static final String SUBSCRIPTION_STATUS = "subscription_status";
            public static final String SUNDAY = "Su";
            public static final String SYNC_V1_ERROR_CODE = "sync_v1_error_code";
            public static final String SYNC_V1_ERROR_MESSAGE = "sync_v1_error_message";
            public static final String SYNC_V1_TYPE = "sync_v1_type";
            public static final String TAB = "tab";
            public static final String TAB_NAME = "tab_name";
            public static final String TAG = "tag";
            public static final String THURSDAY = "Th";
            public static final String TIME_TO_LOG = "time_to_log";
            public static final String TOP_RIGHT = "top_right";
            public static final String TUESDAY = "Tu";
            public static final String TURNED_OFF = "turned_off";
            public static final String TURNED_ON = "turned_on";
            public static final String TYPE = "type";
            public static final String UNIT = "unit";
            public static final String UNKNOWN = "unknown";
            public static final String UNMATCHED = "unmatched";
            public static final String USER = "user";
            public static final String USERNAME = "username";
            public static final String USER_ID = "user_id";
            public static final String UTM_CAMPAIGN = "utm_campaign";
            public static final String UTM_MEDIUM = "utm_medium";
            public static final String UTM_SOURCE = "utm_source";
            public static final String VALUE_NO_CHANGE = "0";
            public static final String VARIANT = "variant";
            public static final String WATER_DIARY_UNIT = "diary";
            public static final String WATER_TRACKING_PIXEL_UNIT = "tracking_pixel";
            public static final String WEB = "web";
            public static final String WEDNESDAY = "W";
            public static final String WEIGHT_CHANGED = "weight_changed";
            public static final String WEIGHT_READ = "weight_read";
            public static final String WEIGHT_WRITE = "weight_write";
            public static final String XPROMO_APP_NAME = "app_name";
            public static final String XPROMO_CONNECT = "connect";
            public static final String XPROMO_DOWNLOAD = "download";
        }

        public static final class AuthType {
            public static final String EMAIL = "email";
            public static final String FACEBOOK = "facebook";
            public static final String USERNAME = "username";
        }

        public static final class Events {
            public static final String ACHIEVEMENT_AD_DISPLAYED = "achievements_ad_displayed";
            public static final String ACHIEVEMENT_AD_DISPLAYED_TIME = "achievements_ad_displayed_time";
            public static final String ACHIEVEMENT_AD_REQUESTED = "achievements_ad_requested";
            public static final String ACHIEVEMENT_STREAK_AD_NOT_LOADED = "achievements_unfilled";
            public static final String ACHIEVEMENT_STREAK_CELEBRATION_VIEWED = "streak_celebration_viewed";
            public static final String ACHIEVEMENT_STREAK_CTA_CLICKED = "streak_CTA_clicked";
            public static final String ACHIEVEMENT_STREAK_REACHED = "streak_reached";
            public static final String ADDFOOD_ADDBTN_CLICK = "AddFood_AddBtn_Click";
            public static final String ADD_ENTRY = "_add_entry";
            public static final String ANALYTICS_BATCH_SIZE = "analytics_batch_size";
            public static final String APP_DETAILS_CONNECT_CLICK = "App Details Screen Connect button touched";
            public static final String APP_DETAILS_LEARN_MORE_CLICK = "App Details Screen Learn More button touched";
            public static final String APP_DETAILS_VIEWED = "Viewed App Details Screen";
            public static final String APP_INDEXING_INVALID_PARAMS = "app_indexing_invalid_params";
            public static final String APP_INDEXING_INVALID_RESPONSE = "app_indexing_invalid_response";
            public static final String APP_INDEXING_PAGE_VIEW = "app_indexing_page_view";
            public static final String APP_INDEXING_TAP_AUTO_COMPLETE = "app_indexing_tap_autocomplete";
            public static final String APP_INDEXING_TAP_SEARCH_RESULT = "app_indexing_tap_search_result";
            public static final String APP_INSTALL = "app_install";
            public static final String APP_OPENED_LOGGED_IN = "app_opened_logged_in";
            public static final String APP_OPENED_LOGGED_OUT = "app_opened_logged_out";
            public static final String APP_UPGRADE = "app_upgrade";
            public static final String ASSIGN_EXERCISE = "assign_exercise";
            public static final String AUTO_FOCUS = "auto_focus_";
            public static final String AUTO_PLAY_SETTING_TOGGLED = "auto_play_setting_toggled";
            public static final String AUTO_PLAY_SETTING_VIEWED = "auto_play_setting_viewed";
            public static final String AUTO_PLAY_VIDEO_SETTING_TOGGLED = "auto_play_video_setting_toggled";
            public static final String BANNER_AD_CLICKED = "banner_ad_clicked";
            public static final String BANNER_AD_DISPLAYED = "banner_ad_displayed";
            public static final String BANNER_AD_DISPLAY_TIME = "banner_ad_display_time";
            public static final String BANNER_AD_REQUESTED = "banner_ad_requested";
            public static final String BANNER_AD_REQUESTED_OLD = "banner_ad_requested_old";
            public static final String BARCODE_CREATE_FOOD_LOGGED = "barcode_createfood_logged";
            public static final String BARCODE_CREATE_FOOD_MACROS_CHECK_FAIL = "barcode_createfood_macros_check_failed";
            public static final String BARCODE_SCAN_AUTOMATIC_MATCH = "barcode_scan_match";
            public static final String BARCODE_SCAN_MANUAL_MATCH = "barcode_scan_manual_match";
            public static final String BARCODE_SCAN_MATCH_AND_LOGGED = "barcode_scan_match_and_logged";
            public static final String BARCODE_SCAN_NO_MATCH = "barcode_scan_no_match_found";
            public static final String BARCODE_SCAN_READ = "barcode_scan_read";
            public static final String BARCODE_SCAN_START = "barcode_scan_start";
            public static final String BLOG_SUMMARY = "blog_summary";
            public static final String COMPLETED = "Completed";
            public static final String CONGRATS_START_TRACKING = "congratulations_start_tracking";
            public static final String CONNECT_FEATURED_PARTNERGALLERY = "connect_featured_partnergallery";
            public static final String CREATE_FOOD_SCREEN_BRAND_ALERT = "create_food_screen_brand_alert";
            public static final String CREATE_FOOD_SCREEN_DESCRIPTION_ALERT = "create_food_screen_description_alert";
            public static final String CREATE_FOOD_SCREEN_ONE = "create_food_screen_one";
            public static final String CREATE_FOOD_SCREEN_SERVINGS_PER_CONTAINER_ALERT = "create_food_screen_servings_per_container_alert";
            public static final String CREATE_FOOD_SCREEN_SERVING_SIZE_ALERT = "create_food_screen_serving_size_alert";
            public static final String CREATE_FOOD_SCREEN_TWO = "create_food_screen_two";
            public static final String CREATE_FOOD_SUMMARY = "create_food_summary";
            public static final String DAILY_GOALS_SAVED = "daily_goals_saved";
            public static final String DAILY_GOALS_VIEWED = "daily_goals_viewed";
            public static final String DIARY = "_diary";
            public static final String DIARY_COMPLETE_UPSELL = "SCREEN_DiaryComplete";
            public static final String DIARY_COMPLETE_VIEWED = "complete_diary_screen_viewed";
            public static final String DIARY_DASH_ASSIGN = "diary_dash_assign";
            public static final String DIARY_DELETEBTN_LONGPRESS = "Diary_DeleteBtn_LongPress";
            public static final String DIARY_TAP_NOTES = "diary_tap_notes";
            public static final String DISCONNECT_PARTNER = "disconnect_partner";
            public static final String EDITFOOD_SAVEBTN_CLICK = "EditFood_SaveBtn_Click";
            public static final String EDIT_PROFILE_CLICK = "edit_profile_click";
            public static final String EDIT_PROFILE_SCREEN_GOALS_CLICK = "edit_profile_screen_goals_click";
            public static final String ED_GOALS_SCREEN_SET_NORMAL_GOAL_WEIGHT = "ed_goals_screen_set_normal_goal_weight";
            public static final String ED_GOALS_SCREEN_SET_UNDERWEIGHT_GOAL_WEIGHT = "ed_goals_screen_set_underweight_goal_weight";
            public static final String ED_GOALS_SCREEN_SHOW_LOW_GOAL_WEIGHT_WARNING = "ed_goals_screen_show_low_goal_weight_warning";
            public static final String ED_GOAL_RECOMMENDATION_SCREEN_CLICK_CHANGE_GOAL_WEIGHT = "ed_goal_recommendation_screen_click_change_goal_weight";
            public static final String ED_GOAL_RECOMMENDATION_SCREEN_CLICK_NO_THANKS = "ed_goal_recommendation_screen_click_no_thanks";
            public static final String ED_GOAL_WEIGHT_SET_NORMAL_BMI = "ed_goal_weight_set_normal_BMI";
            public static final String ED_GOAL_WEIGHT_SET_UNDERWEIGHT_BMI = "ed_goal_weight_set_underweight_BMI";
            public static final String ED_LOW_CURRENT_WEIGHT_SHOW_GOAL_RECOMMENDATION_SCREEN = "ed_low_current_weight_show_goal_recommendation_screen";
            public static final String ED_SIGNUP_GOAL_WEIGHT_SET_NORMAL_BMI = "ed_signup_goal_weight_set_normal_BMI";
            public static final String ED_SIGNUP_GOAL_WEIGHT_SET_UNDERWEIGHT_BMI = "ed_signup_goal_weight_set_underweight_BMI";
            public static final String ED_SIGNUP_SHOW_LOW_GOAL_WEIGHT_WARNING = "ed_signup_show_low_goal_weight_warning";
            public static final String EMAIL_CHANGED = "Email Changed";
            public static final String EXERCISE_LOGGED = "exercise_logged";
            public static final String EXPERIMENT_START = "experiment_start";
            public static final String FACEBOOK_CONNECT_FAILED_OR_CANCELLED = "fb_connect_failed_or_cancelled";
            public static final String FAQ_CLICK = "FAQ_click";
            public static final String FOODSEARCH_RESULTITEM_CLICK = "FoodSearch_ResultItem_Click";
            public static final String FOODSEARCH_ZERO_RESULTS_FOUND = "FoodSearch_ZeroResultsFound";
            public static final String FOOD_CREATION_BARCODE_FLOW_ADD_NUTRIENTS_ALERT_CLICK = "food_creation_barcode_flow_add_nutrients_alert_click";
            public static final String FOOD_CREATION_BARCODE_FLOW_ADD_NUTRIENTS_ALERT_SHOWN = "food_creation_barcode_flow_add_nutrients_alert_shown";
            public static final String FOOD_CREATION_FLOW_SUGGESTION_SELECTED = "food_creation_flow_suggestion_selected";
            public static final String FOOD_CREATION_MANUAL_FLOW_ADD_NUTRIENTS_ALERT_CLICK = "food_creation_manual_flow_add_nutrients_alert_click";
            public static final String FOOD_CREATION_MANUAL_FLOW_ADD_NUTRIENTS_ALERT_SHOWN = "food_creation_manual_flow_add_nutrients_alert_shown";
            public static final String FOOD_DELETED = "food_deleted";
            public static final String FOOD_DELETE_RECENTS = "food_delete_recents";
            public static final String FOOD_DETAILS_EXPAND = "food_details_expand";
            public static final String FOOD_DETAILS_HIDE = "food_details_hide";
            public static final String FOOD_ENTRY_FAILED_CREATION = "food_entry_failed_creation";
            public static final String FOOD_LOGGED = "food_logged";
            public static final String FOOD_LOOKUP = "food_lookup";
            public static final String FORGOT_PASSWORD_LINK_CLICK = "forgot_password_link_click";
            public static final String FORGOT_PASSWORD_SCREEN_SEE = "forgot_password_screen_see";
            public static final String FORGOT_PASSWORD_SUBMIT_CLICK = "forgot_password_submit_click";
            public static final String FORGOT_PASSWORD_SUBMIT_CLICK_RESULT = "forgot_password_submit_click_result";
            public static final String FORUM_SUMMARY = "forum_summary";
            public static final String FRIENDS_TAB_FRIENDLIST = "Friends_Friendlist";
            public static final String FRIENDS_TAB_REQUESTS = "Friends_Requests";
            public static final String FRIEND_INVITE_SENT = "Friend Invite Sent";
            public static final String FRIEND_LIST_DISPLAYED = "Friend List Displayed";
            public static final String FRIEND_LIST_SKIPPED = "Friend List Skipped";
            public static final String GOOGLE_FIT = "google_fit";
            public static final String HOME_DASH_ASSIGN = "home_dash_assign";
            public static final String HOME_SUMMARY_MATH_CLICK = "HomeSummary_Math_Click";
            public static final String HOME_SUMMARY_SWIPE_LEFT = "HomeSummary_SwipeLeft";
            public static final String HOME_SUMMARY_SWIPE_RIGHT = "HomeSummary_SwipeRight";
            public static final String IMAGE_UPLOAD_FAILED = "image_upload_failed";
            public static final String IMAGE_UPLOAD_START = "image_upload_start";
            public static final String IMAGE_UPLOAD_SUCCESS = "image_upload_success";
            public static final String IMPORT_RECIPE = "import_recipe";
            public static final String INSTALL_FEATURED_PARTNERGALLERY = "install_featured_partnergallery";
            public static final String INTERSTITIAL_VIEW = "interstitial_view";
            public static final String INVITE_FROM_CONTACT_INFO = "Invite sent using imported contact information";
            public static final String IS_LAST_PRESSED_SEARCH = "is_last_pressed_search";
            public static final String JOIN_SCREEN_SIGNUP_CLICK = "join_screen_signup_click";
            public static final String JOIN_SCREEN_UA_ACCOUNT_FAQ_CLICK = "join_screen_ua_account_faq_click";
            public static final String LIMIT_AD_TRACKING = "limit_ad_tracking";
            public static final String LOCALYTICS_REGISTER_INVITE_SENT = "A friend invite has been sent";
            public static final String LOGIN_SCREEN_LOGIN_CLICK = "login_screen_login_click";
            public static final String LOGIN_SUCCESSFUL = "login_successful";
            public static final String LOGIN_UA_ACCOUNT_EXISTS = "ua_account_already_exists";
            public static final String LOGIN_UA_ACCOUNT_EXISTS_CLICK = "ua_account_already_exists_click";
            public static final String LOGIN_UNSUCCESSFUL_ERROR_SEE = "login_unsuccessful_error_see";
            public static final String LOGOUT = "logout";
            public static final String MACROS_CHANGE = "macros_change";
            public static final String MANUAL_CREATE_FOOD_LOGGED = "manual_createfood_logged";
            public static final String MEAL_GOALS_LOADED = "meal_goals_loaded";
            public static final String MEAL_GOALS_OFFLINE = "meal_goals_offline";
            public static final String MEAL_GOALS_SAVED = "meal_goals_saved";
            public static final String MEAL_GOALS_SAVED_FAILED = "meal_goals_saved_failure";
            public static final String MEAL_GOALS_TOGGLED = "meal_goals_toggled";
            public static final String MEAL_GOAL_UNIT_TOGGLED = "meal_goals_unit_toggled";
            public static final String MEDIUM_AD_CLICKED = "medium_ad_clicked";
            public static final String MEDIUM_AD_DISPLAYED = "medium_ad_displayed";
            public static final String MEDIUM_AD_DISPLAY_TIME = "medium_ad_display_time";
            public static final String MEDIUM_AD_REQUESTED = "medium_ad_requested";
            public static final String MESSAGES_TAB_INBOX = "Messages_Inbox";
            public static final String MESSAGES_TAB_SENT = "Messages_Sent";
            public static final String MFP_ACCOUNT_EXISTS = "mfp_legacy_account_exists";
            public static final String MFP_ACCOUNT_EXISTS_CLICK = "mfp_legacy_account_exists_click";
            public static final String MULTIADD_ADDITEMSBTN_CLICK = "MultiAdd_AddItemsBtn_Click";
            public static final String NAV_DRAWER_APP_GALLERY_CLICK = "NavDrawerGalleryClick";
            public static final String NAV_DRAWER_BETA_FEEDBACK_CLICK = "NavDrawerBetaFeedbackClick";
            public static final String NAV_DRAWER_BLOG_CLICK = "NavDrawerBlogClick";
            public static final String NAV_DRAWER_BROWSE_MEALS_CLICK = "NavDrawerBrowseMealsClick";
            public static final String NAV_DRAWER_CHALLENGES_CLICK = "NavDrawerChallengesClick";
            public static final String NAV_DRAWER_DIARY_CLICK = "NavDrawerDiaryClick";
            public static final String NAV_DRAWER_FORUM_CLICK = "NavDrawerForumClick";
            public static final String NAV_DRAWER_FRIENDS_CLICK = "NavDrawerFriendsClick";
            public static final String NAV_DRAWER_GOALS_CLICK = "NavDrawerGoalsClick";
            public static final String NAV_DRAWER_HELP_CLICK = "NavDrawerHelpClick";
            public static final String NAV_DRAWER_HOME_CLICK = "NavDrawerHomeClick";
            public static final String NAV_DRAWER_MESSAGES_CLICK = "NavDrawerMessagesClick";
            public static final String NAV_DRAWER_NUTRITION_CLICK = "NavDrawerNutritionClick";
            public static final String NAV_DRAWER_OUR_OTHER_APPS_CLICK = "NavDrawerOurOtherAppsClick";
            public static final String NAV_DRAWER_PREMIUM_CONTENT_CLICK = "NavDrawerPremiumContentClick";
            public static final String NAV_DRAWER_PREMIUM_UPSELL_CLICK = "NavDrawerPremiumUpsellClick";
            public static final String NAV_DRAWER_PRIVACY_CENTER_CLICK = "NavDrawerPrivacyCenterClick";
            public static final String NAV_DRAWER_PROFILE_CLICK = "NavDrawerProfileClick";
            public static final String NAV_DRAWER_RECIPES_AND_FOODS_CLICK = "NavDrawerRecipesAndFoodsClick";
            public static final String NAV_DRAWER_REMINDERS_CLICK = "NavDrawerRemindersClick";
            public static final String NAV_DRAWER_SETTINGS_CLICK = "NavDrawerSettingsClick";
            public static final String NAV_DRAWER_STEPS_CLICK = "NavDrawerStepsClick";
            public static final String NAV_DRAWER_SYNC_CLICK = "NavDrawerSyncClick";
            public static final String NAV_DRAWER_UA_SHOP_CLICK = "NavDrawerUAShopClick";
            public static final String NAV_DRAWER_VIEWED = "NavDrawerViewed";
            public static final String NAV_DRAWER_WEIGHT_CLICK = "NavDrawerWeightClick";
            public static final String NEWREMINDER_SAVEBTN_CLICK = "NewReminder_SaveBtn_Click";
            public static final String NEWS_FEED_TURNED_OFF = "news_feed_turned_off";
            public static final String NOTIFICATION_ACTION = "notification_action";
            public static final String NOTIFICATION_OPENED = "notification_opened";
            public static final String NOTIFICATION_RECEIVED = "notification_received";
            public static final String ONLINE_SEARCH_SUMMARY = "online_search_summary";
            public static final String ON_PAUSE = "on_pause";
            public static final String OPTED_IN = "opted in";
            public static final String OPT_OUT = "opted out";
            public static final String OUR_OTHER_APPS = "ourotherapps_app";
            public static final String PAIRED_FOOD_DISPLAYED = "Paired Food Displayed";
            public static final String PAIRED_FOOD_LOGGED = "Paired Foods Logged";
            public static final String PAYMENT_FAILURE = "payment_failure";
            public static final String PAYMENT_INITIATE = "payment_initiate";
            public static final String PAYMENT_POTENTIALLY_CHARGED = "premium_unknown_error";
            public static final String PAYMENT_SUCCESS = "payment_success";
            public static final String PAYMENT_UPSELL_LOAD_FAILURE = "premium_reg_upsell_failure";
            public static final String PREMIUM_EXPIRED = "premium_expired";
            public static final String PREMIUM_FEATURE_LIST = "premium_feature_list";
            public static final String PREMIUM_HOUSE_AD_COMPLETE_DIARY = "premium-house-ad-complete_diary";
            public static final String PREMIUM_HOUSE_AD_DIARY = "premium-house-ad-diary";
            public static final String PREMIUM_HOUSE_AD_DISPLAYED = "premium_house_ad_displayed";
            public static final String PREMIUM_HOUSE_AD_PROGRESS = "premium-house-ad-progress";
            public static final String PREMIUM_MENU = "premium_menu";
            public static final String PRIMARY_STEP_SOURCE_SELECTED = "primary_step_source_selected";
            public static final String PROGRESS_RECORDBTN_CLICK = "Progress_RecordBtn_Click";
            public static final String PROGRESS_REPORT_VIEWED = "progress_report_viewed";
            public static final String PUSH_NOTIFICATION_RECEIVED = "push_notification_received";
            public static final String QUICK_ADD = "quick_add";
            public static final String REACTIVATE = "reactivate";
            public static final String REACTIVATION_SCREEN_MEAL_REMINDERS = "reactivation_screen_meal_reminders";
            public static final String REACTIVATION_SCREEN_USER_CHANGED_ACTIVITY_LEVEL = "reactivation_screen_user_changed_activity_level";
            public static final String REACTIVATION_SCREEN_USER_CHANGED_CURRENT_WEIGHT = "reactivation_screen_user_changed_current_weight";
            public static final String REACTIVATION_SCREEN_USER_CHANGED_GOAL_WEIGHT = "reactivation_screen_user_changed_goal_weight";
            public static final String REACTIVATION_SCREEN_USER_CHANGED_WEIGHT_LOSS_GOAL = "reactivation_screen_user_changed_weight_loss_goal";
            public static final String RECIPE_BROWSER = "recipe_browser";
            public static final String RECIPE_BROWSER_BAD_AD = "recipe_browser_bad_ad";
            public static final String RECIPE_EDITOR_ADD_TO_DIARY = "recipe_editor_add_to_diary";
            public static final String RECIPE_INGREDIENT_MANUAL_ENTRY_SUMMARY = "recipe_ingredient_manual_entry_summary";
            public static final String RECIPE_MANUAL_IMPORT = "recipe_manual_import";
            public static final String RECIPE_MATCH_SUMMARY = "recipe_match_summary";
            public static final String RECIPE_TEXT_PARSE_SUMMARY = "recipe_text_parse_summary";
            public static final String REFERRER = "referrer";
            public static final String REMINDER_ANY_ITEM_ONE_DAY = "reminder_any_item_one_day";
            public static final String REMINDER_ANY_ITEM_SEVEN_DAYS = "reminder_any_item_seven_days";
            public static final String REMINDER_ANY_ITEM_THREE_DAYS = "reminder_any_item_three_days";
            public static final String REMINDER_BREAKFAST = "reminder_breakfast";
            public static final String REMINDER_CUSTOM_MEAL = "reminder_custom_meal";
            public static final String REMINDER_DINNER = "reminder_dinner";
            public static final String REMINDER_LUNCH = "reminder_lunch";
            public static final String REMINDER_SNACK = "reminder_snack";
            public static final String REMINDER_WEIGHT = "reminder_weight";
            public static final String RESEND_EMAIL_CONFIRM = "Resend Email Confirmation";
            public static final String REVIEW_APP_RATE_IT = "review_app_rate_it";
            public static final String SCREEN_ROTATED = "screen_rotated";
            public static final String SEARCH_FOOD_SCREEN_OVERFLOW_TAP_ITEM = "search_food_screen_overflow_tap_item";
            public static final String SELECT_CATEGORY_PARTNERGALLERY = "select_category_partnergallery";
            public static final String SERVING_SIZE_LOOKUP = "serving_sizes_lookup";
            public static final String SESSION_START = "session_start";
            public static final String SHOW_UPSELL = "payment_upsell";
            public static final String SIGNUP_ZIPCODE_VALIDATION_FAILED = "signup_zipcode_validation_failed";
            public static final String SIGN_UP = "sign_up";
            public static final String SIGN_UP_CHANGE_ENERGY = "sign_up_change_energy";
            public static final String SIGN_UP_FAILED = "sign_up_failed";
            public static final String SIGN_UP_FLOW_FORMAT = "Signup Flow %s";
            public static final String SIGN_UP_FLOW_STEP_01_GOALS = "sign_up_step_completed_01_goals";
            public static final String SIGN_UP_FLOW_STEP_02_ACTIVITY_LEVEL = "sign_up_step_completed_02_activity_level";
            public static final String SIGN_UP_FLOW_STEP_03_GENDER_AGE = "sign_up_step_completed_03_gender_age";
            public static final String SIGN_UP_FLOW_STEP_04_HEIGHT_WEIGHT = "sign_up_step_completed_04_height_weight";
            public static final String SIGN_UP_FLOW_STEP_05_WEEKLY_WEIGHT_GOAL = "sign_up_step_completed_05_weekly_weight_goal";
            public static final String SIGN_UP_FLOW_STEP_06_CREATE_USERNAME = "sign_up_step_completed_06_create_username";
            public static final String SIGN_UP_FLOW_STEP_06_EMAIL_PASSWORD = "sign_up_step_completed_06_email_password";
            public static final String SIGN_UP_FLOW_STEP_07_MARKETING_OPT_IN = "sign_up_step_completed_07_marketing_opt_in";
            public static final String SIGN_UP_REMINDERS_CHECKBOX_CHECKED = "reminders_signup_checkbox_checked";
            public static final String SIGN_UP_REMINDERS_CHECKBOX_UNCHECKED = "reminders_signup_checkbox_unchecked";
            public static final String SORT_ORDER_CHANGE = "sort_order_change";
            public static final String SPOTLIGHT_MEAL_SELECT_SEE = "spotlight_meal_select_see";
            public static final String SPOTLIGHT_MEAL_SELECT_SKIP = "spotlight_meal_select_skip";
            public static final String SPOTLIGHT_SEARCH_FIELD_SEE = "spotlight_search_field_see";
            public static final String SPOTLIGHT_SEARCH_FIELD_SKIP = "spotlight_search_field_skip";
            public static final String SPOTLIGHT_SEARCH_RESULTS_SKIP = "spotlight_search_results_skip";
            public static final String SPOTLIGHT_SERVING_SIZE_NUMBER_OF_SERVINGS = "spotlight_serving_size_number_of_servings";
            public static final String SPOTLIGHT_SERVING_SIZE_SEE = "spotlight_serving_size_see";
            public static final String SPOTLIGHT_SERVING_SIZE_SERVING_SIZE = "spotlight_serving_size_serving_size";
            public static final String SPOTLIGHT_SERVING_SIZE_SKIP = "spotlight_serving_size_skip";
            public static final String SPOTLIGHT_SERVING_SIZE_TAP_CHECKMARK = "spotlight_serving_size_tap_checkmark";
            public static final String SPOTLIGHT_ZERO_RESULTS_SEE = "spotlight_zero_results_see";
            public static final String SPOTLIGHT_ZERO_RESULTS_SKIP = "spotlight_zero_results_skip";
            public static final String STARTED = "Started";
            public static final String STARTING_WEIGHT_CLICK = "starting_weight_click";
            public static final String STARTING_WEIGHT_DIALOG_DISMISS = "starting_weight_picker_dismiss";
            public static final String STARTING_WEIGHT_PICKER_SAVE = "starting_weight_picker_save";
            public static final String START_SCREEN_SEE = "start_screen_see";
            public static final String STEP_SOURCE = "step_source";
            public static final String SYNC_V1_FAILED = "sync_v1_failed";
            public static final String TAP_FEATURED_PARTNERGALLERY = "tap_featured_partnergallery";
            public static final String TAP_SEARCH_ICON_PARTNERGALLERY = "tap_search_icon_partnergallery";
            public static final String TAP_SEARCH_RESULT_PARTNERGALLERY = "tap_search_result_partnergallery";
            public static final String UNDERAGE_REGISTRATION_FAILED = "Date Picker Signup Error Displayed";
            public static final String USERNAME_IS_TAKEN = "username_is_taken";
            public static final String USERNAME_SUGGESTIONS_DISPLAYED = "username_suggestions_displayed";
            public static final String USERNAME_SUGGESTION_SELECTED = "username_suggestion_selected";
            public static final String USER_EDITED_EXERCISE_ENTRY = "user_edited_exercise_entry";
            public static final String USER_LIKED_ITEM = "user_liked_item";
            public static final String USER_UNLIKED_ITEM = "user_unliked_item";
            public static final String VIEW_CATEGORY_SELECTOR_PARTNERGALLERY = "view_category_selector_partnergallery";
            public static final String WATER_AD_CLICKED = "water_sponsor_ad_clicked";
            public static final String WATER_AD_DISPLAYED = "water_sponsor_ad_displayed";
            public static final String WATER_AD_REQUESTED = "water_sponsor_ad_requested";
            public static final String WATER_DIALOG_VIEWED = "water_dialog_viewed";
            public static final String WEEK_STARTS_ON = "week_starts_on_setting";
            public static final String WEEK_STARTS_ON_CHANGE = "week_starts_on_setting_change";
            public static final String WELCOME_BACK_SCREEN_VIEW = "welcome_back_screen_view";
            public static final String WELCOME_FACEBOOKBTN_CLICK = "Welcome_FacebookBtn_Click";
            public static final String WELCOME_SIGNUPEMAILBTN_CLICK = "Welcome_SignupEmailBtn_Click";
            public static final String WIDGET_APP_OPENED = "widget_app_opened";
            public static final String WIDGET_INSTALLED = "widget_installed";
            public static final String WIDGET_LARGE = "widget_large";
            public static final String WIDGET_SMALL = "widget_small";
            public static final String WIDGET_UNINSTALLED = "widget_uninstalled";
            public static final String XPROMO_POSTWORKOUT_CTA_TAP = "xpromo_postworkout_cta_tap";
            public static final String XPROMO_POSTWORKOUT_DISMISSED = "xpromo_postworkout_dismissed";
            public static final String XPROMO_POSTWORKOUT_VIEW = "xpromo_postworkout_view";

            public static final class PreGDPROnBoarding {
                public static final String COUNTRY = "country_iso";
                public static final String EMAIL = "email";
                public static final String FB = "fb";
                public static final String MARKETING_EMAIL = "marketing_emails";
                public static final String NAME = "onboarding_finished";
                public static final String REMAINDERS = "reminders";
                public static final String SSO = "sso";
                public static final String TYPE = "type";
            }
        }

        public static final class Flows {
            public static final String LOGGING = "logging_operation";
            public static final String RECIPE_IMPORTER = "recipe_importer";
        }

        public static final class FoodSearchView {

            public static final class TabName {
                public static final String ALL = "history";
                public static final String FREQUENT = "frequent";
                public static final String MEALS = "meals";
                public static final String MYFOODS = "myfoods";
                public static final String RECENT = "recent";
                public static final String RECIPES = "recipes";
            }
        }

        public static final class ListType {
            public static final String ALL = "history";
            public static final String ALL_EXERCISES = "all_exercises";
            public static final String FREQUENT = "frequent";
            public static final String MEALS = "meals";
            public static final String MOST_USED_EXERCISES = "most_used_exercises";
            public static final String MY_EXERCISES = "my_exercises";
            public static final String MY_FOODS = "my_foods";
            public static final String RECENT = "recent";
            public static final String RECIPES = "recipes";
            public static final String SEARCH = "search";
        }

        public static final class LoggingType {
            public static final String CARDIO = "cardio";
            public static final String FOOD = "food";
            public static final String STRENGTH = "strength";
            public static final String WEIGHT = "weight";
        }

        public static final class Screens {
            public static final String ABOUT_US = "AboutUs";
            public static final String ADDITIONAL_NUTRIENT_GOALS = "additional_nutrient_goals";
            public static final String ADD_EXERCISE = "AddExercise";
            public static final String ADD_FOOD_ENTRY = "AddFood";
            public static final String ADD_FRIENDS_CONTACTS = "add_friends_contacts";
            public static final String ADD_WEIGHT = "add_weight";
            public static final String ADJUST_GOAL_COMPLETE = "AdjustGoalComplete";
            public static final String BARCODE_SCANNER = "barcode_scanner";
            public static final String BLOG = "blog_screen";
            public static final String CALORIE_MACRO_GOALS = "calorie_macro_goals";
            public static final String CARDIO_SEARCH = "CardioSearch";
            public static final String CHALLENGES = "challenges";
            public static final String CHALLENGE_ACHIEVEMENT_SHARE = "challenge_achievement_share";
            public static final String CONSENTS_SCREEN = "Consents";
            public static final String CUSTOM_CALORIE_MACRO_GOAL_BY_DAY = "custom_calorie_macro_goal_by_day";
            public static final String CUSTOM_MEAL_NAMES = "custom_meal";
            public static final String DEBUG_LOGS = "DebugLogs";
            public static final String DIARY = "Diary";
            public static final String DIARY_COMPLETE = "DiaryComplete";
            public static final String DIARY_SETTINGS = "DiarySettings";
            public static final String DRAWER_MENU = "Drawer";
            public static final String EATING_DISORDER_ADJUST_GOAL_COMPLETE = "EatingDisorderAdjustGoalComplete";
            public static final String EDIT_FOOD_ENTRY = "EditFood";
            public static final String EDIT_PROFILE_SCREEN = "EditProfile";
            public static final String EMAIL_SETTINGS = "EmailSettings";
            public static final String EXERCISE_CALORIES = "exercise_calories";
            public static final String EXERCISE_LIST = "exercise_list";
            public static final String FACEBOOK_SETTINGS = "FacebookSettings";
            public static final String FOOD_SEARCH = "FoodSearch";
            public static final String FRIENDS = "Friends";
            public static final String GDPR_HELP = "GDPRHelp";
            public static final String GOALS = "Goals";
            public static final String HELP = "Help";
            public static final String HOME = "Home";
            public static final String JOIN_SCREEN_SEE = "join_screen_see";
            public static final String LOGIN_SCREEN_SEE = "login_screen_see";
            public static final String MEAL_GOALS = "meal_goals";
            public static final String MESSAGES = "Messages";
            public static final String NATIVE_ADS_SETTINGS = "NativeAdsSettings";
            public static final String NEWS_FEED_SETTINGS = "NewsFeedSettings";
            public static final String NEW_REMINDER = "NewReminder";
            public static final String NEW_STATUS_OR_COMMENT = "new_status_or_comment";
            public static final String NOTIFICATION_SETTINGS = "NotificationSettings";
            public static final String NUTRITION = "Nutrition";
            public static final String OUR_OTHER_APPS = "OurOtherApps";
            public static final String PREMIUM_CONTENT = "premium_content";
            public static final String PREMIUM_ONBOARDING_CUSTOM_DASHBOARD = "custom_dashboard";
            public static final String PREMIUM_ONBOARDING_FOOD_ANALYSIS = "food_analysis";
            public static final String PREMIUM_ONBOARDING_MACRO_GOALS = "macro_goals";
            public static final String PREMIUM_ONBOARDING_WELCOME = "welcome";
            public static final String PREMIUM_UPSELL = "premium_upsell";
            public static final String PRIVACY_CENTER_SCREEN = "PrivacyCenter";
            public static final String PROGRESS = "Progress";
            public static final String RECIPES_AND_FOODS = "recipes_and_foods";
            public static final String REMINDERS = "Reminders";
            public static final String SCREEN_PREFIX = "SCREEN_";
            public static final String SETTINGS = "LegacySettingsActivity";
            public static final String SHARING_AND_PRIVACY_SETTINGS = "SharingAndPrivacySettings";
            public static final String SIGN_UP_ACTIVITY_LEVEL = "activity_level_see";
            public static final String SIGN_UP_CONGRATS = "congratulations_see";
            public static final String SIGN_UP_EMAIL_PASSWORD_USERNAME = "email_username_see";
            public static final String SIGN_UP_GENDER_BIRTHDATE = "gender_dob_see";
            public static final String SIGN_UP_HEIGHT_WEIGHT = "height_weight_see";
            public static final String SIGN_UP_MARKETING_OPT_IN = "marketing_opt_in";
            public static final String SIGN_UP_SSO_USERNAME = "sign_up_sso_username";
            public static final String SIGN_UP_SSO_WELCOME = "sso_welcome_interstital_see";
            public static final String SIGN_UP_WEEKLY_WEIGHT_GOAL = "weekly_goal_see";
            public static final String SIGN_UP_WEIGHT_GOAL = "weight_goal_see";
            public static final String SINGLE_CHALLENGE = "single_challenge";
            public static final String STEPS_SETTINGS = "StepsSettings";
            public static final String STRENGTH_SEARCH = "StrengthSearch";
            public static final String TROUBLESHOOTING = "Troubleshooting";
            public static final String UPDATE_GOALS = "UpdateGoals";
            public static final String VENUES = "venues";
            public static final String WELCOME = "Welcome";
        }

        public static final class Settings {
            public static final String INSIGHTS_OPT_IN = "insights_setting_in";
            public static final String INSIGHTS_OPT_OUT = "insights_setting_out";
        }

        public static final class UtmParameters {
            public static final String UTM_CAMPAIGN = "utm_campaign";
            public static final String UTM_MEDIUM = "utm_medium";
            public static final String UTM_SOURCE = "utm_source";
        }

        public static final class UtmValues {
            public static final String ORGANIC = "organic";
        }
    }

    public static final class Animation {
        public static final int FOOD_SEARCH_WALKTHROUGH_OUT_ANIMATION = 0;
    }

    public static final class BarcodeSearch {
        public static final int INVALID_BARCODE_CHECKSUM = 260;
        public static final int MALFORMED_BARCODE = 259;
        public static final int NONEXISTENT_FOOD_ID = 258;
        public static final int NO_MATCHES = 257;
        public static final int SHOW_ERROR_DIALOG_ID = 0;
        public static final int SUCCESS_EXACT_MATCH = 256;
        public static final int UNKNOWN_ERROR = -1;
    }

    public static final class BuildFlavors {
        public static final String BETA = "beta";
        public static final String DEV = "dev";
        public static final String GOOGLE = "google";
        public static final String QA = "qa";
    }

    public static final class BusyStates {
        public static final int ALL = 65535;
        public static final int SYNC = 32768;

        public static final class Diary {
            public static final int COMPLETE_ENTRY = 1;
        }

        public static final class EditServingSize {
            public static final int ADDING_FOOD_ENTRY = 1;
        }

        public static final class FoodEditor {
            public static final int ADDING_FOOD = 1;
        }

        public static final class FoodSearch {
            public static final int SEARCH = 4;
        }

        public static class FullScreenWebView {
            public static final int LOADING = 1;
        }

        public static final class Menus {
            public static final int ADDING_MULTI_FOOD = 16;
        }

        public static final class Status {
            public static final int LOADING = 1;
        }

        public static class TermsOfUse extends FullScreenWebView {
            public static final int AGREED = 2;
        }

        public static final class UpdateGoals {
            public static final int SAVING = 1;
        }
    }

    public static final class Cache {
        public static final String CONFIG_CACHE_KEY = "config_cache_";
        public static final String FRIENDS_CACHE_KEY = "friends_cache";
        public static final String FRIENDS_ON_MFP_KEY = "friends_on_mfp_";
    }

    public final class CalendarList {
        public static final int LOAD_MORE_BOUNDS = 2;
        public static final int LOAD_MORE_LIMIT = 5;
        public static final int MIDDLE_INDEX = 5;
        public static final int PREFETCH_LIMIT = 11;

        public CalendarList() {
        }
    }

    public static final class Challenges {
        public static final int ALREADY_JOINED_CODE = 422;
        public static final String ALREADY_JOINED_ERROR = "challenges/005";
        public static final String CHALLENGE_INVITE_EMAIL = "email";
        public static final String CHALLENGE_INVITE_FACEBOOK = "facebook";
        public static final String CHALLENGE_INVITE_MFP_FRIENDS = "mfp_friends";
        public static final String CHALLENGE_INVITE_TWITTER = "twitter";
        public static final String CHALLENGE_SHARE_EMAIL_DISABLED = "disabled";
        public static final String CHALLENGE_SHARE_EMAIL_ENABLED = "enabled";
        public static final String CHALLENGE_STATUS_ENDED = "ended";
        public static final String CHALLENGE_STATUS_STARTED = "started";
        public static final String CHALLENGE_TAB_DETAILS = "details";
        public static final String CHALLENGE_TAB_FRIENDS = "friends";
        public static final String CHALLENGE_TAB_PRIZES = "prizes";
        public static final String CHALLENGE_TAB_RULES = "rules";
        public static final String CHALLENGE_TAB_SUMMARY = "summary";
        public static final String CHALLENGE_TYPE_EXPIRED = "expired_challenge";
        public static final String CHALLENGE_TYPE_JOINED = "joined_challenge";
        public static final String CHALLENGE_TYPE_MY = "my_challenge";
        public static final String CHALLENGE_TYPE_NEW = "new_challenge";
        public static final String ENDED_CHALLENGE_DATE_FORMAT = "MM/dd/yy";
        public static final int JOINED_CHALLENGE_FRIENDS_TAB = 1;
        public static final int JOINED_CHALLENGE_PRIZES_TAB = 2;
        public static final int JOINED_CHALLENGE_RULES_TAB = 3;
        public static final int JOINED_CHALLENGE_SUMMARY_TAB = 0;
        public static final int MY_CHALLENGE_TAB = 1;
        public static final int NEW_CHALLENGE_DETAILS_TAB = 0;
        public static final int NEW_CHALLENGE_PRIZES_TAB = 1;
        public static final int NEW_CHALLENGE_RULES_TAB = 2;
        public static final int NEW_CHALLENGE_TAB = 0;
        public static final String PARTICIPANT_STATUS_JOINED = "joined";
        public static final String RELATIONSHIP_TYPE = "relationship_type";
        public static final String RELATION_TYPE_FRIENDS = "friends";
        public static final String USER_AGE = "user_age";
        public static final String USER_COUNTRY_CODE = "user_country_code";
        public static final String USER_GENDER = "user_gender";
        public static final String USER_ID = "user_id";
        public static final String USER_STATUS = "user_status";
        public static final String USER_STATUS_ALL = "all";
        public static final String USER_STATUS_ELIGIBLE = "eligible";
        public static final String USER_STATUS_JOINED = "joined";
    }

    public static final class Compose {
        public static final String COMPOSE_REPLY = "COMPOSE_REPLY";
        public static final String NEW_MESSAGE = "NEW_MESSAGE";
        public static final String NEW_MESSAGE_FROM_USER_PROFILE = "NEW_MESSAGE_FROM_USER_PROFILE";
    }

    public static class Config {
        public static final int CACHE_TTL_MILLISECONDS = 300000;
    }

    public enum ConfigParam {
        NI_SDK_MAX_PRIORITY_SLOTS_AVAILABLE("ni_sdk_max_priority_slots_available", ConfigValueType.INTEGER),
        NI_SDK_LIMIT_PRIORITY_TO_ONE_PER_CATEGORY("ni_sdk_limit_priority_to_one_per_category", ConfigValueType.BOOLEAN),
        EMAIL_VERIFICATION_LAUNCH_FREQUENCY("email_verification_launch_frequency", ConfigValueType.INTEGER),
        EMAIL_VERIFICATION_LAUNCH_HOUR_DELAY("email_verification_launch_hour_delay", ConfigValueType.INTEGER),
        NATIVE_DFP_TEMPLATE_AD_COMPAINGS_ID("ad_settings/native_dfp_template_ad_campaings_id", ConfigValueType.INTEGER),
        AMAZON_AD_APP_ID("ad_settings/amazon_ad_app_id", ConfigValueType.STRING),
        VIDEO_ADS_IMA_TAG_TEMPLATE("ad_settings/ima_tag_template", ConfigValueType.AD_UNIT),
        DFP_UNIT_VIDEO_SCREEN("ad_settings/dfp_adunits/video_gallery_screen", ConfigValueType.AD_UNIT),
        DFP_UNIT_DIARY_SCREEN("ad_settings/dfp_adunits/diary_screen", ConfigValueType.AD_UNIT),
        DFP_UNIT_PROGRESS_SCREEN("ad_settings/dfp_adunits/progress_screen", ConfigValueType.AD_UNIT),
        DFP_UNIT_COMPLETE_ENTRY_SCREEN("ad_settings/dfp_adunits/complete_entry_screen", ConfigValueType.AD_UNIT),
        DFP_UNIT_ADD_ENTRY("ad_settings/dfp_adunits/add_entry", ConfigValueType.AD_UNIT),
        DFP_UNIT_ADD_INGREDIENT_SCREEN("ad_settings/dfp_adunits/add_ingredient_screen", ConfigValueType.AD_UNIT),
        DFP_UNIT_ADD_EDIT_ENTRY("ad_settings/dfp_adunits/add_edit_entry", ConfigValueType.AD_UNIT),
        DFP_UNIT_ADD_EDIT_EXERCISE("ad_settings/dfp_adunits/add_edit_exercise", ConfigValueType.AD_UNIT),
        DFP_UNIT_DAILY_NUTRITION("ad_settings/dfp_adunits/daily_nutrition", ConfigValueType.AD_UNIT),
        DFP_UNIT_EXERCISE_SEARCH("ad_settings/dfp_adunits/exercise_search", ConfigValueType.AD_UNIT),
        DFP_UNIT_FOOD_SEARCH("ad_settings/dfp_adunits/food_search", ConfigValueType.AD_UNIT),
        DFP_UNIT_FRIENDS_FRIENDS("ad_settings/dfp_adunits/friends_friends", ConfigValueType.AD_UNIT),
        DFP_UNIT_FRIENDS_MESSAGES("ad_settings/dfp_adunits/friends_messages", ConfigValueType.AD_UNIT),
        DFP_UNIT_FRIENDS_NEWS("ad_settings/dfp_adunits/friends_news", ConfigValueType.AD_UNIT),
        DFP_UNIT_FRIENDS_PROFILE("ad_settings/dfp_adunits/friends_profile", ConfigValueType.AD_UNIT),
        DFP_UNIT_FRIENDS_REQUEST("ad_settings/dfp_adunits/friends_request", ConfigValueType.AD_UNIT),
        DFP_UNIT_HOME("ad_settings/dfp_adunits/home", ConfigValueType.AD_UNIT),
        DFP_UNIT_NOTES("ad_settings/dfp_adunits/notes", ConfigValueType.AD_UNIT),
        DFP_UNIT_WEEKLY_NUTRITION("ad_settings/dfp_adunits/weekly_nutrition", ConfigValueType.AD_UNIT),
        DFP_UNIT_SMART_WATER_SECTION_HEADER("ad_settings/dfp_adunits/smart_water_section_header", ConfigValueType.AD_UNIT),
        DFP_UNIT_SMART_WATER_ENTRY_DIALOG("ad_settings/dfp_adunits/smart_water_entry_dialog", ConfigValueType.AD_UNIT),
        DFP_UNIT_NATIVE_FIRST_POSITION("ad_settings/dfp_adunits/native_first_position", ConfigValueType.AD_UNIT),
        DFP_UNIT_NATIVE_TENTH_POSITION("ad_settings/dfp_adunits/native_tenth_position", ConfigValueType.AD_UNIT),
        DFP_UNIT_NATIVE_WITH_PARTNERS("ad_settings/dfp_adunits/native_with_partners", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_DIARY_SCREEN("ad_settings/amazon_slot_uuids/diary_screen", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_PROGRESS_SCREEN("ad_settings/amazon_slot_uuids/progress_screen", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_COMPLETE_ENTRY_SCREEN("ad_settings/amazon_slot_uuids/complete_entry_screen", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_ADD_ENTRY("ad_settings/amazon_slot_uuids/add_entry", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_ADD_INGREDIENT_SCREEN("ad_settings/amazon_slot_uuids/add_ingredient_screen", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_ADD_EDIT_ENTRY("ad_settings/amazon_slot_uuids/add_edit_entry", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_ADD_EDIT_EXERCISE("ad_settings/amazon_slot_uuids/add_edit_exercise", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_DAILY_NUTRITION("ad_settings/amazon_slot_uuids/daily_nutrition", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_EXERCISE_SEARCH("ad_settings/amazon_slot_uuids/exercise_search", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_FOOD_SEARCH("ad_settings/amazon_slot_uuids/food_search", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_FRIENDS_FRIENDS("ad_settings/amazon_slot_uuids/friends_friends", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_FRIENDS_MESSAGES("ad_settings/amazon_slot_uuids/friends_messages", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_FRIENDS_NEWS("ad_settings/amazon_slot_uuids/friends_news", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_FRIENDS_PROFILE("ad_settings/amazon_slot_uuids/friends_profile", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_FRIENDS_REQUEST("ad_settings/amazon_slot_uuids/friends_request", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_HOME("ad_settings/amazon_slot_uuids/home", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_NOTES("ad_settings/amazon_slot_uuids/notes", ConfigValueType.AD_UNIT),
        AMAZON_AD_UUID_WEEKLY_NUTRITION("ad_settings/amazon_slot_uuids/weekly_nutrition", ConfigValueType.AD_UNIT);
        
        @NotNull
        private String path;
        @NotNull
        private ConfigValueType type;

        private ConfigParam(String str, ConfigValueType configValueType) {
            this.path = str;
            this.type = configValueType;
        }

        @NotNull
        public String getPath() {
            return this.path;
        }

        @NotNull
        public ConfigValueType getType() {
            return this.type;
        }
    }

    public enum ConfigValueType {
        INTEGER,
        STRING,
        BOOLEAN,
        AD_UNIT
    }

    public static final class Consents {
        public static final String DEFAULT_CONSENTS_MATRIX_VERSION = "0";
        public static final int DEFAULT_NUMBER_OF_CONSENTS = 0;
    }

    public static final class Database {
        public static final int FIRST_SYNC_V2_VERSION = 25;
        public static final int FOODS_DB_MIGRATION_VERSION = 33;
        public static final int FOOD_ENTRY_TIMESTAMP_VERSION = 48;
        public static final int FOOD_NOTES_VERSION = 47;
        public static final int FOOD_PERMISSIONS_VERSION = 44;
        public static final int FOOD_PROMOTED_FROM_VERSION = 44;
        public static final int IMAGE_ASSOCATION_SERVICE_INTEGRATION = 35;
        public static final int IMAGE_ASSOCIATIONS_INDEX_VERSION = 46;
        public static final String MAIN_DATABASE_NAME = "myfitnesspal.db";
        public static final int MAIN_DATABASE_VERSION = 48;
        public static final int MEAL_FOOD_ID_COLUMN_VERSION = 43;
        public static final int RECIPE_V2_TABLE_VERSION = 45;
        public static final int REMOVE_ORIGINAL_EXERCISE_ID = 37;
        public static final String STOCK_DATABASE_NAME = "stock_data.db";
        public static final int STOCK_DATABASE_VERSION = 3;
        public static final int SYNC_V2_INITIAL_WORK_VERSION = 25;
        public static final int SYNC_V2_PHASE_1 = 35;

        public static final class Recipes {

            public static final class Queries {
                public static final String GET_RECIPE_BOX_ITEMS_ALPHABETICAL_WITH_SORT_FORMAT = "select \n  rbi.id, \n  rbi.master_id, \n  rbi.food_id, \n  rbi.food_description \nfrom recipe_box_items as rbi \njoin foods f on f.id = rbi.food_id\nwhere\n  rbi.user_id = ? and\n  f.deleted = 0\norder by\n  LOWER(rbi.food_description) %s \nlimit ? \noffset ?";
            }
        }

        public static final class Statements {
            public static final int DeleteDiaryNote = 31;
            public static final int DeleteFoodEntryId = 59;
            public static final int DeleteRecipeIngredientByMasterId = 74;
            public static final int DeleteWaterEntryForDate = 3;
            public static final int EraseDiaryNoteById = 33;
            public static final int EraseMealIngredientsForMealFoodId = 22;
            public static final int EraseRecipeBoxItemByLocalId = 83;
            public static final int EraseRecipeBoxItemByMasterId = 84;
            public static final int EraseRecipeIngredients = 73;
            public static final int EraseRecipeProperties = 76;
            public static final int EraseTrackedNutrientWithMasterId = 123;
            public static final int FetchDiaryNoteById = 34;
            public static final int FetchDiaryNotesOnEntryDate = 35;
            public static final int FetchTrackedNutrientById = 120;
            public static final int FetchTrackedNutrientsForUser2 = 122;
            public static final int FindRecipeBoxItemWithMasterId = 78;
            public static final int GetFoodEntriesIdCutoff = 63;
            public static final int GetFoodLocalIdForV2RecipeId = 136;
            public static final int GetMeasurementTypesForUserId = 94;
            public static final int GetOverallFoodUsageCount = 58;
            public static final int GetOwnedFoodIdsAlphabeticalAscending = 65;
            public static final int GetOwnedFoodIdsAlphabeticalDescending = 66;
            public static final int GetOwnedFoodIdsDateAscending = 126;
            public static final int GetOwnedFoodIdsDateDescending = 127;
            public static final int GetOwnedFoodIdsNoSort = 128;
            public static final int GetOwnedFoodIdsRecentlyUsed = 67;
            public static final int GetRecipeBoxItemsAlphabeticalAscending = 91;
            public static final int GetRecipeBoxItemsAlphabeticalDescending = 92;
            public static final int GetRecipeBoxItemsDateAscending = 129;
            public static final int GetRecipeBoxItemsRecentlyUsedFirst = 93;
            public static final int GetRecipeIngredientsForRecipeFoodId = 89;
            public static final int GetRecipeProperties = 90;
            public static final int InsertDeletedItem = 20;
            public static final int InsertDiaryNote = 32;
            public static final int InsertFood = 14;
            public static final int InsertFoodEntry = 1;
            public static final int InsertMealIngredient = 23;
            public static final int InsertRecipeBoxItem = 80;
            public static final int InsertRecipeIngredient = 75;
            public static final int InsertRecipeProperty = 77;
            public static final int InsertTrackedNutrient = 125;
            public static final int InsertUserProperty = 40;
            public static final int InsertWaterEntry = 4;
            public static final int LookupFoodLocalIdAndOriginalIdFromMasterId = 2;
            public static final int LookupFoodMasterAndUidsFromLocalId = 16;
            public static final int LookupUserLocalIdFromMasterId = 24;
            public static final int MarkFoodAsDeleted = 6;
            public static final int PurgeFoodEntryOriginalFoodIdReferences = 57;
            public static final int PurgeOriginalFoodIdReferences = 56;
            public static final int SelectUserProperty = 38;
            public static final int UpdateFoodEntryOriginalFoodIdReferences = 19;
            public static final int UpdateFoodType = 82;
            public static final int UpdateOriginalFoodIdReferences = 18;
            public static final int UpdateOriginalIdsForFoodId = 17;
            public static final int UpdateRecipeBoxItem = 79;
            public static final int UpdateRecipeBoxItemReferencesForRecipeFood = 81;
            public static final int UpdateUserPincode = 118;
            public static final int UpdateUserPropertiesLastSyncAt = 41;
            public static final int UpdateUserProperty = 39;
            public static final int UpdateUserRow = 5;
        }

        public static final class Users {

            public static final class Columns {
                public static final String ID = "id";
                public static final int IDX_ID = 0;
                public static final int IDX_MASTER_ID = 1;
                public static final int IDX_THIRD_PARTY_AUTH_TOKEN = 5;
                public static final int IDX_THIRD_PARTY_SERVICE_ID = 3;
                public static final int IDX_THIRD_PARTY_USER_ID = 4;
                public static final int IDX_USERNAME = 2;
                public static final String LAST_SYNC_AT = "last_sync_at";
                public static final String MASTER_ID = "master_id";
                public static final String THIRD_PARTY_AUTH_TOKEN = "third_party_auth_token";
                public static final String THIRD_PARTY_SERVICE_ID = "third_party_service_id";
                public static final String THIRD_PARTY_USER_ID = "third_party_user_id";
                public static final String USERNAME = "username";
            }

            public static final class Queries {

                public static final class UpdateUserRow {
                    public static final int IDX_LOCAL_ID = 6;
                    public static final int IDX_MASTER_ID = 1;
                    public static final int IDX_THIRD_PARTY_AUTH_TOKEN = 5;
                    public static final int IDX_THIRD_PARTY_SERVICE_ID = 3;
                    public static final int IDX_THIRD_PARTY_USER_ID = 4;
                    public static final int IDX_USERNAME = 2;
                    public static final String QUERY = "update users set master_id = ?, username = ?, third_party_service_id = ?, third_party_user_id = ?, third_party_auth_token = ? WHERE id = ?";
                }
            }
        }
    }

    public static final class DateTime {
        public static final String FRI = "fri";
        public static final String FRIDAY = "friday";
        public static final String MON = "mon";
        public static final String MONDAY = "monday";
        public static final String SAT = "sat";
        public static final String SATURDAY = "saturday";
        public static final String SUN = "sun";
        public static final String SUNDAY = "sunday";
        public static final String THU = "thu";
        public static final String THURSDAY = "thursday";
        public static final String TUE = "tue";
        public static final String TUESDAY = "tuesday";
        public static final String WED = "wed";
        public static final String WEDNESDAY = "wednesday";
    }

    public static final class Dialogs {
        public static final int ACQUIRE_CAMERA_FAILED = 7621;
        public static final int CHANGE_UNIT = 7624;
        public static final int COPY_MEAL = 7619;
        public static final int COPY_TO_DATE_DIALOG = 7616;
        public static final int DEVICE_OFFLINE_DIALOG = 7613;
        public static final int EDIT_SERVINGS_DIALOG = 7605;
        public static final int EDIT_SERVINGS_DIALOG_NO_KEYBOARD = 7623;
        public static final int EXERCISE_QUICK_TOOLS_DIALOG = 7615;
        public static final int FOOD_QUICK_TOOLS_DIALOG = 7614;
        public static final int INVALID_BARCODE_CHECKSUM_DIALOG = 7611;
        public static final int INVALID_INPUT = 7607;
        public static final int MALFORMED_BARCODE_DIALOG = 7610;
        public static final int MEAL_NAMES_DIALOG = 7606;
        public static final int NONEXISTENT_FOOD_ID_DIALOG = 7612;
        public static final int NO_MATCH_DIALOG = 7622;
        public static final int OUT_OF_RANGE_ERROR_DIALOG = 7604;
        public static final int QUICK_ADD = 7620;
        public static final int QUICK_ADD_CALORIES_DIALOG = 7602;
        public static final int REMINDERS = 7625;
        public static final int REMOVE_FRIEND_PROGRESS_DIALOG = 534;
        public static final int SAVE_MEAL = 7618;
        public static final int SEARCH_IN_PROGRESS_DIALOG = 6007;
        public static final int SERVING_ERROR_DIALOG = 7603;
        public static final int UNKNOWN_ERROR_DIALOG = 7609;

        public static final class Fragments {
            public static final String ACTIVITY_LEVEL_DIALOG = "activityLevelDialogFragment";
            public static final String ADD_CUSTOM_FOOD_IMPROVEMENT_DIALOG = "add_custom_food_improvement_dialog";
            public static final String BARCODE_SCAN_DIALOG = "barcode_scan_dialog";
            public static final String BIRTHDATE = "birthdatePicker";
            public static final String CALORIES_ADD_ERROR_DIALOG = "calories_add_error_dialog";
            public static final String COUNTRY_PICKER_DIALOG = "country_picker_dialog";
            public static final String CREATE_RECIPE_FRAGMENT = "create_recipe_fragment";
            public static final String CURRENT_HEIGHT_DIALOG = "currentHeightDialog";
            public static final String CURRENT_WEIGHT_DIALOG = "currentWeightDialog";
            public static final String DAY_OF_WEEK_DIALOG = "day_of_week_dialog";
            public static final String DIARY_QUICK_TOOL_DIALOG = "diary_quick_tools_dialog";
            public static final String EDIT_SERVINGS_DIALOG = "editServingsDialog";
            public static final String EXERCISE_GOAL_DIALOG = "exerciseGoalsDialog";
            public static final String EXERCISE_TYPE_DIALOG = "exercise_type_dialog";
            public static final String FRIEND_DIARY_PASSWORD_DIALOG = "friend_diary_password_dialog";
            public static final String GENDER_DIALOG = "genderDialogFragment";
            public static final String GOAL_WEIGHT_DIALOG = "goalWeightDialog";
            public static final String HEIGHT_DIALOG = "heightDialog";
            public static final String INVALID_INPUT = "invalid_input";
            public static final String LOCATION_DIALOG = "locationDialog";
            public static final String LONG_PRESS_DIALOG = "long_press_dialog";
            public static final String MACRO_NUTRIENT_DIALOG = "macro_nutrient_dialog";
            public static final String MEAL_NAMES_DIALOG = "mealNamesDialog";
            public static final String MEASUREMENT_DIALOG = "measurementDialog";
            public static final String MORE_DIALOG = "more_dialog";
            public static final String NET_ENERGY_DIALOG = "netEnergyDialog";
            public static final String NOTE_TYPE_DIALOG = "note_type_dialog";
            public static final String NUMBER_PICKER_DIALOG = "number_picker_dialog";
            public static final String PIN_CODE_PICKER_DIALOG = "pin_code_picker_dialog";
            public static final String PROFILE_PHOTO = "profile_photo";
            public static final String PROGRESS_PHOTO_DIALOG = "progress_photo_dialog";
            public static final String QUICK_ADD_DIALOG = "quickAddDialog";
            public static final String RATE_AND_REVIEW = "rate_and_review";
            public static final String REMINDER_FREQUENCY_DIALOG = "reminder_frequency_dialog";
            public static final String SIGN_UP_GENDER_AGE_FRAGMENT_TAG = "SignUpGenderAgeFragment";
            public static final String STARTING_WEIGHT_DIALOG = "startingWeightDialog";
            public static final String STATUS_PHOTO_DIALOG = "status_photo_dialog";
            public static final String TIMEZONE_PICKER_DIALOG = "timezone_picker_dialog";
            public static final String TIME_PICKER = "time_picker";
            public static final String UNITS_DIALOG = "units_dialog";
            public static final String WATER_DIALOG = "waterDialog";
            public static final String WEIGHT_GOAL_DIALOG = "weightGoalDialogFragment";
        }
    }

    public static final class Diary {
        public static final long DEFAULT_FRIEND_MASTER_ID = Long.MIN_VALUE;
        public static final int SECTION_CARDIO = 6;
        public static final int SECTION_EXERCISE = 3;
        public static final int SECTION_MEAL = 2;
        public static final int SECTION_NOTES = 5;
        public static final int SECTION_STRENGTH = 7;
        public static final int SECTION_WATER = 4;

        public static final class FriendDiaryErrorCodes {
            public static final int ACCESS_DENIED = 257;
            public static final int INVALID_PASSWORD = 258;
            public static final int USER_DOES_NOT_EXIST = 256;
        }
    }

    public static final class EntryType {
        public static final String EXERCISE = "exercise";
        public static final String MEAL = "meal";
    }

    public static final class Errors {
        public static final int FACEBOOK_NO_EMAIL = 2001;
        public static final int FACEBOOK_UNKNOWN = 0;
        public static final int USER_UNDERAGE = 2000;
    }

    public static final class Exercise {

        public static final class ActivityLevel {
            public static final String ACTIVE = "Active";
            public static final String LIGHTLY_ACTIVE = "Lightly Active";
            public static final String SEDENTARY = "Sedentary";
            public static final String VERY_ACTIVE = "Very Active";
        }

        public static final class Cardio {
            public static final int MINUTES_PERFORMED_DAILY_LIMIT = 1440;
        }

        public static final class ExerciseTypeName {
            public static final String CARDIOVASCULAR = "Cardiovascular";
            public static final String STRENGTH = "Strength";
        }

        public static final class Properties {
            public static final String SOURCE = "source";
            public static final String START_TIME = "start_time";
        }

        public static final class Source {
            public static final String IPHONE = "Iphone";
        }
    }

    public static final class Extras {
        public static final String ACHIEVEMENT_EVENT_NO = "no";
        public static final String ACHIEVEMENT_EVENT_YES = "yes";
        public static final String ACTIVE_BUTTON = "activeButton";
        public static final String ACTIVE_TAB = "activeTab";
        public static final String ACTIVITY_ENTRY_DETAIL = "activity_entry_detail";
        public static final String ADD_BTN_TEXT = "addBtnText";
        public static final String ADD_COMMENT = "add_comment";
        public static final String ADD_OR_EDIT_ENTRY_ON_START = "add_or_edit_entry_on_start";
        public static final String ADD_TO_DIARY_AFTER_CREATE = "add_to_diary_after_create";
        public static final String ADD_TO_MEAL_ON_SUCCESS = "addToMealOnSuccess";
        public static final String ALLOW_NEGATIVE_CALORIE_ADJUSTMENT = "allow_negative_calorie_adjustment";
        public static final String APPS_DEVICES_FILTER_CATEGORY = "apps_devices_filter_category";
        public static final String APP_JUST_STARTED = "appStarted";
        public static final String ASSIGN_EXERCISE_CUSTOM = "custom";
        public static final String ASSIGN_EXERCISE_NUTRIENT = "nutrient_goal";
        public static final String ASSIGN_EXERCISE_OFF = "off";
        public static final String BARCODE = "barcode";
        public static final String BODY = "body";
        public static final String CALLED_FROM_WITHIN_APP = "withinApp";
        public static final String CALLER = "caller";
        public static final String CALLER_KEY = "calley_key";
        public static final String CALORIES = "calories";
        public static final String CARBS = "carbs";
        public static final String CHALLENGE_EMAIL_BODY_SHARE = "challenge_email_body_share";
        public static final String CHALLENGE_EMAIL_SUBJECT_SHARE = "challenge_email_subject_share";
        public static final String CHALLENGE_ID = "challenge_id";
        public static final String CHALLENGE_NAME = "challenge_name";
        public static final String CHALLENGE_SOCIAL_DATA = "challenge_social_data";
        public static final String CHALLENGE_TWITTER_SHARE = "challenge_twitter_share";
        public static final String CHANNEL = "channel";
        public static final String CHART_SUB_TYPE = "chart_sub_type";
        public static final String CHART_TYPE = "chart_type";
        public static final String CHECKED_ITEMS = "checked_items";
        public static final String CLICKED_SERVING_SIZE = "clicked_serving_size";
        public static final String COMMENT = "comment";
        public static final String COMMENT_ID = "comment_id";
        public static final String CONTENT_URI = "content_uri";
        public static final String CREATE = "create";
        public static final String CURRENT_GRAPH = "current_graph";
        public static final String CURRENT_PASSWORD = "currentPassword";
        public static final String CURRENT_STATUS = "currentStatus";
        public static final String CURRENT_SW_DATE = "current_sw_date";
        public static final String CURRENT_USERNAME = "currentUsername";
        public static final String CURRENT_VALUE = "current_value";
        public static final String CURRENT_WEIGHT = "current_weight";
        public static final String CUSTOM_DAYS = "custom_days";
        public static final String CUSTOM_GOAL_DELETED = "custom_goal_deleted";
        public static final String CUSTOM_GOAL_DISPLAY = "custom_goal_display";
        public static final String DAILY_GOAL = "daily_goal";
        public static final String DATA = "data";
        public static final String DATE = "date";
        public static final String DAY_OF_MONTH = "day_of_month";
        public static final String DAY_OF_WEEK = "day_of_week";
        public static final String DEEP_LINK_DESTINATION = "__deep_link_destination__";
        public static final String DEEP_LINK_IS_LOCAL = "__deep_link_is_local__";
        public static final String DEFAULT_GOAL_DISPLAY = "energy_breakdown";
        public static final String DEFAULT_VALUE = "default_value";
        public static final String DESTINATION_USER_UID = "destinationUserUid";
        public static final String DIARY_PARENT = "diary";
        public static final String DIARY_SETTINGS_PARENT = "diary_settings";
        public static final String DISABLE_EXIT_TO_LAUNCHER = "disable_exit_to_launcher";
        public static final String DISABLE_NEW_TASK = "disableNewTask";
        public static final String DISPLAY_OPTIONS_ENABLED = "displayOptionsEnabled";
        public static final String EDIT = "edit";
        public static final String EDITABLE = "editable";
        public static final String EMAIL_OPTIN_DATA = "email_optin_data";
        public static final String ENERGY_CHANGED = "energy_changed";
        public static final String ERROR_CODE = "errorCode";
        public static final String EVENT_ID = "event_id";
        public static final String EVENT_SOURCE = "event_source";
        public static final String EVENT_TO_REPORT_ON_LOAD = "event_to_report_on_load";
        public static final String EXACT_MATCH = "exactMatch";
        public static final String EXERCISE = "exercise";
        public static final String EXERCISE_CALORIES_UPDATED = "exercise_calories_updated";
        public static final String EXERCISE_ENTRY = "exercise_entry";
        public static final String EXERCISE_TYPE = "exerciseType";
        public static final int EXTRA_ABSENT_INT = Integer.MIN_VALUE;
        public static final String EXTRA_PROMOTED_FEATURE = "promoted_feature";
        public static final String FACEBOOK_REDIRECT = "facebook_redirect";
        public static final String FAT = "fat";
        public static final String FOCUS_SEARCH = "focusSearch";
        public static final String FOOD = "food";
        public static final String FOOD_ANALYZER_DATA = "food_analyzer_data";
        public static final String FOOD_ENTRY = "food_entry";
        public static final String FOOD_HAS_MASTER_ID = "food_has_master_id";
        public static final String FOOD_LIST = "food_list";
        public static final String FOOD_LOCAL_ID = "food_local_id";
        public static final String FOOD_MASTER_ID = "food_master_id";
        public static final String FOOD_ORIGINAL_ID = "food_original_id";
        public static final String FOOD_ORIGINAL_MASTER_ID = "food_original_master_id";
        public static final String FOOD_ORIGINAL_UID = "food_original_uid";
        public static final String FOOD_PORTION = "food_portion";
        public static final String FOOD_RESULT = "foodResult";
        public static final String FOOD_UID = "food_uid";
        public static final String FOOD_V2_LOGGING = "food_v2_logging";
        public static final String FOOD_VERIFIED = "food_verified";
        public static final String FRIENDS_IN_CHALLENGE = "friends_in_challenge";
        public static final String FRIEND_TO_INVITE = "friendToInviteUsername";
        public static final String FROM_INTERSTITIAL = "from_interstitial";
        public static final String GALLERY_VIEW_MODE = "gallery_view_mode";
        public static final String GOOGLE_FIT_CLIENT_ID = "google_fit";
        public static final String GO_HOME_INSTEAD_OF_BACK = "go_home_instead_of_back";
        public static final String GO_TO_DIARY = "go_to_diary";
        public static final String HARDWARE_TRACKERS_ONLY = "hardware_trackers_only";
        public static final String HEART_HEALTHY_GOAL_DISPLAY = "heart_healthy_remaining";
        public static final String HIDE_WHEN_DELETED = "hide_when_deleted";
        public static final String HOME_PARENT = "home";
        public static final String ID = "id";
        public static final String IMAGE_ID = "image_id";
        public static final String IMAGE_REPORTED = "image_reported";
        public static final String IMAGE_STATUS_METADATA = "image_status_metadata";
        public static final String IMAGE_URL = "image_url";
        public static final String IMPORT_FROM_WEB = "import_from_web";
        public static final String INGREDIENT = "ingredient";
        public static final String INITIAL_VALUE_TO_EDIT = "initial_value_to_edit";
        public static final String INPUT_MODE = "input_mode";
        public static final String IS_CHALLENGE_PRIVATE = "is_challenge_private";
        public static final String IS_CONGRATS_IMAGE = "is_congrats_image";
        public static final String IS_EDITING = "IsEditing";
        public static final String IS_EMAIL_OPTIN_REQUIRED = "is_email_optin_required";
        public static final String IS_FOOD_ENTRY = "is_food_entry";
        public static final String IS_FOR_EDIT = "isForEdit";
        public static final String IS_FRIEND_REQUEST = "isFriendRequest";
        public static final String IS_FROM_SETTINGS = "is_from_settings";
        public static final String IS_LANDSCAPE = "is_landscape";
        public static final String IS_PREV_MACROS_BY_GRAM = "isPreviousMacroGoalsByGram";
        public static final String IS_PROFILE_VISIBLE = "isProfileVisible";
        public static final String IS_STARTING_WEIGHT = "is_starting_weight";
        public static final String IS_VIEWING_MULTI_ADD_ITEMS = "isViewingMultiAddItems";
        public static final String IS_WALK_THROUGH = "is_walk_through";
        public static final String ITEM_TYPE = "itemType";
        public static final String JUST_JOINED_FB_FRIENDS = "justJoinedFbFriends";
        public static final String LIST_TYPE = "list_type";
        public static final String LOCALIZED_ENERGY = "localized_energy";
        public static final String LOW_CARB_GOAL_DISPLAY = "low_carb_remaining";
        public static final String MACROS_REM_GOAL_DISPLAY = "macros_remaining";
        public static final String MACRO_INDEX = "macro_index";
        public static final String MATCHED_INGREDIENTS = "matched_ingredients";
        public static final String MATCH_REQUEST = "match_request";
        public static final String MATCH_RESULT = "match_result";
        public static final String MAX_VALUE = "max_value";
        public static final String MAX_WEIGHT = "max_weight";
        public static final String MEAL_FOOD = "meal_food";
        public static final String MEAL_ID = "meal_id";
        public static final String MEAL_INDEX = "mealIndex";
        public static final String MEAL_NAME = "mealName";
        public static final String MEAL_NAMES = "meal_names";
        public static final String MEAL_VERSION = "meal_version";
        public static final String MEASUREMENT_NAME = "measurement_name";
        public static final String MEASUREMENT_TYPE_NAME = "measurementTypeName";
        public static final String MESSAGE = "message";
        public static final String MESSAGE_BODY = "messageBody";
        public static final String MESSAGE_ID = "message_id";
        public static final String MESSAGE_UID = "message_uid";
        public static final String MFP_FOOD = "mfp_food";
        public static final String MFP_FRIENDS = "mfpFriends";
        public static final String MFP_MOBILE_ANDROID = "mfp-mobile-android-google";
        public static final String MFP_MOBILE_IOS = "mfp-mobile-ios";
        public static final String MINI_USER_INFO = "mini_user_info";
        public static final String MIN_VALUE = "min_value";
        public static final String MIN_WEIGHT = "min_weight";
        public static final String MONTH = "month";
        public static final String MYFITNESSPAL_CLIENT_ID = "myfitnesspal";
        public static final String NAVIGATE_TO = "navigate_to";
        public static final String NAVIGATE_TO_HOME = "go_to_home";
        public static final String NEWSFEED = "newsfeed";
        public static final String NEWSFEED_ENTRY_ID = "newsfeed_entry_id";
        public static final String NEWSFEED_ID = "newsfeed_id";
        public static final String NON_MFP_FRIENDS = "nonMfpFriends";
        public static final String NOTIFICATION_ID = "notification_id";
        public static final String NUM_OF_SERVINGS = "num_of_servings";
        public static final String NUTRIENT = "nutrient";
        public static final String NUTRIENTS_KEY = "nutrients_list";
        public static final String NUTRIENT_GOAL = "nutrient_goal";
        public static final String NUTRITIONAL_VALUES = "nutritional_values";
        public static final String OBJECT_MASTER_DATABASE_ID = "object_master_database_id";
        public static final String OPERATION = "operation";
        public static final String ORIENTATION_LANDSCAPE = "landscape";
        public static final String ORIENTATION_PORTRAIT = "portrait";
        public static final String ORIGINAL_EXTRAS = "original_extras";
        public static final String ORIGINAL_INTENT = "original_intent";
        public static final String ORIGINAL_RAW_TEXT = "original_raw_text";
        public static final String OUTPUT_FORMAT = "outputFormat";
        public static final String PAIRED_FOODS = "paired_foods";
        public static final String PARSED_RECIPE = "parsed_recipe";
        public static final String PARTNER_APP = "partner_app";
        public static final String PARTNER_NAME = "partner_name";
        public static final String PASS = "pass";
        public static final String PASSWORD = "password";
        public static final String PAYMENT_RESULT = "payment_result";
        public static final String POSITION = "position";
        public static final String PREMIUM_CONTENT = "premium_content";
        public static final String PREMIUM_SERVINGS = "serving(s)";
        public static final String PROJECTED_WEIGHT = "projected_weight";
        public static final String PROTEIN = "protein";
        public static final String QUERY = "query";
        public static final String REASON_ID = "reason_id";
        public static final String RECIPE = "recipe";
        public static final String RECIPE_NAME = "recipe_name";
        public static final String RECIPE_ORIGINAL_UID = "recipe_original_uid";
        public static final String RECIPE_UID = "recipe_uid";
        public static final String RECIPIENT = "recipient";
        public static final String REFERRER = "referrer";
        public static final String REFERRER_ADD_FOOD = "add_food";
        public static final String REFERRER_ADD_FOOD_DEEPLINK_MIXIN = "add_food_deeplink_mixin";
        public static final String REFERRER_DIARY_ADD_DEEPLINK = "diary_add_deeplink";
        public static final String REFERRER_DIARY_ADD_FOOD = "diary_add_food";
        public static final String REFERRER_DIARY_DELEGATE = "diary_delegate";
        public static final String REFERRER_DIARY_JUST_LOGGED = "just_logged";
        public static final String REFERRER_DIARY_RECIPE_IMPORT = "recipe_import";
        public static final String REFERRER_EXTERNAL = "external";
        public static final String REFERRER_FAB = "fab";
        public static final String REFERRER_HOME_SUMMARY = "home_summary";
        public static final String REFERRER_LOCAL_FOOD_SEARCH_FRAGMENT = "local_food_search_fragment";
        public static final String REFERRER_MULTI_ADD = "multi_add";
        public static final String REFERRER_MYRECIPES_ACTION_BAR_ADD = "myrecipes_action_bar_add";
        public static final String REFERRER_NAVIGATION = "navigation";
        public static final String REFERRER_ONLINE_FOOD_SEARCH_FRAGMENT = "online_food_search_fragment";
        public static final String REFERRER_RECIPE_EDITOR_FRAGMENT = "recipe_editor_fragment";
        public static final String REFERRER_RECIPE_IMPORTER = "recipe_importer";
        public static final String REMINDER = "reminder";
        public static final String REMINDER_DESCRIPTION = "reminder_description";
        public static final String REMINDER_MEAL_NAME = "reminder_meal_name";
        public static final String REMINDER_TYPE = "reminder_type";
        public static final String REPORTEE_ID = "reportee_id";
        public static final String REQUEST_CODE = "REQUEST_CODE";
        public static final String REQUIRE_MACROS = "require_macros";
        public static final String RETURN_DATA = "return-data";
        public static final String SCANNED_ENRTY = "scanned_entry";
        public static final String SEARCH_ON_NO_MATCH = "searchOnNoMatch";
        public static final String SEARCH_RESULTS = "search_results";
        public static final String SECTION = "section";
        public static final String SELECTED_ARTIFACT_TYPE = "selected_artifact_type";
        public static final String SELECTED_INDEX = "selected_index";
        public static final String SELECTED_SERVINGS = "selectedServings";
        public static final String SERVINGS = "servings";
        public static final String SERVING_SIZE = "serving_size";
        public static final String SERVING_SIZE_INDEX = "serving_size_index";
        public static final String SETTINGS_PARENT = "settings_parent";
        public static final String SETTING_OFF = "off";
        public static final String SETTING_ON = "on";
        public static final String SHARE_URI = "share_uri";
        public static final String SHOW_AS_TOP_LEVEL_ACTIVITY = "extra_show_as_top_level_navigation";
        public static final String SHOW_EXERCISE_TYPES = "show_exercise_types";
        public static final String SHOW_MEALS = "show_meals";
        public static final String SHOW_RECEIVED_MENU_REQUEST_SNACKBAR = "show_received_menu_request_snackbar";
        public static final String SKIP_PRIVACY_CHECK_ONCE = "skip_privacy_check_once";
        public static final String SOURCE = "source";
        public static final String STARTED_FROM_DEEP_LINK = "started_from_deep_link";
        public static final String STARTED_FROM_WIDGET_OR_NOTIFICATION = "started_from_widget_or_notification";
        public static final String SUMMARY_TYPE_KEY = "summery_type";
        public static final String TITLE = "title";
        public static final String TITLE_FOR_NOTE = "titleForNote";
        public static final String TYPE = "type";
        public static final String UNABLE_TO_POST_TO_SERVER = "unable_to_post_to_server";
        public static final String UNKNOWN = "unknown";
        public static final String UNMATCHED_INGREDIENTS = "unmatched_ingredients";
        public static final String URL = "url";
        public static final String USERNAME = "username";
        public static final String USERNAMES = "usernames[]";
        public static final String USER_INFO = "userInfo";
        public static final String USER_MUST_AGREE = "user_must_agree";
        public static final String USER_NAME = "user_name";
        public static final String USER_UID = "user_uid";
        public static final String UTM_CAMPAIGN = "utm_campaign";
        public static final String VARIANT_ID = "variant_id";
        public static final String VERSION = "version";
        public static final String WEIGHT_OPTION_INDEX = "weight_option_index";
        public static final String WEIGHT_TYPE = "weight_type";
        public static final String WELCOME_NOTIFICATION = "welcome_notification";
        public static final String YEAR = "year";
    }

    public static final class FAQ {
        public static final String AD_FREE_FAQ = "1935017";
        public static final int AD_FREE_TAG = 104;
        public static final int BASE_TAG = 100;
        public static final String CALORIE_ADJUSTMENT_ARTICLE = "1084232";
        public static final int CALORIE_ADJUSTMENT_TAG = 102;
        public static final String CHANGE_MEAL_NAMES_ARTICLE = "11171";
        public static final int CHANGE_MEAL_NAMES_TAG = 109;
        public static final String FOOD_ANALYSIS_FAQ = "1935037";
        public static final int FOOD_ANALYSIS_TAG = 106;
        public static final String GOAL_RECALCULATION_ARTICLE = "1375583";
        public static final int GOAL_RECALCULATION_TAG = 103;
        public static final String OFFLINE_SEARCH_ARTICLE = "1029725";
        public static final int OFFLINE_SEARCH_TAG = 101;
        public static final String PRIORITY_SUPPORT_FAQ = "1935032";
        public static final int PRIORITY_SUPPORT_TAG = 107;
        public static final String QUICK_ADD_FAQ = "403947";
        public static final int QUICK_ADD_TAG = 105;
        public static final String STRENGTH_CALORIES_ARTICLE = "11170";
        public static final int STRENGTH_CALORIES_TAG = 110;
        public static final String USER_POTENTIALLY_CHANGED_ARTICLE = "1957687";
        public static final int USER_POTENTIALLY_CHARGED_TAG = 108;
    }

    public static final class Facebook {

        public static final class Login {
            public static final Collection<String> PERMISSIONS = Arrays.asList(new String[]{"public_profile", "email", Permissions.BIRTHDAY});
            public static final Collection<String> PERMISSIONS_PLUS_FRIENDS = Arrays.asList(new String[]{"public_profile", "email", Permissions.BIRTHDAY, Permissions.USER_FRIENDS});
        }

        public static final class Permissions {
            public static final String BIRTHDAY = "user_birthday";
            public static final String EMAIL = "email";
            static final String PUBLIC_PROFILE = "public_profile";
            public static final String PUBLISH_ACTIONS = "publish_actions";
            public static final String USER_FRIENDS = "user_friends";
        }
    }

    public static final class FoodSearch {
        public static final String CREATE_NEW = "Create New";
        public static final int DUMMY_TYPE = -1;
        public static final String EMPTY = "Empty";
        public static final String FOOTER_FOOD_ITEM = "Footer Food Item";
        public static final int ITEMS_PER_PAGE = 50;
        public static final int PAGES_TO_LOAD_INITIALLY = 4;
    }

    public static final class Fragments {
        public static final String DASHBOARD_CUSTOM_NUTRIENTS_FRAGMENT = "custom_summary_fragment";
        public static final String DASHBOARD_PRESET_FRAGMENT = "change_summary_fragment";
        public static final String GOALS_PREMIUM_FRAGMENT = "goals_premium";
        public static final String JOIN_CHALLENGE_ADD_FRIENDS = "challengeAddFriends";
        public static final String JOIN_CHALLENGE_EMAIL_PREFS = "challengeEmailPrefs";
    }

    public static class Gender {
        public static final int FEMALE = 0;
        public static final int MALE = 1;
        public static final int UNKNOWN = 2;
    }

    public static final class Goals {
        public static final int BMI_CONVERSION_FACTOR = 703;
        public static final double BMI_LOW_THRESHOLD = 18.5d;
        public static final String GOAL_CALCIUM_PER_DAY = "goal_calcium_per_day";
        public static final String GOAL_CALORIES_BURNED_PER_WEEK = "goal_calories_burned_per_week";
        public static final String GOAL_CALORIES_PER_DAY = "goal_calories_per_day";
        public static final String GOAL_CARBS_PER_DAY = "goal_carbs_per_day";
        public static final String GOAL_CHOLESTEROL_PER_DAY = "goal_cholesterol_per_day";
        public static final String GOAL_FAT_PER_DAY = "goal_fat_per_day";
        public static final String GOAL_FIBER_PER_DAY = "goal_fiber_per_day";
        public static final String GOAL_IRON_PER_DAY = "goal_iron_per_day";
        public static final String GOAL_LOSS_PER_WEEK = "goal_loss_per_week";
        public static final String GOAL_MONOSAT_PER_DAY = "goal_monounsaturated_fat_per_day";
        public static final String GOAL_POLYSAT_PER_DAY = "goal_polyunsaturated_fat_per_day";
        public static final String GOAL_POTASSIUM_PER_DAY = "goal_potassium_per_day";
        public static final String GOAL_PROTEIN_PER_DAY = "goal_protein_per_day";
        public static final String GOAL_SAT_FAT_PER_DAY = "goal_saturated_fat_per_day";
        public static final String GOAL_SODIUM_PER_DAY = "goal_sodium_per_day";
        public static final String GOAL_SUGAR_PER_DAY = "goal_sugar_per_day";
        public static final String GOAL_TRANS_PER_DAY = "goal_trans_fat_per_day";
        public static final String GOAL_VIT_A_PER_DAY = "goal_vitamin_a_per_day";
        public static final String GOAL_VIT_C_PER_DAY = "goal_vitamin_c_per_day";
        public static final double MAX_BMI = 25.0d;
        public static final double MIN_BMI = 18.5d;
        public static final String MIN_PER_WORKOUT = "minutes_per_workout";
        public static final String PROJECTED_POUNDS_LOST_PER_WEEK = "projected_pounds_lost_per_week";
        public static final String WORKOUTS_PER_WEEK = "workouts_per_week";

        public static final class Nutrient {
            public static final String CALCIUM = "calcium";
            public static final String CALORIES = "calories";
            public static final String CARBOHYDRATES = "carbohydrates";
            public static final String CHOLESTEROL = "cholesterol";
            public static final String FAT = "fat";
            public static final String FIBER = "fiber";
            public static final String IRON = "iron";
            public static final String MONOUNSATURATED_FAT = "monounsaturated_fat";
            public static final String POLYUNSATURATED_FAT = "polyunsaturated_fat";
            public static final String POTASSIUM = "potassium";
            public static final String PROTEIN = "protein";
            public static final String SATURATED_FAT = "saturated_fat";
            public static final String SODIUM = "sodium";
            public static final String SUGAR = "sugar";
            public static final String TRANS_FAT = "trans_fat";
            public static final String VITAMIN_A = "vitamin_a";
            public static final String VITAMIN_C = "vitamin_c";
        }
    }

    public static final class Graphs {

        public static class Colors {
            public static final int[] EMPTY_COLORS_RES = {R.color.graph_empty};
            public static final int[] MACROS_COLORS_RES = {R.color.graph_carbs, R.color.graph_fat, R.color.graph_protein};
            public static final int[] MEAL_COLORS_RES = {R.color.graph_meal_1, R.color.graph_meal_2, R.color.graph_meal_3, R.color.graph_meal_4, R.color.graph_meal_5, R.color.graph_meal_6};
        }

        public static class SubTypes {
            public static final int DEFAULT = 1;
            public static final int OTHER = 2;
        }

        public static class Types {
            public static final String CALORIES = "Calories";
            public static final String FOOD_LISTS = "FoodLists";
            public static final String MACROS = "Macros";
            public static final String NUTRIENTS = "Nutrients";
            public static final String SINGLE_NUTRIENT = "SingleNutrient";
        }
    }

    public static final class Http {
        public static final String ACCOUNT = "account";
        public static final String ALL = "all";
        public static final String BRAND_NAME = "brand_name";
        public static final String DESCRIPTION = "description";
        public static final String EXERCISE_ID = "exercise_id";
        public static final String FIELDS_ARRAY = "fields[]";
        public static final String FOODS = "foods";
        public static final String ID = "id";
        public static final String IDS = "ids[]";
        public static final String LINK = "link";
        public static final String LOG_OBJECT_REQUIRED = "log_object_required";
        public static final String MAX_ITEMS = "max_items";
        public static final String NATIVE_CLIENT = "native_client";
        public static final String NUTRITIONAL_CONTENTS = "nutritional_contents";
        public static final String Q = "q";
        public static final String RESOURCE_TYPE_ARRAY = "resource_type[]";
        public static final String SERVING_SIZES = "serving_sizes";
        public static final String UACF_PROMOTED = "uacf_promoted";
        public static final String URL = "url";
        public static final String VENUES = "venues";
    }

    public static final class Injection {

        public static final class Named {
            public static final String ABTEST_SETTINGS_STORE = "abtest-settings";
            public static final String ACHIEVEMENT_STORE = "achievement-store";
            public static final String ACTION_TRACKING_STORE = "action-tracking";
            public static final String ADS_SETTINGS_STORE = "ads-settings";
            public static final String APP_SETTINGS_STORE = "app-settings";
            public static final String DEBUG_STORE = "debug-store";
            public static final String EXERCISE_SORTING_PREFERENCES = "exerciseSortingPreferences";
            public static final String FOOD_SORTING_PREFERENCES = "foodSortingPreferences";
            public static final String GLOBAL_SETTINGS_PREFERENCES = "global_settings_preferences";
            public static final String INSIGHTS_SETTINGS_STORE = "quick-tip-settings";
            public static final String INSTALL_REFERRER_RECEIVERS = "installReferrerReceivers";
            public static final String LOCAL_SETTINGS_PREFERENCES = "settings_preferences";
            public static final String LOGIN_SHARED_PREFERENCES = "login-shared-preferences";
            public static final String MFP_ANALYTICS_STORE = "mfp-analytics-store";
            public static final String NEWS_FEED_CACHE_STORE = "news-feed-cache-store";
            public static final String NUTRITION_GRAPH_PREFERENCE = "nutrition-graph-preference";
            public static final String PREMIUM_OVERRIDES_SETTINGS_STORE = "premium-overrides";
            public static final String PRODUCT_SERVICE_STORE = "product_service_preferences";
            public static final String PROGRESS_CONGRATS_PREFERENCES = "progress_congrats_preferences";
            public static final String RECIPE_CACHE_STORE = "recipe-cache-store";
            public static final String RESTAURANT_SETTINGS_PREFERENCES = "restaurant_settings_preferences";
            public static final String SEARCH_HISTORY_CARDIO_EXERCISE = "searchHistoryCardioExercise";
            public static final String SEARCH_HISTORY_STRENGTH_EXERCISE = "searchHistoryStrengthExercise";
            public static final String SIGNUP_SHARED_PREFERENCES = "signup-shared-preferences";
            public static final String STOCK_DATABASE = "stock_database";
            public static final String SUBSCRIPTION_SERVICE_STORE = "subscription_service_preferences";
            public static final String SYNC_V2_SETTINGS_STORE = "sync-v2-settings";
            public static final String USER_GOALS_SHARED_PREFERENCES = "user_goals_preferences";
            public static final String XPROMO_SETTINGS_STORE = "xpromo-settings";
        }
    }

    public static final class LocalizedStrings {
        public static final String ADJUSTMENT_HEADER = "adjustment_header";
        public static final String ADJUSTMENT_TEXT = "adjustment_text2";
        public static final String ALERT_EXERCISE = "alert_exercise";
        public static final String APP_ADJUSTMENT_COMMENT = "app_adjustment_comment";
        public static final String APP_ADJUSTMENT_LABEL = "app_adjustment_label";
        public static final String APP_INDEXING_EXERCISE_FORMAT = "app_indexing_exercise_format";
        public static final String APP_INDEXING_FOOD_FORMAT = "app_indexing_food_format";
        public static final String BASED_ON = "based_on";
        public static final String BURNED = "burned";
        public static final String BURNED_LABEL = "burned_label";
        public static final String CALORIE_FOCUSED = "calorie_focused";
        public static final String CALORIE_FOCUSED_SUBTEXT = "calorie_focused_subtext";
        public static final String CUSTOM_GOAL_ENERGY_TYPE = "custom_goal_energy_type";
        public static final String CUSTOM_GOAL_GRAMS_SUBTEXT = "custom_goal_list_item_subtext_grams";
        public static final String CUSTOM_GOAL_PERCENT_SUBTEXT = "custom_goal_list_item_subtext_percent";
        public static final String DAILY_GOAL_LABEL = "daily_goal_label";
        public static final String DIARY_STEPS_FOOTER_DESCRIPTION = "diary_steps_footer_description";
        public static final String ENERGY_AND_MACRONUTRIENT_GOALS = "energy_and_macronutrient_goals";
        public static final String ENERGY_BY_GRAM_ERROR = "energy_by_gram_error";
        public static final String ENTER_NUMBER_OF = "enter_number_of";
        public static final String EXTRA_EARNED = "extra_earned";
        public static final String FOODS_HIGHEST_IN = "foods_highest_in";
        public static final String HEART_HEALTHY_SUBTEXT = "heart_healthy_subtext";
        public static final String HIGHEST_IN = "highest_in";
        public static final String HOW_WE_CALC_HEADER = "how_we_calc_your_adjustment_header";
        public static final String INCLUDES_TEXT = "includes_text";
        public static final String INCREASE_GOAL = "increase_goal";
        public static final String INCREASE_MACRO_NUTRIENT_CALORIES = "increase_macro_nutrient";
        public static final String LOW_CARB_SUBTEXT = "low_carb_subtext";
        public static final String MACRO_ENERGY_ABBREVIATION = "macro_energy_abbreviation";
        public static final String MACRO_FOCUSED_SUBTEXT = "macro_focused_subtext";
        public static final String MIGHT_OVERWRITE_EXISTING_GOALS = "might_overwrite_existing_goals";
        public static final String NET_GOAL = "net_goal";
        public static final String NET_OVER_WEEKLY_GOAL_NEW = "net_over_weekly_goal_new";
        public static final String NET_UNDER_WEEKLY_GOAL_NEW = "net_under_weekly_goal_new";
        public static final String NET_WEEKLY = "net_weekly";
        public static final String QUICK_ADD = "quick_add";
        public static final String QUICK_ADDED = "quick_added";
        public static final String QUICK_ADD_ENERGY_HINT = "quick_add_hint";
        public static final String QUICK_ADD_FAIL = "quick_add_fail";
        public static final String QUICK_ADD_LABEL = "quick_add_label";
        public static final String QUICK_ADD_TOO_LOW = "quick_add_too_low";
        public static final String RADIO_TIP_DEFAULT = "radio_tip_default";
        public static final String RADIO_TIP_HEART_HEALTHY = "radio_tip_heart_healthy";
        public static final String RADIO_TIP_LOW_CARB = "radio_tip_low_carb";
        public static final String RADIO_TIP_MACROS = "radio_tip_macros";
        public static final String RECOMMENDATION_LOW_NEW = "recommendation_new";
        public static final String RECOMMENDATION_NORMAL = "projected";
        public static final String REMAINING = "summary_remaining";
        public static final String SUMMARY_REMAINING = "summary_remaining";
        public static final String TITLE_ACTIVITY_EXERCISE = "title_activity_exercise";
        public static final String TITLE_ACTIVITY_MACRO_GOALS = "title_activity_macro_goals";
        public static final String TOTAL = "total";
        public static final String TOTAL_OVER_WEEKLY_GOAL = "total_over_weekly_goal";
        public static final String TOTAL_UNDER_WEEKLY_GOAL = "total_under_weekly_goal";
        public static final String TO_ADD = "to_add";
        public static final String TO_RECORD = "to_record";
        public static final String UPDATE_GOAL_COMPLETE_NOTE_GAIN = "update_goals_complete_note_gain";
        public static final String UPDATE_GOAL_COMPLETE_NOTE_LOSS = "update_goals_complete_note_loss";
        public static final String UPDATE_GOAL_COMPLETE_YOUR_DAILY_GOAL = "update_goals_your_daily_goal";
        public static final String UPDATE_GOAL_DAILY_NEW = "update_goals_daily_new";
        public static final String UPGRADE_FOR_HIGHEST_IN = "upgrade_for_highest_in";
        public static final String UPGRADE_FOR_HIGHEST_IN_V2 = "upgrade_for_highest_in_v2";
    }

    public static final class Login {
        public static final String AUTH_FAILED = "authFailed";
        public static final int MINIMUM_AGE_TO_USE_MFP = 18;
        public static final String OAUTH2 = "OAUTH2";
    }

    public static class MacrosIndex {
        public static final int CARBS = 0;
        public static final int FAT = 1;
        public static final int NO_MACRO = -1;
        public static final int PROTEIN = 2;
        public static final int SIZE = 3;
    }

    public static final class MealTypeName {
        public static final String BREAKFAST = "Breakfast";
        public static final String DINNER = "Dinner";
        public static final String LEGACY_QUICK_ADDED_CALORIES = "Quick Added Calories";
        public static final String LUNCH = "Lunch";
        public static final String QUICK_ADD = "Quick Add - Myfitnesspal Premium";
        public static final String SNACK = "Snacks";
    }

    public static final class Measurement {
        public static final String HIPS = "Hips";
        public static final int MAX_MEASUREMENT_VALUE = 99999;
        public static final int MIN_MEASUREMENT_VALUE = -99999;
        public static final String NECK = "Neck";
        public static final String STEPS = "Steps";
        public static final String WAIST = "Waist";
        public static final String WEIGHT = "Weight";

        public static class Localization {
            public static final String CALORIES_SUFFIX = "_calories";
            public static final Object KILOGRAMS_SUFFIX = "_kilograms";
            public static final String KILOJOULES_SUFFIX = "_kilojoules";
            public static final String PLURAL_SUFFIX = "_plural";
            public static final Object POUNDS_SUFFIX = "_pounds";
            public static final String SINGULAR_SUFFIX = "_singular";
            public static final Object STONES_SUFFIX = "_stones";
        }
    }

    public static final class PairedFoods {
        public static final int LIMIT = 10;
    }

    public static final class Partner {
        public static final String MAPMYFITNESS_CLIENT_ID = "mapmyfitness";
        public static final String UNDER_ARMOUR_RECORD_NAME = "UA Record";
    }

    public static final class Payments {
        public static final String MISSING_ORDER_ID = "MISSING_ORDER_ID";

        public static final class APIErrorCode {
            public static final String DUPLICATE_RECEIPT = "duplicate_receipt/1";
            public static final String INVALID_RECEIPT = "invalid_receipt/1";
        }

        public static final class Extras {
            public static final String PAYMENT_ERROR = "payments.payment_error";
            public static final String PAYMENT_RESULT = "payments.purchase_result";
            public static final String PRODUCT = "payments.product";
            public static final String PROVIDER = "payments.provider";
        }

        public enum GenericError {
            NO_ERROR(0, 0),
            USER_CANCELED(1, R.string.payment_error_dialog_message_canceled),
            NETWORK_ERROR(2, R.string.payment_error_dialog_message_network),
            EXTERNAL_INVOCATION_ERROR(3, R.string.payment_error_dialog_message_external_invocation),
            PRICE_MISMATCH(4, R.string.payment_error_dialog_message_price_mismatch),
            BACKEND_SERVICE_ERROR(5, R.string.payment_error_dialog_message_backend_error),
            EXTERNAL_BACKEND_SERVICE_ERROR(6, R.string.payment_error_dialog_message_backend_error),
            PAYMENT_DECLINED(7, R.string.payment_error_dialog_message_payment_declined),
            ITEM_UNAVAILABLE(8, R.string.payment_error_dialog_message_item_unavailable),
            ITEM_NOT_FOUND(9, R.string.payment_error_dialog_message_item_not_found),
            INVALID_RESPONSE(10, R.string.payment_error_dialog_message_invalid_response),
            VERIFICATION_FAILED(11, R.string.payment_error_dialog_message_verification_failed),
            USER_INTERFACE_ERROR(12, R.string.payment_error_dialog_message_unknown),
            SDK_USAGE_ERROR(13, R.string.payment_error_dialog_message_unknown),
            POTENTIALLY_CHARGED(14, R.string.payment_error_dialog_message_potentially_charged),
            UNKNOWN_ERROR(Integer.MAX_VALUE, R.string.payment_error_dialog_message_unknown),
            SIMULATED_FAILURE_TESTING(-1, 0);
            
            private final int errorCode;
            private final int errorMessageResourceId;

            private GenericError(int i, int i2) {
                this.errorCode = i;
                this.errorMessageResourceId = i2;
            }

            public static GenericError fromErrorCode(int i) {
                switch (i) {
                    case 0:
                        return NO_ERROR;
                    case 1:
                        return USER_CANCELED;
                    case 2:
                        return NETWORK_ERROR;
                    case 3:
                        return EXTERNAL_INVOCATION_ERROR;
                    case 4:
                        return PRICE_MISMATCH;
                    case 5:
                        return BACKEND_SERVICE_ERROR;
                    case 6:
                        return EXTERNAL_BACKEND_SERVICE_ERROR;
                    case 7:
                        return PAYMENT_DECLINED;
                    case 8:
                        return ITEM_UNAVAILABLE;
                    case 9:
                        return ITEM_NOT_FOUND;
                    case 10:
                        return INVALID_RESPONSE;
                    case 11:
                        return VERIFICATION_FAILED;
                    case 12:
                        return USER_INTERFACE_ERROR;
                    case 13:
                        return SDK_USAGE_ERROR;
                    case 14:
                        return POTENTIALLY_CHARGED;
                    default:
                        return UNKNOWN_ERROR;
                }
            }

            public int getErrorCode() {
                return this.errorCode;
            }

            public int getErrorMessageResourceId() {
                return this.errorMessageResourceId;
            }
        }

        public static final class Platforms {
            public static final String ANDROID = "android";
        }

        public static final class Providers {
            public static final String AMAZON = "amazon";
            public static final String GOOGLE_PLAY = "google";
            public static final String MOCK = "mock";
            public static final String STRIPE = "stripe";
        }
    }

    public final class Performance {
        public static final String DATABASE_CREATE = "Database Create";
        public static final String DATABASE_TABLE_CREATE_FORMAT = "Database Table Create %s";
        public static final String DATABASE_TABLE_UPGRADE_FORMAT = "Database Table Upgrade %s [%s --> %s]";
        public static final String DATABASE_UPGRADE = "Database Upgrade";
        public static final String FOODS_DB_MIGRATION = "Foods DB Migration";
        public static final String NAVIGATE_ADDFOODSUMMARYVIEW = "Navigate to Add Food Summary View";
        public static final String NAVIGATE_ADDFOODSUMMARYVIEW_V2 = "Navigate to Add Food Summary View V2";
        public static final String NAVIGATE_DIARY = "Navigate to Diary";
        public static final String NAVIGATE_NEW_FOODSEARCH = "Navigate to New Food Search";
        public static final String NEW_FOOD_SEARCH_BUILD_RESULTS = "New Food Search Build Results '%s'";
        public static final String NEW_FOOD_SEARCH_FETCH_DATA = "New Food Search Fetch Data '%s'";
        public static final String RESUME_DIARY = "Resume Diary";
        public static final String SESSION_START = "Session Start";
        public static final String SPLASH_SCREEN = "Splash Screen";
        public static final String STARTUP_MANAGER = "Startup Manager";

        public Performance() {
        }
    }

    public static final class Preference {
        public static final String CALORIES = "pref_key_calories_setting";
        public static final String DONT_SHOW_XPROMO_INTERSTITIAL = "dont_show_xpromo_interstitial";
        public static final String FIT_EXERCISES_SYNC_TIME_POINT = "fit_exercises_sync_time_point";
        public static final String FIT_STEPS = "fit_steps";
        public static final String INSIGHTS_ENABLED = "insights_enabled";
        public static final String IN_APP_NOTIFICATION = "pref_in_app_notifications";
        public static final String KILOGRAMS = "pref_key_kilograms_settings";
        public static final String POUNDS = "pref_key_pounds_setting";
        public static final String RAISED_MALE_CALORIE_GOAL = "raised_male_calorie_goal";
        public static final String RAISED_MALE_CALORIE_GOAL_ALERT_SHOWN = "raised_male_calorie_goal_alert_shown";
        public static final String TIP_CARD = "tip_card_";
    }

    public final class Premium {
        public static final String AD_FREE_FEATURE_ID = "premium-ad-free";
        public static final String ASSIGN_EXERCISE_FEATURE_ID = "premium-assign-exercise";
        public static final String CONTENT_FEATURE_ID = "premium-content";
        public static final String CUSTOM_DAILY_GOALS_FEATURE_ID = "premium-custom-daily-goals";
        public static final int ENERGY_MAXIMUM = 99999;
        public static final String FILE_EXPORT_FEATURE_ID = "premium-file-export";
        public static final String FOOD_LISTS_FEATURE_ID = "premium-food-list";
        public static final String FOOD_TIMESTAMPS = "premium-food-entry-timestamps";
        public static final String GRAPHS_FEATURE_ID = "premium-graphs";
        public static final int MACROS_MAXIMUM = 9999;
        public static final String MEAL_GOALS_ID = "premium-custom-meal-goals";
        public static final String MEAL_MACRO_FEATURE_ID = "premium-diary-meal-macros";
        public static final String NUTRIENT_DASHBOARD_FEATURE_ID = "premium-nutrient-dashboard";
        public static final String PREMIUM_UPSELL_DISPLAY_MODE = "display_mode";
        public static final String PRIORITY_SUPPORT_FEATURE_ID = "premium-priority-support";
        public static final String QUICK_ADD_FEATURE_ID = "premium-quick-add";
        public static final String TRACK_MACROS_FEATURE_ID = "premium-track-macros";
        public static final String UPSELL_FEATURE_ID = "premium-upsell";
        public static final String VERIFIED_FOODS_FEATURE_ID = "premium-verified-foods";
        public static final String WEEKLY_DIGEST_ID = "premium-weekly-digest";

        public Premium() {
        }
    }

    public static class PremiumAPIException {
        public static final String API_ERROR_CODE = "premium_subscription/001";
        public static int HTTP_ERROR_CODE = 403;
    }

    public static final class Reminder {
        public static final String DAILY_FREQUENCY = "daily";
        public static final String DAY_OF_MONTH = "day_of_month";
        public static final String DAY_OF_WEEK = "day_of_week";
        public static final String EIGHT_OCLOCK = "20:00:00";
        public static final int EIGHT_OCLOCK_HOUR = 20;
        public static final String EXERCISE = "exercise";
        public static final String FOOD = "food";
        public static final String FOOD_OR_EXERCISE = "food_or_exercise";
        public static final String FREQUENCY = "frequency";
        public static final String INTERVAL_IN_DAYS = "interval_in_days";
        public static final String MEAL_NAME = "meal_name";
        public static final String MONTHLY_FREQUENCY = "monthly";
        public static final String REMINDER_TYPE = "reminder_type";
        public static final String SEVEN_THIRTY_OCLOCK = "07:30:00";
        public static final String TEN_OCLOCK = "10:00:00";
        public static final int TEN_OCLOCK_HOUR = 10;
        public static final String TWELVE_OCLOCK = "12:00:00";
        public static final String TWO_OCLOCK = "14:00:00";
        public static final int TWO_OCLOCK_HOUR = 14;
        public static final String UNKNOWN = "unknown";
        public static final String WALL_CLOCK_TIME = "wall_clock_time";
        public static final String WEEKLY_FREQUENCY = "weekly";
        public static final String WEIGHT = "log_weight";
    }

    public static final class Report {
        public static final String CARDIO_EXERCISE = "Cardio Exercise";
        public static final String EXERCISE = "Exercise";
        public static final String NOTES = "Notes";
        public static final String STRENGTH_EXERCISE = "Strength Exercise";
        public static final String WATER_CONSUMPTION = "Water Consumption";
    }

    public static final class RequestCodes {
        public static final int ADD_ENTRY = 32;
        public static final int ADD_EXERCISE = 61;
        public static final int ADD_FOOD = 53;
        public static final int ADD_FOOD_SUMMARY_VIEW = 54;
        public static final int ADD_FRIENDS_SPLASH = 10;
        public static final int ADD_MEAL_ENTRIES = 65;
        public static final int ADD_MEAL_VIEW = 52;
        public static final int ADD_RECIPE = 64;
        public static final int ADD_REMINDER = 90;
        public static final int ADJUST_GOAL = 28;
        public static final int BARCODE_FOOD_MULTI_ADD_EDITOR = 194;
        public static final int BARCODE_MATCH = 55;
        public static final int CARDIO = 47;
        public static final int CHALLENGES = 170;
        public static final int CHALLENGE_DETAIL = 171;
        public static final int CHOOSE_SORT_ORDER = 63;
        public static final int COMPOSE_MESSAGE = 112;
        public static final int CONFIRM_PHOTO_IMPORT = 193;
        public static final int CONSENTS = 206;
        public static final int CONTACT_PICKER_RESULT = 114;
        public static final int CREATE_MEAL = 87;
        public static final int CUSTOM_EXERCISE_CALORIE = 153;
        public static final int CUSTOM_GOAL_BY_DAY = 192;
        public static final int DISCONNECT_FACEBOOK = 20;
        public static final int EDIT_CARDIO = 100;
        public static final int EDIT_DIARY_NOTE_VIEW = 51;
        public static final int EDIT_FOOD = 197;
        public static final int EDIT_RECIPE_INGREDIENT = 201;
        public static final int EDIT_RECIPE_INGREDIENT_FOOD = 200;
        public static final int EDIT_REMINDER = 89;
        public static final int EDIT_STRENGTH = 101;
        public static final int FACEBOOK_LOGIN = 186;
        public static final int FAQ = 44;
        public static final int FILE_CHOOSER = 145;
        public static final int FINISH_ONBOARDING = 26;
        public static final int FOOD_EDITOR = 183;
        public static final int FOOD_MULTI_ADD_EDITOR = 196;
        public static final int FOOD_SEARCH_VIEW = 49;
        public static final int FRIENDS = 14;
        public static final int FRIEND_REQ_VIEW_PROFILE = 113;
        public static final int GOOGLE_PLAY_PAYMENT = 141;
        public static final int IMAGE_REPORTING = 189;
        public static final int IMAGE_REPORTING_DETAILS = 190;
        public static final int IMPORT_PHOTO = 180;
        public static final int INVITE_FRIENDS = 18;
        public static final int JOIN_CHALLENGE = 174;
        public static final int LEARN_ABOUT_PERMISSIONS = 188;
        public static final int MACROS_SUMMARY = 162;
        public static final int MEAL_BROWSE_FILTER = 198;
        public static final int MEAL_COLLECTIONS = 203;
        public static final int MENUS = 185;
        public static final int MULTI_ADD_EXERCISE = 195;
        public static final int NEWS_FEED_PRIVACY_SETTINGS = 82;
        public static final int NEW_CARDIO = 45;
        public static final int NEW_STATUS = 34;
        public static final int NEW_STRENGTH = 67;
        public static final int OFFLINE_SEARCH_NOTE = 43;
        public static final int OS_SETTINGS = 205;
        public static final int PAYMENT = 140;
        public static final int PREMIUM_TRACK_MACROS = 149;
        public static final int PREMIUM_UPSELL = 208;
        public static final int PROFILE = 36;
        public static final int PROFILE_POST = 105;
        public static final int PROGRESS_PHOTOS_GALLERY = 178;
        public static final int PROGRESS_STATUS_UPDATE = 187;
        public static final int QUICK_ADD = 159;
        public static final int READ_MESSAGE = 88;
        public static final int RECIPE_DETAILS = 202;
        public static final int RECIPE_IMPORTER = 128;
        public static final int SCAN_BARCODE = 62;
        public static final int SEARCH_MATCH = 184;
        public static final int SEARCH_RECIPE_INGREDIENT = 199;
        public static final int SELECT_REMINDER = 56;
        public static final int SHARE_PROGRESS = 179;
        public static final int SIGNUP = 7;
        public static final int STATUS_COMMENTS = 35;
        public static final int STRENGTH = 48;
        public static final int SUBMIT_CORRECTION = 207;
        public static final int UPDATE_STATUS = 111;
        public static final int VIEW_FOOD = 204;
        public static final int WATER_VIEW = 50;
        public static final int WEIGHT_PICKER = 25;
    }

    public static final class RestaurantLogging {
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String QUERY = "query";
        public static final String RADIUS = "radius";
    }

    public static final class Rollouts {
        public static final String EMAIL_VERIFICATION = "mfp-emverif-android-2018-10";
        public static final String EMAIL_VERIFICATION_WORLD_WIDE = "mfp-emverif-world-wide-android";
        public static final String NI_SDK_USE_CONFIG = "mfp-android-ni-sdk-use-config";

        public static final class Variants {
            public static final String CONTROL = "control";
            public static final String ENABLED = "enabled";
        }
    }

    public static final class Search {
        public static final String SEARCH_RESULT_ITEM = "search_result_item";
    }

    public static final class SearchHistory {
        public static final String CARDIO_EXERCISE = "cardio_exercise";
        public static final String FOOD = "food";
        public static final String STRENGTH_EXERCISE = "strength_exercise";
    }

    public static final class SearchTabs {
        public static final int TAB_ALL = 6008;
        public static final int TAB_ALL_EXERCISES = 6007;
        public static final int TAB_FREQUENT_FOODS = 6000;
        public static final int TAB_MOST_USED_EXERCISES = 6005;
        public static final int TAB_MY_EXERCISES = 6006;
        public static final int TAB_MY_FOODS = 6002;
        public static final int TAB_MY_MEALS = 6003;
        public static final int TAB_RECENT_FOODS = 6001;
        public static final int TAB_RECIPES = 6004;
        public static final int TAB_SEARCH = 0;
    }

    public static final class Settings {

        public static final class Ads {
            public static final String DEFAULT_TEST_NETWORK = "DFP";
            public static final String LOGIN_STREAK = "ads.loginStreak";
            public static final String STREAK_LAST_SHOWN_DATE = "ads.streakLastShownDate";
            public static final String TEST_MODE = "ads.testMode";
            public static final String TEST_NETWORK = "ads.testNetwork";
            public static final String TRACKING_ID = "ads.tracking_id";

            public static final class Amazon {
                public static final String APP_ID = "ads.amazon.appId";
                public static final String DEFAULT_APP_ID = "7e172d56949d46ffa83d7abc5c23cb20";
            }

            public static final class Facebook {
                public static final String APP_ID = "ads.facebook.appId";
            }

            public static final class InMobi {
                public static final String APP_ID = "ads.inmobi.appId";
                public static final String DEFAULT_APP_ID = "4028cba62f727758012f92d981ed00e4";
            }
        }

        public static final class App {
            public static final String ANALYTICS = "app.analytics";
            public static final String AUTH_DEV_MENU = "app.authDebugMenu";
            public static final String AUTH_TOKEN = "app.auth_token";
            public static final String DEEPLINK_UTM_INFORMATION = "app.utmInformation.deeplink";
            public static final String EXPIRE_SYNC_TOKEN = "app.misc.expireSyncToken";
            public static final String FLAG_TOGGLE = "app.flagToggle";
            public static final String FORCE_CRASH = "app.misc.forceCrash";
            public static final String HAS_INITIALIZED_STOCK_DATABASE = "app.hasInitializedStockDatabase";
            public static final String INSTALLATION_DATE = "app.installedDate";
            public static final String INSTALLED_VERSION = "app.installedVersion";
            public static final String INSTALL_UTM_INFORMATION = "app.utmInformation";
            public static final String LAST_FRESH_STOCK_DATABASE_VERSION = "app.lastFreshStockDatabaseVersion";
            public static final String LOCALE_OVERRIDE = "app.localeOverride";
            public static final String NEW_PRIVACY_FLOW_DEBUG = "app.privacyFlow";
            public static final String NOTIFICATIONS_DEBUG = "app.notificationsDebugMenu";
            public static final String PAYMENT_DEBUG = "app.paymentDebug";
            public static final String PERSONALIZED_CONSENTS = "app.personalizedConsents";
            public static final String PREMIUM_DEV_MENU = "app.premiumDevMenu";
            public static final String PREMIUM_ONBOARDING = "app.walkthrough.premiumOnboarding";
            public static final String PROFILE_DELETION = "app.profileDeletion";
            public static final String PROGRESS_PHOTOS_DEBUG = "app.progressPhotosDebugMenu";
            public static final String REMOVE_SYNC_TOKEN = "app.misc.removeSyncToken";
            public static final String RETRIEVE_MEAL_FOOD_DEBUG = "app.retrieveMealFoodDebugMenu";
            public static final String SHOULD_TRACK_STEPS = "app.shouldTrackSteps";
            public static final String SIGNUP_CONGRATS_DEBUG = "app.signUpCongrats";
            public static final String WALKTHROUGH_FOOD_SEARCH = "app.walkthrough.foodSearch";
            public static final String WALKTHROUGH_LOGGING = "app.walkthrough.logging";

            public static final class Logging {
                public static final String PRIVATE_FILE_LOGGING = "app.logging.privateFileLogging";
                public static final String PRIVATE_LOG_FILE_CLEAR = "app.logging.privateLogFileClear";
            }

            public static final class URLs {
                public static final String ANDROID_MARKET_URL = "market://details?id=com.myfitnesspal.android";
                public static final String BETA_SIGN_UP = "http://myfitnesspal.desk.com/customer/portal/articles/2292940-join-the-beta-program";
                public static final String BLOG_PREMIUM_CONTENT = "/premium";
                public static final String BLOG_PREMIUM_QUERY_PARAM = "4a934b73fd678698ea3c05a172f2107d";
                public static final String DEFAULT_TOS_RELATIVE_URL = "/account/terms_and_privacy";
                public static final String ED_LEARN_MORE_URL = "http://myfitnesspal.desk.com/customer/portal/articles/1375583-a-message-about-myfitnesspal-s-updated-nutrition-goals";
                public static final String GOLD_FOOD_LEARN_MORE = "http://myfitnesspal.desk.com/customer/portal/articles/1859089";
                public static final String TOS_URL_FORMAT = "file:///android_asset/tos/%s.html";
                public static final String UA_AFFILIATES = "https://account.underarmour.com/privacy#who-we-are";
                public static final String UA_CONTACT = "http://myfitnesspal.desk.com/customer/portal/emails/new";
                public static final String UA_PRIVACY_POLICY = "https://account.underarmour.com/privacy";
                public static final String UA_SHOP_MEN = "https://www.underarmour.com/en-us/best-sellers/mens/g/3926z?cid=MMF|REF|MFPal|App|Nav|mens|";
                public static final String UA_SHOP_WOMEN = "https://www.underarmour.com/en-us/best-sellers/womens/g/3c26z?cid=MMF|REF|MFPal|App|Nav|womens|";
                public static final String UA_TERMS = "https://account.underarmour.com/terms-and-conditions";
            }
        }

        public static class DeepLink {
            public static final String DESTINATION_DEEP_LINK = "app.settings.deeplink.destination_deep_link";
        }

        public static final class Gallery {
            public static final String PACKAGE_NAME_VENDING = "com.android.vending";
        }
    }

    public static final class URLs {

        public static final class Integ {
            public static final String API = "https://integ.myfitnesspal.com";
            public static final String SYNC = "https://integ.myfitnesspal.com";
            public static final String SYNCV2 = "https://integ-mobilesync.api.ua.com";
            public static final String WEB = "https://integ.myfitnesspal.com";
        }

        public static final class PreProd {
            public static final String API = "https://preprod.myfitnesspal.com";
            public static final String SYNC = "https://preprod.myfitnesspal.com";
            public static final String SYNCV2 = "https://integ-mobilesync.api.ua.com";
            public static final String WEB = "https://preprod.myfitnesspal.com";
        }

        public static final class Prod {
            public static final String API = "https://api.myfitnesspal.com";
            public static final String SYNC = "https://sync.myfitnesspal.com";
            public static final String SYNCV2 = "https://mobilesync.api.ua.com";
            public static final String WEB = "https://www.myfitnesspal.com";
        }
    }

    public static final class Upgrade {
        public static final String BODY_KEY = "body";
        public static final String TITLE = "title";
        public static final String URL_KEY = "url";
    }

    public enum UpsellDisplayMode {
        Normal,
        SignUp,
        FeatureScreen
    }

    public static final class Uri {
        public static final String ACTION_REQUEST = "iphone_api/synchronize";
        public static final String ACTIVITY = "/v2/activity";
        public static final String ACTIVITY_CONVERSATION_ENTRY = "/v2/activity/%1$s/comments";
        public static final String ACTIVITY_CONVERSATION_ENTRY_COMMENT = "/v2/activity/%1$s/comments/%2$s";
        public static final String ACTIVITY_CONVERSATION_ENTRY_COMMENT_LIKES = "/v2/activity/%1$s/comments/%2$s/likes";
        public static final String ACTIVITY_ENTRY = "/v2/activity/%1$s";
        public static final String ACTIVITY_LIKES = "/v2/activity/%1$s/likes/";
        public static final String ACTIVITY_TIMELINE = "/v2/activity/timeline";
        public static final String ANDROID_MARKET_URI = "market://details?id=com.myfitnesspal.android";
        public static final String APP_DISCONNECT = "/v2/authorizations/%1$s";
        public static final String APP_LIST = "/v2/partners/apps";
        public static final String BLOG_POSTS = "/v2/activity/blog_posts";
        public static final String CHALLENGES = "/v2/challenges";
        public static final String CHALLENGE_BY_ID = "/v2/challenges/%1$s";
        public static final String CHALLENGE_INVITE = "/v2/challenges/%1$s/invitations";
        public static final String CHALLENGE_PARTICPANT = "/v2/challenges/%1$s/participants";
        public static final String CHECK_DIAGNOSTIC_STATUS = "iphone_api/check_diagnostic_status";
        public static final String CONFIG = "config";
        public static final String DELETE_ACCOUNT = "/v2/uacf-nutrition/user/delete";
        public static final String DELETE_IMAGES = "/v2/images/%s";
        public static final String DELETE_IMAGE_ASSOCIATION = "/v2/image-associations/%s";
        public static final String DIAGNOSTIC_UPLOAD = "iphone_api/diagnostic_upload";
        public static final String DIARY = "/v2/diary";
        public static final String DIARY_EXERCISES = "/v2/diary/exercises";
        public static final String DIARY_SINGLE_EXERCISE = "/v2/diary/exercises/%s";
        public static final String EVENTS = "/v2/events";
        public static final String EXERCISES = "/v2/exercises";
        public static final String EXERCISE_TRACKING_APP_RECOMMENDATION = "/v2/exercise-tracking-app-recommendations";
        public static final String FILE_EXPORT = "/v2/data-exports?from-date=%1s&to-date=%2s";
        public static final String FOODS = "/v2/foods/%1$s";
        public static final String FOODS_V2 = "/v2/foods";
        public static final String FOOD_FEEDBACK = "/v2/feedback/food";
        public static final String FORGOT_PASSWORD = "iphone_api/forgot_password";
        public static final String FORUM_SSO = "/v2/vanilla/sso";
        public static final String FRIENDS_AND_APPS = "/v2/uacf-nutrition/mobile/friends-and-apps";
        public static final String GOOGLE_PLAY = "play.google.com";
        public static final String HERO_CARDS_COOLDOWN = "/v2/hero-cards/cooldown/%s";
        public static final String ID_GENERATOR = "/v2/id-generator";
        public static final String IMAGES = "/v2/images";
        public static final String IMAGE_DOWNLOAD_BY_ID = "/v2/images/%1$s/download";
        public static final String INFORMATION_REQUEST = "iphone_api/synchronize";
        public static final String INSIGHTS = "/v2/analyzer/food";
        static final String IPHONE_API = "iphone_api";
        public static final String LEAVE_CHALLENGE = "/v2/challenges/%1$s/participants/%2$s";
        public static final String MEALS_FOR_COLLECTIONS = "/v2/collections/%s/meals";
        public static final String MEAL_COLLECTIONS = "/v2/collections";
        public static final String MEAL_SUGGESTIONS = "/v2/uacf-nutrition/mobile/meals/suggestions";
        public static final String MENUS = "/v2/venues/%1s/menus";
        public static final String MENU_ITEMS = "/v2/menus/%1s/items";
        public static final String MENU_ITEM_MATCHES = "/v2/menu-item-matches";
        public static final String MENU_MATCHES = "/v2/menus/%1s/items/%2s/matches";
        public static final String NEW_PRODUCT_CATALOG = "/v2/products/user?application=MYFITNESSPAL";
        public static final String NUTRIENT_GOALS = "/v2/nutrient-goals";
        public static final String OAUTH2_AUTHORIZE = "/oauth2/authorize";
        public static final String ONLINE_SEARCH = "iphone_api/online_search";
        public static final String PAID_SUBSCRIPTIONS = "/v2/paid-subscriptions";
        public static final String POST_IMAGE_ASSOCIATION = "/v2/image-associations";
        public static final String PRODUCT_CATALOG = "/v2/products";
        public static final String RECIPES = "/v2/recipes";
        public static final String RECIPE_ID_GENERATOR = "/v2/recipes/id-generator";
        public static final String RECIPE_MATCH_INGREDIENTS = "/v2/recipes/match";
        public static final String RECIPE_PARSE = "/v2/recipes/parse";
        public static final String REGION_LOOKUP = "/v2/regions";
        public static final String REPORT_IMAGE = "/v2/image-flagging/flag";
        public static final String SEARCH_FOOD_SEARCH_MAPPING = "/v2/food-search-ads/category";
        public static final String SEARCH_NUTRITION = "/v2/search/nutrition";
        public static final String SINGLE_EXERCISE = "/v2/exercises/%s";
        public static final String SINGLE_RECIPE = "/v2/recipes/%s";
        public static final String STEPS_PREMIUM_AD = "/ads/steps_premium_ad.html";
        public static final String SUGGESTED_SERVINGS = "/v2/foods/%1$s/suggested_servings";
        public static final String SUGGESTED_SERVINGS_PATCH = "/v2/foods/%1$s";
        public static final String SYNCHRONIZE = "iphone_api/synchronize";
        public static final String T_AND_P_UPDATE = "/account/terms_and_privacy.json";
        public static final String USER_APPLICATION_SETTINGS = "/v2/user-application-settings";
        public static final String USER_PROPERTIES = "/v2/users/%1$s";
        public static final String USER_TIMELINE = "/v2/activity/user_timeline";
        public static final String VENUE = "/v2/venues/%1s?fields[]=menus";
        public static final String VENUES = "/v2/venues";
        private static final String VERSION = "/v2";
    }

    public static final class UserProperties {
        public static final String FALSE = "false";
        public static final String NO = "no";
        public static final String TRUE = "true";
        public static final String YES = "yes";

        public static final class Basic {
            public static final String COUNTRY_NAME = "country_name";
            public static final String CURRENT_WEIGHT_IN_POUNDS = "current_weight_in_pounds";
            public static final String DATE_OF_BIRTH = "date_of_birth";
            public static final String DIARY_PASSWORD = "diary_password";
            public static final String DIARY_PRIVACY = "diary_privacy_setting";
            public static final String DISPLAY_DIARY_MEAL_MACROS = "display_diary_meal_macros";
            public static final String EMAIL = "email";
            public static final String GENDER = "gender";
            public static final String GOAL_WEIGHT_IN_POUNDS = "goal_weight_in_pounds";
            public static final String HEIGHT_IN_INCHES = "height_in_inches";
            public static final String LAST_GOALS_RECALCULATION_DATE = "last_goals_recalculation_date";
            public static final String LIFESTYLE_NAME = "lifestyle_name";
            public static final String MEAL_GOALS_ENABLED = "meal_goals_enabled";
            public static final String MEAL_NAMES = "meal_names";
            public static final String POSTAL_CODE = "postal_code";
            public static final String REQUIRES_START_TIME_FOR_EXERCISE_ENTRIES = "requires_start_time_for_exercise_entries";
            public static final String SHOULD_UPDATE_GOALS = "should_update_goals";
            public static final String TIMEZONE_IDENTIFIER = "timezone_identifier";
            public static final String TOS_ACCEPTED_VERSION = "terms_of_service_accepted_version";
            public static final String TOS_CURRENT_VERSION = "terms_of_service_current_version";
            public static final String UACF_ID = "uacf_id";
            public static final String USE_METRIC = "use_metric";
            public static final String VALID_EMAIL = "valid_email";
        }

        public static final class Email {
            public static final String FIND_BY_EMAIL_ENABLED = "find_by_email_enabled";
            public static final String SEND_EMAIL_FOR_ACCEPTED_FRIEND_REQUESTS = "send_email_for_accepted_friend_requests";
            public static final String SEND_EMAIL_FOR_ACCEPTED_GROUP_INVITES = "send_email_for_accepted_group_invites";
            public static final String SEND_EMAIL_FOR_BLOG_COMMENTS = "send_email_for_blog_comments";
            public static final String SEND_EMAIL_FOR_CHALLENGE_AVAILABLE = "send_email_for_challenge_available";
            public static final String SEND_EMAIL_FOR_EARN_CHALLENGE_ACHIEVEMENT = "send_email_for_earn_challenge_achievement";
            public static final String SEND_EMAIL_FOR_FRIEND_CHALLENGE_INVITE = "send_email_for_friend_challenge_invite";
            public static final String SEND_EMAIL_FOR_FRIEND_COMPLETES_CHALLENGE = "send_email_for_friend_completes_challenge";
            public static final String SEND_EMAIL_FOR_FRIEND_JOINED_CHALLENEGE = "send_email_for_friend_joined_challenge";
            public static final String SEND_EMAIL_FOR_FRIEND_REQUESTS = "send_email_for_friend_requests";
            public static final String SEND_EMAIL_FOR_GROUP_INVITES = "send_email_for_group_invites";
            public static final String SEND_EMAIL_FOR_MESSAGES = "send_email_for_messages";
            public static final String SEND_EMAIL_FOR_PROFILE_COMMENTS = "send_email_for_profile_comments";
            public static final String SEND_EMAIL_FOR_STATUS_COMMENTS = "send_email_for_status_comments";
            public static final String SEND_EMAIL_FOR_STATUS_COMMENT_LIKES = "send_email_for_status_comment_likes";
            public static final String SEND_EMAIL_FOR_STATUS_LIKES = "send_email_for_status_likes";
            public static final String SEND_EMAIL_FOR_STATUS_THREAD_COMMENTS = "send_email_for_status_thread_comments";
            public static final String SEND_EMAIL_FOR_USER_COMPLETES_CHALLENGE = "send_email_for_user_completes_challenge";
        }

        public static final class Facebook {
            public static final String AUTOPOST_TO_FACEBOOK_ENABLED = "autopost_to_facebook_enabled";
            public static final String FACEBOOK_POST_ON_BLOG_POSTS = "facebook_post_on_blog_posts";
            public static final String FACEBOOK_POST_ON_COMPLETED_DIARY = "facebook_post_on_completed_diary";
            public static final String FACEBOOK_POST_ON_LOST_WEIGHT = "facebook_post_on_lost_weight";
            public static final String FACEBOOK_POST_ON_PERFORMED_EXERCISE = "facebook_post_on_performed_exercise";
            public static final String FACEBOOK_POST_ON_STATUS_UPDATES = "facebook_post_on_status_updates";
            public static final String FIND_BY_FACEBOOK_ENABLED = "find_by_facebook_enabled";
        }

        public static final class NewsFeed {
            public static final String CREATE_STATUS_ON_BLOG_POSTS = "create_status_on_blog_posts";
            public static final String CREATE_STATUS_ON_COMPLETED_DIARY = "create_status_on_completed_diary";
            public static final String CREATE_STATUS_ON_CONSECUTIVE_LOGINS = "create_status_on_consecutive_logins";
            public static final String CREATE_STATUS_ON_CREATED_TOPIC = "create_status_on_created_topic";
            public static final String CREATE_STATUS_ON_HASNT_LOGGED_IN = "create_status_on_hasnt_logged_in";
            public static final String CREATE_STATUS_ON_LOST_WEIGHT = "create_status_on_lost_weight";
            public static final String CREATE_STATUS_ON_NEW_FRIENDS = "create_status_on_new_friends";
            public static final String CREATE_STATUS_ON_PERFORMED_EXERCISE = "create_status_on_performed_exercise";
            public static final String CREATE_STATUS_ON_REPLIED_TO_TOPIC = "create_status_on_replied_to_topic";
            public static final String CREATE_STATUS_ON_STATUS_COMMENTS = "create_status_on_status_comments";
            public static final String CREATE_STATUS_ON_WALL_POSTS = "create_status_on_wall_posts";
            public static final String SHOW_BLOG_ARTICLES = "__show_blog_articles__";
            public static final String SHOW_BLOG_VIDEOS = "__show_blog_videos__";
        }

        public static final class Newsletter {
            public static final String NEWSLETTER_FOR_FEATURE_ANNOUNCEMENTS = "feature_announcements";
            public static final String NEWSLETTER_FOR_HEALTHY_LIVING_TIP = "healthy_living_tip";
            public static final String NEWSLETTER_FOR_RECIPES = "recipe";
            public static final String NEWSLETTER_FOR_UA_GEAR = "ua_gear";
            public static final String NEWSLETTER_FOR_WEEKLY_DIGEST = "weekly_digest";
            public static final String NEWSLETTER_FOR_WORKOUTS = "workout";
        }

        public static final class Notifications {
            public static final String ENABLE_QUIET_TIME = "enable_quiet_time";
            public static final String NOTIFY_OF_A_NEW_CHALLENGE_AVAILABLE = "notify_of_a_new_challenge_available";
            public static final String NOTIFY_OF_EARNED_ACHIEVEMENT = "notify_of_earned_achievement";
            public static final String NOTIFY_OF_FRIENDS_HIT_LOGIN_STREAK = "notify_of_friend_hit_login_streak";
            public static final String NOTIFY_OF_FRIEND_CHALLENGE_INVITE = "notify_of_friend_challenge_invite";
            public static final String NOTIFY_OF_FRIEND_COMPLETES_CHALLENGE = "notify_of_friend_completes_challenge";
            public static final String NOTIFY_OF_FRIEND_JOINED = "notify_of_friend_joined";
            public static final String NOTIFY_OF_FRIEND_JOINED_CHALLENGE = "notify_of_friend_joined_challenge";
            public static final String NOTIFY_OF_FRIEND_LOGGED_WORKOUT = "notify_of_friend_logged_workout";
            public static final String NOTIFY_OF_FRIEND_REQUESTS = "notify_of_friend_requests";
            public static final String NOTIFY_OF_NEW_COMMENTS = "notify_of_new_comments";
            public static final String NOTIFY_OF_NEW_MESSAGES = "notify_of_new_messages";
            public static final String NOTIFY_OF_STATUS_COMMENT_LIKES = "notify_of_status_comment_likes";
            public static final String NOTIFY_OF_STATUS_LIKES = "notify_of_status_likes";
            public static final String NOTIFY_OF_STATUS_THREAD_COMMENTS = "notify_of_status_thread_comments";
            public static final String NOTIFY_OF_USER_COMPLETES_CHALLENGE = "notify_of_user_completes_challenge";
            public static final String NOTIFY_OF_WALL_POSTS = "notify_of_wall_posts";
            public static final String QUIET_TIME_BEGIN_OFFSET = "quiet_time_begin_offset_from_midnight_utc";
            public static final String QUIET_TIME_END_OFFSET = "quiet_time_end_offset_from_midnight_utc";
        }

        public static class Registration {
            public static final String GAIN = "gain";
            public static final String LOSE = "lose";
            public static final String MAINTAIN = "maintain";
            public static final int MAX_USERNAME_LENGTH = 30;
            public static final int MIN_PASSWORD_LENGTH = 6;
            public static final int MIN_USERNAME_LENGTH = 4;
            public static final int US_ZIPCODE_LENGTH = 5;
        }

        public static class Units {
            public static final String BODY_WEIGHT_UNIT_PREFERENCE = "body_weight_unit_preference";
            public static final String DISTANCE_UNIT_PREFERENCE = "distance_unit_preference";
            public static final String ENERGY_UNIT_PREFERENCE = "energy_unit_preference";
            public static final String HEIGHT_UNIT_PREFERENCE = "height_unit_preference";
            public static final String WATER_UNIT_PREFERENCE = "water_unit_preference";
        }

        public static class UserStatus {
            public static final int EATING_DISORDER = 6;
            public static final int NORMAL = 0;
            public static final int UNDERAGE = 4;
            public static final int UNDERAGE_EATING_DISORDER = 5;
            public static final String USER_STATUS = "user_status";
        }
    }
}
