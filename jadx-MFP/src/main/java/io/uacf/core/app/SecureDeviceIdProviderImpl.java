package io.uacf.core.app;

import android.content.Context;
import android.provider.Settings.Secure;
import io.uacf.core.util.ContextUtil;

public class SecureDeviceIdProviderImpl extends BackgroundFetchDeviceIdProvider {
    private final Context context;

    /* access modifiers changed from: protected */
    public String fetchDeviceId() {
        return Secure.getString(ContextUtil.getApplicationContextSafe(this.context).getContentResolver(), "android_id");
    }
}
