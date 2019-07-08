package com.samsung.android.sdk.healthdata;

import android.content.Context;

public final class HealthDataService {
    static boolean a;

    public final int getVersionCode() {
        return 102001;
    }

    public final String getVersionName() {
        return "1.2.1";
    }

    public final boolean isFeatureEnabled(int i) {
        return true;
    }

    public final void initialize(Context context) {
        if (context != null) {
            try {
                context.getApplicationContext();
                a = true;
            } catch (Exception unused) {
                throw new IllegalArgumentException("context is invalid.");
            }
        } else {
            throw new IllegalArgumentException("context is invalid.");
        }
    }
}
