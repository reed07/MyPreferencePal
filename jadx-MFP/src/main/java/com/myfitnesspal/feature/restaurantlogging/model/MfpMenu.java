package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.ParcelableUtil;
import java.util.List;

public class MfpMenu implements Parcelable {
    public static final Creator<MfpMenu> CREATOR = new Creator<MfpMenu>() {
        public MfpMenu createFromParcel(Parcel parcel) {
            return new MfpMenu(parcel);
        }

        public MfpMenu[] newArray(int i) {
            return new MfpMenu[i];
        }
    };
    @Expose
    private MenuAttribution attribution;
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private List<MfpMenuSection> sections;
    @Expose
    private String venueId;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpMenu> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpMenu() {
    }

    private MfpMenu(Parcel parcel) {
        this.id = parcel.readString();
        this.venueId = parcel.readString();
        this.name = parcel.readString();
        this.attribution = (MenuAttribution) parcel.readParcelable(MenuAttribution.class.getClassLoader());
        this.sections = ParcelableUtil.readList(parcel, MfpMenuSection.class);
    }

    public String getId() {
        return this.id;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public String getName() {
        return this.name;
    }

    public MenuAttribution getAttribution() {
        return this.attribution;
    }

    public List<MfpMenuSection> getSections() {
        return this.sections;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.venueId);
        parcel.writeString(this.name);
        parcel.writeParcelable(this.attribution, i);
        ParcelableUtil.writeList(parcel, this.sections);
    }
}
