package com.myfitnesspal.feature.progress.model;

public class MeasurementTypeItem {
    private String description;
    private long id;
    private boolean state;

    public MeasurementTypeItem(long j, String str) {
        this.id = j;
        this.description = str;
    }

    public long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getState() {
        return this.state;
    }

    public void setState(boolean z) {
        this.state = z;
    }
}
