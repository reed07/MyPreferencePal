package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.EmailUniquenessCheckObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;

public class EmailUniquenessCheckResponsePacket extends ApiResponsePacketImpl<EmailUniquenessCheckObject> {
    public int getPacketType() {
        return PacketTypes.EmailUniquenessCheckResponse;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(EmailUniquenessCheckObject.BINARY_CREATOR));
    }
}
