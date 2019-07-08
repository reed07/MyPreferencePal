package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.MapOfStringString;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import java.util.Date;
import java.util.Map;

public class MfpTimelineContent {
    private static final Map<String, Class<? extends MfpTimelineContentData>> dataTypeMap = new Builder().put(DataTypes.PLAINTEXT, MfpTimelineContentPlaintextEntry.class).put(DataTypes.STICKER, MfpTimelineContentStickerEntry.class).put("image", MfpTimelineContentImageEntry.class).put("meal", MfpTimelineContentMealEntry.class).put("exercise_entry", MfpTimelineContentExerciseEntry.class).put(DataTypes.LOGGED_WEIGHT, MfpTimelineContentLoggedWeightEntry.class).put(DataTypes.DAILY_STEPS_COUNT, MfpTimelineContentDailyStepsCountEntry.class).put(DataTypes.COMPLETED_DIARY_EVENT, MfpTimelineContentCompletedDiaryEventEntry.class).put(DataTypes.SYSTEM, MfpTimelineContentSystemEntry.class).put(DataTypes.VIEWED_COACHING_TIMELINE, MfpTimelineContentViewedCoachingTimelineEntry.class).build();
    @Expose
    private Map<String, Object> data;
    private MfpTimelineContentData entryData;
    @Expose
    private String id;
    @Expose
    private Date occurredAt;
    @Expose
    private String type;
    @Expose
    private String userId;

    public static final class DataTypes {
        public static final String COACHING_DAY = "coaching_day";
        public static final String COMPLETED_DIARY_EVENT = "completed_diary_event";
        public static final String DAILY_STEPS_COUNT = "daily_steps_count";
        public static final String EXERCISE_ENTRY = "exercise_entry";
        public static final String IMAGE = "image";
        public static final String LOGGED_WEIGHT = "logged_weight";
        public static final String MEAL = "meal";
        public static final String PLAINTEXT = "plaintext";
        public static final String STICKER = "sticker";
        public static final String SYSTEM = "system";
        public static final String VIEWED_COACHING_TIMELINE = "viewed_coaching_screen";
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public Date getOccurredAt() {
        return this.occurredAt;
    }

    public void setOccurredAt(Date date) {
        this.occurredAt = date;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public <T extends MfpTimelineContentData> T getEntryData() {
        if (this.entryData == null && this.data != null) {
            if (Strings.notEmpty(this.type) && dataTypeMap.containsKey(this.type)) {
                Class cls = (Class) dataTypeMap.get(this.type);
                ApiJsonMapper apiJsonMapper = new ApiJsonMapper();
                String reverseMap = apiJsonMapper.reverseMap((Object) this.data);
                apiJsonMapper.withType(cls);
                this.entryData = (MfpTimelineContentData) apiJsonMapper.tryMapFrom(reverseMap);
            }
            this.data = null;
        }
        return this.entryData;
    }

    public void setEntryData(MfpTimelineContentData mfpTimelineContentData) {
        this.entryData = mfpTimelineContentData;
        ApiJsonMapper apiJsonMapper = new ApiJsonMapper();
        String reverseMap = apiJsonMapper.reverseMap((Object) mfpTimelineContentData);
        apiJsonMapper.withType(MapOfStringString.class);
        this.data = (Map) apiJsonMapper.tryMapFrom(reverseMap);
    }
}
