package com.myfitnesspal.feature.dashboard.event;

import com.myfitnesspal.shared.event.MfpEventBase;

public class PresetNutrientSelectedEvent extends MfpEventBase {
    private final String displayType;

    public PresetNutrientSelectedEvent(String str) {
        this.displayType = str;
    }

    public String getDisplayType() {
        return this.displayType;
    }
}
