package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.internal.acj;
import com.google.ads.interactivemedia.v3.internal.acp;
import com.google.ads.interactivemedia.v3.internal.acq;
import com.google.ads.interactivemedia.v3.internal.aet;
import com.google.ads.interactivemedia.v3.internal.agb;
import com.google.ads.interactivemedia.v3.internal.age;

/* compiled from: IMASDK */
final class l extends y {
    private final age<String, String> adTagParameters;
    private final String adTagUrl;
    private final String adsResponse;
    private final String apiKey;
    private final String assetKey;
    private final String authToken;
    private final age<String, String> companionSlots;
    private final Float contentDuration;
    private final agb<String> contentKeywords;
    private final String contentSourceId;
    private final String contentTitle;
    private final String env;
    private final age<String, String> extraParameters;
    private final String format;
    private final aet identifierInfo;
    private final Boolean isTv;
    private final Integer linearAdSlotHeight;
    private final Integer linearAdSlotWidth;
    private final Float liveStreamPrefetchSeconds;
    private final acj marketAppInfo;
    private final String msParameter;
    private final String network;
    private final ImaSdkSettings settings;
    private final String streamActivityMonitorId;
    private final Boolean useQAStreamBaseUrl;
    private final Float vastLoadTimeout;
    private final String videoId;
    private final acp videoPlayActivation;
    private final acq videoPlayMuted;

    private l(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, age<String, String> age, String str9, String str10, Float f, agb<String> agb, String str11, Float f2, Float f3, age<String, String> age2, age<String, String> age3, Boolean bool, String str12, Integer num, Integer num2, String str13, aet aet, Boolean bool2, acp acp, acq acq, ImaSdkSettings imaSdkSettings, acj acj) {
        this.adsResponse = str;
        this.adTagUrl = str2;
        this.assetKey = str3;
        this.authToken = str4;
        this.contentSourceId = str5;
        this.videoId = str6;
        this.apiKey = str7;
        this.format = str8;
        this.adTagParameters = age;
        this.env = str9;
        this.network = str10;
        this.contentDuration = f;
        this.contentKeywords = agb;
        this.contentTitle = str11;
        this.vastLoadTimeout = f2;
        this.liveStreamPrefetchSeconds = f3;
        this.companionSlots = age2;
        this.extraParameters = age3;
        this.isTv = bool;
        this.msParameter = str12;
        this.linearAdSlotWidth = num;
        this.linearAdSlotHeight = num2;
        this.streamActivityMonitorId = str13;
        this.identifierInfo = aet;
        this.useQAStreamBaseUrl = bool2;
        this.videoPlayActivation = acp;
        this.videoPlayMuted = acq;
        this.settings = imaSdkSettings;
        this.marketAppInfo = acj;
    }

    public final String adsResponse() {
        return this.adsResponse;
    }

    public final String adTagUrl() {
        return this.adTagUrl;
    }

    public final String assetKey() {
        return this.assetKey;
    }

    public final String authToken() {
        return this.authToken;
    }

    public final String contentSourceId() {
        return this.contentSourceId;
    }

    public final String videoId() {
        return this.videoId;
    }

    public final String apiKey() {
        return this.apiKey;
    }

    public final String format() {
        return this.format;
    }

    public final age<String, String> adTagParameters() {
        return this.adTagParameters;
    }

    public final String env() {
        return this.env;
    }

    public final String network() {
        return this.network;
    }

    public final Float contentDuration() {
        return this.contentDuration;
    }

    public final agb<String> contentKeywords() {
        return this.contentKeywords;
    }

    public final String contentTitle() {
        return this.contentTitle;
    }

    public final Float vastLoadTimeout() {
        return this.vastLoadTimeout;
    }

    public final Float liveStreamPrefetchSeconds() {
        return this.liveStreamPrefetchSeconds;
    }

    public final age<String, String> companionSlots() {
        return this.companionSlots;
    }

    public final age<String, String> extraParameters() {
        return this.extraParameters;
    }

    public final Boolean isTv() {
        return this.isTv;
    }

    public final String msParameter() {
        return this.msParameter;
    }

    public final Integer linearAdSlotWidth() {
        return this.linearAdSlotWidth;
    }

    public final Integer linearAdSlotHeight() {
        return this.linearAdSlotHeight;
    }

    public final String streamActivityMonitorId() {
        return this.streamActivityMonitorId;
    }

    public final aet identifierInfo() {
        return this.identifierInfo;
    }

    public final Boolean useQAStreamBaseUrl() {
        return this.useQAStreamBaseUrl;
    }

    public final acp videoPlayActivation() {
        return this.videoPlayActivation;
    }

    public final acq videoPlayMuted() {
        return this.videoPlayMuted;
    }

    public final ImaSdkSettings settings() {
        return this.settings;
    }

    public final acj marketAppInfo() {
        return this.marketAppInfo;
    }

    public final String toString() {
        String str = this.adsResponse;
        String str2 = this.adTagUrl;
        String str3 = this.assetKey;
        String str4 = this.authToken;
        String str5 = this.contentSourceId;
        String str6 = this.videoId;
        String str7 = this.apiKey;
        String str8 = this.format;
        String valueOf = String.valueOf(this.adTagParameters);
        String str9 = this.env;
        String str10 = this.network;
        String valueOf2 = String.valueOf(this.contentDuration);
        String valueOf3 = String.valueOf(this.contentKeywords);
        String str11 = this.contentTitle;
        String valueOf4 = String.valueOf(this.vastLoadTimeout);
        String valueOf5 = String.valueOf(this.liveStreamPrefetchSeconds);
        String valueOf6 = String.valueOf(this.companionSlots);
        String valueOf7 = String.valueOf(this.extraParameters);
        String valueOf8 = String.valueOf(this.isTv);
        String str12 = this.msParameter;
        String valueOf9 = String.valueOf(this.linearAdSlotWidth);
        String valueOf10 = String.valueOf(this.linearAdSlotHeight);
        String str13 = this.streamActivityMonitorId;
        String valueOf11 = String.valueOf(this.identifierInfo);
        String valueOf12 = String.valueOf(this.useQAStreamBaseUrl);
        String valueOf13 = String.valueOf(this.videoPlayActivation);
        String valueOf14 = String.valueOf(this.videoPlayMuted);
        String valueOf15 = String.valueOf(this.settings);
        String valueOf16 = String.valueOf(this.marketAppInfo);
        String str14 = valueOf16;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 466 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length() + String.valueOf(str7).length() + String.valueOf(str8).length() + String.valueOf(valueOf).length() + String.valueOf(str9).length() + String.valueOf(str10).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(str11).length() + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length() + String.valueOf(valueOf7).length() + String.valueOf(valueOf8).length() + String.valueOf(str12).length() + String.valueOf(valueOf9).length() + String.valueOf(valueOf10).length() + String.valueOf(str13).length() + String.valueOf(valueOf11).length() + String.valueOf(valueOf12).length() + String.valueOf(valueOf13).length() + String.valueOf(valueOf14).length() + String.valueOf(valueOf15).length() + String.valueOf(valueOf16).length());
        sb.append("GsonAdsRequest{adsResponse=");
        sb.append(str);
        sb.append(", adTagUrl=");
        sb.append(str2);
        sb.append(", assetKey=");
        sb.append(str3);
        sb.append(", authToken=");
        sb.append(str4);
        sb.append(", contentSourceId=");
        sb.append(str5);
        sb.append(", videoId=");
        sb.append(str6);
        sb.append(", apiKey=");
        sb.append(str7);
        sb.append(", format=");
        sb.append(str8);
        sb.append(", adTagParameters=");
        sb.append(valueOf);
        sb.append(", env=");
        sb.append(str9);
        sb.append(", network=");
        sb.append(str10);
        sb.append(", contentDuration=");
        sb.append(valueOf2);
        sb.append(", contentKeywords=");
        sb.append(valueOf3);
        sb.append(", contentTitle=");
        sb.append(str11);
        sb.append(", vastLoadTimeout=");
        sb.append(valueOf4);
        sb.append(", liveStreamPrefetchSeconds=");
        sb.append(valueOf5);
        sb.append(", companionSlots=");
        sb.append(valueOf6);
        sb.append(", extraParameters=");
        sb.append(valueOf7);
        sb.append(", isTv=");
        sb.append(valueOf8);
        sb.append(", msParameter=");
        sb.append(str12);
        sb.append(", linearAdSlotWidth=");
        sb.append(valueOf9);
        sb.append(", linearAdSlotHeight=");
        sb.append(valueOf10);
        sb.append(", streamActivityMonitorId=");
        sb.append(str13);
        sb.append(", identifierInfo=");
        sb.append(valueOf11);
        sb.append(", useQAStreamBaseUrl=");
        sb.append(valueOf12);
        sb.append(", videoPlayActivation=");
        sb.append(valueOf13);
        sb.append(", videoPlayMuted=");
        sb.append(valueOf14);
        sb.append(", settings=");
        sb.append(valueOf15);
        sb.append(", marketAppInfo=");
        sb.append(str14);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        String str = this.adsResponse;
        if (str != null ? str.equals(yVar.adsResponse()) : yVar.adsResponse() == null) {
            String str2 = this.adTagUrl;
            if (str2 != null ? str2.equals(yVar.adTagUrl()) : yVar.adTagUrl() == null) {
                String str3 = this.assetKey;
                if (str3 != null ? str3.equals(yVar.assetKey()) : yVar.assetKey() == null) {
                    String str4 = this.authToken;
                    if (str4 != null ? str4.equals(yVar.authToken()) : yVar.authToken() == null) {
                        String str5 = this.contentSourceId;
                        if (str5 != null ? str5.equals(yVar.contentSourceId()) : yVar.contentSourceId() == null) {
                            String str6 = this.videoId;
                            if (str6 != null ? str6.equals(yVar.videoId()) : yVar.videoId() == null) {
                                String str7 = this.apiKey;
                                if (str7 != null ? str7.equals(yVar.apiKey()) : yVar.apiKey() == null) {
                                    String str8 = this.format;
                                    if (str8 != null ? str8.equals(yVar.format()) : yVar.format() == null) {
                                        age<String, String> age = this.adTagParameters;
                                        if (age != null ? age.equals(yVar.adTagParameters()) : yVar.adTagParameters() == null) {
                                            String str9 = this.env;
                                            if (str9 != null ? str9.equals(yVar.env()) : yVar.env() == null) {
                                                String str10 = this.network;
                                                if (str10 != null ? str10.equals(yVar.network()) : yVar.network() == null) {
                                                    Float f = this.contentDuration;
                                                    if (f != null ? f.equals(yVar.contentDuration()) : yVar.contentDuration() == null) {
                                                        agb<String> agb = this.contentKeywords;
                                                        if (agb != null ? agb.equals(yVar.contentKeywords()) : yVar.contentKeywords() == null) {
                                                            String str11 = this.contentTitle;
                                                            if (str11 != null ? str11.equals(yVar.contentTitle()) : yVar.contentTitle() == null) {
                                                                Float f2 = this.vastLoadTimeout;
                                                                if (f2 != null ? f2.equals(yVar.vastLoadTimeout()) : yVar.vastLoadTimeout() == null) {
                                                                    Float f3 = this.liveStreamPrefetchSeconds;
                                                                    if (f3 != null ? f3.equals(yVar.liveStreamPrefetchSeconds()) : yVar.liveStreamPrefetchSeconds() == null) {
                                                                        age<String, String> age2 = this.companionSlots;
                                                                        if (age2 != null ? age2.equals(yVar.companionSlots()) : yVar.companionSlots() == null) {
                                                                            age<String, String> age3 = this.extraParameters;
                                                                            if (age3 != null ? age3.equals(yVar.extraParameters()) : yVar.extraParameters() == null) {
                                                                                Boolean bool = this.isTv;
                                                                                if (bool != null ? bool.equals(yVar.isTv()) : yVar.isTv() == null) {
                                                                                    String str12 = this.msParameter;
                                                                                    if (str12 != null ? str12.equals(yVar.msParameter()) : yVar.msParameter() == null) {
                                                                                        Integer num = this.linearAdSlotWidth;
                                                                                        if (num != null ? num.equals(yVar.linearAdSlotWidth()) : yVar.linearAdSlotWidth() == null) {
                                                                                            Integer num2 = this.linearAdSlotHeight;
                                                                                            if (num2 != null ? num2.equals(yVar.linearAdSlotHeight()) : yVar.linearAdSlotHeight() == null) {
                                                                                                String str13 = this.streamActivityMonitorId;
                                                                                                if (str13 != null ? str13.equals(yVar.streamActivityMonitorId()) : yVar.streamActivityMonitorId() == null) {
                                                                                                    aet aet = this.identifierInfo;
                                                                                                    if (aet != null ? aet.equals(yVar.identifierInfo()) : yVar.identifierInfo() == null) {
                                                                                                        Boolean bool2 = this.useQAStreamBaseUrl;
                                                                                                        if (bool2 != null ? bool2.equals(yVar.useQAStreamBaseUrl()) : yVar.useQAStreamBaseUrl() == null) {
                                                                                                            acp acp = this.videoPlayActivation;
                                                                                                            if (acp != null ? acp.equals(yVar.videoPlayActivation()) : yVar.videoPlayActivation() == null) {
                                                                                                                acq acq = this.videoPlayMuted;
                                                                                                                if (acq != null ? acq.equals(yVar.videoPlayMuted()) : yVar.videoPlayMuted() == null) {
                                                                                                                    ImaSdkSettings imaSdkSettings = this.settings;
                                                                                                                    if (imaSdkSettings != null ? imaSdkSettings.equals(yVar.settings()) : yVar.settings() == null) {
                                                                                                                        acj acj = this.marketAppInfo;
                                                                                                                        return acj != null ? acj.equals(yVar.marketAppInfo()) : yVar.marketAppInfo() == null;
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public final int hashCode() {
        String str = this.adsResponse;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.adTagUrl;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.assetKey;
        int hashCode3 = (hashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.authToken;
        int hashCode4 = (hashCode3 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.contentSourceId;
        int hashCode5 = (hashCode4 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.videoId;
        int hashCode6 = (hashCode5 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.apiKey;
        int hashCode7 = (hashCode6 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        String str8 = this.format;
        int hashCode8 = (hashCode7 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        age<String, String> age = this.adTagParameters;
        int hashCode9 = (hashCode8 ^ (age == null ? 0 : age.hashCode())) * 1000003;
        String str9 = this.env;
        int hashCode10 = (hashCode9 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        String str10 = this.network;
        int hashCode11 = (hashCode10 ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
        Float f = this.contentDuration;
        int hashCode12 = (hashCode11 ^ (f == null ? 0 : f.hashCode())) * 1000003;
        agb<String> agb = this.contentKeywords;
        int hashCode13 = (hashCode12 ^ (agb == null ? 0 : agb.hashCode())) * 1000003;
        String str11 = this.contentTitle;
        int hashCode14 = (hashCode13 ^ (str11 == null ? 0 : str11.hashCode())) * 1000003;
        Float f2 = this.vastLoadTimeout;
        int hashCode15 = (hashCode14 ^ (f2 == null ? 0 : f2.hashCode())) * 1000003;
        Float f3 = this.liveStreamPrefetchSeconds;
        int hashCode16 = (hashCode15 ^ (f3 == null ? 0 : f3.hashCode())) * 1000003;
        age<String, String> age2 = this.companionSlots;
        int hashCode17 = (hashCode16 ^ (age2 == null ? 0 : age2.hashCode())) * 1000003;
        age<String, String> age3 = this.extraParameters;
        int hashCode18 = (hashCode17 ^ (age3 == null ? 0 : age3.hashCode())) * 1000003;
        Boolean bool = this.isTv;
        int hashCode19 = (hashCode18 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        String str12 = this.msParameter;
        int hashCode20 = (hashCode19 ^ (str12 == null ? 0 : str12.hashCode())) * 1000003;
        Integer num = this.linearAdSlotWidth;
        int hashCode21 = (hashCode20 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.linearAdSlotHeight;
        int hashCode22 = (hashCode21 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str13 = this.streamActivityMonitorId;
        int hashCode23 = (hashCode22 ^ (str13 == null ? 0 : str13.hashCode())) * 1000003;
        aet aet = this.identifierInfo;
        int hashCode24 = (hashCode23 ^ (aet == null ? 0 : aet.hashCode())) * 1000003;
        Boolean bool2 = this.useQAStreamBaseUrl;
        int hashCode25 = (hashCode24 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        acp acp = this.videoPlayActivation;
        int hashCode26 = (hashCode25 ^ (acp == null ? 0 : acp.hashCode())) * 1000003;
        acq acq = this.videoPlayMuted;
        int hashCode27 = (hashCode26 ^ (acq == null ? 0 : acq.hashCode())) * 1000003;
        ImaSdkSettings imaSdkSettings = this.settings;
        int hashCode28 = (hashCode27 ^ (imaSdkSettings == null ? 0 : imaSdkSettings.hashCode())) * 1000003;
        acj acj = this.marketAppInfo;
        if (acj != null) {
            i = acj.hashCode();
        }
        return hashCode28 ^ i;
    }

    /* synthetic */ l(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, age age, String str9, String str10, Float f, agb agb, String str11, Float f2, Float f3, age age2, age age3, Boolean bool, String str12, Integer num, Integer num2, String str13, aet aet, Boolean bool2, acp acp, acq acq, ImaSdkSettings imaSdkSettings, acj acj, f fVar) {
        this(str, str2, str3, str4, str5, str6, str7, str8, age, str9, str10, f, agb, str11, f2, f3, age2, age3, bool, str12, num, num2, str13, aet, bool2, acp, acq, imaSdkSettings, acj);
    }
}
