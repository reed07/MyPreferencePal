package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.codeless.internal.UnityReflection;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.appevents.internal.InAppPurchaseActivityLifecycleTracker;
import com.facebook.internal.FetchedAppSettings.DialogFeatureConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class FetchedAppSettingsManager {
    private static final String ADVERTISER_ID_KEY = "advertiser_id";
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    private static final String APP_SETTING_APP_EVENTS_CODELESS_SETUP_ENABLED = "auto_event_setup_enabled";
    private static final String APP_SETTING_APP_EVENTS_EVENT_BINDINGS = "auto_event_mapping_android";
    private static final String APP_SETTING_APP_EVENTS_FEATURE_BITMASK = "app_events_feature_bitmask";
    private static final String APP_SETTING_APP_EVENTS_SESSION_TIMEOUT = "app_events_session_timeout";
    private static final String APP_SETTING_CUSTOM_TABS_ENABLED = "gdpv4_chrome_custom_tabs_enabled";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String[] APP_SETTING_FIELDS = {APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, APP_SETTING_NUX_CONTENT, APP_SETTING_NUX_ENABLED, APP_SETTING_CUSTOM_TABS_ENABLED, APP_SETTING_DIALOG_CONFIGS, APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES, APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, APP_SETTING_APP_EVENTS_FEATURE_BITMASK, APP_SETTING_APP_EVENTS_EVENT_BINDINGS, APP_SETTING_APP_EVENTS_CODELESS_SETUP_ENABLED, APP_SETTING_SMART_LOGIN_OPTIONS, SMART_LOGIN_BOOKMARK_ICON_URL, SMART_LOGIN_MENU_ICON_URL};
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_SMART_LOGIN_OPTIONS = "seamless_login";
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    private static final int AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 8;
    private static final int CODELESS_EVENTS_ENABLED_BITMASK_FIELD = 32;
    private static final int IAP_AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 16;
    private static final String SDK_UPDATE_MESSAGE = "sdk_update_message";
    private static final String SMART_LOGIN_BOOKMARK_ICON_URL = "smart_login_bookmark_icon_url";
    private static final String SMART_LOGIN_MENU_ICON_URL = "smart_login_menu_icon_url";
    /* access modifiers changed from: private */
    public static final String TAG = "FetchedAppSettingsManager";
    private static final int TRACK_UNINSTALL_ENABLED_BITMASK_FIELD = 256;
    /* access modifiers changed from: private */
    public static final Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
    private static final ConcurrentLinkedQueue<FetchedAppSettingsCallback> fetchedAppSettingsCallbacks = new ConcurrentLinkedQueue<>();
    private static boolean isUnityInit = false;
    /* access modifiers changed from: private */
    public static final AtomicReference<FetchAppSettingState> loadingState = new AtomicReference<>(FetchAppSettingState.NOT_LOADED);
    /* access modifiers changed from: private */
    public static boolean printedSDKUpdatedMessage = false;
    @Nullable
    private static JSONArray unityEventBindings = null;

    enum FetchAppSettingState {
        NOT_LOADED,
        LOADING,
        SUCCESS,
        ERROR
    }

    public interface FetchedAppSettingsCallback {
        void onError();

        void onSuccess(FetchedAppSettings fetchedAppSettings);
    }

    public static void loadAppSettingsAsync() {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        final String applicationId = FacebookSdk.getApplicationId();
        if (Utility.isNullOrEmpty(applicationId)) {
            loadingState.set(FetchAppSettingState.ERROR);
            pollCallbacks();
        } else if (fetchedAppSettings.containsKey(applicationId)) {
            loadingState.set(FetchAppSettingState.SUCCESS);
            pollCallbacks();
        } else {
            if (!(loadingState.compareAndSet(FetchAppSettingState.NOT_LOADED, FetchAppSettingState.LOADING) || loadingState.compareAndSet(FetchAppSettingState.ERROR, FetchAppSettingState.LOADING))) {
                pollCallbacks();
                return;
            }
            final String format = String.format(APP_SETTINGS_PREFS_KEY_FORMAT, new Object[]{applicationId});
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    JSONObject jSONObject;
                    SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(FetchedAppSettingsManager.APP_SETTINGS_PREFS_STORE, 0);
                    FetchedAppSettings fetchedAppSettings = null;
                    String string = sharedPreferences.getString(format, null);
                    if (!Utility.isNullOrEmpty(string)) {
                        try {
                            jSONObject = new JSONObject(string);
                        } catch (JSONException e) {
                            Utility.logd("FacebookSDK", (Exception) e);
                            jSONObject = null;
                        }
                        if (jSONObject != null) {
                            fetchedAppSettings = FetchedAppSettingsManager.parseAppSettingsFromJSON(applicationId, jSONObject);
                        }
                    }
                    JSONObject access$100 = FetchedAppSettingsManager.getAppSettingsQueryResponse(applicationId);
                    if (access$100 != null) {
                        FetchedAppSettingsManager.parseAppSettingsFromJSON(applicationId, access$100);
                        sharedPreferences.edit().putString(format, access$100.toString()).apply();
                    }
                    if (fetchedAppSettings != null) {
                        String sdkUpdateMessage = fetchedAppSettings.getSdkUpdateMessage();
                        if (!FetchedAppSettingsManager.printedSDKUpdatedMessage && sdkUpdateMessage != null && sdkUpdateMessage.length() > 0) {
                            FetchedAppSettingsManager.printedSDKUpdatedMessage = true;
                            Log.w(FetchedAppSettingsManager.TAG, sdkUpdateMessage);
                        }
                    }
                    FetchedAppGateKeepersManager.queryAppGateKeepers(applicationId, true);
                    AutomaticAnalyticsLogger.logActivateAppEvent();
                    InAppPurchaseActivityLifecycleTracker.update();
                    FetchedAppSettingsManager.loadingState.set(FetchedAppSettingsManager.fetchedAppSettings.containsKey(applicationId) ? FetchAppSettingState.SUCCESS : FetchAppSettingState.ERROR);
                    FetchedAppSettingsManager.pollCallbacks();
                }
            });
        }
    }

    public static FetchedAppSettings getAppSettingsWithoutQuery(String str) {
        if (str != null) {
            return (FetchedAppSettings) fetchedAppSettings.get(str);
        }
        return null;
    }

    public static void getAppSettingsAsync(FetchedAppSettingsCallback fetchedAppSettingsCallback) {
        fetchedAppSettingsCallbacks.add(fetchedAppSettingsCallback);
        loadAppSettingsAsync();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void pollCallbacks() {
        /*
            java.lang.Class<com.facebook.internal.FetchedAppSettingsManager> r0 = com.facebook.internal.FetchedAppSettingsManager.class
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicReference<com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState> r1 = loadingState     // Catch:{ all -> 0x0071 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0071 }
            com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState r1 = (com.facebook.internal.FetchedAppSettingsManager.FetchAppSettingState) r1     // Catch:{ all -> 0x0071 }
            com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState r2 = com.facebook.internal.FetchedAppSettingsManager.FetchAppSettingState.NOT_LOADED     // Catch:{ all -> 0x0071 }
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x0071 }
            if (r2 != 0) goto L_0x006f
            com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState r2 = com.facebook.internal.FetchedAppSettingsManager.FetchAppSettingState.LOADING     // Catch:{ all -> 0x0071 }
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x0071 }
            if (r2 == 0) goto L_0x001c
            goto L_0x006f
        L_0x001c:
            java.lang.String r2 = com.facebook.FacebookSdk.getApplicationId()     // Catch:{ all -> 0x0071 }
            java.util.Map<java.lang.String, com.facebook.internal.FetchedAppSettings> r3 = fetchedAppSettings     // Catch:{ all -> 0x0071 }
            java.lang.Object r2 = r3.get(r2)     // Catch:{ all -> 0x0071 }
            com.facebook.internal.FetchedAppSettings r2 = (com.facebook.internal.FetchedAppSettings) r2     // Catch:{ all -> 0x0071 }
            android.os.Handler r3 = new android.os.Handler     // Catch:{ all -> 0x0071 }
            android.os.Looper r4 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0071 }
            r3.<init>(r4)     // Catch:{ all -> 0x0071 }
            com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState r4 = com.facebook.internal.FetchedAppSettingsManager.FetchAppSettingState.ERROR     // Catch:{ all -> 0x0071 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0071 }
            if (r1 == 0) goto L_0x0054
        L_0x0039:
            java.util.concurrent.ConcurrentLinkedQueue<com.facebook.internal.FetchedAppSettingsManager$FetchedAppSettingsCallback> r1 = fetchedAppSettingsCallbacks     // Catch:{ all -> 0x0071 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0071 }
            if (r1 != 0) goto L_0x0052
            java.util.concurrent.ConcurrentLinkedQueue<com.facebook.internal.FetchedAppSettingsManager$FetchedAppSettingsCallback> r1 = fetchedAppSettingsCallbacks     // Catch:{ all -> 0x0071 }
            java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x0071 }
            com.facebook.internal.FetchedAppSettingsManager$FetchedAppSettingsCallback r1 = (com.facebook.internal.FetchedAppSettingsManager.FetchedAppSettingsCallback) r1     // Catch:{ all -> 0x0071 }
            com.facebook.internal.FetchedAppSettingsManager$2 r2 = new com.facebook.internal.FetchedAppSettingsManager$2     // Catch:{ all -> 0x0071 }
            r2.<init>(r1)     // Catch:{ all -> 0x0071 }
            r3.post(r2)     // Catch:{ all -> 0x0071 }
            goto L_0x0039
        L_0x0052:
            monitor-exit(r0)
            return
        L_0x0054:
            java.util.concurrent.ConcurrentLinkedQueue<com.facebook.internal.FetchedAppSettingsManager$FetchedAppSettingsCallback> r1 = fetchedAppSettingsCallbacks     // Catch:{ all -> 0x0071 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0071 }
            if (r1 != 0) goto L_0x006d
            java.util.concurrent.ConcurrentLinkedQueue<com.facebook.internal.FetchedAppSettingsManager$FetchedAppSettingsCallback> r1 = fetchedAppSettingsCallbacks     // Catch:{ all -> 0x0071 }
            java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x0071 }
            com.facebook.internal.FetchedAppSettingsManager$FetchedAppSettingsCallback r1 = (com.facebook.internal.FetchedAppSettingsManager.FetchedAppSettingsCallback) r1     // Catch:{ all -> 0x0071 }
            com.facebook.internal.FetchedAppSettingsManager$3 r4 = new com.facebook.internal.FetchedAppSettingsManager$3     // Catch:{ all -> 0x0071 }
            r4.<init>(r1, r2)     // Catch:{ all -> 0x0071 }
            r3.post(r4)     // Catch:{ all -> 0x0071 }
            goto L_0x0054
        L_0x006d:
            monitor-exit(r0)
            return
        L_0x006f:
            monitor-exit(r0)
            return
        L_0x0071:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FetchedAppSettingsManager.pollCallbacks():void");
    }

    public static FetchedAppSettings queryAppSettings(String str, boolean z) {
        if (!z && fetchedAppSettings.containsKey(str)) {
            return (FetchedAppSettings) fetchedAppSettings.get(str);
        }
        JSONObject appSettingsQueryResponse = getAppSettingsQueryResponse(str);
        if (appSettingsQueryResponse == null) {
            return null;
        }
        FetchedAppSettings parseAppSettingsFromJSON = parseAppSettingsFromJSON(str, appSettingsQueryResponse);
        if (str.equals(FacebookSdk.getApplicationId())) {
            loadingState.set(FetchAppSettingState.SUCCESS);
            pollCallbacks();
        }
        return parseAppSettingsFromJSON;
    }

    /* access modifiers changed from: private */
    public static FetchedAppSettings parseAppSettingsFromJSON(String str, JSONObject jSONObject) {
        FacebookRequestErrorClassification facebookRequestErrorClassification;
        JSONObject jSONObject2 = jSONObject;
        JSONArray optJSONArray = jSONObject2.optJSONArray(APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES);
        if (optJSONArray == null) {
            facebookRequestErrorClassification = FacebookRequestErrorClassification.getDefaultErrorClassification();
        } else {
            facebookRequestErrorClassification = FacebookRequestErrorClassification.createFromJSON(optJSONArray);
        }
        int optInt = jSONObject2.optInt(APP_SETTING_APP_EVENTS_FEATURE_BITMASK, 0);
        boolean z = (optInt & 8) != 0;
        boolean z2 = (optInt & 16) != 0;
        boolean z3 = (optInt & 32) != 0;
        boolean z4 = (optInt & 256) != 0;
        boolean optBoolean = jSONObject2.optBoolean(APP_SETTING_APP_EVENTS_CODELESS_SETUP_ENABLED, false);
        JSONArray optJSONArray2 = jSONObject2.optJSONArray(APP_SETTING_APP_EVENTS_EVENT_BINDINGS);
        unityEventBindings = optJSONArray2;
        if (unityEventBindings != null && InternalSettings.isUnityApp()) {
            UnityReflection.sendEventMapping(optJSONArray2.toString());
        }
        FetchedAppSettings fetchedAppSettings2 = r2;
        FetchedAppSettings fetchedAppSettings3 = new FetchedAppSettings(jSONObject2.optBoolean(APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, false), jSONObject2.optString(APP_SETTING_NUX_CONTENT, ""), jSONObject2.optBoolean(APP_SETTING_NUX_ENABLED, false), jSONObject2.optBoolean(APP_SETTING_CUSTOM_TABS_ENABLED, false), jSONObject2.optInt(APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, Constants.getDefaultAppEventsSessionTimeoutInSeconds()), SmartLoginOption.parseOptions(jSONObject2.optLong(APP_SETTING_SMART_LOGIN_OPTIONS)), parseDialogConfigurations(jSONObject2.optJSONObject(APP_SETTING_DIALOG_CONFIGS)), z, facebookRequestErrorClassification, jSONObject2.optString(SMART_LOGIN_BOOKMARK_ICON_URL), jSONObject2.optString(SMART_LOGIN_MENU_ICON_URL), z2, z3, optJSONArray2, jSONObject2.optString(SDK_UPDATE_MESSAGE), z4, optBoolean);
        FetchedAppSettings fetchedAppSettings4 = fetchedAppSettings2;
        fetchedAppSettings.put(str, fetchedAppSettings4);
        return fetchedAppSettings4;
    }

    public static void setIsUnityInit(boolean z) {
        isUnityInit = z;
        JSONArray jSONArray = unityEventBindings;
        if (jSONArray != null && isUnityInit) {
            UnityReflection.sendEventMapping(jSONArray.toString());
        }
    }

    /* access modifiers changed from: private */
    public static JSONObject getAppSettingsQueryResponse(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", new ArrayList(Arrays.asList(APP_SETTING_FIELDS))));
        AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
        if (!(attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null)) {
            bundle.putString(ADVERTISER_ID_KEY, attributionIdentifiers.getAndroidAdvertiserId());
        }
        GraphRequest newGraphPathRequest = GraphRequest.newGraphPathRequest(null, str, null);
        newGraphPathRequest.setSkipClientToken(true);
        newGraphPathRequest.setParameters(bundle);
        return newGraphPathRequest.executeAndWait().getJSONObject();
    }

    private static Map<String, Map<String, DialogFeatureConfig>> parseDialogConfigurations(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    DialogFeatureConfig parseDialogConfig = DialogFeatureConfig.parseDialogConfig(optJSONArray.optJSONObject(i));
                    if (parseDialogConfig != null) {
                        String dialogName = parseDialogConfig.getDialogName();
                        Map map = (Map) hashMap.get(dialogName);
                        if (map == null) {
                            map = new HashMap();
                            hashMap.put(dialogName, map);
                        }
                        map.put(parseDialogConfig.getFeatureName(), parseDialogConfig);
                    }
                }
            }
        }
        return hashMap;
    }
}
