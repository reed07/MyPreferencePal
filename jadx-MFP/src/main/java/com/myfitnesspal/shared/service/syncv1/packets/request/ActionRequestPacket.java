package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class ActionRequestPacket extends InformationRequestPacket {
    public ActionRequestPacket() {
        super(101);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        super.writeDataInternal(binaryEncoder);
        binaryEncoder.write2ByteInt(1);
    }
}
