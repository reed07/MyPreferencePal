package com.facebook.appevents.internal;

import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.AppEventsLogger.FlushBehavior;
import com.facebook.internal.Logger;
import java.util.Locale;

class SessionLogger {
    private static final long[] INACTIVE_SECONDS_QUANTA = {300000, 900000, 1800000, 3600000, 21600000, 43200000, 86400000, 172800000, 259200000, 604800000, 1209600000, 1814400000, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L};
    private static final String TAG = SessionLogger.class.getCanonicalName();

    SessionLogger() {
    }

    public static void logActivateApp(String str, SourceApplicationInfo sourceApplicationInfo, String str2) {
        String sourceApplicationInfo2 = sourceApplicationInfo != null ? sourceApplicationInfo.toString() : "Unclassified";
        Bundle bundle = new Bundle();
        bundle.putString(AppEventsConstants.EVENT_PARAM_SOURCE_APPLICATION, sourceApplicationInfo2);
        InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(str, str2, null);
        internalAppEventsLogger.logEvent(AppEventsConstants.EVENT_NAME_ACTIVATED_APP, bundle);
        if (AppEventsLogger.getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            internalAppEventsLogger.flush();
        }
    }

    public static void logDeactivateApp(String str, SessionInfo sessionInfo, String str2) {
        Long valueOf = Long.valueOf(sessionInfo.getDiskRestoreTime() - sessionInfo.getSessionLastEventTime().longValue());
        if (valueOf.longValue() < 0) {
            valueOf = Long.valueOf(0);
            logClockSkewEvent();
        }
        Long valueOf2 = Long.valueOf(sessionInfo.getSessionLength());
        if (valueOf2.longValue() < 0) {
            logClockSkewEvent();
            valueOf2 = Long.valueOf(0);
        }
        Bundle bundle = new Bundle();
        bundle.putInt(AppEventsConstants.EVENT_NAME_SESSION_INTERRUPTIONS, sessionInfo.getInterruptionCount());
        bundle.putString(AppEventsConstants.EVENT_NAME_TIME_BETWEEN_SESSIONS, String.format(Locale.ROOT, "session_quanta_%d", new Object[]{Integer.valueOf(getQuantaIndex(valueOf.longValue()))}));
        SourceApplicationInfo sourceApplicationInfo = sessionInfo.getSourceApplicationInfo();
        bundle.putString(AppEventsConstants.EVENT_PARAM_SOURCE_APPLICATION, sourceApplicationInfo != null ? sourceApplicationInfo.toString() : "Unclassified");
        bundle.putLong(Constants.LOG_TIME_APP_EVENT_KEY, sessionInfo.getSessionLastEventTime().longValue() / 1000);
        new InternalAppEventsLogger(str, str2, null).logEvent(AppEventsConstants.EVENT_NAME_DEACTIVATED_APP, (double) (valueOf2.longValue() / 1000), bundle);
    }

    private static void logClockSkewEvent() {
        Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Clock skew detected");
    }

    private static int getQuantaIndex(long j) {
        int i = 0;
        while (true) {
            long[] jArr = INACTIVE_SECONDS_QUANTA;
            if (i >= jArr.length || jArr[i] >= j) {
                return i;
            }
            i++;
        }
        return i;
    }
}
