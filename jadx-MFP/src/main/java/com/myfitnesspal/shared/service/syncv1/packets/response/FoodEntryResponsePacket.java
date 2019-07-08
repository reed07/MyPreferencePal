package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.FoodEntryFromServer;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class FoodEntryResponsePacket extends ApiResponsePacketImpl<FoodEntryFromServer> {
    public int getPacketType() {
        return 5;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(FoodEntryFromServer.BINARY_CREATOR));
    }
}
