package com.myfitnesspal.shared.model;

import android.support.annotation.NonNull;
import com.google.android.gms.ads.AdSize;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.uacf.core.util.Strings;

public enum AdType {
    MEDIUM(R.dimen.medium_ads_height, AdSize.MEDIUM_RECTANGLE, R.dimen.medium_ads_width, R.dimen.medium_ads_height, Events.MEDIUM_AD_DISPLAYED, Events.MEDIUM_AD_CLICKED, Events.MEDIUM_AD_REQUESTED),
    BANNER(R.dimen.banner_ads_height, AdSize.BANNER, R.dimen.banner_ads_width, R.dimen.banner_ads_height, Events.BANNER_AD_DISPLAYED, Events.BANNER_AD_CLICKED, Events.BANNER_AD_REQUESTED),
    WATER(R.dimen.sponsored_water_section_header_ad_height, new AdSize(RequestCodes.SEARCH_MATCH, 32), R.dimen.sponsored_water_section_header_ad_width, R.dimen.sponsored_water_section_header_ad_height, Events.WATER_AD_DISPLAYED, Events.WATER_AD_CLICKED, Events.WATER_AD_REQUESTED),
    ONE_PIXEL(R.dimen.one, new AdSize(1, 1), R.dimen.one, R.dimen.one, Events.WATER_AD_DISPLAYED, Events.WATER_AD_CLICKED, Events.WATER_AD_REQUESTED);
    
    private int adContainerHeightResId;
    @NonNull
    private AdSize adSize;
    private int adViewHeightResId;
    private int adViewWidthResId;
    @NonNull
    private String displayAnalyticsEventName;
    @NonNull
    private String openAnalyticsEventName;
    @NonNull
    private String requestedAdAnalyticsEventName;

    private AdType(int i, AdSize adSize2, int i2, @NonNull int i3, String str, String str2, @NonNull String str3) {
        this.adContainerHeightResId = i;
        this.adSize = adSize2;
        this.adViewWidthResId = i2;
        this.adViewHeightResId = i3;
        this.displayAnalyticsEventName = str;
        this.openAnalyticsEventName = str2;
        this.requestedAdAnalyticsEventName = str3;
    }

    public int getAdContainerHeightResId() {
        return this.adContainerHeightResId;
    }

    @NonNull
    public AdSize getAdSize() {
        return this.adSize;
    }

    public int getAdViewWidthResId() {
        return this.adViewWidthResId;
    }

    public int getAdViewHeightResId() {
        return this.adViewHeightResId;
    }

    @NonNull
    public String getDisplayAnalyticsEventName() {
        return this.displayAnalyticsEventName;
    }

    @NonNull
    public String getOpenAnalyticsEventName() {
        return this.openAnalyticsEventName;
    }

    @NonNull
    public String getRequestedAdAnalyticsEventName() {
        return this.requestedAdAnalyticsEventName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name());
        sb.append("[adSize = ");
        sb.append(Strings.toString(this.adSize));
        sb.append(" displayAnalyticsEventName = ");
        sb.append(this.displayAnalyticsEventName);
        sb.append(" openAnalyticsEventName = ");
        sb.append(this.openAnalyticsEventName);
        sb.append(" requestedAdAnalyticsEventName = ");
        sb.append(this.requestedAdAnalyticsEventName);
        sb.append("]");
        return sb.toString();
    }
}
