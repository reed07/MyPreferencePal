package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;

public class ChangePasswordRequestPacket extends ApiRequestPacketImpl {
    private final String newPassword;

    public ChangePasswordRequestPacket(String str) {
        super(140);
        this.newPassword = str;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return Strings.length(this.newPassword) >= 6;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.newPassword);
    }
}
