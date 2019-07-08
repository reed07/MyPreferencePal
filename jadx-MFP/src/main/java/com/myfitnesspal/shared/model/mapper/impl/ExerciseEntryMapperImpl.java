package com.myfitnesspal.shared.model.mapper.impl;

import android.content.ContentValues;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable.Columns;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue.Unit;
import com.uacf.core.util.Ln;
import java.io.IOException;

public class ExerciseEntryMapperImpl implements ExerciseEntryMapper {
    private final ExerciseMapper exerciseMapper;

    public ExerciseEntryMapperImpl(ExerciseMapper exerciseMapper2) {
        this.exerciseMapper = exerciseMapper2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x017b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.ExerciseEntry reverseMap(com.myfitnesspal.shared.model.v2.MfpExerciseEntry r8) {
        /*
            r7 = this;
            com.myfitnesspal.shared.model.v1.ExerciseEntry r0 = new com.myfitnesspal.shared.model.v1.ExerciseEntry
            r0.<init>()
            long r1 = r8.getLocalId()
            r0.setLocalId(r1)
            long r1 = r8.getMasterId()
            r0.setMasterDatabaseId(r1)
            java.lang.String r1 = r8.getId()
            r0.setUid(r1)
            java.util.Date r1 = r8.getDate()
            r0.setDate(r1)
            com.myfitnesspal.shared.model.v2.MfpExercise r1 = r8.getExercise()
            java.lang.String r1 = r1.getType()
            int r2 = r1.hashCode()
            r3 = -1367604170(0xffffffffae7c0436, float:-5.7301906E-11)
            if (r2 == r3) goto L_0x0042
            r3 = 1791316033(0x6ac55041, float:1.1926869E26)
            if (r2 == r3) goto L_0x0038
            goto L_0x004c
        L_0x0038:
            java.lang.String r2 = "strength"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x004c
            r1 = 1
            goto L_0x004d
        L_0x0042:
            java.lang.String r2 = "cardio"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x004c
            r1 = 0
            goto L_0x004d
        L_0x004c:
            r1 = -1
        L_0x004d:
            switch(r1) {
                case 0: goto L_0x0072;
                case 1: goto L_0x0058;
                default: goto L_0x0050;
            }
        L_0x0050:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "Invalid exercise type"
            r8.<init>(r0)
            throw r8
        L_0x0058:
            int r1 = r8.getSets()
            r0.setSets(r1)
            int r1 = r8.getRepsPerSet()
            r0.setQuantity(r1)
            com.myfitnesspal.shared.model.v2.MfpMeasuredValue r1 = r8.getWeightPerSet()
            float r1 = r1.getValue()
            r0.setWeight(r1)
            goto L_0x0086
        L_0x0072:
            int r1 = r8.getDuration()
            int r1 = r1 / 60
            r0.setQuantity(r1)
            com.myfitnesspal.shared.model.v2.MfpMeasuredValue r1 = r8.getEnergy()
            float r1 = r1.getValue()
            r0.setCalories(r1)
        L_0x0086:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r0.setExtraProperties(r1)
            java.lang.String r2 = r8.getSource()
            boolean r2 = com.uacf.core.util.Strings.notEmpty(r2)
            if (r2 == 0) goto L_0x00a1
            java.lang.String r2 = "source"
            java.lang.String r3 = r8.getSource()
            r1.put(r2, r3)
        L_0x00a1:
            java.lang.String r2 = r8.getStartTimeString()
            boolean r3 = com.uacf.core.util.Strings.notEmpty(r2)
            if (r3 == 0) goto L_0x00b0
            java.lang.String r3 = "start_time"
            r1.put(r3, r2)
        L_0x00b0:
            java.lang.Boolean r2 = r8.isCalorieAdjustment()
            boolean r2 = com.uacf.core.util.NumberUtils.getBooleanValue(r2)
            com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForCalorieAdjustment r3 = r8.getCalorieAdjustmentData()
            if (r3 == 0) goto L_0x013e
            java.lang.String r4 = "client_app_calorie_burned_projection_amount"
            double r5 = r3.getCalorieBurnedProjectionAmount()
            java.lang.Double r5 = java.lang.Double.valueOf(r5)
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)
            r1.put(r4, r5)
            java.lang.String r4 = "client_app_mfp_calorie_projection"
            double r5 = r3.getMfpCalorieProjection()
            java.lang.Double r5 = java.lang.Double.valueOf(r5)
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)
            r1.put(r4, r5)
            java.lang.String r4 = "client_app_name"
            java.lang.String r5 = r3.getPartnerName()
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)
            r1.put(r4, r5)
            java.lang.String r4 = "calorie_adjustment_reduced"
            boolean r5 = r3.getCalorieAdjustmentReduced()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)
            r1.put(r4, r5)
            java.lang.String r4 = "allow_negative_calorie_adjustment"
            boolean r5 = r3.getAllowNegativeCalorieAdjustment()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)
            r1.put(r4, r5)
            java.lang.String r4 = "client_app_projection_timestamp"
            com.myfitnesspal.shared.model.date.MfpIso8601Date r5 = r3.getProjectionTimestamp()
            java.lang.String r5 = com.myfitnesspal.shared.util.Database.encodeDateAndTime(r5)
            r1.put(r4, r5)
            java.lang.String r4 = "client_app_calorie_burned_amount"
            double r5 = r3.getCalorieBurnedAmount()
            java.lang.Double r5 = java.lang.Double.valueOf(r5)
            java.lang.String r5 = com.uacf.core.util.Strings.toString(r5)
            r1.put(r4, r5)
            java.lang.String r4 = "client_app_exercise_calories"
            double r5 = r3.getPartnerExerciseCalories()
            java.lang.Double r3 = java.lang.Double.valueOf(r5)
            java.lang.String r3 = com.uacf.core.util.Strings.toString(r3)
            r1.put(r4, r3)
        L_0x013e:
            com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps r1 = r8.getStepsData()
            if (r1 == 0) goto L_0x0157
            int r3 = r1.getSteps()
            java.lang.String r4 = r1.getClientId()
            java.lang.String r5 = r1.getDeviceId()
            java.util.Date r1 = r1.getDate()
            r0.setStepsInfo(r3, r4, r5, r1)
        L_0x0157:
            com.myfitnesspal.shared.model.v2.MfpExercise r1 = r8.getExercise()
            if (r1 == 0) goto L_0x0171
            com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper r1 = r7.exerciseMapper
            com.myfitnesspal.shared.model.v2.MfpExercise r3 = r8.getExercise()
            java.lang.Object r1 = r1.reverseMap(r3)
            com.myfitnesspal.shared.model.v1.Exercise r1 = (com.myfitnesspal.shared.model.v1.Exercise) r1
            if (r1 == 0) goto L_0x0171
            r1.setCalorieAdjustmentExercise(r2)
            r0.setExercise(r1)
        L_0x0171:
            java.lang.String r1 = r8.getAppId()
            boolean r1 = com.uacf.core.util.Strings.notEmpty(r1)
            if (r1 == 0) goto L_0x0184
            java.lang.String r1 = "app_id"
            java.lang.String r8 = r8.getAppId()
            r0.setExtraPropertyNamed(r1, r8)
        L_0x0184:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapperImpl.reverseMap(com.myfitnesspal.shared.model.v2.MfpExerciseEntry):com.myfitnesspal.shared.model.v1.ExerciseEntry");
    }

    public MfpExerciseEntry mapFrom(ExerciseEntry exerciseEntry) throws IOException {
        MfpExerciseEntry mfpExerciseEntry = new MfpExerciseEntry();
        mfpExerciseEntry.setLocalId(exerciseEntry.getLocalId());
        mfpExerciseEntry.setMasterId(exerciseEntry.getMasterDatabaseId());
        mfpExerciseEntry.setId(exerciseEntry.getUid());
        mfpExerciseEntry.setDate(exerciseEntry.getDate());
        switch (exerciseEntry.exerciseType()) {
            case 0:
                mfpExerciseEntry.setDuration(exerciseEntry.getQuantity() * 60);
                mfpExerciseEntry.setEnergy(new MfpMeasuredValue("calories", exerciseEntry.getCalories()));
                break;
            case 1:
                mfpExerciseEntry.setSets(exerciseEntry.getSets());
                mfpExerciseEntry.setRepsPerSet(exerciseEntry.getQuantity());
                mfpExerciseEntry.setWeightPerSet(new MfpMeasuredValue(Unit.POUNDS, exerciseEntry.getWeight()));
                break;
            default:
                throw new IllegalStateException("Invalid exercise type");
        }
        ContentValues v2ContentValuesFromV1ExtraProperties = getV2ContentValuesFromV1ExtraProperties(exerciseEntry.getExtraProperties());
        mfpExerciseEntry.setSource(v2ContentValuesFromV1ExtraProperties.getAsString("source"));
        mfpExerciseEntry.setEnergyOffsetDataString(v2ContentValuesFromV1ExtraProperties.getAsString(Columns.ENERGY_OFFSET_DATA));
        mfpExerciseEntry.setStartTimeString(v2ContentValuesFromV1ExtraProperties.getAsString("start_time"));
        mfpExerciseEntry.setAppId(v2ContentValuesFromV1ExtraProperties.getAsString("app_id"));
        Exercise exercise = exerciseEntry.getExercise();
        if (exercise != null) {
            mfpExerciseEntry.setExercise((MfpExercise) this.exerciseMapper.tryMapFrom(exercise));
            mfpExerciseEntry.setIsCalorieAdjustment(Boolean.valueOf(exercise.isCalorieAdjustmentExercise()));
        }
        mfpExerciseEntry.setStepsData(exerciseEntry.getStepsData());
        return mfpExerciseEntry;
    }

    public MfpExerciseEntry tryMapFrom(ExerciseEntry exerciseEntry) {
        try {
            return mapFrom(exerciseEntry);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.ContentValues getV2ContentValuesFromV1ExtraProperties(java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
            r10 = this;
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            boolean r1 = com.uacf.core.util.CollectionUtils.isEmpty(r11)
            if (r1 == 0) goto L_0x000c
            return r0
        L_0x000c:
            com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForCalorieAdjustment r1 = new com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForCalorieAdjustment
            r1.<init>()
            java.util.Set r2 = r11.keySet()
            java.util.Iterator r2 = r2.iterator()
            r3 = 0
            r4 = 0
        L_0x001b:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0119
            java.lang.Object r5 = r2.next()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r11.get(r5)
            java.lang.String r6 = (java.lang.String) r6
            r7 = -1
            int r8 = r5.hashCode()
            r9 = 1
            switch(r8) {
                case -1573145462: goto L_0x009f;
                case -1411074055: goto L_0x0094;
                case -1165474235: goto L_0x008a;
                case -979210120: goto L_0x0080;
                case -896505829: goto L_0x0075;
                case -590792739: goto L_0x006b;
                case -238542789: goto L_0x0061;
                case 86267443: goto L_0x0057;
                case 147145054: goto L_0x004d;
                case 1337623931: goto L_0x0043;
                case 1383936731: goto L_0x0038;
                default: goto L_0x0036;
            }
        L_0x0036:
            goto L_0x00aa
        L_0x0038:
            java.lang.String r8 = "client_app_mfp_calorie_projection"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 1
            goto L_0x00ab
        L_0x0043:
            java.lang.String r8 = "client_app_exercise_calories"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 7
            goto L_0x00ab
        L_0x004d:
            java.lang.String r8 = "calorie_adjustment_reduced"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 3
            goto L_0x00ab
        L_0x0057:
            java.lang.String r8 = "allow_negative_calorie_adjustment"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 4
            goto L_0x00ab
        L_0x0061:
            java.lang.String r8 = "client_app_calorie_burned_projection_amount"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 0
            goto L_0x00ab
        L_0x006b:
            java.lang.String r8 = "client_app_name"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 2
            goto L_0x00ab
        L_0x0075:
            java.lang.String r8 = "source"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 9
            goto L_0x00ab
        L_0x0080:
            java.lang.String r8 = "client_app_projection_timestamp"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 5
            goto L_0x00ab
        L_0x008a:
            java.lang.String r8 = "client_app_calorie_burned_amount"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 6
            goto L_0x00ab
        L_0x0094:
            java.lang.String r8 = "app_id"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 10
            goto L_0x00ab
        L_0x009f:
            java.lang.String r8 = "start_time"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x00aa
            r5 = 8
            goto L_0x00ab
        L_0x00aa:
            r5 = -1
        L_0x00ab:
            switch(r5) {
                case 0: goto L_0x010f;
                case 1: goto L_0x0105;
                case 2: goto L_0x00ff;
                case 3: goto L_0x00f1;
                case 4: goto L_0x00e7;
                case 5: goto L_0x00d9;
                case 6: goto L_0x00cf;
                case 7: goto L_0x00c5;
                case 8: goto L_0x00be;
                case 9: goto L_0x00b7;
                case 10: goto L_0x00b0;
                default: goto L_0x00ae;
            }
        L_0x00ae:
            goto L_0x001b
        L_0x00b0:
            java.lang.String r5 = "app_id"
            r0.put(r5, r6)
            goto L_0x001b
        L_0x00b7:
            java.lang.String r5 = "source"
            r0.put(r5, r6)
            goto L_0x001b
        L_0x00be:
            java.lang.String r5 = "start_time"
            r0.put(r5, r6)
            goto L_0x001b
        L_0x00c5:
            double r4 = com.uacf.core.util.NumberUtils.tryParseDouble(r6)
            r1.setPartnerExerciseCalories(r4)
            r4 = 1
            goto L_0x001b
        L_0x00cf:
            double r4 = com.uacf.core.util.NumberUtils.tryParseDouble(r6)
            r1.setCalorieBurnedAmount(r4)
            r4 = 1
            goto L_0x001b
        L_0x00d9:
            java.util.Date r4 = com.myfitnesspal.shared.util.Database.decodeDateAndTimeString(r6)
            com.myfitnesspal.shared.model.date.MfpIso8601Date r4 = com.myfitnesspal.shared.model.date.MfpIso8601Date.newInstance(r4)
            r1.setProjectionTimestamp(r4)
            r4 = 1
            goto L_0x001b
        L_0x00e7:
            boolean r4 = java.lang.Boolean.parseBoolean(r6)
            r1.setAllowNegativeCalorieAdjustment(r4)
            r4 = 1
            goto L_0x001b
        L_0x00f1:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r6)
            boolean r4 = r4.booleanValue()
            r1.setCalorieAdjustmentReduced(r4)
            r4 = 1
            goto L_0x001b
        L_0x00ff:
            r1.setPartnerName(r6)
            r4 = 1
            goto L_0x001b
        L_0x0105:
            double r4 = com.uacf.core.util.NumberUtils.tryParseDouble(r6)
            r1.setMfpCalorieProjection(r4)
            r4 = 1
            goto L_0x001b
        L_0x010f:
            double r4 = com.uacf.core.util.NumberUtils.tryParseDouble(r6)
            r1.setCalorieBurnedProjectionAmount(r4)
            r4 = 1
            goto L_0x001b
        L_0x0119:
            if (r4 == 0) goto L_0x0129
            com.myfitnesspal.shared.model.mapper.ApiJsonMapper r11 = new com.myfitnesspal.shared.model.mapper.ApiJsonMapper
            r11.<init>()
            java.lang.String r11 = r11.reverseMap(r1)
            java.lang.String r1 = "energy_offset_data"
            r0.put(r1, r11)
        L_0x0129:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapperImpl.getV2ContentValuesFromV1ExtraProperties(java.util.Map):android.content.ContentValues");
    }
}
