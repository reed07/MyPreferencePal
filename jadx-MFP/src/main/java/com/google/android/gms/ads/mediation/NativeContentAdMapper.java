package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd.Image;
import java.util.List;

@Deprecated
public class NativeContentAdMapper extends NativeAdMapper {
    private String zzdaw;
    private List<Image> zzdax;
    private String zzday;
    private String zzdba;
    private String zzdbm;
    private Image zzfbp;

    public final void setHeadline(String str) {
        this.zzdaw = str;
    }

    public final void setImages(List<Image> list) {
        this.zzdax = list;
    }

    public final void setBody(String str) {
        this.zzday = str;
    }

    public final void setLogo(Image image) {
        this.zzfbp = image;
    }

    public final void setCallToAction(String str) {
        this.zzdba = str;
    }

    public final void setAdvertiser(String str) {
        this.zzdbm = str;
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

    public final Image getLogo() {
        return this.zzfbp;
    }

    public final String getCallToAction() {
        return this.zzdba;
    }

    public final String getAdvertiser() {
        return this.zzdbm;
    }
}
