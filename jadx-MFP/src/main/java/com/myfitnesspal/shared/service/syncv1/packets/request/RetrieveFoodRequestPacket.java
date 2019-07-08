package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;

public class RetrieveFoodRequestPacket extends ApiRequestPacketImpl {
    private long masterId;
    private boolean retrieveMostRecent;
    private String uid;

    public RetrieveFoodRequestPacket(long j, String str, boolean z) {
        super(159);
        this.masterId = j;
        this.uid = str;
        this.retrieveMostRecent = z;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(this.masterId);
        binaryEncoder.writeString(this.uid);
        binaryEncoder.write2ByteInt(this.retrieveMostRecent ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return this.masterId != 0 || !Strings.isEmpty(this.uid);
    }
}
