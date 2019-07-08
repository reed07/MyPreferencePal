package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.internal.acj;
import com.google.ads.interactivemedia.v3.internal.acp;
import com.google.ads.interactivemedia.v3.internal.acq;
import com.google.ads.interactivemedia.v3.internal.aet;
import com.google.ads.interactivemedia.v3.internal.agb;
import com.google.ads.interactivemedia.v3.internal.age;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
final class m implements z {
    private age<String, String> adTagParameters;
    private String adTagUrl;
    private String adsResponse;
    private String apiKey;
    private String assetKey;
    private String authToken;
    private age<String, String> companionSlots;
    private Float contentDuration;
    private agb<String> contentKeywords;
    private String contentSourceId;
    private String contentTitle;
    private String env;
    private age<String, String> extraParameters;
    private String format;
    private aet identifierInfo;
    private Boolean isTv;
    private Integer linearAdSlotHeight;
    private Integer linearAdSlotWidth;
    private Float liveStreamPrefetchSeconds;
    private acj marketAppInfo;
    private String msParameter;
    private String network;
    private ImaSdkSettings settings;
    private String streamActivityMonitorId;
    private Boolean useQAStreamBaseUrl;
    private Float vastLoadTimeout;
    private String videoId;
    private acp videoPlayActivation;
    private acq videoPlayMuted;

    m() {
    }

    public final z adsResponse(String str) {
        this.adsResponse = str;
        return this;
    }

    public final z adTagUrl(String str) {
        this.adTagUrl = str;
        return this;
    }

    public final z assetKey(String str) {
        this.assetKey = str;
        return this;
    }

    public final z authToken(String str) {
        this.authToken = str;
        return this;
    }

    public final z contentSourceId(String str) {
        this.contentSourceId = str;
        return this;
    }

    public final z videoId(String str) {
        this.videoId = str;
        return this;
    }

    public final z apiKey(String str) {
        this.apiKey = str;
        return this;
    }

    public final z format(String str) {
        this.format = str;
        return this;
    }

    public final z adTagParameters(Map<String, String> map) {
        this.adTagParameters = map == null ? null : age.a(map);
        return this;
    }

    public final z env(String str) {
        this.env = str;
        return this;
    }

    public final z network(String str) {
        this.network = str;
        return this;
    }

    public final z contentDuration(Float f) {
        this.contentDuration = f;
        return this;
    }

    public final z contentKeywords(List<String> list) {
        this.contentKeywords = list == null ? null : agb.a((Collection<? extends E>) list);
        return this;
    }

    public final z contentTitle(String str) {
        this.contentTitle = str;
        return this;
    }

    public final z vastLoadTimeout(Float f) {
        this.vastLoadTimeout = f;
        return this;
    }

    public final z liveStreamPrefetchSeconds(Float f) {
        this.liveStreamPrefetchSeconds = f;
        return this;
    }

    public final z companionSlots(Map<String, String> map) {
        this.companionSlots = map == null ? null : age.a(map);
        return this;
    }

    public final z extraParameters(Map<String, String> map) {
        this.extraParameters = map == null ? null : age.a(map);
        return this;
    }

    public final z isTv(Boolean bool) {
        this.isTv = bool;
        return this;
    }

    public final z msParameter(String str) {
        this.msParameter = str;
        return this;
    }

    public final z linearAdSlotWidth(Integer num) {
        this.linearAdSlotWidth = num;
        return this;
    }

    public final z linearAdSlotHeight(Integer num) {
        this.linearAdSlotHeight = num;
        return this;
    }

    public final z streamActivityMonitorId(String str) {
        this.streamActivityMonitorId = str;
        return this;
    }

    public final z identifierInfo(aet aet) {
        this.identifierInfo = aet;
        return this;
    }

    public final z useQAStreamBaseUrl(Boolean bool) {
        this.useQAStreamBaseUrl = bool;
        return this;
    }

    public final z videoPlayActivation(acp acp) {
        this.videoPlayActivation = acp;
        return this;
    }

    public final z videoPlayMuted(acq acq) {
        this.videoPlayMuted = acq;
        return this;
    }

    public final z settings(ImaSdkSettings imaSdkSettings) {
        this.settings = imaSdkSettings;
        return this;
    }

    public final z marketAppInfo(acj acj) {
        this.marketAppInfo = acj;
        return this;
    }

    public final y build() {
        l lVar = new l(this.adsResponse, this.adTagUrl, this.assetKey, this.authToken, this.contentSourceId, this.videoId, this.apiKey, this.format, this.adTagParameters, this.env, this.network, this.contentDuration, this.contentKeywords, this.contentTitle, this.vastLoadTimeout, this.liveStreamPrefetchSeconds, this.companionSlots, this.extraParameters, this.isTv, this.msParameter, this.linearAdSlotWidth, this.linearAdSlotHeight, this.streamActivityMonitorId, this.identifierInfo, this.useQAStreamBaseUrl, this.videoPlayActivation, this.videoPlayMuted, this.settings, this.marketAppInfo, null);
        return lVar;
    }
}
