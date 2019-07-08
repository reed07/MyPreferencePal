package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.internal.acj;
import com.google.ads.interactivemedia.v3.internal.acp;
import com.google.ads.interactivemedia.v3.internal.acq;
import com.google.ads.interactivemedia.v3.internal.aet;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public interface z {
    z adTagParameters(Map<String, String> map);

    z adTagUrl(String str);

    z adsResponse(String str);

    z apiKey(String str);

    z assetKey(String str);

    z authToken(String str);

    y build();

    z companionSlots(Map<String, String> map);

    z contentDuration(Float f);

    z contentKeywords(List<String> list);

    z contentSourceId(String str);

    z contentTitle(String str);

    z env(String str);

    z extraParameters(Map<String, String> map);

    z format(String str);

    z identifierInfo(aet aet);

    z isTv(Boolean bool);

    z linearAdSlotHeight(Integer num);

    z linearAdSlotWidth(Integer num);

    z liveStreamPrefetchSeconds(Float f);

    z marketAppInfo(acj acj);

    z msParameter(String str);

    z network(String str);

    z settings(ImaSdkSettings imaSdkSettings);

    z streamActivityMonitorId(String str);

    z useQAStreamBaseUrl(Boolean bool);

    z vastLoadTimeout(Float f);

    z videoId(String str);

    z videoPlayActivation(acp acp);

    z videoPlayMuted(acq acq);
}
