package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.constants.SyncConstants;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.packets.MfpPacketException;
import com.uacf.core.exception.UacfNotImplementedException;

public class ApiResponsePacketHeader implements ApiResponsePacket {
    private int packetType;

    public Object getPayload() {
        return null;
    }

    public void readData(BinaryDecoder binaryDecoder) throws MfpPacketException {
        if (binaryDecoder.decode2ByteInt() != 1235) {
            throwException(SyncConstants.MAGIC_NUMBER);
        }
        long decode4ByteInt = binaryDecoder.decode4ByteInt();
        binaryDecoder.decode2ByteInt();
        if (binaryDecoder.getTotalBytesLeft() < decode4ByteInt - 8) {
            throwException(0);
        }
        this.packetType = binaryDecoder.decode2ByteInt();
    }

    public int getPacketType() {
        return this.packetType;
    }

    public boolean processForSync() {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    private void throwException(int i) throws MfpPacketException {
        throw new MfpPacketException(i);
    }
}
