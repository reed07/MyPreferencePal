package com.brightcove.player.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {
    private static final int CALLER_POSITION = 2;

    private ReflectionUtil() {
    }

    public static boolean setField(@NonNull Object obj, @NonNull String str, @Nullable Object obj2) {
        try {
            Field field = obj.getClass().getField(str);
            field.setAccessible(true);
            field.set(obj, obj2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void assertCallerAnnotation(Class<? extends Annotation> cls, String str) {
        try {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length >= 2) {
                StackTraceElement stackTraceElement = stackTrace[2];
                String className = stackTraceElement.getClassName();
                String methodName = stackTraceElement.getMethodName();
                Method[] declaredMethods = Class.forName(className).getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                while (i < length) {
                    Method method = declaredMethods[i];
                    if (!methodName.equals(method.getName()) || method.getAnnotation(cls) == null) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        } catch (ClassNotFoundException | SecurityException unused) {
        }
        throw new UnsupportedOperationException(str);
    }
}
