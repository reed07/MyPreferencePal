package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uacf.core.util.ParcelableUtil;
import java.util.Date;

public class MenusActivityBundleData implements Parcelable {
    public static final Creator<MenusActivityBundleData> CREATOR = new Creator<MenusActivityBundleData>() {
        public MenusActivityBundleData createFromParcel(Parcel parcel) {
            return new MenusActivityBundleData(parcel);
        }

        public MenusActivityBundleData[] newArray(int i) {
            return new MenusActivityBundleData[i];
        }
    };
    private final Date date;
    private final String flowId;
    private final boolean isCurrentlyInMealCreationFlow;
    private final String mealName;
    private final String source;
    private final Venue venue;

    public int describeContents() {
        return 0;
    }

    public MenusActivityBundleData(Venue venue2, String str, Date date2, String str2, String str3, boolean z) {
        this.venue = venue2;
        this.mealName = str;
        this.date = date2;
        this.flowId = str2;
        this.source = str3;
        this.isCurrentlyInMealCreationFlow = z;
    }

    public MenusActivityBundleData(Venue venue2, String str, String str2) {
        this.venue = venue2;
        this.mealName = null;
        this.date = new Date();
        this.flowId = str;
        this.source = str2;
        this.isCurrentlyInMealCreationFlow = false;
    }

    protected MenusActivityBundleData(Parcel parcel) {
        this.venue = (Venue) parcel.readParcelable(Venue.class.getClassLoader());
        this.mealName = parcel.readString();
        this.date = ParcelableUtil.readDate(parcel);
        this.flowId = parcel.readString();
        this.source = parcel.readString();
        this.isCurrentlyInMealCreationFlow = ParcelableUtil.readBoolean(parcel);
    }

    public Venue getVenue() {
        return this.venue;
    }

    public String getMealName() {
        return this.mealName;
    }

    public Date getDate() {
        return this.date;
    }

    public String getFlowId() {
        return this.flowId;
    }

    public String getSource() {
        return this.source;
    }

    public boolean isCurrentlyInMealCreationFlow() {
        return this.isCurrentlyInMealCreationFlow;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.venue, i);
        parcel.writeString(this.mealName);
        ParcelableUtil.writeDate(parcel, this.date);
        parcel.writeString(this.flowId);
        parcel.writeString(this.source);
        ParcelableUtil.writeBoolean(parcel, this.isCurrentlyInMealCreationFlow);
    }
}
