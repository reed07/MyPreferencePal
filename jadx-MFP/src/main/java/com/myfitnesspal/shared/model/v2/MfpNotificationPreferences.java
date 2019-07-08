package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class MfpNotificationPreferences implements Parcelable {
    public static final Creator<MfpNotificationPreferences> CREATOR = new Creator<MfpNotificationPreferences>() {
        public MfpNotificationPreferences createFromParcel(Parcel parcel) {
            return new MfpNotificationPreferences(parcel);
        }

        public MfpNotificationPreferences[] newArray(int i) {
            return new MfpNotificationPreferences[i];
        }
    };
    @Expose
    private List<String> activityNotifyByEmail;
    @Expose
    private List<String> activityNotifyByPush;
    @Expose
    private boolean autoReminderOptOut;
    @Expose
    private List<String> systemEmails;

    public int describeContents() {
        return 0;
    }

    public MfpNotificationPreferences() {
    }

    public MfpNotificationPreferences(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setActivityNotifyByEmail(List<String> list) {
        if (list == null) {
            this.activityNotifyByEmail = new ArrayList();
        } else {
            this.activityNotifyByEmail = list;
        }
    }

    public void setActivityNotifyByPush(List<String> list) {
        if (list == null) {
            this.activityNotifyByPush = new ArrayList();
        } else {
            this.activityNotifyByPush = list;
        }
    }

    public void setSystemEmails(List<String> list) {
        if (list == null) {
            this.systemEmails = new ArrayList();
        } else {
            this.systemEmails = list;
        }
    }

    public void setAutoReminderOptOut(boolean z) {
        this.autoReminderOptOut = z;
    }

    public List<String> getActivityNotifyByEmail() {
        return this.activityNotifyByEmail;
    }

    public List<String> getActivityNotifyByPush() {
        return this.activityNotifyByPush;
    }

    public List<String> getSystemEmails() {
        return this.systemEmails;
    }

    public boolean isAutoReminderOptOut() {
        return this.autoReminderOptOut;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.activityNotifyByEmail);
        parcel.writeList(this.activityNotifyByPush);
        parcel.writeList(this.systemEmails);
        parcel.writeByte(this.autoReminderOptOut ? (byte) 1 : 0);
    }

    private void readFromParcel(Parcel parcel) {
        this.activityNotifyByEmail.clear();
        parcel.readList(this.activityNotifyByEmail, String.class.getClassLoader());
        this.activityNotifyByPush.clear();
        parcel.readList(this.activityNotifyByPush, String.class.getClassLoader());
        this.systemEmails.clear();
        parcel.readList(this.systemEmails, String.class.getClassLoader());
        this.autoReminderOptOut = parcel.readByte() != 0;
    }
}
