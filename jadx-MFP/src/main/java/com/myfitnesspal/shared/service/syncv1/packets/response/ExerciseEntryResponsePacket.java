package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.ExerciseEntryFromServer;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class ExerciseEntryResponsePacket extends ApiResponsePacketImpl<ExerciseEntryFromServer> {
    public int getPacketType() {
        return 5;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(ExerciseEntryFromServer.BINARY_CREATOR));
    }
}
