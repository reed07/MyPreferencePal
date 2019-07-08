package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;

public class TakeActionOnInvitationRequestPacket extends ApiRequestPacketImpl {
    private int action;
    private long requestMasterId;
    private String requestUid;

    public static final class ActionTypes {
        public static final int ACCEPT = 1;
        public static final int BLOCK = 3;
        public static final int REJECT = 2;
    }

    public static final class ErrorCodes {
        public static final int INVALID_INVITATION_ID = 256;
        public static final int SENDER_IS_DELETED = 257;
        public static final int TOO_MANY_FRIENDS = 258;
    }

    public TakeActionOnInvitationRequestPacket() {
        super(119);
    }

    public long getRequestMasterId() {
        return this.requestMasterId;
    }

    public void setRequestMasterId(long j) {
        this.requestMasterId = j;
    }

    public String getRequestUid() {
        return this.requestUid;
    }

    public void setRequestUid(String str) {
        this.requestUid = str;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int i) {
        this.action = i;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        if (this.requestMasterId > 0 || Strings.notEmpty(this.requestUid)) {
            int i = this.action;
            if (i == 1 || i == 2 || i == 3) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(this.requestMasterId);
        binaryEncoder.writeString(this.requestUid);
        binaryEncoder.write2ByteInt(this.action);
    }
}
