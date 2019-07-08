package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;

public class ResendEmailVerificationToUserRequestPacket extends ApiRequestPacketImpl {
    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
    }

    public ResendEmailVerificationToUserRequestPacket() {
        super(PacketTypes.ResendEmailVerificationToUser);
    }
}
