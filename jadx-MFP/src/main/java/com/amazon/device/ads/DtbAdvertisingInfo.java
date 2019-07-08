package com.amazon.device.ads;

final class DtbAdvertisingInfo {
    private static final String LOG_TAG = "DtbAdvertisingInfo";

    public DtbAdvertisingInfo() {
        if (AdRegistration.getContext() != null) {
            initializeAdvertisingInfo();
        } else {
            DtbLog.debugError("unable to initialize advertising info without setting app context");
            throw new IllegalArgumentException("unable to initialize advertising info without setting app context");
        }
    }

    private void setIsAdvertisingIdentifierChanged(boolean z) {
        DtbSharedPreferences.getInstance().saveIsAdIdChanged(z);
    }

    private void setIsAdvertisingIdentifierNew(boolean z) {
        DtbSharedPreferences.getInstance().saveIsAdIdNew(z);
    }

    private void initializeAdvertisingInfo() {
        DtbLog.debug("Initializing advertising info using Google Play Service");
        AdvertisingInfo advertisingIdentifierInfo = new DtbGooglePlayServices().getAdvertisingIdentifierInfo();
        String advertisingIdentifier = advertisingIdentifierInfo.getAdvertisingIdentifier();
        String idfa = DtbSharedPreferences.getInstance().getIdfa();
        if (advertisingIdentifierInfo.hasAdvertisingIdentifier() && !DtbCommonUtils.isNullOrEmpty(advertisingIdentifier)) {
            if (DtbCommonUtils.isNullOrEmpty(idfa)) {
                setIsAdvertisingIdentifierNew(true);
                StringBuilder sb = new StringBuilder();
                sb.append("Advertising identifier is new. Idfa=");
                sb.append(advertisingIdentifier);
                DtbLog.debug(sb.toString());
            } else if (!DtbCommonUtils.isNullOrEmpty(idfa) && !idfa.equals(advertisingIdentifier)) {
                setIsAdvertisingIdentifierChanged(true);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Advertising identifier has changed. CurrentIdfa=");
                sb2.append(advertisingIdentifier);
                sb2.append(" storedIdfa=");
                sb2.append(idfa);
                DtbLog.debug(sb2.toString());
            }
        }
        if (!advertisingIdentifierInfo.hasAdvertisingIdentifier() && !DtbCommonUtils.isNullOrEmpty(idfa)) {
            setIsAdvertisingIdentifierNew(true);
        }
        if (!DtbCommonUtils.isNullOrEmpty(advertisingIdentifier)) {
            DtbSharedPreferences.getInstance().saveIdfa(advertisingIdentifier);
        }
        if (advertisingIdentifierInfo.isLimitAdTrackingEnabled() != null) {
            DtbSharedPreferences.getInstance().saveOptOut(advertisingIdentifierInfo.isLimitAdTrackingEnabled());
        }
        DtbLog.info(LOG_TAG, "Advertising identifier intialization process complete");
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Google AdId intialized using Google Play Service. AdvertisingIdentifier=");
        sb3.append(advertisingIdentifier);
        sb3.append(" isLimitAdTrackingEnabled=");
        sb3.append(advertisingIdentifierInfo.isLimitAdTrackingEnabled());
        DtbLog.debug(sb3.toString());
    }
}
