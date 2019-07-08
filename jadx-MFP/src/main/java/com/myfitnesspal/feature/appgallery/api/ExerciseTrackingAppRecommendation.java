package com.myfitnesspal.feature.appgallery.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.core.util.ParcelableUtil;
import java.util.List;

public class ExerciseTrackingAppRecommendation implements Parcelable {
    public static Creator<ExerciseTrackingAppRecommendation> CREATOR = new Creator<ExerciseTrackingAppRecommendation>() {
        public ExerciseTrackingAppRecommendation createFromParcel(Parcel parcel) {
            return new ExerciseTrackingAppRecommendation(parcel);
        }

        public ExerciseTrackingAppRecommendation[] newArray(int i) {
            return new ExerciseTrackingAppRecommendation[i];
        }
    };
    @Expose
    private List<MfpPlatformApp> apps;
    @Expose
    private String description;
    @Expose
    private ExerciseTrackingAppRecommendationExercise exercise;
    @Expose
    private String headline;
    @Expose
    private String id;

    public static class API_RESPONSE_MAPPER extends ApiResponse<ExerciseTrackingAppRecommendation> {
    }

    public int describeContents() {
        return 0;
    }

    public ExerciseTrackingAppRecommendation() {
    }

    public ExerciseTrackingAppRecommendation(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getId() {
        return this.id;
    }

    public List<MfpPlatformApp> getApps() {
        return this.apps;
    }

    public String getDescription() {
        return this.description;
    }

    public ExerciseTrackingAppRecommendationExercise getExercise() {
        return this.exercise;
    }

    public String getHeadline() {
        return this.headline;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        ParcelableUtil.writeList(parcel, this.apps);
        parcel.writeString(this.description);
        parcel.writeParcelable(this.exercise, 0);
        parcel.writeString(this.headline);
    }

    private void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.apps = ParcelableUtil.readList(parcel, MfpPlatformApp.class);
        this.description = parcel.readString();
        this.exercise = (ExerciseTrackingAppRecommendationExercise) parcel.readParcelable(ExerciseTrackingAppRecommendationExercise.class.getClassLoader());
        this.headline = parcel.readString();
    }
}
