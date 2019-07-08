package com.myfitnesspal.shared.service.location;

import com.myfitnesspal.shared.model.UserLocation;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/shared/service/location/LocationService;", "", "getUserLocation", "Lcom/myfitnesspal/shared/model/UserLocation;", "setUserLocation", "", "userLocation", "updateUserLocation", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: LocationService.kt */
public interface LocationService {
    @NotNull
    UserLocation getUserLocation();

    void setUserLocation(@NotNull UserLocation userLocation);

    void updateUserLocation();
}
