package com.inmobi.ads.listeners;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import java.util.Map;

public abstract class BannerAdEventListener {
    public void onAdClicked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
    }

    public void onAdDismissed(InMobiBanner inMobiBanner) {
    }

    public void onAdDisplayed(InMobiBanner inMobiBanner) {
    }

    public void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus) {
    }

    public void onAdLoadSucceeded(InMobiBanner inMobiBanner) {
    }

    public void onRequestPayloadCreated(byte[] bArr) {
    }

    public void onRequestPayloadCreationFailed(InMobiAdRequestStatus inMobiAdRequestStatus) {
    }

    public void onRewardsUnlocked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
    }

    public void onUserLeftApplication(InMobiBanner inMobiBanner) {
    }
}
