package com.google.ads.interactivemedia.v3.api;

import android.content.Context;
import android.net.Uri;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.internal.acb;
import com.google.ads.interactivemedia.v3.internal.ach;
import com.google.ads.interactivemedia.v3.internal.acn;
import com.google.ads.interactivemedia.v3.internal.aco;
import com.google.ads.interactivemedia.v3.internal.acw;
import com.google.ads.interactivemedia.v3.internal.adj;
import com.google.ads.interactivemedia.v3.internal.adl;
import com.google.ads.interactivemedia.v3.internal.aek;
import com.google.ads.interactivemedia.v3.internal.aem;

/* compiled from: IMASDK */
public class ImaSdkFactory {
    private static ImaSdkFactory instance;

    private ImaSdkFactory() {
    }

    public static ImaSdkFactory getInstance() {
        if (instance == null) {
            instance = new ImaSdkFactory();
        }
        return instance;
    }

    public ImaSdkSettings createImaSdkSettings() {
        return new adl();
    }

    public AdsLoader createAdsLoader(Context context) {
        return createAdsLoader(context, createImaSdkSettings());
    }

    @Deprecated
    public AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings) {
        return new ach(context, readJsCoreUri(imaSdkSettings), imaSdkSettings);
    }

    public AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings, AdDisplayContainer adDisplayContainer) {
        ach ach = new ach(context, readJsCoreUri(imaSdkSettings), imaSdkSettings, adDisplayContainer, (TestingConfiguration) null);
        return ach;
    }

    public AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings, StreamDisplayContainer streamDisplayContainer) {
        ach ach = new ach(context, readJsCoreUri(imaSdkSettings), imaSdkSettings, streamDisplayContainer, (TestingConfiguration) null);
        return ach;
    }

    private AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings, TestingConfiguration testingConfiguration, AdDisplayContainer adDisplayContainer) {
        return createAdsLoader(context, readJsCoreUri(imaSdkSettings), imaSdkSettings, testingConfiguration, adDisplayContainer);
    }

    private AdsLoader createAdsLoader(Context context, Uri uri, ImaSdkSettings imaSdkSettings, TestingConfiguration testingConfiguration, AdDisplayContainer adDisplayContainer) {
        ach ach = new ach(context, uri, imaSdkSettings, adDisplayContainer, testingConfiguration);
        ach.a();
        return ach;
    }

    private AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings, TestingConfiguration testingConfiguration, StreamDisplayContainer streamDisplayContainer) {
        return createAdsLoader(context, readJsCoreUri(imaSdkSettings), imaSdkSettings, testingConfiguration, streamDisplayContainer);
    }

    private AdsLoader createAdsLoader(Context context, Uri uri, ImaSdkSettings imaSdkSettings, TestingConfiguration testingConfiguration, StreamDisplayContainer streamDisplayContainer) {
        ach ach = new ach(context, uri, imaSdkSettings, streamDisplayContainer, testingConfiguration);
        ach.a();
        return ach;
    }

    public AdDisplayContainer createAdDisplayContainer() {
        return new acb();
    }

    public StreamDisplayContainer createStreamDisplayContainer() {
        return new aek();
    }

    public AdsRenderingSettings createAdsRenderingSettings() {
        return new acn();
    }

    public AdsRequest createAdsRequest() {
        return new aco();
    }

    @Deprecated
    public StreamRequest createLiveStreamRequest(String str, String str2, StreamDisplayContainer streamDisplayContainer) {
        aem aem = new aem();
        aem.a(str);
        aem.d(str2);
        aem.a(streamDisplayContainer);
        return aem;
    }

    public StreamRequest createLiveStreamRequest(String str, String str2) {
        aem aem = new aem();
        aem.a(str);
        aem.d(str2);
        return aem;
    }

    @Deprecated
    public StreamRequest createVodStreamRequest(String str, String str2, String str3, StreamDisplayContainer streamDisplayContainer) {
        aem aem = new aem();
        aem.b(str);
        aem.c(str2);
        aem.d(str3);
        aem.a(streamDisplayContainer);
        return aem;
    }

    public StreamRequest createVodStreamRequest(String str, String str2, String str3) {
        aem aem = new aem();
        aem.b(str);
        aem.c(str2);
        aem.d(str3);
        return aem;
    }

    public CompanionAdSlot createCompanionAdSlot() {
        return new acw();
    }

    private Uri readJsCoreUri(ImaSdkSettings imaSdkSettings) {
        Uri uri = adj.b;
        return (imaSdkSettings == null || !imaSdkSettings.isDebugMode()) ? uri : adj.c;
    }
}
