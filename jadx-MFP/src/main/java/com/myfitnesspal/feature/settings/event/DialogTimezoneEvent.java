package com.myfitnesspal.feature.settings.event;

import com.myfitnesspal.shared.model.v1.Timezone;

public class DialogTimezoneEvent {
    private final Timezone timezone;

    public DialogTimezoneEvent(Timezone timezone2) {
        this.timezone = timezone2;
    }

    public Timezone getTimezone() {
        return this.timezone;
    }
}
