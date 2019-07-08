package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.ParcelableUtil;
import java.util.List;

public class MfpMenuSection implements Parcelable, MfpMenuListItem {
    public static final Creator<MfpMenuSection> CREATOR = new Creator<MfpMenuSection>() {
        public MfpMenuSection createFromParcel(Parcel parcel) {
            return new MfpMenuSection(parcel);
        }

        public MfpMenuSection[] newArray(int i) {
            return new MfpMenuSection[i];
        }
    };
    @Expose
    private String description;
    @Expose
    private String id;
    @Expose
    private List<MfpMenuItem> items;
    @Expose
    private String menuId;
    @Expose
    private String name;

    public int describeContents() {
        return 0;
    }

    public MfpMenuSection() {
    }

    private MfpMenuSection(Parcel parcel) {
        this.id = parcel.readString();
        this.menuId = parcel.readString();
        this.name = parcel.readString();
        this.description = parcel.readString();
        this.items = ParcelableUtil.readList(parcel, MfpMenuItem.class);
    }

    public String getId() {
        return this.id;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<MfpMenuItem> getItems() {
        return this.items;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.menuId);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        ParcelableUtil.writeList(parcel, this.items);
    }
}
