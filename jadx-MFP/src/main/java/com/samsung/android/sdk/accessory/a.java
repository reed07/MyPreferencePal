package com.samsung.android.sdk.accessory;

import android.content.Context;

public abstract class a {
    public static a a;
    private static /* synthetic */ boolean b = (!a.class.desiredAssertionStatus());

    public static synchronized a a() {
        synchronized (a.class) {
            if (a != null) {
                a aVar = a;
                return aVar;
            }
            try {
                Class cls = Class.forName("com.samsung.accessory.a.a.b");
                if (cls != null) {
                    Class.forName("com.samsung.accessory.a.a.b", true, cls.getClassLoader());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                if (!b) {
                    throw new AssertionError(e);
                }
            }
            if (!b) {
                if (a == null) {
                    throw new AssertionError("The DEFAULT field must be initialized");
                }
            }
            a aVar2 = a;
            return aVar2;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(int i);

    /* access modifiers changed from: protected */
    public abstract void a(Context context);

    /* access modifiers changed from: protected */
    public abstract com.samsung.accessory.a.a.a b(int i);
}
