package com.myfitnesspal.shared.service.location;

import android.annotation.SuppressLint;
import android.content.Context;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.myfitnesspal.shared.model.UserLocation;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/shared/service/location/LocationServiceImpl;", "Lcom/myfitnesspal/shared/service/location/LocationService;", "context", "Landroid/content/Context;", "localSettingsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "(Landroid/content/Context;Ldagger/Lazy;)V", "getContext", "()Landroid/content/Context;", "getLocalSettingsService", "()Ldagger/Lazy;", "getUserLocation", "Lcom/myfitnesspal/shared/model/UserLocation;", "setUserLocation", "", "userLocation", "updateUserLocation", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: LocationServiceImpl.kt */
public final class LocationServiceImpl implements LocationService {
    @NotNull
    private final Context context;
    @NotNull
    private final Lazy<LocalSettingsService> localSettingsService;

    public LocationServiceImpl(@NotNull Context context2, @NotNull Lazy<LocalSettingsService> lazy) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(lazy, "localSettingsService");
        this.context = context2;
        this.localSettingsService = lazy;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final Lazy<LocalSettingsService> getLocalSettingsService() {
        return this.localSettingsService;
    }

    @SuppressLint({"MissingPermission"})
    public void updateUserLocation() {
        Object obj = this.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "localSettingsService.get()");
        if (((LocalSettingsService) obj).isUserAcceptedLocationPermission()) {
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.context);
            Intrinsics.checkExpressionValueIsNotNull(fusedLocationProviderClient, "LocationServices.getFuse…onProviderClient(context)");
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new LocationServiceImpl$updateUserLocation$$inlined$with$lambda$1(this));
        }
    }

    @NotNull
    public UserLocation getUserLocation() {
        Object obj = this.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "localSettingsService.get()");
        UserLocation userLocation = ((LocalSettingsService) obj).getUserLocation();
        Intrinsics.checkExpressionValueIsNotNull(userLocation, "localSettingsService.get().userLocation");
        return userLocation;
    }

    public void setUserLocation(@NotNull UserLocation userLocation) {
        Intrinsics.checkParameterIsNotNull(userLocation, "userLocation");
        Object obj = this.localSettingsService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "localSettingsService.get()");
        ((LocalSettingsService) obj).setUserLocation(userLocation);
    }
}
