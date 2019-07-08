package com.integralads.avid.library.inmobi.utils;

import android.text.TextUtils;
import android.util.Log;
import com.mopub.mobileads.VastExtensionXmlManager;

public class AvidLogs {
    public static void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.e(VastExtensionXmlManager.AVID, str);
        }
    }

    public static void e(String str, Exception exc) {
        if (!TextUtils.isEmpty(str) || exc != null) {
            Log.e(VastExtensionXmlManager.AVID, str, exc);
        }
    }
}
