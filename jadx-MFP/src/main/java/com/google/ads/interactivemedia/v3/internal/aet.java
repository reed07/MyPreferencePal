package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* compiled from: IMASDK */
public final class aet {
    public String deviceId = "";
    public String idType = "";
    public boolean isLimitedAdTracking = false;

    public aet() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:4|5|(1:7)(1:8)|9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0040, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        android.util.Log.w("IMASDK", "Failed to get advertising ID.");
        r3.deviceId = "";
        r3.idType = "";
        r3.isLimitedAdTracking = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0052, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
        r4 = r4.getContentResolver();
        r3.deviceId = android.provider.Settings.Secure.getString(r4, com.myfitnesspal.shared.constants.Constants.Analytics.Attributes.ADVERTISING_ID);
        r3.idType = "afai";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0039, code lost:
        if (android.provider.Settings.Secure.getInt(r4, com.myfitnesspal.shared.constants.Constants.Analytics.Events.LIMIT_AD_TRACKING) == 0) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003b, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003d, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        r3.isLimitedAdTracking = r4;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0023 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public aet(android.content.Context r4) {
        /*
            r3 = this;
            r3.<init>()
            java.lang.String r0 = ""
            r3.deviceId = r0
            java.lang.String r0 = ""
            r3.idType = r0
            r0 = 0
            r3.isLimitedAdTracking = r0
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r1 = r3.getInfo(r4)     // Catch:{ Exception -> 0x0023 }
            java.lang.String r2 = r1.getId()     // Catch:{ Exception -> 0x0023 }
            r3.deviceId = r2     // Catch:{ Exception -> 0x0023 }
            java.lang.String r2 = "adid"
            r3.idType = r2     // Catch:{ Exception -> 0x0023 }
            boolean r1 = r1.isLimitAdTrackingEnabled()     // Catch:{ Exception -> 0x0023 }
            r3.isLimitedAdTracking = r1     // Catch:{ Exception -> 0x0023 }
            return
        L_0x0023:
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ SettingNotFoundException -> 0x0041 }
            java.lang.String r1 = "advertising_id"
            java.lang.String r1 = android.provider.Settings.Secure.getString(r4, r1)     // Catch:{ SettingNotFoundException -> 0x0041 }
            r3.deviceId = r1     // Catch:{ SettingNotFoundException -> 0x0041 }
            java.lang.String r1 = "afai"
            r3.idType = r1     // Catch:{ SettingNotFoundException -> 0x0041 }
            java.lang.String r1 = "limit_ad_tracking"
            int r4 = android.provider.Settings.Secure.getInt(r4, r1)     // Catch:{ SettingNotFoundException -> 0x0041 }
            if (r4 != 0) goto L_0x003d
            r4 = 1
            goto L_0x003e
        L_0x003d:
            r4 = 0
        L_0x003e:
            r3.isLimitedAdTracking = r4     // Catch:{ SettingNotFoundException -> 0x0041 }
            return
        L_0x0041:
            java.lang.String r4 = "IMASDK"
            java.lang.String r1 = "Failed to get advertising ID."
            android.util.Log.w(r4, r1)
            java.lang.String r4 = ""
            r3.deviceId = r4
            java.lang.String r4 = ""
            r3.idType = r4
            r3.isLimitedAdTracking = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.aet.<init>(android.content.Context):void");
    }

    /* access modifiers changed from: protected */
    public final Info getInfo(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        return AdvertisingIdClient.getAdvertisingIdInfo(context);
    }
}
