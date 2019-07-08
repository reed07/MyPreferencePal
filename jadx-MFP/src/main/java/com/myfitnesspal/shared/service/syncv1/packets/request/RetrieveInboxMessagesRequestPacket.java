package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class RetrieveInboxMessagesRequestPacket extends ApiRequestPacketImpl {
    private final int limit;
    private final int messageType;
    private final int offset = 0;
    private final int reserved = 0;

    public static final class MessageTypes {
        public static final int RECEIVED = 1;
        public static final int SENT = 2;
    }

    public RetrieveInboxMessagesRequestPacket(int i, int i2) {
        super(112);
        this.messageType = i;
        this.limit = i2;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        int i = this.messageType;
        if (i == 1 || i == 2) {
            int i2 = this.limit;
            if (i2 > 0) {
                int i3 = this.offset;
                if (i3 >= 0 && i3 % i2 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.messageType);
        binaryEncoder.write4ByteInt((long) this.limit);
        binaryEncoder.write4ByteInt((long) this.offset);
        binaryEncoder.write4ByteInt((long) this.reserved);
    }
}
