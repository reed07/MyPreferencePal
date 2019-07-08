package com.mopub.common;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import java.math.BigDecimal;

public class LocationService {
    private static volatile LocationService sInstance;
    @Nullable
    @VisibleForTesting
    Location mLastKnownLocation;
    @VisibleForTesting
    long mLocationLastUpdatedMillis;

    public enum LocationAwareness {
        NORMAL,
        TRUNCATED,
        DISABLED;

        @Deprecated
        public com.mopub.common.MoPub.LocationAwareness getNewLocationAwareness() {
            if (this == TRUNCATED) {
                return com.mopub.common.MoPub.LocationAwareness.TRUNCATED;
            }
            if (this == DISABLED) {
                return com.mopub.common.MoPub.LocationAwareness.DISABLED;
            }
            return com.mopub.common.MoPub.LocationAwareness.NORMAL;
        }

        @Deprecated
        public static LocationAwareness fromMoPubLocationAwareness(com.mopub.common.MoPub.LocationAwareness locationAwareness) {
            if (locationAwareness == com.mopub.common.MoPub.LocationAwareness.DISABLED) {
                return DISABLED;
            }
            if (locationAwareness == com.mopub.common.MoPub.LocationAwareness.TRUNCATED) {
                return TRUNCATED;
            }
            return NORMAL;
        }
    }

    public enum ValidLocationProvider {
        NETWORK("network"),
        GPS("gps");
        
        @NonNull
        final String name;

        private ValidLocationProvider(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }

        /* access modifiers changed from: private */
        public boolean hasRequiredPermissions(@NonNull Context context) {
            boolean z = false;
            switch (this) {
                case NETWORK:
                    if (DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                        z = true;
                    }
                    return z;
                case GPS:
                    return DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION");
                default:
                    return false;
            }
        }
    }

    private LocationService() {
    }

    @NonNull
    @VisibleForTesting
    static LocationService getInstance() {
        LocationService locationService = sInstance;
        if (locationService == null) {
            synchronized (LocationService.class) {
                locationService = sInstance;
                if (locationService == null) {
                    locationService = new LocationService();
                    sInstance = locationService;
                }
            }
        }
        return locationService;
    }

    @Nullable
    public static Location getLastKnownLocation(@NonNull Context context, int i, @NonNull com.mopub.common.MoPub.LocationAwareness locationAwareness) {
        if (!MoPub.canCollectPersonalInformation()) {
            return null;
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(locationAwareness);
        if (locationAwareness == com.mopub.common.MoPub.LocationAwareness.DISABLED) {
            return null;
        }
        LocationService instance = getInstance();
        if (isLocationFreshEnough()) {
            return instance.mLastKnownLocation;
        }
        Location mostRecentValidLocation = getMostRecentValidLocation(getLocationFromProvider(context, ValidLocationProvider.GPS), getLocationFromProvider(context, ValidLocationProvider.NETWORK));
        if (locationAwareness == com.mopub.common.MoPub.LocationAwareness.TRUNCATED) {
            truncateLocationLatLon(mostRecentValidLocation, i);
        }
        instance.mLastKnownLocation = mostRecentValidLocation;
        instance.mLocationLastUpdatedMillis = SystemClock.elapsedRealtime();
        return mostRecentValidLocation;
    }

    @Nullable
    @VisibleForTesting
    static Location getLocationFromProvider(@NonNull Context context, @NonNull ValidLocationProvider validLocationProvider) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(validLocationProvider);
        if (!MoPub.canCollectPersonalInformation() || !validLocationProvider.hasRequiredPermissions(context)) {
            return null;
        }
        try {
            return ((LocationManager) context.getSystemService("location")).getLastKnownLocation(validLocationProvider.toString());
        } catch (SecurityException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to retrieve location from ");
            sb.append(validLocationProvider.toString());
            sb.append(" provider: access appears to be disabled.");
            MoPubLog.d(sb.toString());
            return null;
        } catch (IllegalArgumentException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to retrieve location: device has no ");
            sb2.append(validLocationProvider.toString());
            sb2.append(" location provider.");
            MoPubLog.d(sb2.toString());
            return null;
        } catch (NullPointerException unused3) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Failed to retrieve location: device has no ");
            sb3.append(validLocationProvider.toString());
            sb3.append(" location provider.");
            MoPubLog.d(sb3.toString());
            return null;
        }
    }

    @Nullable
    @VisibleForTesting
    static Location getMostRecentValidLocation(@Nullable Location location, @Nullable Location location2) {
        if (location == null) {
            return location2;
        }
        if (location2 == null) {
            return location;
        }
        if (location.getTime() <= location2.getTime()) {
            location = location2;
        }
        return location;
    }

    @VisibleForTesting
    static void truncateLocationLatLon(@Nullable Location location, int i) {
        if (location != null && i >= 0) {
            location.setLatitude(BigDecimal.valueOf(location.getLatitude()).setScale(i, 5).doubleValue());
            location.setLongitude(BigDecimal.valueOf(location.getLongitude()).setScale(i, 5).doubleValue());
        }
    }

    private static boolean isLocationFreshEnough() {
        LocationService instance = getInstance();
        boolean z = false;
        if (instance.mLastKnownLocation == null) {
            return false;
        }
        if (SystemClock.elapsedRealtime() - instance.mLocationLastUpdatedMillis <= MoPub.getMinimumLocationRefreshTimeMillis()) {
            z = true;
        }
        return z;
    }

    @Deprecated
    @VisibleForTesting
    public static void clearLastKnownLocation() {
        getInstance().mLastKnownLocation = null;
    }
}
