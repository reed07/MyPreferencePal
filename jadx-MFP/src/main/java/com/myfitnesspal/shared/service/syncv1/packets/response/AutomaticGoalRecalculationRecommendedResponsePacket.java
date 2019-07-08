package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.NullObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class AutomaticGoalRecalculationRecommendedResponsePacket extends ApiResponsePacketImpl<NullObject> {
    public int getPacketType() {
        return 128;
    }

    public void readData(BinaryDecoder binaryDecoder) {
    }
}
