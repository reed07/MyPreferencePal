package com.myfitnesspal.shared.service.syncv1.packets;

import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacket;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import java.util.List;

public class PacketPayloadExtractor<T> implements ReturningFunction1<T, List<ApiResponsePacket>> {
    private final int packetType;

    public PacketPayloadExtractor(int i) {
        this.packetType = i;
    }

    public T execute(List<ApiResponsePacket> list) {
        if (this.packetType == 154) {
            Ln.d("VALIDATION: PacketPayloadExtractor<UsernameValidationResponse> execute", new Object[0]);
        }
        T payloadForFirstPacketOfType = PacketUtils.getPayloadForFirstPacketOfType(list, this.packetType);
        if (this.packetType == 154) {
            Ln.d("VALIDATION: found payload %s", payloadForFirstPacketOfType);
        }
        return payloadForFirstPacketOfType;
    }
}
