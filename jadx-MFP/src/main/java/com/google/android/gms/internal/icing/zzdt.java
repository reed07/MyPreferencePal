package com.google.android.gms.internal.icing;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public enum zzdt {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, Integer.valueOf(0)),
    LONG(Long.TYPE, Long.class, Long.valueOf(0)),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(BitmapDescriptorFactory.HUE_RED)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.valueOf(false)),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzce.class, zzce.class, zzce.zzfx),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> zzle;
    private final Class<?> zzlf;
    private final Object zzlg;

    private zzdt(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzle = cls;
        this.zzlf = cls2;
        this.zzlg = obj;
    }

    public final Class<?> zzcd() {
        return this.zzlf;
    }
}
