package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.FriendCheckResponseObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class FriendCheckResponsePacket extends ApiResponsePacketImpl<FriendCheckResponseObject> {
    private final int packetType;

    public FriendCheckResponsePacket(int i) {
        this.packetType = i;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(FriendCheckResponseObject.BINARY_CREATOR));
    }

    public int getPacketType() {
        return this.packetType;
    }
}
