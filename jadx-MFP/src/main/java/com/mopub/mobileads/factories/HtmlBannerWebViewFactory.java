package com.mopub.mobileads.factories;

import android.content.Context;
import android.support.annotation.NonNull;
import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.HtmlBannerWebView;

public class HtmlBannerWebViewFactory {
    protected static HtmlBannerWebViewFactory instance = new HtmlBannerWebViewFactory();

    @NonNull
    public static HtmlBannerWebView create(Context context, AdReport adReport, CustomEventBannerListener customEventBannerListener, String str) {
        return instance.internalCreate(context, adReport, customEventBannerListener, str);
    }

    public HtmlBannerWebView internalCreate(Context context, AdReport adReport, CustomEventBannerListener customEventBannerListener, String str) {
        HtmlBannerWebView htmlBannerWebView = new HtmlBannerWebView(context, adReport);
        htmlBannerWebView.init(customEventBannerListener, str, adReport.getDspCreativeId());
        return htmlBannerWebView;
    }

    @Deprecated
    public static void setInstance(HtmlBannerWebViewFactory htmlBannerWebViewFactory) {
        instance = htmlBannerWebViewFactory;
    }
}
