package com.myfitnesspal.shared.service.localsettings;
import lanchon.dexpatcher.annotation.*;
import com.myfitnesspal.shared.service.session.*;
import com.myfitnesspal.feature.diary.model.*;
import com.uacf.core.preferences.*;
import java.util.*;
import com.myfitnesspal.shared.model.*;
import com.myfitnesspal.shared.model.unitconv.*;

@DexEdit(defaultAction = DexAction.IGNORE)
public interface LocalSettingsService
{
	@DexAdd
	void setShouldShowNetCarbs(final boolean p0);
	
	@DexAdd
	boolean shouldShowNetCarbs();

    @DexAdd
    void setShouldShowSmartCarbs(final boolean p0);

    @DexAdd
    boolean shouldShowSmartCarbs();
	
    void addRecentsDeletedFoodOriginalUid(final String p0);
    
    boolean areFreeTrialsEnabled();
    
    int getAcceptedTOSVersion();
    
    Long getAppInstallationDate();
    
    int getCurrentMeasurementFilterSelection();
    
    ArrayList getCustomWaterQuickPicks(final Session p0);
    
    int getDefaultSearchTab();
    
    boolean getDontAskForReview();
    
    String getGAID();
    
    int getJoinedChallengesCount();
    
    int getLastLoginDayNumber();
    
    long getLastSeenNewChallengesDate();
    
    boolean getMealGoalsScreenVisited();
    
    MealMacrosDisplayUnit getMealMacrosDisplayUnit();
    
    boolean getMultiAddToggleOnByDefault();
    
    KeyedSharedPreferences getPrefs();
    
    Set<String> getRecentsDeletedFoodOriginalUids();
    
    boolean getShowFoodEditorMealNotesHint();
    
    boolean getShowFoodEditorShareHint();
    
    boolean getShowInvitePromotionView();
    
    boolean getShowMealBrowseNavigationHint();
    
    int getUnseenNewChallenges();
    
    UserLocation getUserLocation();
    
    int getWeeklyStartDay();
    
    boolean hasClickedOnPremiumFeature();
    
    boolean hasFriends();
    
    boolean hasPremiumAdBeenDisplayed();
    
    boolean hasProgressPhotosIntroBeenDisplayed();
    
    boolean hasUserClickedFileExportOnce();
    
    boolean isGlobalPrefDBMigrationComplete();
    
    boolean isNewUser();
    
    boolean mealIdeasViewed();
    
    void migrateSharedPreferencesToUserApplicationSettingsIfNeeded();
    
    void removeRecentsDeletedFoodOriginalUid(final String p0);
    
    void setAcceptedTOSVersion(final int p0);
    
    void setAppInstallationDate(final Long p0);
    
    void setCurrentMeasurementFilterSelection(final int p0);
    
    void setCustomWaterQuickPicks(final Session p0, final ArrayList<LocalizedFluid> p1);
    
    void setDefaultSearchTab(final int p0);
    
    void setDontAskForReview(final boolean p0);
    
    void setFoodDBMigrated(final boolean p0);
    
    void setFreeTrialsEnabled(final boolean p0);
    
    void setGAID(final String p0);
    
    void setGlobalPrefDBMigrationComplete();
    
    void setHasClickedOnPremiumFeature(final boolean p0);
    
    void setHasFriends(final boolean p0);
    
    void setIsNewUser(final boolean p0);
    
    void setJoinedChallengesCount(final int p0);
    
    void setLastLoginDayNumber(final int p0);
    
    void setLastSeenNewChallengesDate(final long p0);
    
    void setMealGoalsScreenVisited(final boolean p0);
    
    void setMealIdeasViewed();
    
    void setMealMacrosDisplayUnit(final MealMacrosDisplayUnit p0);
    
    void setMultiAddToggleOnByDefault(final boolean p0);
    
    void setPremiumAdDisplayed(final boolean p0);
    
    void setProgressPhotosIntroDisplayed(final boolean p0);
    
    void setShouldNativeAdVideoAutoPlay(final boolean p0);
    
    void setShouldShowAllMeals(final boolean p0);
    
    void setShouldShowBlogArticlesInNewsFeed(final boolean p0);
    
    void setShouldShowWaterCard(final boolean p0);
    
    void setShowDeleteDiaryEntriesConfirm(final boolean p0);
    
    void setShowDeleteMealConfirmationDialog(final boolean p0);
    
    void setShowFoodEditorMealNotesHint(final boolean p0);
    
    void setShowFoodEditorShareHint(final boolean p0);
    
    void setShowInvitePromotionView(final boolean p0);
    
    void setShowMealBrowseNavigationHint(final boolean p0);
    
    void setShowMealGoalsTip(final boolean p0);
    
    void setShowMealMacrosTip(final boolean p0);
    
    void setUnseenNewChallenges(final int p0);
    
    void setUserHasClickedFileExportOnce();
    
    void setUserLocation(final UserLocation p0);
    
    void setWeeklyStartDay(final int p0);
    
    void setWereFoodOriginalIdsCorrected(final boolean p0);
    
    boolean shouldNativeAdVideoAutoPlay();
    
    boolean shouldShowAllMeals();
    
    boolean shouldShowBlogArticlesInNewsFeed();
    
    boolean shouldShowDeleteDiaryEntriesConfirm();
    
    boolean shouldShowWaterCard();
    
    boolean showDeleteMealConfirmationDialog();
    
    boolean showMealGoalsTip();
    
    boolean showMealMacrosTip();
    
    void updateJoinedChallengesCountBy(final int p0);
    
    boolean wasFoodDBMigrated();
    
    boolean wereFoodOriginalIdsCorrected();
}
