package com.amazon.device.ads;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;

class DtbGeoLocation {
    private static final String LOG_TAG = "DtbGeoLocation";

    DtbGeoLocation() {
        if (AdRegistration.getContext() == null) {
            DtbLog.debugError("unable to initialize DtbGeoLocation without setting app context");
            throw new IllegalArgumentException("unable to initialize DtbGeoLocation without setting app context");
        }
    }

    @Nullable
    public String getLocationParam() {
        Location location = getLocation();
        if (location == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(location.getLatitude());
        sb.append(",");
        sb.append(location.getLongitude());
        sb.append(",");
        sb.append(location.getAccuracy());
        return sb.toString();
    }

    @Nullable
    private Location getLocation() {
        Context context = AdRegistration.getContext();
        Location location = null;
        if (!hasLocationPermission(context)) {
            return null;
        }
        Location lastLocation = getLastLocation(context, "gps");
        Location lastLocation2 = getLastLocation(context, "network");
        if (lastLocation == null || lastLocation2 == null) {
            if (lastLocation != null) {
                DtbLog.debug("Setting location using gps, network not available");
                location = lastLocation;
            } else if (lastLocation2 != null) {
                DtbLog.debug("Setting location using network, gps not available");
                location = lastLocation2;
            }
        } else if (lastLocation.distanceTo(lastLocation2) / 1000.0f <= 3.0f) {
            float f = Float.MAX_VALUE;
            float accuracy = lastLocation.hasAccuracy() ? lastLocation.getAccuracy() : Float.MAX_VALUE;
            if (lastLocation2.hasAccuracy()) {
                f = lastLocation2.getAccuracy();
            }
            if (accuracy < f) {
                DtbLog.debug("Setting location using GPS determined by accuracy");
                lastLocation2 = lastLocation;
            } else {
                DtbLog.debug("Setting location using network determined by accuracy");
            }
            location = lastLocation2;
        } else if (lastLocation.getTime() > lastLocation2.getTime()) {
            DtbLog.debug("Setting location using GPS determined by time");
            location = lastLocation;
        } else {
            DtbLog.debug("Setting location using network determined by time");
            location = lastLocation2;
        }
        return location;
    }

    @Nullable
    private Location getLastLocation(Context context, String str) {
        try {
            return ((LocationManager) context.getSystemService("location")).getLastKnownLocation(str);
        } catch (SecurityException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to retrieve location: No permissions to access ");
            sb.append(str);
            DtbLog.warn(sb.toString());
            return null;
        } catch (IllegalArgumentException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to retrieve location: ");
            sb2.append(str);
            sb2.append("not found");
            DtbLog.warn(sb2.toString());
            return null;
        }
    }

    private boolean hasLocationPermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
    }
}
