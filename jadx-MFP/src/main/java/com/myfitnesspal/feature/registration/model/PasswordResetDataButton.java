package com.myfitnesspal.feature.registration.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public class PasswordResetDataButton implements Parcelable {
    public static final Creator<PasswordResetDataButton> CREATOR = new Creator<PasswordResetDataButton>() {
        public PasswordResetDataButton createFromParcel(Parcel parcel) {
            PasswordResetDataButton passwordResetDataButton = new PasswordResetDataButton();
            passwordResetDataButton.readFromParcel(parcel);
            return passwordResetDataButton;
        }

        public PasswordResetDataButton[] newArray(int i) {
            return new PasswordResetDataButton[i];
        }
    };
    @Expose
    private String action;
    @Expose
    private String text;

    public int describeContents() {
        return 0;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Strings.toString(this.text));
        parcel.writeString(Strings.toString(this.action));
    }

    /* access modifiers changed from: private */
    public void readFromParcel(Parcel parcel) {
        this.text = parcel.readString();
        this.action = parcel.readString();
    }
}
