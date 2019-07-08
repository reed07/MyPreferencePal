package com.myfitnesspal.shared.service.syncv1.packets;

import java.io.IOException;

public class MfpPacketException extends IOException {
    private final int type;

    public MfpPacketException(int i) {
        super(String.format("PACKET EXCEPTION: packet type = %s", new Object[]{Integer.valueOf(i)}));
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}
