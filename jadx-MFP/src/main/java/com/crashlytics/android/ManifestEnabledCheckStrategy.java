package com.crashlytics.android;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

class ManifestEnabledCheckStrategy implements EnabledCheckStrategy {
    ManifestEnabledCheckStrategy() {
    }

    public boolean isCrashlyticsEnabled(Context context) {
        boolean z = true;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null && !bundle.getBoolean("firebase_crashlytics_collection_enabled", true)) {
                z = false;
            }
            return z;
        } catch (NameNotFoundException unused) {
            return true;
        }
    }
}
