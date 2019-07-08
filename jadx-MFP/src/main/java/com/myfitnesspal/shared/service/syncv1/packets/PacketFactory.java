package com.myfitnesspal.shared.service.syncv1.packets;

import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacket;

public interface PacketFactory {
    ApiResponsePacket createPacket(int i);
}
