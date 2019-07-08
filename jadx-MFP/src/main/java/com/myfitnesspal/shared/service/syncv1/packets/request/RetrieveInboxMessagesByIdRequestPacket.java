package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Tuple2;
import java.util.Collection;
import java.util.List;

public class RetrieveInboxMessagesByIdRequestPacket extends ApiRequestPacketImpl {
    private List<Tuple2<Long, String>> messageIds;

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return true;
    }

    public RetrieveInboxMessagesByIdRequestPacket() {
        super(113);
    }

    public List<Tuple2<Long, String>> getMessageIds() {
        return this.messageIds;
    }

    public void setMessageIds(List<Tuple2<Long, String>> list) {
        this.messageIds = list;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write4ByteInt((long) CollectionUtils.size((Collection<?>) this.messageIds));
        for (Tuple2 tuple2 : this.messageIds) {
            binaryEncoder.write8ByteInt(((Long) tuple2.getItem1()).longValue());
            binaryEncoder.writeString((String) tuple2.getItem2());
        }
    }
}
