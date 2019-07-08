package com.myfitnesspal.feature.progress.event;

import com.myfitnesspal.feature.progress.model.MeasurementTypeItem;

public class MeasurementTypeChangeEvent {
    private MeasurementTypeItem measurementTypeItem;

    public MeasurementTypeChangeEvent(MeasurementTypeItem measurementTypeItem2) {
        this.measurementTypeItem = measurementTypeItem2;
    }

    public MeasurementTypeItem getMeasurementTypeItem() {
        return this.measurementTypeItem;
    }
}
