package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzark
public final class zzamx extends zzalz {
    private final UnifiedNativeAdMapper zzdom;

    public zzamx(UnifiedNativeAdMapper unifiedNativeAdMapper) {
        this.zzdom = unifiedNativeAdMapper;
    }

    public final zzacx zzse() {
        return null;
    }

    public final String getHeadline() {
        return this.zzdom.getHeadline();
    }

    public final List getImages() {
        List<Image> images = this.zzdom.getImages();
        ArrayList arrayList = new ArrayList();
        if (images != null) {
            for (Image image : images) {
                arrayList.add(new zzabr(image.getDrawable(), image.getUri(), image.getScale()));
            }
        }
        return arrayList;
    }

    public final String getBody() {
        return this.zzdom.getBody();
    }

    public final zzadb zzsb() {
        Image icon = this.zzdom.getIcon();
        if (icon != null) {
            return new zzabr(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        return null;
    }

    public final String getCallToAction() {
        return this.zzdom.getCallToAction();
    }

    public final String getAdvertiser() {
        return this.zzdom.getAdvertiser();
    }

    public final double getStarRating() {
        if (this.zzdom.getStarRating() != null) {
            return this.zzdom.getStarRating().doubleValue();
        }
        return -1.0d;
    }

    public final String getStore() {
        return this.zzdom.getStore();
    }

    public final String getPrice() {
        return this.zzdom.getPrice();
    }

    public final zzyp getVideoController() {
        if (this.zzdom.getVideoController() != null) {
            return this.zzdom.getVideoController().zzbc();
        }
        return null;
    }

    public final IObjectWrapper zzvb() {
        View adChoicesContent = this.zzdom.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzvc() {
        View zzafh = this.zzdom.zzafh();
        if (zzafh == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzafh);
    }

    public final IObjectWrapper zzsd() {
        Object zzic = this.zzdom.zzic();
        if (zzic == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzic);
    }

    public final Bundle getExtras() {
        return this.zzdom.getExtras();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzdom.getOverrideImpressionRecording();
    }

    public final boolean getOverrideClickHandling() {
        return this.zzdom.getOverrideClickHandling();
    }

    public final void recordImpression() {
        this.zzdom.recordImpression();
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        this.zzdom.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzdom.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzm(IObjectWrapper iObjectWrapper) {
        this.zzdom.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
