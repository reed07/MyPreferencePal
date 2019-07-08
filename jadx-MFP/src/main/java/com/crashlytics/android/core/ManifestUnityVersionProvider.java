package com.crashlytics.android.core;

import android.content.Context;
import android.os.Bundle;

class ManifestUnityVersionProvider implements UnityVersionProvider {
    private final Context context;
    private final String packageName;

    public ManifestUnityVersionProvider(Context context2, String str) {
        this.context = context2;
        this.packageName = str;
    }

    public String getUnityVersion() {
        try {
            Bundle bundle = this.context.getPackageManager().getApplicationInfo(this.packageName, 128).metaData;
            if (bundle != null) {
                return bundle.getString("io.fabric.unity.crashlytics.version");
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
