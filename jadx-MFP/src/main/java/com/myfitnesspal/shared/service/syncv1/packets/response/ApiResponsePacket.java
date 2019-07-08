package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;

public interface ApiResponsePacket<T> {
    int getPacketType();

    T getPayload();

    boolean processForSync();

    void readData(BinaryDecoder binaryDecoder) throws MfpPacketException;
}
