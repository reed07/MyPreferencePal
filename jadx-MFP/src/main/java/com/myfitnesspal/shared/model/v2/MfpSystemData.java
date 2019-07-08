package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class MfpSystemData implements Parcelable {
    public static final Creator<MfpSystemData> CREATOR = new Creator<MfpSystemData>() {
        public MfpSystemData createFromParcel(Parcel parcel) {
            return new MfpSystemData(parcel);
        }

        public MfpSystemData[] newArray(int i) {
            return new MfpSystemData[i];
        }
    };
    @Expose
    private int bulletinsDeleted;
    @Expose
    private int bulletinsRead;
    @Expose
    private int loginStreak;
    @Expose
    private int unseenNotifications;

    public int describeContents() {
        return 0;
    }

    public MfpSystemData() {
    }

    public MfpSystemData(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setLoginStreak(int i) {
        this.loginStreak = i;
    }

    public void setBulletinsRead(int i) {
        this.bulletinsRead = i;
    }

    public void setBulletinsDeleted(int i) {
        this.bulletinsDeleted = i;
    }

    public void setUnseenNotifications(int i) {
        this.unseenNotifications = i;
    }

    public int getLoginStreak() {
        return this.loginStreak;
    }

    public int getBulletinsRead() {
        return this.bulletinsRead;
    }

    public int getBulletinsDeleted() {
        return this.bulletinsDeleted;
    }

    public int getUnseenNotifications() {
        return this.unseenNotifications;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.loginStreak);
        parcel.writeInt(this.bulletinsRead);
        parcel.writeInt(this.bulletinsDeleted);
        parcel.writeInt(this.unseenNotifications);
    }

    private void readFromParcel(Parcel parcel) {
        this.loginStreak = parcel.readInt();
        this.bulletinsRead = parcel.readInt();
        this.bulletinsDeleted = parcel.readInt();
        this.unseenNotifications = parcel.readInt();
    }
}
