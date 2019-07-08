package com.myfitnesspal.feature.settings.model;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.constants.Constants.Preference;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;

public class XPromoSettings extends PersonalizedSettingsBase {
    private final SharedPreferences prefs;
    private final Lazy<Session> session;

    @Inject
    public XPromoSettings(@Named("xpromo-settings") SharedPreferences sharedPreferences, Lazy<Session> lazy) {
        this.prefs = sharedPreferences;
        this.session = lazy;
    }

    public boolean hasDontShowBeenSet() {
        return this.prefs.getBoolean(personalizePreferenceName(Preference.DONT_SHOW_XPROMO_INTERSTITIAL, ((Session) this.session.get()).getUser()), false);
    }

    public void setDontShow(boolean z) {
        this.prefs.edit().putBoolean(personalizePreferenceName(Preference.DONT_SHOW_XPROMO_INTERSTITIAL, ((Session) this.session.get()).getUser()), z).apply();
    }
}
