package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public interface ApiRequestPacket {
    void writeData(BinaryEncoder binaryEncoder);
}
