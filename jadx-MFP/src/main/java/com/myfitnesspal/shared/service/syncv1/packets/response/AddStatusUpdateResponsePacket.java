package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.StatusUpdateObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;

public class AddStatusUpdateResponsePacket extends ApiResponsePacketImpl<StatusUpdateObject> {
    public int getPacketType() {
        return 26;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        setPayload(binaryDecoder.decodeObject(StatusUpdateObject.BINARY_CREATOR));
    }
}
