package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public abstract class Friend implements Parcelable {
    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        public Friend createFromParcel(Parcel parcel) {
            throw new UnsupportedOperationException();
        }

        public FacebookFriend[] newArray(int i) {
            throw new UnsupportedOperationException();
        }
    };
    @Expose
    private boolean hasBeenInvited;
    @Expose
    private String id;
    @Expose
    private String mfpUsername;

    public int describeContents() {
        return 0;
    }

    public abstract boolean matches(String str);

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getMfpUsername() {
        return this.mfpUsername;
    }

    public void setMfpUsername(String str) {
        this.mfpUsername = str;
    }

    public boolean isOnMfp() {
        return Strings.notEmpty(this.mfpUsername);
    }

    public boolean hasBeenInvited() {
        return this.hasBeenInvited;
    }

    public void setHasBeenInvited(boolean z) {
        this.hasBeenInvited = z;
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.mfpUsername = parcel.readString();
        this.hasBeenInvited = parcel.readByte() != 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Strings.toString(this.id));
        parcel.writeString(Strings.toString(this.mfpUsername));
        parcel.writeByte(this.hasBeenInvited ? (byte) 1 : 0);
    }

    public void copy(Friend friend) {
        Parcel obtain = Parcel.obtain();
        friend.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        readFromParcel(obtain);
    }
}
