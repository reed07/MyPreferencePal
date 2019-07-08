package com.myfitnesspal.feature.exercise.service;

import com.myfitnesspal.feature.exercise.constants.ExerciseSearchTab;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;

public class ExerciseAnalyticsHelper {
    private static final String ATTR_CALORIES = "calories";
    private static final String ATTR_DURATION = "duration";
    private static final String ATTR_INDEX = "index";
    private static final String ATTR_QUERY = "query";
    private static final String ATTR_RESULTS = "results";
    private static final String ATTR_SOURCE = "source";
    private static final String ATTR_TYPE = "type";
    private static final String EVENT_ADD_EXERCISE_ENTRY_DISPLAYED = "add_exercise_entry_displayed";
    private static final String EVENT_ADD_EXERCISE_SCREEN_DISPLAYED = "add_exercise_screen_displayed";
    private static final String EVENT_ADD_EXERCISE_TAPPED = "add_exercise_tapped";
    private static final String EVENT_EXERCISE_SEARCH = "exercise_search";
    private static final String EVENT_EXERCISE_SEARCH_FAILED = "exercise_search_failed";
    private static final String EVENT_EXERCISE_SEARCH_RESULT_SELECTED = "exercise_search_result_selected";
    private static final String EVENT_EXERCISE_SEARCH_TAPPED = "exercise_search_tapped";
    private static final String EVENT_NEW_EXERCISE_CREATED = "new_exercise_created";
    private static final String EVENT_NEW_EXERCISE_SCREEN_DISPLAYED = "new_exercise_screen_displayed";
    private static final String EXERCISE_CARDIO = "cardio";
    private static final String EXERCISE_STRENGTH = "strength";
    private static final String LOCAL_SEARCH = "local_search";
    private static final String ONLINE_SEARCH = "online_search";
    public static final String SOURCE_DIARY = "diary";
    public static final String SOURCE_FAB = "fab";
    private Lazy<AnalyticsService> analyticsService;

    private String getExerciseTypeString(int i) {
        return i == 0 ? "cardio" : "strength";
    }

    private String getSearchSource(boolean z) {
        return z ? "online_search" : "local_search";
    }

    public ExerciseAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportAddExerciseTapped(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_ADD_EXERCISE_TAPPED, MapUtil.createMap("source", str));
    }

    public void reportAddExerciseScreenDisplayed(String str, int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_ADD_EXERCISE_SCREEN_DISPLAYED, MapUtil.createMap("source", str, "type", getExerciseTypeString(i)));
    }

    public void reportAddExerciseEntryDisplayed(String str, boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_ADD_EXERCISE_ENTRY_DISPLAYED, MapUtil.createMap("source", getSearchSource(z), "type", str));
    }

    public void reportExerciseSearchTapped(ExerciseSearchTab exerciseSearchTab) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_EXERCISE_SEARCH_TAPPED, MapUtil.createMap("source", exerciseSearchTab.getAnalyticsTabName()));
    }

    public void reportExericseSearch(String str, int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_EXERCISE_SEARCH, MapUtil.createMap("query", str, ATTR_RESULTS, String.valueOf(i)));
    }

    public void reportExericseSearchFailed(String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_EXERCISE_SEARCH_FAILED, MapUtil.createMap("query", str));
    }

    public void reportExerciseSearchResultSelected(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_EXERCISE_SEARCH_RESULT_SELECTED, MapUtil.createMap("index", String.valueOf(i)));
    }

    public void reportNewExerciseScreenDisplayed(int i, String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_NEW_EXERCISE_SCREEN_DISPLAYED, MapUtil.createMap("type", getExerciseTypeString(i), "source", str));
    }

    public void reportNewCardioExerciseCreated(float f, int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_NEW_EXERCISE_CREATED, MapUtil.createMap("type", "cardio", "calories", String.valueOf(f), "duration", String.valueOf(i)));
    }

    public void reportNewStrengthExerciseCreated() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_NEW_EXERCISE_CREATED, MapUtil.createMap("type", "strength"));
    }
}
