package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.db.table.ExercisesTable.Columns;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;

public class MfpExercise extends MfpVersionedDatabaseObjectV2 {
    public static final Creator<MfpExercise> CREATOR = new Creator<MfpExercise>() {
        public MfpExercise createFromParcel(Parcel parcel) {
            return new MfpExercise(parcel);
        }

        public MfpExercise[] newArray(int i) {
            return new MfpExercise[i];
        }
    };
    private boolean calorieAdjustment;
    private boolean deleted;
    @Expose
    private String description;
    private boolean destroyed;
    @SerializedName("public")
    @Expose
    private Boolean isPublic;
    @Expose
    private Double mets;
    @Expose
    private Double metsDouble;
    @Expose
    private String type;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpExercise> {
    }

    public static final class ExerciseTypes {
        public static final int ALL_VALUE = 2;
        public static final String CARDIO_STRING = "cardio";
        public static final int CARDIO_VALUE = 0;
        public static final String STRENGTH_STRING = "strength";
        public static final int STRENGTH_VALUE = 1;

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0031 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0032 A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static final int toValue(java.lang.String r4) {
            /*
                int r0 = r4.hashCode()
                r1 = -1367604170(0xffffffffae7c0436, float:-5.7301906E-11)
                r2 = 1
                r3 = 0
                if (r0 == r1) goto L_0x001b
                r1 = 1791316033(0x6ac55041, float:1.1926869E26)
                if (r0 == r1) goto L_0x0011
                goto L_0x0025
            L_0x0011:
                java.lang.String r0 = "strength"
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L_0x0025
                r4 = 1
                goto L_0x0026
            L_0x001b:
                java.lang.String r0 = "cardio"
                boolean r4 = r4.equals(r0)
                if (r4 == 0) goto L_0x0025
                r4 = 0
                goto L_0x0026
            L_0x0025:
                r4 = -1
            L_0x0026:
                switch(r4) {
                    case 0: goto L_0x0032;
                    case 1: goto L_0x0031;
                    default: goto L_0x0029;
                }
            L_0x0029:
                java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "Exercise type is invalid"
                r4.<init>(r0)
                throw r4
            L_0x0031:
                return r2
            L_0x0032:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v2.MfpExercise.ExerciseTypes.toValue(java.lang.String):int");
        }

        public static final String toString(int i) {
            switch (i) {
                case 0:
                    return "cardio";
                case 1:
                    return "strength";
                default:
                    throw new IllegalArgumentException("Exercise type is invalid");
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getSyncResourceName() {
        return "exercise";
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public Double getMets() {
        return this.mets;
    }

    public Double getMetsDouble() {
        Double d = this.metsDouble;
        return d != null ? d : this.mets;
    }

    public void setMets(Double d) {
        this.mets = d;
    }

    public void setMetsDouble(Double d) {
        this.metsDouble = d;
    }

    public Boolean isPublic() {
        return this.isPublic;
    }

    public void setIsPublic(Boolean bool) {
        this.isPublic = bool;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean z) {
        this.deleted = z;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean z) {
        this.destroyed = z;
    }

    public boolean isCalorieAdjustment() {
        return this.calorieAdjustment;
    }

    public void setIsCalorieAdjustment(boolean z) {
        this.calorieAdjustment = z;
    }

    public void toContentValues(ContentValuesMapper contentValuesMapper) {
        super.toContentValues(contentValuesMapper);
        contentValuesMapper.put("exercise_type", Integer.valueOf(ExerciseTypes.toValue(getType())));
        contentValuesMapper.put("description", getDescription());
        contentValuesMapper.put(Columns.METS, getMetsDouble());
        contentValuesMapper.put("is_public", Boolean.valueOf(NumberUtils.getBooleanValue(this.isPublic)));
        contentValuesMapper.put("deleted", Boolean.valueOf(isDeleted()));
        contentValuesMapper.put("destroyed", Boolean.valueOf(isDestroyed()));
        contentValuesMapper.put(Columns.IS_CALORIE_ADJUSTMENT_EXERCISE, Boolean.valueOf(isCalorieAdjustment()));
        contentValuesMapper.put("exercise_type", Integer.valueOf(ExerciseTypes.toValue(getType())));
    }

    protected MfpExercise(Parcel parcel) {
        super(parcel);
        this.type = parcel.readString();
        this.description = parcel.readString();
        this.mets = Double.valueOf(parcel.readDouble());
        this.metsDouble = Double.valueOf(parcel.readDouble());
        this.isPublic = Boolean.valueOf(ParcelableUtil.readBoolean(parcel));
        this.deleted = ParcelableUtil.readBoolean(parcel);
        this.destroyed = ParcelableUtil.readBoolean(parcel);
        this.calorieAdjustment = ParcelableUtil.readBoolean(parcel);
    }

    public MfpExercise() {
    }

    public MfpExercise(CursorMapper cursorMapper) {
        super(cursorMapper.forClass(MfpExercise.class));
        setDescription(cursorMapper.getString("description"));
        setMets(Double.valueOf(cursorMapper.getDouble(Columns.METS)));
        setMetsDouble(Double.valueOf(cursorMapper.getDouble(Columns.METS)));
        setIsPublic(cursorMapper.getBoolean("is_public"));
        setType(ExerciseTypes.toString(cursorMapper.getInt("exercise_type")));
        setDeleted(cursorMapper.getBoolean("deleted").booleanValue());
        setDestroyed(cursorMapper.getBoolean("destroyed").booleanValue());
        setIsCalorieAdjustment(cursorMapper.getBoolean(Columns.IS_CALORIE_ADJUSTMENT_EXERCISE).booleanValue());
        if (NumberUtils.getBooleanValue(isPublic())) {
            setId(getVersion());
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.type);
        parcel.writeString(this.description);
        parcel.writeDouble(this.mets.doubleValue());
        parcel.writeDouble(getMetsDouble().doubleValue());
        ParcelableUtil.writeBoolean(parcel, this.isPublic.booleanValue());
        ParcelableUtil.writeBoolean(parcel, this.deleted);
        ParcelableUtil.writeBoolean(parcel, this.destroyed);
        ParcelableUtil.writeBoolean(parcel, this.calorieAdjustment);
    }

    /* access modifiers changed from: protected */
    public void readV1Information(CursorMapper cursorMapper) {
        this.ownerUserLocalId = cursorMapper.getLong("owner_user_id");
        this.ownerUserMasterId = cursorMapper.getLong("owner_user_master_id");
    }

    /* access modifiers changed from: protected */
    public void writeV1Information(ContentValuesMapper contentValuesMapper) {
        contentValuesMapper.put("owner_user_id", Long.valueOf(this.ownerUserLocalId));
        contentValuesMapper.put("owner_user_master_id", Long.valueOf(this.ownerUserMasterId));
    }

    public static int cardioCaloriesBurnedForHours(MfpExercise mfpExercise, float f, int i) {
        if (mfpExercise == null || i < 0) {
            return 0;
        }
        return (int) (((double) f) * (((double) i) / 60.0d) * mfpExercise.getMetsDouble().doubleValue());
    }
}
