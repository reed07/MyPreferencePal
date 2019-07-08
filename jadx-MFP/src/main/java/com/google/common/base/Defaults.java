package com.google.common.base;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.annotations.GwtIncompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
public final class Defaults {
    private static final Double DOUBLE_DEFAULT = Double.valueOf(0.0d);
    private static final Float FLOAT_DEFAULT = Float.valueOf(BitmapDescriptorFactory.HUE_RED);

    private Defaults() {
    }

    @NullableDecl
    public static <T> T defaultValue(Class<T> cls) {
        Preconditions.checkNotNull(cls);
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Character.TYPE) {
            return Character.valueOf(0);
        }
        if (cls == Byte.TYPE) {
            return Byte.valueOf(0);
        }
        if (cls == Short.TYPE) {
            return Short.valueOf(0);
        }
        if (cls == Integer.TYPE) {
            return Integer.valueOf(0);
        }
        if (cls == Long.TYPE) {
            return Long.valueOf(0);
        }
        if (cls == Float.TYPE) {
            return FLOAT_DEFAULT;
        }
        if (cls == Double.TYPE) {
            return DOUBLE_DEFAULT;
        }
        return null;
    }
}
