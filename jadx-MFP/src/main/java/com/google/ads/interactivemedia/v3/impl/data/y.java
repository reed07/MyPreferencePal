package com.google.ads.interactivemedia.v3.impl.data;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.StreamRequest.StreamFormat;
import com.google.ads.interactivemedia.v3.internal.acb;
import com.google.ads.interactivemedia.v3.internal.acj;
import com.google.ads.interactivemedia.v3.internal.aco;
import com.google.ads.interactivemedia.v3.internal.acp;
import com.google.ads.interactivemedia.v3.internal.acq;
import com.google.ads.interactivemedia.v3.internal.act;
import com.google.ads.interactivemedia.v3.internal.aek;
import com.google.ads.interactivemedia.v3.internal.aet;
import com.google.ads.interactivemedia.v3.internal.agb;
import com.google.ads.interactivemedia.v3.internal.age;
import com.google.ads.interactivemedia.v3.internal.agj;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public abstract class y {
    public abstract age<String, String> adTagParameters();

    public abstract String adTagUrl();

    public abstract String adsResponse();

    public abstract String apiKey();

    public abstract String assetKey();

    public abstract String authToken();

    public abstract age<String, String> companionSlots();

    public abstract Float contentDuration();

    public abstract agb<String> contentKeywords();

    public abstract String contentSourceId();

    public abstract String contentTitle();

    public abstract String env();

    public abstract age<String, String> extraParameters();

    public abstract String format();

    public abstract aet identifierInfo();

    public abstract Boolean isTv();

    public abstract Integer linearAdSlotHeight();

    public abstract Integer linearAdSlotWidth();

    public abstract Float liveStreamPrefetchSeconds();

    public abstract acj marketAppInfo();

    public abstract String msParameter();

    public abstract String network();

    public abstract ImaSdkSettings settings();

    public abstract String streamActivityMonitorId();

    public abstract Boolean useQAStreamBaseUrl();

    public abstract Float vastLoadTimeout();

    public abstract String videoId();

    public abstract acp videoPlayActivation();

    public abstract acq videoPlayMuted();

    public static y create(AdsRequest adsRequest, String str, String str2, ImaSdkSettings imaSdkSettings, acj acj, boolean z, aet aet, AdDisplayContainer adDisplayContainer) {
        String adTagUrl = adsRequest.getAdTagUrl();
        String adsResponse = adsRequest.getAdsResponse();
        Map extraParameters = adsRequest.getExtraParameters();
        aco aco = (aco) adsRequest;
        acp a = aco.a();
        acq b = aco.b();
        Float c = aco.c();
        List d = aco.d();
        String e = aco.e();
        Float f = aco.f();
        Float g = aco.g();
        Map companionSlots = getCompanionSlots((acb) adDisplayContainer);
        ViewGroup adContainer = adDisplayContainer.getAdContainer();
        z adsResponse2 = builder().adTagUrl(adTagUrl).adsResponse(adsResponse);
        String str3 = str;
        String str4 = str2;
        return adsResponse2.env(str).network(str2).extraParameters(extraParameters).settings(imaSdkSettings).videoPlayActivation(a).videoPlayMuted(b).contentDuration(c).contentKeywords(d).contentTitle(e).vastLoadTimeout(f).liveStreamPrefetchSeconds(g).companionSlots(companionSlots).marketAppInfo(acj).isTv(Boolean.valueOf(z)).identifierInfo(aet).linearAdSlotWidth(Integer.valueOf(adContainer.getWidth())).linearAdSlotHeight(Integer.valueOf(adContainer.getHeight())).build();
    }

    public static y createFromStreamRequest(StreamRequest streamRequest, String str, String str2, ImaSdkSettings imaSdkSettings, acj acj, boolean z, String str3, aet aet, StreamDisplayContainer streamDisplayContainer) {
        Map companionSlots = getCompanionSlots((aek) streamDisplayContainer);
        ViewGroup adContainer = streamDisplayContainer.getAdContainer();
        String str4 = "hls";
        if (streamRequest.getFormat() == StreamFormat.DASH) {
            str4 = "dash";
        }
        return builder().assetKey(streamRequest.getAssetKey()).authToken(streamRequest.getAuthToken()).contentSourceId(streamRequest.getContentSourceId()).videoId(streamRequest.getVideoId()).apiKey(streamRequest.getApiKey()).adTagParameters(streamRequest.getAdTagParameters()).env(str).network(str2).settings(imaSdkSettings).companionSlots(companionSlots).marketAppInfo(acj).isTv(Boolean.valueOf(z)).msParameter(str3).linearAdSlotWidth(Integer.valueOf(adContainer.getWidth())).linearAdSlotHeight(Integer.valueOf(adContainer.getHeight())).streamActivityMonitorId(streamRequest.getStreamActivityMonitorId()).format(str4).identifierInfo(aet).useQAStreamBaseUrl(streamRequest.getUseQAStreamBaseUrl()).build();
    }

    public static z builder() {
        return new m();
    }

    private static Map<String, String> getCompanionSlots(act act) {
        Map a = act.a();
        if (a == null || a.isEmpty()) {
            return null;
        }
        agj agj = new agj();
        for (String str : a.keySet()) {
            CompanionAdSlot companionAdSlot = (CompanionAdSlot) a.get(str);
            int width = companionAdSlot.getWidth();
            int height = companionAdSlot.getHeight();
            StringBuilder sb = new StringBuilder(23);
            sb.append(width);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(height);
            agj.b(str, sb.toString());
        }
        return agj.a();
    }
}
