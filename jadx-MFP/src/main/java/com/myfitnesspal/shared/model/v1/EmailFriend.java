package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.HashSet;
import java.util.Set;

public class EmailFriend extends Friend {
    public static final Creator<EmailFriend> CREATOR = new Creator<EmailFriend>() {
        public EmailFriend createFromParcel(Parcel parcel) {
            EmailFriend emailFriend = new EmailFriend();
            emailFriend.readFromParcel(parcel);
            return emailFriend;
        }

        public EmailFriend[] newArray(int i) {
            return new EmailFriend[i];
        }
    };
    private HashSet<String> emailAddresses = new HashSet<>();
    private String name;
    private String thumbnailUrl;

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.thumbnailUrl = parcel.readString();
        this.name = parcel.readString();
        try {
            this.emailAddresses = (HashSet) parcel.readSerializable();
        } catch (NullPointerException e) {
            Ln.e(e);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.thumbnailUrl);
        parcel.writeString(this.name);
        parcel.writeInt(this.emailAddresses.size());
        parcel.writeSerializable(this.emailAddresses);
    }

    public EmailFriend() {
    }

    public EmailFriend(String str, String str2, String str3) {
        this.thumbnailUrl = str2;
        this.name = str3;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String str) {
        this.thumbnailUrl = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Set<String> getEmailAddresses() {
        return this.emailAddresses;
    }

    public boolean hasEmailAddresses() {
        HashSet<String> hashSet = this.emailAddresses;
        return hashSet != null && hashSet.size() > 0;
    }

    public boolean hasMultipleEmailAddresses() {
        HashSet<String> hashSet = this.emailAddresses;
        return hashSet != null && hashSet.size() > 1;
    }

    public String getFirstEmailAddress() {
        return Strings.toString(hasEmailAddresses() ? this.emailAddresses.iterator().next() : null);
    }

    public boolean hasEmailAddress(String str) {
        boolean z = false;
        if (Strings.isEmpty(str)) {
            return false;
        }
        if (hasEmailAddresses() && this.emailAddresses.contains(str)) {
            z = true;
        }
        return z;
    }

    public void addEmailAddress(String str) {
        if (!Strings.isEmpty(str)) {
            if (this.emailAddresses == null) {
                this.emailAddresses = new HashSet<>();
            }
            if (!hasEmailAddress(str)) {
                this.emailAddresses.add(str);
            }
        }
    }

    public boolean matches(String str) {
        return hasEmailAddress(str);
    }
}
