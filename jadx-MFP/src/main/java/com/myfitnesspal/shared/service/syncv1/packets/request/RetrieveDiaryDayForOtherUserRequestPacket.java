package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.uacf.core.util.Strings;
import java.util.Date;

public class RetrieveDiaryDayForOtherUserRequestPacket extends ApiRequestPacketImpl {
    private Date date;
    private String password;
    private String userIdentifier;

    public static final class ErrorCodes {
        public static final int ACCESS_DENIED = 257;
        public static final int INVALID_PASSWORD = 258;
        public static final int USER_DOES_NOT_EXIST = 256;
    }

    public static final class UserIdentifierTypes {
        public static final int USERNAME = 2;
        public static final int USER_ID = 1;
    }

    public RetrieveDiaryDayForOtherUserRequestPacket(String str, Date date2, String str2) {
        super(PacketTypes.RetrieveDiaryDayForOtherUser);
        this.userIdentifier = str;
        this.date = date2;
        this.password = str2;
    }

    public RetrieveDiaryDayForOtherUserRequestPacket(long j, Date date2, String str) {
        super(PacketTypes.RetrieveDiaryDayForOtherUser);
        StringBuilder sb = new StringBuilder();
        sb.append("id:");
        sb.append(j);
        this.userIdentifier = sb.toString();
        this.date = date2;
        this.password = str;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public String getUserIdentifier() {
        return this.userIdentifier;
    }

    public void setUserIdentifier(String str) {
        this.userIdentifier = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return Strings.notEmpty(this.userIdentifier);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeDate(this.date);
        binaryEncoder.writeString(this.userIdentifier);
        binaryEncoder.writeString(this.password);
    }
}
