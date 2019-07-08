package com.myfitnesspal.shared.service.localsettings;

import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myfitnesspal.feature.consents.model.ConsentData;
import com.myfitnesspal.feature.diary.model.MealMacrosDisplayUnit;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellSkus;
import com.myfitnesspal.shared.model.UserLocation;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LocalSettingsServiceImpl implements LocalSettingsService {
    private static final String ACCEPTED_TOS_VERSION = "accepted_tos_version";
    private static final String AD_TRACKING_ENABLED = "ad_tracking_enabled";
    private static final String APP_INSTALLATION_DATE = "app_installation_date";
    private static final String CONSENTS_MATRIX_VERSION = "consents_matrix_version_";
    private static final String CURRENT_WEIGHT_FILTER_SELECTION = "current_weight_filter_selection";
    private static final String DEFAULT_SEARCH_TAB = "default_search_tab";
    private static final String DONT_ASK_FOR_REVIEW = "dont_ask_for_review";
    private static final String FOOD_DB_MIGRATED = "food_db_migrated";
    private static final String FREE_TRIALS_ENABLED = "free_trials_enabled";
    private static final String GENERIC_WEBVIEW_TRCKING = "generic_webview_tracking";
    private static final String HAS_CLICKED_PREMIUM_FEATURE = "has_clicked_premium_feature";
    private static final String HAS_FRIENDS = "has_friends";
    private static final String HAS_USER_CLICKED_FILE_EXPORT_ONCE = "has_user_clicked_file_export_once";
    private static final String INTERRUPT_FOR_AD_CONSENTS = "interrupt_for_ad_consents";
    private static final String IS_GLOBAL_PREF_MIGRATION_COMPLETE = "is_global_pref_migration_complete";
    private static final String IS_NEW_USER = "is_new_user";
    private static final String JOINED_CHALLENGES = "joined_challenges";
    private static final String LAST_FETCHED_CHALLENGES = "last_fetched";
    private static final String LAST_LOGIN_DAY_NUMBER = "last_login_day_number";
    private static final String MEALS_RECIPES_AND_FOODS_SORT_ORDER = "meals_recipes_and_foods_sort_order";
    private static final String MEAL_GOALS_SCREEN_VISITED = "meal_goals_screen_visited";
    private static final String MEAL_IDEAS_VIEWED = "meal_ideas_viewed";
    private static final String MEAL_MACROS_DISPLAY_UNIT = "meal_macros_display_unit";
    private static final String MULTI_ADD_TOGGLE_ON_BY_DEFUALT = "multi_add_toggle_on_by_default";
    private static final String PASSWORD_RESET_DATE_TIME = "password_reset_date_time";
    private static final String PERSONLAIZED_AD_CONSENT_ACCEPTED = "personalized_ad_consent_accepted";
    private static final String PREMIUM_AD_DISPLAYED = "premium_ad_displayed";
    private static final String PREMIUM_CROWN_TOOLTIP_SHOWN = "premium_crown_tooltip_shown";
    private static final String PREMIUM_CROWN_TOOLTIP_SHOWN_AT = "premium_crown_tooltip_shown_at";
    private static final String PROGRESS_PHOTOS_INTRO_DISPLAYED = "progress_photos_intro_displayed";
    private static final String RECENTS_DELETED_FOOD_ORIGINAL_UIDS = "recents_deleted_food_original_uids";
    private static final int RESET_NEW_USER_FLAG_DELAY_MILLIS = 3600000;
    private static final String SAW_FOOD_SEARCH_WALKTHROUGH = "user_saw_food_search_walkthrough";
    private static final String SAW_FOOD_TIMESTAMP_FEATURE = "user_saw_timestamp_feature";
    private static final String SHOULD_SHOW_ALL_MEALS = "should_show_all_meals";
    private static final String SHOULD_SHOW_DIARY_ENTRIES_DELETE_CONFIRM = "should_show_diary_delete_entries_confirm";
    private static final String SHOULD_SHOW_FOOD_TIMESTAMPS = "show_food_timestamps";
    private static final String SHOULD_SHOW_WATER_CARD = "should_show_water_card";
    private static final String SHOULD_VIDEO_AD_AUTO_PLAY = "should_video_ad_auto_play";
    private static final String SHOW_BLOG_ARTICLES_IN_NEWS_FEED = "show_blog_articles_in_news_feed";
    private static final String SHOW_BLOG_VIDEOS_IN_NEWS_FEED = "show_blog_videos_in_news_feed";
    private static final String SHOW_DELETE_MEAL_CONFIRMATION_DIALOG = "show_delete_meal_confirmation_dialog";
    private static final String SHOW_FOOD_EDITOR_MEAL_NOTES_HINT = "show_food_editor_meal_notes_hint";
    private static final String SHOW_FOOD_EDITOR_SHARE_HINT = "show_food_editor_share_hint";
    private static final String SHOW_INVITE_PROMOTION_VIEW = "show_invite_promotion_view";
    private static final String SHOW_MEAL_BROWSE_NAVIGATION_HINT = "show_meal_browse_navigation_hint";
    private static final String SHOW_MEAL_GOALS_PROMO_CARD = "show_meal_goals_promo_card";
    private static final String SHOW_MEAL_GOALS_TIP = "show_meal_goals_tip";
    private static final String SHOW_MEAL_MACROS_TIP = "show_meal_macros_tip";
    private static final String SUBJECT_TO_PERSONALIZED_AD_CONSENT = "subject_to_personalized_ad_consent";
    private static final String TRIAL_ROLLOUT_NAME = "trail_rollout_name";
    private static final String UNSEEN_NEW_CHALLENGES = "unseen_challenges";
    private static final String USER_ACCEPTED_LOCATION_PERMISSION = "user_accepted_location_permission";
    private static final String USER_GAID = "user_gaid";
    private static final String USER_LATITUDE = "user_latitude";
    private static final String USER_LONGITUDE = "user_longitude";
    private static final String WATER_QUICK_PICKS = "water_quick_picks_";
    private static final String WEEKLY_START_DAY = "weekly_start_day";
    private static final String WERE_FOOD_ORIGINAL_IDS_CORRECTED = "were_food_original_ids_corrected";
    private Lazy<CountryService> countryService;
    private Handler handler = new Handler(Looper.getMainLooper());
    private KeyedSharedPreferences prefs;
    private Lazy<UserApplicationSettingsService> userApplicationSettingsService;

    public LocalSettingsServiceImpl(KeyedSharedPreferences keyedSharedPreferences, Lazy<UserApplicationSettingsService> lazy, Lazy<CountryService> lazy2) {
        this.prefs = keyedSharedPreferences;
        this.userApplicationSettingsService = lazy;
        this.countryService = lazy2;
    }

    public void setWeeklyStartDay(int i) {
        writeInt(WEEKLY_START_DAY, i);
    }

    public int getWeeklyStartDay() {
        return this.prefs.getInt(WEEKLY_START_DAY, 0);
    }

    public void setHasFriends(boolean z) {
        writeBoolean(HAS_FRIENDS, z);
    }

    public boolean hasFriends() {
        return this.prefs.getBoolean(HAS_FRIENDS, false);
    }

    public void setPremiumAdDisplayed(boolean z) {
        writeBoolean(PREMIUM_AD_DISPLAYED, z);
    }

    public boolean hasPremiumAdBeenDisplayed() {
        return this.prefs.getBoolean(PREMIUM_AD_DISPLAYED, false);
    }

    public void setProgressPhotosIntroDisplayed(boolean z) {
        writeBoolean(PROGRESS_PHOTOS_INTRO_DISPLAYED, z);
    }

    public boolean hasProgressPhotosIntroBeenDisplayed() {
        return this.prefs.getBoolean(PROGRESS_PHOTOS_INTRO_DISPLAYED, false);
    }

    public void setShouldShowBlogArticlesInNewsFeed(boolean z) {
        writeBoolean(SHOW_BLOG_ARTICLES_IN_NEWS_FEED, z);
    }

    public boolean shouldShowBlogArticlesInNewsFeed() {
        return this.prefs.getBoolean(SHOW_BLOG_ARTICLES_IN_NEWS_FEED, true);
    }

    public void setShouldShowBlogVideosInNewsFeed(boolean z) {
        writeBoolean(SHOW_BLOG_VIDEOS_IN_NEWS_FEED, z);
    }

    public boolean shouldShowBlogVideosInNewsFeed() {
        return this.prefs.getBoolean(SHOW_BLOG_VIDEOS_IN_NEWS_FEED, true);
    }

    public void setIsNewUser(boolean z) {
        if (z) {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    LocalSettingsServiceImpl.this.setIsNewUser(false);
                }
            }, 3600000);
        }
        this.prefs.edit().putBoolean(IS_NEW_USER, z).apply();
    }

    public boolean isNewUser() {
        return this.prefs.getBoolean(IS_NEW_USER, false);
    }

    public void setUnseenNewChallenges(int i) {
        writeInt(UNSEEN_NEW_CHALLENGES, i);
    }

    public int getUnseenNewChallenges() {
        return this.prefs.getInt(UNSEEN_NEW_CHALLENGES, 0);
    }

    public void setLastSeenNewChallengesDate(long j) {
        writeLong(LAST_FETCHED_CHALLENGES, j);
    }

    public long getLastSeenNewChallengesDate() {
        return this.prefs.getLong(LAST_FETCHED_CHALLENGES, 0);
    }

    public void setJoinedChallengesCount(int i) {
        writeInt(JOINED_CHALLENGES, i);
    }

    public int getJoinedChallengesCount() {
        return this.prefs.getInt(JOINED_CHALLENGES, 0);
    }

    public void updateJoinedChallengesCountBy(int i) {
        setJoinedChallengesCount(getJoinedChallengesCount() + i);
    }

    public void setFoodDBMigrated(boolean z) {
        writeBoolean(FOOD_DB_MIGRATED, z);
    }

    public boolean wasFoodDBMigrated() {
        return this.prefs.getBoolean(FOOD_DB_MIGRATED, false);
    }

    public boolean shouldNativeAdVideoAutoPlay() {
        return this.prefs.getBoolean(SHOULD_VIDEO_AD_AUTO_PLAY, true);
    }

    public void setShowDeleteDiaryEntriesConfirm(boolean z) {
        writeBoolean(SHOULD_SHOW_DIARY_ENTRIES_DELETE_CONFIRM, z);
    }

    public boolean shouldShowDeleteDiaryEntriesConfirm() {
        return this.prefs.getBoolean(SHOULD_SHOW_DIARY_ENTRIES_DELETE_CONFIRM, true);
    }

    public void setCurrentMeasurementFilterSelection(int i) {
        writeInt(CURRENT_WEIGHT_FILTER_SELECTION, i);
    }

    public int getCurrentMeasurementFilterSelection() {
        return this.prefs.getInt(CURRENT_WEIGHT_FILTER_SELECTION, 0);
    }

    public void setAcceptedTOSVersion(int i) {
        writeInt(ACCEPTED_TOS_VERSION, i);
    }

    public int getAcceptedTOSVersion() {
        return this.prefs.getInt(ACCEPTED_TOS_VERSION, 0);
    }

    public void setMealMacrosDisplayUnit(MealMacrosDisplayUnit mealMacrosDisplayUnit) {
        writeInt(MEAL_MACROS_DISPLAY_UNIT, mealMacrosDisplayUnit.ordinal());
    }

    public MealMacrosDisplayUnit getMealMacrosDisplayUnit() {
        return MealMacrosDisplayUnit.values()[this.prefs.getInt(MEAL_MACROS_DISPLAY_UNIT, MealMacrosDisplayUnit.Percent.ordinal())];
    }

    public void setShowMealMacrosTip(boolean z) {
        writeBoolean(SHOW_MEAL_MACROS_TIP, z);
    }

    public boolean showMealMacrosTip() {
        return this.prefs.getBoolean(SHOW_MEAL_MACROS_TIP, true);
    }

    public void setShowMealGoalsTip(boolean z) {
        writeBoolean(SHOW_MEAL_GOALS_TIP, z);
    }

    public boolean showMealGoalsTip() {
        return this.prefs.getBoolean(SHOW_MEAL_GOALS_TIP, true);
    }

    public void setMealGoalsScreenVisited(boolean z) {
        writeBoolean(MEAL_GOALS_SCREEN_VISITED, z);
    }

    public boolean getMealGoalsScreenVisited() {
        return this.prefs.getBoolean(MEAL_GOALS_SCREEN_VISITED, false);
    }

    public void setUserHasClickedFileExportOnce() {
        writeBoolean(HAS_USER_CLICKED_FILE_EXPORT_ONCE, true);
    }

    public boolean hasUserClickedFileExportOnce() {
        return this.prefs.getBoolean(HAS_USER_CLICKED_FILE_EXPORT_ONCE, false);
    }

    public void setShouldShowWaterCard(boolean z) {
        writeBoolean(SHOULD_SHOW_WATER_CARD, z);
    }

    public boolean shouldShowWaterCard() {
        return this.prefs.getBoolean(SHOULD_SHOW_WATER_CARD, true);
    }

    public void setUserLocation(UserLocation userLocation) {
        writeString(USER_LATITUDE, userLocation.getLatitude());
        writeString(USER_LONGITUDE, userLocation.getLongitude());
    }

    public void setCustomWaterQuickPicks(Session session, ArrayList<LocalizedFluid> arrayList) {
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        sb.append(WATER_QUICK_PICKS);
        sb.append(LocalizedFluid.getNonTranslatedUnitAbbrString(session));
        writeString(sb.toString(), gson.toJson((Object) arrayList));
    }

    public ArrayList<LocalizedFluid> getCustomWaterQuickPicks(Session session) {
        Gson gson = new Gson();
        KeyedSharedPreferences keyedSharedPreferences = this.prefs;
        StringBuilder sb = new StringBuilder();
        sb.append(WATER_QUICK_PICKS);
        sb.append(LocalizedFluid.getNonTranslatedUnitAbbrString(session));
        return (ArrayList) gson.fromJson(keyedSharedPreferences.getString(sb.toString(), "[]"), new TypeToken<ArrayList<LocalizedFluid>>() {
        }.getType());
    }

    public UserLocation getUserLocation() {
        return new UserLocation(this.prefs.getString(USER_LATITUDE, "0.0"), this.prefs.getString(USER_LONGITUDE, "0.0"));
    }

    public boolean isUserAcceptedLocationPermission() {
        return this.prefs.getBoolean(USER_ACCEPTED_LOCATION_PERMISSION, false);
    }

    public void setUserAcceptedLocationPermission(boolean z) {
        writeBoolean(USER_ACCEPTED_LOCATION_PERMISSION, z);
    }

    public void setGAID(String str) {
        writeString(USER_GAID, str);
    }

    public String getGAID() {
        return this.prefs.getString(USER_GAID, "");
    }

    private void writeBoolean(String str, boolean z) {
        this.prefs.edit().putBoolean(str, z).apply();
    }

    private void writeInt(String str, int i) {
        this.prefs.edit().putInt(str, i).apply();
    }

    private void writeLong(String str, long j) {
        this.prefs.edit().putLong(str, j).apply();
    }

    private void writeString(String str, String str2) {
        this.prefs.edit().putString(str, str2).apply();
    }

    private void writeStringSet(String str, Set<String> set) {
        this.prefs.edit().putStringSet(str, set).apply();
    }

    public KeyedSharedPreferences getPrefs() {
        return this.prefs;
    }

    public void migrateSharedPreferencesToUserApplicationSettingsIfNeeded() {
        KeyedSharedPreferences prefs2 = getPrefs();
        Editor edit = prefs2.edit();
        if (prefs2.contains(SHOW_MEAL_GOALS_PROMO_CARD)) {
            ((UserApplicationSettingsService) this.userApplicationSettingsService.get()).setMealGoalsBuyUpsellDismissed(!prefs2.getBoolean(SHOW_MEAL_GOALS_PROMO_CARD, false));
            edit.remove(SHOW_MEAL_GOALS_PROMO_CARD);
        }
        edit.apply();
    }

    public boolean hasClickedOnPremiumFeature() {
        return this.prefs.getBoolean(HAS_CLICKED_PREMIUM_FEATURE, false);
    }

    public void setHasClickedOnPremiumFeature(boolean z) {
        writeBoolean(HAS_CLICKED_PREMIUM_FEATURE, z);
    }

    public boolean showDeleteMealConfirmationDialog() {
        return this.prefs.getBoolean(SHOW_DELETE_MEAL_CONFIRMATION_DIALOG, true);
    }

    public void setShowDeleteMealConfirmationDialog(boolean z) {
        writeBoolean(SHOW_DELETE_MEAL_CONFIRMATION_DIALOG, z);
    }

    public boolean wereFoodOriginalIdsCorrected() {
        return this.prefs.getBoolean(WERE_FOOD_ORIGINAL_IDS_CORRECTED, false);
    }

    public void setWereFoodOriginalIdsCorrected(boolean z) {
        writeBoolean(WERE_FOOD_ORIGINAL_IDS_CORRECTED, z);
    }

    public int getLastLoginDayNumber() {
        return this.prefs.getInt("last_login_day_number", -1);
    }

    public void setLastLoginDayNumber(int i) {
        writeInt("last_login_day_number", i);
    }

    public Long getAppInstallationDate() {
        return Long.valueOf(this.prefs.getLong("app_installation_date", 0));
    }

    public void setAppInstallationDate(Long l) {
        writeLong("app_installation_date", 0);
    }

    public boolean getDontAskForReview() {
        return this.prefs.getBoolean("dont_ask_for_review", false);
    }

    public void setDontAskForReview(boolean z) {
        writeBoolean("dont_ask_for_review", z);
    }

    public boolean getShowInvitePromotionView() {
        return this.prefs.getBoolean(SHOW_INVITE_PROMOTION_VIEW, true);
    }

    public void setShowInvitePromotionView(boolean z) {
        writeBoolean(SHOW_INVITE_PROMOTION_VIEW, z);
    }

    public boolean getMultiAddToggleOnByDefault() {
        return this.prefs.getBoolean("multi_add_toggle_on_by_default", false);
    }

    public void setMultiAddToggleOnByDefault(boolean z) {
        writeBoolean("multi_add_toggle_on_by_default", z);
    }

    public boolean shouldShowAllMeals() {
        return this.prefs.getBoolean(SHOULD_SHOW_ALL_MEALS, true);
    }

    public void setShouldShowAllMeals(boolean z) {
        writeBoolean(SHOULD_SHOW_ALL_MEALS, z);
    }

    public int getDefaultSearchTab() {
        int i = this.prefs.getInt("default_search_tab", 6001);
        if (((CountryService) this.countryService.get()).getCurrentCountry().isUnitedStates()) {
            return i;
        }
        if (i != 6000 && i != 6002) {
            return i;
        }
        setDefaultSearchTab(6001);
        return 6001;
    }

    public void setDefaultSearchTab(int i) {
        writeInt("default_search_tab", i);
    }

    public boolean isGlobalPrefDBMigrationComplete() {
        return this.prefs.getBoolean(IS_GLOBAL_PREF_MIGRATION_COMPLETE, false);
    }

    public void setGlobalPrefDBMigrationComplete() {
        writeBoolean(IS_GLOBAL_PREF_MIGRATION_COMPLETE, true);
    }

    public void setMealIdeasViewed() {
        writeBoolean(MEAL_IDEAS_VIEWED, true);
    }

    public boolean mealIdeasViewed() {
        return this.prefs.getBoolean(MEAL_IDEAS_VIEWED, false);
    }

    public Set<String> getRecentsDeletedFoodOriginalUids() {
        return new HashSet(this.prefs.getStringSet(RECENTS_DELETED_FOOD_ORIGINAL_UIDS, new HashSet()));
    }

    public void addRecentsDeletedFoodOriginalUid(String str) {
        Set recentsDeletedFoodOriginalUids = getRecentsDeletedFoodOriginalUids();
        recentsDeletedFoodOriginalUids.add(str);
        this.prefs.edit().putStringSet(RECENTS_DELETED_FOOD_ORIGINAL_UIDS, recentsDeletedFoodOriginalUids).apply();
    }

    public void removeRecentsDeletedFoodOriginalUid(String str) {
        Set recentsDeletedFoodOriginalUids = getRecentsDeletedFoodOriginalUids();
        recentsDeletedFoodOriginalUids.remove(str);
        this.prefs.edit().putStringSet(RECENTS_DELETED_FOOD_ORIGINAL_UIDS, recentsDeletedFoodOriginalUids).apply();
    }

    public void setShowFoodEditorMealNotesHint(boolean z) {
        this.prefs.edit().putBoolean(SHOW_FOOD_EDITOR_MEAL_NOTES_HINT, z).apply();
    }

    public boolean getShowFoodEditorMealNotesHint() {
        return this.prefs.getBoolean(SHOW_FOOD_EDITOR_MEAL_NOTES_HINT, true);
    }

    public void setShowMealBrowseNavigationHint(boolean z) {
        this.prefs.edit().putBoolean(SHOW_MEAL_BROWSE_NAVIGATION_HINT, z).apply();
    }

    public boolean getShowMealBrowseNavigationHint() {
        return this.prefs.getBoolean(SHOW_MEAL_BROWSE_NAVIGATION_HINT, true);
    }

    public void setFreeTrialsEnabled(boolean z) {
        writeBoolean(FREE_TRIALS_ENABLED, z);
    }

    public boolean areFreeTrialsEnabled() {
        return this.prefs.getBoolean(FREE_TRIALS_ENABLED, false);
    }

    public void setTrialRolloutName(String str) {
        writeString(TRIAL_ROLLOUT_NAME, str);
    }

    public String getTrialRolloutName() {
        return this.prefs.getString(TRIAL_ROLLOUT_NAME, UpsellSkus.NAME_STANDARD);
    }

    public void setMealRecipesAndFoodsSortOrder(String str) {
        writeString(MEALS_RECIPES_AND_FOODS_SORT_ORDER, str);
    }

    public String getMealRecipesAndFoodsSortOrder() {
        return this.prefs.getString(MEALS_RECIPES_AND_FOODS_SORT_ORDER, "");
    }

    public boolean shouldShowFoodTimestamps() {
        return this.prefs.getBoolean(SHOULD_SHOW_FOOD_TIMESTAMPS, false);
    }

    public void setShouldShowFoodTimestamps(boolean z) {
        writeBoolean(SHOULD_SHOW_FOOD_TIMESTAMPS, z);
    }

    public boolean didUserSeeTimestampFeature() {
        return this.prefs.getBoolean(SAW_FOOD_TIMESTAMP_FEATURE, false);
    }

    public void setUserSawTimestampFeature(boolean z) {
        writeBoolean(SAW_FOOD_TIMESTAMP_FEATURE, z);
    }

    public boolean didUserSeeFoodSearchWalkthrough() {
        return this.prefs.getBoolean(SAW_FOOD_SEARCH_WALKTHROUGH, false);
    }

    public void userSawFoodSearchWalkthrough() {
        writeBoolean(SAW_FOOD_SEARCH_WALKTHROUGH, true);
    }

    public void storeUserAcceptedConsentsMatrix(@Nullable ConsentData consentData, String str) {
        if (consentData != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(CONSENTS_MATRIX_VERSION);
            sb.append(str);
            writeString(sb.toString(), new Gson().toJson((Object) consentData));
        }
    }

    @Nullable
    public ConsentData isUserAcceptedConsentsMatrix(String str) {
        Gson gson = new Gson();
        KeyedSharedPreferences keyedSharedPreferences = this.prefs;
        StringBuilder sb = new StringBuilder();
        sb.append(CONSENTS_MATRIX_VERSION);
        sb.append(str);
        return (ConsentData) gson.fromJson(keyedSharedPreferences.getString(sb.toString(), null), new TypeToken<ConsentData>() {
        }.getType());
    }

    public void trackGenericWebView(String str) {
        Set stringSet = this.prefs.getStringSet(GENERIC_WEBVIEW_TRCKING, new HashSet());
        stringSet.add(str);
        writeStringSet(GENERIC_WEBVIEW_TRCKING, stringSet);
    }

    public boolean hasUserSeenGenericWebView(String str) {
        return this.prefs.getStringSet(GENERIC_WEBVIEW_TRCKING, new HashSet()).contains(str);
    }

    public void setPasswordResetDateTime(long j) {
        writeLong(PASSWORD_RESET_DATE_TIME, j);
    }

    public long getPasswordResetDateTime() {
        return this.prefs.getLong(PASSWORD_RESET_DATE_TIME, DateTimeUtils.getDateTimeFromNow(12, -1));
    }

    public void setShouldInterruptUserForAdConsents(boolean z) {
        writeBoolean(INTERRUPT_FOR_AD_CONSENTS, z);
    }

    public boolean getShouldInterruptUserForAdConsents() {
        return this.prefs.getBoolean(INTERRUPT_FOR_AD_CONSENTS, false);
    }

    public void setIsUserSubjectToPersonalizedAds(boolean z) {
        writeBoolean(SUBJECT_TO_PERSONALIZED_AD_CONSENT, z);
    }

    public boolean getIsUserSubjectToPersonalizedAds() {
        return this.prefs.getBoolean(SUBJECT_TO_PERSONALIZED_AD_CONSENT, false);
    }

    public void setIsPersonalizedAdConsentAccepted(boolean z) {
        writeBoolean(PERSONLAIZED_AD_CONSENT_ACCEPTED, z);
    }

    public boolean getIsPersonalizedAdConsentAccepted() {
        return this.prefs.getBoolean(PERSONLAIZED_AD_CONSENT_ACCEPTED, false);
    }

    public void setPremiumCrownTooltipShown(boolean z) {
        writeBoolean(PREMIUM_CROWN_TOOLTIP_SHOWN, z);
    }

    public boolean isPremiumCrownTooltipShown() {
        return this.prefs.getBoolean(PREMIUM_CROWN_TOOLTIP_SHOWN, false);
    }

    public void setPremiumCrownTooltipShownTime(long j) {
        writeLong(PREMIUM_CROWN_TOOLTIP_SHOWN_AT, j);
    }

    public long getPremiumCrownTooltipShownTime() {
        return this.prefs.getLong(PREMIUM_CROWN_TOOLTIP_SHOWN_AT, 0);
    }

    public void enableAdTracking() {
        writeBoolean(AD_TRACKING_ENABLED, true);
    }

    public void disableAdTracking() {
        writeBoolean(AD_TRACKING_ENABLED, false);
    }

    public boolean isAdTrackingEnabled() {
        return this.prefs.getBoolean(AD_TRACKING_ENABLED, false);
    }
}
