package com.mopub.mobileads;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.CtaButtonDrawable;

public class VastVideoCtaButtonWidget extends ImageView {
    @NonNull
    private CtaButtonDrawable mCtaButtonDrawable;
    private boolean mHasClickthroughUrl;
    private boolean mHasCompanionAd;
    private boolean mHasSocialActions = false;
    private boolean mIsVideoComplete;
    private boolean mIsVideoSkippable;
    @NonNull
    private final LayoutParams mLandscapeLayoutParams;
    @NonNull
    private final LayoutParams mPortraitLayoutParams;

    public VastVideoCtaButtonWidget(@NonNull Context context, int i, boolean z, boolean z2) {
        super(context);
        this.mHasCompanionAd = z;
        this.mHasClickthroughUrl = z2;
        setId((int) Utils.generateUniqueId());
        int dipsToIntPixels = Dips.dipsToIntPixels(150.0f, context);
        int dipsToIntPixels2 = Dips.dipsToIntPixels(38.0f, context);
        int dipsToIntPixels3 = Dips.dipsToIntPixels(16.0f, context);
        this.mCtaButtonDrawable = new CtaButtonDrawable(context);
        setImageDrawable(this.mCtaButtonDrawable);
        this.mLandscapeLayoutParams = new LayoutParams(dipsToIntPixels, dipsToIntPixels2);
        this.mLandscapeLayoutParams.setMargins(dipsToIntPixels3, dipsToIntPixels3, dipsToIntPixels3, dipsToIntPixels3);
        this.mLandscapeLayoutParams.addRule(8, i);
        this.mLandscapeLayoutParams.addRule(7, i);
        this.mPortraitLayoutParams = new LayoutParams(dipsToIntPixels, dipsToIntPixels2);
        this.mPortraitLayoutParams.setMargins(dipsToIntPixels3, dipsToIntPixels3, dipsToIntPixels3, dipsToIntPixels3);
        this.mPortraitLayoutParams.addRule(12);
        this.mPortraitLayoutParams.addRule(11);
        updateLayoutAndVisibility();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateLayoutAndVisibility();
    }

    /* access modifiers changed from: 0000 */
    public void updateCtaText(@NonNull String str) {
        this.mCtaButtonDrawable.setCtaText(str);
    }

    /* access modifiers changed from: 0000 */
    public void setHasSocialActions(boolean z) {
        this.mHasSocialActions = z;
    }

    /* access modifiers changed from: 0000 */
    public boolean getHasSocialActions() {
        return this.mHasSocialActions;
    }

    /* access modifiers changed from: 0000 */
    public void notifyVideoSkippable() {
        this.mIsVideoSkippable = true;
        updateLayoutAndVisibility();
    }

    /* access modifiers changed from: 0000 */
    public void notifyVideoComplete() {
        this.mIsVideoSkippable = true;
        this.mIsVideoComplete = true;
        updateLayoutAndVisibility();
    }

    private void updateLayoutAndVisibility() {
        if (!this.mHasClickthroughUrl) {
            setVisibility(8);
        } else if (!this.mIsVideoSkippable) {
            setVisibility(4);
        } else if (!this.mIsVideoComplete || !this.mHasCompanionAd || this.mHasSocialActions) {
            switch (getResources().getConfiguration().orientation) {
                case 0:
                    MoPubLog.d("Screen orientation undefined: CTA button widget defaulting to portrait layout");
                    setLayoutParams(this.mPortraitLayoutParams);
                    break;
                case 1:
                    setLayoutParams(this.mPortraitLayoutParams);
                    break;
                case 2:
                    setLayoutParams(this.mLandscapeLayoutParams);
                    break;
                case 3:
                    MoPubLog.d("Screen orientation is deprecated ORIENTATION_SQUARE: CTA button widget defaulting to portrait layout");
                    setLayoutParams(this.mPortraitLayoutParams);
                    break;
                default:
                    MoPubLog.d("Unrecognized screen orientation: CTA button widget defaulting to portrait layout");
                    setLayoutParams(this.mPortraitLayoutParams);
                    break;
            }
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    @VisibleForTesting
    public String getCtaText() {
        return this.mCtaButtonDrawable.getCtaText();
    }
}
