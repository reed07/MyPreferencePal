package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uacf.core.util.ParcelableUtil;
import java.util.Date;

public class MenuItemEditorBundleData implements Parcelable {
    public static final Creator<MenuItemEditorBundleData> CREATOR = new Creator<MenuItemEditorBundleData>() {
        public MenuItemEditorBundleData createFromParcel(Parcel parcel) {
            return new MenuItemEditorBundleData(parcel);
        }

        public MenuItemEditorBundleData[] newArray(int i) {
            return new MenuItemEditorBundleData[i];
        }
    };
    private final Date date;
    private final String flowId;
    private final String mealName;
    private MfpMenuItem menuItem;
    private final String menuName;
    private final String sectionName;
    private final Venue venue;

    public int describeContents() {
        return 0;
    }

    public MenuItemEditorBundleData(MfpMenuItem mfpMenuItem, String str, Venue venue2, String str2, String str3, String str4, Date date2) {
        this.menuItem = mfpMenuItem;
        this.flowId = str;
        this.venue = venue2;
        this.menuName = str2;
        this.sectionName = str3;
        this.mealName = str4;
        this.date = date2;
    }

    protected MenuItemEditorBundleData(Parcel parcel) {
        this.menuItem = (MfpMenuItem) parcel.readParcelable(MfpMenuItem.class.getClassLoader());
        this.flowId = parcel.readString();
        this.venue = (Venue) parcel.readParcelable(Venue.class.getClassLoader());
        this.menuName = parcel.readString();
        this.sectionName = parcel.readString();
        this.mealName = parcel.readString();
        this.date = ParcelableUtil.readDate(parcel);
    }

    public void setMenuItem(MfpMenuItem mfpMenuItem) {
        this.menuItem = (MfpMenuItem) ParcelableUtil.clone(mfpMenuItem, MfpMenuItem.CREATOR);
    }

    public MfpMenuItem getMenuItem() {
        return this.menuItem;
    }

    public String getFlowId() {
        return this.flowId;
    }

    public String getVenueId() {
        Venue venue2 = this.venue;
        if (venue2 == null) {
            return null;
        }
        return venue2.getId();
    }

    public String getVenueName() {
        Venue venue2 = this.venue;
        if (venue2 == null) {
            return null;
        }
        return venue2.getName();
    }

    public Boolean isVenueVerified() {
        Venue venue2 = this.venue;
        if (venue2 == null) {
            return null;
        }
        return Boolean.valueOf(venue2.isVerified());
    }

    public String getMenuName() {
        return this.menuName;
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public String getMealName() {
        return this.mealName;
    }

    public Date getDate() {
        return this.date;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.menuItem, i);
        parcel.writeString(this.flowId);
        parcel.writeParcelable(this.venue, i);
        parcel.writeString(this.menuName);
        parcel.writeString(this.sectionName);
        parcel.writeString(this.mealName);
        ParcelableUtil.writeDate(parcel, this.date);
    }
}
