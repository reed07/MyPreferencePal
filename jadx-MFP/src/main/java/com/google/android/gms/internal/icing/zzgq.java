package com.google.android.gms.internal.icing;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public enum zzgq {
    INT(Integer.valueOf(0)),
    LONG(Long.valueOf(0)),
    FLOAT(Float.valueOf(BitmapDescriptorFactory.HUE_RED)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(Boolean.valueOf(false)),
    STRING(""),
    BYTE_STRING(zzce.zzfx),
    ENUM(null),
    MESSAGE(null);
    
    private final Object zzlg;

    private zzgq(Object obj) {
        this.zzlg = obj;
    }
}
