package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzcy {
    private static volatile boolean zzgs = false;
    private static final Class<?> zzgt = zzbc();
    static final zzcy zzgu = new zzcy(true);
    private final Map<Object, Object> zzgv;

    private static Class<?> zzbc() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzcy zzbd() {
        return zzcx.zzbb();
    }

    zzcy() {
        this.zzgv = new HashMap();
    }

    private zzcy(boolean z) {
        this.zzgv = Collections.emptyMap();
    }
}
