package com.myfitnesspal.feature.progress.constants;

import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v2.MfpProfile;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.DateTimeUtils;

public enum GraphPeriod {
    OneWeek(7, R.string.one_week),
    OneMonth(30, R.string.one_month),
    TwoMonths(60, R.string.two_months),
    ThreeMonths(90, R.string.three_months),
    SixMonths(180, R.string.six_months),
    OneYear(365, R.string.one_year),
    AllTime(Integer.MAX_VALUE, R.string.all_time),
    StartingWeight(1, R.string.since_starting_weight);
    
    private final int days;
    private final int stringId;

    private GraphPeriod(int i, int i2) {
        this.days = i;
        this.stringId = i2;
    }

    public int getDays() {
        return this.days;
    }

    public int getDaysBack(Session session) {
        int i;
        if (this == StartingWeight) {
            MfpProfile userProfile = session.getUser().getUserProfile();
            if (userProfile == null) {
                i = 0;
            } else {
                i = DateTimeUtils.getNumberOfDaysSince(userProfile.getStartingWeightDate());
            }
            return i;
        }
        int i2 = this.days;
        return i2 == Integer.MAX_VALUE ? i2 : i2 - 1;
    }

    public int getStringId() {
        return this.stringId;
    }

    public static GraphPeriod getGraphPeriod(int i) {
        switch (i) {
            case R.string.all_time /*2131886347*/:
                return AllTime;
            case R.string.one_month /*2131888619*/:
                return OneMonth;
            case R.string.one_week /*2131888621*/:
                return OneWeek;
            case R.string.one_year /*2131888622*/:
                return OneYear;
            case R.string.since_starting_weight /*2131889223*/:
                return StartingWeight;
            case R.string.six_months /*2131889224*/:
                return SixMonths;
            case R.string.three_months /*2131889329*/:
                return ThreeMonths;
            case R.string.two_months /*2131889401*/:
                return TwoMonths;
            default:
                return ThreeMonths;
        }
    }
}
