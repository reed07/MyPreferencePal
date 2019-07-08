package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.UsernameValidationObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;

public class UsernameValidationResponsePacket extends ApiResponsePacketImpl<UsernameValidationObject> {
    public int getPacketType() {
        return PacketTypes.UsernameValidationResponse;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(UsernameValidationObject.BINARY_CREATOR));
    }
}
