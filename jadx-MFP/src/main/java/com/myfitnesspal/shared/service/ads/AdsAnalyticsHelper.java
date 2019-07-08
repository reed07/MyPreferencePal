package com.myfitnesspal.shared.service.ads;

import java.util.Map;

public interface AdsAnalyticsHelper {
    void reportAdEvent(String str, String str2, long j, Map<String, String> map);

    void reportAdEvent(String str, String str2, String str3, Map<String, String> map);

    void reportAdEvent(String str, String str2, Map<String, String> map);

    void reportAdLimitingEvent(boolean z);
}
