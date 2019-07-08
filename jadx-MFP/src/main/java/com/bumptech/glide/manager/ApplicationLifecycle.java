package com.bumptech.glide.manager;

import android.support.annotation.NonNull;

class ApplicationLifecycle implements Lifecycle {
    public void removeListener(@NonNull LifecycleListener lifecycleListener) {
    }

    ApplicationLifecycle() {
    }

    public void addListener(@NonNull LifecycleListener lifecycleListener) {
        lifecycleListener.onStart();
    }
}
