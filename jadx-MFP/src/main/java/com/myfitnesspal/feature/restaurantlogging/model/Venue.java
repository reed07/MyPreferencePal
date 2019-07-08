package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.SearchResultItem;
import com.uacf.core.util.ParcelableUtil;

public class Venue implements Parcelable, SearchResultItem, Comparable<Venue> {
    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        public Venue createFromParcel(Parcel parcel) {
            return new Venue(parcel);
        }

        public Venue[] newArray(int i) {
            return new Venue[i];
        }
    };
    @Expose
    private boolean hasMenu;
    @Expose
    private String id;
    @Expose
    private MfpLocation location;
    @Expose
    private int menuItemCount;
    @Expose
    private String name;
    @Expose
    private boolean verified;

    public static class API_RESPONSE_MAPPER extends ApiResponse<Venue> {
    }

    public int describeContents() {
        return 0;
    }

    public Venue(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.verified = ParcelableUtil.readBoolean(parcel);
        this.location = (MfpLocation) parcel.readParcelable(MfpLocation.class.getClassLoader());
        this.hasMenu = ParcelableUtil.readBoolean(parcel);
        this.menuItemCount = parcel.readInt();
    }

    public Venue() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public MfpLocation getLocation() {
        return this.location;
    }

    public boolean hasMenu() {
        return this.hasMenu;
    }

    public int getMenuItemCount() {
        return this.menuItemCount;
    }

    public int compareTo(@NonNull Venue venue) {
        return this.location.compareTo(venue.location);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        ParcelableUtil.writeBoolean(parcel, this.verified);
        parcel.writeParcelable(this.location, i);
        ParcelableUtil.writeBoolean(parcel, this.hasMenu);
        parcel.writeInt(this.menuItemCount);
    }
}
