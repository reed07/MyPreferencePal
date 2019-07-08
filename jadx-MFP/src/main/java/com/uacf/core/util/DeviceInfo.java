package com.uacf.core.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceInfo {
    private final Context context;

    public DeviceInfo(Context context2) {
        this.context = context2;
    }

    public float getDensity() {
        return this.context.getResources().getDisplayMetrics().density;
    }

    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public int toPixels(int i) {
        return (int) (((float) i) * getDensity());
    }

    public int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }
}
