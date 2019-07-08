package io.fabric.sdk.android.services.settings;

import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.logging.type.LogSeverity;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.UpsellOptimizations;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import org.json.JSONException;
import org.json.JSONObject;

class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    DefaultSettingsJsonTransform() {
    }

    public SettingsData buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject jSONObject) throws JSONException {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", 3600);
        SettingsData settingsData = new SettingsData(getExpiresAtFrom(currentTimeProvider, (long) optInt2, jSONObject), buildAppDataFrom(jSONObject.getJSONObject("app")), buildSessionDataFrom(jSONObject.getJSONObject("session")), buildPromptDataFrom(jSONObject.getJSONObject("prompt")), buildFeaturesSessionDataFrom(jSONObject.getJSONObject(UpsellOptimizations.VARIANT_FEATURES)), buildAnalyticsSessionDataFrom(jSONObject.getJSONObject("analytics")), buildBetaSettingsDataFrom(jSONObject.getJSONObject("beta")), optInt, optInt2);
        return settingsData;
    }

    private AppSettingsData buildAppDataFrom(JSONObject jSONObject) throws JSONException {
        AppSettingsData appSettingsData = new AppSettingsData(jSONObject.getString(Columns.IDENTIFIER), jSONObject.getString("status"), jSONObject.getString("url"), jSONObject.getString("reports_url"), jSONObject.getString("ndk_reports_url"), jSONObject.optBoolean("update_required", false), (!jSONObject.has(InMobiNetworkValues.ICON) || !jSONObject.getJSONObject(InMobiNetworkValues.ICON).has("hash")) ? null : buildIconDataFrom(jSONObject.getJSONObject(InMobiNetworkValues.ICON)));
        return appSettingsData;
    }

    private AppIconSettingsData buildIconDataFrom(JSONObject jSONObject) throws JSONException {
        return new AppIconSettingsData(jSONObject.getString("hash"), jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    private FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject jSONObject) {
        FeaturesSettingsData featuresSettingsData = new FeaturesSettingsData(jSONObject.optBoolean("prompt_enabled", false), jSONObject.optBoolean("collect_logged_exceptions", true), jSONObject.optBoolean("collect_reports", true), jSONObject.optBoolean("collect_analytics", false), jSONObject.optBoolean("firebase_crashlytics_enabled", false));
        return featuresSettingsData;
    }

    private AnalyticsSettingsData buildAnalyticsSessionDataFrom(JSONObject jSONObject) {
        AnalyticsSettingsData analyticsSettingsData = new AnalyticsSettingsData(jSONObject.optString("url", "https://e.crashlytics.com/spi/v2/events"), jSONObject.optInt("flush_interval_secs", LogSeverity.CRITICAL_VALUE), jSONObject.optInt("max_byte_size_per_file", 8000), jSONObject.optInt("max_file_count_per_send", 1), jSONObject.optInt("max_pending_send_file_count", 100), jSONObject.optBoolean("forward_to_google_analytics", false), jSONObject.optBoolean("include_purchase_events_in_forwarded_events", false), jSONObject.optBoolean("track_custom_events", true), jSONObject.optBoolean("track_predefined_events", true), jSONObject.optInt("sampling_rate", 1), jSONObject.optBoolean("flush_on_background", true));
        return analyticsSettingsData;
    }

    private SessionSettingsData buildSessionDataFrom(JSONObject jSONObject) throws JSONException {
        SessionSettingsData sessionSettingsData = new SessionSettingsData(jSONObject.optInt("log_buffer_size", 64000), jSONObject.optInt("max_chained_exception_depth", 8), jSONObject.optInt("max_custom_exception_events", 64), jSONObject.optInt("max_custom_key_value_pairs", 64), jSONObject.optInt("identifier_mask", 255), jSONObject.optBoolean("send_session_without_crash", false), jSONObject.optInt("max_complete_sessions_count", 4));
        return sessionSettingsData;
    }

    private PromptSettingsData buildPromptDataFrom(JSONObject jSONObject) throws JSONException {
        PromptSettingsData promptSettingsData = new PromptSettingsData(jSONObject.optString("title", "Send Crash Report?"), jSONObject.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject.optString("send_button_title", "Send"), jSONObject.optBoolean("show_cancel_button", true), jSONObject.optString("cancel_button_title", "Don't Send"), jSONObject.optBoolean("show_always_send_button", true), jSONObject.optString("always_send_button_title", "Always Send"));
        return promptSettingsData;
    }

    private BetaSettingsData buildBetaSettingsDataFrom(JSONObject jSONObject) throws JSONException {
        return new BetaSettingsData(jSONObject.optString("update_endpoint", SettingsJsonConstants.BETA_UPDATE_ENDPOINT_DEFAULT), jSONObject.optInt("update_suspend_duration", 3600));
    }

    private long getExpiresAtFrom(CurrentTimeProvider currentTimeProvider, long j, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("expires_at")) {
            return jSONObject.getLong("expires_at");
        }
        return currentTimeProvider.getCurrentTimeMillis() + (j * 1000);
    }
}
