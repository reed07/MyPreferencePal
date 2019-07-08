package com.myfitnesspal.shared.service.ads;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdsAnalyticsHelperImpl implements AdsAnalyticsHelper {
    private static final String TIME_ATTRIBUTE = "time";
    private final Lazy<MfpAnalyticsService> analyticsService;
    private final Map<String, Long> timestampMap = new HashMap();

    private static final class AdsConsentsValues {
        public static final String FALSE = "false";
        public static final String TRUE = "true";

        private AdsConsentsValues() {
        }
    }

    public AdsAnalyticsHelperImpl(Lazy<MfpAnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportAdEvent(String str, String str2, Map<String, String> map) {
        putScreenTagToAttributes(map, str2);
        checkForWaterEventAndAddUnit(str, str2, map);
        ((MfpAnalyticsService) this.analyticsService.get()).reportEvent(str, (Map) map);
    }

    public void reportAdEvent(String str, String str2, String str3, Map<String, String> map) {
        try {
            putScreenTagToAttributes(map, str2);
            map.put(Attributes.LOCAL_TIME, new Date().toString());
            map.put(Attributes.AD_VIEW_TIME_DELTA, getTimeDeltaAndUpdate(str2, str));
            map.put("ad_unit_id", str3);
            checkForWaterEventAndAddUnit(str, str2, map);
            ((MfpAnalyticsService) this.analyticsService.get()).reportEvent(str, (Map) map);
        } catch (Exception e) {
            Ln.e("failed to report ad event for DFP banner ad", e);
        }
    }

    public void reportAdEvent(String str, String str2, long j, Map<String, String> map) {
        putScreenTagToAttributes(map, str2);
        map.put("time", Long.toString(j / 1000));
        ((MfpAnalyticsService) this.analyticsService.get()).reportEvent(str, (Map) map);
    }

    public void reportAdLimitingEvent(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", z ? "true" : "false");
        ((MfpAnalyticsService) this.analyticsService.get()).reportEvent(Events.LIMIT_AD_TRACKING, (Map) hashMap);
    }

    private void checkForWaterEventAndAddUnit(String str, String str2, Map<String, String> map) {
        if (Strings.equals(str, Events.WATER_AD_DISPLAYED) || Strings.equals(str, Events.WATER_AD_REQUESTED) || Strings.equals(str, Events.WATER_AD_CLICKED)) {
            map.put("unit", Strings.equals(str2, "Diary") ? "diary" : Attributes.WATER_TRACKING_PIXEL_UNIT);
        }
    }

    private void putScreenTagToAttributes(Map<String, String> map, String str) {
        map.put("screen", str);
    }

    private synchronized String getTimeDeltaAndUpdate(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        String format = String.format("%s-%s", new Object[]{str, str2});
        if (!this.timestampMap.containsKey(format)) {
            this.timestampMap.put(format, Long.valueOf(currentTimeMillis));
            return String.valueOf(0);
        }
        long longValue = currentTimeMillis - ((Long) this.timestampMap.get(format)).longValue();
        this.timestampMap.put(format, Long.valueOf(currentTimeMillis));
        return String.valueOf(longValue);
    }
}
