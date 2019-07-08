package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.CompleteDiaryDayResultObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class CompleteDiaryDayResponsePacket extends ApiResponsePacketImpl<CompleteDiaryDayResultObject> {
    public int getPacketType() {
        return 126;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(CompleteDiaryDayResultObject.BINARY_CREATOR));
    }
}
