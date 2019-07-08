package com.mopub.mobileads;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils.ForceOrientation;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.resource.DrawableConstants.GradientStrip;

public class VastVideoGradientStripWidget extends ImageView {
    @NonNull
    ForceOrientation mForceOrientation;
    private boolean mHasCompanionAd;
    private boolean mIsVideoComplete;
    private int mVisibilityForCompanionAd;

    public VastVideoGradientStripWidget(@NonNull Context context, @NonNull Orientation orientation, @NonNull ForceOrientation forceOrientation, boolean z, int i, int i2, int i3) {
        super(context);
        this.mForceOrientation = forceOrientation;
        this.mVisibilityForCompanionAd = i;
        this.mHasCompanionAd = z;
        setImageDrawable(new GradientDrawable(orientation, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
        LayoutParams layoutParams = new LayoutParams(-1, Dips.dipsToIntPixels(72.0f, context));
        layoutParams.addRule(i2, i3);
        setLayoutParams(layoutParams);
        updateVisibility();
    }

    /* access modifiers changed from: 0000 */
    public void notifyVideoComplete() {
        this.mIsVideoComplete = true;
        updateVisibility();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateVisibility();
    }

    private void updateVisibility() {
        if (this.mIsVideoComplete) {
            if (this.mHasCompanionAd) {
                setVisibility(this.mVisibilityForCompanionAd);
            } else {
                setVisibility(8);
            }
            return;
        }
        if (this.mForceOrientation != ForceOrientation.FORCE_PORTRAIT) {
            if (this.mForceOrientation != ForceOrientation.FORCE_LANDSCAPE) {
                switch (getResources().getConfiguration().orientation) {
                    case 0:
                        MoPubLog.d("Screen orientation undefined: do not show gradient strip widget");
                        setVisibility(4);
                        break;
                    case 1:
                        setVisibility(4);
                        break;
                    case 2:
                        setVisibility(0);
                        break;
                    case 3:
                        MoPubLog.d("Screen orientation is deprecated ORIENTATION_SQUARE: do not show gradient strip widget");
                        setVisibility(4);
                        break;
                    default:
                        MoPubLog.d("Unrecognized screen orientation: do not show gradient strip widget");
                        setVisibility(4);
                        break;
                }
            } else {
                setVisibility(0);
            }
        } else {
            setVisibility(4);
        }
    }
}
