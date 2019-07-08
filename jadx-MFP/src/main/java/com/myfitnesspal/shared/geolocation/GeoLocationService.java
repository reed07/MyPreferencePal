package com.myfitnesspal.shared.geolocation;

import com.uacf.core.util.Function0;

public interface GeoLocationService {
    String getCountryCode();

    String getLocaleCode();

    void refresh(Function0 function0);

    void refreshSync();

    void reset();
}
