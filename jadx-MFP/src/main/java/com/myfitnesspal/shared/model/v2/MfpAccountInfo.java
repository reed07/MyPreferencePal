package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import java.util.Calendar;
import java.util.Date;

public class MfpAccountInfo implements Parcelable {
    public static final Creator<MfpAccountInfo> CREATOR = new Creator<MfpAccountInfo>() {
        public MfpAccountInfo createFromParcel(Parcel parcel) {
            return new MfpAccountInfo(parcel);
        }

        public MfpAccountInfo[] newArray(int i) {
            return new MfpAccountInfo[i];
        }
    };
    @Expose
    private MfpAdminFlags adminFlags;
    @Expose
    private Date createdAt;
    @Expose
    private Date lastLogin;
    @Expose
    private String passwordResetCode;
    @Expose
    private String registrationIp;
    @Expose
    private String[] roles;
    @Expose
    private Date updatedAt;
    @Expose
    private Boolean validEmail;

    public int describeContents() {
        return 0;
    }

    public MfpAccountInfo() {
    }

    public MfpAdminFlags getAdminFlags() {
        return this.adminFlags;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }

    public String getPasswordResetCode() {
        return this.passwordResetCode;
    }

    public String getRegistrationIp() {
        return this.registrationIp;
    }

    public String[] getRoles() {
        return this.roles;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public Boolean getValidEmail() {
        return this.validEmail;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.adminFlags);
        Date date = this.createdAt;
        parcel.writeLong(date == null ? Calendar.getInstance().getTimeInMillis() : date.getTime());
        Date date2 = this.lastLogin;
        parcel.writeLong(date2 == null ? Calendar.getInstance().getTimeInMillis() : date2.getTime());
        parcel.writeString(this.passwordResetCode);
        parcel.writeString(this.registrationIp);
        parcel.writeStringArray(this.roles);
        Date date3 = this.updatedAt;
        parcel.writeLong(date3 == null ? Calendar.getInstance().getTimeInMillis() : date3.getTime());
        parcel.writeByte(this.validEmail.booleanValue() ? (byte) 1 : 0);
    }

    protected MfpAccountInfo(Parcel parcel) {
        this.adminFlags = (MfpAdminFlags) parcel.readValue(MfpAdminFlags.class.getClassLoader());
        this.createdAt = new Date(parcel.readLong());
        this.lastLogin = new Date(parcel.readLong());
        this.passwordResetCode = parcel.readString();
        this.registrationIp = parcel.readString();
        parcel.readStringArray(this.roles);
        this.updatedAt = new Date(parcel.readLong());
        this.validEmail = Boolean.valueOf(parcel.readByte() != 0);
    }
}
