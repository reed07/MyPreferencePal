package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpAdminFlags implements Parcelable {
    public static final Creator<MfpAdminFlags> CREATOR = new Creator<MfpAdminFlags>() {
        public MfpAdminFlags createFromParcel(Parcel parcel) {
            return new MfpAdminFlags(parcel);
        }

        public MfpAdminFlags[] newArray(int i) {
            return new MfpAdminFlags[i];
        }
    };
    @Expose
    private Boolean forgotPasswordOrUsername;
    @Expose
    private Boolean hasChangedUsername;
    @Expose
    private String status;
    @Expose
    private Number strikes;
    @Expose
    private Number warnings;

    public int describeContents() {
        return 0;
    }

    public MfpAdminFlags() {
    }

    public Boolean getForgotPasswordOrUsername() {
        return this.forgotPasswordOrUsername;
    }

    public Boolean getHasChangedUsername() {
        return this.hasChangedUsername;
    }

    public String getStatus() {
        return this.status;
    }

    public Number getStrikes() {
        return this.strikes;
    }

    public Number getWarnings() {
        return this.warnings;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(getForgotPasswordOrUsername().booleanValue() ? (byte) 1 : 0);
        parcel.writeByte(getHasChangedUsername().booleanValue() ? (byte) 1 : 0);
        parcel.writeString(this.status);
        parcel.writeInt(this.strikes.intValue());
        parcel.writeInt(this.warnings.intValue());
    }

    protected MfpAdminFlags(Parcel parcel) {
        boolean z = true;
        this.forgotPasswordOrUsername = Boolean.valueOf(parcel.readByte() != 0);
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.hasChangedUsername = Boolean.valueOf(z);
        this.status = parcel.readString();
        this.strikes = Integer.valueOf(parcel.readInt());
        this.warnings = Integer.valueOf(parcel.readInt());
    }
}
