package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class DissociateThirdPartyRequestPacket extends ApiRequestPacketImpl {
    private final int serviceId;

    public DissociateThirdPartyRequestPacket(int i) {
        super(141);
        this.serviceId = i;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return this.serviceId > 0;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.serviceId);
    }
}
