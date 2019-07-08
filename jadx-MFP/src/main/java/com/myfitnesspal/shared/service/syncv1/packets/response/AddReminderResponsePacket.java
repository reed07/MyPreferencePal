package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;
import dagger.Lazy;
import javax.inject.Inject;

public class AddReminderResponsePacket extends ApiResponsePacketImpl<ReminderObject> {
    @Inject
    Lazy<RemindersService> remindersService;

    public int getPacketType() {
        return 35;
    }

    public void readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        setPayload(binaryDecoder.decodeObject(ReminderObject.BINARY_CREATOR));
    }

    public boolean processForSync() {
        return ((RemindersService) this.remindersService.get()).insertIfMissing((ReminderObject) getPayload());
    }
}
