package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import com.google.android.gms.internal.ads.zzark;

@zzark
public final class NativeExpressAdView extends BaseAdView {
    public NativeExpressAdView(Context context) {
        super(context, 1);
    }

    public NativeExpressAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 1);
    }

    public NativeExpressAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 1);
    }

    public final VideoController getVideoController() {
        return this.zzvw.getVideoController();
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzvw.setVideoOptions(videoOptions);
    }

    public final VideoOptions getVideoOptions() {
        return this.zzvw.getVideoOptions();
    }

    public final /* bridge */ /* synthetic */ String getMediationAdapterClassName() {
        return super.getMediationAdapterClassName();
    }

    public final /* bridge */ /* synthetic */ void setAdUnitId(String str) {
        super.setAdUnitId(str);
    }

    public final /* bridge */ /* synthetic */ void setAdSize(AdSize adSize) {
        super.setAdSize(adSize);
    }

    public final /* bridge */ /* synthetic */ void setAdListener(AdListener adListener) {
        super.setAdListener(adListener);
    }

    public final /* bridge */ /* synthetic */ boolean isLoading() {
        return super.isLoading();
    }

    public final /* bridge */ /* synthetic */ void resume() {
        super.resume();
    }

    public final /* bridge */ /* synthetic */ void pause() {
        super.pause();
    }

    @RequiresPermission
    public final /* bridge */ /* synthetic */ void loadAd(AdRequest adRequest) {
        super.loadAd(adRequest);
    }

    public final /* bridge */ /* synthetic */ String getAdUnitId() {
        return super.getAdUnitId();
    }

    public final /* bridge */ /* synthetic */ AdSize getAdSize() {
        return super.getAdSize();
    }

    public final /* bridge */ /* synthetic */ AdListener getAdListener() {
        return super.getAdListener();
    }

    public final /* bridge */ /* synthetic */ void destroy() {
        super.destroy();
    }
}
