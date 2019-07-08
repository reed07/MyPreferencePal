package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.Date;

public class CompleteDiaryDayRequestPacket extends ApiRequestPacketImpl {
    private Date date;

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return true;
    }

    public CompleteDiaryDayRequestPacket(Date date2) {
        super(125);
        this.date = date2;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeDate(this.date);
    }
}
