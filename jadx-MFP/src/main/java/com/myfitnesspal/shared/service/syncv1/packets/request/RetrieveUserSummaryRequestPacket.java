package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;

public class RetrieveUserSummaryRequestPacket extends ApiRequestPacketImpl {
    private String userIdOrUsername;
    private final String userUid;

    public static final class ErrorCodes {
        public static final int USER_NOT_FOUND = 256;
    }

    public RetrieveUserSummaryRequestPacket(String str, String str2) {
        super(123);
        this.userIdOrUsername = str;
        this.userUid = str2;
    }

    public String getUserIdOrUsername() {
        return this.userIdOrUsername;
    }

    public void setUserIdOrUsername(String str) {
        this.userIdOrUsername = str;
    }

    public String getUserUid() {
        return this.userUid;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return Strings.notEmpty(this.userIdOrUsername) || Strings.notEmpty(this.userUid);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        StringBuilder sb = new StringBuilder();
        sb.append("username:");
        sb.append(this.userIdOrUsername);
        binaryEncoder.writeString(sb.toString());
        binaryEncoder.writeString(this.userUid);
    }
}
