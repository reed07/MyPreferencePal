package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.ThirdPartyAccountInformationObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;

public class ThirdPartyAccountInfoResponsePacket extends ApiResponsePacketImpl<ThirdPartyAccountInformationObject> {
    public int getPacketType() {
        return 150;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        setPayload(binaryDecoder.decodeObject(ThirdPartyAccountInformationObject.BINARY_CREATOR));
    }
}
