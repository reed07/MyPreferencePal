package com.myfitnesspal.shared.api.auth;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.constants.SharedConstants.Settings.App;
import com.uacf.core.util.Strings;
import java.util.UUID;

public class LegacyAuthTokenStore {
    private static final String DUMMY_VALUE = "dummy";
    private final SharedPreferences prefs;

    public LegacyAuthTokenStore(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    public String getRefreshToken() {
        return this.prefs.getString(App.OAUTH_RESOURCE_OWNER_REFRESH_TOKEN, "");
    }

    public String getAccessToken() {
        return this.prefs.getString(App.OAUTH_RESOURCE_OWNER_ACCESS_TOKEN, "");
    }

    public String getUserId() {
        return this.prefs.getString(App.OAUTH_OBFUSCATED_USER_ID, "");
    }

    public String getDeviceId() {
        String[] split = this.prefs.getString(App.OAUTH_REQUEST_TRIPLE, "").split(",");
        if (split.length < 3) {
            return null;
        }
        return split[1];
    }

    public boolean isLoggedIn() {
        return Strings.notEmpty(getAccessToken()) && Strings.notEmpty(getDeviceId()) && Strings.notEmpty(getUserId()) && Strings.notEmpty(getRefreshToken());
    }

    public void setAccessToken(String str) {
        this.prefs.edit().putString(App.OAUTH_RESOURCE_OWNER_ACCESS_TOKEN, str).apply();
    }

    public void setRefreshToken(String str) {
        this.prefs.edit().putString(App.OAUTH_RESOURCE_OWNER_REFRESH_TOKEN, str).apply();
    }

    public void setUserId(String str) {
        this.prefs.edit().putString(App.OAUTH_OBFUSCATED_USER_ID, str).apply();
    }

    public void setDeviceId(UUID uuid) {
        this.prefs.edit().putString(App.OAUTH_REQUEST_TRIPLE, String.format("%s,%s,%s", new Object[]{Strings.toString(DUMMY_VALUE), Strings.toString(uuid), Strings.toString(DUMMY_VALUE)})).apply();
    }

    public void clear() {
        this.prefs.edit().remove(App.OAUTH_RESOURCE_OWNER_ACCESS_TOKEN).remove(App.OAUTH_RESOURCE_OWNER_REFRESH_TOKEN).remove(App.OAUTH_OBFUSCATED_USER_ID).remove(App.OAUTH_REQUEST_TRIPLE).remove(App.OAUTH_REVERSE_MIGRATION_PENDING).apply();
    }
}
