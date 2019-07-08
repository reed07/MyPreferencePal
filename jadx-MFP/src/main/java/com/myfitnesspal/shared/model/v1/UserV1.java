package com.myfitnesspal.shared.model.v1;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Facebook;
import com.myfitnesspal.shared.constants.Constants.UserProperties.NewsFeed;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Notifications;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Units;
import com.myfitnesspal.shared.constants.DiarySharingSetting;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.MeasurementTypesDBAdapter;
import com.myfitnesspal.shared.event.GoalsRecalculatedEvent;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.TimeZoneHelper;
import com.squareup.otto.Bus;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;

public class UserV1 extends DatabaseObject {
    private static final boolean VERBOSE_LOGGING = false;
    private int acceptedTOSVersion;
    private Date activeDate;
    private boolean allowFacebookFriendsToFindMe;
    private boolean autoPostBlogToFacebook;
    private boolean autoPostCompleteToFacebook;
    private boolean autoPostExerciseToFacebook;
    private boolean autoPostLostWeightToFacebook;
    private boolean autoPostStatusToFacebook;
    private boolean autoPostToFacebook;
    private int bodyWeightUnitPreference;
    @Inject
    Lazy<Bus> bus;
    @Inject
    Lazy<CountryService> countryService;
    private int currentTOSVersion;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<DiaryService> diaryService;
    private boolean displayDiaryMealMacros = true;
    private int distanceUnitPreference;
    private String email;
    private boolean emailValid;
    private int energyUnitPreference;
    private HashMap<String, Boolean> feedSettings = new HashMap<>();
    @Inject
    Lazy<UserV1GoalPreferences> goalPreferences;
    private UserV1NutrientGoals goals;
    private int heightUnitPreference;
    private boolean isMealGoalsEnabled;
    private boolean isValid = true;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    private MealNames mealNames;
    @Inject
    Lazy<MeasurementsService> measurementsService;
    private UserProfile profile;
    private boolean shouldUpdateGoals;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;
    private String thirdPartyAuthToken;
    private int thirdPartyServiceId;
    private String thirdPartyUserId;
    private String uacfId;
    @Inject
    Lazy<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelper;
    @Inject
    Lazy<UserImageService> userImageService;
    private int userStatus;
    private String username;
    private int waterUnitPreference;

    public boolean isShouldShowConsent() {
        return false;
    }

    public UserV1() {
        MyFitnessPalApp.getInstance().component().inject(this);
        resetToDefault();
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void invalidate() {
        this.isValid = false;
    }

    public UserV1NutrientGoals getNutrientGoals() {
        return this.goals;
    }

    public UserV1GoalPreferences getGoalPreferences() {
        return (UserV1GoalPreferences) this.goalPreferences.get();
    }

    public void setGoals(UserV1NutrientGoals userV1NutrientGoals) {
        this.goals = userV1NutrientGoals;
    }

    public boolean isEmailValid() {
        return this.emailValid;
    }

    public void setEmailValid(boolean z) {
        this.emailValid = z;
    }

    public synchronized UserV1 resetToDefault() {
        this.profile = new UserProfile(getLocalId());
        this.profile.initAsBlankProfile();
        UserV1NutrientGoals userV1NutrientGoals = new UserV1NutrientGoals();
        userV1NutrientGoals.setDefaults();
        setGoals(userV1NutrientGoals);
        ((UserV1GoalPreferences) this.goalPreferences.get()).setDefaults();
        initializeUnitPrefs(true);
        createDefaultMealNames();
        createInitialFeedSettings();
        setUserStatus(0);
        setLocalId(0);
        setMasterDatabaseId(0);
        this.username = "";
        this.thirdPartyServiceId = 0;
        this.thirdPartyUserId = "";
        this.thirdPartyAuthToken = "";
        this.email = "";
        this.emailValid = false;
        this.allowFacebookFriendsToFindMe = false;
        this.autoPostToFacebook = false;
        this.autoPostStatusToFacebook = false;
        this.autoPostLostWeightToFacebook = false;
        this.autoPostExerciseToFacebook = false;
        this.autoPostBlogToFacebook = false;
        this.autoPostCompleteToFacebook = false;
        this.shouldUpdateGoals = false;
        this.activeDate = new Date();
        return this;
    }

    public void setLocalId(long j) {
        super.setLocalId(j);
        this.profile.setLocalId(j);
    }

    public Date getActiveDate() {
        return this.activeDate;
    }

    public void setActiveDate(Date date) {
        this.activeDate = date;
    }

    public MealNames getMealNames() {
        return new MealNames(this.mealNames);
    }

    public void setMealNames(String[] strArr) {
        this.mealNames = new MealNames(strArr);
    }

    private void createDefaultMealNames() {
        this.mealNames = new MealNames(new String[]{MealTypeName.BREAKFAST, MealTypeName.LUNCH, MealTypeName.DINNER, MealTypeName.SNACK});
    }

    public UserProfile getProfile() {
        return this.profile;
    }

    public void setProfile(UserProfile userProfile) {
        this.profile = userProfile;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public int getThirdPartyServiceId() {
        return this.thirdPartyServiceId;
    }

    public String getThirdPartyUserId() {
        return this.thirdPartyUserId;
    }

    public String getThirdPartyAuthToken() {
        return this.thirdPartyAuthToken;
    }

    public String getEmail() {
        return this.email;
    }

    public void setDisplayDiaryMealMacros(boolean z) {
        this.displayDiaryMealMacros = z;
    }

    public boolean shouldDisplayDiaryMealMacros() {
        return this.displayDiaryMealMacros;
    }

    public boolean isMealGoalsEnabled() {
        return this.isMealGoalsEnabled;
    }

    public void setIsMealGoalsEnabled(boolean z) {
        this.isMealGoalsEnabled = z;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public Map<String, String> allProperties() {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            writeAllPropertyKeysTo(arrayList, arrayList2);
            int size = arrayList.size();
            HashMap hashMap = new HashMap(size);
            for (int i = 0; i < size; i++) {
                hashMap.put(arrayList.get(i), arrayList2.get(i));
            }
            return hashMap;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public void writeAllPropertyKeysTo(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        String[] feedSettingPropertyKeys;
        String[] notificationSettingPropertyKeys;
        String[] emailSettingPropertyKeys;
        String[] newsletterSettingsPropertyKeys;
        try {
            if (this.email != null) {
                arrayList.add("email");
                arrayList2.add(this.email);
            }
            if (this.profile.currentTimezone != null) {
                arrayList.add(Basic.TIMEZONE_IDENTIFIER);
                arrayList2.add(this.profile.currentTimezone.getTimezone_identifier());
            }
            arrayList.add(Basic.VALID_EMAIL);
            arrayList2.add(this.emailValid ? "yes" : "no");
            arrayList.add("meal_names");
            arrayList2.add(Strings.arrayOfStringsToCSV(this.mealNames != null ? (String[]) this.mealNames.getNames().toArray(new String[this.mealNames.size()]) : new String[0]));
            arrayList.add(Basic.USE_METRIC);
            arrayList2.add(this.profile.useMetric ? "yes" : "no");
            arrayList.add("gender");
            arrayList2.add(this.profile.genderString);
            arrayList.add(Basic.DATE_OF_BIRTH);
            arrayList2.add(Database.encodeDate(this.profile.dateOfBirth));
            arrayList.add(Basic.COUNTRY_NAME);
            arrayList2.add(this.profile.countryName);
            arrayList.add(Basic.POSTAL_CODE);
            arrayList2.add(this.profile.postalCode);
            arrayList.add(Basic.LIFESTYLE_NAME);
            arrayList2.add(this.profile.lifestyleName);
            arrayList.add(Basic.CURRENT_WEIGHT_IN_POUNDS);
            arrayList2.add(String.valueOf(this.profile.getCurrentWeight().getPounds()));
            arrayList.add(Basic.GOAL_WEIGHT_IN_POUNDS);
            arrayList2.add(String.valueOf(this.profile.getGoalWeight().getPounds()));
            arrayList.add(Basic.HEIGHT_IN_INCHES);
            arrayList2.add(String.valueOf(this.profile.height.getInches()));
            arrayList.add(Basic.REQUIRES_START_TIME_FOR_EXERCISE_ENTRIES);
            arrayList2.add(this.profile.getIsLinkedWithFitbit() ? "yes" : "no");
            arrayList.add(Basic.DIARY_PRIVACY);
            arrayList2.add(this.profile.getDiarySharingSetting().toString());
            arrayList.add(Basic.DIARY_PASSWORD);
            arrayList2.add(this.profile.diaryPassword);
            arrayList.add(Basic.UACF_ID);
            arrayList2.add(this.uacfId);
            this.goals.readKeysAndValues(arrayList, arrayList2);
            ((UserV1GoalPreferences) this.goalPreferences.get()).readKeysAndValues(arrayList, arrayList2);
            arrayList.add(Basic.LAST_GOALS_RECALCULATION_DATE);
            arrayList2.add(Database.encodeDateAndTime(this.goals.getLastRecalculatedDate()));
            if (this.feedSettings.size() == 0) {
                createInitialFeedSettings();
            }
            for (String str : getFeedSettingPropertyKeys()) {
                boolean booleanValue = ((Boolean) this.feedSettings.get(str)).booleanValue();
                arrayList.add(str);
                arrayList2.add(booleanValue ? "true" : "false");
            }
            for (String str2 : UserProfile.getNotificationSettingPropertyKeys()) {
                Boolean bool = (Boolean) this.profile.notificationSettings.get(str2);
                if (bool != null) {
                    arrayList.add(str2);
                    arrayList2.add(bool.booleanValue() ? "true" : "false");
                }
            }
            arrayList.add(Notifications.QUIET_TIME_BEGIN_OFFSET);
            arrayList2.add(Integer.toString(this.profile.quietTimeBeginOffsetFromMidnightUTC));
            arrayList.add(Notifications.QUIET_TIME_END_OFFSET);
            arrayList2.add(Integer.toString(this.profile.quietTimeEndOffsetFromMidnightUTC));
            for (String str3 : UserProfile.getEmailSettingPropertyKeys()) {
                Boolean bool2 = (Boolean) this.profile.emailSettings.get(str3);
                if (bool2 != null) {
                    arrayList.add(str3);
                    arrayList2.add(bool2.booleanValue() ? "true" : "false");
                }
            }
            for (String str4 : UserProfile.getNewsletterSettingsPropertyKeys()) {
                Boolean bool3 = (Boolean) this.profile.emailSettings.get(str4);
                if (bool3 != null) {
                    arrayList.add(str4);
                    arrayList2.add(bool3.booleanValue() ? "yes" : "no");
                }
            }
            arrayList.add(Basic.TOS_CURRENT_VERSION);
            arrayList2.add(Strings.toString(Integer.valueOf(getTOSCurrentVersion())));
            arrayList.add(Basic.TOS_ACCEPTED_VERSION);
            arrayList2.add(Strings.toString(Integer.valueOf(getTOSAcceptedVersion())));
            arrayList.add(Basic.SHOULD_UPDATE_GOALS);
            arrayList2.add(Strings.toString(Boolean.valueOf(shouldUpdateGoals())));
            arrayList.add(Basic.DISPLAY_DIARY_MEAL_MACROS);
            arrayList2.add(Strings.toString(Boolean.valueOf(shouldDisplayDiaryMealMacros())));
            arrayList.add(Basic.MEAL_GOALS_ENABLED);
            arrayList2.add(Strings.toString(Boolean.valueOf(isMealGoalsEnabled())));
            arrayList.add(Facebook.FIND_BY_FACEBOOK_ENABLED);
            arrayList2.add(Strings.toString(Boolean.valueOf(allowFacebookFriendsToFindMe())));
            arrayList.add(Facebook.AUTOPOST_TO_FACEBOOK_ENABLED);
            arrayList2.add(Strings.toString(Boolean.valueOf(autoPostToFacebook())));
            arrayList.add(Facebook.FACEBOOK_POST_ON_STATUS_UPDATES);
            arrayList2.add(Strings.toString(Boolean.valueOf(autoPostStatusToFacebook())));
            arrayList.add(Facebook.FACEBOOK_POST_ON_LOST_WEIGHT);
            arrayList2.add(Strings.toString(Boolean.valueOf(autoPostLostWeightToFacebook())));
            arrayList.add(Facebook.FACEBOOK_POST_ON_PERFORMED_EXERCISE);
            arrayList2.add(Strings.toString(Boolean.valueOf(autoPostExerciseToFacebook())));
            arrayList.add(Facebook.FACEBOOK_POST_ON_BLOG_POSTS);
            arrayList2.add(Strings.toString(Boolean.valueOf(autoPostBlogToFacebook())));
            arrayList.add(Facebook.FACEBOOK_POST_ON_COMPLETED_DIARY);
            arrayList2.add(Strings.toString(Boolean.valueOf(autoPostCompleteToFacebook())));
            arrayList.add(Units.BODY_WEIGHT_UNIT_PREFERENCE);
            arrayList2.add(Strings.toString(Integer.valueOf(getBodyWeightUnitPreference())));
            arrayList.add(Units.HEIGHT_UNIT_PREFERENCE);
            arrayList2.add(Strings.toString(Integer.valueOf(getHeightUnitPreference())));
            arrayList.add(Units.DISTANCE_UNIT_PREFERENCE);
            arrayList2.add(Strings.toString(Integer.valueOf(getDistanceUnitPreference())));
            arrayList.add(Units.ENERGY_UNIT_PREFERENCE);
            arrayList2.add(Strings.toString(Integer.valueOf(getEnergyUnitPreference())));
            arrayList.add(Units.WATER_UNIT_PREFERENCE);
            arrayList2.add(Strings.toString(Integer.valueOf(getWaterUnitPreference())));
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void setProperty(String str, String str2) {
        String[] notificationSettingPropertyKeys;
        String[] emailSettingPropertyKeys;
        String[] newsletterSettingsPropertyKeys;
        boolean z;
        if (str2 != null) {
            try {
                if (str.equals("email")) {
                    setEmail(str2);
                } else if (str.equals(Basic.VALID_EMAIL)) {
                    setEmailValid(str2.equalsIgnoreCase("yes"));
                } else if (str.equals("meal_names")) {
                    setMealNames(Strings.csvToArrayOfStringsWithEscaping(str2));
                } else if (str.equals(Basic.USE_METRIC)) {
                    this.profile.setUseMetric(str2.equals("yes"));
                } else if (str.equals("gender")) {
                    this.profile.setGenderString(str2);
                } else if (str.equals(Basic.DATE_OF_BIRTH)) {
                    this.profile.setDateOfBirth(Database.decodeDateString(str2));
                } else if (str.equals(Basic.COUNTRY_NAME)) {
                    this.profile.setCountryName(str2);
                } else if (str.equals(Basic.POSTAL_CODE)) {
                    this.profile.setPostalCode(str2);
                } else if (str.equals(Basic.LIFESTYLE_NAME)) {
                    this.profile.setLifestyleName(str2);
                } else if (str.equals(Basic.CURRENT_WEIGHT_IN_POUNDS)) {
                    this.profile.setCurrentWeight(new UserWeight(Float.valueOf(str2).floatValue()));
                } else if (str.equals(Basic.GOAL_WEIGHT_IN_POUNDS)) {
                    this.profile.setGoalWeight(new UserWeight(Float.valueOf(str2).floatValue()));
                } else if (str.equals(Basic.HEIGHT_IN_INCHES)) {
                    this.profile.height.setInches(Float.valueOf(str2).floatValue());
                } else if (str.equals(Basic.LAST_GOALS_RECALCULATION_DATE)) {
                    this.goals.setLastRecalculatedDate(Database.decodeDateString(str2));
                } else if (str.equals(Basic.REQUIRES_START_TIME_FOR_EXERCISE_ENTRIES)) {
                    this.profile.setIsLinkedWithFitbit(str2.equals("yes"));
                } else if (str.equals(Basic.DIARY_PRIVACY)) {
                    this.profile.setDiarySharingSetting(DiarySharingSetting.fromString(str2));
                } else if (str.equals(Basic.DIARY_PASSWORD)) {
                    this.profile.setDiaryPassword(str2);
                } else if (str.equals(Basic.TIMEZONE_IDENTIFIER)) {
                    Timezone timezoneWithIdentifier = TimeZoneHelper.timezoneWithIdentifier(str2);
                    if (timezoneWithIdentifier != null) {
                        this.profile.setCurrentTimezone(timezoneWithIdentifier);
                    }
                } else if (str.equals(Basic.TOS_ACCEPTED_VERSION)) {
                    setTOSAcceptedVersion(Integer.valueOf(str2).intValue());
                } else if (str.equals(Basic.TOS_CURRENT_VERSION)) {
                    setTOSCurrentVersion(Integer.valueOf(str2).intValue());
                } else if (str.equals(Basic.SHOULD_UPDATE_GOALS)) {
                    setShouldUpdateGoals(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Basic.DISPLAY_DIARY_MEAL_MACROS)) {
                    setDisplayDiaryMealMacros(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Basic.MEAL_GOALS_ENABLED)) {
                    setIsMealGoalsEnabled(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Facebook.FIND_BY_FACEBOOK_ENABLED)) {
                    setAllowFacebookFriendsToFindMe(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Facebook.AUTOPOST_TO_FACEBOOK_ENABLED)) {
                    setAutoPostToFacebook(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Facebook.FACEBOOK_POST_ON_STATUS_UPDATES)) {
                    setAutoPostStatusToFacebook(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Facebook.FACEBOOK_POST_ON_LOST_WEIGHT)) {
                    setAutoPostLostWeightToFacebook(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Facebook.FACEBOOK_POST_ON_PERFORMED_EXERCISE)) {
                    setAutoPostExerciseToFacebook(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Facebook.FACEBOOK_POST_ON_BLOG_POSTS)) {
                    setAutoPostBlogToFacebook(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Facebook.FACEBOOK_POST_ON_COMPLETED_DIARY)) {
                    setAutoPostCompleteToFacebook(Boolean.valueOf(str2).booleanValue());
                } else if (str.equals(Units.BODY_WEIGHT_UNIT_PREFERENCE)) {
                    setBodyWeightUnitPreference(Integer.valueOf(str2).intValue());
                } else if (str.equals(Units.HEIGHT_UNIT_PREFERENCE)) {
                    setHeightUnitPreference(Integer.valueOf(str2).intValue());
                } else if (str.equals(Units.DISTANCE_UNIT_PREFERENCE)) {
                    setDistanceUnitPreference(Integer.valueOf(str2).intValue());
                } else if (str.equals(Units.ENERGY_UNIT_PREFERENCE)) {
                    setEnergyUnitPreference(Integer.valueOf(str2).intValue());
                } else if (str.equals(Units.WATER_UNIT_PREFERENCE)) {
                    setWaterUnitPreference(Integer.valueOf(str2).intValue());
                } else if (str.equals("user_status")) {
                    setUserStatus(Integer.valueOf(str2).intValue());
                } else if (str.equals(Basic.UACF_ID)) {
                    setUacfId(str2);
                }
                this.goals.writeKeyAndValue(str, str2);
                ((UserV1GoalPreferences) this.goalPreferences.get()).writeKeyAndValue(str, str2);
                String[] feedSettingPropertyKeys = getFeedSettingPropertyKeys();
                int length = feedSettingPropertyKeys.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String str3 = feedSettingPropertyKeys[i];
                    if (str.equals(str3)) {
                        if (this.feedSettings == null) {
                            this.feedSettings = new HashMap<>();
                        }
                        this.feedSettings.put(str3, Boolean.valueOf(str2.trim()));
                    } else {
                        i++;
                    }
                }
                for (String str4 : UserProfile.getNotificationSettingPropertyKeys()) {
                    if (str.equals(str4)) {
                        this.profile.notificationSettings.put(str4, Boolean.valueOf(str2.equals("true")));
                    }
                }
                if (str.equals(Notifications.QUIET_TIME_BEGIN_OFFSET)) {
                    if (Strings.notEmpty(str2)) {
                        this.profile.setQuietTimeBeginOffsetFromMidnightUTC(Integer.parseInt(str2));
                    }
                } else if (str.equals(Notifications.QUIET_TIME_END_OFFSET) && Strings.notEmpty(str2)) {
                    this.profile.setQuietTimeEndOffsetFromMidnightUTC(Integer.parseInt(str2));
                }
                for (String str5 : UserProfile.getEmailSettingPropertyKeys()) {
                    if (str.equals(str5)) {
                        this.profile.emailSettings.put(str5, Boolean.valueOf(str2.equals("true")));
                    }
                }
                for (String str6 : UserProfile.getNewsletterSettingsPropertyKeys()) {
                    if (str.equals(str6)) {
                        HashMap<String, Boolean> hashMap = this.profile.emailSettings;
                        if (!str2.equals("true")) {
                            if (!str2.equals("yes")) {
                                z = false;
                                hashMap.put(str6, Boolean.valueOf(z));
                            }
                        }
                        z = true;
                        hashMap.put(str6, Boolean.valueOf(z));
                    }
                }
            } catch (Exception e) {
                Ln.e(e);
            }
        }
    }

    @Deprecated
    public void recalculateGoals() {
        float calculateCalorieGoalForUser = ((UserV1GoalPreferences) this.goalPreferences.get()).calculateCalorieGoalForUser(this);
        this.goals.recalculate(calculateCalorieGoalForUser, getMealNames());
        ((UserV1GoalPreferences) this.goalPreferences.get()).recalculate(this, calculateCalorieGoalForUser);
        ((DbConnectionManager) this.dbConnectionManager.get()).usersDbAdapter().saveUser(this);
        ((Bus) this.bus.get()).post(new GoalsRecalculatedEvent());
    }

    public float poundsLost() {
        try {
            long measurementTypeIdFromDescription = ((DbConnectionManager) this.dbConnectionManager.get()).measurementTypesDbAdapter().measurementTypeIdFromDescription(Measurement.WEIGHT);
            if (measurementTypeIdFromDescription == 0) {
                return BitmapDescriptorFactory.HUE_RED;
            }
            return ((MeasurementsService) this.measurementsService.get()).getMostRecentMeasurementValueBeforeDate(this.goals.getLastRecalculatedDate(), this.localId, measurementTypeIdFromDescription) - this.profile.getCurrentWeight().getPounds();
        } catch (SQLiteException e) {
            Ln.e(e);
            return -1.0f;
        }
    }

    public void updateCurrentWeightFromMeasurements(Context context) {
        try {
            long weightMeasurementTypeId = getWeightMeasurementTypeId(context);
            if (weightMeasurementTypeId > 0) {
                float mostRecentMeasurementValueBeforeDate = ((MeasurementsService) this.measurementsService.get()).getMostRecentMeasurementValueBeforeDate(new Date(), this.localId, weightMeasurementTypeId);
                int pounds = (int) (this.profile.getCurrentWeight().getPounds() * 10.0f);
                int i = (int) (10.0f * mostRecentMeasurementValueBeforeDate);
                if (((double) mostRecentMeasurementValueBeforeDate) > 0.0d && pounds != i) {
                    this.profile.setCurrentWeight(new UserWeight(mostRecentMeasurementValueBeforeDate));
                    updatePropertyNamed(Basic.CURRENT_WEIGHT_IN_POUNDS);
                }
            }
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void updateStartingWeight(float f) {
        ((MeasurementsService) this.measurementsService.get()).setInitialMeasurementOfType(Measurement.WEIGHT, f);
        this.profile.setStartingWeight(new UserWeight(f));
    }

    public void updatePropertyNamed(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            writeAllPropertyKeysTo(arrayList, arrayList2);
            for (int i = 0; i < arrayList.size(); i++) {
                if (((String) arrayList.get(i)).equals(str)) {
                    ((DbConnectionManager) this.dbConnectionManager.get()).userPropertiesDbAdapter().saveUserProperty(str, (String) arrayList2.get(i), this.localId);
                }
            }
            ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public DiaryDay getActiveDiaryDay() {
        return ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
    }

    public String[] getFeedSettingPropertyKeys() {
        return new String[]{NewsFeed.CREATE_STATUS_ON_NEW_FRIENDS, NewsFeed.CREATE_STATUS_ON_REPLIED_TO_TOPIC, NewsFeed.CREATE_STATUS_ON_CREATED_TOPIC, NewsFeed.CREATE_STATUS_ON_BLOG_POSTS, NewsFeed.CREATE_STATUS_ON_STATUS_COMMENTS, NewsFeed.CREATE_STATUS_ON_WALL_POSTS, NewsFeed.CREATE_STATUS_ON_HASNT_LOGGED_IN, NewsFeed.CREATE_STATUS_ON_CONSECUTIVE_LOGINS, NewsFeed.CREATE_STATUS_ON_LOST_WEIGHT, NewsFeed.CREATE_STATUS_ON_COMPLETED_DIARY, NewsFeed.CREATE_STATUS_ON_PERFORMED_EXERCISE};
    }

    private void createInitialFeedSettings() {
        try {
            HashMap hashMap = new HashMap();
            String[] feedSettingPropertyKeys = getFeedSettingPropertyKeys();
            for (String str : feedSettingPropertyKeys) {
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -1825340116) {
                    if (hashCode != -302746899) {
                        if (hashCode == 602519165) {
                            if (str.equals(NewsFeed.CREATE_STATUS_ON_LOST_WEIGHT)) {
                                c = 0;
                            }
                        }
                    } else if (str.equals(NewsFeed.CREATE_STATUS_ON_HASNT_LOGGED_IN)) {
                        c = 1;
                    }
                } else if (str.equals(NewsFeed.CREATE_STATUS_ON_BLOG_POSTS)) {
                    c = 2;
                }
                switch (c) {
                    case 0:
                    case 1:
                    case 2:
                        hashMap.put(str, Boolean.valueOf(false));
                        break;
                    default:
                        hashMap.put(str, Boolean.valueOf(true));
                        break;
                }
            }
            setFeedSettings(hashMap);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public HashMap<String, Boolean> getFeedSettings() {
        if (this.feedSettings.size() == 0) {
            createInitialFeedSettings();
        }
        return this.feedSettings;
    }

    public void setFeedSettings(HashMap<String, Boolean> hashMap) {
        this.feedSettings = hashMap;
    }

    public int getGender() {
        return getProfile().isFemale().booleanValue() ^ true ? 1 : 0;
    }

    public String getImageUrl() {
        UserImage userImageWithLocalId = ((UserImageService) this.userImageService.get()).getUserImageWithLocalId(((UserImageService) this.userImageService.get()).getImageIdForMostRecentThumbnail(this.localId));
        if (userImageWithLocalId == null) {
            return "";
        }
        return Strings.toString(userImageWithLocalId.getThumbnailURL());
    }

    public void updateNewsletterSettings(boolean z) {
        String[] newsletterSettingsPropertyKeys;
        for (String str : UserProfile.getNewsletterSettingsPropertyKeys()) {
            this.profile.emailSettings.put(str, Boolean.valueOf(z));
            updatePropertyNamed(str);
        }
    }

    public boolean allNewslettersEnabled() {
        for (String str : UserProfile.getNewsletterSettingsPropertyKeys()) {
            if (!((Boolean) this.profile.emailSettings.get(str)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasAcceptedTermsAndPrivacy() {
        int tOSAcceptedVersion = getTOSAcceptedVersion();
        int acceptedTOSVersion2 = ((LocalSettingsService) this.localSettingsService.get()).getAcceptedTOSVersion();
        if (acceptedTOSVersion2 < tOSAcceptedVersion) {
            ((LocalSettingsService) this.localSettingsService.get()).setAcceptedTOSVersion(tOSAcceptedVersion);
        } else {
            if (acceptedTOSVersion2 > tOSAcceptedVersion) {
                setTOSAcceptedVersion(acceptedTOSVersion2);
                updatePropertyNamed(Basic.TOS_ACCEPTED_VERSION);
                ((UpdatedTermsAnalyticsHelper) this.updatedTermsAnalyticsHelper.get()).reportOutdatedValueReceived(tOSAcceptedVersion, acceptedTOSVersion2);
            }
            tOSAcceptedVersion = acceptedTOSVersion2;
        }
        return tOSAcceptedVersion >= this.currentTOSVersion;
    }

    public boolean shouldUpdateGoals() {
        return this.shouldUpdateGoals;
    }

    public void setShouldUpdateGoals(boolean z) {
        this.shouldUpdateGoals = z;
    }

    public boolean allowFacebookFriendsToFindMe() {
        return this.allowFacebookFriendsToFindMe;
    }

    public void setAllowFacebookFriendsToFindMe(boolean z) {
        this.allowFacebookFriendsToFindMe = z;
    }

    public boolean autoPostToFacebook() {
        return this.autoPostToFacebook;
    }

    public void setAutoPostToFacebook(boolean z) {
        this.autoPostToFacebook = z;
    }

    public boolean autoPostStatusToFacebook() {
        return this.autoPostStatusToFacebook;
    }

    public void setAutoPostStatusToFacebook(boolean z) {
        this.autoPostStatusToFacebook = z;
    }

    public boolean autoPostLostWeightToFacebook() {
        return this.autoPostLostWeightToFacebook;
    }

    public void setAutoPostLostWeightToFacebook(boolean z) {
        this.autoPostLostWeightToFacebook = z;
    }

    public boolean autoPostExerciseToFacebook() {
        return this.autoPostExerciseToFacebook;
    }

    public void setAutoPostExerciseToFacebook(boolean z) {
        this.autoPostExerciseToFacebook = z;
    }

    public boolean autoPostBlogToFacebook() {
        return this.autoPostBlogToFacebook;
    }

    public void setAutoPostBlogToFacebook(boolean z) {
        this.autoPostBlogToFacebook = z;
    }

    public boolean autoPostCompleteToFacebook() {
        return this.autoPostCompleteToFacebook;
    }

    public void setAutoPostCompleteToFacebook(boolean z) {
        this.autoPostCompleteToFacebook = z;
    }

    public int getBodyWeightUnitPreference() {
        return this.bodyWeightUnitPreference;
    }

    public void setBodyWeightUnitPreference(int i) {
        this.bodyWeightUnitPreference = i;
    }

    public int getHeightUnitPreference() {
        return this.heightUnitPreference;
    }

    public void setHeightUnitPreference(int i) {
        this.heightUnitPreference = i;
    }

    public int getDistanceUnitPreference() {
        return this.distanceUnitPreference;
    }

    public void setDistanceUnitPreference(int i) {
        this.distanceUnitPreference = i;
    }

    public int getEnergyUnitPreference() {
        return this.energyUnitPreference;
    }

    public void setEnergyUnitPreference(int i) {
        this.energyUnitPreference = i;
    }

    public int getWaterUnitPreference() {
        return this.waterUnitPreference;
    }

    public void setWaterUnitPreference(int i) {
        this.waterUnitPreference = i;
    }

    private void initializeUnitPrefs(boolean z) {
        PreferredUnits preferredUnits = PreferredUnits.PREFERRED_UNITS_DEFAULT;
        if (!z) {
            Country countryFromCountryCode = ((CountryService) this.countryService.get()).getCountryFromCountryCode(Locale.getDefault().getCountry());
            preferredUnits = countryFromCountryCode != null ? countryFromCountryCode.getPreferredUnits() : PreferredUnits.PREFERRED_UNITS_DEFAULT;
        }
        setBodyWeightUnitPreference(preferredUnits.getWeight().getValue());
        setHeightUnitPreference(preferredUnits.getHeight().getValue());
        setDistanceUnitPreference(preferredUnits.getDistance().getValue());
        setEnergyUnitPreference(preferredUnits.getEnergy().getValue());
        setWaterUnitPreference(preferredUnits.getWater().getValue());
    }

    private long getWeightMeasurementTypeId(Context context) {
        return new MeasurementTypesDBAdapter(context).measurementTypeIdFromDescription(Measurement.WEIGHT);
    }

    public void setUserStatus(int i) {
        this.userStatus = i;
    }

    public void setUacfId(String str) {
        this.uacfId = str;
    }

    public int getUserStatus() {
        return this.userStatus;
    }

    public int getTOSCurrentVersion() {
        return this.currentTOSVersion;
    }

    public int getTOSAcceptedVersion() {
        return this.acceptedTOSVersion;
    }

    public void setTOSAcceptedVersion(int i) {
        this.acceptedTOSVersion = i;
    }

    public void setTOSCurrentVersion(int i) {
        this.currentTOSVersion = i;
    }

    public void markTOSAccepted() {
        this.acceptedTOSVersion = this.currentTOSVersion;
        ((LocalSettingsService) this.localSettingsService.get()).setAcceptedTOSVersion(this.currentTOSVersion);
        updatePropertyNamed(Basic.TOS_ACCEPTED_VERSION);
    }

    public void resetTOSAcceptedVersion() {
        this.acceptedTOSVersion = 0;
        ((LocalSettingsService) this.localSettingsService.get()).setAcceptedTOSVersion(0);
    }
}
