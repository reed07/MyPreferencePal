package com.myfitnesspal.feature.progress.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ImageStatusMetadata implements Parcelable {
    public static final Creator<ImageStatusMetadata> CREATOR = new Creator<ImageStatusMetadata>() {
        public ImageStatusMetadata createFromParcel(Parcel parcel) {
            return new ImageStatusMetadata(parcel);
        }

        public ImageStatusMetadata[] newArray(int i) {
            return new ImageStatusMetadata[i];
        }
    };
    private String age;
    private String artifactDataType;
    private String compositionImageId;
    private String country;
    private String customCaption;
    private String customCaptionString;
    private String dataPoint;
    private String date1;
    private String date2;
    private String gender;
    private String height;
    private String imageId;
    private String imageId1;
    private String imageId2;
    private String language;
    private String measurementValue1;
    private String measurementValue2;
    private String startingBmi;
    private String startingWeight;

    public int describeContents() {
        return 0;
    }

    public ImageStatusMetadata(Parcel parcel) {
        readFromParcel(parcel);
    }

    public ImageStatusMetadata() {
    }

    public String getArtifactDataType() {
        return this.artifactDataType;
    }

    public void setArtifactDataType(String str) {
        this.artifactDataType = str;
    }

    public String getImageId() {
        return this.imageId;
    }

    public void setImageId(String str) {
        this.imageId = str;
    }

    public String getCompositionImageId() {
        return this.compositionImageId;
    }

    public void setCompositionImageId(String str) {
        this.compositionImageId = str;
    }

    public String getCustomCaption() {
        return this.customCaption;
    }

    public void setCustomCaption(String str) {
        this.customCaption = str;
    }

    public String getCustomCaptionString() {
        return this.customCaptionString;
    }

    public void setCustomCaptionString(String str) {
        this.customCaptionString = str;
    }

    public String getDataPoint() {
        return this.dataPoint;
    }

    public void setDataPoint(String str) {
        this.dataPoint = str;
    }

    public String getDate1() {
        return this.date1;
    }

    public void setDate1(String str) {
        this.date1 = str;
    }

    public String getImageId1() {
        return this.imageId1;
    }

    public void setImageId1(String str) {
        this.imageId1 = str;
    }

    public String getMeasurementValue1() {
        return this.measurementValue1;
    }

    public void setMeasurementValue1(String str) {
        this.measurementValue1 = str;
    }

    public String getDate2() {
        return this.date2;
    }

    public void setDate2(String str) {
        this.date2 = str;
    }

    public String getImageId2() {
        return this.imageId2;
    }

    public void setImageId2(String str) {
        this.imageId2 = str;
    }

    public String getMeasurementValue2() {
        return this.measurementValue2;
    }

    public void setMeasurementValue2(String str) {
        this.measurementValue2 = str;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String str) {
        this.age = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getStartingBmi() {
        return this.startingBmi;
    }

    public void setStartingBmi(String str) {
        this.startingBmi = str;
    }

    public String getStartingWeight() {
        return this.startingWeight;
    }

    public void setStartingWeight(String str) {
        this.startingWeight = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.artifactDataType);
        parcel.writeString(this.imageId);
        parcel.writeString(this.compositionImageId);
        parcel.writeString(this.customCaption);
        parcel.writeString(this.customCaptionString);
        parcel.writeString(this.dataPoint);
        parcel.writeString(this.age);
        parcel.writeString(this.country);
        parcel.writeString(this.gender);
        parcel.writeString(this.height);
        parcel.writeString(this.language);
        parcel.writeString(this.startingBmi);
        parcel.writeString(this.startingWeight);
        parcel.writeString(this.date1);
        parcel.writeString(this.imageId1);
        parcel.writeString(this.measurementValue1);
        parcel.writeString(this.date2);
        parcel.writeString(this.imageId2);
        parcel.writeString(this.measurementValue2);
    }

    private void readFromParcel(Parcel parcel) {
        this.artifactDataType = parcel.readString();
        this.imageId = parcel.readString();
        this.compositionImageId = parcel.readString();
        this.customCaption = parcel.readString();
        this.customCaptionString = parcel.readString();
        this.dataPoint = parcel.readString();
        this.age = parcel.readString();
        this.country = parcel.readString();
        this.gender = parcel.readString();
        this.height = parcel.readString();
        this.language = parcel.readString();
        this.startingBmi = parcel.readString();
        this.startingWeight = parcel.readString();
        this.date1 = parcel.readString();
        this.imageId1 = parcel.readString();
        this.measurementValue1 = parcel.readString();
        this.date2 = parcel.readString();
        this.imageId2 = parcel.readString();
        this.measurementValue2 = parcel.readString();
    }
}
