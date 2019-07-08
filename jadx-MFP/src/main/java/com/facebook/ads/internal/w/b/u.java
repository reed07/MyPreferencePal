package com.facebook.ads.internal.w.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class u {

    public enum a {
        UNKNOWN(0),
        NONE(0),
        MOBILE_INTERNET(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        MOBILE_4G(4);
        
        public final int g;

        private a(int i) {
            this.g = i;
        }
    }

    public static a a(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return a.UNKNOWN;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return a.NONE;
        }
        if (activeNetworkInfo.getType() != 0) {
            return a.MOBILE_INTERNET;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return a.MOBILE_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return a.MOBILE_3G;
            case 13:
                return a.MOBILE_4G;
            default:
                return a.UNKNOWN;
        }
    }
}
