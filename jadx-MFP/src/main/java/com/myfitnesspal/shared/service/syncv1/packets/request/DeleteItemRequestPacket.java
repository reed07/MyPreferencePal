package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;

public class DeleteItemRequestPacket extends ApiRequestPacketImpl {
    private final int destructionType;
    private final int itemType;
    private final long masterId;
    private final String uid;

    public DeleteItemRequestPacket(int i, long j, String str, int i2) {
        super(17);
        this.itemType = i;
        this.masterId = j;
        this.uid = str;
        this.destructionType = i2;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return this.itemType > 0 && (this.masterId > 0 || Strings.notEmpty(this.uid));
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.itemType);
        binaryEncoder.write8ByteInt(this.masterId);
        binaryEncoder.writeString(this.uid);
        binaryEncoder.write2ByteInt(this.destructionType);
    }
}
