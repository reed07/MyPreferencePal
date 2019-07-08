package com.myfitnesspal.shared.service.analytics;

import java.util.List;
import java.util.Map;

public interface ActionTrackingService {
    void addToBreadcrumbs(String str, String str2, String str3);

    ActionTrackingService appendToEvent(String str, String str2, String str3);

    ActionTrackingService appendToEvent(String str, Map<String, String> map);

    ActionTrackingService appendToEventAndReport(String str, Map<String, String> map);

    void deleteBreadcrumbs(String str);

    ActionTrackingService deleteEvent(String str);

    List<String> getBreadCrumbs(String str);

    String getTrackingDataForEvent(String str, String str2);

    Map<String, String> getTrackingEvents(String str);

    void registerAndReportEvent(String str);

    void registerAppendAndReportEvent(String str, Map<String, String> map);

    ActionTrackingService registerEvent(String str);

    ActionTrackingService registerEvent(String str, String str2, String str3);

    ActionTrackingService registerEvent(String str, Map<String, String> map);

    void reportEventToAnalytics(String str);

    void reportEventToAnalytics(String str, String str2);

    void reportEventToAnalytics(String str, String str2, boolean z);

    void reportEventToAnalytics(String str, String str2, boolean z, String... strArr);

    void reportEventToAnalytics(String str, boolean z);
}
