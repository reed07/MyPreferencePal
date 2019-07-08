package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.uacf.core.util.Strings;

public class VerifyThirdPartyRequestPacket extends ApiRequestPacketImpl {
    private final boolean initialRequest;
    private final String thirdPartyAuthToken;
    private final int thirdPartyServiceId;
    private final String thirdPartyUserId;

    public VerifyThirdPartyRequestPacket(boolean z, int i, String str, String str2) {
        super(PacketTypes.ThirdPartyAccountVerify);
        this.initialRequest = z;
        this.thirdPartyServiceId = i;
        this.thirdPartyUserId = str;
        this.thirdPartyAuthToken = str2;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return this.thirdPartyServiceId > 0 && Strings.notEmpty(this.thirdPartyUserId) && Strings.notEmpty(this.thirdPartyAuthToken);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeBoolean(this.initialRequest);
        binaryEncoder.write2ByteInt(this.thirdPartyServiceId);
        binaryEncoder.writeString(this.thirdPartyUserId);
        binaryEncoder.writeString(this.thirdPartyAuthToken);
    }
}
