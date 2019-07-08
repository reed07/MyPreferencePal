package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.ExerciseObjectFromServer;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class AddExerciseResponsePacket extends ApiResponsePacketImpl<ExerciseObjectFromServer> {
    public int getPacketType() {
        return 4;
    }

    public boolean processForSync() {
        return true;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(ExerciseObjectFromServer.BINARY_CREATOR));
    }
}
