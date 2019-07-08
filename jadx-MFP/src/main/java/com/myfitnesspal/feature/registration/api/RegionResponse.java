package com.myfitnesspal.feature.registration.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;

public class RegionResponse implements Parcelable {
    public static Creator<RegionResponse> CREATOR = new Creator<RegionResponse>() {
        public RegionResponse createFromParcel(Parcel parcel) {
            return new RegionResponse(parcel);
        }

        public RegionResponse[] newArray(int i) {
            return new RegionResponse[i];
        }
    };
    @Expose
    private String name;

    public static class API_RESPONSE_MAPPER extends ApiResponse<RegionResponse> {
    }

    public int describeContents() {
        return 0;
    }

    public RegionResponse() {
    }

    public RegionResponse(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getName() {
        return this.name;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
    }

    private void readFromParcel(Parcel parcel) {
        this.name = parcel.readString();
    }
}
