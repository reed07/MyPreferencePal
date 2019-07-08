package com.myfitnesspal.shared.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.myfitnesspal.app.MyFitnessPalApp;

public class ConnectivityUtil {
    static ConnectivityManager manager = ((ConnectivityManager) MyFitnessPalApp.getInstance().getSystemService("connectivity"));

    public static boolean isOnline() {
        ConnectivityManager connectivityManager = manager;
        boolean z = true;
        if (connectivityManager == null) {
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            z = false;
        }
        return z;
    }

    public static Boolean isOffline() {
        return Boolean.valueOf(!isOnline());
    }
}
