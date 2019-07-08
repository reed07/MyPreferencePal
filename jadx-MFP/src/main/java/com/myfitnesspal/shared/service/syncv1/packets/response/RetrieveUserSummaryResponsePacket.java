package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class RetrieveUserSummaryResponsePacket extends ApiResponsePacketImpl<UserSummaryObject> {
    public int getPacketType() {
        return 120;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(UserSummaryObject.BINARY_CREATOR));
    }
}
