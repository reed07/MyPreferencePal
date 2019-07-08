package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.UserPropertyUpdateObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class UserPropertyUpdateResponsePacket extends ApiResponsePacketImpl<UserPropertyUpdateObject> {
    public int getPacketType() {
        return 13;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(UserPropertyUpdateObject.BINARY_CREATOR));
    }
}
