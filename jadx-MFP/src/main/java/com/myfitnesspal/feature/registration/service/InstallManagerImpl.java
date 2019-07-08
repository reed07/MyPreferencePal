package com.myfitnesspal.feature.registration.service;

import android.content.Context;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.UtmInformation;
import com.uacf.core.util.Ln;
import com.uacf.core.util.VersionUtils;
import java.util.Calendar;
import java.util.Date;

public class InstallManagerImpl implements InstallManager {
    private final AnalyticsService analyticsService;
    private final AppSettings appSettings;
    private final Context context;

    public InstallManagerImpl(Context context2, AnalyticsService analyticsService2, AppSettings appSettings2) {
        this.context = context2;
        this.analyticsService = analyticsService2;
        this.appSettings = appSettings2;
    }

    public void trackInstallOrUpdate() {
        Date installationDate = this.appSettings.getInstallationDate();
        int installedVersionCode = this.appSettings.getInstalledVersionCode();
        int appVersionCode = VersionUtils.getAppVersionCode(this.context);
        Ln.d("ANALYTICS: stored date = %s, stored version code = %s, this version code = %s", installationDate, Integer.valueOf(installedVersionCode), Integer.valueOf(appVersionCode));
        if (installationDate == null) {
            Ln.d("ANALYTICS: was a fresh install", new Object[0]);
            this.analyticsService.reportInstall();
        } else if (installedVersionCode != appVersionCode) {
            Ln.d("ANALYTICS: was an update", new Object[0]);
            this.analyticsService.reportUpgrade();
        } else {
            Ln.d("ANALYTICS: no action on install", new Object[0]);
            return;
        }
        Date time = Calendar.getInstance().getTime();
        Ln.d("ANALYTICS: set installation date to %s, code to %s", time, Integer.valueOf(appVersionCode));
        this.appSettings.setInstallationDate(time);
        this.appSettings.setInstalledVersionCode(appVersionCode);
    }

    public void trackInstallOrUpdate(String str, String str2, String str3, String str4, String str5) {
        AppSettings appSettings2 = this.appSettings;
        UtmInformation utmInformation = new UtmInformation(str, str2, str3, str4, str5);
        appSettings2.setInstallUtmInformation(utmInformation);
        trackInstallOrUpdate();
    }
}
