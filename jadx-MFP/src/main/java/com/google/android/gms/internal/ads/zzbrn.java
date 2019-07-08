package com.google.android.gms.internal.ads;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public enum zzbrn {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, Integer.valueOf(0)),
    LONG(Long.TYPE, Long.class, Long.valueOf(0)),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(BitmapDescriptorFactory.HUE_RED)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.valueOf(false)),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzbpu.class, zzbpu.class, zzbpu.zzfli),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> zzfrf;
    private final Class<?> zzfrg;
    private final Object zzfrh;

    private zzbrn(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzfrf = cls;
        this.zzfrg = cls2;
        this.zzfrh = obj;
    }

    public final Class<?> zzanl() {
        return this.zzfrg;
    }
}
