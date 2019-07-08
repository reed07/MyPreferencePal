package com.myfitnesspal.feature.externalsync.impl.googlefit.util;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

public class ReportAnalyticsDebouncer extends Debouncer<String> {
    private static final int DELAY_MILLIS = 2000;
    private final Lazy<AnalyticsService> analyticsService;
    private final String eventName;
    private final Map<String, Integer> sourceToCountMap = new HashMap();

    public ReportAnalyticsDebouncer(Lazy<AnalyticsService> lazy, String str) {
        super(2000);
        this.analyticsService = lazy;
        this.eventName = str;
    }

    /* access modifiers changed from: protected */
    public synchronized void onCalled(String str) {
        if (!this.sourceToCountMap.containsKey(str)) {
            this.sourceToCountMap.put(str, Integer.valueOf(1));
        } else {
            this.sourceToCountMap.put(str, Integer.valueOf(((Integer) this.sourceToCountMap.get(str)).intValue() + 1));
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void onDebounced(String str) {
        for (String str2 : this.sourceToCountMap.keySet()) {
            Integer num = (Integer) this.sourceToCountMap.get(str2);
            AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
            String str3 = "google_fit";
            String[] strArr = new String[6];
            int i = 0;
            strArr[0] = Attributes.DATA_TYPE;
            strArr[1] = this.eventName;
            strArr[2] = Attributes.DATA_SOURCE;
            strArr[3] = str2;
            strArr[4] = "count";
            if (num != null) {
                i = num.intValue();
            }
            strArr[5] = String.valueOf(i);
            analyticsService2.reportEvent(str3, MapUtil.createMap(strArr));
        }
        this.sourceToCountMap.clear();
    }
}
