package com.myfitnesspal.feature.externalsync.impl.shealth.model;

import com.google.gson.annotations.Expose;
import com.google.logging.type.LogSeverity;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpStepsEntryV2;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SHealthDailySteps {
    private static final int BIN_DURATION_MINS = 10;
    private static ApiJsonMapper mapper = new ApiJsonMapper();
    private final List<SHealthStepsBin> bins;

    private static class SHealthStepsBin {
        private static String API_DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
        private static String API_DATE_TIME_FORMAT = Format.newDatabaseDateTimeFormat().toPattern();
        @Expose
        private double calorie;
        @Expose
        private int count;
        @Expose
        private double distance;
        @Expose
        private double speed;

        public static class LIST_MAPPER extends ArrayList<SHealthStepsBin> {
        }

        private SHealthStepsBin() {
        }

        public double getCalorie() {
            return this.calorie;
        }

        public int getCount() {
            return this.count;
        }

        public double getDistance() {
            return this.distance;
        }

        public double getSpeed() {
            return this.speed;
        }

        public MfpStepsEntryV2 toMfpStepsEntry(Date date, int i) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(12, i);
            MfpStepsEntryV2 mfpStepsEntryV2 = new MfpStepsEntryV2();
            mfpStepsEntryV2.setDate(DateTimeUtils.format(API_DATE_FORMAT, date));
            mfpStepsEntryV2.setDuration(LogSeverity.CRITICAL_VALUE);
            mfpStepsEntryV2.setStartTime(DateTimeUtils.format(API_DATE_TIME_FORMAT, instance.getTime()));
            mfpStepsEntryV2.setSteps(this.count);
            mfpStepsEntryV2.setEnergy(new MfpMeasuredValue("calories", (float) this.calorie));
            mfpStepsEntryV2.setType("steps");
            return mfpStepsEntryV2;
        }
    }

    public static SHealthDailySteps fromJson(String str) {
        if (Strings.notEmpty(str)) {
            List list = (List) mapper.tryMapFrom(str, LIST_MAPPER.class);
            if (list != null) {
                return new SHealthDailySteps(list);
            }
        }
        return null;
    }

    private SHealthDailySteps(List<SHealthStepsBin> list) {
        this.bins = list;
    }

    public int getTotalStepCount() {
        int i = 0;
        for (SHealthStepsBin count : this.bins) {
            i += count.getCount();
        }
        return i;
    }

    public List<MfpStepsEntryV2> toMfpStepsEntryList(Date date) {
        Date toMidnight = DateTimeUtils.setToMidnight(date);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.bins.size(); i++) {
            arrayList.add(((SHealthStepsBin) this.bins.get(i)).toMfpStepsEntry(toMidnight, i * 10));
        }
        return arrayList;
    }
}
