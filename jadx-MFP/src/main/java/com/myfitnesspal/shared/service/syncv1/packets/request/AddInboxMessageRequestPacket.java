package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;
import java.util.Date;

public class AddInboxMessageRequestPacket extends ApiRequestPacketImpl {
    private final String body;
    private final long inReplyToMessageId;
    private final String inReplyToMessageUid;
    private final String inboxUid;
    private final long recipientUserId;
    private final String recipientUserUid;
    private final String recipientUsername;
    private final String subject;

    public static final class MessageTypes {
        public static final int RECEIVED = 1;
        public static final int SENT = 2;
    }

    public AddInboxMessageRequestPacket(long j, String str, String str2, long j2, String str3, String str4, String str5, String str6) {
        super(27);
        this.inReplyToMessageId = j;
        this.inboxUid = str;
        this.inReplyToMessageUid = str2;
        this.recipientUserId = j2;
        this.recipientUserUid = str3;
        this.recipientUsername = str4;
        this.subject = str5;
        this.body = str6;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return Strings.notEmpty(this.recipientUsername);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(2);
        binaryEncoder.write8ByteInt(0);
        binaryEncoder.writeString(this.inboxUid);
        binaryEncoder.write8ByteInt(this.inReplyToMessageId);
        binaryEncoder.writeString(this.inReplyToMessageUid);
        binaryEncoder.write8ByteInt(this.recipientUserId);
        binaryEncoder.writeString(this.recipientUserUid);
        binaryEncoder.writeString(this.recipientUsername);
        binaryEncoder.writeString("");
        binaryEncoder.write2ByteInt(0);
        binaryEncoder.writeTimestamp(new Date());
        binaryEncoder.writeTimestamp(new Date());
        binaryEncoder.writeString(this.subject);
        binaryEncoder.writeString(this.body);
    }
}
