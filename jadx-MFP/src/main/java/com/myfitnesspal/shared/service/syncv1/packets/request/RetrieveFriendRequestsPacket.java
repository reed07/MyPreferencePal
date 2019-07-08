package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class RetrieveFriendRequestsPacket extends ApiRequestPacketImpl {
    private final int limit;
    private final int offset;
    private final int requestType;

    public static final class RequestTypes {
        public static final int RECEIVED = 1;
        public static final int SENT = 2;
    }

    public RetrieveFriendRequestsPacket(int i, int i2, int i3) {
        super(117);
        this.requestType = i;
        this.limit = i2;
        this.offset = i3;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        int i = this.requestType;
        if (i == 1 || i == 2) {
            int i2 = this.offset;
            if (i2 >= 0) {
                int i3 = this.limit;
                if (i3 > 0 && i2 % i3 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.requestType);
        binaryEncoder.write4ByteInt((long) this.limit);
        binaryEncoder.write4ByteInt((long) this.offset);
    }
}
