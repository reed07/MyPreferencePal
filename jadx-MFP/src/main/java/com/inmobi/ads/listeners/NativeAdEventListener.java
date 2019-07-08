package com.inmobi.ads.listeners;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;

public abstract class NativeAdEventListener {
    public void onAdClicked(InMobiNative inMobiNative) {
    }

    public void onAdFullScreenDismissed(InMobiNative inMobiNative) {
    }

    public void onAdFullScreenDisplayed(InMobiNative inMobiNative) {
    }

    public void onAdFullScreenWillDisplay(InMobiNative inMobiNative) {
    }

    public void onAdImpressed(InMobiNative inMobiNative) {
    }

    public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
    }

    public void onAdLoadSucceeded(InMobiNative inMobiNative) {
    }

    public void onAdStatusChanged(InMobiNative inMobiNative) {
    }

    public void onRequestPayloadCreated(byte[] bArr) {
    }

    public void onRequestPayloadCreationFailed(InMobiAdRequestStatus inMobiAdRequestStatus) {
    }

    public void onUserWillLeaveApplication(InMobiNative inMobiNative) {
    }
}
