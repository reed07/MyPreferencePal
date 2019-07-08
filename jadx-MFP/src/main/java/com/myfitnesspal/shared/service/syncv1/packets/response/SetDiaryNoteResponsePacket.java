package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.DiaryNoteObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;

public class SetDiaryNoteResponsePacket extends ApiResponsePacketImpl<DiaryNoteObject> {
    public int getPacketType() {
        return 23;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        setPayload(binaryDecoder.decodeObject(DiaryNoteObject.BINARY_CREATOR));
    }
}
