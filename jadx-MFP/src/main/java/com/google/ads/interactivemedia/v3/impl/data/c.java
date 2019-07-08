package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.api.CompanionAd;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.internal.ahh;
import com.google.ads.interactivemedia.v3.internal.ahi;
import com.google.ads.interactivemedia.v3.internal.ahj;
import com.google.ads.interactivemedia.v3.internal.ahk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/* compiled from: IMASDK */
public final class c implements Ad {
    private String adId;
    @ahk
    @ahi
    private d adPodInfo = new d();
    private String adSystem;
    @ahk
    @ahi
    private String[] adWrapperCreativeIds;
    @ahk
    @ahi
    private String[] adWrapperIds;
    @ahk
    @ahi
    private String[] adWrapperSystems;
    private String advertiserName;
    private String clickThroughUrl;
    @ahk
    @ahi
    private List<CompanionAdImpl> companions;
    private String contentType;
    private String creativeAdId;
    private String creativeId;
    private String dealId;
    private String description;
    private boolean disableUi;
    private double duration;
    private int height;
    private boolean isUiDisabled_ = false;
    private boolean linear;
    private double skipTimeOffset = -1.0d;
    private boolean skippable;
    private String surveyUrl;
    private String title;
    private String traffickingParameters;
    @ahk
    @ahi
    private Set<UiElement> uiElements;
    private String universalAdIdRegistry;
    private String universalAdIdValue;
    private int vastMediaBitrate;
    private int vastMediaHeight;
    private int vastMediaWidth;
    private int width;

    public final String getAdId() {
        return this.adId;
    }

    public final void setAdId(String str) {
        this.adId = str;
    }

    public final String getCreativeId() {
        return this.creativeId;
    }

    public final void setCreativeId(String str) {
        this.creativeId = str;
    }

    public final String getCreativeAdId() {
        return this.creativeAdId;
    }

    public final void setCreativeAdId(String str) {
        this.creativeAdId = str;
    }

    public final String getUniversalAdIdValue() {
        return this.universalAdIdValue;
    }

    public final void setUniversalAdIdValue(String str) {
        this.universalAdIdValue = str;
    }

    public final String getUniversalAdIdRegistry() {
        return this.universalAdIdRegistry;
    }

    public final void setUniversalAdIdRegistry(String str) {
        this.universalAdIdRegistry = str;
    }

    public final String getAdSystem() {
        return this.adSystem;
    }

    public final void setAdSystem(String str) {
        this.adSystem = str;
    }

    public final String[] getAdWrapperIds() {
        return this.adWrapperIds;
    }

    public final void setAdWrapperIds(String[] strArr) {
        this.adWrapperIds = strArr;
    }

    public final String[] getAdWrapperSystems() {
        return this.adWrapperSystems;
    }

    public final void setAdWrapperSystems(String[] strArr) {
        this.adWrapperSystems = strArr;
    }

    public final String[] getAdWrapperCreativeIds() {
        return this.adWrapperCreativeIds;
    }

    public final void setAdWrapperCreativeIds(String[] strArr) {
        this.adWrapperCreativeIds = strArr;
    }

    public final boolean isLinear() {
        return this.linear;
    }

    public final void setLinear(boolean z) {
        this.linear = z;
    }

    public final boolean isSkippable() {
        return this.skippable;
    }

    public final void setSkippable(boolean z) {
        this.skippable = z;
    }

    public final double getSkipTimeOffset() {
        return this.skipTimeOffset;
    }

    public final void setSkipTimeOffset(double d) {
        this.skipTimeOffset = d;
    }

    public final boolean isUiDisabled() {
        return this.isUiDisabled_;
    }

    public final void setUiDisabled(boolean z) {
        this.isUiDisabled_ = z;
    }

    public final boolean canDisableUi() {
        return this.disableUi;
    }

    public final void setCanDisableUi(boolean z) {
        this.disableUi = z;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final void setContentType(String str) {
        this.contentType = str;
    }

    public final String getAdvertiserName() {
        return this.advertiserName;
    }

    public final void setAdvertiserName(String str) {
        this.advertiserName = str;
    }

    public final String getSurveyUrl() {
        return this.surveyUrl;
    }

    public final void setSurveyUrl(String str) {
        this.surveyUrl = str;
    }

    public final String getDealId() {
        return this.dealId;
    }

    public final void setDealId(String str) {
        this.dealId = str;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final int getVastMediaWidth() {
        return this.vastMediaWidth;
    }

    public final void setVastMediaWidth(int i) {
        this.vastMediaWidth = i;
    }

    public final int getVastMediaHeight() {
        return this.vastMediaHeight;
    }

    public final void setVastMediaHeight(int i) {
        this.vastMediaHeight = i;
    }

    public final int getVastMediaBitrate() {
        return this.vastMediaBitrate;
    }

    public final void setVastMediaBitrate(int i) {
        this.vastMediaBitrate = i;
    }

    public final String getTraffickingParameters() {
        return this.traffickingParameters;
    }

    public final void setTraffickingParameters(String str) {
        this.traffickingParameters = str;
    }

    public final String getClickThruUrl() {
        return this.clickThroughUrl;
    }

    public final void setClickThruUrl(String str) {
        this.clickThroughUrl = str;
    }

    public final double getDuration() {
        return this.duration;
    }

    public final void setDuration(double d) {
        this.duration = d;
    }

    public final AdPodInfo getAdPodInfo() {
        return this.adPodInfo;
    }

    public final void setAdPodInfo(d dVar) {
        this.adPodInfo = dVar;
    }

    public final Set<UiElement> getUiElements() {
        return this.uiElements;
    }

    public final void setUiElements(Set<UiElement> set) {
        this.uiElements = set;
    }

    public final List<CompanionAd> getCompanionAds() {
        ArrayList arrayList = new ArrayList();
        for (CompanionAd add : this.companions) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public final void setCompanionAds(List<CompanionAdImpl> list) {
        this.companions = list;
    }

    public final int hashCode() {
        return ahj.a(this, new String[0]);
    }

    public final boolean equals(Object obj) {
        return ahh.a((Object) this, obj, new String[0]);
    }

    public final String toString() {
        String str = this.adId;
        String str2 = this.creativeId;
        String str3 = this.creativeAdId;
        String str4 = this.universalAdIdValue;
        String str5 = this.universalAdIdRegistry;
        String str6 = this.title;
        String str7 = this.description;
        String str8 = this.contentType;
        String arrays = Arrays.toString(this.adWrapperIds);
        String arrays2 = Arrays.toString(this.adWrapperSystems);
        String arrays3 = Arrays.toString(this.adWrapperCreativeIds);
        String str9 = this.adSystem;
        String str10 = this.advertiserName;
        String str11 = this.surveyUrl;
        String str12 = this.dealId;
        boolean z = this.linear;
        boolean z2 = this.skippable;
        int i = this.width;
        int i2 = this.height;
        String str13 = this.traffickingParameters;
        String str14 = str11;
        String str15 = this.clickThroughUrl;
        double d = this.duration;
        String valueOf = String.valueOf(this.adPodInfo);
        String valueOf2 = String.valueOf(this.uiElements);
        String str16 = valueOf;
        boolean z3 = this.disableUi;
        double d2 = this.skipTimeOffset;
        double d3 = d2;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 455 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length() + String.valueOf(str7).length() + String.valueOf(str8).length() + String.valueOf(arrays).length() + String.valueOf(arrays2).length() + String.valueOf(arrays3).length() + String.valueOf(str9).length() + String.valueOf(str10).length() + String.valueOf(str14).length() + String.valueOf(str12).length() + String.valueOf(str13).length() + String.valueOf(str15).length() + String.valueOf(str16).length() + String.valueOf(valueOf2).length());
        sb.append("Ad [adId=");
        sb.append(str);
        sb.append(", creativeId=");
        sb.append(str2);
        sb.append(", creativeAdId=");
        sb.append(str3);
        sb.append(", universalAdIdValue=");
        sb.append(str4);
        sb.append(", universalAdIdRegistry=");
        sb.append(str5);
        sb.append(", title=");
        sb.append(str6);
        sb.append(", description=");
        sb.append(str7);
        sb.append(", contentType=");
        sb.append(str8);
        sb.append(", adWrapperIds=");
        sb.append(arrays);
        sb.append(", adWrapperSystems=");
        sb.append(arrays2);
        sb.append(", adWrapperCreativeIds=");
        sb.append(arrays3);
        sb.append(", adSystem=");
        sb.append(str9);
        sb.append(", advertiserName=");
        sb.append(str10);
        sb.append(", surveyUrl=");
        sb.append(str14);
        sb.append(", dealId=");
        sb.append(str12);
        sb.append(", linear=");
        sb.append(z);
        sb.append(", skippable=");
        sb.append(z2);
        sb.append(", width=");
        sb.append(i);
        sb.append(", height=");
        sb.append(i2);
        sb.append(", traffickingParameters=");
        sb.append(str13);
        sb.append(", clickThroughUrl=");
        sb.append(str15);
        sb.append(", duration=");
        sb.append(d);
        sb.append(", adPodInfo=");
        sb.append(str16);
        sb.append(", uiElements=");
        sb.append(valueOf2);
        sb.append(", disableUi=");
        sb.append(z3);
        sb.append(", skipTimeOffset=");
        sb.append(d3);
        sb.append("]");
        return sb.toString();
    }
}
