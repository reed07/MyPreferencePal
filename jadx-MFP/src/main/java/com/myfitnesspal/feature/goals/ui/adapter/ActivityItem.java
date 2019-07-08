package com.myfitnesspal.feature.goals.ui.adapter;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Exercise.ActivityLevel;
import com.uacf.core.util.Strings;

public class ActivityItem {
    private String description;
    private String label;
    private String name;
    private boolean state;

    public ActivityItem(String str, String str2, String str3, boolean z) {
        this.name = str;
        this.label = str2;
        this.description = str3;
        this.state = z;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getState() {
        return this.state;
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }

    public void setState(boolean z) {
        this.state = z;
    }

    public static String getActivityLabelFromName(Context context, String str) {
        if (Strings.equals(str, ActivityLevel.SEDENTARY)) {
            return context.getResources().getString(R.string.not_very_active);
        }
        if (Strings.equals(str, ActivityLevel.LIGHTLY_ACTIVE)) {
            return context.getResources().getString(R.string.lightActivetxt);
        }
        if (Strings.equals(str, ActivityLevel.ACTIVE)) {
            return context.getResources().getString(R.string.activetxt);
        }
        return Strings.equals(str, ActivityLevel.VERY_ACTIVE) ? context.getResources().getString(R.string.veryActivetxt) : "";
    }
}
