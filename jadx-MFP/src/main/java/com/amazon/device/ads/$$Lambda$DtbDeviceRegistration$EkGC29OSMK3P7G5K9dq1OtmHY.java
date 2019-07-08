package com.amazon.device.ads;

/* renamed from: com.amazon.device.ads.-$$Lambda$DtbDeviceRegistration$-EkGC29OSM-K3P7G5K9dq1OtmHY reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DtbDeviceRegistration$EkGC29OSMK3P7G5K9dq1OtmHY implements Runnable {
    public static final /* synthetic */ $$Lambda$DtbDeviceRegistration$EkGC29OSMK3P7G5K9dq1OtmHY INSTANCE = new $$Lambda$DtbDeviceRegistration$EkGC29OSMK3P7G5K9dq1OtmHY();

    private /* synthetic */ $$Lambda$DtbDeviceRegistration$EkGC29OSMK3P7G5K9dq1OtmHY() {
    }

    public final void run() {
        DtbDeviceRegistration.dtbDeviceRegistrationInstance.retryInitializeAds();
    }
}
