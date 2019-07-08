package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;
import com.uacf.core.util.VersionUtils;

public class InformationRequestPacket extends ApiRequestPacketImpl {
    private static long nextRequestSerialNumber = 1;

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return true;
    }

    private static long nextSerialNumber() {
        long j = nextRequestSerialNumber;
        nextRequestSerialNumber = 1 + j;
        return j;
    }

    public InformationRequestPacket() {
        super(100);
    }

    protected InformationRequestPacket(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        writeVersionInformation(binaryEncoder);
        writeSerialNumber(binaryEncoder);
        writeFlagsAndPlatformInformation(binaryEncoder);
        writeCurrentUUID(binaryEncoder);
        writeCurrentDeviceToken(binaryEncoder);
    }

    private void writeSerialNumber(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(nextSerialNumber());
    }

    private void writeFlagsAndPlatformInformation(BinaryEncoder binaryEncoder) {
        int platformMajorVersion = VersionUtils.getPlatformMajorVersion();
        int platformMinorVersion = VersionUtils.getPlatformMinorVersion();
        binaryEncoder.write2ByteInt(0);
        binaryEncoder.write2ByteInt(2);
        binaryEncoder.write2ByteInt(platformMajorVersion);
        binaryEncoder.write2ByteInt(platformMinorVersion);
    }

    /* access modifiers changed from: protected */
    public void dump(StringBuilder sb) {
        sb.append(Strings.tabify("nextRequestSerialNumber", Long.valueOf(nextRequestSerialNumber), 20));
    }
}
