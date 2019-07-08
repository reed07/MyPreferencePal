package com.facebook.ads.internal.view.c;

public enum f {
    UNSPECIFIED(0),
    PORTRAIT(1),
    LANDSCAPE(2);
    
    private int d;

    private f(int i) {
        this.d = i;
    }

    public static f a(int i) {
        f[] values;
        for (f fVar : values()) {
            if (fVar.d == i) {
                return fVar;
            }
        }
        return PORTRAIT;
    }

    public int a() {
        return this.d;
    }
}
