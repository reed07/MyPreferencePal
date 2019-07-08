package com.myfitnesspal.shared.model.v15;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class DiaryDaySummaryObject implements BinaryApiSerializable {
    public static final BinaryCreator<DiaryDaySummaryObject> BINARY_CREATOR = new BinaryCreator<DiaryDaySummaryObject>() {
        public DiaryDaySummaryObject create(BinaryDecoder binaryDecoder) {
            DiaryDaySummaryObject diaryDaySummaryObject = new DiaryDaySummaryObject();
            diaryDaySummaryObject.readData(binaryDecoder);
            return diaryDaySummaryObject;
        }
    };
    private Date date;
    private Map<String, String> summaryValues;

    private static final class Keys {
        public static final String CALORIES_BURNED = "calories_burned";
        public static final String GOAL_CALORIES = "goal_calories";

        private Keys() {
        }
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public float getGoalCalories() {
        return NumberUtils.tryParseFloat(getSummaryValue(Keys.GOAL_CALORIES), BitmapDescriptorFactory.HUE_RED, Locale.US);
    }

    public float getCaloriesBurned() {
        return NumberUtils.tryParseFloat(getSummaryValue(Keys.CALORIES_BURNED));
    }

    public Map<String, String> getSummaryValues() {
        return this.summaryValues;
    }

    public void setSummaryValues(Map<String, String> map) {
        this.summaryValues = map;
    }

    private String getSummaryValue(String str) {
        if (Strings.notEmpty(str)) {
            Map<String, String> map = this.summaryValues;
            if (map != null) {
                return (String) map.get(str);
            }
        }
        return null;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeDate(this.date);
        binaryEncoder.writeStringToStringMap(this.summaryValues);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.date = binaryDecoder.decodeDate();
        this.summaryValues = binaryDecoder.decodeStringToStringMap();
    }
}
