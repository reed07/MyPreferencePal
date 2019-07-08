package com.myfitnesspal.shared.api;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.constants.Environment.Config;
import com.myfitnesspal.shared.constants.Environment.DeviceActivation;
import com.myfitnesspal.shared.constants.SharedConstants.APIToken;
import com.myfitnesspal.shared.constants.SharedConstants.Preference;
import com.myfitnesspal.shared.constants.SharedConstants.Settings.App;
import com.myfitnesspal.shared.constants.SharedConstants.Settings.Sync;
import com.uacf.core.util.Strings;

public class MfpApiSettingsImpl implements MfpApiSettings {
    private final SharedPreferences prefs;

    public MfpApiSettingsImpl(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    public void setV1Endpoint(String str) {
        this.prefs.edit().putString(Sync.BASE_URL, str).remove(App.MFP_SERVER_CERTIFICATE_IS_TRUSTED_BY_THIS_DEVICE).apply();
    }

    public String getV1Endpoint() {
        return this.prefs.getString(Sync.BASE_URL, "https://sync.myfitnesspal.com");
    }

    public void setWebsiteEndpoint(String str) {
        this.prefs.edit().putString(App.BASE_WEBSITE_URL, str).apply();
    }

    public String getWebsiteEndpoint() {
        return this.prefs.getString(App.BASE_WEBSITE_URL, "https://www.myfitnesspal.com");
    }

    public void setBlogEndpoint(String str) {
        this.prefs.edit().putString(App.BASE_BLOG_URL, str).apply();
    }

    public String getBlogEndpoint() {
        return this.prefs.getString(App.BASE_BLOG_URL, "http://blog.myfitnesspal.com");
    }

    public void setV2Endpoint(String str) {
        this.prefs.edit().putString(App.BASE_APIV2_URL, str).apply();
    }

    public String getV2Endpoint() {
        return this.prefs.getString(App.BASE_APIV2_URL, "https://api.myfitnesspal.com");
    }

    public String getConfigEndpoint() {
        return this.prefs.getString(App.BASE_CONFIG_URL, Config.PROD);
    }

    public void setConfigEndpoint(String str) {
        this.prefs.edit().putString(App.BASE_CONFIG_URL, str).apply();
    }

    public String getIdmEndpoint() {
        return this.prefs.getString(App.SSO_AUTH_API_ENVIRONMENT, "prod");
    }

    public void setIdmEndpoint(String str) {
        this.prefs.edit().putString(App.SSO_AUTH_API_ENVIRONMENT, str).apply();
    }

    public String getNISEndpoint() {
        return this.prefs.getString(App.NIS_API_ENVIRONMENT, "prod");
    }

    public void setNISEndpoint(String str) {
        this.prefs.edit().putString(App.NIS_API_ENVIRONMENT, str).apply();
    }

    public String getDeviceActivationEnvironment() {
        return this.prefs.getString(App.DEVICE_ACTIVATION_API_ENVIRONMENT, DeviceActivation.PROD);
    }

    public void setDeviceActivationEnvironment(String str) {
        this.prefs.edit().putString(App.DEVICE_ACTIVATION_API_ENVIRONMENT, str).apply();
    }

    public String getSyncV2Endpoint() {
        return this.prefs.getString(App.SYNCV2_API_ENVIRONMENT, "prod");
    }

    public void setSyncV2Endpoint(String str) {
        this.prefs.edit().putString(App.SYNCV2_API_ENVIRONMENT, str).apply();
    }

    public String getConsentsEndpoint() {
        return this.prefs.getString(App.CONSENTS_API_ENVIRONMENT, "prod");
    }

    public void setConsentsEndpoint(String str) {
        this.prefs.edit().putString(App.CONSENTS_API_ENVIRONMENT, str).apply();
    }

    public void setCurrentMock(String str) {
        this.prefs.edit().putString(Preference.MOCK_API_PREF, str).apply();
    }

    public String getCurrentMock() {
        return this.prefs.getString(Preference.MOCK_API_PREF, null);
    }

    public String getAPIToken() {
        return Strings.equals(getV2Endpoint(), "https://integ.myfitnesspal.com") ? APIToken.GOOGLE_INTEG_TOKEN : APIToken.GOOGLE_PROD_TOKEN;
    }
}
