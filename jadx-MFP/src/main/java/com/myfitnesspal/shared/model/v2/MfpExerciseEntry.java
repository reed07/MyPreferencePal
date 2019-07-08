package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.exercise.model.ExerciseSearchAdapterItem;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable.Columns;
import com.myfitnesspal.shared.model.date.MfpIso8601Date;
import com.myfitnesspal.shared.model.date.MfpYmdDate;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpExercise.ExerciseTypes;
import com.myfitnesspal.shared.model.v2.MfpExerciseContents.LIST_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue.Unit;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MfpExerciseEntry extends MfpDatabaseObjectV2 implements ExerciseSearchAdapterItem {
    public static final Creator<MfpExerciseEntry> CREATOR = new Creator<MfpExerciseEntry>() {
        public MfpExerciseEntry createFromParcel(Parcel parcel) {
            return new MfpExerciseEntry(parcel);
        }

        public MfpExerciseEntry[] newArray(int i) {
            return new MfpExerciseEntry[i];
        }
    };
    @Expose
    private String appId;
    @Expose
    private int avgHeartRate;
    @Expose
    private List<MfpExerciseContents> contents;
    @Expose
    private MfpIso8601Date createdAt;
    @Expose
    private MfpYmdDate date;
    @Expose
    private MfpMeasuredValue distance;
    @Expose
    private int duration;
    @Expose
    private MfpMeasuredValue elevationChange;
    @Expose
    private MfpMeasuredValue energy;
    @Expose
    private MfpExercise exercise;
    @Expose
    private Boolean isCalorieAdjustment;
    @Expose
    private int maxHeartRate;
    @Expose
    private MfpMeasuredValue maxSpeed;
    @Expose
    private int repsPerSet;
    @Expose
    private int sets;
    @Expose
    private String source;
    @Expose
    private MfpIso8601Date startTime;
    @Expose
    private List<String> tags;
    @Expose
    private String type = getSyncResourceName();
    @Expose
    private MfpMeasuredValue weightPerSet;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpExerciseEntry> {
    }

    /* access modifiers changed from: protected */
    public String getSyncResourceName() {
        return "exercise_entry";
    }

    public MfpExerciseEntry() {
    }

    public MfpExerciseEntry(CursorMapper cursorMapper) {
        super(cursorMapper.forClass(MfpExerciseEntry.class));
        setDate(Database.decodeDateString(cursorMapper.getString("entry_date")));
        setDuration(cursorMapper.getInt(Columns.DURATION_IN_SECONDS));
        setEnergy(new MfpMeasuredValue("calories", cursorMapper.getFloat("calories")));
        setStartTimeString(cursorMapper.getString("start_time"));
        setSets(cursorMapper.getInt(Columns.SETS));
        setRepsPerSet(cursorMapper.getInt(Columns.REPETITIONS));
        setWeightPerSet(new MfpMeasuredValue(Unit.POUNDS, cursorMapper.getFloat("weight")));
        setDistance(new MfpMeasuredValue(Unit.MILES, cursorMapper.getFloat(Columns.DISTANCE_IN_MILES)));
        setMaxSpeed(new MfpMeasuredValue(Unit.MILES_PER_HOUR, cursorMapper.getFloat(Columns.MAX_SPEED_IN_MPH)));
        setAvgHeartRate(cursorMapper.getInt(Columns.AVG_HEART_RATE));
        setMaxHeartRate(cursorMapper.getInt("max_heart_rate"));
        setElevationChange(new MfpMeasuredValue(Unit.FEET, cursorMapper.getFloat(Columns.ELEVATION_CHANGE_IN_FEET)));
        setSource(cursorMapper.getString("source"));
        setAppId(cursorMapper.getString("app_id"));
        setIsCalorieAdjustment(cursorMapper.getBoolean(Columns.IS_CALORIE_ADJUSTMENT));
        this.contents = new ArrayList();
        setEnergyOffsetDataString(cursorMapper.getString(Columns.ENERGY_OFFSET_DATA));
        int i = cursorMapper.getInt("steps");
        String string = cursorMapper.getString("client_id");
        String string2 = cursorMapper.getString("device_id");
        Date decodeDateString = Database.decodeDateString(cursorMapper.getString("entry_date"));
        if (i > 0 && Strings.notEmpty(string) && Strings.notEmpty(string2) && decodeDateString != null) {
            setStepsData(new MfpExerciseMetadataForSteps(i, string, string2, decodeDateString));
        }
        MfpExercise mfpExercise = (MfpExercise) new ApiJsonMapper().withType(MfpExercise.class).tryMapFrom(cursorMapper.getString("exercise"));
        setExercise(mfpExercise);
        if (mfpExercise.getMasterId() == 0) {
            mfpExercise.setMasterId(cursorMapper.getLong(Columns.EXERCISE_MASTER_ID));
        }
        if (Strings.isEmpty(mfpExercise.getVersion())) {
            mfpExercise.setVersion(cursorMapper.getString(Columns.EXERCISE_VERSION));
        }
    }

    public MfpExerciseEntry(MfpExercise mfpExercise) {
        this.exercise = mfpExercise;
        this.duration = 0;
        this.sets = 0;
        this.repsPerSet = 0;
        this.weightPerSet = new MfpMeasuredValue(Unit.POUNDS, BitmapDescriptorFactory.HUE_RED);
        this.energy = new MfpMeasuredValue("calories", BitmapDescriptorFactory.HUE_RED);
    }

    protected MfpExerciseEntry(Parcel parcel) {
        super(parcel);
        ClassLoader classLoader = MfpMeasuredValue.class.getClassLoader();
        this.date = MfpYmdDate.newInstance(ParcelableUtil.readDate(parcel));
        this.type = parcel.readString();
        this.exercise = (MfpExercise) parcel.readParcelable(MfpExercise.class.getClassLoader());
        this.duration = parcel.readInt();
        this.energy = (MfpMeasuredValue) parcel.readParcelable(classLoader);
        this.startTime = MfpIso8601Date.newInstance(ParcelableUtil.readDate(parcel));
        this.tags = parcel.createStringArrayList();
        this.sets = parcel.readInt();
        this.repsPerSet = parcel.readInt();
        this.weightPerSet = (MfpMeasuredValue) parcel.readParcelable(classLoader);
        this.distance = (MfpMeasuredValue) parcel.readParcelable(classLoader);
        this.maxSpeed = (MfpMeasuredValue) parcel.readParcelable(classLoader);
        this.avgHeartRate = parcel.readInt();
        this.maxHeartRate = parcel.readInt();
        this.elevationChange = (MfpMeasuredValue) parcel.readParcelable(classLoader);
        this.createdAt = MfpIso8601Date.newInstance(ParcelableUtil.readDate(parcel));
        this.source = parcel.readString();
        this.appId = parcel.readString();
        this.isCalorieAdjustment = Boolean.valueOf(ParcelableUtil.readBoolean(parcel));
        this.contents = (List) new ApiJsonMapper().withType(LIST_MAPPER.class).tryMapFrom(parcel.readString());
    }

    public void setStepsData(MfpExerciseMetadataForSteps mfpExerciseMetadataForSteps) {
        ensureContentsArray();
        removeMetadataOfType(MfpExerciseMetadataForSteps.class);
        if (mfpExerciseMetadataForSteps != null) {
            this.contents.add(new MfpExerciseContents(mfpExerciseMetadataForSteps));
        }
    }

    public void setEnergyOffsetDataString(String str) {
        if (Strings.notEmpty(str)) {
            MfpExerciseMetadataForCalorieAdjustment mfpExerciseMetadataForCalorieAdjustment = (MfpExerciseMetadataForCalorieAdjustment) new ApiJsonMapper().withType(MfpExerciseMetadataForCalorieAdjustment.class).tryMapFrom(str);
            ensureContentsArray();
            removeMetadataOfType(MfpExerciseMetadataForCalorieAdjustment.class);
            this.contents.add(new MfpExerciseContents(mfpExerciseMetadataForCalorieAdjustment));
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        Boolean bool = this.isCalorieAdjustment;
        boolean z = bool != null && bool.booleanValue();
        super.writeToParcel(parcel, i);
        ParcelableUtil.writeDate(parcel, this.date);
        parcel.writeString(this.type);
        parcel.writeParcelable(this.exercise, i);
        parcel.writeInt(this.duration);
        parcel.writeParcelable(this.energy, i);
        ParcelableUtil.writeDate(parcel, this.startTime);
        parcel.writeStringList(this.tags);
        parcel.writeInt(this.sets);
        parcel.writeInt(this.repsPerSet);
        parcel.writeParcelable(this.weightPerSet, i);
        parcel.writeParcelable(this.distance, i);
        parcel.writeParcelable(this.maxSpeed, i);
        parcel.writeInt(this.avgHeartRate);
        parcel.writeInt(this.maxHeartRate);
        parcel.writeParcelable(this.elevationChange, i);
        ParcelableUtil.writeDate(parcel, this.createdAt);
        parcel.writeString(this.source);
        parcel.writeString(this.appId);
        ParcelableUtil.writeBoolean(parcel, z);
        String str = "";
        if (this.contents != null) {
            str = new ApiJsonMapper().reverseMap((Object) this.contents);
        }
        parcel.writeString(str);
    }

    public void toContentValues(ContentValuesMapper contentValuesMapper) {
        super.toContentValues(contentValuesMapper);
        MfpExercise exercise2 = getExercise();
        contentValuesMapper.put("exercise_type", Integer.valueOf(ExerciseTypes.toValue(exercise2.getType())));
        contentValuesMapper.put("exercise_id", Long.valueOf(exercise2.getLocalId()));
        contentValuesMapper.put(Columns.EXERCISE_MASTER_ID, Long.valueOf(exercise2.getMasterId()));
        contentValuesMapper.put(Columns.EXERCISE_VERSION, exercise2.getVersion());
        contentValuesMapper.put("exercise", new ApiJsonMapper().reverseMap((Object) exercise2));
        contentValuesMapper.put("entry_date", Database.encodeDate(getDate()));
        contentValuesMapper.put(Columns.DURATION_IN_SECONDS, Integer.valueOf(getDuration()));
        contentValuesMapper.put("calories", MfpMeasuredValue.getValueSafe(getEnergy()));
        Date startTime2 = getStartTime();
        if (startTime2 != null) {
            contentValuesMapper.put("start_time", Database.encodeTime(startTime2));
        }
        contentValuesMapper.put(Columns.DISTANCE_IN_MILES, MfpMeasuredValue.getValueSafe(getDistance()));
        contentValuesMapper.put(Columns.MAX_SPEED_IN_MPH, MfpMeasuredValue.getValueSafe(getMaxSpeed()));
        contentValuesMapper.put(Columns.AVG_HEART_RATE, Integer.valueOf(getAvgHeartRate()));
        contentValuesMapper.put("max_heart_rate", Integer.valueOf(getMaxHeartRate()));
        contentValuesMapper.put(Columns.ELEVATION_CHANGE_IN_FEET, MfpMeasuredValue.getValueSafe(getElevationChange()));
        contentValuesMapper.put(Columns.SETS, Integer.valueOf(getSets()));
        contentValuesMapper.put(Columns.REPETITIONS, Integer.valueOf(getRepsPerSet()));
        contentValuesMapper.put("weight", MfpMeasuredValue.getValueSafe(getWeightPerSet()));
        contentValuesMapper.put(Columns.IS_CALORIE_ADJUSTMENT, Boolean.valueOf(NumberUtils.getBooleanValue(isCalorieAdjustment())));
        if (Strings.notEmpty(getAppId())) {
            contentValuesMapper.put("app_id", getAppId());
        }
        if (Strings.notEmpty(getSource())) {
            contentValuesMapper.put("source", getSource());
        }
        MfpExerciseMetadataForCalorieAdjustment calorieAdjustmentData = getCalorieAdjustmentData();
        if (calorieAdjustmentData != null) {
            contentValuesMapper.put(Columns.ENERGY_OFFSET_DATA, new ApiJsonMapper().reverseMap((Object) calorieAdjustmentData));
        }
        MfpExerciseMetadataForSteps stepsData = getStepsData();
        if (stepsData != null) {
            contentValuesMapper.put("steps", Integer.valueOf(stepsData.getSteps()));
            contentValuesMapper.put("client_id", stepsData.getClientId());
            contentValuesMapper.put("device_id", stepsData.getDeviceId());
        }
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = MfpYmdDate.newInstance(date2);
    }

    public MfpExercise getExercise() {
        return this.exercise;
    }

    public void setExercise(MfpExercise mfpExercise) {
        this.exercise = mfpExercise;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public MfpMeasuredValue getEnergy() {
        return this.energy;
    }

    public void setEnergy(MfpMeasuredValue mfpMeasuredValue) {
        this.energy = mfpMeasuredValue;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public String getStartTimeString() {
        MfpIso8601Date mfpIso8601Date = this.startTime;
        if (mfpIso8601Date != null) {
            return Database.encodeTime(mfpIso8601Date);
        }
        return null;
    }

    public void setStartTime(Date date2) {
        this.startTime = MfpIso8601Date.newInstance(date2);
    }

    public void setStartTimeString(String str) {
        if (Strings.notEmpty(str)) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(Database.decodeTimeString(str));
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(getDate() != null ? getDate() : instance.getTime());
            instance2.set(11, instance.get(11));
            instance2.set(12, instance.get(12));
            instance2.set(13, instance.get(13));
            setStartTime(instance2.getTime());
            return;
        }
        setStartTime(null);
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> list) {
        this.tags = list;
    }

    public int getSets() {
        return this.sets;
    }

    public void setSets(int i) {
        this.sets = i;
    }

    public int getRepsPerSet() {
        return this.repsPerSet;
    }

    public void setRepsPerSet(int i) {
        this.repsPerSet = i;
    }

    public MfpMeasuredValue getWeightPerSet() {
        return this.weightPerSet;
    }

    public void setWeightPerSet(MfpMeasuredValue mfpMeasuredValue) {
        this.weightPerSet = mfpMeasuredValue;
    }

    public MfpMeasuredValue getDistance() {
        return this.distance;
    }

    public void setDistance(MfpMeasuredValue mfpMeasuredValue) {
        this.distance = mfpMeasuredValue;
    }

    public MfpMeasuredValue getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(MfpMeasuredValue mfpMeasuredValue) {
        this.maxSpeed = mfpMeasuredValue;
    }

    public int getAvgHeartRate() {
        return this.avgHeartRate;
    }

    public void setAvgHeartRate(int i) {
        this.avgHeartRate = i;
    }

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public MfpMeasuredValue getElevationChange() {
        return this.elevationChange;
    }

    public void setElevationChange(MfpMeasuredValue mfpMeasuredValue) {
        this.elevationChange = mfpMeasuredValue;
    }

    public List<MfpExerciseContents> getContents() {
        return this.contents;
    }

    public void setContents(List<MfpExerciseContents> list) {
        this.contents = list;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date2) {
        this.createdAt = MfpIso8601Date.newInstance(date2);
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public Boolean isCalorieAdjustment() {
        return this.isCalorieAdjustment;
    }

    public void setIsCalorieAdjustment(Boolean bool) {
        this.isCalorieAdjustment = bool;
    }

    public MfpExerciseMetadataForCalorieAdjustment getCalorieAdjustmentData() {
        return (MfpExerciseMetadataForCalorieAdjustment) getSpecificMetadata(MfpExerciseMetadataForCalorieAdjustment.class);
    }

    public boolean hasSteps() {
        return getStepsData() != null;
    }

    public MfpExerciseMetadataForSteps getStepsData() {
        return (MfpExerciseMetadataForSteps) getSpecificMetadata(MfpExerciseMetadataForSteps.class);
    }

    private <T extends MfpExerciseMetadata> T getSpecificMetadata(final Class<T> cls) {
        MfpExerciseContents mfpExerciseContents = (MfpExerciseContents) Enumerable.firstOrDefault(this.contents, new ReturningFunction1<Boolean, MfpExerciseContents>() {
            public Boolean execute(MfpExerciseContents mfpExerciseContents) throws RuntimeException {
                return Boolean.valueOf(cls.isAssignableFrom(mfpExerciseContents.getMetadata().getClass()));
            }
        });
        if (mfpExerciseContents != null) {
            return mfpExerciseContents.getMetadata();
        }
        return null;
    }

    private <T extends MfpExerciseMetadata> void removeMetadataOfType(Class<T> cls) {
        if (CollectionUtils.notEmpty((Collection<?>) this.contents)) {
            for (int i = 0; i < this.contents.size(); i++) {
                if (cls.isAssignableFrom(((MfpExerciseContents) this.contents.get(i)).getMetadata().getClass())) {
                    this.contents.remove(i);
                    return;
                }
            }
        }
    }

    private void ensureContentsArray() {
        if (this.contents == null) {
            this.contents = new ArrayList();
        }
    }
}
