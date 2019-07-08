package com.myfitnesspal.shared.service.analytics;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class ActionTrackingServiceImpl implements ActionTrackingService {
    private static final Map<String, Map<String, String>> TRACKED_EVENT_ATTRIBUTES_MAP = new HashMap();
    private static final Map<String, List<String>> TRACKED_FLOW_KEY_BREADCRUMBS_MAP = new HashMap();
    private final Lazy<AnalyticsService> analyticsService;

    @Inject
    public ActionTrackingServiceImpl(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public ActionTrackingService appendToEvent(String str, Map<String, String> map) {
        Map attributesFromEvent = getAttributesFromEvent(str);
        attributesFromEvent.putAll(map);
        updateEventAttributesMap(str, attributesFromEvent);
        return this;
    }

    public ActionTrackingService appendToEventAndReport(String str, Map<String, String> map) {
        appendToEvent(str, map);
        reportEventToAnalytics(str, str, false);
        return this;
    }

    public ActionTrackingService appendToEvent(String str, String str2, String str3) {
        appendToEvent(str, MapUtil.createMap(str2, str3));
        return this;
    }

    public ActionTrackingService deleteEvent(String str) {
        removeFromEventAttributesMap(str);
        return this;
    }

    public ActionTrackingService registerEvent(String str) {
        addToEventAttributesMap(str);
        return this;
    }

    public ActionTrackingService registerEvent(String str, String str2, String str3) {
        registerEvent(str);
        appendToEvent(str, str2, str3);
        return this;
    }

    public ActionTrackingService registerEvent(String str, Map<String, String> map) {
        registerEvent(str);
        appendToEvent(str, map);
        return this;
    }

    public void reportEventToAnalytics(String str) {
        reportEventToAnalytics(str, str, false);
    }

    public void reportEventToAnalytics(String str, boolean z) {
        reportEventToAnalytics(str, str, z);
    }

    public void reportEventToAnalytics(String str, String str2) {
        reportEventToAnalytics(str, str2, false);
    }

    public void reportEventToAnalytics(String str, String str2, boolean z) {
        reportEventToAnalytics(str, str2, z, null);
    }

    public void reportEventToAnalytics(String str, String str2, boolean z, String... strArr) {
        Map attributesFromEvent = getAttributesFromEvent(str2);
        if (CollectionUtils.notEmpty(attributesFromEvent)) {
            if (strArr != null) {
                Builder builder = new Builder();
                for (String str3 : strArr) {
                    if (attributesFromEvent.containsKey(str3)) {
                        builder.put(str3, attributesFromEvent.get(str3));
                    }
                }
                attributesFromEvent = builder.build();
            }
            ((AnalyticsService) this.analyticsService.get()).reportEvent(str, attributesFromEvent);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(" ReportToAnalytics failed for :");
            sb.append(str2);
            Ln.e(sb.toString(), new Object[0]);
        }
        if (z) {
            removeFromEventAttributesMap(str2);
        }
    }

    public void registerAndReportEvent(String str) {
        registerEvent(str);
        reportEventToAnalytics(str);
    }

    public void registerAppendAndReportEvent(String str, Map<String, String> map) {
        registerEvent(str);
        appendToEventAndReport(str, map);
    }

    public void addToBreadcrumbs(String str, String str2, String str3) {
        insertBreadcrumb(getBreadcrumbFlowKey(str), str2, str3);
    }

    public List<String> getBreadCrumbs(String str) {
        String str2 = Attributes.BREADCRUMB;
        if (Strings.notEmpty(str)) {
            str2 = getBreadcrumbFlowKey(str);
        }
        return new ArrayList(getCurrentBreadcrumbsFromFlowKey(str2));
    }

    public void deleteBreadcrumbs(String str) {
        String str2 = Attributes.BREADCRUMB;
        if (Strings.notEmpty(str)) {
            str2 = getBreadcrumbFlowKey(str);
        }
        removeFromFlowKeyBreadcrumbsMap(str2);
    }

    public Map<String, String> getTrackingEvents(String str) {
        return new HashMap(getAttributesFromEvent(str));
    }

    public String getTrackingDataForEvent(String str, String str2) {
        return (String) getAttributesFromEvent(str).get(str2);
    }

    private void addToEventAttributesMap(String str) {
        TRACKED_EVENT_ATTRIBUTES_MAP.put(str, new HashMap());
    }

    private void removeFromEventAttributesMap(String str) {
        TRACKED_EVENT_ATTRIBUTES_MAP.remove(str);
    }

    private Map<String, String> getAttributesFromEvent(String str) {
        Map<String, String> map = (Map) TRACKED_EVENT_ATTRIBUTES_MAP.get(str);
        return map == null ? new HashMap() : map;
    }

    private void updateEventAttributesMap(String str, Map<String, String> map) {
        TRACKED_EVENT_ATTRIBUTES_MAP.put(str, map);
    }

    private String getBreadcrumbFlowKey(String str) {
        return String.format("%s.%s", new Object[]{Attributes.BREADCRUMB, str});
    }

    private void removeFromFlowKeyBreadcrumbsMap(String str) {
        TRACKED_FLOW_KEY_BREADCRUMBS_MAP.remove(str);
    }

    private List<String> getCurrentBreadcrumbsFromFlowKey(String str) {
        List<String> list = (List) TRACKED_FLOW_KEY_BREADCRUMBS_MAP.get(str);
        return list == null ? new ArrayList() : list;
    }

    private void updateFlowKeyBreadcrumbsMap(String str, List<String> list) {
        TRACKED_FLOW_KEY_BREADCRUMBS_MAP.put(str, list);
    }

    private boolean isCurrentBreadcrumbLatest(List<String> list, String str) {
        if (!CollectionUtils.notEmpty((Collection<?>) list) || !((String) list.get(list.size() - 1)).equals(str)) {
            return false;
        }
        return true;
    }

    private void insertBreadcrumb(String str, String str2, String str3) {
        List currentBreadcrumbsFromFlowKey = getCurrentBreadcrumbsFromFlowKey(str);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str3);
        String sb2 = sb.toString();
        if (currentBreadcrumbsFromFlowKey == null) {
            currentBreadcrumbsFromFlowKey = new ArrayList();
        }
        if (!isCurrentBreadcrumbLatest(currentBreadcrumbsFromFlowKey, sb2)) {
            currentBreadcrumbsFromFlowKey.add(sb2);
            updateFlowKeyBreadcrumbsMap(str, currentBreadcrumbsFromFlowKey);
        }
    }
}
