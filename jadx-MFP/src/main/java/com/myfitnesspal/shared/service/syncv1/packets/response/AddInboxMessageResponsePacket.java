package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.InboxMessageObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;

public class AddInboxMessageResponsePacket extends ApiResponsePacketImpl<InboxMessageObject> {
    public int getPacketType() {
        return 27;
    }

    public void readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        setPayload(binaryDecoder.decodeObject(InboxMessageObject.BINARY_CREATOR));
    }
}
