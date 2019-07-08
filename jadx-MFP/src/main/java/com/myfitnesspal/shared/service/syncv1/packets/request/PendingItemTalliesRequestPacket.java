package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class PendingItemTalliesRequestPacket extends ApiRequestPacketImpl {
    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
    }

    public PendingItemTalliesRequestPacket() {
        super(130);
    }
}
