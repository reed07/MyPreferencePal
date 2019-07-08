package com.myfitnesspal.shared.service.syncv1.packets;

import com.myfitnesspal.shared.service.syncv1.packets.response.ApiResponsePacket;
import com.uacf.core.util.ReturningFunction1;
import java.util.Arrays;
import java.util.List;

public class PacketPayloadListExtractor<T> implements ReturningFunction1<List<T>, List<ApiResponsePacket>> {
    private final List<Integer> packetTypes;

    public PacketPayloadListExtractor(int i) {
        this(Arrays.asList(new Integer[]{Integer.valueOf(i)}));
    }

    public PacketPayloadListExtractor(List<Integer> list) {
        this.packetTypes = list;
    }

    public PacketPayloadListExtractor(Integer... numArr) {
        this(Arrays.asList(numArr));
    }

    public List<T> execute(List<ApiResponsePacket> list) {
        return PacketUtils.getPayloadForAllPacketsOfTypes(list, this.packetTypes);
    }
}
