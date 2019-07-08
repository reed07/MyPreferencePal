package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.uacf.core.util.Strings;

public class InformationOrActionResponsePacket extends ApiResponsePacketImpl {
    @Expose
    private String errorMessage;
    @Expose
    private long packetCount;
    @Expose
    private int resultCode;
    @Expose
    private long serialNumber;

    public int getPacketType() {
        return 102;
    }

    public boolean processForSync() {
        return false;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.serialNumber = binaryDecoder.decode8ByteInt();
        this.resultCode = binaryDecoder.decode2ByteInt();
        this.errorMessage = Strings.toString(binaryDecoder.decodeString());
        this.packetCount = binaryDecoder.decode4ByteInt();
    }

    public long getSerialNumber() {
        return this.serialNumber;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public long getPacketCount() {
        return this.packetCount;
    }
}
