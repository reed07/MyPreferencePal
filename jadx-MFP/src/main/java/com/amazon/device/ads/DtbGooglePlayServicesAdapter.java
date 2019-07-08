package com.amazon.device.ads;

class DtbGooglePlayServicesAdapter {
    DtbGooglePlayServicesAdapter() {
    }

    public static DtbGooglePlayServicesAdapter newAdapter() {
        return new DtbGooglePlayServicesAdapter();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.amazon.device.ads.DtbGooglePlayServices.AdvertisingInfo getAdvertisingIdentifierInfo() {
        /*
            r5 = this;
            android.content.Context r0 = com.amazon.device.ads.AdRegistration.getContext()     // Catch:{ IllegalStateException -> 0x005b, IOException -> 0x0041, GooglePlayServicesNotAvailableException -> 0x0023, GooglePlayServicesRepairableException -> 0x0009 }
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r0 = com.google.android.gms.ads.identifier.AdvertisingIdClient.getAdvertisingIdInfo(r0)     // Catch:{ IllegalStateException -> 0x005b, IOException -> 0x0041, GooglePlayServicesNotAvailableException -> 0x0023, GooglePlayServicesRepairableException -> 0x0009 }
            goto L_0x0075
        L_0x0009:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Retrieving the Google Play Services Advertising Identifier caused a GooglePlayServicesRepairableException."
            r1.append(r2)
            java.lang.String r0 = com.amazon.device.ads.DtbCommonUtils.exceptionToString(r0)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.amazon.device.ads.DtbLog.error(r0)
            goto L_0x0074
        L_0x0023:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Retrieving the Google Play Services Advertising Identifier caused a GooglePlayServicesNotAvailableException."
            r1.append(r2)
            java.lang.String r0 = com.amazon.device.ads.DtbCommonUtils.exceptionToString(r0)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.amazon.device.ads.DtbLog.error(r0)
            com.amazon.device.ads.DtbGooglePlayServices$AdvertisingInfo r0 = com.amazon.device.ads.DtbGooglePlayServices.AdvertisingInfo.createNotAvailable()
            return r0
        L_0x0041:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Retrieving the Google Play Services Advertising Identifier caused an IOException."
            r1.append(r2)
            java.lang.String r0 = com.amazon.device.ads.DtbCommonUtils.exceptionToString(r0)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.amazon.device.ads.DtbLog.error(r0)
            goto L_0x0074
        L_0x005b:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Retrieving the Google Play Services  caused Illegal State Exception ( be sure the call was made from a non-background thread)."
            r1.append(r2)
            java.lang.String r0 = com.amazon.device.ads.DtbCommonUtils.exceptionToString(r0)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.amazon.device.ads.DtbLog.error(r0)
        L_0x0074:
            r0 = 0
        L_0x0075:
            if (r0 != 0) goto L_0x0082
            java.lang.String r0 = "The Google Play Services Advertising Identifier could not be retrieved."
            com.amazon.device.ads.DtbLog.debug(r0)
            com.amazon.device.ads.DtbGooglePlayServices$AdvertisingInfo r0 = new com.amazon.device.ads.DtbGooglePlayServices$AdvertisingInfo
            r0.<init>()
            return r0
        L_0x0082:
            java.lang.String r1 = "The Google Play Services Advertising Identifier was successfully retrieved."
            com.amazon.device.ads.DtbLog.debug(r1)
            java.lang.String r1 = r0.getId()
            boolean r2 = r0.isLimitAdTrackingEnabled()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = " Retrieved google ad id "
            r3.append(r4)
            java.lang.String r4 = r0.getId()
            r3.append(r4)
            java.lang.String r4 = " and tracking enabled : "
            r3.append(r4)
            boolean r0 = r0.isLimitAdTrackingEnabled()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.amazon.device.ads.DtbLog.debug(r0)
            com.amazon.device.ads.DtbGooglePlayServices$AdvertisingInfo r0 = new com.amazon.device.ads.DtbGooglePlayServices$AdvertisingInfo
            r0.<init>()
            com.amazon.device.ads.DtbGooglePlayServices$AdvertisingInfo r0 = r0.setAdvertisingIdentifier(r1)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            com.amazon.device.ads.DtbGooglePlayServices$AdvertisingInfo r0 = r0.setLimitAdTrackingEnabled(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.DtbGooglePlayServicesAdapter.getAdvertisingIdentifierInfo():com.amazon.device.ads.DtbGooglePlayServices$AdvertisingInfo");
    }
}
