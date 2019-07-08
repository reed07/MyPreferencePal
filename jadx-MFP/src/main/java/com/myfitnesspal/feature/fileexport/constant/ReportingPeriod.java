package com.myfitnesspal.feature.fileexport.constant;

import com.myfitnesspal.android.R;

public enum ReportingPeriod {
    Last7Days(R.string.last_7_days, 7),
    Last30Days(R.string.last_30_days, 30),
    AllTime(R.string.all_days, -1),
    CustomDates(R.string.custom_dates, -1);
    
    private final int numDays;
    private final int stringResId;

    private ReportingPeriod(int i, int i2) {
        this.stringResId = i;
        this.numDays = i2;
    }

    public int getStringResId() {
        return this.stringResId;
    }

    public int getNumDays() {
        return this.numDays;
    }
}
