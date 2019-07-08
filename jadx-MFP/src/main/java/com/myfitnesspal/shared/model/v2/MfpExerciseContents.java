package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.MapOfStringObject;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MfpExerciseContents {
    private static final Map<String, Class<? extends MfpExerciseMetadata>> dataTypeMap = new Builder().put(DataTypes.CALORIE_ADJUSTMENT, MfpExerciseMetadataForCalorieAdjustment.class).put("steps", MfpExerciseMetadataForSteps.class).build();
    @Expose
    private Map<String, Object> data;
    private MfpExerciseMetadata metadata;
    @Expose
    private String type;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpExerciseContents> {
    }

    public static final class DataTypes {
        public static final String CALORIE_ADJUSTMENT = "calorie_adjustment";
        public static final String STEPS = "steps";
    }

    public static class LIST_MAPPER extends ArrayList<MfpExerciseContents> {
    }

    public MfpExerciseContents() {
    }

    public MfpExerciseContents(MfpExerciseMetadata mfpExerciseMetadata) {
        this();
        setMetadata(mfpExerciseMetadata);
        Iterator it = dataTypeMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            if (((Class) entry.getValue()).isAssignableFrom(mfpExerciseMetadata.getClass())) {
                setType((String) entry.getKey());
                break;
            }
        }
        setData(getDataFromMetadata());
    }

    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeString(new ApiJsonMapper().reverseMap((Object) this));
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public MfpExerciseContents readFromParcel(Parcel parcel) {
        try {
            return (MfpExerciseContents) new ApiJsonMapper().withType(MfpExerciseContents.class).tryMapFrom(parcel.readString());
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Map<String, Object> getData() {
        Map<String, Object> map = this.data;
        if (map != null) {
            return map;
        }
        Map<String, Object> dataFromMetadata = getDataFromMetadata();
        if (dataFromMetadata != null) {
            return dataFromMetadata;
        }
        return null;
    }

    private Map<String, Object> getDataFromMetadata() {
        if (this.metadata == null || !Strings.notEmpty(this.type) || !dataTypeMap.containsKey(this.type)) {
            return null;
        }
        ApiJsonMapper apiJsonMapper = new ApiJsonMapper();
        String reverseMap = apiJsonMapper.reverseMap((Object) this.metadata);
        apiJsonMapper.withType(MapOfStringObject.class);
        return (Map) apiJsonMapper.tryMapFrom(reverseMap);
    }

    public void setData(Map<String, Object> map) {
        this.data = map;
    }

    public <T extends MfpExerciseMetadata> T getMetadata() {
        if (this.metadata == null && this.data != null && Strings.notEmpty(this.type) && dataTypeMap.containsKey(this.type)) {
            Class cls = (Class) dataTypeMap.get(this.type);
            ApiJsonMapper apiJsonMapper = new ApiJsonMapper();
            String reverseMap = apiJsonMapper.reverseMap((Object) this.data);
            apiJsonMapper.withType(cls);
            this.metadata = (MfpExerciseMetadata) apiJsonMapper.tryMapFrom(reverseMap);
        }
        return this.metadata;
    }

    public <T extends MfpExerciseMetadata> void setMetadata(T t) {
        this.metadata = t;
    }
}
