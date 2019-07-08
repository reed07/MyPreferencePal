package com.myfitnesspal.shared.model;

import com.myfitnesspal.feature.progress.model.ProgressEntryItem;
import java.util.Date;

public class ProgressEntryViewModel implements ProgressEntryItem, Comparable<ProgressEntryViewModel> {
    private final double convertedValue;
    private final Date date;
    private final long imageAssociationLocalId;
    private final String imageId;
    private final String imageLocalFilepath;
    private final long measurementId;
    private final String measurementUid;
    private final boolean showInList;
    private final double valueInPounds;

    public ProgressEntryViewModel(long j, String str, Date date2, double d, double d2, String str2, String str3, long j2, boolean z) {
        this.measurementId = j;
        this.measurementUid = str;
        this.date = date2;
        this.valueInPounds = d;
        this.convertedValue = d2;
        this.imageId = str2;
        this.imageLocalFilepath = str3;
        this.imageAssociationLocalId = j2;
        this.showInList = z;
    }

    public Date getDate() {
        return this.date;
    }

    public double getValue() {
        return this.convertedValue;
    }

    public double getNormalizedUnitValue() {
        return this.valueInPounds;
    }

    public String getImageId() {
        return this.imageId;
    }

    public String getImageLocalFilepath() {
        return this.imageLocalFilepath;
    }

    public long getImageAssociationLocalId() {
        return this.imageAssociationLocalId;
    }

    public boolean isShowInList() {
        return this.showInList;
    }

    public String getMeasurementUid() {
        return this.measurementUid;
    }

    public long getMeasurementId() {
        return this.measurementId;
    }

    public int compareTo(ProgressEntryViewModel progressEntryViewModel) {
        if (progressEntryViewModel == null) {
            return -1;
        }
        return Double.compare(this.valueInPounds, progressEntryViewModel.valueInPounds);
    }
}
