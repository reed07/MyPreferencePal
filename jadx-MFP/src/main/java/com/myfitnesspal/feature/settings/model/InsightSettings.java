package com.myfitnesspal.feature.settings.model;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.constants.Constants.Preference;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;

public class InsightSettings extends PersonalizedSettingsBase {
    private final SharedPreferences prefs;
    private final Lazy<Session> session;

    @Inject
    public InsightSettings(@Named("quick-tip-settings") SharedPreferences sharedPreferences, Lazy<Session> lazy) {
        this.prefs = sharedPreferences;
        this.session = lazy;
    }

    public boolean areInsightsEnabled() {
        return this.prefs.getBoolean(personalizePreferenceName(Preference.INSIGHTS_ENABLED, ((Session) this.session.get()).getUser()), true);
    }

    public void setInsightsEnabled(boolean z) {
        this.prefs.edit().putBoolean(personalizePreferenceName(Preference.INSIGHTS_ENABLED, ((Session) this.session.get()).getUser()), z).commit();
    }
}
