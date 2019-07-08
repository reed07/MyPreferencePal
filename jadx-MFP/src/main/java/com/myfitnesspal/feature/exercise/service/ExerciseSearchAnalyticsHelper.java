package com.myfitnesspal.feature.exercise.service;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.UUID;

public class ExerciseSearchAnalyticsHelper {
    public static final String EVENT_PAGE_TYPE_CARDIO = "cardio";
    public static final String EVENT_PAGE_TYPE_STRENGTH = "strength";
    private Lazy<ActionTrackingService> actionTrackingService;

    public ExerciseSearchAnalyticsHelper(Lazy<ActionTrackingService> lazy) {
        this.actionTrackingService = lazy;
    }

    public void updateExerciseSearchBreadcrumb(String str) {
        ((ActionTrackingService) this.actionTrackingService.get()).deleteBreadcrumbs(Attributes.EXERCISE_SEARCH_BREADCRUMB);
        ((ActionTrackingService) this.actionTrackingService.get()).addToBreadcrumbs(Attributes.EXERCISE_SEARCH_BREADCRUMB, str, "");
    }

    public void initOnlineSearchSummaryForAnalytics(int i) {
        String trackingDataForEvent = ((ActionTrackingService) this.actionTrackingService.get()).getTrackingDataForEvent("channel", "referrer");
        HashMap hashMap = new HashMap();
        hashMap.put("channel", trackingDataForEvent);
        hashMap.put("source", "online_search");
        hashMap.put(Attributes.LOGGED, "no");
        hashMap.put(Attributes.SELECTED_COUNT, Strings.toString(Integer.valueOf(0)));
        hashMap.put(Attributes.FIRST_SELECTION_INDEX, "none");
        hashMap.put(Attributes.LAST_SELECTION_INDEX, "none");
        hashMap.put(Attributes.RESULT_COUNT, "none");
        if (i == 1) {
            hashMap.put(Attributes.CATEGORY, "strength");
        } else if (i == 0) {
            hashMap.put(Attributes.CATEGORY, "cardio");
        }
        hashMap.put("flow_id", UUID.randomUUID().toString());
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, hashMap);
    }

    public void appendPropertiesToOnlineSearchSummary(int i) {
        int selectedCountFromOnlineSearchSummary = getSelectedCountFromOnlineSearchSummary();
        appendSelectedCountToOnlineSearchSummary(selectedCountFromOnlineSearchSummary + 1);
        appendLastSelectionIndexToOnlineSearchSummary(i);
        if (selectedCountFromOnlineSearchSummary == 0) {
            appendFirstSelectionIndexToOnlineSearchSummary(i);
        }
    }

    public void logEventsAndReportToAnalytics() {
        ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.ONLINE_SEARCH_SUMMARY, Events.ONLINE_SEARCH_SUMMARY);
    }

    public void logSearchResultSize(int i) {
        String str = i == 0 ? "0" : i < 5 ? Attributes.RESULT_COUNT_1_4 : i < 10 ? Attributes.RESULT_COUNT_5_9 : i < 25 ? Attributes.RESULT_COUNT_10_24 : Attributes.RESULT_COUNT_25;
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.RESULT_COUNT, str);
    }

    public void appendLastSearchType(boolean z) {
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent("is_last_pressed_search", "is_last_pressed_search", Strings.toString(Boolean.valueOf(z)));
    }

    private void appendSelectedCountToOnlineSearchSummary(int i) {
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.SELECTED_COUNT, Strings.toString(Integer.valueOf(i)));
    }

    private int getSelectedCountFromOnlineSearchSummary() {
        String str = (String) ((ActionTrackingService) this.actionTrackingService.get()).getTrackingEvents(Events.ONLINE_SEARCH_SUMMARY).get(Attributes.SELECTED_COUNT);
        if (Strings.notEmpty(str)) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    private void appendLastSelectionIndexToOnlineSearchSummary(int i) {
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.LAST_SELECTION_INDEX, Strings.toString(Integer.valueOf(i)));
    }

    private void appendFirstSelectionIndexToOnlineSearchSummary(int i) {
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.FIRST_SELECTION_INDEX, Strings.toString(Integer.valueOf(i)));
    }
}
