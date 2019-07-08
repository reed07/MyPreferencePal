package com.google.android.gms.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

@Deprecated
public interface FusedLocationProviderApi {
    @Deprecated
    public static final String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
    public static final String KEY_MOCK_LOCATION = "mockLocation";

    PendingResult<Status> flushLocations(GoogleApiClient googleApiClient);

    @RequiresPermission
    Location getLastLocation(GoogleApiClient googleApiClient);

    @RequiresPermission
    LocationAvailability getLocationAvailability(GoogleApiClient googleApiClient);

    PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, LocationCallback locationCallback);

    PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, LocationListener locationListener);

    @RequiresPermission
    PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, PendingIntent pendingIntent);

    @RequiresPermission
    PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationCallback locationCallback, Looper looper);

    @RequiresPermission
    PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener);

    @RequiresPermission
    PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper);

    @RequiresPermission
    PendingResult<Status> setMockLocation(GoogleApiClient googleApiClient, Location location);

    @RequiresPermission
    PendingResult<Status> setMockMode(GoogleApiClient googleApiClient, boolean z);
}
