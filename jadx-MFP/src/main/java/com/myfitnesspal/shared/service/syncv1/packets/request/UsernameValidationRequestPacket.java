package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public class UsernameValidationRequestPacket extends ApiRequestPacketImpl {
    private String requestedUsername;

    public UsernameValidationRequestPacket(String str) {
        super(153);
        this.requestedUsername = str;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        boolean notEmpty = Strings.notEmpty(this.requestedUsername);
        Ln.d("VALIDATION: UsernameValidationRequestPacket#validatePacketData, requestedUsername='%s', return %s", Strings.toString(this.requestedUsername), Boolean.valueOf(notEmpty));
        return notEmpty;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.requestedUsername);
    }
}
