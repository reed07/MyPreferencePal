package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v1.MealIngredientsContainer;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;

public class MealIngredientsResponsePacket extends ApiResponsePacketImpl<MealIngredientsContainer> {
    public int getPacketType() {
        return 11;
    }

    public void readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        setPayload(binaryDecoder.decodeObject(MealIngredientsContainer.BINARY_CREATOR));
    }
}
