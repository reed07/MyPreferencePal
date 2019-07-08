package com.myfitnesspal.feature.tizen.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.login.Welcome;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.event.DiaryUpdatedExternallyEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.model.MacroValues;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.tizen.model.DeviceActivationRequest;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.api.v2.DeviceActivationApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.extension.BuildUtil;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.notification.model.MfpNotificationChannel;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.squareup.otto.Bus;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.gear.bridge.Message;
import com.uacf.gear.bridge.Message.Builder;
import com.uacf.gear.bridge.UacfGearBridgeBase;
import com.uacf.gear.bridge.UacfGearBridgeBase.ErrorCode;
import com.uacf.identity.sdk.model.UacfUser;
import dagger.Lazy;
import io.uacf.core.api.UacfApiException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Provider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MfpGearMessageBridge extends UacfGearBridgeBase {
    public static final String ACTION_BROADCAST_SYNC_FINISHED = "action_broadcast_sync_finished";
    private static final String API_ACTIVATION_PATH = "/activate";
    private static final String APP_ID = "mfp-android";
    private static final String DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    private static final int DEFAULT_STEP_GOAL = 10000;
    private static final Map<String, String> DEVICE_MODEL_TO_VALUE_MAP = new HashMap();
    private static final String EVENT_CONNECTED = "samsung_gear_connected";
    private static final String EVENT_KEY_DEVICE_NAME = "device_name";
    private static final String EXTRA_REQUESTED_INTERNALLY = "requested_internally";
    private static final String FIT_2_DEVICE_MODEL = "SM-R360";
    private static final String FIT_2_DEVICE_VALUE = "gear_fit_2";
    private static final int FOREGROUND_NOTIFICATION_ID = 101;
    private static final Set<String> TRUSTED_KEYS = new HashSet();
    private static final String UNKNOWN_DEVICE = "unknown_device";
    @Inject
    Lazy<AnalyticsService> analytics;
    @Inject
    Provider<DeviceActivationApi> api;
    @Inject
    Bus bus;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalsService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Session session;
    @Inject
    Lazy<StepService> stepService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    interface Code {
        public static final String InvalidArguments = "invalid_arguments";
        public static final String UnknownFailure = "unknown_failure";
        public static final String UserNotSignedIn = "user_not_signed_in";
    }

    private static class EmptyResponse extends ApiResponseBase {
        private EmptyResponse() {
        }
    }

    interface Key {
        public static final String Carbs = "carbs";
        public static final String Code = "code";
        public static final String Date = "date";
        public static final String Energy = "energy";
        public static final String Error = "error";
        public static final String Exercise = "exercise";
        public static final String Fat = "fat";
        public static final String Goal = "goal";
        public static final String LoggedIn = "loggedIn";
        public static final String Macros = "macros";
        public static final String MealId = "mealId";
        public static final String Meals = "meals";
        public static final String Name = "name";
        public static final String Percent = "percent";
        public static final String Protein = "protein";
        public static final String RawValue = "rawValue";
        public static final String Remaining = "remaining";
        public static final String Steps = "steps";
        public static final String Total = "total";
        public static final String Unit = "unit";
        public static final String Value = "value";
        public static final String Water = "water";
    }

    interface Name {
        public static final String AddEnergy = "addEnergy";
        public static final String AddWater = "addWater";
        public static final String GetDiarySummary = "getDiarySummary";
        public static final String IsUserLoggedIn = "isUserLoggedIn";
        public static final String NavigateToLogin = "navigateToLogin";
        public static final String Started = "started";
        public static final String SyncFinished = "syncFinished";
    }

    /* access modifiers changed from: protected */
    public void onMessageSendFailed(Message message) {
    }

    static {
        DEVICE_MODEL_TO_VALUE_MAP.put(FIT_2_DEVICE_MODEL, FIT_2_DEVICE_VALUE);
        TRUSTED_KEYS.add("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg8IfExBCfsoKjcXGQmTb1gkjHF39qHeSqKhf59fiwQJXQCiVAN4g7Cle01uyam6wt+NxH2uD4iw3kh/f+OF80ngod5/W80e1O0dkIRZZMIQ3JdmvIEXkNWvTeRfNyypRvoDKB/S3sVBGkNZLFWCzkiGOkB97GLwH6QHqJzt6CUE5nHSL/IRSPzOCRfO/2GDhhdqYOTmKI9XvtbJ2HQb9s+15mXh3I+T58OAyioaU4eLQ/crA5gg8NOBpF8JPzQp03EWi/Wns626vQlGD1PPZiPtlMS/C9dSkh39foQZeopp9czqcAQq7Q15LXWQ05DZ6CzR6KR9B4Xw9pPd2dN2OawIDAQAB");
        TRUSTED_KEYS.add("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAimUFo9XmO1ri+PKwe5Z/5aY0Ogh/4wjETgp8NuqAnqQQ4kLT1PXr+1AhaWSWLcXPlDW0bB8YZ+YP+xru/XMI8VcWEQQBBXHEoOQ/kByaia31ErgiFqhg1DWpTRpw0uZEzsl4WqSGCMA0GBsyiZEdsTNgp9wpyIWIyZTt0DvsLsxY3fMpAFl5LNIMiSqwJQzsGMlykWJXm/W0YGO6M0jvbLJg0UfLzRe2nycn/4ceEkZ18p2ELSpVf+9XRR5QLAnIvN8NaupvSTUHD2nedyx0bqJ/ZcEWyygnMh0JknAEvKyt3VLGeOOKC3qdmQsu8pYjuqKLbXPkKrIiIPbIgQjzNwIDAQAB");
    }

    public static void notifyDiaryContentsUpdated(Context context) {
        Intent intent = new Intent(context, MfpGearMessageBridge.class);
        intent.setAction(ACTION_BROADCAST_SYNC_FINISHED);
        intent.putExtra(EXTRA_REQUESTED_INTERNALLY, true);
        context.startService(intent);
    }

    public MfpGearMessageBridge() {
        super(APP_ID);
    }

    public void onCreate() {
        super.onCreate();
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void onDestroy() {
        if (BuildUtil.isOreoOrHigher()) {
            stopForeground(true);
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (!intent.getBooleanExtra(EXTRA_REQUESTED_INTERNALLY, false) && BuildUtil.isOreoOrHigher()) {
            startForeground(101, createForegroundNotification());
        }
        if (!ACTION_BROADCAST_SYNC_FINISHED.equals(intent.getAction())) {
            return super.onStartCommand(intent, i, i2);
        }
        broadcastSyncFinished();
        return 2;
    }

    /* access modifiers changed from: protected */
    public void onError(ErrorCode errorCode, Exception exc) {
        stopSelf();
    }

    /* access modifiers changed from: protected */
    public void onMessageReceived(Message message) {
        try {
            String name = message.getName();
            if (Name.IsUserLoggedIn.equals(name)) {
                respondWithLoginState(message);
            } else if (Name.NavigateToLogin.equals(name)) {
                navigateToLogin();
            } else if (!this.session.getUser().isLoggedIn()) {
                respondWithError(message, Code.UserNotSignedIn);
            } else if (Name.GetDiarySummary.equals(name)) {
                respondWithDiarySummary(message);
            } else if (Name.AddWater.equals(name)) {
                respondWithAddWater(message);
            } else if (Name.AddEnergy.equals(name)) {
                respondWithAddEnergy(message);
            } else if ("started".equals(name)) {
                reportConnectedEvent(message);
                if (ConfigUtils.isSamsungGearDeviceActivationEnabled((ConfigService) this.configService.get())) {
                    new Thread(new Runnable(message) {
                        private final /* synthetic */ Message f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            MfpGearMessageBridge.lambda$onMessageReceived$0(MfpGearMessageBridge.this, this.f$1);
                        }
                    }).start();
                }
            }
        } catch (IllegalArgumentException unused) {
            respondWithError(message, Code.InvalidArguments);
        } catch (JSONException unused2) {
            respondWithError(message, Code.UnknownFailure);
        }
    }

    public static /* synthetic */ void lambda$onMessageReceived$0(MfpGearMessageBridge mfpGearMessageBridge, Message message) {
        Thread.currentThread().setName("samsung device activation");
        mfpGearMessageBridge.activateDevice(message);
    }

    private void reportConnectedEvent(Message message) {
        String stringFromBody = message.getStringFromBody("model");
        String str = UNKNOWN_DEVICE;
        if (DEVICE_MODEL_TO_VALUE_MAP.containsKey(stringFromBody)) {
            str = (String) DEVICE_MODEL_TO_VALUE_MAP.get(stringFromBody);
        }
        ((AnalyticsService) this.analytics.get()).reportEvent(EVENT_CONNECTED, MapUtil.createMap(EVENT_KEY_DEVICE_NAME, str));
    }

    /* access modifiers changed from: protected */
    public boolean isPeerKeyValid(String str) {
        return TRUSTED_KEYS.contains(str);
    }

    private void broadcastSyncFinished() {
        sendMessage(Builder.broadcast(APP_ID).setName(Name.SyncFinished).build());
    }

    private void navigateToLogin() {
        if (!this.session.getUser().isLoggedIn()) {
            new NavigationHelper().withContext(this).withClearTopAndNewTask().withIntent(Welcome.newStartIntent(this)).startActivity();
        }
    }

    private void respondWithLoginState(Message message) throws JSONException {
        sendMessage(Builder.respondTo(message).addBodyValue(Key.LoggedIn, Boolean.valueOf(this.session.getUser().isLoggedIn())).build());
    }

    private void respondWithDiarySummary(Message message) throws JSONException, IllegalArgumentException {
        Date extractDate = extractDate(message);
        MfpDailyGoal dailyGoalForDayOfWeekSync = ((NutrientGoalService) this.nutrientGoalsService.get()).getDailyGoalForDayOfWeekSync(extractDate);
        if (dailyGoalForDayOfWeekSync == null) {
            respondWithError(message, Code.UnknownFailure);
            return;
        }
        DiaryDay diaryDay = new DiaryDay();
        diaryDay.initFromDatabaseForDate(extractDate);
        sendMessage(Builder.respondTo(message).setBody(new JSONObject().put("macros", createMacrosJson(diaryDay, dailyGoalForDayOfWeekSync)).put(Key.Energy, createEnergyJson(diaryDay, dailyGoalForDayOfWeekSync)).put("steps", createStepsJson()).put(Key.Water, createWaterJson(diaryDay)).put("meals", createMealsJson())).build());
    }

    private void respondWithAddWater(Message message) throws JSONException, IllegalArgumentException {
        Date extractDate = extractDate(message);
        double optDouble = message.getBody().optDouble("value", -1.0d);
        double d = 0.0d;
        if (optDouble <= 0.0d) {
            respondWithError(message, Code.InvalidArguments);
            return;
        }
        Water fromInt = Water.fromInt(this.session.getUser().getWaterUnitPreference());
        DiaryDay diaryDay = new DiaryDay();
        diaryDay.initFromDatabaseForDate(extractDate);
        if (diaryDay.getLocalizedWater() != null) {
            d = diaryDay.getLocalizedWater().getValue(fromInt);
        }
        diaryDay.setLocalizedWaterEntry((float) (d + optDouble), fromInt);
        respondWithSuccess(message);
        this.bus.post(new DiaryUpdatedExternallyEvent());
    }

    private void respondWithAddEnergy(Message message) throws JSONException, IllegalArgumentException {
        FoodEntry foodEntry;
        Date extractDate = extractDate(message);
        double optDouble = message.getBody().optDouble("value", -1.0d);
        String optString = message.getBody().optString(Key.MealId, null);
        if (optDouble <= 0.0d || Strings.isEmpty(optString)) {
            respondWithError(message, Code.InvalidArguments);
            return;
        }
        User user = this.session.getUser();
        DiaryDay diaryDay = new DiaryDay();
        diaryDay.initFromDatabaseForDate(extractDate);
        float value = (float) LocalizedEnergy.from(((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit(), optDouble).getValue(Energy.CALORIES);
        MealNames mealNames = this.session.getUser().getMealNames();
        int i = 0;
        String str = (String) mealNames.getNames().get(0);
        while (true) {
            if (i >= mealNames.size()) {
                break;
            } else if (String.valueOf(i).equals(optString)) {
                str = mealNames.nameForIndex(i);
                break;
            } else {
                i++;
            }
        }
        if (Strings.notEmpty(str)) {
            if (((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.QuickAddMacros)) {
                foodEntry = FoodEntry.quickAddedPremiumFoodEntry(user, new QuickAddFood(value), str, (DbConnectionManager) this.dbConnectionManager.get());
            } else {
                foodEntry = FoodEntry.quickAddedCaloriesFoodEntry(value, str, this.session, (DbConnectionManager) this.dbConnectionManager.get());
            }
            if (foodEntry != null) {
                foodEntry.setDate(extractDate);
                diaryDay.addFoodEntry(foodEntry);
                respondWithSuccess(message);
                this.bus.post(new DiaryUpdatedExternallyEvent());
                return;
            }
        }
        respondWithError(message, Code.UnknownFailure);
    }

    private static Date extractDate(Message message) throws IllegalArgumentException {
        Date date;
        try {
            date = DateTimeUtils.parse(DATE_FORMAT, message.getStringFromBody("date"));
        } catch (Exception unused) {
            date = null;
        }
        if (date != null) {
            return date;
        }
        throw new IllegalArgumentException();
    }

    private JSONObject createMacroJson(float f, float f2, int i) throws JSONException {
        return new JSONObject().put("total", Math.round(f)).put(Key.Remaining, Math.round(f2)).put("percent", i);
    }

    private JSONObject createStepsJson() throws JSONException {
        MfpStepSource primaryStepSource = ((StepService) this.stepService.get()).getPrimaryStepSource();
        return new JSONObject().put(Key.Goal, (primaryStepSource == null || primaryStepSource.getStepGoal() <= 0) ? 10000 : primaryStepSource.getStepGoal());
    }

    private JSONObject createWaterJson(DiaryDay diaryDay) throws JSONException {
        Water fromInt = Water.fromInt(this.session.getUser().getWaterUnitPreference());
        double value = diaryDay.getLocalizedWater() != null ? diaryDay.getLocalizedWater().getValue(fromInt) : 0.0d;
        return new JSONObject().put("unit", fromInt.toString()).put(Key.RawValue, value).put("value", NumberUtils.localeStringFromDouble(value, LocalizedFluid.getDisplayDecimalPrecision(fromInt)));
    }

    private JSONArray createMealsJson() throws JSONException {
        MealNames mealNames = this.session.getUser().getMealNames();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < mealNames.size(); i++) {
            jSONArray.put(new JSONObject().put(Key.MealId, String.valueOf(i)).put("name", ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(mealNames.nameForIndex(i), (UserEnergyService) this.userEnergyService.get())));
        }
        return jSONArray;
    }

    private JSONObject createEnergyJson(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) throws JSONException {
        boolean z = mfpDailyGoal == null || mfpDailyGoal.isAssignExerciseEnergyOn();
        double value = LocalizedEnergy.fromMeasuredValue(mfpDailyGoal.getEnergy()).getValue(Energy.fromString(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit()));
        double caloriesConsumed = (double) diaryDay.caloriesConsumed(true);
        double caloriesBurnedByExercise = (double) diaryDay.caloriesBurnedByExercise(true);
        return new JSONObject().put("unit", ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit()).put("total", Math.round(value)).put("exercise", Math.round(caloriesBurnedByExercise)).put(Key.Remaining, Math.round((value - caloriesConsumed) + (z ? caloriesBurnedByExercise : 0.0d)));
    }

    private JSONObject createMacrosJson(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) throws JSONException {
        float round = (float) Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 9));
        float round2 = (float) Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 1));
        float round3 = (float) Math.round(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 12));
        float consumed = consumed(diaryDay, 9);
        float consumed2 = consumed(diaryDay, 1);
        float round4 = (float) Math.round(round - consumed);
        float round5 = (float) Math.round(round2 - consumed2);
        float round6 = (float) Math.round(round3 - consumed(diaryDay, 12));
        float[] percentagesForMacroValues = NutritionUtils.getPercentagesForMacroValues(MacroValues.fromDiaryDay(diaryDay));
        int i = 0;
        int round7 = Math.round(percentagesForMacroValues[0]);
        int round8 = Math.round(percentagesForMacroValues[1]);
        if (!(round7 == 0 && round8 == 0)) {
            i = Math.round(percentagesForMacroValues[2]);
        }
        return new JSONObject().put("carbs", createMacroJson(round, round4, round7)).put("fat", createMacroJson(round2, round5, round8)).put("protein", createMacroJson(round3, round6, i));
    }

    private static float consumed(DiaryDay diaryDay, int i) {
        return (float) Math.round(diaryDay.amountOfNutrientConsumed(i));
    }

    private void respondWithError(Message message, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error", new JSONObject().put("code", str));
            sendMessage(Builder.respondTo(message).setBody(jSONObject).build());
        } catch (JSONException unused) {
            throw new RuntimeException("should never happen");
        }
    }

    private void respondWithSuccess(Message message) {
        sendMessage(Builder.respondTo(message).build());
    }

    private void activateDevice(Message message) {
        try {
            String email = this.session.getUser().getEmail();
            if (Strings.notEmpty(email)) {
                List findUserByEmailAddress = SSO.getSdk().findUserByEmailAddress(email);
                if (CollectionUtils.notEmpty((Collection<?>) findUserByEmailAddress)) {
                    String userId = ((UacfUser) findUserByEmailAddress.get(0)).getUserId();
                    if (Strings.notEmpty(userId)) {
                        ((MfpV2Api) ((MfpV2Api) ((DeviceActivationApi) this.api.get()).withOutputType(EmptyResponse.class)).withJsonBody(new ApiRequest(new DeviceActivationRequest(userId, message)))).post(API_ACTIVATION_PATH, new Object[0]);
                    }
                }
            }
        } catch (ApiException | UacfApiException e) {
            Ln.e(e);
        } catch (IllegalArgumentException e2) {
            Ln.e(e2, "invalid 'start' message contents. device registration failed", new Object[0]);
        }
    }

    @RequiresApi
    private Notification createForegroundNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            NotificationChannel notificationChannel = new NotificationChannel(MfpNotificationChannel.SAMSUNG_GEAR.getChannelId(), getString(MfpNotificationChannel.SAMSUNG_GEAR.getChannelNameResId()), 2);
            notificationChannel.setDescription(getString(MfpNotificationChannel.SAMSUNG_GEAR.getDescriptionResId()));
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return new Notification.Builder(this).setOngoing(true).setContentTitle(getString(MfpNotificationChannel.SAMSUNG_GEAR.getChannelNameResId())).setContentText(getString(R.string.notification_message_samsung_gear_sync)).setChannelId(MfpNotificationChannel.SAMSUNG_GEAR.getChannelId()).setSmallIcon(R.drawable.ic_reminder).build();
    }
}
