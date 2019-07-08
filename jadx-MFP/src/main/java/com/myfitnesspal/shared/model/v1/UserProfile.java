package com.myfitnesspal.shared.model.v1;

import com.myfitnesspal.shared.constants.Constants.Exercise.ActivityLevel;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Email;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Newsletter;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Notifications;
import com.myfitnesspal.shared.constants.DiarySharingSetting;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.util.CalendarUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.TimeZoneHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class UserProfile extends DatabaseObject {
    public static final float DEFAULT_CURRENT_WEIGHT = 120.0f;
    public static final float DEFAULT_GOAL_WEIGHT = 100.0f;
    public static final float DEFAULT_STARTING_WEIGHT = 120.0f;
    private double bmi;
    String countryName;
    Timezone currentTimezone;
    private UserWeight currentWeight;
    Date dateOfBirth;
    String diaryPassword;
    DiarySharingSetting diarySharingSetting;
    HashMap<String, Integer> diary_privacy_setting_t;
    public HashMap<String, Boolean> emailSettings;
    String genderString;
    float goalCalories;
    float goalLossPerWeek;
    private UserWeight goalWeight;
    UserLinearMeasurement height;
    private boolean isLinkedWithFitbit;
    final int kDiaryPrivacySettingFriendsOnly;
    final int kDiaryPrivacySettingPasswordProtected;
    final int kDiaryPrivacySettingPrivate;
    final int kDiaryPrivacySettingPublic;
    String lifestyleName;
    String[] measurementTypes;
    int minutesPerWorkout;
    public HashMap<String, Boolean> notificationSettings;
    String postalCode;
    int quietTimeBeginOffsetFromMidnightUTC;
    int quietTimeEndOffsetFromMidnightUTC;
    private UserWeight startingWeight;
    boolean useMetric;
    int workoutsPerWeek;

    public boolean getDefaultValueForEmailSetting(String str) {
        return true;
    }

    public UserProfile() {
        this.kDiaryPrivacySettingPrivate = 0;
        this.kDiaryPrivacySettingPublic = 1;
        this.kDiaryPrivacySettingFriendsOnly = 2;
        this.kDiaryPrivacySettingPasswordProtected = 3;
        this.diary_privacy_setting_t = new HashMap<>();
        this.useMetric = false;
        this.diary_privacy_setting_t.put("private", Integer.valueOf(0));
        this.diary_privacy_setting_t.put("public", Integer.valueOf(1));
        this.diary_privacy_setting_t.put(AttributeValue.PERMISSION_FRIENDS_ONLY, Integer.valueOf(2));
        this.diary_privacy_setting_t.put("password", Integer.valueOf(3));
    }

    public UserProfile(long j) {
        this();
        setLocalId(j);
    }

    public void setUseMetric(boolean z) {
        this.useMetric = z;
    }

    public Boolean getUseMetric() {
        return Boolean.valueOf(this.useMetric);
    }

    public UserWeight getCurrentWeight() {
        return this.currentWeight;
    }

    public void setCurrentWeight(UserWeight userWeight) {
        this.currentWeight = userWeight;
    }

    public UserWeight getGoalWeight() {
        return this.goalWeight;
    }

    public void setGoalWeight(UserWeight userWeight) {
        this.goalWeight = userWeight;
    }

    public UserWeight getStartingWeight(MeasurementsService measurementsService) {
        if (this.startingWeight == null) {
            MfpMeasurementValue initialMeasurementOfType = measurementsService.getInitialMeasurementOfType(Measurement.WEIGHT);
            setStartingWeight(new UserWeight(initialMeasurementOfType != null ? initialMeasurementOfType.getValue() : 120.0f));
        }
        return this.startingWeight;
    }

    public void setStartingWeight(UserWeight userWeight) {
        this.startingWeight = userWeight;
    }

    public double getCurrentBMI() {
        return this.bmi;
    }

    public void setCurrentBMI(double d) {
        this.bmi = d;
    }

    public UserLinearMeasurement getHeight() {
        return this.height;
    }

    public void setHeight(UserLinearMeasurement userLinearMeasurement) {
        this.height = userLinearMeasurement;
    }

    public String getGenderString() {
        return this.genderString;
    }

    public void setGenderString(String str) {
        this.genderString = str;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date date) {
        this.dateOfBirth = date;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String str) {
        this.countryName = str;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String str) {
        this.postalCode = str;
    }

    public String getLifestyleName() {
        return this.lifestyleName;
    }

    public void setLifestyleName(String str) {
        this.lifestyleName = str;
    }

    public float getGoalLossPerWeek() {
        return this.goalLossPerWeek;
    }

    public void setGoalLossPerWeek(float f) {
        this.goalLossPerWeek = f;
    }

    public float getGoalCalories() {
        return this.goalCalories;
    }

    public void setGoalCalories(float f) {
        this.goalCalories = f;
    }

    public String[] getMeasurementTypes() {
        return this.measurementTypes;
    }

    public void setMeasurementTypes(String[] strArr) {
        this.measurementTypes = strArr;
    }

    public Timezone getCurrentTimezone() {
        return this.currentTimezone;
    }

    public void setCurrentTimezone(Timezone timezone) {
        this.currentTimezone = timezone;
    }

    public void initAsBlankProfile() {
        this.currentTimezone = TimeZoneHelper.currentTimezoneFromLocale();
        this.useMetric = false;
        createDefaultMeasurementTypes();
        this.currentWeight = new UserWeight(120.0f);
        this.goalWeight = new UserWeight(100.0f);
        UserLinearMeasurement userLinearMeasurement = new UserLinearMeasurement();
        userLinearMeasurement.initWithInches(67.0f);
        userLinearMeasurement.setIsForHeight(true);
        this.height = userLinearMeasurement;
        Calendar instance = Calendar.getInstance();
        instance.set(1, 1980);
        instance.set(2, 0);
        instance.set(5, 1);
        this.dateOfBirth = new Date(instance.get(1), instance.get(2), instance.get(5));
        this.genderString = "Female";
        this.lifestyleName = ActivityLevel.SEDENTARY;
        this.workoutsPerWeek = 0;
        this.minutesPerWorkout = 0;
        this.goalLossPerWeek = 1.0f;
        this.countryName = "United LocationUtil";
        this.postalCode = "";
        this.goalCalories = 1500.0f;
        this.isLinkedWithFitbit = false;
        this.diaryPassword = "";
        this.diarySharingSetting = DiarySharingSetting.Private;
        createInitialNotificationSettings();
        createInitialEmailSettings();
    }

    public void setIsLinkedWithFitbit(boolean z) {
        this.isLinkedWithFitbit = z;
    }

    public boolean getIsLinkedWithFitbit() {
        return this.isLinkedWithFitbit;
    }

    public void createDefaultMeasurementTypes() {
        this.measurementTypes = new String[]{Measurement.WEIGHT, Measurement.NECK, Measurement.WAIST, Measurement.HIPS};
    }

    public Boolean isFemale() {
        return Boolean.valueOf(this.genderString.equals("Female"));
    }

    public String getDiaryPassword() {
        return this.diaryPassword;
    }

    public void setDiaryPassword(String str) {
        this.diaryPassword = str;
    }

    public DiarySharingSetting getDiarySharingSetting() {
        return this.diarySharingSetting;
    }

    public void setDiarySharingSetting(DiarySharingSetting diarySharingSetting2) {
        this.diarySharingSetting = diarySharingSetting2;
    }

    public static String[] getNotificationSettingPropertyKeys() {
        return new String[]{Notifications.ENABLE_QUIET_TIME, Notifications.NOTIFY_OF_FRIEND_REQUESTS, Notifications.NOTIFY_OF_NEW_COMMENTS, Notifications.NOTIFY_OF_NEW_MESSAGES, Notifications.NOTIFY_OF_STATUS_COMMENT_LIKES, Notifications.NOTIFY_OF_STATUS_LIKES, Notifications.NOTIFY_OF_STATUS_THREAD_COMMENTS, Notifications.NOTIFY_OF_WALL_POSTS, Notifications.NOTIFY_OF_FRIEND_LOGGED_WORKOUT, Notifications.NOTIFY_OF_FRIEND_JOINED, Notifications.NOTIFY_OF_FRIENDS_HIT_LOGIN_STREAK, Notifications.QUIET_TIME_BEGIN_OFFSET, Notifications.QUIET_TIME_END_OFFSET, Notifications.NOTIFY_OF_A_NEW_CHALLENGE_AVAILABLE, Notifications.NOTIFY_OF_EARNED_ACHIEVEMENT, Notifications.NOTIFY_OF_FRIEND_JOINED_CHALLENGE, Notifications.NOTIFY_OF_FRIEND_CHALLENGE_INVITE, Notifications.NOTIFY_OF_FRIEND_COMPLETES_CHALLENGE, Notifications.NOTIFY_OF_USER_COMPLETES_CHALLENGE};
    }

    private void createInitialNotificationSettings() {
        this.notificationSettings = new HashMap<>();
        String[] notificationSettingPropertyKeys = getNotificationSettingPropertyKeys();
        for (int i = 0; i < notificationSettingPropertyKeys.length; i++) {
            this.notificationSettings.put(notificationSettingPropertyKeys[i], Boolean.valueOf(getDefaultValueForNotificationSetting(notificationSettingPropertyKeys[i])));
        }
        setLocalTimeForQuietTimeBegin(DateTimeUtils.hours(8, new Date()));
        setLocalTimeForQuietTimeEnd(DateTimeUtils.hours(20, new Date()));
    }

    public boolean getDefaultValueForNotificationSetting(String str) {
        return !str.equalsIgnoreCase(Notifications.ENABLE_QUIET_TIME);
    }

    public void setQuietTimeBeginOffsetFromMidnightUTC(int i) {
        this.quietTimeBeginOffsetFromMidnightUTC = i;
    }

    public void setQuietTimeEndOffsetFromMidnightUTC(int i) {
        this.quietTimeEndOffsetFromMidnightUTC = i;
    }

    public Date getLocalTimeForQuietTimeBegin() {
        return TimeZoneHelper.localTimeOfDayFromOffsetFromMidnightUTC(this.quietTimeBeginOffsetFromMidnightUTC, getCurrentTimezone().getTimezone_identifier());
    }

    public void setLocalTimeForQuietTimeBegin(Date date) {
        this.quietTimeBeginOffsetFromMidnightUTC = TimeZoneHelper.offsetFromMidnightUTCFromLocalTimeOfDay(CalendarUtils.getCalendarFromDate(date), getCurrentTimezone().getTimezone_identifier());
    }

    public Date getLocalTimeForQuietTimeEnd() {
        return TimeZoneHelper.localTimeOfDayFromOffsetFromMidnightUTC(this.quietTimeEndOffsetFromMidnightUTC, getCurrentTimezone().getTimezone_identifier());
    }

    public void setLocalTimeForQuietTimeEnd(Date date) {
        this.quietTimeEndOffsetFromMidnightUTC = TimeZoneHelper.offsetFromMidnightUTCFromLocalTimeOfDay(CalendarUtils.getCalendarFromDate(date), getCurrentTimezone().getTimezone_identifier());
    }

    public static String[] getNewsletterSettingsPropertyKeys() {
        return new String[]{Newsletter.NEWSLETTER_FOR_FEATURE_ANNOUNCEMENTS, Newsletter.NEWSLETTER_FOR_HEALTHY_LIVING_TIP, "recipe", Newsletter.NEWSLETTER_FOR_WORKOUTS, Newsletter.NEWSLETTER_FOR_UA_GEAR, Newsletter.NEWSLETTER_FOR_WEEKLY_DIGEST};
    }

    public static String[] getEmailSettingPropertyKeys() {
        return new String[]{Email.FIND_BY_EMAIL_ENABLED, Email.SEND_EMAIL_FOR_ACCEPTED_FRIEND_REQUESTS, Email.SEND_EMAIL_FOR_ACCEPTED_GROUP_INVITES, Email.SEND_EMAIL_FOR_BLOG_COMMENTS, Email.SEND_EMAIL_FOR_FRIEND_REQUESTS, Email.SEND_EMAIL_FOR_GROUP_INVITES, Email.SEND_EMAIL_FOR_MESSAGES, Email.SEND_EMAIL_FOR_PROFILE_COMMENTS, Email.SEND_EMAIL_FOR_STATUS_COMMENT_LIKES, Email.SEND_EMAIL_FOR_STATUS_COMMENTS, Email.SEND_EMAIL_FOR_STATUS_LIKES, Email.SEND_EMAIL_FOR_STATUS_THREAD_COMMENTS, Email.SEND_EMAIL_FOR_CHALLENGE_AVAILABLE, Email.SEND_EMAIL_FOR_EARN_CHALLENGE_ACHIEVEMENT, Email.SEND_EMAIL_FOR_FRIEND_JOINED_CHALLENEGE, Email.SEND_EMAIL_FOR_FRIEND_CHALLENGE_INVITE, Email.SEND_EMAIL_FOR_FRIEND_COMPLETES_CHALLENGE, Email.SEND_EMAIL_FOR_USER_COMPLETES_CHALLENGE};
    }

    public void createInitialEmailSettings() {
        String[] emailSettingPropertyKeys;
        String[] newsletterSettingsPropertyKeys;
        this.emailSettings = new HashMap<>();
        for (String str : getEmailSettingPropertyKeys()) {
            this.emailSettings.put(str, Boolean.valueOf(getDefaultValueForEmailSetting(str)));
        }
        for (String str2 : getNewsletterSettingsPropertyKeys()) {
            this.emailSettings.put(str2, Boolean.valueOf(getDefaultValueForEmailSetting(str2)));
        }
    }
}
