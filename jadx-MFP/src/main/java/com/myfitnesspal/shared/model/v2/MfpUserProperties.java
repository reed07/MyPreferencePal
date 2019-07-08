package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import java.util.ArrayList;
import java.util.List;

public class MfpUserProperties implements Parcelable {
    public static final Creator<MfpUserProperties> CREATOR = new Creator<MfpUserProperties>() {
        public MfpUserProperties createFromParcel(Parcel parcel) {
            return new MfpUserProperties(parcel);
        }

        public MfpUserProperties[] newArray(int i) {
            return new MfpUserProperties[i];
        }
    };
    @Expose
    private MfpAccountInfo account;
    @Expose
    private String email;
    @Expose
    private String id;
    @Expose
    private List<MfpStepSource> stepSources;
    @Expose
    private String username;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpUserProperties> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpUserProperties() {
    }

    public MfpAccountInfo getAccount() {
        return this.account;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.id;
    }

    public List<MfpStepSource> getStepSources() {
        return this.stepSources;
    }

    public String getUsername() {
        return this.username;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.account);
        parcel.writeString(this.email);
        parcel.writeString(this.id);
        if (this.stepSources == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.stepSources);
        }
        parcel.writeString(this.username);
    }

    protected MfpUserProperties(Parcel parcel) {
        this.account = (MfpAccountInfo) parcel.readValue(MfpAccountInfo.class.getClassLoader());
        this.email = parcel.readString();
        this.id = parcel.readString();
        if (parcel.readByte() == 1) {
            this.stepSources = new ArrayList();
            parcel.readList(this.stepSources, MfpStepSource.class.getClassLoader());
        } else {
            this.stepSources = null;
        }
        this.username = parcel.readString();
    }
}
