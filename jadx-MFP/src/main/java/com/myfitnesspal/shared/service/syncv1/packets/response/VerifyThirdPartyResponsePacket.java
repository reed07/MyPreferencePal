package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.VerifyThirdPartyObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class VerifyThirdPartyResponsePacket extends ApiResponsePacketImpl<VerifyThirdPartyObject> {
    public int getPacketType() {
        return 134;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(VerifyThirdPartyObject.BINARY_CREATOR));
    }
}
