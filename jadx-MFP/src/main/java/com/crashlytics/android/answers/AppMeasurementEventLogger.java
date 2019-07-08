package com.crashlytics.android.answers;

import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;

public class AppMeasurementEventLogger implements EventLogger {
    private final Object logEventInstance;
    private final Method logEventMethod;

    public static EventLogger getEventLogger(Context context) {
        Class cls = getClass(context);
        if (cls == null) {
            return null;
        }
        Object instance = getInstance(context, cls);
        if (instance == null) {
            return null;
        }
        Method logEventMethod2 = getLogEventMethod(context, cls);
        if (logEventMethod2 == null) {
            return null;
        }
        return new AppMeasurementEventLogger(instance, logEventMethod2);
    }

    private static Class getClass(Context context) {
        try {
            return context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement");
        } catch (Exception unused) {
            return null;
        }
    }

    private static Object getInstance(Context context, Class cls) {
        try {
            return cls.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception unused) {
            return null;
        }
    }

    private static Method getLogEventMethod(Context context, Class cls) {
        try {
            return cls.getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class});
        } catch (Exception unused) {
            return null;
        }
    }

    public AppMeasurementEventLogger(Object obj, Method method) {
        this.logEventInstance = obj;
        this.logEventMethod = method;
    }

    public void logEvent(String str, Bundle bundle) {
        logEvent("fab", str, bundle);
    }

    public void logEvent(String str, String str2, Bundle bundle) {
        try {
            this.logEventMethod.invoke(this.logEventInstance, new Object[]{str, str2, bundle});
        } catch (Exception unused) {
        }
    }
}
