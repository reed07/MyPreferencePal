package com.google.android.exoplayer2.audio;

import android.support.annotation.Nullable;

public final class AuxEffectInfo {
    public static final int NO_AUX_EFFECT_ID = 0;
    public final int effectId;
    public final float sendLevel;

    public AuxEffectInfo(int i, float f) {
        this.effectId = i;
        this.sendLevel = f;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuxEffectInfo auxEffectInfo = (AuxEffectInfo) obj;
        if (!(this.effectId == auxEffectInfo.effectId && Float.compare(auxEffectInfo.sendLevel, this.sendLevel) == 0)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((527 + this.effectId) * 31) + Float.floatToIntBits(this.sendLevel);
    }
}
