package io.uacf.thumbprint.ui.sdk.uiconfig;

import android.graphics.Typeface;

public class UacfThumbprintTypefaceConfig {
    private Typeface blackTypeface;
    private Typeface condensedLightTypeface;
    private Typeface condensedTypeface;
    private Typeface lightTypeface;
    private Typeface mediumTypeface;
    private Typeface regularTypeface;
    private Typeface thinTypeface;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Typeface blackTypeface;
        /* access modifiers changed from: private */
        public Typeface condensedLightTypeface;
        /* access modifiers changed from: private */
        public Typeface condensedTypeface;
        /* access modifiers changed from: private */
        public Typeface lightTypeface;
        /* access modifiers changed from: private */
        public Typeface mediumTypeface;
        /* access modifiers changed from: private */
        public Typeface regularTypeface;
        /* access modifiers changed from: private */
        public Typeface thinTypeface;

        public UacfThumbprintTypefaceConfig build() {
            return new UacfThumbprintTypefaceConfig(this);
        }
    }

    public Typeface getRegularTypeface() {
        return this.regularTypeface;
    }

    public Typeface getBlackTypeface() {
        return this.blackTypeface;
    }

    private UacfThumbprintTypefaceConfig(Builder builder) {
        this.regularTypeface = builder.regularTypeface != null ? builder.regularTypeface : Typeface.create("sans-serif", 0);
        this.blackTypeface = builder.blackTypeface != null ? builder.blackTypeface : Typeface.create("sans-serif-black", 0);
        this.mediumTypeface = builder.mediumTypeface != null ? builder.mediumTypeface : Typeface.create("sans-serif-medium", 0);
        this.condensedTypeface = builder.condensedTypeface != null ? builder.condensedTypeface : Typeface.create("sans-serif-condensed", 0);
        this.condensedLightTypeface = builder.condensedLightTypeface != null ? builder.condensedLightTypeface : Typeface.create("sans-serif-condensed-light", 0);
        this.lightTypeface = builder.lightTypeface != null ? builder.lightTypeface : Typeface.create("sans-serif-light", 0);
        this.thinTypeface = builder.thinTypeface != null ? builder.thinTypeface : Typeface.create("sans-serif-thin", 0);
    }
}
