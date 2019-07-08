package com.myfitnesspal.feature.restaurantlogging.service;

import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatch;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMultiAddMenuItem;
import com.myfitnesspal.shared.api.ApiException;
import java.util.List;

public interface MenuService {
    MfpMenuItemMatch createMenuItemMatch(MfpMenuItemMatch mfpMenuItemMatch, String str, String str2) throws ApiException;

    List<MfpMultiAddMenuItem> createMenuItemMatches(List<MfpMenuItem> list) throws ApiException;

    List<MfpMenuItem> getMenuItemsForMenuIdAndItemIds(String str, List<String> list) throws ApiException;
}
