package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.uacf.core.util.Strings;

public class AssociateThirdPartyRequestPacket extends ApiRequestPacketImpl {
    private final String accessToken;
    private final int serviceId;
    private final String userId;

    public AssociateThirdPartyRequestPacket(int i, String str, String str2) {
        super(PacketTypes.ThirdPartyAssociate);
        this.serviceId = i;
        this.userId = str;
        this.accessToken = str2;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return this.serviceId > 0 && Strings.notEmpty(this.userId) && Strings.notEmpty(this.accessToken);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.serviceId);
        binaryEncoder.writeString(this.userId);
        binaryEncoder.writeString(this.accessToken);
    }
}
