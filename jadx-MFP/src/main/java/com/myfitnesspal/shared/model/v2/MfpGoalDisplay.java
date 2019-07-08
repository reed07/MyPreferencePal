package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class MfpGoalDisplay implements Parcelable {
    public static final Creator<MfpGoalDisplay> CREATOR = new Creator<MfpGoalDisplay>() {
        public MfpGoalDisplay createFromParcel(Parcel parcel) {
            return new MfpGoalDisplay(parcel);
        }

        public MfpGoalDisplay[] newArray(int i) {
            return new MfpGoalDisplay[i];
        }
    };
    @Expose
    private String displayType;
    @Expose
    private String id;
    @Expose
    private List<String> nutrients;

    public static class LIST_MAPPER extends ArrayList<MfpGoalDisplay> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpGoalDisplay() {
    }

    public MfpGoalDisplay(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setDisplayType(String str) {
        this.displayType = str;
    }

    public void setNutrients(List<String> list) {
        if (list == null) {
            this.nutrients = new ArrayList();
        } else {
            this.nutrients = list;
        }
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayType() {
        return this.displayType;
    }

    public List<String> getNutrients() {
        return this.nutrients;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.displayType);
        parcel.writeList(this.nutrients);
    }

    private void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.displayType = parcel.readString();
        this.nutrients.clear();
        parcel.readList(this.nutrients, String.class.getClassLoader());
    }
}
