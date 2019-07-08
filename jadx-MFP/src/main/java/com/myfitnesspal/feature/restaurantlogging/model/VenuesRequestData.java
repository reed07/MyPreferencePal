package com.myfitnesspal.feature.restaurantlogging.model;

import android.location.Location;
import com.uacf.core.util.Strings;

public class VenuesRequestData {
    private final String query;
    private final float radius;
    private final RequestedVenueLocation requestedVenueLocation;
    private final Location userLocation;

    public VenuesRequestData(Location location, String str, float f) {
        this(location, str, f, null);
    }

    public VenuesRequestData(Location location, String str, float f, RequestedVenueLocation requestedVenueLocation2) {
        this.requestedVenueLocation = requestedVenueLocation2;
        this.userLocation = location;
        this.query = str;
        this.radius = f;
    }

    public Location getUserLocation() {
        return this.userLocation;
    }

    public String getQuery() {
        return this.query;
    }

    public float getRadius() {
        return this.radius;
    }

    public RequestedVenueLocation getRequestedVenueLocation() {
        return this.requestedVenueLocation;
    }

    public boolean hasCustomLocation() {
        return this.requestedVenueLocation != null;
    }

    public boolean hasQuery() {
        return Strings.notEmpty(this.query);
    }
}
