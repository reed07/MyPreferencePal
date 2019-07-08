package com.mopub.common;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.privacy.PersonalInfoManager;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class MoPubLifecycleManager implements LifecycleListener {
    private static MoPubLifecycleManager sInstance;
    @NonNull
    private final Set<LifecycleListener> mLifecycleListeners = new HashSet();
    @NonNull
    private final WeakReference<Activity> mMainActivity;

    private MoPubLifecycleManager(Activity activity) {
        this.mMainActivity = new WeakReference<>(activity);
    }

    @NonNull
    public static synchronized MoPubLifecycleManager getInstance(Activity activity) {
        MoPubLifecycleManager moPubLifecycleManager;
        synchronized (MoPubLifecycleManager.class) {
            if (sInstance == null) {
                sInstance = new MoPubLifecycleManager(activity);
            }
            moPubLifecycleManager = sInstance;
        }
        return moPubLifecycleManager;
    }

    public void addLifecycleListener(@Nullable LifecycleListener lifecycleListener) {
        if (lifecycleListener != null && this.mLifecycleListeners.add(lifecycleListener)) {
            Activity activity = (Activity) this.mMainActivity.get();
            if (activity != null) {
                lifecycleListener.onCreate(activity);
                lifecycleListener.onStart(activity);
            }
        }
    }

    public void onCreate(@NonNull Activity activity) {
        for (LifecycleListener onCreate : this.mLifecycleListeners) {
            onCreate.onCreate(activity);
        }
    }

    public void onStart(@NonNull Activity activity) {
        for (LifecycleListener onStart : this.mLifecycleListeners) {
            onStart.onStart(activity);
        }
    }

    public void onPause(@NonNull Activity activity) {
        for (LifecycleListener onPause : this.mLifecycleListeners) {
            onPause.onPause(activity);
        }
    }

    public void onResume(@NonNull Activity activity) {
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (personalInformationManager != null) {
            personalInformationManager.requestSync(false);
        }
        for (LifecycleListener onResume : this.mLifecycleListeners) {
            onResume.onResume(activity);
        }
    }

    public void onRestart(@NonNull Activity activity) {
        for (LifecycleListener onRestart : this.mLifecycleListeners) {
            onRestart.onRestart(activity);
        }
    }

    public void onStop(@NonNull Activity activity) {
        for (LifecycleListener onStop : this.mLifecycleListeners) {
            onStop.onStop(activity);
        }
    }

    public void onDestroy(@NonNull Activity activity) {
        for (LifecycleListener onDestroy : this.mLifecycleListeners) {
            onDestroy.onDestroy(activity);
        }
    }

    public void onBackPressed(@NonNull Activity activity) {
        for (LifecycleListener onBackPressed : this.mLifecycleListeners) {
            onBackPressed.onBackPressed(activity);
        }
    }
}
