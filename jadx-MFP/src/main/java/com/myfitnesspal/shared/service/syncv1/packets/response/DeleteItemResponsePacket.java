package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.DeleteItemObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import javax.inject.Inject;

public class DeleteItemResponsePacket extends ApiResponsePacketImpl<DeleteItemObject> {
    @Inject
    RemindersService remindersService;

    public int getPacketType() {
        return 17;
    }

    public boolean processForSync() {
        return true;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(DeleteItemObject.BINARY_CREATOR));
    }
}
