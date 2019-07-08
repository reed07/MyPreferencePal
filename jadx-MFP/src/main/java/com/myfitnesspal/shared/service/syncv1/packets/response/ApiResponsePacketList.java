package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;
import com.myfitnesspal.shared.service.syncv1.packets.PacketFactory;
import java.util.ArrayList;

public class ApiResponsePacketList extends ArrayList<ApiResponsePacket> {
    private int expectedItemCount;
    private final PacketFactory packetFactory;

    public ApiResponsePacketList(PacketFactory packetFactory2) {
        this.packetFactory = packetFactory2;
    }

    public int getExpectedItemCount() {
        return this.expectedItemCount;
    }

    public ApiResponsePacketList withExpectedItemCount(int i) {
        this.expectedItemCount = i;
        return this;
    }

    public ApiResponsePacketList readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        while (binaryDecoder.hasMoreData()) {
            ApiResponsePacket createPacket = this.packetFactory.createPacket(0);
            createPacket.readData(binaryDecoder);
            if (!binaryDecoder.isBorked()) {
                ApiResponsePacket createPacket2 = this.packetFactory.createPacket(createPacket.getPacketType());
                if (createPacket2 != null) {
                    createPacket2.readData(binaryDecoder);
                    add(createPacket2);
                    if (this.expectedItemCount > 0 && size() == this.expectedItemCount) {
                        break;
                    }
                }
            } else {
                throw new MfpPacketException(0);
            }
        }
        return this;
    }
}
