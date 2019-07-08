package com.myfitnesspal.shared.uacf;

import android.content.Context;
import android.content.SharedPreferences;
import com.myfitnesspal.feature.help.ui.activity.EmailSettings;
import com.myfitnesspal.shared.constants.Constants.ConfigParam;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.Ln;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.identity.sdk.model.UacfUser;
import dagger.Lazy;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity.Actions;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity.ExportType;
import io.uacf.thumbprint.ui.sdk.ClientEmailVerificationStatus;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkManager;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UacfEmailVerificationManager {
    private static final String APP_LAUNCH_COUNT = "AppLaunchCount";
    private static final String IDM_REGION_US = "US";
    private static final String LAST_TIME_BLOCKER_SHOW_TIMESTAMP = "LastTimeBlockerShownTimeStamp";
    private Date accountCreationCutOffDate;
    private Lazy<UacfConfigurationUtil> configurationUtil;
    private Lazy<CountryService> countryService;
    private Lazy<UacfIdentitySdk> identitySdk;
    private Lazy<NavigationHelper> navigationHelper;
    private Lazy<ClientEmailVerificationStatus> refreshClientEmailVerificationStatus;
    private Lazy<UacfRolloutUtil> rolloutUtil;
    private Lazy<Session> session;
    private SharedPreferences sharedPreferences;

    public UacfEmailVerificationManager(Lazy<Session> lazy, Lazy<UacfIdentitySdk> lazy2, Lazy<UacfRolloutUtil> lazy3, Lazy<UacfConfigurationUtil> lazy4, SharedPreferences sharedPreferences2, Lazy<ClientEmailVerificationStatus> lazy5, Lazy<CountryService> lazy6, Lazy<NavigationHelper> lazy7) {
        this.session = lazy;
        this.identitySdk = lazy2;
        this.rolloutUtil = lazy3;
        this.configurationUtil = lazy4;
        this.sharedPreferences = sharedPreferences2;
        this.refreshClientEmailVerificationStatus = lazy5;
        this.countryService = lazy6;
        this.navigationHelper = lazy7;
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.set(2018, 4, 1);
        this.accountCreationCutOffDate = instance.getTime();
    }

    public boolean isUserVerified() {
        if (!isUserInPhase0()) {
            return ((Session) this.session.get()).getUser().isEmailValid();
        }
        UacfUser cachedCurrentUser = ((UacfIdentitySdk) this.identitySdk.get()).getCachedCurrentUser();
        return ((Session) this.session.get()).getUser().isEmailValid() || (cachedCurrentUser != null && cachedCurrentUser.isVerified());
    }

    public boolean isUserInPhase0() {
        boolean z = false;
        if ((!((UacfRolloutUtil) this.rolloutUtil.get()).shouldShowUacfEmailVerificationScreen() || !isUserEnglishUS()) && (!((UacfRolloutUtil) this.rolloutUtil.get()).shouldShowUacfEmailVerificationScreenWorldWide() || isUserEnglishUS())) {
            return false;
        }
        try {
            Date parse = Format.newIso8601DateTimeFormat().parse(((Session) this.session.get()).getUser().getAccount().getCreatedAt());
            if (parse != null && parse.after(this.accountCreationCutOffDate)) {
                z = true;
            }
            return z;
        } catch (ParseException e) {
            Ln.d(e, "failed to parse createdAt date for user", new Object[0]);
            return false;
        }
    }

    private boolean isUserEnglishUS() {
        UacfUser cachedCurrentUser = ((UacfIdentitySdk) this.identitySdk.get()).getCachedCurrentUser();
        return ((CountryService) this.countryService.get()).isEnglishUSCurrentLocale() && cachedCurrentUser != null && "US".equals(cachedCurrentUser.getRegion());
    }

    public boolean shouldShowInterstitial() {
        return isUserInPhase0() && !isUserVerified();
    }

    public boolean shouldShowAppLaunchInterstitial() {
        boolean z = false;
        if (!shouldShowInterstitial()) {
            return false;
        }
        int intValue = ((UacfConfigurationUtil) this.configurationUtil.get()).getInteger(ConfigParam.EMAIL_VERIFICATION_LAUNCH_HOUR_DELAY).intValue();
        int intValue2 = ((UacfConfigurationUtil) this.configurationUtil.get()).getInteger(ConfigParam.EMAIL_VERIFICATION_LAUNCH_FREQUENCY).intValue();
        if (intValue > -1 && getInterstitialShownTimestamp() + TimeUnit.HOURS.toMillis((long) intValue) < System.currentTimeMillis() && intValue2 > 0 && getAppLaunchCount() >= intValue2) {
            z = true;
        }
        return z;
    }

    public void showInterstitial(Context context, ExportType exportType) {
        UacfThumbprintUiSdkManager.getInstance().showEmailVerificationOnFileExport(context, exportType, (ClientEmailVerificationStatus) this.refreshClientEmailVerificationStatus.get(), new Actions(context) {
            private final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            public final void editEmailClicked() {
                ((NavigationHelper) UacfEmailVerificationManager.this.navigationHelper.get()).withContext(this.f$1).withIntent(EmailSettings.newStartIntent(this.f$1)).startActivity();
            }
        });
    }

    public void showAppLaunchInterstitial(Context context) {
        setInterstitialShownTimestampToCurrentTime();
        resetAppLaunchCount();
        UacfThumbprintUiSdkManager.getInstance().showEmailVerificationOnAppLaunch(context, (ClientEmailVerificationStatus) this.refreshClientEmailVerificationStatus.get(), new Actions(context) {
            private final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            public final void editEmailClicked() {
                ((NavigationHelper) UacfEmailVerificationManager.this.navigationHelper.get()).withContext(this.f$1).withIntent(EmailSettings.newStartIntent(this.f$1)).startActivity();
            }
        });
    }

    public void incrementAppLaunchCount() {
        this.sharedPreferences.edit().putInt(APP_LAUNCH_COUNT, this.sharedPreferences.getInt(APP_LAUNCH_COUNT, 0) + 1).apply();
    }

    public void resetAppLaunchCount() {
        this.sharedPreferences.edit().putInt(APP_LAUNCH_COUNT, 0).apply();
    }

    private int getAppLaunchCount() {
        return this.sharedPreferences.getInt(APP_LAUNCH_COUNT, 0);
    }

    private void setInterstitialShownTimestampToCurrentTime() {
        this.sharedPreferences.edit().putLong(LAST_TIME_BLOCKER_SHOW_TIMESTAMP, System.currentTimeMillis()).apply();
    }

    public void resetInterstitialShownTimestamp() {
        this.sharedPreferences.edit().putLong(LAST_TIME_BLOCKER_SHOW_TIMESTAMP, 0).apply();
    }

    private long getInterstitialShownTimestamp() {
        return this.sharedPreferences.getLong(LAST_TIME_BLOCKER_SHOW_TIMESTAMP, 0);
    }
}
