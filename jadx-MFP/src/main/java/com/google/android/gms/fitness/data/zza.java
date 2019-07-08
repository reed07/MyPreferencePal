package com.google.android.gms.fitness.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zza {
    static final Map<DataType, List<DataType>> zzac;

    static {
        HashMap hashMap = new HashMap();
        zzac = hashMap;
        hashMap.put(DataType.TYPE_MOVE_MINUTES, Collections.singletonList(DataType.AGGREGATE_MOVE_MINUTES));
        zzac.put(DataType.TYPE_HEART_POINTS, Collections.singletonList(DataType.AGGREGATE_HEART_POINTS));
        zzac.put(DataType.TYPE_ACTIVITY_SEGMENT, Collections.singletonList(DataType.AGGREGATE_ACTIVITY_SUMMARY));
        zzac.put(DataType.TYPE_BASAL_METABOLIC_RATE, Collections.singletonList(DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY));
        zzac.put(DataType.TYPE_BODY_FAT_PERCENTAGE, Collections.singletonList(DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY));
        zzac.put(DataType.zzbm, Collections.singletonList(DataType.zzbq));
        zzac.put(DataType.zzbl, Collections.singletonList(DataType.zzbr));
        zzac.put(DataType.TYPE_CALORIES_CONSUMED, Collections.singletonList(DataType.AGGREGATE_CALORIES_CONSUMED));
        zzac.put(DataType.TYPE_CALORIES_EXPENDED, Collections.singletonList(DataType.AGGREGATE_CALORIES_EXPENDED));
        zzac.put(DataType.TYPE_DISTANCE_DELTA, Collections.singletonList(DataType.AGGREGATE_DISTANCE_DELTA));
        zzac.put(DataType.zzbh, Collections.singletonList(DataType.zzbp));
        zzac.put(DataType.TYPE_LOCATION_SAMPLE, Collections.singletonList(DataType.AGGREGATE_LOCATION_BOUNDING_BOX));
        zzac.put(DataType.TYPE_NUTRITION, Collections.singletonList(DataType.AGGREGATE_NUTRITION_SUMMARY));
        zzac.put(DataType.TYPE_HYDRATION, Collections.singletonList(DataType.AGGREGATE_HYDRATION));
        zzac.put(DataType.TYPE_HEART_RATE_BPM, Collections.singletonList(DataType.AGGREGATE_HEART_RATE_SUMMARY));
        zzac.put(DataType.TYPE_POWER_SAMPLE, Collections.singletonList(DataType.AGGREGATE_POWER_SUMMARY));
        zzac.put(DataType.TYPE_SPEED, Collections.singletonList(DataType.AGGREGATE_SPEED_SUMMARY));
        zzac.put(DataType.TYPE_STEP_COUNT_DELTA, Collections.singletonList(DataType.AGGREGATE_STEP_COUNT_DELTA));
        zzac.put(DataType.TYPE_WEIGHT, Collections.singletonList(DataType.AGGREGATE_WEIGHT_SUMMARY));
        zzac.put(HealthDataTypes.TYPE_BLOOD_PRESSURE, Collections.singletonList(HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY));
        zzac.put(HealthDataTypes.TYPE_BLOOD_GLUCOSE, Collections.singletonList(HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY));
        zzac.put(HealthDataTypes.TYPE_OXYGEN_SATURATION, Collections.singletonList(HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY));
        zzac.put(HealthDataTypes.TYPE_BODY_TEMPERATURE, Collections.singletonList(HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY));
        zzac.put(HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE, Collections.singletonList(HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY));
        zzac.put(HealthDataTypes.TYPE_CERVICAL_MUCUS, Collections.singletonList(HealthDataTypes.TYPE_CERVICAL_MUCUS));
        zzac.put(HealthDataTypes.TYPE_CERVICAL_POSITION, Collections.singletonList(HealthDataTypes.TYPE_CERVICAL_POSITION));
        zzac.put(HealthDataTypes.TYPE_MENSTRUATION, Collections.singletonList(HealthDataTypes.TYPE_MENSTRUATION));
        zzac.put(HealthDataTypes.TYPE_OVULATION_TEST, Collections.singletonList(HealthDataTypes.TYPE_OVULATION_TEST));
        zzac.put(HealthDataTypes.TYPE_VAGINAL_SPOTTING, Collections.singletonList(HealthDataTypes.TYPE_VAGINAL_SPOTTING));
    }
}
