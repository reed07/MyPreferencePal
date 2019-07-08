package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable.Columns;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences.MacroGoalFormat;

@Class(creator = "FieldCreator")
@Reserved({1000})
public final class Field extends AbstractSafeParcelable {
    public static final Creator<Field> CREATOR = new zzq();
    public static final Field FIELD_ACCURACY = zzf("accuracy");
    public static final Field FIELD_ACTIVITY = zzd(AbstractEvent.ACTIVITY);
    public static final Field FIELD_ACTIVITY_CONFIDENCE = zzh("activity_confidence");
    public static final Field FIELD_ALTITUDE = new Field("altitude", 2, Boolean.valueOf(true));
    public static final Field FIELD_AVERAGE = zzf("average");
    public static final Field FIELD_BPM = zzf("bpm");
    public static final Field FIELD_CALORIES = zzf("calories");
    public static final Field FIELD_CIRCUMFERENCE = zzf("circumference");
    public static final Field FIELD_CONFIDENCE = zzf("confidence");
    public static final Field FIELD_DISTANCE = zzf("distance");
    public static final Field FIELD_DURATION = zzd("duration");
    public static final Field FIELD_EXERCISE = zzg("exercise");
    public static final Field FIELD_FOOD_ITEM = zzg("food_item");
    public static final Field FIELD_HEIGHT = zzf("height");
    public static final Field FIELD_HIGH_LATITUDE = zzf("high_latitude");
    public static final Field FIELD_HIGH_LONGITUDE = zzf("high_longitude");
    public static final Field FIELD_INTENSITY = zzf("intensity");
    public static final Field FIELD_LATITUDE = zzf("latitude");
    public static final Field FIELD_LONGITUDE = zzf("longitude");
    public static final Field FIELD_LOW_LATITUDE = zzf("low_latitude");
    public static final Field FIELD_LOW_LONGITUDE = zzf("low_longitude");
    public static final Field FIELD_MAX = zzf("max");
    public static final Field FIELD_MEAL_TYPE = zzd("meal_type");
    public static final Field FIELD_MIN = zzf("min");
    public static final Field FIELD_NUM_SEGMENTS = zzd("num_segments");
    public static final Field FIELD_NUTRIENTS = zzh("nutrients");
    public static final Field FIELD_OCCURRENCES = zzd("occurrences");
    public static final Field FIELD_PERCENTAGE = zzf(MacroGoalFormat.PERCENTAGE);
    public static final Field FIELD_REPETITIONS = zzd(Columns.REPETITIONS);
    public static final Field FIELD_RESISTANCE = zzf("resistance");
    public static final Field FIELD_RESISTANCE_TYPE = zzd("resistance_type");
    public static final Field FIELD_REVOLUTIONS = zzd("revolutions");
    public static final Field FIELD_RPM = zzf("rpm");
    public static final Field FIELD_SPEED = zzf("speed");
    public static final Field FIELD_STEPS = zzd("steps");
    public static final Field FIELD_STEP_LENGTH = zzf("step_length");
    public static final Field FIELD_VOLUME = zzf("volume");
    public static final Field FIELD_WATTS = zzf("watts");
    public static final Field FIELD_WEIGHT = zzf("weight");
    public static final int FORMAT_FLOAT = 2;
    public static final int FORMAT_INT32 = 1;
    public static final int FORMAT_MAP = 4;
    public static final int FORMAT_STRING = 3;
    public static final int MEAL_TYPE_BREAKFAST = 1;
    public static final int MEAL_TYPE_DINNER = 3;
    public static final int MEAL_TYPE_LUNCH = 2;
    public static final int MEAL_TYPE_SNACK = 4;
    public static final int MEAL_TYPE_UNKNOWN = 0;
    public static final String NUTRIENT_CALCIUM = "calcium";
    public static final String NUTRIENT_CALORIES = "calories";
    public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
    public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
    public static final String NUTRIENT_IRON = "iron";
    public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
    public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
    public static final String NUTRIENT_POTASSIUM = "potassium";
    public static final String NUTRIENT_PROTEIN = "protein";
    public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
    public static final String NUTRIENT_SODIUM = "sodium";
    public static final String NUTRIENT_SUGAR = "sugar";
    public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
    public static final String NUTRIENT_TOTAL_FAT = "fat.total";
    public static final String NUTRIENT_TRANS_FAT = "fat.trans";
    public static final String NUTRIENT_UNSATURATED_FAT = "fat.unsaturated";
    public static final String NUTRIENT_VITAMIN_A = "vitamin_a";
    public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
    public static final int RESISTANCE_TYPE_BARBELL = 1;
    public static final int RESISTANCE_TYPE_BODY = 6;
    public static final int RESISTANCE_TYPE_CABLE = 2;
    public static final int RESISTANCE_TYPE_DUMBBELL = 3;
    public static final int RESISTANCE_TYPE_KETTLEBELL = 4;
    public static final int RESISTANCE_TYPE_MACHINE = 5;
    public static final int RESISTANCE_TYPE_UNKNOWN = 0;
    private static final Field zzcg = zze("duration");
    private static final Field zzch = zzh("activity_duration");
    public static final Field zzci = zzh("activity_duration.ascending");
    public static final Field zzcj = zzh("activity_duration.descending");
    public static final Field zzck = zzi("google.android.fitness.GoalV2");
    public static final Field zzcl = zzi("prescription_event");
    public static final Field zzcm = zzi("symptom");
    public static final Field zzcn = zzi("google.android.fitness.StrideModel");
    public static final Field zzco = zzi("google.android.fitness.Device");
    public static final Field zzcp = zzf("elevation.change");
    public static final Field zzcq = zzh("elevation.gain");
    public static final Field zzcr = zzh("elevation.loss");
    public static final Field zzcs = zzf("floors");
    public static final Field zzct = zzh("floor.gain");
    public static final Field zzcu = zzh("floor.loss");
    public static final Field zzcv = zzd("sensor_type");
    public static final Field zzcw = zzd("sensor_types");
    public static final Field zzcx = new Field("timestamps", 5);
    public static final Field zzcy = zzd("sample_period");
    public static final Field zzcz = zzd("num_samples");
    public static final Field zzda = zzd("num_dimensions");
    public static final Field zzdb = new Field("sensor_values", 6);
    public static final Field zzdc = zzf("probability");
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getFormat", id = 2)
    private final int format;
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getName", id = 1)
    private final String name;
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isOptional", id = 3)
    private final Boolean zzdd;

    public static class zza {
        public static final Field zzde = Field.zzf(AvidJSONUtil.KEY_X);
        public static final Field zzdf = Field.zzf("y");
        public static final Field zzdg = Field.zzf("z");
        public static final Field zzdh = Field.zzj("debug_session");
        public static final Field zzdi = Field.zzj("google.android.fitness.SessionV2");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.fitness.data.Field zza(java.lang.String r2, int r3) {
        /*
            int r0 = r2.hashCode()
            switch(r0) {
                case -2131707655: goto L_0x049e;
                case -2083865430: goto L_0x0493;
                case -2006370880: goto L_0x0488;
                case -1992012396: goto L_0x047d;
                case -1859447186: goto L_0x0472;
                case -1743016407: goto L_0x0467;
                case -1655966961: goto L_0x045d;
                case -1595712862: goto L_0x0452;
                case -1579612127: goto L_0x0447;
                case -1579449403: goto L_0x043b;
                case -1569430471: goto L_0x042f;
                case -1531570079: goto L_0x0423;
                case -1440707631: goto L_0x0417;
                case -1439978388: goto L_0x040b;
                case -1352492506: goto L_0x03ff;
                case -1290561483: goto L_0x03f3;
                case -1271636505: goto L_0x03e7;
                case -1248595573: goto L_0x03db;
                case -1221029593: goto L_0x03cf;
                case -1220952307: goto L_0x03c3;
                case -1133736764: goto L_0x03b8;
                case -1129337776: goto L_0x03ac;
                case -1110756780: goto L_0x03a0;
                case -921832806: goto L_0x0394;
                case -918978307: goto L_0x0388;
                case -810883302: goto L_0x037c;
                case -803244749: goto L_0x0370;
                case -791592328: goto L_0x0364;
                case -631448035: goto L_0x0359;
                case -626344110: goto L_0x034d;
                case -619868540: goto L_0x0341;
                case -511934137: goto L_0x0335;
                case -494782871: goto L_0x0329;
                case -452643911: goto L_0x031d;
                case -437053898: goto L_0x0311;
                case -277306353: goto L_0x0305;
                case -266093204: goto L_0x02f9;
                case -228366862: goto L_0x02ed;
                case -168965370: goto L_0x02e1;
                case -126538880: goto L_0x02d5;
                case -28590302: goto L_0x02c9;
                case 120: goto L_0x02bd;
                case 121: goto L_0x02b1;
                case 122: goto L_0x02a5;
                case 97759: goto L_0x0299;
                case 107876: goto L_0x028d;
                case 108114: goto L_0x0281;
                case 113135: goto L_0x0275;
                case 66639641: goto L_0x0269;
                case 109641799: goto L_0x025d;
                case 109761319: goto L_0x0251;
                case 112903913: goto L_0x0245;
                case 120904628: goto L_0x0239;
                case 137365935: goto L_0x022d;
                case 198162679: goto L_0x0221;
                case 220648413: goto L_0x0215;
                case 248891292: goto L_0x0209;
                case 286612066: goto L_0x01fe;
                case 288459765: goto L_0x01f2;
                case 292126261: goto L_0x01e6;
                case 306600408: goto L_0x01da;
                case 320627489: goto L_0x01ce;
                case 419669488: goto L_0x01c2;
                case 455965230: goto L_0x01b7;
                case 475560024: goto L_0x01ab;
                case 475560262: goto L_0x019f;
                case 499324979: goto L_0x0193;
                case 514168969: goto L_0x0187;
                case 581888402: goto L_0x017b;
                case 623947695: goto L_0x016f;
                case 738210934: goto L_0x0163;
                case 784486594: goto L_0x0157;
                case 811264586: goto L_0x014b;
                case 815736413: goto L_0x013f;
                case 829251210: goto L_0x0133;
                case 833248065: goto L_0x0127;
                case 883161687: goto L_0x011b;
                case 984367650: goto L_0x010f;
                case 998412730: goto L_0x0104;
                case 1136011766: goto L_0x00f8;
                case 1276952063: goto L_0x00ec;
                case 1284575222: goto L_0x00e0;
                case 1284575460: goto L_0x00d4;
                case 1403812644: goto L_0x00c8;
                case 1403812882: goto L_0x00bc;
                case 1527920799: goto L_0x00b0;
                case 1708915229: goto L_0x00a4;
                case 1857734768: goto L_0x0098;
                case 1857897492: goto L_0x008c;
                case 1863800889: goto L_0x0080;
                case 1880897007: goto L_0x0074;
                case 1892583496: goto L_0x0068;
                case 1958191058: goto L_0x005c;
                case 1958191296: goto L_0x0050;
                case 1983072038: goto L_0x0044;
                case 2020153105: goto L_0x0038;
                case 2036550306: goto L_0x002d;
                case 2056323544: goto L_0x0021;
                case 2072582505: goto L_0x0015;
                case 2078370221: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x04a8
        L_0x0009:
            java.lang.String r0 = "supplemental_oxygen_flow_rate"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 72
            goto L_0x04a9
        L_0x0015:
            java.lang.String r0 = "cervical_firmness"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 25
            goto L_0x04a9
        L_0x0021:
            java.lang.String r0 = "exercise"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 37
            goto L_0x04a9
        L_0x002d:
            java.lang.String r0 = "altitude"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 6
            goto L_0x04a9
        L_0x0038:
            java.lang.String r0 = "blood_pressure_systolic_average"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 16
            goto L_0x04a9
        L_0x0044:
            java.lang.String r0 = "body_position"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 19
            goto L_0x04a9
        L_0x0050:
            java.lang.String r0 = "supplemental_oxygen_flow_rate_min"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 75
            goto L_0x04a9
        L_0x005c:
            java.lang.String r0 = "supplemental_oxygen_flow_rate_max"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 74
            goto L_0x04a9
        L_0x0068:
            java.lang.String r0 = "menstrual_flow"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 52
            goto L_0x04a9
        L_0x0074:
            java.lang.String r0 = "oxygen_therapy_administration_mode"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 66
            goto L_0x04a9
        L_0x0080:
            java.lang.String r0 = "resistance"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 80
            goto L_0x04a9
        L_0x008c:
            java.lang.String r0 = "elevation.loss"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 36
            goto L_0x04a9
        L_0x0098:
            java.lang.String r0 = "elevation.gain"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 35
            goto L_0x04a9
        L_0x00a4:
            java.lang.String r0 = "timestamps"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 67
            goto L_0x04a9
        L_0x00b0:
            java.lang.String r0 = "sensor_type"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 69
            goto L_0x04a9
        L_0x00bc:
            java.lang.String r0 = "blood_pressure_diastolic_min"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 13
            goto L_0x04a9
        L_0x00c8:
            java.lang.String r0 = "blood_pressure_diastolic_max"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 12
            goto L_0x04a9
        L_0x00d4:
            java.lang.String r0 = "oxygen_saturation_min"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 64
            goto L_0x04a9
        L_0x00e0:
            java.lang.String r0 = "oxygen_saturation_max"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 62
            goto L_0x04a9
        L_0x00ec:
            java.lang.String r0 = "blood_pressure_diastolic"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 10
            goto L_0x04a9
        L_0x00f8:
            java.lang.String r0 = "sample_period"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 68
            goto L_0x04a9
        L_0x0104:
            java.lang.String r0 = "activity_confidence"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 2
            goto L_0x04a9
        L_0x010f:
            java.lang.String r0 = "repetitions"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 79
            goto L_0x04a9
        L_0x011b:
            java.lang.String r0 = "body_temperature"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 20
            goto L_0x04a9
        L_0x0127:
            java.lang.String r0 = "temporal_relation_to_meal"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 89
            goto L_0x04a9
        L_0x0133:
            java.lang.String r0 = "confidence"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 30
            goto L_0x04a9
        L_0x013f:
            java.lang.String r0 = "oxygen_saturation_system"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 65
            goto L_0x04a9
        L_0x014b:
            java.lang.String r0 = "revolutions"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 82
            goto L_0x04a9
        L_0x0157:
            java.lang.String r0 = "occurrences"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 58
            goto L_0x04a9
        L_0x0163:
            java.lang.String r0 = "google.android.fitness.StrideModel"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 88
            goto L_0x04a9
        L_0x016f:
            java.lang.String r0 = "oxygen_saturation_average"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 61
            goto L_0x04a9
        L_0x017b:
            java.lang.String r0 = "cervical_mucus_amount"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 26
            goto L_0x04a9
        L_0x0187:
            java.lang.String r0 = "google.android.fitness.GoalV2"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 87
            goto L_0x04a9
        L_0x0193:
            java.lang.String r0 = "intensity"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 43
            goto L_0x04a9
        L_0x019f:
            java.lang.String r0 = "blood_pressure_systolic_min"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 18
            goto L_0x04a9
        L_0x01ab:
            java.lang.String r0 = "blood_pressure_systolic_max"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 17
            goto L_0x04a9
        L_0x01b7:
            java.lang.String r0 = "activity_duration.ascending"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 4
            goto L_0x04a9
        L_0x01c2:
            java.lang.String r0 = "google.android.fitness.Device"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 31
            goto L_0x04a9
        L_0x01ce:
            java.lang.String r0 = "cervical_mucus_texture"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 27
            goto L_0x04a9
        L_0x01da:
            java.lang.String r0 = "google.android.fitness.SessionV2"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 98
            goto L_0x04a9
        L_0x01e6:
            java.lang.String r0 = "prescription_event"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 76
            goto L_0x04a9
        L_0x01f2:
            java.lang.String r0 = "distance"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 32
            goto L_0x04a9
        L_0x01fe:
            java.lang.String r0 = "activity_duration.descending"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 5
            goto L_0x04a9
        L_0x0209:
            java.lang.String r0 = "blood_glucose_specimen_source"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 9
            goto L_0x04a9
        L_0x0215:
            java.lang.String r0 = "blood_pressure_diastolic_average"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 11
            goto L_0x04a9
        L_0x0221:
            java.lang.String r0 = "low_latitude"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 48
            goto L_0x04a9
        L_0x022d:
            java.lang.String r0 = "longitude"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 47
            goto L_0x04a9
        L_0x0239:
            java.lang.String r0 = "sensor_types"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 70
            goto L_0x04a9
        L_0x0245:
            java.lang.String r0 = "watts"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 92
            goto L_0x04a9
        L_0x0251:
            java.lang.String r0 = "steps"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 85
            goto L_0x04a9
        L_0x025d:
            java.lang.String r0 = "speed"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 84
            goto L_0x04a9
        L_0x0269:
            java.lang.String r0 = "temporal_relation_to_sleep"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 90
            goto L_0x04a9
        L_0x0275:
            java.lang.String r0 = "rpm"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 83
            goto L_0x04a9
        L_0x0281:
            java.lang.String r0 = "min"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 53
            goto L_0x04a9
        L_0x028d:
            java.lang.String r0 = "max"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 50
            goto L_0x04a9
        L_0x0299:
            java.lang.String r0 = "bpm"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 22
            goto L_0x04a9
        L_0x02a5:
            java.lang.String r0 = "z"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 96
            goto L_0x04a9
        L_0x02b1:
            java.lang.String r0 = "y"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 95
            goto L_0x04a9
        L_0x02bd:
            java.lang.String r0 = "x"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 94
            goto L_0x04a9
        L_0x02c9:
            java.lang.String r0 = "ovulation_test_result"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 59
            goto L_0x04a9
        L_0x02d5:
            java.lang.String r0 = "resistance_type"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 81
            goto L_0x04a9
        L_0x02e1:
            java.lang.String r0 = "calories"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 23
            goto L_0x04a9
        L_0x02ed:
            java.lang.String r0 = "oxygen_saturation_measurement_method"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 63
            goto L_0x04a9
        L_0x02f9:
            java.lang.String r0 = "nutrients"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 57
            goto L_0x04a9
        L_0x0305:
            java.lang.String r0 = "circumference"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 29
            goto L_0x04a9
        L_0x0311:
            java.lang.String r0 = "meal_type"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 51
            goto L_0x04a9
        L_0x031d:
            java.lang.String r0 = "step_length"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 86
            goto L_0x04a9
        L_0x0329:
            java.lang.String r0 = "high_latitude"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 44
            goto L_0x04a9
        L_0x0335:
            java.lang.String r0 = "sensor_values"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 71
            goto L_0x04a9
        L_0x0341:
            java.lang.String r0 = "low_longitude"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 49
            goto L_0x04a9
        L_0x034d:
            java.lang.String r0 = "high_longitude"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 45
            goto L_0x04a9
        L_0x0359:
            java.lang.String r0 = "average"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 7
            goto L_0x04a9
        L_0x0364:
            java.lang.String r0 = "weight"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 93
            goto L_0x04a9
        L_0x0370:
            java.lang.String r0 = "blood_pressure_systolic"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 15
            goto L_0x04a9
        L_0x037c:
            java.lang.String r0 = "volume"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 91
            goto L_0x04a9
        L_0x0388:
            java.lang.String r0 = "cervical_position"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 28
            goto L_0x04a9
        L_0x0394:
            java.lang.String r0 = "percentage"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 78
            goto L_0x04a9
        L_0x03a0:
            java.lang.String r0 = "food_item"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 41
            goto L_0x04a9
        L_0x03ac:
            java.lang.String r0 = "num_samples"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 55
            goto L_0x04a9
        L_0x03b8:
            java.lang.String r0 = "activity_duration"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 3
            goto L_0x04a9
        L_0x03c3:
            java.lang.String r0 = "blood_pressure_measurement_location"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 14
            goto L_0x04a9
        L_0x03cf:
            java.lang.String r0 = "height"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 42
            goto L_0x04a9
        L_0x03db:
            java.lang.String r0 = "supplemental_oxygen_flow_rate_average"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 73
            goto L_0x04a9
        L_0x03e7:
            java.lang.String r0 = "floors"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 40
            goto L_0x04a9
        L_0x03f3:
            java.lang.String r0 = "probability"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 99
            goto L_0x04a9
        L_0x03ff:
            java.lang.String r0 = "num_dimensions"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 54
            goto L_0x04a9
        L_0x040b:
            java.lang.String r0 = "latitude"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 46
            goto L_0x04a9
        L_0x0417:
            java.lang.String r0 = "oxygen_saturation"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 60
            goto L_0x04a9
        L_0x0423:
            java.lang.String r0 = "elevation.change"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 34
            goto L_0x04a9
        L_0x042f:
            java.lang.String r0 = "num_segments"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 56
            goto L_0x04a9
        L_0x043b:
            java.lang.String r0 = "floor.loss"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 39
            goto L_0x04a9
        L_0x0447:
            java.lang.String r0 = "floor.gain"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 38
            goto L_0x04a9
        L_0x0452:
            java.lang.String r0 = "cervical_dilation"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 24
            goto L_0x04a9
        L_0x045d:
            java.lang.String r0 = "activity"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 1
            goto L_0x04a9
        L_0x0467:
            java.lang.String r0 = "symptom"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 77
            goto L_0x04a9
        L_0x0472:
            java.lang.String r0 = "blood_glucose_level"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 8
            goto L_0x04a9
        L_0x047d:
            java.lang.String r0 = "duration"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 33
            goto L_0x04a9
        L_0x0488:
            java.lang.String r0 = "body_temperature_measurement_location"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 21
            goto L_0x04a9
        L_0x0493:
            java.lang.String r0 = "debug_session"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 97
            goto L_0x04a9
        L_0x049e:
            java.lang.String r0 = "accuracy"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x04a8
            r0 = 0
            goto L_0x04a9
        L_0x04a8:
            r0 = -1
        L_0x04a9:
            switch(r0) {
                case 0: goto L_0x05dc;
                case 1: goto L_0x05d9;
                case 2: goto L_0x05d6;
                case 3: goto L_0x05d3;
                case 4: goto L_0x05d0;
                case 5: goto L_0x05cd;
                case 6: goto L_0x05ca;
                case 7: goto L_0x05c7;
                case 8: goto L_0x05c4;
                case 9: goto L_0x05c1;
                case 10: goto L_0x05be;
                case 11: goto L_0x05bb;
                case 12: goto L_0x05b8;
                case 13: goto L_0x05b5;
                case 14: goto L_0x05b2;
                case 15: goto L_0x05af;
                case 16: goto L_0x05ac;
                case 17: goto L_0x05a9;
                case 18: goto L_0x05a6;
                case 19: goto L_0x05a3;
                case 20: goto L_0x05a0;
                case 21: goto L_0x059d;
                case 22: goto L_0x059a;
                case 23: goto L_0x0597;
                case 24: goto L_0x0594;
                case 25: goto L_0x0591;
                case 26: goto L_0x058e;
                case 27: goto L_0x058b;
                case 28: goto L_0x0588;
                case 29: goto L_0x0585;
                case 30: goto L_0x0582;
                case 31: goto L_0x057f;
                case 32: goto L_0x057c;
                case 33: goto L_0x0579;
                case 34: goto L_0x0576;
                case 35: goto L_0x0573;
                case 36: goto L_0x0570;
                case 37: goto L_0x056d;
                case 38: goto L_0x056a;
                case 39: goto L_0x0567;
                case 40: goto L_0x0564;
                case 41: goto L_0x0561;
                case 42: goto L_0x055e;
                case 43: goto L_0x055b;
                case 44: goto L_0x0558;
                case 45: goto L_0x0555;
                case 46: goto L_0x0552;
                case 47: goto L_0x054f;
                case 48: goto L_0x054c;
                case 49: goto L_0x0549;
                case 50: goto L_0x0546;
                case 51: goto L_0x0543;
                case 52: goto L_0x0540;
                case 53: goto L_0x053d;
                case 54: goto L_0x053a;
                case 55: goto L_0x0537;
                case 56: goto L_0x0534;
                case 57: goto L_0x0531;
                case 58: goto L_0x052e;
                case 59: goto L_0x052b;
                case 60: goto L_0x0528;
                case 61: goto L_0x0525;
                case 62: goto L_0x0522;
                case 63: goto L_0x051f;
                case 64: goto L_0x051c;
                case 65: goto L_0x0519;
                case 66: goto L_0x0516;
                case 67: goto L_0x0513;
                case 68: goto L_0x0510;
                case 69: goto L_0x050d;
                case 70: goto L_0x050a;
                case 71: goto L_0x0507;
                case 72: goto L_0x0504;
                case 73: goto L_0x0501;
                case 74: goto L_0x04fe;
                case 75: goto L_0x04fb;
                case 76: goto L_0x04f8;
                case 77: goto L_0x04f5;
                case 78: goto L_0x04f2;
                case 79: goto L_0x04ef;
                case 80: goto L_0x04ec;
                case 81: goto L_0x04e9;
                case 82: goto L_0x04e6;
                case 83: goto L_0x04e3;
                case 84: goto L_0x04e0;
                case 85: goto L_0x04dd;
                case 86: goto L_0x04da;
                case 87: goto L_0x04d7;
                case 88: goto L_0x04d4;
                case 89: goto L_0x04d1;
                case 90: goto L_0x04ce;
                case 91: goto L_0x04cb;
                case 92: goto L_0x04c8;
                case 93: goto L_0x04c5;
                case 94: goto L_0x04c2;
                case 95: goto L_0x04bf;
                case 96: goto L_0x04bc;
                case 97: goto L_0x04b9;
                case 98: goto L_0x04b6;
                case 99: goto L_0x04b3;
                default: goto L_0x04ac;
            }
        L_0x04ac:
            com.google.android.gms.fitness.data.Field r0 = new com.google.android.gms.fitness.data.Field
            r1 = 0
            r0.<init>(r2, r3, r1)
            return r0
        L_0x04b3:
            com.google.android.gms.fitness.data.Field r2 = zzdc
            return r2
        L_0x04b6:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.Field.zza.zzdi
            return r2
        L_0x04b9:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.Field.zza.zzdh
            return r2
        L_0x04bc:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.Field.zza.zzdg
            return r2
        L_0x04bf:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.Field.zza.zzdf
            return r2
        L_0x04c2:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.Field.zza.zzde
            return r2
        L_0x04c5:
            com.google.android.gms.fitness.data.Field r2 = FIELD_WEIGHT
            return r2
        L_0x04c8:
            com.google.android.gms.fitness.data.Field r2 = FIELD_WATTS
            return r2
        L_0x04cb:
            com.google.android.gms.fitness.data.Field r2 = FIELD_VOLUME
            return r2
        L_0x04ce:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP
            return r2
        L_0x04d1:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL
            return r2
        L_0x04d4:
            com.google.android.gms.fitness.data.Field r2 = zzcn
            return r2
        L_0x04d7:
            com.google.android.gms.fitness.data.Field r2 = zzck
            return r2
        L_0x04da:
            com.google.android.gms.fitness.data.Field r2 = FIELD_STEP_LENGTH
            return r2
        L_0x04dd:
            com.google.android.gms.fitness.data.Field r2 = FIELD_STEPS
            return r2
        L_0x04e0:
            com.google.android.gms.fitness.data.Field r2 = FIELD_SPEED
            return r2
        L_0x04e3:
            com.google.android.gms.fitness.data.Field r2 = FIELD_RPM
            return r2
        L_0x04e6:
            com.google.android.gms.fitness.data.Field r2 = FIELD_REVOLUTIONS
            return r2
        L_0x04e9:
            com.google.android.gms.fitness.data.Field r2 = FIELD_RESISTANCE_TYPE
            return r2
        L_0x04ec:
            com.google.android.gms.fitness.data.Field r2 = FIELD_RESISTANCE
            return r2
        L_0x04ef:
            com.google.android.gms.fitness.data.Field r2 = FIELD_REPETITIONS
            return r2
        L_0x04f2:
            com.google.android.gms.fitness.data.Field r2 = FIELD_PERCENTAGE
            return r2
        L_0x04f5:
            com.google.android.gms.fitness.data.Field r2 = zzcm
            return r2
        L_0x04f8:
            com.google.android.gms.fitness.data.Field r2 = zzcl
            return r2
        L_0x04fb:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN
            return r2
        L_0x04fe:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX
            return r2
        L_0x0501:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE
            return r2
        L_0x0504:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE
            return r2
        L_0x0507:
            com.google.android.gms.fitness.data.Field r2 = zzdb
            return r2
        L_0x050a:
            com.google.android.gms.fitness.data.Field r2 = zzcw
            return r2
        L_0x050d:
            com.google.android.gms.fitness.data.Field r2 = zzcv
            return r2
        L_0x0510:
            com.google.android.gms.fitness.data.Field r2 = zzcy
            return r2
        L_0x0513:
            com.google.android.gms.fitness.data.Field r2 = zzcx
            return r2
        L_0x0516:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE
            return r2
        L_0x0519:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM
            return r2
        L_0x051c:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MIN
            return r2
        L_0x051f:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD
            return r2
        L_0x0522:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MAX
            return r2
        L_0x0525:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_AVERAGE
            return r2
        L_0x0528:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION
            return r2
        L_0x052b:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_OVULATION_TEST_RESULT
            return r2
        L_0x052e:
            com.google.android.gms.fitness.data.Field r2 = FIELD_OCCURRENCES
            return r2
        L_0x0531:
            com.google.android.gms.fitness.data.Field r2 = FIELD_NUTRIENTS
            return r2
        L_0x0534:
            com.google.android.gms.fitness.data.Field r2 = FIELD_NUM_SEGMENTS
            return r2
        L_0x0537:
            com.google.android.gms.fitness.data.Field r2 = zzcz
            return r2
        L_0x053a:
            com.google.android.gms.fitness.data.Field r2 = zzda
            return r2
        L_0x053d:
            com.google.android.gms.fitness.data.Field r2 = FIELD_MIN
            return r2
        L_0x0540:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_MENSTRUAL_FLOW
            return r2
        L_0x0543:
            com.google.android.gms.fitness.data.Field r2 = FIELD_MEAL_TYPE
            return r2
        L_0x0546:
            com.google.android.gms.fitness.data.Field r2 = FIELD_MAX
            return r2
        L_0x0549:
            com.google.android.gms.fitness.data.Field r2 = FIELD_LOW_LONGITUDE
            return r2
        L_0x054c:
            com.google.android.gms.fitness.data.Field r2 = FIELD_LOW_LATITUDE
            return r2
        L_0x054f:
            com.google.android.gms.fitness.data.Field r2 = FIELD_LONGITUDE
            return r2
        L_0x0552:
            com.google.android.gms.fitness.data.Field r2 = FIELD_LATITUDE
            return r2
        L_0x0555:
            com.google.android.gms.fitness.data.Field r2 = FIELD_HIGH_LONGITUDE
            return r2
        L_0x0558:
            com.google.android.gms.fitness.data.Field r2 = FIELD_HIGH_LATITUDE
            return r2
        L_0x055b:
            com.google.android.gms.fitness.data.Field r2 = FIELD_INTENSITY
            return r2
        L_0x055e:
            com.google.android.gms.fitness.data.Field r2 = FIELD_HEIGHT
            return r2
        L_0x0561:
            com.google.android.gms.fitness.data.Field r2 = FIELD_FOOD_ITEM
            return r2
        L_0x0564:
            com.google.android.gms.fitness.data.Field r2 = zzcs
            return r2
        L_0x0567:
            com.google.android.gms.fitness.data.Field r2 = zzcu
            return r2
        L_0x056a:
            com.google.android.gms.fitness.data.Field r2 = zzct
            return r2
        L_0x056d:
            com.google.android.gms.fitness.data.Field r2 = FIELD_EXERCISE
            return r2
        L_0x0570:
            com.google.android.gms.fitness.data.Field r2 = zzcr
            return r2
        L_0x0573:
            com.google.android.gms.fitness.data.Field r2 = zzcq
            return r2
        L_0x0576:
            com.google.android.gms.fitness.data.Field r2 = zzcp
            return r2
        L_0x0579:
            com.google.android.gms.fitness.data.Field r2 = FIELD_DURATION
            return r2
        L_0x057c:
            com.google.android.gms.fitness.data.Field r2 = FIELD_DISTANCE
            return r2
        L_0x057f:
            com.google.android.gms.fitness.data.Field r2 = zzco
            return r2
        L_0x0582:
            com.google.android.gms.fitness.data.Field r2 = FIELD_CONFIDENCE
            return r2
        L_0x0585:
            com.google.android.gms.fitness.data.Field r2 = FIELD_CIRCUMFERENCE
            return r2
        L_0x0588:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_POSITION
            return r2
        L_0x058b:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_MUCUS_TEXTURE
            return r2
        L_0x058e:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_MUCUS_AMOUNT
            return r2
        L_0x0591:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_FIRMNESS
            return r2
        L_0x0594:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_DILATION
            return r2
        L_0x0597:
            com.google.android.gms.fitness.data.Field r2 = FIELD_CALORIES
            return r2
        L_0x059a:
            com.google.android.gms.fitness.data.Field r2 = FIELD_BPM
            return r2
        L_0x059d:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION
            return r2
        L_0x05a0:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_TEMPERATURE
            return r2
        L_0x05a3:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_POSITION
            return r2
        L_0x05a6:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MIN
            return r2
        L_0x05a9:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MAX
            return r2
        L_0x05ac:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE
            return r2
        L_0x05af:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC
            return r2
        L_0x05b2:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION
            return r2
        L_0x05b5:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MIN
            return r2
        L_0x05b8:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MAX
            return r2
        L_0x05bb:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE
            return r2
        L_0x05be:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC
            return r2
        L_0x05c1:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE
            return r2
        L_0x05c4:
            com.google.android.gms.fitness.data.Field r2 = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_GLUCOSE_LEVEL
            return r2
        L_0x05c7:
            com.google.android.gms.fitness.data.Field r2 = FIELD_AVERAGE
            return r2
        L_0x05ca:
            com.google.android.gms.fitness.data.Field r2 = FIELD_ALTITUDE
            return r2
        L_0x05cd:
            com.google.android.gms.fitness.data.Field r2 = zzcj
            return r2
        L_0x05d0:
            com.google.android.gms.fitness.data.Field r2 = zzci
            return r2
        L_0x05d3:
            com.google.android.gms.fitness.data.Field r2 = zzch
            return r2
        L_0x05d6:
            com.google.android.gms.fitness.data.Field r2 = FIELD_ACTIVITY_CONFIDENCE
            return r2
        L_0x05d9:
            com.google.android.gms.fitness.data.Field r2 = FIELD_ACTIVITY
            return r2
        L_0x05dc:
            com.google.android.gms.fitness.data.Field r2 = FIELD_ACCURACY
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.Field.zza(java.lang.String, int):com.google.android.gms.fitness.data.Field");
    }

    private static Field zzd(String str) {
        return new Field(str, 1);
    }

    static Field zze(String str) {
        return new Field(str, 1, Boolean.valueOf(true));
    }

    static Field zzf(String str) {
        return new Field(str, 2);
    }

    private static Field zzg(String str) {
        return new Field(str, 3);
    }

    private static Field zzh(String str) {
        return new Field(str, 4);
    }

    private static Field zzi(String str) {
        return new Field(str, 7);
    }

    static Field zzj(String str) {
        return new Field(str, 7, Boolean.valueOf(true));
    }

    private Field(String str, int i) {
        this(str, i, null);
    }

    @Constructor
    Field(@Param(id = 1) String str, @Param(id = 2) int i, @Nullable @Param(id = 3) Boolean bool) {
        this.name = (String) Preconditions.checkNotNull(str);
        this.format = i;
        this.zzdd = bool;
    }

    public final String getName() {
        return this.name;
    }

    public final int getFormat() {
        return this.format;
    }

    @Nullable
    public final Boolean isOptional() {
        return this.zzdd;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        return this.name.equals(field.name) && this.format == field.format;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final String toString() {
        String str = "%s(%s)";
        Object[] objArr = new Object[2];
        objArr[0] = this.name;
        objArr[1] = this.format == 1 ? "i" : "f";
        return String.format(str, objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeInt(parcel, 2, getFormat());
        SafeParcelWriter.writeBooleanObject(parcel, 3, isOptional(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
