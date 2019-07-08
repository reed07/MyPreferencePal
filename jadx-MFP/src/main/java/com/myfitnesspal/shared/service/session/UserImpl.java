package com.myfitnesspal.shared.service.session;

import android.content.Context;
import android.support.annotation.Nullable;
import com.crashlytics.android.Crashlytics;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Newsletter;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.Country;
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
import com.myfitnesspal.shared.model.v2.UserV2;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.util.UserV1Utils;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserImpl implements User {
    private static final String CUSTOM_GOALS_DISPLAY_ID = "custom_goal_display";
    private static UserV1 DEFAULT_USER_V1;
    private static UserV2 DEFAULT_USER_V2;
    private final Lazy<AppIndexerBot> appIndexerBot;
    private final DbConnectionManager dbConnectionManager;
    private final Lazy<LoginModel> loginModel;
    private final Lazy<PremiumService> premiumService;
    private final Lazy<UserV2Service> userService;
    private UserV1 userV1 = DEFAULT_USER_V1;
    private UserV2 userV2;

    public UserImpl(Lazy<AppIndexerBot> lazy, Lazy<UserV2Service> lazy2, Lazy<LoginModel> lazy3, Lazy<PremiumService> lazy4, DbConnectionManager dbConnectionManager2) {
        ensureDefaultUsers();
        this.appIndexerBot = lazy;
        this.userService = lazy2;
        this.loginModel = lazy3;
        this.premiumService = lazy4;
        this.dbConnectionManager = dbConnectionManager2;
        loadFromDiskSync();
    }

    public boolean isLoggedIn() {
        if (!((AppIndexerBot) this.appIndexerBot.get()).isActive()) {
            UserV1 userV12 = this.userV1;
            if (!(userV12 == null || userV12 == DEFAULT_USER_V1)) {
                UserV2 userV22 = this.userV2;
                if (userV22 != null && userV22 != DEFAULT_USER_V2 && Strings.notEmpty(getUserId()) && Strings.notEmpty(getUsername()) && hasMasterDatabaseId()) {
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized void setUserV1(UserV1 userV12) {
        if (userV12 == null) {
            userV12 = DEFAULT_USER_V1;
        }
        this.userV1 = userV12;
    }

    public synchronized void setUserV2(UserV2 userV22) {
        this.userV2 = userV22 != null ? userV22 : DEFAULT_USER_V2;
        if (userV22 != null && Strings.notEmpty(userV22.getId())) {
            String username = userV22.getUsername();
            Crashlytics.setUserIdentifier(userV22.getId());
            Crashlytics.setUserName(username);
        }
    }

    public synchronized UserV1 getUserV1() {
        return this.userV1 == DEFAULT_USER_V1 ? null : this.userV1;
    }

    public void setUserId(String str) {
        throw new UacfNotImplementedException();
    }

    public void setUsername(String str) {
        throw new UacfNotImplementedException();
    }

    public void setEmail(String str) {
        getUserV1().setEmail(str);
    }

    public void setAccount(MfpAccount mfpAccount) {
        throw new UacfNotImplementedException();
    }

    public void updateGoalPreferences(Function1<MfpGoalPreferences> function1, Function1<List<Exception>> function12, MfpGoalPreferences mfpGoalPreferences) {
        ((UserV2Service) this.userService.get()).updateGoalPreferencesAsync(function1, function12, mfpGoalPreferences);
    }

    public void updateStepSourcesAsync(Function1<List<MfpStepSource>> function1, Function1<List<Exception>> function12, List<MfpStepSource> list) {
        ((UserV2Service) this.userService.get()).updateStepsSourcesAsync(function1, function12, list);
    }

    public List<MfpStepSource> updateStepSources(List<MfpStepSource> list) throws ApiException {
        return ((UserV2Service) this.userService.get()).updateStepsSources(list);
    }

    public void updateGoalDisplays(Function1<List<MfpGoalDisplay>> function1, Function1<List<Exception>> function12, MfpGoalDisplay mfpGoalDisplay) {
        ((UserV2Service) this.userService.get()).updateGoalDisplaysAsync(function1, function12, mfpGoalDisplay);
    }

    public void updateNewsletterSettings(boolean z) {
        this.userV1.updateNewsletterSettings(z);
    }

    public String getEncryptedEmailAddress() {
        return Strings.encryptString(getEmail());
    }

    public List<MfpProfile> getProfiles() {
        return this.userV2.getProfiles();
    }

    public MfpAccount getAccount() {
        return this.userV2.getAccount();
    }

    public MfpGoalPreferences getGoalPreferences() {
        return this.userV2.getGoalPreferences();
    }

    public MfpLocationPreferences getLocationPreferences() {
        return this.userV2.getLocationPreferences();
    }

    public List<MfpStepSource> getStepSources() {
        return this.userV2.getStepSources();
    }

    public MfpGoalDisplay getCustomDisplayGoal() {
        List<MfpGoalDisplay> goalDisplays = this.userV2.getGoalDisplays();
        if (CollectionUtils.notEmpty((Collection<?>) goalDisplays)) {
            for (MfpGoalDisplay mfpGoalDisplay : goalDisplays) {
                if (Strings.equals(mfpGoalDisplay.getId(), "custom_goal_display")) {
                    return mfpGoalDisplay;
                }
            }
        }
        return new MfpGoalDisplay();
    }

    public String getUserId() {
        return this.userV2.getId();
    }

    public String getUsername() {
        return this.userV2.getUsername();
    }

    public String getEmail() {
        return this.userV1.getEmail();
    }

    public long getLocalId() {
        return this.userV1.getLocalId();
    }

    public long getMasterDatabaseId() {
        return this.userV1.getMasterDatabaseId();
    }

    public String getUid() {
        return this.userV2.getId();
    }

    public boolean hasMasterDatabaseId() {
        return this.userV1.hasMasterDatabaseId();
    }

    public int getGender() {
        return this.userV1.getGender();
    }

    @Deprecated
    public UserV1NutrientGoals getUserV1NutrientGoals() {
        return this.userV1.getNutrientGoals();
    }

    public UserV1GoalPreferences getUserV1GoalPreferences() {
        return this.userV1.getGoalPreferences();
    }

    public UserProfile getProfile() {
        return this.userV1.getProfile();
    }

    public void setProfile(UserProfile userProfile) {
        this.userV1.setProfile(userProfile);
    }

    public float poundsLost() {
        return this.userV1.poundsLost();
    }

    public int getBodyWeightUnitPreference() {
        return this.userV1.getBodyWeightUnitPreference();
    }

    public void setActiveDate(Date date) {
        this.userV1.setActiveDate(date);
    }

    public Date getActiveDate() {
        return this.userV1.getActiveDate();
    }

    public DiaryDay getActiveDiaryDay() {
        return this.userV1.getActiveDiaryDay();
    }

    public MealNames getMealNames() {
        return this.userV1.getMealNames();
    }

    public int getEnergyUnitPreference() {
        return this.userV1.getEnergyUnitPreference();
    }

    public void setProperty(String str, String str2) {
        this.userV1.setProperty(str, str2);
    }

    public void updatePropertyNamed(String str) {
        this.userV1.updatePropertyNamed(str);
    }

    public int getUserStatus() {
        return this.userV1.getUserStatus();
    }

    public boolean hasAcceptedTermsAndPrivacy() {
        return this.userV1.hasAcceptedTermsAndPrivacy();
    }

    public boolean shouldShowConsent() {
        return this.userV1.isShouldShowConsent();
    }

    public boolean shouldUpdateGoals() {
        return this.userV1.shouldUpdateGoals();
    }

    public void setShouldUpdateGoals(boolean z) {
        this.userV1.setShouldUpdateGoals(z);
    }

    public void setShouldUpdateGoalsAndUpdateProperty(boolean z) {
        setShouldUpdateGoals(z);
        updatePropertyNamed(Basic.SHOULD_UPDATE_GOALS);
    }

    public boolean allowFacebookFriendsToFindMe() {
        return this.userV1.allowFacebookFriendsToFindMe();
    }

    public boolean autoPostToFacebook() {
        return this.userV1.autoPostToFacebook();
    }

    public boolean autoPostStatusToFacebook() {
        return this.userV1.autoPostToFacebook();
    }

    public boolean autoPostLostWeightToFacebook() {
        return this.userV1.autoPostLostWeightToFacebook();
    }

    public boolean autoPostExerciseToFacebook() {
        return this.userV1.autoPostExerciseToFacebook();
    }

    public boolean autoPostBlogToFacebook() {
        return this.userV1.autoPostBlogToFacebook();
    }

    public boolean autoPostCompleteToFacebook() {
        return this.userV1.autoPostCompleteToFacebook();
    }

    public int getHeightUnitPreference() {
        return this.userV1.getHeightUnitPreference();
    }

    public int getDistanceUnitPreference() {
        return this.userV1.getDistanceUnitPreference();
    }

    public int getWaterUnitPreference() {
        return this.userV1.getWaterUnitPreference();
    }

    public void setEnergyUnitPreference(int i) {
        this.userV1.setEnergyUnitPreference(i);
    }

    public HashMap<String, Boolean> getFeedSettings() {
        return this.userV1.getFeedSettings();
    }

    @Nullable
    public String getImageUrl() {
        String imageUrl = this.userV1.getImageUrl();
        return Strings.notEmpty(imageUrl) ? imageUrl : getUserProfile().getMainImageUrl();
    }

    public boolean isEmailValid() {
        return this.userV1.isEmailValid();
    }

    public String[] getFeedSettingPropertyKeys() {
        return this.userV1.getFeedSettingPropertyKeys();
    }

    public MfpProfile getUserProfile() {
        List profiles = getProfiles();
        if (CollectionUtils.notEmpty((Collection<?>) profiles)) {
            return (MfpProfile) profiles.get(0);
        }
        return new MfpProfile();
    }

    public void updateCurrentWeightFromMeasurements(Context context) {
        this.userV1.updateCurrentWeightFromMeasurements(context);
    }

    public int getTOSCurrentVersion() {
        return this.userV1.getTOSCurrentVersion();
    }

    public int getTOSAcceptedVersion() {
        return this.userV1.getTOSAcceptedVersion();
    }

    public void resetTOSAcceptedVersion() {
        this.userV1.resetTOSAcceptedVersion();
    }

    public void markTOSAccepted() {
        this.userV1.markTOSAccepted();
    }

    public void setDisplayDiaryMealMacros(boolean z) {
        this.userV1.setDisplayDiaryMealMacros(z);
    }

    public boolean shouldDisplayDiaryMealMacros() {
        return ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealMacros) && this.userV1.shouldDisplayDiaryMealMacros();
    }

    public boolean isMealGoalsEnabled() {
        return this.userV1.isMealGoalsEnabled();
    }

    public void setIsMealGoalsEnabled(boolean z) {
        this.userV1.setIsMealGoalsEnabled(z);
    }

    public boolean isUnderageOrEatingDisorder() {
        int userStatus = getUserStatus();
        return userStatus == 4 || userStatus == 5 || userStatus == 6;
    }

    private void loadFromDiskSync() {
        Thread thread = new Thread(new Runnable() {
            public final void run() {
                UserImpl.this.setUserV1(UserV1Utils.loadUserOnCurrentThread(((LoginModel) UserImpl.this.loginModel.get()).getUsername(), UserImpl.this.dbConnectionManager));
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public final void run() {
                UserImpl.this.setUserV2(((UserV2Service) UserImpl.this.userService.get()).fetchFromDiskSync());
            }
        });
        thread.setName("UserV1 load");
        thread.start();
        thread2.setName("UserV2 load");
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            Ln.e("error while loading session from disk!", e);
        }
    }

    private synchronized void ensureDefaultUsers() {
        if (DEFAULT_USER_V1 == null) {
            DEFAULT_USER_V1 = new UserV1();
            DEFAULT_USER_V1.invalidate();
        }
        if (DEFAULT_USER_V2 == null) {
            DEFAULT_USER_V2 = new UserV2();
        }
    }

    public boolean isMarketingOptinEnabled() {
        HashMap<String, Boolean> hashMap = getProfile().emailSettings;
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(Newsletter.NEWSLETTER_FOR_FEATURE_ANNOUNCEMENTS);
        arrayList.add(Newsletter.NEWSLETTER_FOR_HEALTHY_LIVING_TIP);
        arrayList.add("recipe");
        arrayList.add(Newsletter.NEWSLETTER_FOR_WORKOUTS);
        arrayList.add(Newsletter.NEWSLETTER_FOR_UA_GEAR);
        for (String str : arrayList) {
            if (((Boolean) hashMap.get(str)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean isProfileCountryUS() {
        return getProfile().getCountryName().equals(Country.UNITED_STATES_LONG);
    }
}
