package com.myfitnesspal.feature.restaurantlogging.service;

import com.myfitnesspal.feature.restaurantlogging.model.MfpMenu;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.model.VenuesRequestData;
import com.myfitnesspal.shared.api.ApiException;
import java.util.List;

public interface VenueService {
    List<MfpMenu> getMenusForVenueId(String str) throws ApiException;

    Venue getVenueById(String str) throws ApiException;

    List<Venue> getVenuesForLocationAndRadius(VenuesRequestData venuesRequestData) throws ApiException;
}
