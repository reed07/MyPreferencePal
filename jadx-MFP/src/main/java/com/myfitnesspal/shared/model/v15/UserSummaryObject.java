package com.myfitnesspal.shared.model.v15;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.ArrayList;
import java.util.Date;

public class UserSummaryObject extends BaseObject {
    public static final BinaryCreator<UserSummaryObject> BINARY_CREATOR = new BinaryCreator<UserSummaryObject>() {
        public UserSummaryObject create(BinaryDecoder binaryDecoder) {
            UserSummaryObject userSummaryObject = new UserSummaryObject();
            userSummaryObject.readData(binaryDecoder);
            return userSummaryObject;
        }
    };
    @Expose
    private String city;
    @Expose
    private int flags;
    @Expose
    private int friendCount;
    @Expose
    private String fullSizeImageUrl;
    @Expose
    private boolean isProfileViewable;
    @Expose
    private Date lastLoginTimestamp;
    @Expose
    private int loginStreak;
    @Expose
    private float poundsLost;
    @Expose
    private String state;
    @Expose
    private String thumbnailImageUrl;
    @Expose
    private String username;

    public static final class FlagTypes {
        public static final int FRIEND_OF_REQUESTOR = 1;
        public static final int IS_MALE = 2;
        public static final int PRIVACY_MASK = 12;
    }

    public static class LIST_MAPPER extends ArrayList<UserSummaryObject> {
    }

    public static final class PrivacyTypes {
        public static final int FRIENDS_ONLY = 8;
        public static final int PASSWORD_PROTECTED = 12;
        public static final int PRIVATE = 0;
        public static final int PUBLIC = 4;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getThumbnailImageUrl() {
        return this.thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String str) {
        this.thumbnailImageUrl = str;
    }

    public String getFullSizeImageUrl() {
        return this.fullSizeImageUrl;
    }

    public void setFullSizeImageUrl(String str) {
        this.fullSizeImageUrl = str;
    }

    public Date getLastLoginTimestamp() {
        return this.lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(Date date) {
        this.lastLoginTimestamp = date;
    }

    public float getPoundsLost() {
        return this.poundsLost;
    }

    public void setPoundsLost(float f) {
        this.poundsLost = f;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public boolean isFriendOfRequestor() {
        return checkFlag(1);
    }

    public void setIsFriendOfRequestor(boolean z) {
        if (z) {
            this.flags |= 1;
        } else {
            this.flags &= -2;
        }
    }

    public boolean isMale() {
        return checkFlag(2);
    }

    public int getDiaryFlags() {
        return getFlags() & 12;
    }

    public boolean isDiaryPrivate() {
        return getDiaryFlags() == 0;
    }

    public boolean isDiaryPublic() {
        return getDiaryFlags() == 4;
    }

    public boolean isDiaryPasswordProtected() {
        return getDiaryFlags() == 12;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public int getFriendCount() {
        return this.friendCount;
    }

    public void setFriendCount(int i) {
        this.friendCount = i;
    }

    public boolean isProfileViewable() {
        return this.isProfileViewable;
    }

    public void setProfileViewable(boolean z) {
        this.isProfileViewable = z;
    }

    public int getLoginStreak() {
        return this.loginStreak;
    }

    public void setLoginStreak(int i) {
        this.loginStreak = i;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(getMasterId());
        binaryEncoder.writeString(getUid());
        binaryEncoder.writeString(this.username);
        binaryEncoder.writeString(this.thumbnailImageUrl);
        binaryEncoder.writeString(this.fullSizeImageUrl);
        binaryEncoder.writeString(this.city);
        binaryEncoder.writeString(this.state);
        binaryEncoder.write4ByteInt((long) this.friendCount);
        binaryEncoder.write4ByteInt((long) this.loginStreak);
        binaryEncoder.writeBoolean(this.isProfileViewable);
        binaryEncoder.writeTimestamp(this.lastLoginTimestamp);
        binaryEncoder.writeFloat(this.poundsLost);
        binaryEncoder.write4ByteInt((long) this.flags);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setMasterId(binaryDecoder.decode8ByteInt());
        setUid(binaryDecoder.decodeString());
        this.username = binaryDecoder.decodeString();
        this.thumbnailImageUrl = binaryDecoder.decodeString();
        this.fullSizeImageUrl = binaryDecoder.decodeString();
        this.city = binaryDecoder.decodeString();
        this.state = binaryDecoder.decodeString();
        this.friendCount = (int) binaryDecoder.decode4ByteInt();
        this.loginStreak = (int) binaryDecoder.decode4ByteInt();
        this.isProfileViewable = binaryDecoder.decodeBoolean();
        this.lastLoginTimestamp = binaryDecoder.decodeTimestamp();
        this.poundsLost = binaryDecoder.decodeFloat();
        this.flags = (int) binaryDecoder.decode4ByteInt();
    }

    private boolean checkFlag(int i) {
        return (getFlags() & i) == i;
    }
}
