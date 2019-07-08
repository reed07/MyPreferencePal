package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class SetFoodNotesResponsePacket extends ApiResponsePacketImpl<FoodNotes> {
    public int getPacketType() {
        return 44;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(FoodNotes.BINARY_CREATOR));
    }
}
