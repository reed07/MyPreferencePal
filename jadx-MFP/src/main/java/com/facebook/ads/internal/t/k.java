package com.facebook.ads.internal.t;

import com.google.logging.type.LogSeverity;

public enum k {
    HEIGHT_100(-1, 100, 1),
    HEIGHT_120(-1, 120, 2),
    HEIGHT_300(-1, LogSeverity.NOTICE_VALUE, 3),
    HEIGHT_400(-1, LogSeverity.WARNING_VALUE, 4),
    HEIGHT_50(-1, 50, 5);
    
    private final int f;
    private final int g;
    private final int h;

    private k(int i2, int i3, int i4) {
        this.f = i2;
        this.g = i3;
        this.h = i4;
    }

    public int a() {
        return this.f;
    }

    public int b() {
        return this.g;
    }

    public int c() {
        return this.h;
    }
}
