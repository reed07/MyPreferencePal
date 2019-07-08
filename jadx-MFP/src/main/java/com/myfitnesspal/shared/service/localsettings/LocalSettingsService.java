package com.myfitnesspal.shared.service.localsettings;

import com.myfitnesspal.feature.consents.model.ConsentData;
import com.myfitnesspal.feature.diary.model.MealMacrosDisplayUnit;
import com.myfitnesspal.shared.model.UserLocation;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.preferences.KeyedSharedPreferences;
import java.util.ArrayList;
import java.util.Set;

public interface LocalSettingsService {
    void addRecentsDeletedFoodOriginalUid(String str);

    boolean areFreeTrialsEnabled();

    boolean didUserSeeFoodSearchWalkthrough();

    boolean didUserSeeTimestampFeature();

    void disableAdTracking();

    void enableAdTracking();

    int getAcceptedTOSVersion();

    Long getAppInstallationDate();

    int getCurrentMeasurementFilterSelection();

    ArrayList getCustomWaterQuickPicks(Session session);

    int getDefaultSearchTab();

    boolean getDontAskForReview();

    String getGAID();

    boolean getIsPersonalizedAdConsentAccepted();

    boolean getIsUserSubjectToPersonalizedAds();

    int getJoinedChallengesCount();

    int getLastLoginDayNumber();

    long getLastSeenNewChallengesDate();

    boolean getMealGoalsScreenVisited();

    MealMacrosDisplayUnit getMealMacrosDisplayUnit();

    String getMealRecipesAndFoodsSortOrder();

    boolean getMultiAddToggleOnByDefault();

    long getPasswordResetDateTime();

    KeyedSharedPreferences getPrefs();

    long getPremiumCrownTooltipShownTime();

    Set<String> getRecentsDeletedFoodOriginalUids();

    boolean getShouldInterruptUserForAdConsents();

    boolean getShowFoodEditorMealNotesHint();

    boolean getShowInvitePromotionView();

    boolean getShowMealBrowseNavigationHint();

    String getTrialRolloutName();

    int getUnseenNewChallenges();

    UserLocation getUserLocation();

    int getWeeklyStartDay();

    boolean hasClickedOnPremiumFeature();

    boolean hasFriends();

    boolean hasPremiumAdBeenDisplayed();

    boolean hasProgressPhotosIntroBeenDisplayed();

    boolean hasUserClickedFileExportOnce();

    boolean hasUserSeenGenericWebView(String str);

    boolean isAdTrackingEnabled();

    boolean isGlobalPrefDBMigrationComplete();

    boolean isNewUser();

    boolean isPremiumCrownTooltipShown();

    ConsentData isUserAcceptedConsentsMatrix(String str);

    boolean isUserAcceptedLocationPermission();

    boolean mealIdeasViewed();

    void migrateSharedPreferencesToUserApplicationSettingsIfNeeded();

    void removeRecentsDeletedFoodOriginalUid(String str);

    void setAcceptedTOSVersion(int i);

    void setAppInstallationDate(Long l);

    void setCurrentMeasurementFilterSelection(int i);

    void setCustomWaterQuickPicks(Session session, ArrayList<LocalizedFluid> arrayList);

    void setDefaultSearchTab(int i);

    void setDontAskForReview(boolean z);

    void setFoodDBMigrated(boolean z);

    void setFreeTrialsEnabled(boolean z);

    void setGAID(String str);

    void setGlobalPrefDBMigrationComplete();

    void setHasClickedOnPremiumFeature(boolean z);

    void setHasFriends(boolean z);

    void setIsNewUser(boolean z);

    void setIsPersonalizedAdConsentAccepted(boolean z);

    void setIsUserSubjectToPersonalizedAds(boolean z);

    void setJoinedChallengesCount(int i);

    void setLastLoginDayNumber(int i);

    void setLastSeenNewChallengesDate(long j);

    void setMealGoalsScreenVisited(boolean z);

    void setMealIdeasViewed();

    void setMealMacrosDisplayUnit(MealMacrosDisplayUnit mealMacrosDisplayUnit);

    void setMealRecipesAndFoodsSortOrder(String str);

    void setMultiAddToggleOnByDefault(boolean z);

    void setPasswordResetDateTime(long j);

    void setPremiumAdDisplayed(boolean z);

    void setPremiumCrownTooltipShown(boolean z);

    void setPremiumCrownTooltipShownTime(long j);

    void setProgressPhotosIntroDisplayed(boolean z);

    void setShouldInterruptUserForAdConsents(boolean z);

    void setShouldShowAllMeals(boolean z);

    void setShouldShowBlogArticlesInNewsFeed(boolean z);

    void setShouldShowBlogVideosInNewsFeed(boolean z);

    void setShouldShowFoodTimestamps(boolean z);

    void setShouldShowWaterCard(boolean z);

    void setShowDeleteDiaryEntriesConfirm(boolean z);

    void setShowDeleteMealConfirmationDialog(boolean z);

    void setShowFoodEditorMealNotesHint(boolean z);

    void setShowInvitePromotionView(boolean z);

    void setShowMealBrowseNavigationHint(boolean z);

    void setShowMealGoalsTip(boolean z);

    void setShowMealMacrosTip(boolean z);

    void setTrialRolloutName(String str);

    void setUnseenNewChallenges(int i);

    void setUserAcceptedLocationPermission(boolean z);

    void setUserHasClickedFileExportOnce();

    void setUserLocation(UserLocation userLocation);

    void setUserSawTimestampFeature(boolean z);

    void setWeeklyStartDay(int i);

    void setWereFoodOriginalIdsCorrected(boolean z);

    boolean shouldNativeAdVideoAutoPlay();

    boolean shouldShowAllMeals();

    boolean shouldShowBlogArticlesInNewsFeed();

    boolean shouldShowBlogVideosInNewsFeed();

    boolean shouldShowDeleteDiaryEntriesConfirm();

    boolean shouldShowFoodTimestamps();

    boolean shouldShowWaterCard();

    boolean showDeleteMealConfirmationDialog();

    boolean showMealGoalsTip();

    boolean showMealMacrosTip();

    void storeUserAcceptedConsentsMatrix(ConsentData consentData, String str);

    void trackGenericWebView(String str);

    void updateJoinedChallengesCountBy(int i);

    void userSawFoodSearchWalkthrough();

    boolean wasFoodDBMigrated();

    boolean wereFoodOriginalIdsCorrected();
}
