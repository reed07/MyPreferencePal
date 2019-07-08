package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.uacf.core.util.Strings;

public class UsernameSuggestionRequestPacket extends ApiRequestPacketImpl {
    private String requestedUsername;

    public UsernameSuggestionRequestPacket(String str) {
        super(PacketTypes.SuggestUsername);
        this.requestedUsername = str;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return Strings.notEmpty(this.requestedUsername);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.requestedUsername);
    }
}
