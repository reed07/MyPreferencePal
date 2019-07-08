package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class MfpAccount implements Parcelable {
    public static final Creator<MfpAccount> CREATOR = new Creator<MfpAccount>() {
        public MfpAccount createFromParcel(Parcel parcel) {
            return new MfpAccount(parcel);
        }

        public MfpAccount[] newArray(int i) {
            return new MfpAccount[i];
        }
    };
    @Expose
    private MfpAdminFlags adminFlags;
    @Expose
    private String createdAt;
    @Expose
    private String lastLogin;
    @Expose
    private String passwordResetCode;
    @Expose
    private String registrationIp;
    @Expose
    private List<String> roles = new ArrayList();
    @Expose
    private String updatedAt;
    @Expose
    private boolean validEmail;

    public int describeContents() {
        return 0;
    }

    public MfpAccount() {
    }

    public MfpAccount(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public void setLastLogin(String str) {
        this.lastLogin = str;
    }

    public void setValidEmail(boolean z) {
        this.validEmail = z;
    }

    public void setRegistrationIp(String str) {
        this.registrationIp = str;
    }

    public void setPasswordResetCode(String str) {
        this.passwordResetCode = str;
    }

    public void setRoles(List<String> list) {
        if (list == null) {
            this.roles = new ArrayList();
        } else {
            this.roles = list;
        }
    }

    public void setAdminFlags(MfpAdminFlags mfpAdminFlags) {
        this.adminFlags = mfpAdminFlags;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public String getLastLogin() {
        return this.lastLogin;
    }

    public boolean isValidEmail() {
        return this.validEmail;
    }

    public String getRegistrationIp() {
        return this.registrationIp;
    }

    public String getPasswordResetCode() {
        return this.passwordResetCode;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public MfpAdminFlags getAdminFlags() {
        return this.adminFlags;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.createdAt);
        parcel.writeString(this.updatedAt);
        parcel.writeString(this.lastLogin);
        parcel.writeByte(this.validEmail ? (byte) 1 : 0);
        parcel.writeString(this.registrationIp);
        parcel.writeString(this.passwordResetCode);
        parcel.writeList(this.roles);
        if (this.adminFlags != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.adminFlags, 0);
            return;
        }
        parcel.writeByte(0);
    }

    private void readFromParcel(Parcel parcel) {
        this.createdAt = parcel.readString();
        this.updatedAt = parcel.readString();
        this.lastLogin = parcel.readString();
        this.validEmail = parcel.readByte() != 0;
        this.registrationIp = parcel.readString();
        this.passwordResetCode = parcel.readString();
        this.roles.clear();
        parcel.readList(this.roles, String.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.adminFlags = (MfpAdminFlags) parcel.readParcelable(MfpAdminFlags.class.getClassLoader());
        }
    }
}
