package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzark
public final class zzamg extends zzalw {
    private final NativeContentAdMapper zzdoe;

    public zzamg(NativeContentAdMapper nativeContentAdMapper) {
        this.zzdoe = nativeContentAdMapper;
    }

    public final IObjectWrapper zzsd() {
        return null;
    }

    public final zzacx zzse() {
        return null;
    }

    public final String getHeadline() {
        return this.zzdoe.getHeadline();
    }

    public final List getImages() {
        List<Image> images = this.zzdoe.getImages();
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
        return this.zzdoe.getBody();
    }

    public final zzadb zzsf() {
        Image logo = this.zzdoe.getLogo();
        if (logo != null) {
            return new zzabr(logo.getDrawable(), logo.getUri(), logo.getScale());
        }
        return null;
    }

    public final String getCallToAction() {
        return this.zzdoe.getCallToAction();
    }

    public final String getAdvertiser() {
        return this.zzdoe.getAdvertiser();
    }

    public final void recordImpression() {
        this.zzdoe.recordImpression();
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        this.zzdoe.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzdoe.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzdoe.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzm(IObjectWrapper iObjectWrapper) {
        this.zzdoe.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzdoe.getOverrideImpressionRecording();
    }

    public final boolean getOverrideClickHandling() {
        return this.zzdoe.getOverrideClickHandling();
    }

    public final Bundle getExtras() {
        return this.zzdoe.getExtras();
    }

    public final IObjectWrapper zzvb() {
        View adChoicesContent = this.zzdoe.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final zzyp getVideoController() {
        if (this.zzdoe.getVideoController() != null) {
            return this.zzdoe.getVideoController().zzbc();
        }
        return null;
    }

    public final IObjectWrapper zzvc() {
        View zzafh = this.zzdoe.zzafh();
        if (zzafh == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzafh);
    }
}
