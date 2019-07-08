package com.amazon.device.ads;

class DtbGooglePlayServices {
    private static final String LOG_TAG = "DtbGooglePlayServices";

    static class AdvertisingInfo {
        private String advertisingIdentifier;
        private boolean gpsAvailable = true;
        private Boolean limitAdTrackingEnabled;

        AdvertisingInfo() {
        }

        static AdvertisingInfo createNotAvailable() {
            return new AdvertisingInfo().setGooglePlayServicesAvailable(false);
        }

        /* access modifiers changed from: 0000 */
        public boolean isGooglePlayServicesAvailable() {
            return this.gpsAvailable;
        }

        private AdvertisingInfo setGooglePlayServicesAvailable(boolean z) {
            this.gpsAvailable = z;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public String getAdvertisingIdentifier() {
            return this.advertisingIdentifier;
        }

        /* access modifiers changed from: 0000 */
        public AdvertisingInfo setAdvertisingIdentifier(String str) {
            this.advertisingIdentifier = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public boolean hasAdvertisingIdentifier() {
            return getAdvertisingIdentifier() != null;
        }

        /* access modifiers changed from: 0000 */
        public Boolean isLimitAdTrackingEnabled() {
            return this.limitAdTrackingEnabled;
        }

        /* access modifiers changed from: 0000 */
        public AdvertisingInfo setLimitAdTrackingEnabled(Boolean bool) {
            this.limitAdTrackingEnabled = bool;
            return this;
        }
    }

    DtbGooglePlayServices() {
    }

    public AdvertisingInfo getAdvertisingIdentifierInfo() {
        if (!isGooglePlayServicesAvailable()) {
            DtbLog.debug(LOG_TAG, "The Google Play Services Advertising Identifier feature is not available.");
            return AdvertisingInfo.createNotAvailable();
        }
        AdvertisingInfo advertisingInfo = null;
        boolean isAdvertisingClassAvailable = isAdvertisingClassAvailable();
        if (isAdvertisingClassAvailable) {
            advertisingInfo = DtbGooglePlayServicesAdapter.newAdapter().getAdvertisingIdentifierInfo();
            if (advertisingInfo.getAdvertisingIdentifier() != null && !advertisingInfo.getAdvertisingIdentifier().isEmpty()) {
                return advertisingInfo;
            }
        }
        AdvertisingInfo advertisingIdentifierInfo = DtbFireOSServiceAdapter.newAdapter().getAdvertisingIdentifierInfo();
        if (advertisingIdentifierInfo.getAdvertisingIdentifier() == null || advertisingIdentifierInfo.getAdvertisingIdentifier().isEmpty()) {
            DtbLog.debug(LOG_TAG, "The Google Play Services Advertising Identifier feature is not available.");
            if (!isAdvertisingClassAvailable || (advertisingInfo != null && !advertisingInfo.isGooglePlayServicesAvailable())) {
                setGooglePlayServicesAvailable(false);
            }
            return AdvertisingInfo.createNotAvailable();
        }
        setGooglePlayServicesAvailable(advertisingIdentifierInfo.isGooglePlayServicesAvailable());
        return advertisingIdentifierInfo;
    }

    private boolean isGooglePlayServicesAvailable() {
        return !DtbSharedPreferences.getInstance().isGooglePlayServicesUnavailable();
    }

    private void setGooglePlayServicesAvailable(boolean z) {
        DtbSharedPreferences.getInstance().setGooglePlayServicesUnavailable(!z);
    }

    private boolean isAdvertisingClassAvailable() {
        return DtbCommonUtils.isClassAvailable("com.google.android.gms.ads.identifier.AdvertisingIdClient");
    }
}
