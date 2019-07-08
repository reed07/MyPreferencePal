package com.google.android.gms.internal.measurement;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public enum zzux {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, Integer.valueOf(0)),
    LONG(Long.TYPE, Long.class, Long.valueOf(0)),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(BitmapDescriptorFactory.HUE_RED)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.valueOf(false)),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzte.class, zzte.class, zzte.zzbts),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> zzbzq;
    private final Class<?> zzbzr;
    private final Object zzbzs;

    private zzux(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzbzq = cls;
        this.zzbzr = cls2;
        this.zzbzs = obj;
    }

    public final Class<?> zzwy() {
        return this.zzbzr;
    }
}
