package com.myfitnesspal.shared.model;

import android.content.Context;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v1.UserV1GoalPreferences;
import com.myfitnesspal.shared.model.v1.UserV1NutrientGoals;
import com.myfitnesspal.shared.model.v2.MfpAccount;
import com.myfitnesspal.shared.model.v2.MfpGoalDisplay;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpLocationPreferences;
import com.myfitnesspal.shared.model.v2.MfpProfile;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.uacf.core.util.Function1;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface User {
    boolean allowFacebookFriendsToFindMe();

    boolean autoPostBlogToFacebook();

    boolean autoPostCompleteToFacebook();

    boolean autoPostExerciseToFacebook();

    boolean autoPostLostWeightToFacebook();

    boolean autoPostStatusToFacebook();

    boolean autoPostToFacebook();

    MfpAccount getAccount();

    Date getActiveDate();

    DiaryDay getActiveDiaryDay();

    int getBodyWeightUnitPreference();

    MfpGoalDisplay getCustomDisplayGoal();

    int getDistanceUnitPreference();

    String getEmail();

    String getEncryptedEmailAddress();

    int getEnergyUnitPreference();

    String[] getFeedSettingPropertyKeys();

    HashMap<String, Boolean> getFeedSettings();

    int getGender();

    MfpGoalPreferences getGoalPreferences();

    int getHeightUnitPreference();

    String getImageUrl();

    long getLocalId();

    MfpLocationPreferences getLocationPreferences();

    long getMasterDatabaseId();

    MealNames getMealNames();

    UserProfile getProfile();

    List<MfpProfile> getProfiles();

    List<MfpStepSource> getStepSources();

    int getTOSAcceptedVersion();

    int getTOSCurrentVersion();

    String getUid();

    String getUserId();

    MfpProfile getUserProfile();

    int getUserStatus();

    UserV1 getUserV1();

    UserV1GoalPreferences getUserV1GoalPreferences();

    @Deprecated
    UserV1NutrientGoals getUserV1NutrientGoals();

    String getUsername();

    int getWaterUnitPreference();

    boolean hasAcceptedTermsAndPrivacy();

    boolean hasMasterDatabaseId();

    boolean isEmailValid();

    boolean isLoggedIn();

    boolean isMarketingOptinEnabled();

    boolean isMealGoalsEnabled();

    boolean isProfileCountryUS();

    boolean isUnderageOrEatingDisorder();

    void markTOSAccepted();

    float poundsLost();

    void resetTOSAcceptedVersion();

    void setAccount(MfpAccount mfpAccount);

    void setActiveDate(Date date);

    void setDisplayDiaryMealMacros(boolean z);

    void setEmail(String str);

    void setEnergyUnitPreference(int i);

    void setIsMealGoalsEnabled(boolean z);

    void setProfile(UserProfile userProfile);

    void setProperty(String str, String str2);

    void setShouldUpdateGoals(boolean z);

    void setShouldUpdateGoalsAndUpdateProperty(boolean z);

    void setUserId(String str);

    void setUserV1(UserV1 userV1);

    void setUsername(String str);

    boolean shouldDisplayDiaryMealMacros();

    boolean shouldShowConsent();

    boolean shouldUpdateGoals();

    void updateCurrentWeightFromMeasurements(Context context);

    void updateGoalDisplays(Function1<List<MfpGoalDisplay>> function1, Function1<List<Exception>> function12, MfpGoalDisplay mfpGoalDisplay);

    void updateGoalPreferences(Function1<MfpGoalPreferences> function1, Function1<List<Exception>> function12, MfpGoalPreferences mfpGoalPreferences);

    void updateNewsletterSettings(boolean z);

    void updatePropertyNamed(String str);

    List<MfpStepSource> updateStepSources(List<MfpStepSource> list) throws ApiException;

    void updateStepSourcesAsync(Function1<List<MfpStepSource>> function1, Function1<List<Exception>> function12, List<MfpStepSource> list);
}
