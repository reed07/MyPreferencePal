package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;

public class FirebaseInfo {
    /* access modifiers changed from: protected */
    public String getApiKeyFromFirebaseAppId(Context context) {
        int resourcesIdentifier = CommonUtils.getResourcesIdentifier(context, "google_app_id", "string");
        if (resourcesIdentifier == 0) {
            return null;
        }
        Fabric.getLogger().d("Fabric", "Generating Crashlytics ApiKey from google_app_id in Strings");
        return createApiKeyFromFirebaseAppId(context.getResources().getString(resourcesIdentifier));
    }

    /* access modifiers changed from: protected */
    public String createApiKeyFromFirebaseAppId(String str) {
        return CommonUtils.sha256(str).substring(0, 40);
    }

    public boolean isFirebaseCrashlyticsEnabled(Context context) {
        boolean z = false;
        if (CommonUtils.getBooleanResourceValue(context, "com.crashlytics.useFirebaseAppId", false)) {
            return true;
        }
        boolean z2 = CommonUtils.getResourcesIdentifier(context, "google_app_id", "string") != 0;
        boolean z3 = !TextUtils.isEmpty(new ApiKey().getApiKeyFromManifest(context)) || !TextUtils.isEmpty(new ApiKey().getApiKeyFromStrings(context));
        if (z2 && !z3) {
            z = true;
        }
        return z;
    }
}
