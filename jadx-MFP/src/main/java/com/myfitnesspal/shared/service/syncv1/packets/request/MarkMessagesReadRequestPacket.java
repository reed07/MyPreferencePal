package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Tuple2;
import java.util.Collection;
import java.util.List;

public class MarkMessagesReadRequestPacket extends ApiRequestPacketImpl {
    private int actionCode;
    private List<Tuple2<Long, String>> messageIds;

    public static final class ActionCodes {
        public static final int MARK_ALL_MESSAGES_READ = 2;
        public static final int MARK_ALL_MESSAGES_UNREAD = 4;
        public static final int MARK_MESSAGES_READ = 1;
        public static final int MARK_MESSAGES_UNREAD = 3;
    }

    public MarkMessagesReadRequestPacket() {
        super(114);
    }

    public int getActionCode() {
        return this.actionCode;
    }

    public void setActionCode(int i) {
        this.actionCode = i;
    }

    public List<Tuple2<Long, String>> getMessageIds() {
        return this.messageIds;
    }

    public void setMessageIds(List<Tuple2<Long, String>> list) {
        this.messageIds = list;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        int i = this.actionCode;
        return i == 1 || i == 2;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.actionCode);
        binaryEncoder.write4ByteInt((long) CollectionUtils.size((Collection<?>) this.messageIds));
        for (Tuple2 tuple2 : this.messageIds) {
            binaryEncoder.write8ByteInt(((Long) tuple2.getItem1()).longValue());
            binaryEncoder.writeString((String) tuple2.getItem2());
        }
    }
}
