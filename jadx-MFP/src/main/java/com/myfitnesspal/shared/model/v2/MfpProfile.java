package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;

public class MfpProfile implements Parcelable {
    public static final Creator<MfpProfile> CREATOR = new Creator<MfpProfile>() {
        public MfpProfile createFromParcel(Parcel parcel) {
            return new MfpProfile(parcel);
        }

        public MfpProfile[] newArray(int i) {
            return new MfpProfile[i];
        }
    };
    @Expose
    private String activityFactor;
    @Expose
    private String birthdate;
    @Expose
    private MfpMeasuredValue currentWeight;
    @Expose
    private String firstName;
    @Expose
    private MfpMeasuredValue height;
    @Expose
    private String lastName;
    @Expose
    private String mainImageUrl;
    @Expose
    private String sex;
    @Expose
    private MfpMeasuredValue startingWeight;
    @Expose
    private String startingWeightDate;
    @Expose
    private String type;

    public static class LIST_MAPPER extends ArrayList<MfpProfile> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpProfile() {
    }

    public MfpProfile(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setBirthdate(String str) {
        this.birthdate = str;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public void setMainImageUrl(String str) {
        this.mainImageUrl = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setHeight(MfpMeasuredValue mfpMeasuredValue) {
        this.height = mfpMeasuredValue;
    }

    public void setStartingWeight(MfpMeasuredValue mfpMeasuredValue) {
        this.startingWeight = mfpMeasuredValue;
    }

    public void setCurrentWeight(MfpMeasuredValue mfpMeasuredValue) {
        this.currentWeight = mfpMeasuredValue;
    }

    public String getType() {
        return this.type;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public String getSex() {
        return this.sex;
    }

    public String getMainImageUrl() {
        return this.mainImageUrl;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public MfpMeasuredValue getHeight() {
        return this.height;
    }

    public MfpMeasuredValue getStartingWeight() {
        return this.startingWeight;
    }

    public MfpMeasuredValue getCurrentWeight() {
        return this.currentWeight;
    }

    public String getActivityFactor() {
        return this.activityFactor;
    }

    public void setActivityFactor(String str) {
        this.activityFactor = str;
    }

    public String getStartingWeightDate() {
        return this.startingWeightDate;
    }

    public void setStartingWeightDate(String str) {
        this.startingWeightDate = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeString(this.birthdate);
        parcel.writeString(this.sex);
        parcel.writeString(this.mainImageUrl);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        if (this.height != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.height, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.startingWeight != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.startingWeight, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.currentWeight != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.currentWeight, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeString(this.activityFactor);
        parcel.writeString(this.startingWeightDate);
    }

    private void readFromParcel(Parcel parcel) {
        this.type = parcel.readString();
        this.birthdate = parcel.readString();
        this.sex = parcel.readString();
        this.mainImageUrl = parcel.readString();
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        if (parcel.readByte() == 1) {
            this.height = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.startingWeight = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.currentWeight = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        this.activityFactor = parcel.readString();
        this.startingWeightDate = parcel.readString();
    }
}
