package com.myfitnesspal.feature.restaurantlogging.service;

import android.location.Location;
import com.myfitnesspal.feature.restaurantlogging.model.MfpLocation;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenu;
import com.myfitnesspal.feature.restaurantlogging.model.RequestedVenueLocation;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.model.Venue.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.restaurantlogging.model.VenuesRequestData;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.RestaurantLogging;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.UserLocation;
import com.myfitnesspal.shared.service.location.LocationService;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Provider;

public class VenueServiceImpl implements VenueService {
    private final Provider<MfpV2Api> apiProvider;
    private final Lazy<LocationService> locationService;

    public VenueServiceImpl(Provider<MfpV2Api> provider, Lazy<LocationService> lazy) {
        this.apiProvider = provider;
        this.locationService = lazy;
    }

    public List<Venue> getVenuesForLocationAndRadius(VenuesRequestData venuesRequestData) throws ApiException {
        double d;
        double d2;
        ArrayList arrayList = new ArrayList();
        if (venuesRequestData.hasCustomLocation()) {
            RequestedVenueLocation requestedVenueLocation = venuesRequestData.getRequestedVenueLocation();
            d2 = requestedVenueLocation.getLatitude();
            d = requestedVenueLocation.getLongitude();
        } else {
            Location userLocation = venuesRequestData.getUserLocation();
            d2 = userLocation.getLatitude();
            d = userLocation.getLongitude();
        }
        addKeyValueToList(arrayList, "latitude", Double.toString(d2));
        addKeyValueToList(arrayList, "longitude", Double.toString(d));
        ((LocationService) this.locationService.get()).setUserLocation(new UserLocation(String.valueOf(d2), String.valueOf(d)));
        if (venuesRequestData.hasQuery()) {
            addKeyValueToList(arrayList, "query", venuesRequestData.getQuery());
        }
        addKeyValueToList(arrayList, RestaurantLogging.RADIUS, Float.toString(venuesRequestData.getRadius()));
        Object[] objArr = new Object[arrayList.size()];
        arrayList.toArray(objArr);
        List<Venue> items = ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.VENUES, objArr)).getItems();
        calculateAndSetDistanceFromUserLocation(items, venuesRequestData.getUserLocation());
        return items;
    }

    public List<MfpMenu> getMenusForVenueId(String str) throws ApiException {
        return ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(MfpMenu.API_RESPONSE_MAPPER.class)).get(String.format(Uri.MENUS, new Object[]{str}))).getItems();
    }

    public Venue getVenueById(String str) throws ApiException {
        return (Venue) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(String.format(Uri.VENUE, new Object[]{str}))).getItem();
    }

    private void calculateAndSetDistanceFromUserLocation(List<Venue> list, Location location) {
        for (Venue location2 : list) {
            MfpLocation location3 = location2.getLocation();
            if (location3 != null) {
                double latitude = location3.getLatitude();
                double longitude = location3.getLongitude();
                if (location != null) {
                    float[] fArr = new float[1];
                    Location.distanceBetween(location.getLatitude(), location.getLongitude(), latitude, longitude, fArr);
                    location3.setDistanceFromUser(fArr[0]);
                }
            }
        }
        Collections.sort(list);
    }

    private void addKeyValueToList(List<String> list, String str, String str2) {
        list.add(str);
        list.add(str2);
    }
}
