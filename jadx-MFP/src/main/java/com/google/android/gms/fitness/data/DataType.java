package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Class(creator = "DataTypeCreator")
@Reserved({1000})
public final class DataType extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final DataType AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS);
    public static final DataType AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY = new DataType("com.google.calories.bmr.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY = new DataType("com.google.body.fat.percentage.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @Deprecated
    public static final DataType AGGREGATE_CALORIES_CONSUMED = TYPE_CALORIES_CONSUMED;
    public static final DataType AGGREGATE_CALORIES_EXPENDED = TYPE_CALORIES_EXPENDED;
    public static final DataType AGGREGATE_DISTANCE_DELTA = TYPE_DISTANCE_DELTA;
    public static final DataType AGGREGATE_HEART_POINTS = new DataType("com.google.heart_minutes.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_INTENSITY, Field.FIELD_DURATION);
    public static final DataType AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_HEIGHT_SUMMARY = new DataType("com.google.height.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_HYDRATION = TYPE_HYDRATION;
    @Deprecated
    public static final Set<DataType> AGGREGATE_INPUT_TYPES;
    public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE);
    public static final DataType AGGREGATE_MOVE_MINUTES;
    public static final DataType AGGREGATE_NUTRITION_SUMMARY = new DataType("com.google.nutrition.summary", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE);
    public static final DataType AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_STEP_COUNT_DELTA = TYPE_STEP_COUNT_DELTA;
    public static final DataType AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final Creator<DataType> CREATOR = new zzl();
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";
    @Deprecated
    public static final DataType TYPE_ACTIVITY_SAMPLE = new DataType("com.google.activity.sample", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE);
    public static final DataType TYPE_ACTIVITY_SAMPLES = new DataType("com.google.activity.samples", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY_CONFIDENCE);
    public static final DataType TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY);
    public static final DataType TYPE_BASAL_METABOLIC_RATE = new DataType("com.google.calories.bmr", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
    public static final DataType TYPE_BODY_FAT_PERCENTAGE = new DataType("com.google.body.fat.percentage", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_PERCENTAGE);
    @Deprecated
    public static final DataType TYPE_CALORIES_CONSUMED = new DataType("com.google.calories.consumed", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
    public static final DataType TYPE_CALORIES_EXPENDED = new DataType("com.google.calories.expended", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
    public static final DataType TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
    public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_REVOLUTIONS);
    public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_REVOLUTIONS);
    public static final DataType TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_RPM);
    @KeepName
    public static final DataType TYPE_DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
    public static final DataType TYPE_DISTANCE_DELTA = new DataType("com.google.distance.delta", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
    public static final DataType TYPE_HEART_POINTS = new DataType("com.google.heart_minutes", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_INTENSITY);
    public static final DataType TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ, Field.FIELD_BPM);
    public static final DataType TYPE_HEIGHT = new DataType("com.google.height", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_HEIGHT);
    public static final DataType TYPE_HYDRATION = new DataType("com.google.hydration", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_VOLUME);
    public static final DataType TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
    public static final DataType TYPE_LOCATION_TRACK = new DataType("com.google.location.track", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
    public static final DataType TYPE_MOVE_MINUTES;
    public static final DataType TYPE_NUTRITION = new DataType("com.google.nutrition", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE, Field.FIELD_FOOD_ITEM);
    public static final DataType TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_WATTS);
    public static final DataType TYPE_SPEED = new DataType("com.google.speed", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_SPEED);
    public static final DataType TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
    @KeepName
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
    public static final DataType TYPE_STEP_COUNT_DELTA = new DataType("com.google.step_count.delta", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
    public static final DataType TYPE_WEIGHT = new DataType("com.google.weight", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_WEIGHT);
    public static final DataType TYPE_WORKOUT_EXERCISE = new DataType("com.google.activity.exercise", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.FIELD_DURATION, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE);
    public static final DataType zzbc = new DataType("com.google.step_length", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEP_LENGTH);
    public static final DataType zzbd = new DataType("com.google.internal.goal", Field.zzck);
    public static final DataType zzbe = new DataType("com.google.internal.prescription_event", Field.zzcl);
    public static final DataType zzbf = new DataType("com.google.internal.symptom", Field.zzcm);
    public static final DataType zzbg = new DataType("com.google.stride_model", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcn);
    public static final DataType zzbh = new DataType("com.google.floor_change", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE, Field.zzcp, Field.zzcs);
    public static final DataType zzbi = new DataType("com.google.accelerometer", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, com.google.android.gms.fitness.data.Field.zza.zzde, com.google.android.gms.fitness.data.Field.zza.zzdf, com.google.android.gms.fitness.data.Field.zza.zzdg);
    public static final DataType zzbj = new DataType("com.google.sensor.events", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcv, Field.zzcx, Field.zzdb);
    public static final DataType zzbk = new DataType("com.google.sensor.const_rate_events", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcw, Field.zzcy, Field.zzcz, Field.zzda, Field.zzdb);
    public static final DataType zzbl = new DataType("com.google.body.waist.circumference", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_CIRCUMFERENCE);
    public static final DataType zzbm = new DataType("com.google.body.hip.circumference", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_CIRCUMFERENCE);
    public static final DataType zzbn = new DataType("com.google.device_on_body", Field.zzdc);
    public static final DataType zzbo = new DataType("com.google.internal.primary_device", Field.zzco);
    public static final DataType zzbp = new DataType("com.google.floor_change.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzci, Field.zzcj, Field.zzcq, Field.zzcr, Field.zzct, Field.zzcu);
    public static final DataType zzbq = new DataType("com.google.body.hip.circumference.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType zzbr = new DataType("com.google.body.waist.circumference.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @Field(getter = "getName", id = 1)
    private final String name;
    @Field(getter = "getFields", id = 2)
    private final List<Field> zzbs;
    @Field(getter = "getReadScope", id = 3)
    private final String zzbt;
    @Field(getter = "getWriteScope", id = 4)
    private final String zzbu;

    public static final class zza {
        public static final DataType zzbv = new DataType("com.google.internal.session.debug", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, com.google.android.gms.fitness.data.Field.zza.zzdh);
        public static final DataType zzbw = new DataType("com.google.internal.session.v2", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, com.google.android.gms.fitness.data.Field.zza.zzdi);
    }

    public static List<DataType> getAggregatesForInput(DataType dataType) {
        List list = (List) zza.zzac.get(dataType);
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }

    private DataType(String str, Field... fieldArr) {
        this(str, Arrays.asList(fieldArr), (String) null, (String) null);
    }

    public DataType(String str, String str2, String str3, Field... fieldArr) {
        this(str, Arrays.asList(fieldArr), str2, str3);
    }

    @Constructor
    DataType(@Param(id = 1) String str, @Param(id = 2) List<Field> list, @Param(id = 3) String str2, @Param(id = 4) String str3) {
        this.name = str;
        this.zzbs = Collections.unmodifiableList(list);
        this.zzbt = str2;
        this.zzbu = str3;
    }

    public static String getMimeType(DataType dataType) {
        String valueOf = String.valueOf(MIME_TYPE_PREFIX);
        String valueOf2 = String.valueOf(dataType.getName());
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String getName() {
        return this.name;
    }

    public final List<Field> getFields() {
        return this.zzbs;
    }

    public final String zzk() {
        return this.zzbt;
    }

    public final String zzl() {
        return this.zzbu;
    }

    public final int indexOf(Field field) {
        int indexOf = this.zzbs.indexOf(field);
        Preconditions.checkArgument(indexOf >= 0, "%s not a field of %s", field, this);
        return indexOf;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataType)) {
            return false;
        }
        DataType dataType = (DataType) obj;
        return this.name.equals(dataType.name) && this.zzbs.equals(dataType.zzbs);
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final String toString() {
        return String.format("DataType{%s%s}", new Object[]{this.name, this.zzbs});
    }

    public final String zzm() {
        return this.name.startsWith("com.google.") ? this.name.substring(11) : this.name;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbt, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    static {
        DataType dataType = new DataType("com.google.active_minutes", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_DURATION);
        TYPE_MOVE_MINUTES = dataType;
        AGGREGATE_MOVE_MINUTES = dataType;
        ArraySet arraySet = new ArraySet();
        AGGREGATE_INPUT_TYPES = arraySet;
        arraySet.add(TYPE_ACTIVITY_SEGMENT);
        AGGREGATE_INPUT_TYPES.add(TYPE_BASAL_METABOLIC_RATE);
        AGGREGATE_INPUT_TYPES.add(TYPE_BODY_FAT_PERCENTAGE);
        AGGREGATE_INPUT_TYPES.add(zzbm);
        AGGREGATE_INPUT_TYPES.add(zzbl);
        AGGREGATE_INPUT_TYPES.add(TYPE_CALORIES_CONSUMED);
        AGGREGATE_INPUT_TYPES.add(TYPE_CALORIES_EXPENDED);
        AGGREGATE_INPUT_TYPES.add(TYPE_DISTANCE_DELTA);
        AGGREGATE_INPUT_TYPES.add(zzbh);
        AGGREGATE_INPUT_TYPES.add(TYPE_LOCATION_SAMPLE);
        AGGREGATE_INPUT_TYPES.add(TYPE_NUTRITION);
        AGGREGATE_INPUT_TYPES.add(TYPE_HYDRATION);
        AGGREGATE_INPUT_TYPES.add(TYPE_HEART_RATE_BPM);
        AGGREGATE_INPUT_TYPES.add(TYPE_POWER_SAMPLE);
        AGGREGATE_INPUT_TYPES.add(TYPE_SPEED);
        AGGREGATE_INPUT_TYPES.add(TYPE_STEP_COUNT_DELTA);
        AGGREGATE_INPUT_TYPES.add(TYPE_WEIGHT);
    }
}
