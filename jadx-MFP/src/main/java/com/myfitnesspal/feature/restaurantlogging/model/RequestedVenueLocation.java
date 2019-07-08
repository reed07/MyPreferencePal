package com.myfitnesspal.feature.restaurantlogging.model;

public class RequestedVenueLocation {
    private final double latitude;
    private final double longitude;
    private final String searchQuery;

    public RequestedVenueLocation(String str, double d, double d2) {
        this.searchQuery = str;
        this.latitude = d;
        this.longitude = d2;
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
