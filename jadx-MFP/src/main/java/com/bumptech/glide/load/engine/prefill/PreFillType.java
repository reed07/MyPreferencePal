package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap.Config;
import android.support.annotation.VisibleForTesting;

public final class PreFillType {
    @VisibleForTesting
    static final Config DEFAULT_CONFIG = Config.RGB_565;
    private final Config config;
    private final int height;
    private final int weight;
    private final int width;

    public static class Builder {
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof PreFillType)) {
            return false;
        }
        PreFillType preFillType = (PreFillType) obj;
        if (this.height == preFillType.height && this.width == preFillType.width && this.weight == preFillType.weight && this.config == preFillType.config) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (((((this.width * 31) + this.height) * 31) + this.config.hashCode()) * 31) + this.weight;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PreFillSize{width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", config=");
        sb.append(this.config);
        sb.append(", weight=");
        sb.append(this.weight);
        sb.append('}');
        return sb.toString();
    }
}
