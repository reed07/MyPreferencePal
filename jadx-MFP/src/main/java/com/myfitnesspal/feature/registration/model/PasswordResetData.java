package com.myfitnesspal.feature.registration.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.List;

public class PasswordResetData implements Parcelable {
    public static final Creator<PasswordResetData> CREATOR = new Creator<PasswordResetData>() {
        public PasswordResetData createFromParcel(Parcel parcel) {
            PasswordResetData passwordResetData = new PasswordResetData();
            passwordResetData.readFromParcel(parcel);
            return passwordResetData;
        }

        public PasswordResetData[] newArray(int i) {
            return new PasswordResetData[i];
        }
    };
    @Expose
    private List<PasswordResetDataButton> buttons = new ArrayList();
    @Expose
    private String msgBody;
    @Expose
    private String msgSubject;

    public int describeContents() {
        return 0;
    }

    public String getMsgSubject() {
        return this.msgSubject;
    }

    public void setMsgSubject(String str) {
        this.msgSubject = str;
    }

    public String getMsgBody() {
        return this.msgBody;
    }

    public void setMsgBody(String str) {
        this.msgBody = str;
    }

    public List<PasswordResetDataButton> getButtons() {
        return this.buttons;
    }

    public void setButtons(List<PasswordResetDataButton> list) {
        this.buttons = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Strings.toString(this.msgSubject));
        parcel.writeString(Strings.toString(this.msgBody));
        parcel.writeList(this.buttons);
    }

    /* access modifiers changed from: private */
    public void readFromParcel(Parcel parcel) {
        setMsgSubject(parcel.readString());
        setMsgBody(parcel.readString());
        parcel.readList(this.buttons, PasswordResetDataButton.class.getClassLoader());
    }
}
