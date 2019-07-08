package com.myfitnesspal.feature.appgallery.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class ExerciseTrackingAppRecommendationExercise implements Parcelable {
    public static Creator<ExerciseTrackingAppRecommendationExercise> CREATOR = new Creator<ExerciseTrackingAppRecommendationExercise>() {
        public ExerciseTrackingAppRecommendationExercise createFromParcel(Parcel parcel) {
            return new ExerciseTrackingAppRecommendationExercise(parcel);
        }

        public ExerciseTrackingAppRecommendationExercise[] newArray(int i) {
            return new ExerciseTrackingAppRecommendationExercise[i];
        }
    };
    @Expose
    private String id;

    public int describeContents() {
        return 0;
    }

    public ExerciseTrackingAppRecommendationExercise() {
    }

    public ExerciseTrackingAppRecommendationExercise(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getId() {
        return this.id;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
    }

    private void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
    }
}
