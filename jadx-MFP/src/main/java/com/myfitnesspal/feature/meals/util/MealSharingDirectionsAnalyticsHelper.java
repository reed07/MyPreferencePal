package com.myfitnesspal.feature.meals.util;

import android.os.Bundle;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;

public class MealSharingDirectionsAnalyticsHelper {
    private static final String EVENT_MEAL_DIRECTIONS_PROMPT_CLOSED = "meal_directions_prompt_closed";
    private static final String EVENT_MEAL_DIRECTIONS_PROMPT_DISPLAYED = "meal_directions_prompt_displayed";
    private static final String EVENT_MEAL_DIRECTIONS_PROMPT_TAPPED = "meal_directions_prompt_tapped";
    private static final String EVENT_MEAL_SHARING_DIRECTIONS_PROMPT_DISPLAYED = "meal_sharing_directions_prompt_displayed";
    private static final String EVENT_MEAL_SHARING_DIRECTIONS_PROMPT_TAPPED = "meal_sharing_directions_prompt_tapped";
    private static final String EVENT_MEAL_SHARING_PROMPT_DISPLAYED = "meal_sharing_prompt_displayed";
    private final Lazy<AnalyticsService> analyticsService;

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
        }
    }

    public MealSharingDirectionsAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportFoodEditorNotesPromptDisplayed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_DIRECTIONS_PROMPT_DISPLAYED);
    }

    public void reportFoodEditorNotesPromptTapped() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_DIRECTIONS_PROMPT_TAPPED);
    }

    public void reportFoodEditorNotesClosed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_DIRECTIONS_PROMPT_CLOSED);
    }

    public void reportFoodEditorSharingPromptDisplayed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_SHARING_PROMPT_DISPLAYED);
    }

    public void reportSharingPromptDisplayed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_SHARING_DIRECTIONS_PROMPT_DISPLAYED);
    }

    public void reportSharingPromptTapped() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_MEAL_SHARING_DIRECTIONS_PROMPT_TAPPED);
    }
}
