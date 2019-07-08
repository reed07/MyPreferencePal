package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.DissociateThirdPartyObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;

public class DissociateThirdPartyResponsePacket extends ApiResponsePacketImpl<DissociateThirdPartyObject> {
    public int getPacketType() {
        return PacketTypes.ThirdPartyDissociateResponse;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(DissociateThirdPartyObject.BINARY_CREATOR));
    }
}
