package com.google.android.gms.fitness.data;

public final class zzm {
    private static final DataType[] zzbx = {DataType.zzbi, DataType.TYPE_WORKOUT_EXERCISE, DataType.TYPE_MOVE_MINUTES, DataType.TYPE_ACTIVITY_SAMPLE, DataType.TYPE_ACTIVITY_SAMPLES, DataType.TYPE_ACTIVITY_SEGMENT, DataType.AGGREGATE_ACTIVITY_SUMMARY, HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE, HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY, HealthDataTypes.TYPE_BLOOD_GLUCOSE, HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY, HealthDataTypes.TYPE_BLOOD_PRESSURE, HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY, DataType.TYPE_BODY_FAT_PERCENTAGE, DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY, DataType.zzbm, DataType.zzbq, HealthDataTypes.TYPE_BODY_TEMPERATURE, HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY, DataType.zzbl, DataType.zzbr, DataType.TYPE_BASAL_METABOLIC_RATE, DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY, DataType.TYPE_CALORIES_CONSUMED, DataType.TYPE_CALORIES_EXPENDED, HealthDataTypes.TYPE_CERVICAL_MUCUS, HealthDataTypes.TYPE_CERVICAL_POSITION, DataType.TYPE_CYCLING_PEDALING_CADENCE, DataType.TYPE_CYCLING_PEDALING_CUMULATIVE, DataType.TYPE_CYCLING_WHEEL_REVOLUTION, DataType.TYPE_CYCLING_WHEEL_RPM, DataType.TYPE_DISTANCE_CUMULATIVE, DataType.TYPE_DISTANCE_DELTA, DataType.zzbh, DataType.zzbp, DataType.zzbd, DataType.zzbe, DataType.zzbf, DataType.TYPE_HEART_POINTS, DataType.AGGREGATE_HEART_POINTS, DataType.TYPE_HEART_RATE_BPM, DataType.AGGREGATE_HEART_RATE_SUMMARY, DataType.TYPE_HEIGHT, DataType.AGGREGATE_HEIGHT_SUMMARY, DataType.AGGREGATE_LOCATION_BOUNDING_BOX, DataType.TYPE_LOCATION_SAMPLE, DataType.TYPE_LOCATION_TRACK, HealthDataTypes.TYPE_MENSTRUATION, DataType.TYPE_NUTRITION, DataType.TYPE_HYDRATION, DataType.AGGREGATE_NUTRITION_SUMMARY, HealthDataTypes.TYPE_OVULATION_TEST, HealthDataTypes.TYPE_OXYGEN_SATURATION, HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY, DataType.TYPE_POWER_SAMPLE, DataType.AGGREGATE_POWER_SUMMARY, DataType.zzbk, DataType.zzbj, DataType.TYPE_SPEED, DataType.AGGREGATE_SPEED_SUMMARY, DataType.TYPE_STEP_COUNT_CADENCE, DataType.zzbg, DataType.TYPE_STEP_COUNT_CUMULATIVE, DataType.TYPE_STEP_COUNT_DELTA, DataType.zzbc, HealthDataTypes.TYPE_VAGINAL_SPOTTING, DataType.TYPE_WEIGHT, DataType.AGGREGATE_WEIGHT_SUMMARY, DataType.zzbn, DataType.zzbo};
    private static final DataType[] zzby = {HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE, HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY, HealthDataTypes.TYPE_BLOOD_GLUCOSE, HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY, HealthDataTypes.TYPE_BLOOD_PRESSURE, HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY, HealthDataTypes.TYPE_BODY_TEMPERATURE, HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY, HealthDataTypes.TYPE_CERVICAL_MUCUS, HealthDataTypes.TYPE_CERVICAL_POSITION, HealthDataTypes.TYPE_MENSTRUATION, HealthDataTypes.TYPE_OVULATION_TEST, HealthDataTypes.TYPE_OXYGEN_SATURATION, HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY, HealthDataTypes.TYPE_VAGINAL_SPOTTING};

    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.fitness.data.DataType zzc(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -2060095039: goto L_0x034d;
                case -2027664088: goto L_0x0342;
                case -2023954015: goto L_0x0337;
                case -2001464928: goto L_0x032c;
                case -1999891138: goto L_0x0321;
                case -1939429191: goto L_0x0316;
                case -1783842905: goto L_0x030c;
                case -1757812901: goto L_0x0301;
                case -1738991010: goto L_0x02f6;
                case -1659958877: goto L_0x02ea;
                case -1487055015: goto L_0x02de;
                case -1466904157: goto L_0x02d2;
                case -1465729060: goto L_0x02c6;
                case -1431431801: goto L_0x02ba;
                case -1248818137: goto L_0x02ae;
                case -1196687875: goto L_0x02a2;
                case -1103712522: goto L_0x0296;
                case -1102520626: goto L_0x028a;
                case -1099695423: goto L_0x027f;
                case -1091068721: goto L_0x0273;
                case -1063046895: goto L_0x0267;
                case -922976890: goto L_0x025b;
                case -900592674: goto L_0x024f;
                case -886569606: goto L_0x0243;
                case -777285735: goto L_0x0237;
                case -700668164: goto L_0x022b;
                case -661631456: goto L_0x021f;
                case -424876584: goto L_0x0213;
                case -362418992: goto L_0x0207;
                case -217611775: goto L_0x01fb;
                case -185830635: goto L_0x01ef;
                case -177293656: goto L_0x01e3;
                case -164586193: goto L_0x01d8;
                case -98150574: goto L_0x01cc;
                case -56824761: goto L_0x01c0;
                case -43729332: goto L_0x01b4;
                case 2484093: goto L_0x01a8;
                case 53773386: goto L_0x019c;
                case 269180370: goto L_0x0191;
                case 295793957: goto L_0x0185;
                case 296250623: goto L_0x0179;
                case 324760871: goto L_0x016d;
                case 378060028: goto L_0x0162;
                case 529727579: goto L_0x0156;
                case 634821360: goto L_0x014a;
                case 657433501: goto L_0x013e;
                case 682891187: goto L_0x0132;
                case 841663855: goto L_0x0127;
                case 877955159: goto L_0x011b;
                case 899666941: goto L_0x010f;
                case 936279698: goto L_0x0103;
                case 946706510: goto L_0x00f7;
                case 946938859: goto L_0x00eb;
                case 1029221057: goto L_0x00df;
                case 1098265835: goto L_0x00d3;
                case 1111714923: goto L_0x00c7;
                case 1214093899: goto L_0x00bb;
                case 1404118825: goto L_0x00af;
                case 1439932546: goto L_0x00a3;
                case 1483133089: goto L_0x0098;
                case 1524007137: goto L_0x008c;
                case 1532018766: goto L_0x0081;
                case 1633152752: goto L_0x0075;
                case 1674865156: goto L_0x0069;
                case 1819660853: goto L_0x005d;
                case 1921738212: goto L_0x0051;
                case 1925848149: goto L_0x0045;
                case 1975902189: goto L_0x0039;
                case 1980033842: goto L_0x002d;
                case 2051843553: goto L_0x0021;
                case 2053496735: goto L_0x0015;
                case 2131809416: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0358
        L_0x0009:
            java.lang.String r0 = "com.google.body.temperature.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 18
            goto L_0x0359
        L_0x0015:
            java.lang.String r0 = "com.google.speed"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 58
            goto L_0x0359
        L_0x0021:
            java.lang.String r0 = "com.google.oxygen_saturation.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 53
            goto L_0x0359
        L_0x002d:
            java.lang.String r0 = "com.google.internal.session.debug"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 59
            goto L_0x0359
        L_0x0039:
            java.lang.String r0 = "com.google.cervical_mucus"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 25
            goto L_0x0359
        L_0x0045:
            java.lang.String r0 = "com.google.cervical_position"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 26
            goto L_0x0359
        L_0x0051:
            java.lang.String r0 = "com.google.distance.cumulative"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 31
            goto L_0x0359
        L_0x005d:
            java.lang.String r0 = "com.google.body.waist.circumference.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 20
            goto L_0x0359
        L_0x0069:
            java.lang.String r0 = "com.google.body.hip.circumference.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 16
            goto L_0x0359
        L_0x0075:
            java.lang.String r0 = "com.google.nutrition"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 48
            goto L_0x0359
        L_0x0081:
            java.lang.String r0 = "com.google.active_minutes"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 1
            goto L_0x0359
        L_0x008c:
            java.lang.String r0 = "com.google.cycling.wheel_revolution.cumulative"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 29
            goto L_0x0359
        L_0x0098:
            java.lang.String r0 = "com.google.body.temperature.basal"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 7
            goto L_0x0359
        L_0x00a3:
            java.lang.String r0 = "com.google.ovulation_test"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 51
            goto L_0x0359
        L_0x00af:
            java.lang.String r0 = "com.google.oxygen_saturation"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 52
            goto L_0x0359
        L_0x00bb:
            java.lang.String r0 = "com.google.vaginal_spotting"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 67
            goto L_0x0359
        L_0x00c7:
            java.lang.String r0 = "com.google.body.fat.percentage.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 14
            goto L_0x0359
        L_0x00d3:
            java.lang.String r0 = "com.google.floor_change"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 33
            goto L_0x0359
        L_0x00df:
            java.lang.String r0 = "com.google.device_on_body"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 70
            goto L_0x0359
        L_0x00eb:
            java.lang.String r0 = "com.google.stride_model"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 66
            goto L_0x0359
        L_0x00f7:
            java.lang.String r0 = "com.google.hydration"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 49
            goto L_0x0359
        L_0x0103:
            java.lang.String r0 = "com.google.blood_pressure"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 12
            goto L_0x0359
        L_0x010f:
            java.lang.String r0 = "com.google.calories.expended"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 24
            goto L_0x0359
        L_0x011b:
            java.lang.String r0 = "com.google.speed.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 61
            goto L_0x0359
        L_0x0127:
            java.lang.String r0 = "com.google.activity.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 6
            goto L_0x0359
        L_0x0132:
            java.lang.String r0 = "com.google.body.fat.percentage"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 9
            goto L_0x0359
        L_0x013e:
            java.lang.String r0 = "com.google.step_count.cumulative"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 63
            goto L_0x0359
        L_0x014a:
            java.lang.String r0 = "com.google.sensor.const_rate_events"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 56
            goto L_0x0359
        L_0x0156:
            java.lang.String r0 = "com.google.power.sample"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 54
            goto L_0x0359
        L_0x0162:
            java.lang.String r0 = "com.google.activity.segment"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 5
            goto L_0x0359
        L_0x016d:
            java.lang.String r0 = "com.google.step_count.cadence"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 62
            goto L_0x0359
        L_0x0179:
            java.lang.String r0 = "com.google.calories.bmr.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 22
            goto L_0x0359
        L_0x0185:
            java.lang.String r0 = "com.google.sensor.events"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 57
            goto L_0x0359
        L_0x0191:
            java.lang.String r0 = "com.google.activity.samples"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 4
            goto L_0x0359
        L_0x019c:
            java.lang.String r0 = "com.google.blood_pressure.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 13
            goto L_0x0359
        L_0x01a8:
            java.lang.String r0 = "com.google.body.waist.circumference"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 19
            goto L_0x0359
        L_0x01b4:
            java.lang.String r0 = "com.google.body.hip.circumference"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 15
            goto L_0x0359
        L_0x01c0:
            java.lang.String r0 = "com.google.calories.bmr"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 21
            goto L_0x0359
        L_0x01cc:
            java.lang.String r0 = "com.google.heart_rate.bpm"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 40
            goto L_0x0359
        L_0x01d8:
            java.lang.String r0 = "com.google.activity.exercise"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 2
            goto L_0x0359
        L_0x01e3:
            java.lang.String r0 = "com.google.nutrition.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 50
            goto L_0x0359
        L_0x01ef:
            java.lang.String r0 = "com.google.power.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 55
            goto L_0x0359
        L_0x01fb:
            java.lang.String r0 = "com.google.blood_glucose"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 10
            goto L_0x0359
        L_0x0207:
            java.lang.String r0 = "com.google.body.temperature"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 17
            goto L_0x0359
        L_0x0213:
            java.lang.String r0 = "com.google.weight.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 69
            goto L_0x0359
        L_0x021f:
            java.lang.String r0 = "com.google.weight"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 68
            goto L_0x0359
        L_0x022b:
            java.lang.String r0 = "com.google.internal.goal"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 35
            goto L_0x0359
        L_0x0237:
            java.lang.String r0 = "com.google.heart_rate.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 41
            goto L_0x0359
        L_0x0243:
            java.lang.String r0 = "com.google.location.track"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 46
            goto L_0x0359
        L_0x024f:
            java.lang.String r0 = "com.google.cycling.pedaling.cadence"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 27
            goto L_0x0359
        L_0x025b:
            java.lang.String r0 = "com.google.cycling.pedaling.cumulative"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 28
            goto L_0x0359
        L_0x0267:
            java.lang.String r0 = "com.google.step_length"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 65
            goto L_0x0359
        L_0x0273:
            java.lang.String r0 = "com.google.height"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 42
            goto L_0x0359
        L_0x027f:
            java.lang.String r0 = "com.google.activity.sample"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 3
            goto L_0x0359
        L_0x028a:
            java.lang.String r0 = "com.google.step_count.delta"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 64
            goto L_0x0359
        L_0x0296:
            java.lang.String r0 = "com.google.heart_minutes.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 39
            goto L_0x0359
        L_0x02a2:
            java.lang.String r0 = "com.google.internal.session.v2"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 60
            goto L_0x0359
        L_0x02ae:
            java.lang.String r0 = "com.google.distance.delta"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 32
            goto L_0x0359
        L_0x02ba:
            java.lang.String r0 = "com.google.height.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 43
            goto L_0x0359
        L_0x02c6:
            java.lang.String r0 = "com.google.internal.primary_device"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 71
            goto L_0x0359
        L_0x02d2:
            java.lang.String r0 = "com.google.floor_change.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 34
            goto L_0x0359
        L_0x02de:
            java.lang.String r0 = "com.google.body.temperature.basal.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 8
            goto L_0x0359
        L_0x02ea:
            java.lang.String r0 = "com.google.menstruation"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 47
            goto L_0x0359
        L_0x02f6:
            java.lang.String r0 = "com.google.internal.prescription_event"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 36
            goto L_0x0359
        L_0x0301:
            java.lang.String r0 = "com.google.location.sample"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 45
            goto L_0x0359
        L_0x030c:
            java.lang.String r0 = "com.google.accelerometer"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 0
            goto L_0x0359
        L_0x0316:
            java.lang.String r0 = "com.google.blood_glucose.summary"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 11
            goto L_0x0359
        L_0x0321:
            java.lang.String r0 = "com.google.heart_minutes"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 38
            goto L_0x0359
        L_0x032c:
            java.lang.String r0 = "com.google.internal.symptom"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 37
            goto L_0x0359
        L_0x0337:
            java.lang.String r0 = "com.google.location.bounding_box"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 44
            goto L_0x0359
        L_0x0342:
            java.lang.String r0 = "com.google.calories.consumed"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 23
            goto L_0x0359
        L_0x034d:
            java.lang.String r0 = "com.google.cycling.wheel_revolution.rpm"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0358
            r1 = 30
            goto L_0x0359
        L_0x0358:
            r1 = -1
        L_0x0359:
            switch(r1) {
                case 0: goto L_0x0433;
                case 1: goto L_0x0430;
                case 2: goto L_0x042d;
                case 3: goto L_0x042a;
                case 4: goto L_0x0427;
                case 5: goto L_0x0424;
                case 6: goto L_0x0421;
                case 7: goto L_0x041e;
                case 8: goto L_0x041b;
                case 9: goto L_0x0418;
                case 10: goto L_0x0415;
                case 11: goto L_0x0412;
                case 12: goto L_0x040f;
                case 13: goto L_0x040c;
                case 14: goto L_0x0409;
                case 15: goto L_0x0406;
                case 16: goto L_0x0403;
                case 17: goto L_0x0400;
                case 18: goto L_0x03fd;
                case 19: goto L_0x03fa;
                case 20: goto L_0x03f7;
                case 21: goto L_0x03f4;
                case 22: goto L_0x03f1;
                case 23: goto L_0x03ee;
                case 24: goto L_0x03eb;
                case 25: goto L_0x03e8;
                case 26: goto L_0x03e5;
                case 27: goto L_0x03e2;
                case 28: goto L_0x03df;
                case 29: goto L_0x03dc;
                case 30: goto L_0x03d9;
                case 31: goto L_0x03d6;
                case 32: goto L_0x03d3;
                case 33: goto L_0x03d0;
                case 34: goto L_0x03cd;
                case 35: goto L_0x03ca;
                case 36: goto L_0x03c7;
                case 37: goto L_0x03c4;
                case 38: goto L_0x03c1;
                case 39: goto L_0x03be;
                case 40: goto L_0x03bb;
                case 41: goto L_0x03b8;
                case 42: goto L_0x03b5;
                case 43: goto L_0x03b2;
                case 44: goto L_0x03af;
                case 45: goto L_0x03ac;
                case 46: goto L_0x03a9;
                case 47: goto L_0x03a6;
                case 48: goto L_0x03a3;
                case 49: goto L_0x03a0;
                case 50: goto L_0x039d;
                case 51: goto L_0x039a;
                case 52: goto L_0x0397;
                case 53: goto L_0x0394;
                case 54: goto L_0x0391;
                case 55: goto L_0x038e;
                case 56: goto L_0x038b;
                case 57: goto L_0x0388;
                case 58: goto L_0x0385;
                case 59: goto L_0x0382;
                case 60: goto L_0x037f;
                case 61: goto L_0x037c;
                case 62: goto L_0x0379;
                case 63: goto L_0x0376;
                case 64: goto L_0x0373;
                case 65: goto L_0x0370;
                case 66: goto L_0x036d;
                case 67: goto L_0x036a;
                case 68: goto L_0x0367;
                case 69: goto L_0x0364;
                case 70: goto L_0x0361;
                case 71: goto L_0x035e;
                default: goto L_0x035c;
            }
        L_0x035c:
            r1 = 0
            return r1
        L_0x035e:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbo
            return r1
        L_0x0361:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbn
            return r1
        L_0x0364:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_WEIGHT_SUMMARY
            return r1
        L_0x0367:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_WEIGHT
            return r1
        L_0x036a:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_VAGINAL_SPOTTING
            return r1
        L_0x036d:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbg
            return r1
        L_0x0370:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbc
            return r1
        L_0x0373:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_DELTA
            return r1
        L_0x0376:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_CUMULATIVE
            return r1
        L_0x0379:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_CADENCE
            return r1
        L_0x037c:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_SPEED_SUMMARY
            return r1
        L_0x037f:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zza.zzbw
            return r1
        L_0x0382:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zza.zzbv
            return r1
        L_0x0385:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_SPEED
            return r1
        L_0x0388:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbj
            return r1
        L_0x038b:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbk
            return r1
        L_0x038e:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_POWER_SUMMARY
            return r1
        L_0x0391:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_POWER_SAMPLE
            return r1
        L_0x0394:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY
            return r1
        L_0x0397:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_OXYGEN_SATURATION
            return r1
        L_0x039a:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_OVULATION_TEST
            return r1
        L_0x039d:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_NUTRITION_SUMMARY
            return r1
        L_0x03a0:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_HYDRATION
            return r1
        L_0x03a3:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_NUTRITION
            return r1
        L_0x03a6:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_MENSTRUATION
            return r1
        L_0x03a9:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_LOCATION_TRACK
            return r1
        L_0x03ac:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_LOCATION_SAMPLE
            return r1
        L_0x03af:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_LOCATION_BOUNDING_BOX
            return r1
        L_0x03b2:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_HEIGHT_SUMMARY
            return r1
        L_0x03b5:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_HEIGHT
            return r1
        L_0x03b8:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_HEART_RATE_SUMMARY
            return r1
        L_0x03bb:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_HEART_RATE_BPM
            return r1
        L_0x03be:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_HEART_POINTS
            return r1
        L_0x03c1:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_HEART_POINTS
            return r1
        L_0x03c4:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbf
            return r1
        L_0x03c7:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbe
            return r1
        L_0x03ca:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbd
            return r1
        L_0x03cd:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbp
            return r1
        L_0x03d0:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbh
            return r1
        L_0x03d3:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_DISTANCE_DELTA
            return r1
        L_0x03d6:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_DISTANCE_CUMULATIVE
            return r1
        L_0x03d9:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_WHEEL_RPM
            return r1
        L_0x03dc:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_WHEEL_REVOLUTION
            return r1
        L_0x03df:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_PEDALING_CUMULATIVE
            return r1
        L_0x03e2:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_PEDALING_CADENCE
            return r1
        L_0x03e5:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_CERVICAL_POSITION
            return r1
        L_0x03e8:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_CERVICAL_MUCUS
            return r1
        L_0x03eb:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_CALORIES_EXPENDED
            return r1
        L_0x03ee:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_CALORIES_CONSUMED
            return r1
        L_0x03f1:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY
            return r1
        L_0x03f4:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_BASAL_METABOLIC_RATE
            return r1
        L_0x03f7:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbr
            return r1
        L_0x03fa:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbl
            return r1
        L_0x03fd:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY
            return r1
        L_0x0400:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BODY_TEMPERATURE
            return r1
        L_0x0403:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbq
            return r1
        L_0x0406:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbm
            return r1
        L_0x0409:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY
            return r1
        L_0x040c:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY
            return r1
        L_0x040f:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BLOOD_PRESSURE
            return r1
        L_0x0412:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY
            return r1
        L_0x0415:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BLOOD_GLUCOSE
            return r1
        L_0x0418:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_BODY_FAT_PERCENTAGE
            return r1
        L_0x041b:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY
            return r1
        L_0x041e:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE
            return r1
        L_0x0421:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.AGGREGATE_ACTIVITY_SUMMARY
            return r1
        L_0x0424:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_ACTIVITY_SEGMENT
            return r1
        L_0x0427:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_ACTIVITY_SAMPLES
            return r1
        L_0x042a:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_ACTIVITY_SAMPLE
            return r1
        L_0x042d:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_WORKOUT_EXERCISE
            return r1
        L_0x0430:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.TYPE_MOVE_MINUTES
            return r1
        L_0x0433:
            com.google.android.gms.fitness.data.DataType r1 = com.google.android.gms.fitness.data.DataType.zzbi
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.zzm.zzc(java.lang.String):com.google.android.gms.fitness.data.DataType");
    }
}
