package com.twitter.sdk.android.core.internal;

import android.content.Context;
import com.mopub.common.GpsHelper;
import com.twitter.sdk.android.core.Twitter;

class AdvertisingInfoReflectionStrategy implements AdvertisingInfoStrategy {
    private final Context context;

    AdvertisingInfoReflectionStrategy(Context context2) {
        this.context = context2.getApplicationContext();
    }

    /* access modifiers changed from: 0000 */
    public boolean isGooglePlayServiceAvailable(Context context2) {
        boolean z = false;
        try {
            if (((Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context2})).intValue() == 0) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    public AdvertisingInfo getAdvertisingInfo() {
        if (isGooglePlayServiceAvailable(this.context)) {
            return new AdvertisingInfo(getAdvertisingId(), isLimitAdTrackingEnabled());
        }
        return null;
    }

    private String getAdvertisingId() {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(getInfo(), new Object[0]);
        } catch (Exception unused) {
            Twitter.getLogger().w("Twitter", "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    private boolean isLimitAdTrackingEnabled() {
        try {
            return ((Boolean) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(getInfo(), new Object[0])).booleanValue();
        } catch (Exception unused) {
            Twitter.getLogger().w("Twitter", "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return false;
        }
    }

    private Object getInfo() {
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.context});
        } catch (Exception unused) {
            Twitter.getLogger().w("Twitter", "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
            return null;
        }
    }
}
