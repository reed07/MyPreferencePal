package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.common.FirebaseInfo;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;

public class Answers extends Kit<Boolean> {
    SessionAnalyticsManager analyticsManager;
    boolean firebaseEnabled = false;

    public String getIdentifier() {
        return "com.crashlytics.sdk.android:answers";
    }

    public String getVersion() {
        return "1.4.1.19";
    }

    public void onException(LoggedException loggedException) {
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onError(loggedException.getSessionId());
        }
    }

    public void onException(FatalException fatalException) {
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onCrash(fatalException.getSessionId(), fatalException.getExceptionName());
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public boolean onPreExecute() {
        long j;
        try {
            Context context = getContext();
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String num = Integer.toString(packageInfo.versionCode);
            String str = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (VERSION.SDK_INT >= 9) {
                j = packageInfo.firstInstallTime;
            } else {
                j = new File(packageManager.getApplicationInfo(packageName, 0).sourceDir).lastModified();
            }
            this.analyticsManager = SessionAnalyticsManager.build(this, context, getIdManager(), num, str, j);
            this.analyticsManager.enable();
            this.firebaseEnabled = new FirebaseInfo().isFirebaseCrashlyticsEnabled(context);
            return true;
        } catch (Exception e) {
            Fabric.getLogger().e("Answers", "Error retrieving app properties", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground() {
        try {
            SettingsData awaitSettingsData = Settings.getInstance().awaitSettingsData();
            if (awaitSettingsData == null) {
                Fabric.getLogger().e("Answers", "Failed to retrieve settings");
                return Boolean.valueOf(false);
            } else if (awaitSettingsData.featuresData.collectAnalytics) {
                Fabric.getLogger().d("Answers", "Analytics collection enabled");
                this.analyticsManager.setAnalyticsSettingsData(awaitSettingsData.analyticsSettingsData, getOverridenSpiEndpoint());
                return Boolean.valueOf(true);
            } else {
                Fabric.getLogger().d("Answers", "Analytics collection disabled");
                this.analyticsManager.disable();
                return Boolean.valueOf(false);
            }
        } catch (Exception e) {
            Fabric.getLogger().e("Answers", "Error dealing with settings", e);
            return Boolean.valueOf(false);
        }
    }

    /* access modifiers changed from: 0000 */
    public String getOverridenSpiEndpoint() {
        return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
    }
}
