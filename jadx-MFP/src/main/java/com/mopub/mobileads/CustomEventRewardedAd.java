package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubLifecycleManager;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public abstract class CustomEventRewardedAd {
    /* access modifiers changed from: protected */
    public abstract boolean checkAndInitializeSdk(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception;

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String getAdNetworkId();

    /* access modifiers changed from: protected */
    @Nullable
    @VisibleForTesting
    public abstract LifecycleListener getLifecycleListener();

    /* access modifiers changed from: protected */
    public abstract boolean isReady();

    /* access modifiers changed from: protected */
    public abstract void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void onInvalidate();

    /* access modifiers changed from: protected */
    public abstract void show();

    /* access modifiers changed from: 0000 */
    public final void loadCustomEvent(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        try {
            if (checkAndInitializeSdk(activity, map, map2)) {
                MoPubLifecycleManager.getInstance(activity).addLifecycleListener(getLifecycleListener());
            }
            loadWithSdkInitialized(activity, map, map2);
        } catch (Exception e) {
            MoPubLog.e(e.getMessage());
        }
    }
}
