package com.twitter.sdk.android.core.internal;

class AdvertisingInfo {
    final String advertisingId;
    final boolean limitAdTrackingEnabled;

    AdvertisingInfo(String str, boolean z) {
        this.advertisingId = str;
        this.limitAdTrackingEnabled = z;
    }
}
