package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.SyncPointerService;
import javax.inject.Inject;

public class SyncFirstRequestPacket extends ApiRequestPacketImpl {
    private final int mode;
    @Inject
    SyncPointerService syncPointerService;

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return true;
    }

    public SyncFirstRequestPacket(int i) {
        super(1);
        MyFitnessPalApp.getInstance().component().inject((ApiRequestPacketImpl) this);
        this.mode = i;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        writeVersionInformation(binaryEncoder);
        writeModeInformation(binaryEncoder);
        writeFlagsAndPlatformInformation(binaryEncoder);
        writeCurrentUUID(binaryEncoder);
        writeCurrentDeviceToken(binaryEncoder);
        writeLastSyncTimeEntries(binaryEncoder);
        binaryEncoder.write2ByteInt(1);
    }

    private void writeFlagsAndPlatformInformation(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(4);
    }

    /* access modifiers changed from: protected */
    public void writeLastSyncTimeEntries(BinaryEncoder binaryEncoder) {
        switch (this.mode) {
            case 2:
                binaryEncoder.writeListWithTwoByteSize(this.syncPointerService.getLastSyncPointers());
                return;
            case 3:
            case 4:
                binaryEncoder.write2ByteInt(0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void writeModeInformation(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.mode);
    }
}
