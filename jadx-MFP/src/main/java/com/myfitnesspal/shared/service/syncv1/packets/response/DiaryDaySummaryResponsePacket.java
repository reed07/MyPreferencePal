package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.DiaryDaySummaryObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;

public class DiaryDaySummaryResponsePacket extends ApiResponsePacketImpl<DiaryDaySummaryObject> {
    public int getPacketType() {
        return PacketTypes.DiaryDaySummary;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(DiaryDaySummaryObject.BINARY_CREATOR));
    }
}
