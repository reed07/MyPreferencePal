package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public class HtmlInterstitial extends ResponseBodyInterstitial {
    private String mClickthroughUrl;
    private String mHtmlData;
    @NonNull
    private CreativeOrientation mOrientation;

    /* access modifiers changed from: protected */
    public void extractExtras(Map<String, String> map) {
        this.mHtmlData = (String) map.get(DataKeys.HTML_RESPONSE_BODY_KEY);
        this.mClickthroughUrl = (String) map.get(DataKeys.CLICKTHROUGH_URL_KEY);
        this.mOrientation = CreativeOrientation.fromHeader((String) map.get(DataKeys.CREATIVE_ORIENTATION_KEY));
    }

    /* access modifiers changed from: protected */
    public void preRenderHtml(CustomEventInterstitialListener customEventInterstitialListener) {
        MoPubActivity.preRenderHtml(this, this.mContext, this.mAdReport, customEventInterstitialListener, this.mHtmlData, this.mClickthroughUrl, this.mBroadcastIdentifier);
    }

    public void showInterstitial() {
        MoPubActivity.start(this.mContext, this.mHtmlData, this.mAdReport, this.mClickthroughUrl, this.mOrientation, this.mBroadcastIdentifier);
    }
}
