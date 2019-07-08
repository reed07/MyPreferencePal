package com.myfitnesspal.feature.goals.event;

import com.myfitnesspal.shared.event.MfpEventBase;

public class UpdateGoalsCompleteEvent extends MfpEventBase {
    private final boolean navigateToHome;

    public UpdateGoalsCompleteEvent() {
        this(true);
    }

    public UpdateGoalsCompleteEvent(boolean z) {
        this.navigateToHome = z;
    }

    public boolean shouldNavigateToHome() {
        return this.navigateToHome;
    }
}
