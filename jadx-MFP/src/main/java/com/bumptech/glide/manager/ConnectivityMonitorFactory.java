package com.bumptech.glide.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener;

public interface ConnectivityMonitorFactory {
    @NonNull
    ConnectivityMonitor build(@NonNull Context context, @NonNull ConnectivityListener connectivityListener);
}
