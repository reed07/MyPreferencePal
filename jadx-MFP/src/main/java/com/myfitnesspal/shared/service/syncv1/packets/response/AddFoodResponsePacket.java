package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.FoodObjectFromServer;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class AddFoodResponsePacket extends ApiResponsePacketImpl<FoodObjectFromServer> {
    public int getPacketType() {
        return 3;
    }

    public boolean processForSync() {
        return true;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(FoodObjectFromServer.BINARY_CREATOR));
    }
}
