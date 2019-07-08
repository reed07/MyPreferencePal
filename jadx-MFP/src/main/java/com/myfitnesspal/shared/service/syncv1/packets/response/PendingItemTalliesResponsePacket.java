package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.PendingItemTalliesObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class PendingItemTalliesResponsePacket extends ApiResponsePacketImpl<PendingItemTalliesObject> {
    public int getPacketType() {
        return 129;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(PendingItemTalliesObject.BINARY_CREATOR));
    }
}
