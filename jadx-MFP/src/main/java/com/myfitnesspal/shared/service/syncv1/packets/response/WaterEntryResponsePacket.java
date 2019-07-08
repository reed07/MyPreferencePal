package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.WaterEntryObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class WaterEntryResponsePacket extends ApiResponsePacketImpl<WaterEntryObject> {
    public int getPacketType() {
        return 16;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(WaterEntryObject.BINARY_CREATOR));
    }
}
