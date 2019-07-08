package com.myfitnesspal.feature.settings.model;

import android.content.SharedPreferences;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.shared.constants.Constants.Preference;
import com.myfitnesspal.shared.constants.Constants.Settings.App;
import com.myfitnesspal.shared.constants.Constants.Settings.App.Logging;
import com.myfitnesspal.shared.constants.Constants.Settings.DeepLink;
import com.myfitnesspal.shared.constants.SharedConstants.Settings.Sync;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.install.UtmInformation;
import com.myfitnesspal.shared.util.ApiUtil;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.logging.PrivateFilePrinter;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class AppSettings {
    private static final String INSTALLATION_DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    private final Provider<ApiJsonMapper> mapperProvider;
    private final SharedPreferences prefs;

    @Inject
    public AppSettings(@Named("app-settings") SharedPreferences sharedPreferences, Provider<ApiJsonMapper> provider) {
        this.prefs = sharedPreferences;
        this.mapperProvider = provider;
    }

    public void reset() {
        this.prefs.edit().clear().apply();
    }

    public Date getInstallationDate() {
        String string = this.prefs.getString(App.INSTALLATION_DATE, null);
        if (Strings.notEmpty(string)) {
            return DateTimeUtils.parse(INSTALLATION_DATE_FORMAT, string);
        }
        return null;
    }

    public void setInstallationDate(Date date) {
        this.prefs.edit().putString(App.INSTALLATION_DATE, DateTimeUtils.format(INSTALLATION_DATE_FORMAT, date)).apply();
    }

    public int getInstalledVersionCode() {
        return this.prefs.getInt(App.INSTALLED_VERSION, 0);
    }

    public void setInstalledVersionCode(int i) {
        this.prefs.edit().putInt(App.INSTALLED_VERSION, i).apply();
    }

    public void setShouldTrackSteps(boolean z) {
        this.prefs.edit().putBoolean(App.SHOULD_TRACK_STEPS, z).apply();
    }

    public boolean isPrivateFileLoggingEnabled() {
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        return buildConfiguration.isDebug() || buildConfiguration.isQABuild() || this.prefs.getBoolean(Logging.PRIVATE_FILE_LOGGING, false);
    }

    public void setPrivateFileLoggingEnabled(boolean z) {
        this.prefs.edit().putBoolean(Logging.PRIVATE_FILE_LOGGING, z).apply();
        Ln.setEnabled(PrivateFilePrinter.class, z);
        Ln.forceDebugLogging(z);
    }

    public int getApiVersion() {
        int i = this.prefs.getInt(Sync.API_VERSION, 42);
        if (i >= 42) {
            return i;
        }
        setApiVersion(42);
        return 42;
    }

    public String getInAppNotificationsJson() {
        return this.prefs.getString(Preference.IN_APP_NOTIFICATION, "");
    }

    public void setInAppNotificationsJson(String str) {
        this.prefs.edit().putString(Preference.IN_APP_NOTIFICATION, str).apply();
    }

    public void clearInAppNotifications() {
        this.prefs.edit().remove(Preference.IN_APP_NOTIFICATION).apply();
    }

    public void setDestinationDeepLink(String str) {
        this.prefs.edit().putString(DeepLink.DESTINATION_DEEP_LINK, str).apply();
    }

    public String getDestinationDeepLink() {
        return this.prefs.getString(DeepLink.DESTINATION_DEEP_LINK, "");
    }

    public boolean hasDeepLinkDestination() {
        return this.prefs.contains(DeepLink.DESTINATION_DEEP_LINK);
    }

    public void clearDestinationDeepLink() {
        this.prefs.edit().remove(DeepLink.DESTINATION_DEEP_LINK).apply();
    }

    public Boolean getProfileDeletion() {
        return Boolean.valueOf(this.prefs.getBoolean(App.PROFILE_DELETION, false));
    }

    public void setProfileDeletion(Boolean bool) {
        this.prefs.edit().putBoolean(App.PROFILE_DELETION, bool.booleanValue()).apply();
    }

    public UtmInformation getMostRecentUtmInformation() {
        UtmInformation deepLinkUtmInformation = getDeepLinkUtmInformation();
        return (deepLinkUtmInformation == null || !deepLinkUtmInformation.isValid()) ? getInstallUtmInformation() : deepLinkUtmInformation;
    }

    public void setDeepLinkUtmInformation(UtmInformation utmInformation) {
        writeUtmInformation(App.DEEPLINK_UTM_INFORMATION, utmInformation);
    }

    public void setInstallUtmInformation(UtmInformation utmInformation) {
        writeUtmInformation(App.INSTALL_UTM_INFORMATION, utmInformation);
        if (utmInformation != null) {
            setDeepLinkUtmInformation(null);
        }
    }

    private void setApiVersion(int i) {
        if (i < 42) {
            i = 42;
        }
        this.prefs.edit().putInt(Sync.API_VERSION, i).apply();
        ApiUtil.resetApiVersion(i);
    }

    private UtmInformation getDeepLinkUtmInformation() {
        return readUtmInformation(App.DEEPLINK_UTM_INFORMATION);
    }

    private UtmInformation readUtmInformation(String str) {
        String string = this.prefs.getString(str, null);
        return Strings.notEmpty(string) ? (UtmInformation) ((ApiJsonMapper) this.mapperProvider.get()).tryMapFrom(string, UtmInformation.class) : new UtmInformation();
    }

    private UtmInformation getInstallUtmInformation() {
        return readUtmInformation(App.INSTALL_UTM_INFORMATION);
    }

    private void writeUtmInformation(String str, UtmInformation utmInformation) {
        if (utmInformation == null) {
            this.prefs.edit().remove(str).apply();
        } else {
            this.prefs.edit().putString(str, ((ApiJsonMapper) this.mapperProvider.get()).reverseMap((Object) utmInformation)).apply();
        }
    }

    public int getLastFreshStockDatabaseVersion() {
        return this.prefs.getInt(App.LAST_FRESH_STOCK_DATABASE_VERSION, 0);
    }

    public void setLastFreshStockDatabaseVersion(int i) {
        this.prefs.edit().remove(App.HAS_INITIALIZED_STOCK_DATABASE).putInt(App.LAST_FRESH_STOCK_DATABASE_VERSION, i).apply();
    }
}
