package com.inmobi.commons.core.utilities;

import android.util.Log;
import com.google.android.gms.fitness.data.WorkoutExercises;

public final class Logger {
    private static InternalLogLevel a = (WorkoutExercises.ROW.contains("staging") ? InternalLogLevel.INTERNAL : InternalLogLevel.NONE);

    public enum InternalLogLevel {
        NONE,
        ERROR,
        DEBUG,
        INTERNAL
    }

    public static void a(InternalLogLevel internalLogLevel, String str, String str2) {
        if (internalLogLevel.ordinal() <= a.ordinal()) {
            switch (internalLogLevel) {
                case ERROR:
                    Log.e("[InMobi]", str2);
                    return;
                case DEBUG:
                    Log.d("[InMobi]", str2);
                    return;
                case INTERNAL:
                    if (str2.length() <= 4000) {
                        Log.d(str, str2);
                        break;
                    } else {
                        while (str2.length() > 4000) {
                            Log.d(str, str2.substring(0, 4000));
                            str2 = str2.substring(4000);
                        }
                        Log.d(str, str2);
                        return;
                    }
            }
        }
    }

    public static void a(InternalLogLevel internalLogLevel, String str, String str2, Throwable th) {
        if (internalLogLevel.ordinal() <= a.ordinal()) {
            switch (internalLogLevel) {
                case ERROR:
                    Log.e("[InMobi]", str2, th);
                    return;
                case DEBUG:
                    Log.d("[InMobi]", str2, th);
                    return;
                case INTERNAL:
                    Log.d(str, str2, th);
                    break;
            }
        }
    }

    public static void a(InternalLogLevel internalLogLevel) {
        a = internalLogLevel;
    }
}
