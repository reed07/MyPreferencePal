package com.google.android.gms.internal.ads;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public enum zzbuo {
    INT(Integer.valueOf(0)),
    LONG(Long.valueOf(0)),
    FLOAT(Float.valueOf(BitmapDescriptorFactory.HUE_RED)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(Boolean.valueOf(false)),
    STRING(""),
    BYTE_STRING(zzbpu.zzfli),
    ENUM(null),
    MESSAGE(null);
    
    private final Object zzfrh;

    private zzbuo(Object obj) {
        this.zzfrh = obj;
    }
}
