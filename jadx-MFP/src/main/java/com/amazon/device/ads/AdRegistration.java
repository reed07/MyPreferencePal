package com.amazon.device.ads;

import android.content.Context;

public class AdRegistration {
    private static final String LOGTAG = "AdRegistration";
    private static AdRegistration adRegistrationInstance = null;
    private static boolean locationEnabled = false;
    private static String mAppKey = null;
    private static Context mContext = null;
    static MRAIDPolicy mraidPolicy = MRAIDPolicy.AUTO_DETECT;
    private static boolean testMode = false;

    private AdRegistration(String str, Context context) throws IllegalArgumentException {
        if (context == null || str == null || "".equals(str)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Invalid parameters for initialization.");
            DtbLog.fatal(LOGTAG, "mDTB SDK initialize failed due to invalid registration parameters.", illegalArgumentException);
            throw illegalArgumentException;
        }
        mAppKey = str;
        mContext = context;
        DtbSharedPreferences instance = DtbSharedPreferences.getInstance(str);
        context.checkCallingOrSelfPermission("android.permission.INTERNET");
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1) {
            DtbLog.error(LOGTAG, "Network task cannot commence because the INTERNET permission is missing from the app's manifest.");
        }
        String versionInUse = instance.getVersionInUse();
        if (versionInUse == null || DtbCommonUtils.isNullOrEmpty(versionInUse)) {
            instance.setVersionInUse("7.4.3");
            instance.setGooglePlayServicesUnavailable(false);
        }
    }

    public static AdRegistration getInstance(String str, Context context) throws IllegalArgumentException {
        if (adRegistrationInstance == null) {
            adRegistrationInstance = new AdRegistration(str, context);
        }
        return adRegistrationInstance;
    }

    static Context getContext() {
        return mContext;
    }

    static void register() {
        getInstance(mAppKey, mContext);
    }

    public static void setMRAIDPolicy(MRAIDPolicy mRAIDPolicy) {
        mraidPolicy = mRAIDPolicy;
        DTBAdRequest.resetMraid();
    }

    public static MRAIDPolicy getMRAIDPolicy() {
        return mraidPolicy;
    }

    public static void setMRAIDSupportedVersions(String[] strArr) {
        DTBAdRequest.setMRAIDSupportedVersions(strArr);
    }

    public static boolean isTestMode() {
        return testMode;
    }

    public static void useGeoLocation(boolean z) {
        locationEnabled = z;
    }

    public static boolean isLocationEnabled() {
        return locationEnabled;
    }
}
