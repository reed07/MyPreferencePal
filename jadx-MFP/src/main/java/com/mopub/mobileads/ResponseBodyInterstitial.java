package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public abstract class ResponseBodyInterstitial extends CustomEventInterstitial {
    protected AdReport mAdReport;
    protected long mBroadcastIdentifier;
    @Nullable
    private EventForwardingBroadcastReceiver mBroadcastReceiver;
    protected Context mContext;

    /* access modifiers changed from: protected */
    public abstract void extractExtras(Map<String, String> map);

    /* access modifiers changed from: protected */
    public abstract void preRenderHtml(CustomEventInterstitialListener customEventInterstitialListener);

    public abstract void showInterstitial();

    public void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        this.mContext = context;
        if (extrasAreValid(map2)) {
            extractExtras(map2);
            try {
                this.mAdReport = (AdReport) map.get(DataKeys.AD_REPORT_KEY);
                Long l = (Long) map.get(DataKeys.BROADCAST_IDENTIFIER_KEY);
                if (l == null) {
                    MoPubLog.e("Broadcast Identifier was not set in localExtras");
                    customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
                    return;
                }
                this.mBroadcastIdentifier = l.longValue();
                this.mBroadcastReceiver = new EventForwardingBroadcastReceiver(customEventInterstitialListener, this.mBroadcastIdentifier);
                EventForwardingBroadcastReceiver eventForwardingBroadcastReceiver = this.mBroadcastReceiver;
                eventForwardingBroadcastReceiver.register(eventForwardingBroadcastReceiver, context);
                preRenderHtml(customEventInterstitialListener);
            } catch (ClassCastException unused) {
                MoPubLog.e("LocalExtras contained an incorrect type.");
                customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        } else {
            customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
        }
    }

    public void onInvalidate() {
        EventForwardingBroadcastReceiver eventForwardingBroadcastReceiver = this.mBroadcastReceiver;
        if (eventForwardingBroadcastReceiver != null) {
            eventForwardingBroadcastReceiver.unregister(eventForwardingBroadcastReceiver);
            this.mBroadcastReceiver = null;
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }
}
