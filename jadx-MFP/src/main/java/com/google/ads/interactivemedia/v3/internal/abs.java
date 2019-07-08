package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* compiled from: IMASDK */
final class abs extends abr {
    private static Class a;
    private final Object b = b();
    private final Field c = c();

    abs() {
    }

    public final void a(AccessibleObject accessibleObject) {
        if (!b(accessibleObject)) {
            try {
                accessibleObject.setAccessible(true);
            } catch (SecurityException e) {
                StringBuilder sb = new StringBuilder("Gson couldn't modify fields for ");
                sb.append(accessibleObject);
                sb.append("\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.");
                throw new xa(sb.toString(), e);
            }
        }
    }

    private final boolean b(AccessibleObject accessibleObject) {
        if (!(this.b == null || this.c == null)) {
            try {
                long longValue = ((Long) a.getMethod("objectFieldOffset", new Class[]{Field.class}).invoke(this.b, new Object[]{this.c})).longValue();
                a.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE}).invoke(this.b, new Object[]{accessibleObject, Long.valueOf(longValue), Boolean.valueOf(true)});
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static Object b() {
        try {
            Class cls = Class.forName("sun.misc.Unsafe");
            a = cls;
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Field c() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }
}
