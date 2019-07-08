package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.StatusCommentObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;

public class AddStatusCommentResponsePacket extends ApiResponsePacketImpl<StatusCommentObject> {
    public int getPacketType() {
        return 28;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        setPayload(binaryDecoder.decodeObject(StatusCommentObject.BINARY_CREATOR));
    }
}
