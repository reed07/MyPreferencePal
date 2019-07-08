package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzark
public final class zzamf extends zzalt {
    private final NativeAppInstallAdMapper zzdod;

    public zzamf(NativeAppInstallAdMapper nativeAppInstallAdMapper) {
        this.zzdod = nativeAppInstallAdMapper;
    }

    public final IObjectWrapper zzsd() {
        return null;
    }

    public final zzacx zzse() {
        return null;
    }

    public final String getHeadline() {
        return this.zzdod.getHeadline();
    }

    public final List getImages() {
        List<Image> images = this.zzdod.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Image image : images) {
            arrayList.add(new zzabr(image.getDrawable(), image.getUri(), image.getScale()));
        }
        return arrayList;
    }

    public final String getBody() {
        return this.zzdod.getBody();
    }

    public final zzadb zzsb() {
        Image icon = this.zzdod.getIcon();
        if (icon != null) {
            return new zzabr(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        return null;
    }

    public final String getCallToAction() {
        return this.zzdod.getCallToAction();
    }

    public final double getStarRating() {
        return this.zzdod.getStarRating();
    }

    public final String getStore() {
        return this.zzdod.getStore();
    }

    public final String getPrice() {
        return this.zzdod.getPrice();
    }

    public final void recordImpression() {
        this.zzdod.recordImpression();
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        this.zzdod.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzdod.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzdod.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzm(IObjectWrapper iObjectWrapper) {
        this.zzdod.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzdod.getOverrideImpressionRecording();
    }

    public final boolean getOverrideClickHandling() {
        return this.zzdod.getOverrideClickHandling();
    }

    public final Bundle getExtras() {
        return this.zzdod.getExtras();
    }

    public final zzyp getVideoController() {
        if (this.zzdod.getVideoController() != null) {
            return this.zzdod.getVideoController().zzbc();
        }
        return null;
    }

    public final IObjectWrapper zzvb() {
        View adChoicesContent = this.zzdod.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzvc() {
        View zzafh = this.zzdod.zzafh();
        if (zzafh == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzafh);
    }
}
