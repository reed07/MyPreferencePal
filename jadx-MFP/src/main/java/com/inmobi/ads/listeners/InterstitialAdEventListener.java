package com.inmobi.ads.listeners;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiInterstitial;
import java.util.Map;

public abstract class InterstitialAdEventListener {
    public void onAdClicked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
    }

    public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
    }

    public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
    }

    public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
    }

    public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
    }

    public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
    }

    public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
    }

    public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
    }

    public void onRequestPayloadCreated(byte[] bArr) {
    }

    public void onRequestPayloadCreationFailed(InMobiAdRequestStatus inMobiAdRequestStatus) {
    }

    public void onRewardsUnlocked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
    }

    public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
    }
}
