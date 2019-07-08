package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.internal.ads.zzark;
import java.util.List;
import java.util.Map;

@zzark
public class UnifiedNativeAdMapper {
    private Bundle extras = new Bundle();
    private VideoController zzdee;
    private String zzdyb;
    private String zzfbq;
    private List<Image> zzfbr;
    private Image zzfbs;
    private String zzfbt;
    private String zzfbu;
    private Double zzfbv;
    private String zzfbw;
    private String zzfbx;
    private boolean zzfby;
    private View zzfbz;
    private View zzfca;
    private Object zzfcb;
    private boolean zzfcc;
    private boolean zzfcd;

    public void handleClick(View view) {
    }

    public void recordImpression() {
    }

    public void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
    }

    public void untrackView(View view) {
    }

    public final void setHeadline(String str) {
        this.zzfbq = str;
    }

    public final void setImages(List<Image> list) {
        this.zzfbr = list;
    }

    public final void setBody(String str) {
        this.zzdyb = str;
    }

    public final void setIcon(Image image) {
        this.zzfbs = image;
    }

    public final void setCallToAction(String str) {
        this.zzfbt = str;
    }

    public final void setAdvertiser(String str) {
        this.zzfbu = str;
    }

    public final void setStarRating(Double d) {
        this.zzfbv = d;
    }

    public final void setStore(String str) {
        this.zzfbw = str;
    }

    public final void setPrice(String str) {
        this.zzfbx = str;
    }

    public final void zza(VideoController videoController) {
        this.zzdee = videoController;
    }

    public void setHasVideoContent(boolean z) {
        this.zzfby = z;
    }

    public void setAdChoicesContent(View view) {
        this.zzfbz = view;
    }

    public void setMediaView(View view) {
        this.zzfca = view;
    }

    public final void zzp(Object obj) {
        this.zzfcb = obj;
    }

    public final void setExtras(Bundle bundle) {
        this.extras = bundle;
    }

    public final void setOverrideImpressionRecording(boolean z) {
        this.zzfcc = z;
    }

    public final void setOverrideClickHandling(boolean z) {
        this.zzfcd = z;
    }

    public final String getHeadline() {
        return this.zzfbq;
    }

    public final List<Image> getImages() {
        return this.zzfbr;
    }

    public final String getBody() {
        return this.zzdyb;
    }

    public final Image getIcon() {
        return this.zzfbs;
    }

    public final String getCallToAction() {
        return this.zzfbt;
    }

    public final String getAdvertiser() {
        return this.zzfbu;
    }

    public final Double getStarRating() {
        return this.zzfbv;
    }

    public final String getStore() {
        return this.zzfbw;
    }

    public final String getPrice() {
        return this.zzfbx;
    }

    public final VideoController getVideoController() {
        return this.zzdee;
    }

    public boolean hasVideoContent() {
        return this.zzfby;
    }

    public View getAdChoicesContent() {
        return this.zzfbz;
    }

    public final View zzafh() {
        return this.zzfca;
    }

    public final Object zzic() {
        return this.zzfcb;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzfcc;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzfcd;
    }
}
