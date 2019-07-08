package io.fabric.sdk.android.services.common;

class AdvertisingInfo {
    public final String advertisingId;
    public final boolean limitAdTrackingEnabled;

    AdvertisingInfo(String str, boolean z) {
        this.advertisingId = str;
        this.limitAdTrackingEnabled = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvertisingInfo advertisingInfo = (AdvertisingInfo) obj;
        if (this.limitAdTrackingEnabled != advertisingInfo.limitAdTrackingEnabled) {
            return false;
        }
        String str = this.advertisingId;
        return str == null ? advertisingInfo.advertisingId == null : str.equals(advertisingInfo.advertisingId);
    }

    public int hashCode() {
        String str = this.advertisingId;
        return ((str != null ? str.hashCode() : 0) * 31) + (this.limitAdTrackingEnabled ? 1 : 0);
    }
}
