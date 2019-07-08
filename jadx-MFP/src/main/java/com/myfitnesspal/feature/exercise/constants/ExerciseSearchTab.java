package com.myfitnesspal.feature.exercise.constants;

import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.ListType;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;

public enum ExerciseSearchTab {
    RECENT(6005, R.string.mostUsed, "frequent", R.string.mostUsedExercisesTxt),
    MY_EXERCISES(SearchTabs.TAB_MY_EXERCISES, R.string.myExercises, ListType.MY_EXERCISES, R.string.myExercisesTxt),
    BROWSE_ALL(6007, R.string.browseAll, "search", -1);
    
    private final String analyticsTabName;
    private final int emptyListResId;
    private final int labelResId;
    private final int tabId;

    private ExerciseSearchTab(int i, int i2, String str, int i3) {
        this.tabId = i;
        this.labelResId = i2;
        this.analyticsTabName = str;
        this.emptyListResId = i3;
    }

    public int getTabId() {
        return this.tabId;
    }

    public int getLabelResId() {
        return this.labelResId;
    }

    public String getAnalyticsTabName() {
        return this.analyticsTabName;
    }

    public int getEmptyListResId() {
        return this.emptyListResId;
    }
}
