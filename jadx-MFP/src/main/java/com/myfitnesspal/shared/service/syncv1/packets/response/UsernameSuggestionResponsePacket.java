package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.model.v15.UsernameSuggestionObject;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;

public class UsernameSuggestionResponsePacket extends ApiResponsePacketImpl<UsernameSuggestionObject> {
    public int getPacketType() {
        return PacketTypes.SuggestUsernameResponse;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setPayload(binaryDecoder.decodeObject(UsernameSuggestionObject.BINARY_CREATOR));
    }
}
