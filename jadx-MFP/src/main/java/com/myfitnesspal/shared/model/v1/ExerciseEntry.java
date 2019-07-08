package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseEntry extends DatabaseObject implements Parcelable {
    public static final Creator<ExerciseEntry> CREATOR = new Creator<ExerciseEntry>() {
        public ExerciseEntry createFromParcel(Parcel parcel) {
            return new ExerciseEntry(parcel);
        }

        public ExerciseEntry[] newArray(int i) {
            return new ExerciseEntry[i];
        }
    };
    private float calories;
    private Date date;
    private Exercise exercise;
    private Map<String, String> extraProperties;
    private int quantity;
    private int sets;
    private MfpExerciseMetadataForSteps stepsData;
    private float weight;

    public int describeContents() {
        return 0;
    }

    public int itemType() {
        return 5;
    }

    public ExerciseEntry() {
    }

    public ExerciseEntry(Exercise exercise2) {
        this.exercise = exercise2;
    }

    private ExerciseEntry(Parcel parcel) {
        super(parcel);
        this.date = ParcelableUtil.readDate(parcel);
        this.exercise = (Exercise) parcel.readParcelable(Exercise.class.getClassLoader());
        this.quantity = parcel.readInt();
        this.sets = parcel.readInt();
        this.weight = parcel.readFloat();
        this.calories = parcel.readFloat();
        this.extraProperties = ParcelableUtil.createStringToStringMap(parcel);
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public Exercise getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise2) {
        this.exercise = exercise2;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public int getSets() {
        return this.sets;
    }

    public void setSets(int i) {
        this.sets = i;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float f) {
        this.weight = f;
    }

    public float getCalories() {
        return this.calories;
    }

    public void setCalories(float f) {
        this.calories = f;
    }

    public MfpExerciseMetadataForSteps getStepsData() {
        return this.stepsData;
    }

    public void setStepsData(MfpExerciseMetadataForSteps mfpExerciseMetadataForSteps) {
        this.stepsData = mfpExerciseMetadataForSteps;
    }

    public void setStepsInfo(int i, String str, String str2, Date date2) {
        setStepsData(new MfpExerciseMetadataForSteps(i, str, str2, date2));
    }

    public int exerciseType() {
        Exercise exercise2 = this.exercise;
        if (exercise2 != null) {
            return exercise2.getExerciseType();
        }
        return -1;
    }

    public float calculateMetsForUser(User user) {
        if (exerciseType() != 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float f = (float) (((double) this.quantity) / 60.0d);
        float f2 = this.weight;
        float kilograms = (float) (f2 > BitmapDescriptorFactory.HUE_RED ? ((double) f2) / 2.20462262d : (double) user.getProfile().getCurrentWeight().getKilograms());
        if (((double) kilograms) <= 0.0d || ((double) f) <= 0.0d) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return this.calories / (kilograms * f);
    }

    public Map<String, String> getExtraProperties() {
        return this.extraProperties;
    }

    public void setExtraProperties(Map<String, String> map) {
        this.extraProperties = map;
    }

    public void setExtraPropertyNamed(String str, String str2) {
        if (this.extraProperties == null) {
            this.extraProperties = new HashMap();
        }
        this.extraProperties.put(str, str2);
    }

    public void removeExtraPropertyNamed(String str) {
        if (this.extraProperties != null && Strings.notEmpty(str)) {
            this.extraProperties.remove(str);
        }
    }

    public String extraPropertyNamed(String str) {
        Map<String, String> map = this.extraProperties;
        if (map != null) {
            return (String) map.get(str);
        }
        return null;
    }

    public boolean containsAllExtraProperties(List<String> list) {
        if (CollectionUtils.isEmpty(this.extraProperties)) {
            return false;
        }
        return this.extraProperties.keySet().containsAll(list);
    }

    public String startTime() {
        return Strings.toString(extraPropertyNamed("start_time"));
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        ParcelableUtil.writeDate(parcel, this.date);
        parcel.writeParcelable(this.exercise, i);
        parcel.writeInt(this.quantity);
        parcel.writeInt(this.sets);
        parcel.writeFloat(this.weight);
        parcel.writeFloat(this.calories);
        ParcelableUtil.writeStringToStringMap(parcel, this.extraProperties);
    }
}
