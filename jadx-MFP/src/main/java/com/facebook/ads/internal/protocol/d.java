package com.facebook.ads.internal.protocol;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.io.Serializable;

public enum d implements Serializable {
    BANNER_320_50(ModuleDescriptor.MODULE_VERSION, 50),
    INTERSTITIAL(0, 0),
    BANNER_HEIGHT_50(-1, 50),
    BANNER_HEIGHT_90(-1, 90),
    RECTANGLE_HEIGHT_250(-1, Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    
    private final int f;
    private final int g;

    private d(int i, int i2) {
        this.f = i;
        this.g = i2;
    }

    public static d a(int i, int i2) {
        d dVar = INTERSTITIAL;
        if (dVar.g == i2 && dVar.f == i) {
            return dVar;
        }
        d dVar2 = BANNER_320_50;
        if (dVar2.g == i2 && dVar2.f == i) {
            return dVar2;
        }
        d dVar3 = BANNER_HEIGHT_50;
        if (dVar3.g == i2 && dVar3.f == i) {
            return dVar3;
        }
        d dVar4 = BANNER_HEIGHT_90;
        if (dVar4.g == i2 && dVar4.f == i) {
            return dVar4;
        }
        d dVar5 = RECTANGLE_HEIGHT_250;
        if (dVar5.g == i2 && dVar5.f == i) {
            return dVar5;
        }
        return null;
    }

    public int a() {
        return this.f;
    }

    public int b() {
        return this.g;
    }
}
