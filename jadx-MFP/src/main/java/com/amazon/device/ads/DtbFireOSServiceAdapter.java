package com.amazon.device.ads;

import android.content.ContentResolver;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;

public class DtbFireOSServiceAdapter {
    private static final String LOG_TAG = "DtbFireOSServiceAdapter";

    public static DtbFireOSServiceAdapter newAdapter() {
        return new DtbFireOSServiceAdapter();
    }

    private DtbFireOSServiceAdapter() {
    }

    public AdvertisingInfo getAdvertisingIdentifierInfo() {
        boolean z;
        try {
            ContentResolver contentResolver = AdRegistration.getContext().getContentResolver();
            int i = Secure.getInt(contentResolver, Events.LIMIT_AD_TRACKING);
            String string = Secure.getString(contentResolver, Attributes.ADVERTISING_ID);
            String str = LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append(" FireID retrieved : ");
            sb.append(string);
            DtbLog.debug(str, sb.toString());
            if (i != 0) {
                DtbLog.debug(LOG_TAG, " Fire device does not allow AdTracking,");
                z = true;
            } else {
                z = false;
            }
            AdvertisingInfo advertisingInfo = new AdvertisingInfo();
            advertisingInfo.setAdvertisingIdentifier(string);
            advertisingInfo.setLimitAdTrackingEnabled(Boolean.valueOf(z));
            return advertisingInfo;
        } catch (SettingNotFoundException e) {
            String str2 = LOG_TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" Advertising setting not found on this device ");
            sb2.append(e.getLocalizedMessage());
            DtbLog.debug(str2, sb2.toString());
            return new AdvertisingInfo();
        } catch (Exception e2) {
            String str3 = LOG_TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" Attempt to retrieve fireID failed. Reason : ");
            sb3.append(e2.getLocalizedMessage());
            DtbLog.debug(str3, sb3.toString());
            return new AdvertisingInfo();
        }
    }
}
