package com.facebook.appevents;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookUninstallTracker {
    private static final String PLATFORM = "android";
    private static final String SUCCESS = "success";
    /* access modifiers changed from: private */
    public static final String TAG = FacebookUninstallTracker.class.getCanonicalName();
    private static final String UPLOADED_TOKEN_STORE = "com.facebook.appevents.FacebookUninstallTracker.UPLOADED_TOKEN";
    /* access modifiers changed from: private */
    public static final SharedPreferences uploadedTokenSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(UPLOADED_TOKEN_STORE, 0);

    public static void updateDeviceToken(String str) {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if (appSettingsWithoutQuery != null) {
            boolean trackUninstallEnabled = appSettingsWithoutQuery.getTrackUninstallEnabled();
            String string = uploadedTokenSharedPrefs.getString("uploaded_token", null);
            boolean z = uploadedTokenSharedPrefs.getBoolean("pre_track_uninstall_enabled", false);
            if (trackUninstallEnabled && (!z || !str.equals(string))) {
                sendToServer(str);
            } else if (!trackUninstallEnabled && z) {
                uploadedTokenSharedPrefs.edit().putBoolean("pre_track_uninstall_enabled", false).apply();
            }
        }
    }

    private static void sendToServer(final String str) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                GraphRequest access$000 = FacebookUninstallTracker.buildPushDeviceTokenRequest(FacebookSdk.getApplicationId(), str);
                if (access$000 != null) {
                    GraphResponse executeAndWait = access$000.executeAndWait();
                    try {
                        JSONObject jSONObject = executeAndWait.getJSONObject();
                        if (jSONObject == null) {
                            return;
                        }
                        if (!jSONObject.has("success") || !jSONObject.getString("success").equals("true")) {
                            String access$200 = FacebookUninstallTracker.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Error sending device token to Facebook: ");
                            sb.append(executeAndWait.getError());
                            Log.e(access$200, sb.toString());
                            return;
                        }
                        FacebookUninstallTracker.uploadedTokenSharedPrefs.edit().putString("uploaded_token", str).putBoolean("pre_track_uninstall_enabled", true).apply();
                    } catch (JSONException e) {
                        Log.e(FacebookUninstallTracker.TAG, "Error decoding server response.", e);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    @Nullable
    public static GraphRequest buildPushDeviceTokenRequest(String str, String str2) {
        GraphRequest newPostRequest = GraphRequest.newPostRequest(null, String.format(Locale.US, "%s/app_push_device_token", new Object[]{str}), null, null);
        newPostRequest.setSkipClientToken(true);
        AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
        if (attributionIdentifiers == null) {
            return null;
        }
        String androidAdvertiserId = attributionIdentifiers.getAndroidAdvertiserId();
        Bundle parameters = newPostRequest.getParameters();
        if (parameters == null) {
            parameters = new Bundle();
        }
        parameters.putString("device_id", androidAdvertiserId);
        parameters.putString("device_token", str2);
        parameters.putString(Http.PLATFORM, "android");
        newPostRequest.setParameters(parameters);
        return newPostRequest;
    }
}
