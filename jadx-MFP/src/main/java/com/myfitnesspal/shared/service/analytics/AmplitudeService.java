package com.myfitnesspal.shared.service.analytics;

import android.app.Activity;
import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.amplitude.api.Amplitude;
import com.amplitude.api.Identify;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.settings.model.InsightSettings;
import com.myfitnesspal.feature.settings.ui.fragment.WeeklyNutritionSettingsListFragment;
import com.myfitnesspal.feature.settings.util.DiarySharingSettingsManager;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.ListType;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Registration;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.provider.MPFAppWidgetProvider;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.GlobalAppPreferenceMigrationUtil;
import com.myfitnesspal.shared.util.PincodeHelper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class AmplitudeService extends AnalyticsServiceBase {
    private static final String ANLT_PARAM_USER_PREMIUM_STATUS = "premium_user_status";
    private static final String[] DAYS_OF_WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static final String[] FRIEND_COUNT_BUCKETS = {"0", AppEventsConstants.EVENT_PARAM_VALUE_YES, "2-3", "3-5", "6-10", "11-20", "21-40", "41+"};
    private static final String NON_PAYING_CUSTOMER = "non_paying_customer";
    private static final String ONBOARDING_REQUIRED_CONSENTS = "onboarding_required_consents";
    private static final String ONBOARDING_REQUIRED_CONSENTS_COUNT = "onboarding_required_consents_count";
    private static final String ONBOARDING_TYPE = "onboarding_type";
    private static final String PREF_FRIEND_COUNT = "amplitudeService.friend_count";
    private static final String PREF_HAS_CONNECTED = "amplitudeService.has_connected";
    private static final String PREF_LAST_USER_ID = "amplitudeService.last_user_id";
    private static final String STEP_SOURCE_NONE = "none";
    private static boolean hasInitialized;
    private static final Object initLock = new Object();
    private final Lazy<AppGalleryService> appGalleryService;
    private final Lazy<ConfigService> configService;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final UUID deviceId;
    private final Lazy<DiarySharingSettingsManager> diarySharingSettingsManager;
    private FoodDBAdapter foodDBAdapter;
    private final Lazy<FriendService> friendService;
    private final Lazy<InsightSettings> insightSettings;
    private final Lazy<LocalSettingsService> localSettingsService;
    private LoginModel loginModel;
    private final Lazy<NutrientGoalService> nutrientGoalService;
    private final Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private String paidUserTypeCachedValue = null;
    private final Lazy<RemindersService> remindersService;
    private final SharedPreferences sharedPreferences;
    private final Lazy<SubscriptionService> subscriptionService;
    private final Lazy<UserApplicationSettingsService> userApplicationSettingsService;

    private static String getDefaultSearchTabNameFromId(int i) {
        if (i == 0) {
            return "search";
        }
        switch (i) {
            case SearchTabs.TAB_FREQUENT_FOODS /*6000*/:
                return "frequent";
            case 6001:
                return "recent";
            case 6002:
                return ListType.MY_FOODS;
            case 6003:
                return "meals";
            case 6004:
                return "recipes";
            case 6005:
                return ListType.MOST_USED_EXERCISES;
            case SearchTabs.TAB_MY_EXERCISES /*6006*/:
                return ListType.MY_EXERCISES;
            case 6007:
                return ListType.ALL_EXERCISES;
            default:
                return "NA";
        }
    }

    public static void initialize(Application application) {
        Amplitude.getInstance().initialize(application, getAmplitudeKey(application)).enableForegroundTracking(application);
    }

    private static String getAmplitudeKey(Context context) {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        boolean isDebug = buildConfiguration.isDebug();
        boolean isQABuild = buildConfiguration.isQABuild();
        int i = R.string.amplitude_debug_key;
        if (!isDebug && !isQABuild) {
            i = R.string.amplitude_production_key;
        }
        return context.getString(i);
    }

    public AmplitudeService(Context context, Lazy<AppSettings> lazy, String str, String str2, UUID uuid, Lazy<Session> lazy2, Lazy<FriendService> lazy3, Lazy<AppGalleryService> lazy4, Lazy<ConfigService> lazy5, Lazy<SubscriptionService> lazy6, SharedPreferences sharedPreferences2, Lazy<InsightSettings> lazy7, Lazy<LocalSettingsService> lazy8, Lazy<DiarySharingSettingsManager> lazy9, FoodDBAdapter foodDBAdapter2, LoginModel loginModel2, Lazy<NutrientGoalService> lazy10, Lazy<NutrientGoalsUtil> lazy11, Lazy<RemindersService> lazy12, Lazy<DbConnectionManager> lazy13, Lazy<UserApplicationSettingsService> lazy14) {
        super(context, lazy, str, str2, lazy2, lazy6);
        this.deviceId = uuid;
        this.friendService = lazy3;
        this.appGalleryService = lazy4;
        this.configService = lazy5;
        this.subscriptionService = lazy6;
        this.sharedPreferences = sharedPreferences2;
        this.insightSettings = lazy7;
        this.localSettingsService = lazy8;
        this.diarySharingSettingsManager = lazy9;
        this.foodDBAdapter = foodDBAdapter2;
        this.loginModel = loginModel2;
        this.nutrientGoalService = lazy10;
        this.nutrientGoalsUtil = lazy11;
        this.remindersService = lazy12;
        this.dbConnectionManager = lazy13;
        this.userApplicationSettingsService = lazy14;
    }

    public void reportSessionStart() {
        reportEvent(Events.SESSION_START, getSessionStartAttributeBuilder().putAll(((ConfigService) this.configService.get()).getABTests()).build());
    }

    public void initialize(Activity activity) {
        ensureInitialized();
    }

    public void reportEvent(String str, Map<String, String> map, String str2, int i) {
        if (!Strings.equals(str, Events.EXPERIMENT_START)) {
            super.reportEvent(str, map, str2, i);
            if (isEnabled() && Strings.notEmpty(str)) {
                async(new Runnable(map, str2, str) {
                    private final /* synthetic */ Map f$1;
                    private final /* synthetic */ String f$2;
                    private final /* synthetic */ String f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void run() {
                        AmplitudeService.lambda$reportEvent$0(AmplitudeService.this, this.f$1, this.f$2, this.f$3);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void lambda$reportEvent$0(AmplitudeService amplitudeService, Map map, String str, String str2) {
        HashMap hashMap = new HashMap();
        amplitudeService.addNonEmptyValuesTo(hashMap, map);
        if (Strings.notEmpty(str)) {
            hashMap.put("tag", str);
        }
        try {
            amplitudeService.ensureInitialized();
            Amplitude.getInstance().logEvent(str2, new JSONObject(hashMap));
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void restartSession() {
        if (isEnabled()) {
            initCustomDimensions();
        }
    }

    public boolean isEnabled() {
        return ConfigUtils.isAmplitudeEnabled((ConfigService) this.configService.get());
    }

    public void reportExperimentStart(String str, String str2) {
        super.reportExperimentStart(str, str2);
        if (isEnabled() && Strings.notEmpty(str) && Strings.notEmpty(str2)) {
            Amplitude.getInstance().identify(new Identify().set(str, str2));
        }
    }

    public void reportFoodLookup(Map<String, String> map) {
        reportEvent(Events.FOOD_LOOKUP, map);
    }

    public void updateUserPremiumStatus(@NonNull String str) {
        Amplitude.getInstance().identify(new Identify().set(ANLT_PARAM_USER_PREMIUM_STATUS, str));
    }

    public void reportRequiredConsents(String str, int i, String[] strArr) {
        Identify identify = new Identify();
        if (!Strings.isEmpty(str)) {
            identify.setOnce(ONBOARDING_TYPE, str);
        }
        identify.set(ONBOARDING_REQUIRED_CONSENTS, i);
        identify.set(ONBOARDING_REQUIRED_CONSENTS_COUNT, strArr);
        Amplitude.getInstance().identify(identify);
    }

    private void ensureInitialized() {
        synchronized (initLock) {
            if (isEnabled() && !hasInitialized) {
                initCustomDimensions();
            }
        }
    }

    private String getDayOfTheWeekDimension() {
        return DAYS_OF_WEEK[Calendar.getInstance().get(7) - 1];
    }

    private String getFriendCountDimension(boolean z) {
        Integer valueOf = this.sharedPreferences.contains(PREF_FRIEND_COUNT) ? Integer.valueOf(this.sharedPreferences.getInt(PREF_FRIEND_COUNT, 0)) : null;
        if (!z && valueOf != null) {
            return getFriendCountBucketName(valueOf);
        }
        String friendCountBucketName = getFriendCountBucketName(Integer.valueOf(0));
        this.sharedPreferences.edit().putInt(PREF_FRIEND_COUNT, 0).apply();
        ((FriendService) this.friendService.get()).getFriendCount(new Function1() {
            public final void execute(Object obj) {
                AmplitudeService.this.sharedPreferences.edit().putInt(AmplitudeService.PREF_FRIEND_COUNT, ((Integer) obj).intValue()).apply();
            }
        });
        return friendCountBucketName;
    }

    private String getAppConnectedDimension(boolean z) {
        Boolean valueOf = this.sharedPreferences.contains(PREF_HAS_CONNECTED) ? Boolean.valueOf(this.sharedPreferences.getBoolean(PREF_HAS_CONNECTED, false)) : null;
        String appConnectedString = getAppConnectedString(Boolean.valueOf(false));
        if (!z && valueOf != null) {
            return getAppConnectedString(valueOf);
        }
        this.sharedPreferences.edit().putBoolean(PREF_HAS_CONNECTED, false).apply();
        ((AppGalleryService) this.appGalleryService.get()).checkIfUserHasConnectedAnyAppsAsync(new Function1() {
            public final void execute(Object obj) {
                AmplitudeService.this.sharedPreferences.edit().putBoolean(AmplitudeService.PREF_HAS_CONNECTED, ((Boolean) obj).booleanValue()).apply();
            }
        });
        return appConnectedString;
    }

    private String getTestingVariantGroupDimension() {
        return Strings.toString(Integer.valueOf(getTestingVariantGroup()));
    }

    private String getPayingUserDimension(boolean z) {
        if (z || this.paidUserTypeCachedValue == null) {
            this.paidUserTypeCachedValue = getLatestValidSubscriptionDimensionValue();
        }
        return Strings.isEmpty(this.paidUserTypeCachedValue) ? NON_PAYING_CUSTOMER : this.paidUserTypeCachedValue;
    }

    private String getLatestValidSubscriptionDimensionValue() {
        MfpPaidSubscription mostRecentActiveSubscription = ((SubscriptionService) this.subscriptionService.get()).getMostRecentActiveSubscription();
        if (mostRecentActiveSubscription == null) {
            return null;
        }
        return String.format("%s-%s", new Object[]{mostRecentActiveSubscription.getSubscriptionType(), mostRecentActiveSubscription.getPaymentDetails().getProductId()});
    }

    private String getAppConnectedString(Boolean bool) {
        return bool.booleanValue() ? "connected" : "not connected";
    }

    private String getFriendCountBucketName(Integer num) {
        char c = 5;
        if (num.intValue() <= 0) {
            c = 0;
        } else if (num.intValue() == 1) {
            c = 1;
        } else if (num.intValue() <= 3) {
            c = 2;
        } else if (num.intValue() <= 5) {
            c = 3;
        } else if (num.intValue() <= 10) {
            c = 4;
        } else if (num.intValue() > 20) {
            c = num.intValue() <= 40 ? (char) 6 : 7;
        }
        return FRIEND_COUNT_BUCKETS[c];
    }

    private int getTestingVariantGroup() {
        return this.deviceId.hashCode() % 100;
    }

    private boolean isWidgetVisible() {
        int[] appWidgetIds = AppWidgetManager.getInstance(this.context).getAppWidgetIds(new ComponentName(this.context, MPFAppWidgetProvider.class));
        return appWidgetIds != null && appWidgetIds.length > 0;
    }

    private void initCustomDimensions() {
        async(new Runnable() {
            public final void run() {
                AmplitudeService.lambda$initCustomDimensions$3(AmplitudeService.this);
            }
        });
    }

    public static /* synthetic */ void lambda$initCustomDimensions$3(AmplitudeService amplitudeService) {
        synchronized (initLock) {
            User user = ((Session) amplitudeService.session.get()).getUser();
            if (user != null && user.isLoggedIn()) {
                String strings = Strings.toString(user.getUserId());
                UserProfile profile = user.getProfile();
                String strings2 = Strings.toString(user.getEncryptedEmailAddress());
                long masterDatabaseId = user.getMasterDatabaseId();
                boolean notEmpty = Strings.notEmpty(PincodeHelper.current().getPincode((Session) amplitudeService.session.get(), (DbConnectionManager) amplitudeService.dbConnectionManager.get()));
                String[] userGoals = amplitudeService.getUserGoals(user.getUserV1GoalPreferences().getGoalLossPerWeek());
                Identify identify = new Identify();
                if (Strings.notEmpty(strings)) {
                    Amplitude.getInstance().setUserId(strings);
                }
                identify.set("email", strings2);
                identify.set("gender", profile != null ? Strings.toString(profile.getGenderString()).toLowerCase() : "NA");
                identify.set("age", DateTimeUtils.getAgeInYears(user.getProfile().getDateOfBirth()));
                identify.set("lifestyle", user.getProfile().getLifestyleName());
                identify.set("pin_set", notEmpty);
                identify.set("facebook_connected", amplitudeService.loginModel.getFacebookData().isValid());
                identify.set("email_verified", user.isEmailValid());
                identify.set("primary_step_source_set", amplitudeService.getUserPrimaryStepSource(user));
                identify.set("show_all_meals_in_diary_tabs", ((LocalSettingsService) amplitudeService.localSettingsService.get()).shouldShowAllMeals());
                identify.set("show_diary_food_insights", ((InsightSettings) amplitudeService.insightSettings.get()).areInsightsEnabled());
                identify.set("use_multi_add_by_default", ((LocalSettingsService) amplitudeService.localSettingsService.get()).getMultiAddToggleOnByDefault());
                identify.set(GlobalAppPreferenceMigrationUtil.DEFAULT_SEARCH_TAB, getDefaultSearchTabNameFromId(((LocalSettingsService) amplitudeService.localSettingsService.get()).getDefaultSearchTab()));
                identify.set("diary_sharing_option", ((DiarySharingSettingsManager) amplitudeService.diarySharingSettingsManager.get()).getCurrentUserSetting().toString());
                identify.set("weekly_nutrition_setting", WeeklyNutritionSettingsListFragment.getAnalyticsValueForDay(((LocalSettingsService) amplitudeService.localSettingsService.get()).getWeeklyStartDay()));
                identify.set("profile_country", user.getLocationPreferences().getCountryCode());
                identify.set("newsfeed_ad_autoplay", ((UserApplicationSettingsService) amplitudeService.userApplicationSettingsService.get()).isAutoplayNewsfeedAdsEnabled());
                identify.set("email_permissions_granted", amplitudeService.getEnabledEmails(user));
                identify.set("display_meal_macros_in_diary", user.shouldDisplayDiaryMealMacros());
                identify.set(ANLT_PARAM_USER_PREMIUM_STATUS, ((SubscriptionService) amplitudeService.subscriptionService.get()).getActiveSku());
                identify.set("play_store_services", amplitudeService.getGooglePlayServicesEnabled());
                identify.set("num_meals", amplitudeService.foodDBAdapter.countFoodsOfType(masterDatabaseId, 3));
                identify.set("num_recipes", amplitudeService.foodDBAdapter.countFoodsOfType(masterDatabaseId, 11));
                identify.set(Events.WIDGET_INSTALLED, String.valueOf(amplitudeService.isWidgetVisible()));
                identify.set("enabled_premium_feature", amplitudeService.getArrayPremiumFeatures());
                identify.set("reminders", amplitudeService.getUserReminders());
                identify.set("weight_goal", userGoals[0]);
                identify.set("weekly_weight_goal", userGoals[1]);
                if (user.isLoggedIn()) {
                    boolean z = !Strings.equalsIgnoreCase(amplitudeService.sharedPreferences.getString(PREF_LAST_USER_ID, null), strings);
                    if (z) {
                        amplitudeService.sharedPreferences.edit().putString(PREF_LAST_USER_ID, strings).apply();
                    }
                    identify.set("friend_count", amplitudeService.getFriendCountDimension(z));
                    identify.set("day_of_week", amplitudeService.getDayOfTheWeekDimension());
                    identify.set("app_connected", amplitudeService.getAppConnectedDimension(z));
                    identify.set("paying_user", amplitudeService.getPayingUserDimension(z));
                }
                Amplitude.getInstance().identify(identify);
                hasInitialized = true;
            }
        }
    }

    private String[] getUserGoals(float f) {
        String[] strArr = new String[2];
        if (f > BitmapDescriptorFactory.HUE_RED) {
            strArr[0] = Registration.LOSE;
            strArr[1] = String.format("lose_%.1f_pound_per_week", new Object[]{Float.valueOf(Math.abs(f))});
        } else if (f < BitmapDescriptorFactory.HUE_RED) {
            strArr[0] = Registration.GAIN;
            strArr[1] = String.format("gain_%.1f_pound_per_week", new Object[]{Float.valueOf(Math.abs(f))});
        } else {
            strArr[0] = Registration.MAINTAIN;
            strArr[1] = "maintain_weight";
        }
        return strArr;
    }

    private String[] getEnabledEmails(User user) {
        HashMap<String, Boolean> hashMap = user.getProfile().emailSettings;
        HashSet hashSet = new HashSet();
        for (String str : hashMap.keySet()) {
            if (((Boolean) hashMap.get(str)).booleanValue()) {
                hashSet.add(str);
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    private String getUserPrimaryStepSource(User user) {
        String str;
        List stepSources = user.getStepSources();
        if (CollectionUtils.isEmpty((Collection<?>) stepSources)) {
            return "none";
        }
        MfpStepSource mfpStepSource = null;
        Iterator it = stepSources.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            MfpStepSource mfpStepSource2 = (MfpStepSource) it.next();
            if (mfpStepSource2.isPrimary()) {
                mfpStepSource = mfpStepSource2;
                break;
            }
        }
        if (mfpStepSource == null) {
            str = "none";
        } else {
            str = mfpStepSource.getDeviceId();
        }
        return str;
    }

    private String[] getArrayPremiumFeatures() {
        ArrayList arrayList = new ArrayList();
        User user = ((Session) this.session.get()).getUser();
        MfpGoalPreferences goalPreferences = user.getGoalPreferences();
        if (goalPreferences.getHomeGoalDisplay() != Extras.DEFAULT_GOAL_DISPLAY) {
            arrayList.add("home_screen_dashboard_setting");
        }
        if (goalPreferences.getDiaryGoalDisplay() != Extras.DEFAULT_GOAL_DISPLAY) {
            arrayList.add("diary_screen_dashboard_setting");
        }
        try {
            Date time = Calendar.getInstance().getTime();
            MfpNutrientGoal nutrientGoal = ((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(time);
            if (((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).isCustomGoalsPresent(nutrientGoal)) {
                arrayList.add("custom_goals_by_day_set");
            }
            if (nutrientGoal != null) {
                MfpDailyGoal mfpDailyGoalForDayOfWeek = ((NutrientGoalService) this.nutrientGoalService.get()).getMfpDailyGoalForDayOfWeek(nutrientGoal, DateTimeUtils.getDayOfTheWeek(time));
                if (mfpDailyGoalForDayOfWeek != null && mfpDailyGoalForDayOfWeek.isAssignExerciseEnergyOn()) {
                    arrayList.add("exercise_calorie_settings_on");
                }
            }
        } catch (Exception unused) {
        }
        if (Strings.equals(goalPreferences.getMacroGoalFormat(), "grams")) {
            arrayList.add("macros_by_gram_enabled");
        }
        if (user.isMealGoalsEnabled()) {
            arrayList.add("calorie_goals_by_meal_enabled");
        }
        if (user.shouldDisplayDiaryMealMacros()) {
            arrayList.add("macronutrients_by_meal_enabled");
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private boolean getGooglePlayServicesEnabled() {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.context) == 0;
    }

    private String[] getUserReminders() {
        List<ReminderObject> reminders = ((RemindersService) this.remindersService.get()).getReminders();
        ArrayList arrayList = new ArrayList();
        for (ReminderObject reminderObject : reminders) {
            StringBuilder sb = new StringBuilder();
            switch (reminderObject.getReminderType()) {
                case 1:
                    sb.append("meal_");
                    sb.append(reminderObject.getMealId());
                    break;
                case 2:
                case 3:
                    sb.append("any_item_");
                    sb.append(Integer.toString(reminderObject.getIntervalInDays()));
                    sb.append("days");
                    break;
                case 4:
                    sb.append("weight_");
                    sb.append(reminderObject.getFrequency());
                    sb.append("_");
                    sb.append(reminderObject.getDayOfWeek());
                    break;
            }
            String sb2 = sb.toString();
            if (Strings.notEmpty(sb2)) {
                arrayList.add(sb2);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
