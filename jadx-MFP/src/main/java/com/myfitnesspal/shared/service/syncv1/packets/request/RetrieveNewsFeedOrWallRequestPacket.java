package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;

public class RetrieveNewsFeedOrWallRequestPacket extends ApiRequestPacketImpl {
    private int feedType;
    private int limit;
    private int offset;
    private String userIdOrUsername;
    private String userUid;

    public static final class ErrorCodes {
        public static final int FEED_UNAVAILABLE = 258;
        public static final int PERMISSION_DENIED = 257;
        public static final int USER_NOT_FOUND = 256;
    }

    public static final class FeedTypes {
        public static final int MINI_FEED = 1;
        public static final int WALL = 2;
    }

    public RetrieveNewsFeedOrWallRequestPacket(String str, String str2, int i, int i2, int i3) {
        String str3;
        super(111);
        if (Strings.isEmpty(str)) {
            str3 = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("username:");
            sb.append(str);
            str3 = sb.toString();
        }
        init(str3, str2, i, i2, i3);
    }

    public RetrieveNewsFeedOrWallRequestPacket(long j, String str, int i, int i2, int i3) {
        super(111);
        init(Strings.toString(Long.valueOf(j)), str, i, i2, i3);
    }

    private void init(String str, String str2, int i, int i2, int i3) {
        this.userIdOrUsername = str;
        this.userUid = str2;
        this.feedType = i;
        this.limit = i2;
        this.offset = i3;
    }

    public int getFeedType() {
        return this.feedType;
    }

    public void setFeedType(int i) {
        this.feedType = i;
    }

    public String getUserIdOrUsername() {
        return this.userIdOrUsername;
    }

    public void setUserIdOrUsername(String str) {
        this.userIdOrUsername = str;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        boolean z = this.feedType == 1;
        boolean notEmpty = Strings.notEmpty(this.userIdOrUsername);
        Strings.notEmpty(this.userUid);
        if ((!z || notEmpty) && !notEmpty) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.feedType);
        binaryEncoder.writeString(this.userIdOrUsername);
        binaryEncoder.writeString(this.userUid);
        binaryEncoder.write4ByteInt((long) this.limit);
        binaryEncoder.write4ByteInt((long) this.offset);
        binaryEncoder.write4ByteInt(0);
    }
}
