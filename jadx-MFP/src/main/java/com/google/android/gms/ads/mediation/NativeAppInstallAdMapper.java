package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd.Image;
import java.util.List;

@Deprecated
public class NativeAppInstallAdMapper extends NativeAdMapper {
    private String zzdaw;
    private List<Image> zzdax;
    private String zzday;
    private String zzdba;
    private double zzdbb;
    private String zzdbc;
    private String zzdbd;
    private Image zzfbo;

    public final void setHeadline(String str) {
        this.zzdaw = str;
    }

    public final void setImages(List<Image> list) {
        this.zzdax = list;
    }

    public final void setBody(String str) {
        this.zzday = str;
    }

    public final void setIcon(Image image) {
        this.zzfbo = image;
    }

    public final void setCallToAction(String str) {
        this.zzdba = str;
    }

    public final void setStarRating(double d) {
        this.zzdbb = d;
    }

    public final void setStore(String str) {
        this.zzdbc = str;
    }

    public final void setPrice(String str) {
        this.zzdbd = str;
    }

    public final String getHeadline() {
        return this.zzdaw;
    }

    public final List<Image> getImages() {
        return this.zzdax;
    }

    public final String getBody() {
        return this.zzday;
    }

    public final Image getIcon() {
        return this.zzfbo;
    }

    public final String getCallToAction() {
        return this.zzdba;
    }

    public final double getStarRating() {
        return this.zzdbb;
    }

    public final String getStore() {
        return this.zzdbc;
    }

    public final String getPrice() {
        return this.zzdbd;
    }
}
