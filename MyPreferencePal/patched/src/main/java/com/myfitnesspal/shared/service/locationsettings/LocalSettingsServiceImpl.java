package com.myfitnesspal.shared.service.localsettings;
import lanchon.dexpatcher.annotation.*;
import dagger.*;
import com.myfitnesspal.shared.service.install.*;
import com.uacf.core.preferences.*;
import com.myfitnesspal.feature.userapplicationsettings.service.*;
import android.os.*;
import com.myfitnesspal.shared.service.session.*;
import com.myfitnesspal.shared.model.unitconv.*;
import com.google.gson.*;
import com.google.gson.reflect.*;
import com.myfitnesspal.feature.diary.model.*;
import java.util.*;
import com.myfitnesspal.shared.model.*;
import android.content.*;

@DexEdit(defaultAction = DexAction.IGNORE)
public class LocalSettingsServiceImpl implements LocalSettingsService
{
	/*
	//@DexEdit(defaultAction = DexAction.IGNORE)
	public class 1 
	{
		public void 1()
		{
			return;
		}
	}
	//@DexEdit(defaultAction = DexAction.IGNORE)
	public class 2 
	{
		public void 2()
		{
			return;
		}
	}*/
	
    private static final String ACCEPTED_TOS_VERSION = "accepted_tos_version";
    private static final String APP_INSTALLATION_DATE = "app_installation_date";
    private static final String CURRENT_WEIGHT_FILTER_SELECTION = "current_weight_filter_selection";
    private static final String DEFAULT_SEARCH_TAB = "default_search_tab";
    private static final String DONT_ASK_FOR_REVIEW = "dont_ask_for_review";
    private static final String FOOD_DB_MIGRATED = "food_db_migrated";
    private static final String FREE_TRIALS_ENABLED = "free_trials_enabled";
    private static final String HAS_CLICKED_PREMIUM_FEATURE = "has_clicked_premium_feature";
    private static final String HAS_FRIENDS = "has_friends";
    private static final String HAS_USER_CLICKED_FILE_EXPORT_ONCE = "has_user_clicked_file_export_once";
    private static final String IS_GLOBAL_PREF_MIGRATION_COMPLETE = "is_global_pref_migration_complete";
    private static final String IS_NEW_USER = "is_new_user";
    private static final String JOINED_CHALLENGES = "joined_challenges";
    private static final String LAST_FETCHED_CHALLENGES = "last_fetched";
    private static final String LAST_LOGIN_DAY_NUMBER = "last_login_day_number";
    private static final String MEAL_GOALS_SCREEN_VISITED = "meal_goals_screen_visited";
    private static final String MEAL_IDEAS_VIEWED = "meal_ideas_viewed";
    private static final String MEAL_MACROS_DISPLAY_UNIT = "meal_macros_display_unit";
    private static final String MULTI_ADD_TOGGLE_ON_BY_DEFUALT = "multi_add_toggle_on_by_default";
    private static final String PREMIUM_AD_DISPLAYED = "premium_ad_displayed";
    private static final String PROGRESS_PHOTOS_INTRO_DISPLAYED = "progress_photos_intro_displayed";
    private static final String RECENTS_DELETED_FOOD_ORIGINAL_UIDS = "recents_deleted_food_original_uids";
    private static final int RESET_NEW_USER_FLAG_DELAY_MILLIS = 3600000;
    private static final String SHOULD_SHOW_ALL_MEALS = "should_show_all_meals";
    private static final String SHOULD_SHOW_DIARY_ENTRIES_DELETE_CONFIRM = "should_show_diary_delete_entries_confirm";
    private static final String SHOULD_SHOW_SCALE_INTERSTITIAL = "should_show_scale_interstitial";
    private static final String SHOULD_SHOW_SCALE_UPSELL_ON_PROGRESS = "should_show_scale_upsell_on_progress";
    private static final String SHOULD_SHOW_WATER_CARD = "should_show_water_card";
    private static final String SHOULD_VIDEO_AD_AUTO_PLAY = "should_video_ad_auto_play";
    private static final String SHOW_BLOG_ARTICLES_IN_NEWS_FEED = "show_blog_articles_in_news_feed";
    private static final String SHOW_DELETE_MEAL_CONFIRMATION_DIALOG = "show_delete_meal_confirmation_dialog";
    private static final String SHOW_FOOD_EDITOR_MEAL_NOTES_HINT = "show_food_editor_meal_notes_hint";
    private static final String SHOW_FOOD_EDITOR_SHARE_HINT = "show_food_editor_share_hint";
    private static final String SHOW_INVITE_PROMOTION_VIEW = "show_invite_promotion_view";
    private static final String SHOW_MEAL_BROWSE_NAVIGATION_HINT = "show_meal_browse_navigation_hint";
    private static final String SHOW_MEAL_GOALS_PROMO_CARD = "show_meal_goals_promo_card";
    private static final String SHOW_MEAL_GOALS_TIP = "show_meal_goals_tip";
    private static final String SHOW_MEAL_MACROS_TIP = "show_meal_macros_tip";
    private static final String UNSEEN_NEW_CHALLENGES = "unseen_challenges";
    private static final String USER_GAID = "user_gaid";
    private static final String USER_LATITUDE = "user_latitude";
    private static final String USER_LONGITUDE = "user_longitude";
    private static final String WATER_QUICK_PICKS = "water_quick_picks_";
    private static final String WEEKLY_START_DAY = "weekly_start_day";
    private static final String WERE_FOOD_ORIGINAL_IDS_CORRECTED = "were_food_original_ids_corrected";
    private Lazy<CountryService> countryService;
    private Handler handler;
    private KeyedSharedPreferences prefs;
    @DexAdd
    public static KeyedSharedPreferences prefs2;
    private Lazy<UserApplicationSettingsService> userApplicationSettingsService;

    @DexReplace
    public LocalSettingsServiceImpl(final KeyedSharedPreferences prefs, final Lazy<UserApplicationSettingsService> userApplicationSettingsService, final Lazy<CountryService> countryService) {
        this.handler = new Handler(Looper.getMainLooper());
        this.prefs = prefs;
        prefs2 = prefs;
        this.userApplicationSettingsService = userApplicationSettingsService;
        this.countryService = countryService;
    }
    
    private void writeBoolean(final String s, final boolean b) {
        this.prefs.edit().putBoolean(s, b).apply();
    }
    
    private void writeInt(final String s, final int n) {
        this.prefs.edit().putInt(s, n).apply();
    }
    
    private void writeLong(final String s, final long n) {
        this.prefs.edit().putLong(s, n).apply();
    }
    
    private void writeString(final String s, final String s2) {
        this.prefs.edit().putString(s, s2).apply();
    }
    
    @Override
    public void addRecentsDeletedFoodOriginalUid(final String s) {
        final Set<String> recentsDeletedFoodOriginalUids = this.getRecentsDeletedFoodOriginalUids();
        recentsDeletedFoodOriginalUids.add(s);
        this.prefs.edit().putStringSet("recents_deleted_food_original_uids", (Set)recentsDeletedFoodOriginalUids).apply();
    }
    
    @Override
    public boolean areFreeTrialsEnabled() {
        return this.prefs.getBoolean("free_trials_enabled", false);
    }
    
    @Override
    public int getAcceptedTOSVersion() {
        return this.prefs.getInt("accepted_tos_version", 0);
    }
    
    @Override
    public Long getAppInstallationDate() {
        return this.prefs.getLong("app_installation_date", 0L);
    }
    
    @Override
    public int getCurrentMeasurementFilterSelection() {
        return this.prefs.getInt("current_weight_filter_selection", 0);
    }
    
	@DexAdd
	class BToTheFifth_TypeToken extends TypeToken<ArrayList<LocalizedFluid>> {
	};
	
    @Override
	@DexEdit
    public ArrayList<LocalizedFluid> getCustomWaterQuickPicks(final Session session) {
        return new Gson().fromJson(this.prefs.getString("water_quick_picks_" + LocalizedFluid.getNonTranslatedUnitAbbrString(session), "[]"), new BToTheFifth_TypeToken().getType());
    }
    
    @Override
    public int getDefaultSearchTab() {
        int int1;
        final int n = int1 = this.prefs.getInt("default_search_tab", 6001);
        if (!((CountryService)this.countryService.get()).getCurrentCountry().isUnitedStates() && (n == 6000 || (int1 = n) == 6002)) {
            int1 = 6001;
            this.setDefaultSearchTab(6001);
        }
        return int1;
    }
    
    @Override
    public boolean getDontAskForReview() {
        return this.prefs.getBoolean("dont_ask_for_review", false);
    }
    
    @Override
    public String getGAID() {
        return this.prefs.getString("user_gaid", "");
    }
    
    @Override
    public int getJoinedChallengesCount() {
        return this.prefs.getInt("joined_challenges", 0);
    }
    
    @Override
    public int getLastLoginDayNumber() {
        return this.prefs.getInt("last_login_day_number", -1);
    }
    
    @Override
    public long getLastSeenNewChallengesDate() {
        return this.prefs.getLong("last_fetched", 0L);
    }
    
    @Override
    public boolean getMealGoalsScreenVisited() {
        return this.prefs.getBoolean("meal_goals_screen_visited", false);
    }
    
    @Override
    public MealMacrosDisplayUnit getMealMacrosDisplayUnit() {
        return MealMacrosDisplayUnit.values()[this.prefs.getInt("meal_macros_display_unit", MealMacrosDisplayUnit.Percent.ordinal())];
    }
    
    @Override
    public boolean getMultiAddToggleOnByDefault() {
        return this.prefs.getBoolean("multi_add_toggle_on_by_default", false);
    }
    
    @Override
    public KeyedSharedPreferences getPrefs() {
        return this.prefs;
    }
    
    @Override
    public Set<String> getRecentsDeletedFoodOriginalUids() {
        return new HashSet<String>(this.prefs.getStringSet("recents_deleted_food_original_uids", new HashSet<String>()));
    }
    
    @Override
    public boolean getShowFoodEditorMealNotesHint() {
        return this.prefs.getBoolean("show_food_editor_meal_notes_hint", true);
    }
    
    @Override
    public boolean getShowFoodEditorShareHint() {
        return this.prefs.getBoolean("show_food_editor_share_hint", true);
    }
    
    @Override
    public boolean getShowInvitePromotionView() {
        return this.prefs.getBoolean("show_invite_promotion_view", true);
    }
    
    @Override
    public boolean getShowMealBrowseNavigationHint() {
        return this.prefs.getBoolean("show_meal_browse_navigation_hint", true);
    }
    
    @Override
    public int getUnseenNewChallenges() {
        return this.prefs.getInt("unseen_challenges", 0);
    }
    
    @Override
    public UserLocation getUserLocation() {
        return new UserLocation(this.prefs.getString("user_latitude", "0.0"), this.prefs.getString("user_longitude", "0.0"));
    }
    
    @Override
    public int getWeeklyStartDay() {
        return this.prefs.getInt("weekly_start_day", 0);
    }
    
    @Override
    public boolean hasClickedOnPremiumFeature() {
        return this.prefs.getBoolean("has_clicked_premium_feature", false);
    }
    
    @Override
    public boolean hasFriends() {
        return this.prefs.getBoolean("has_friends", false);
    }
    
    @Override
    public boolean hasPremiumAdBeenDisplayed() {
        return this.prefs.getBoolean("premium_ad_displayed", false);
    }
    
    @Override
    public boolean hasProgressPhotosIntroBeenDisplayed() {
        return this.prefs.getBoolean("progress_photos_intro_displayed", false);
    }
    
    @Override
    public boolean hasUserClickedFileExportOnce() {
        return this.prefs.getBoolean("has_user_clicked_file_export_once", false);
    }
    
    @Override
    public boolean isGlobalPrefDBMigrationComplete() {
        return this.prefs.getBoolean("is_global_pref_migration_complete", false);
    }
    
    @Override
    public boolean isNewUser() {
        return this.prefs.getBoolean("is_new_user", false);
    }
    
    @Override
    public boolean mealIdeasViewed() {
        return this.prefs.getBoolean("meal_ideas_viewed", false);
    }
    
    @Override
    public void migrateSharedPreferencesToUserApplicationSettingsIfNeeded() {
        final boolean b = true;
        final KeyedSharedPreferences prefs = this.getPrefs();
        final SharedPreferences.Editor edit = prefs.edit();
        if (prefs.contains("show_meal_goals_promo_card")) {
            ((UserApplicationSettingsService)this.userApplicationSettingsService.get()).setMealGoalsBuyUpsellDismissed(!prefs.getBoolean("show_meal_goals_promo_card", false));
            edit.remove("show_meal_goals_promo_card");
        }
        if (prefs.contains("should_show_scale_interstitial")) {
            ((UserApplicationSettingsService)this.userApplicationSettingsService.get()).setUAScaleUpsellInterstitialViewed(!prefs.getBoolean("should_show_scale_interstitial", false));
            edit.remove("should_show_scale_interstitial");
        }
        if (prefs.contains("should_show_scale_upsell_on_progress")) {
            ((UserApplicationSettingsService)this.userApplicationSettingsService.get()).setUAScaleUpsellProgressEntryViewed(!prefs.getBoolean("should_show_scale_upsell_on_progress", false) && b);
            edit.remove("should_show_scale_upsell_on_progress");
        }
        edit.commit();
    }
    
    @Override
    public void removeRecentsDeletedFoodOriginalUid(final String s) {
        final Set<String> recentsDeletedFoodOriginalUids = this.getRecentsDeletedFoodOriginalUids();
        recentsDeletedFoodOriginalUids.remove(s);
        this.prefs.edit().putStringSet("recents_deleted_food_original_uids", (Set)recentsDeletedFoodOriginalUids).apply();
    }
    
    @Override
    public void setAcceptedTOSVersion(final int n) {
        this.writeInt("accepted_tos_version", n);
    }
    
    @Override
    public void setAppInstallationDate(final Long n) {
        this.writeLong("app_installation_date", 0L);
    }
    
    @Override
    public void setCurrentMeasurementFilterSelection(final int n) {
        this.writeInt("current_weight_filter_selection", n);
    }
    
    @Override
    public void setCustomWaterQuickPicks(final Session session, final ArrayList<LocalizedFluid> list) {
        this.writeString("water_quick_picks_" + LocalizedFluid.getNonTranslatedUnitAbbrString(session), new Gson().toJson((Object)list));
    }
    
    @Override
    public void setDefaultSearchTab(final int n) {
        this.writeInt("default_search_tab", n);
    }
    
    @Override
    public void setDontAskForReview(final boolean b) {
        this.writeBoolean("dont_ask_for_review", b);
    }
    
    @Override
    public void setFoodDBMigrated(final boolean b) {
        this.writeBoolean("food_db_migrated", b);
    }
    
    @Override
    public void setFreeTrialsEnabled(final boolean b) {
        this.writeBoolean("free_trials_enabled", b);
    }
    
    @Override
    public void setGAID(final String s) {
        this.writeString("user_gaid", s);
    }
    
    @Override
    public void setGlobalPrefDBMigrationComplete() {
        this.writeBoolean("is_global_pref_migration_complete", true);
    }
    
    @Override
    public void setHasClickedOnPremiumFeature(final boolean b) {
        this.writeBoolean("has_clicked_premium_feature", b);
    }
    
    @Override
    public void setHasFriends(final boolean b) {
        this.writeBoolean("has_friends", b);
    }
    
	@DexAdd
	class BToTheFifth_Runnable implements Runnable {
        @Override
        public void run() {
            LocalSettingsServiceImpl.this.setIsNewUser(false);
        }
	};
	
	@DexEdit
    @Override
    public void setIsNewUser(final boolean b) {
        if (b) {
            this.handler.postDelayed((Runnable)new BToTheFifth_Runnable(), 3600000L);
        }
        this.prefs.edit().putBoolean("is_new_user", b).apply();
    }
    
    @Override
    public void setJoinedChallengesCount(final int n) {
        this.writeInt("joined_challenges", n);
    }
    
    @Override
    public void setLastLoginDayNumber(final int n) {
        this.writeInt("last_login_day_number", n);
    }
    
    @Override
    public void setLastSeenNewChallengesDate(final long n) {
        this.writeLong("last_fetched", n);
    }
    
    @Override
    public void setMealGoalsScreenVisited(final boolean b) {
        this.writeBoolean("meal_goals_screen_visited", b);
    }
    
    @Override
    public void setMealIdeasViewed() {
        this.writeBoolean("meal_ideas_viewed", true);
    }
    
    @Override
    public void setMealMacrosDisplayUnit(final MealMacrosDisplayUnit mealMacrosDisplayUnit) {
        this.writeInt("meal_macros_display_unit", mealMacrosDisplayUnit.ordinal());
    }
    
    @Override
    public void setMultiAddToggleOnByDefault(final boolean b) {
        this.writeBoolean("multi_add_toggle_on_by_default", b);
    }
    
    @Override
    public void setPremiumAdDisplayed(final boolean b) {
        this.writeBoolean("premium_ad_displayed", b);
    }
    
    @Override
    public void setProgressPhotosIntroDisplayed(final boolean b) {
        this.writeBoolean("progress_photos_intro_displayed", b);
    }
    
    @Override
    public void setShouldNativeAdVideoAutoPlay(final boolean b) {
        this.writeBoolean("should_video_ad_auto_play", b);
    }
    
    @Override
    public void setShouldShowAllMeals(final boolean b) {
        this.writeBoolean("should_show_all_meals", b);
    }
    
    @Override
    public void setShouldShowBlogArticlesInNewsFeed(final boolean b) {
        this.writeBoolean("show_blog_articles_in_news_feed", b);
    }
    
    @Override
    public void setShouldShowWaterCard(final boolean b) {
        this.writeBoolean("should_show_water_card", b);
    }
    
    @Override
    public void setShowDeleteDiaryEntriesConfirm(final boolean b) {
        this.writeBoolean("should_show_diary_delete_entries_confirm", b);
    }
    
    @Override
    public void setShowDeleteMealConfirmationDialog(final boolean b) {
        this.writeBoolean("show_delete_meal_confirmation_dialog", b);
    }
    
    @Override
    public void setShowFoodEditorMealNotesHint(final boolean b) {
        this.prefs.edit().putBoolean("show_food_editor_meal_notes_hint", b).apply();
    }
    
    @Override
    public void setShowFoodEditorShareHint(final boolean b) {
        this.prefs.edit().putBoolean("show_food_editor_share_hint", b).apply();
    }
    
    @Override
    public void setShowInvitePromotionView(final boolean b) {
        this.writeBoolean("show_invite_promotion_view", b);
    }
    
    @Override
    public void setShowMealBrowseNavigationHint(final boolean b) {
        this.prefs.edit().putBoolean("show_meal_browse_navigation_hint", b).apply();
    }
    
    @Override
    public void setShowMealGoalsTip(final boolean b) {
        this.writeBoolean("show_meal_goals_tip", b);
    }
    
    @Override
    public void setShowMealMacrosTip(final boolean b) {
        this.writeBoolean("show_meal_macros_tip", b);
    }
    
    @Override
    public void setUnseenNewChallenges(final int n) {
        this.writeInt("unseen_challenges", n);
    }
    
    @Override
    public void setUserHasClickedFileExportOnce() {
        this.writeBoolean("has_user_clicked_file_export_once", true);
    }
    
    @Override
    public void setUserLocation(final UserLocation userLocation) {
        this.writeString("user_latitude", userLocation.getLatitude());
        this.writeString("user_longitude", userLocation.getLongitude());
    }
    
    @Override
    public void setWeeklyStartDay(final int n) {
        this.writeInt("weekly_start_day", n);
    }
    
    @Override
    public void setWereFoodOriginalIdsCorrected(final boolean b) {
        this.writeBoolean("were_food_original_ids_corrected", b);
    }
    
    @Override
    public boolean shouldNativeAdVideoAutoPlay() {
        return this.prefs.getBoolean("should_video_ad_auto_play", true);
    }
    
    @Override
    public boolean shouldShowAllMeals() {
        return this.prefs.getBoolean("should_show_all_meals", true);
    }
    
    @Override
    public boolean shouldShowBlogArticlesInNewsFeed() {
        return this.prefs.getBoolean("show_blog_articles_in_news_feed", true);
    }
    
    @Override
    public boolean shouldShowDeleteDiaryEntriesConfirm() {
        return this.prefs.getBoolean("should_show_diary_delete_entries_confirm", true);
    }
    
    @Override
    public boolean shouldShowWaterCard() {
        return this.prefs.getBoolean("should_show_water_card", true);
    }
    
    @Override
    public boolean showDeleteMealConfirmationDialog() {
        return this.prefs.getBoolean("show_delete_meal_confirmation_dialog", true);
    }
    
    @Override
    public boolean showMealGoalsTip() {
        return this.prefs.getBoolean("show_meal_goals_tip", true);
    }
    
    @Override
    public boolean showMealMacrosTip() {
        return this.prefs.getBoolean("show_meal_macros_tip", true);
    }
    
    @Override
    public void updateJoinedChallengesCountBy(final int n) {
        this.setJoinedChallengesCount(this.getJoinedChallengesCount() + n);
    }
    
    @Override
    public boolean wasFoodDBMigrated() {
        return this.prefs.getBoolean("food_db_migrated", false);
    }
    
    @Override
    public boolean wereFoodOriginalIdsCorrected() {
        return this.prefs.getBoolean("were_food_original_ids_corrected", false);
    }
	
	@DexAdd
	@Override
    public void setShouldShowNetCarbs(final boolean b) {
        this.writeBoolean("show_net_carbs", b);
    }
	
	@DexAdd
	@Override
    public boolean shouldShowNetCarbs() {
        return this.prefs.getBoolean("show_net_carbs", false);
    }

    @DexAdd
    @Override
    public void setShouldShowSmartCarbs(final boolean b) {
        this.writeBoolean("show_smart_carbs", b);
    }

    @DexAdd
    @Override
    public boolean shouldShowSmartCarbs() {
        return this.prefs.getBoolean("show_smart_carbs", false);
    }
}
