package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class FriendRequestResponsePacket extends ApiResponsePacketImpl<FriendRequestObject> {
    public int getPacketType() {
        return 118;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(FriendRequestObject.BINARY_CREATOR));
    }
}
