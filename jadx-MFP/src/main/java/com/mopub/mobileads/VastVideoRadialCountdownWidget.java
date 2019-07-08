package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.RadialCountdownDrawable;

public class VastVideoRadialCountdownWidget extends ImageView {
    private int mLastProgressMilliseconds;
    @NonNull
    private RadialCountdownDrawable mRadialCountdownDrawable;

    public VastVideoRadialCountdownWidget(@NonNull Context context) {
        super(context);
        setId((int) Utils.generateUniqueId());
        int dipsToIntPixels = Dips.dipsToIntPixels(45.0f, context);
        int dipsToIntPixels2 = Dips.dipsToIntPixels(16.0f, context);
        int dipsToIntPixels3 = Dips.dipsToIntPixels(16.0f, context);
        int dipsToIntPixels4 = Dips.dipsToIntPixels(5.0f, context);
        this.mRadialCountdownDrawable = new RadialCountdownDrawable(context);
        setImageDrawable(this.mRadialCountdownDrawable);
        setPadding(dipsToIntPixels4, dipsToIntPixels4, dipsToIntPixels4, dipsToIntPixels4);
        LayoutParams layoutParams = new LayoutParams(dipsToIntPixels, dipsToIntPixels);
        layoutParams.setMargins(0, dipsToIntPixels2, dipsToIntPixels3, 0);
        layoutParams.addRule(11);
        setLayoutParams(layoutParams);
    }

    public void calibrateAndMakeVisible(int i) {
        this.mRadialCountdownDrawable.setInitialCountdown(i);
        setVisibility(0);
    }

    public void updateCountdownProgress(int i, int i2) {
        if (i2 < this.mLastProgressMilliseconds) {
            return;
        }
        if (i - i2 < 0) {
            setVisibility(8);
            return;
        }
        this.mRadialCountdownDrawable.updateCountdownProgress(i2);
        this.mLastProgressMilliseconds = i2;
    }

    @Deprecated
    @VisibleForTesting
    public RadialCountdownDrawable getImageViewDrawable() {
        return this.mRadialCountdownDrawable;
    }

    @Deprecated
    @VisibleForTesting
    public void setImageViewDrawable(RadialCountdownDrawable radialCountdownDrawable) {
        this.mRadialCountdownDrawable = radialCountdownDrawable;
    }
}
