package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;

public class RetrieveFriendListRequestPacket extends ApiRequestPacketImpl {
    private int limit;
    private int offset;

    public RetrieveFriendListRequestPacket(int i, int i2) {
        super(PacketTypes.RetrieveFriendList);
        this.limit = i;
        this.offset = i2;
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
        int i = this.offset;
        if (i >= 0) {
            int i2 = this.limit;
            if (i2 >= 0 && i % i2 == 0) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write4ByteInt((long) this.limit);
        binaryEncoder.write4ByteInt((long) this.offset);
    }
}
