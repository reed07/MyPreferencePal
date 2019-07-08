package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.Date;

public class InboxMessageObject extends BaseObject {
    public static final BinaryCreator<InboxMessageObject> BINARY_CREATOR = new BinaryCreator<InboxMessageObject>() {
        public InboxMessageObject create(BinaryDecoder binaryDecoder) {
            InboxMessageObject inboxMessageObject = new InboxMessageObject();
            inboxMessageObject.readData(binaryDecoder);
            return inboxMessageObject;
        }
    };
    private String body;
    private int flags;
    private int folder;
    private long inReplyToMasterId;
    private String inReplyToUid;
    private String otherPartyImageUrl;
    private String otherPartyUid;
    private long otherPartyUserId;
    private String otherPartyUsername;
    private Date readAt;
    private Date sentAt;
    private String subject;

    public static final class Flags {
        public static final int DELETED = 1;
        public static final int DELETED_FROM_SENT_MAIL = 2;
        public static final int HAS_BEEN_READ = 16;
        public static final int HAS_BEEN_REPLIED_TO = 8;
        public static final int SPAM = 4;
    }

    public static final class FolderType {
        public static final int RECEIVED = 1;
        public static final int SENT = 2;
    }

    public int getFolder() {
        return this.folder;
    }

    public void setFolder(int i) {
        this.folder = i;
    }

    public long getInReplyToMasterId() {
        return this.inReplyToMasterId;
    }

    public void setInReplyToMasterId(long j) {
        this.inReplyToMasterId = j;
    }

    public String getInReplyToUid() {
        return this.inReplyToUid;
    }

    public void setInReplyToUid(String str) {
        this.inReplyToUid = str;
    }

    public long getOtherPartyUserId() {
        return this.otherPartyUserId;
    }

    public void setOtherPartyUserId(long j) {
        this.otherPartyUserId = j;
    }

    public String getOtherPartyUid() {
        return this.otherPartyUid;
    }

    public void setOtherPartyUid(String str) {
        this.otherPartyUid = str;
    }

    public String getOtherPartyUsername() {
        return this.otherPartyUsername;
    }

    public void setOtherPartyUsername(String str) {
        this.otherPartyUsername = str;
    }

    public String getOtherPartyImageUrl() {
        return this.otherPartyImageUrl;
    }

    public void setOtherPartyImageUrl(String str) {
        this.otherPartyImageUrl = str;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public Date getReadAt() {
        return this.readAt;
    }

    public void setReadAt(Date date) {
        this.readAt = date;
    }

    public Date getSentAt() {
        return this.sentAt;
    }

    public void setSentAt(Date date) {
        this.sentAt = date;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public boolean isDeleted() {
        return is(1);
    }

    public boolean isDeletedFromSentMail() {
        return is(2);
    }

    public boolean isSpam() {
        return is(4);
    }

    public boolean hasBeenRepliedTo() {
        return is(8);
    }

    public boolean hasBeenRead() {
        return is(16);
    }

    public boolean is(int i) {
        return (i & getFlags()) != 0;
    }

    public boolean isReceived() {
        return getFolder() == 1;
    }

    public boolean isSent() {
        return getFolder() == 2;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.folder);
        binaryEncoder.write8ByteInt(getMasterId());
        binaryEncoder.writeString(getUid());
        binaryEncoder.write8ByteInt(this.inReplyToMasterId);
        binaryEncoder.writeString(this.inReplyToUid);
        binaryEncoder.write8ByteInt(this.otherPartyUserId);
        binaryEncoder.writeString(this.otherPartyUid);
        binaryEncoder.writeString(this.otherPartyUsername);
        binaryEncoder.writeString(this.otherPartyImageUrl);
        binaryEncoder.write2ByteInt(this.flags);
        binaryEncoder.writeTimestamp(this.readAt);
        binaryEncoder.writeTimestamp(this.sentAt);
        binaryEncoder.writeString(this.subject);
        binaryEncoder.writeString(this.body);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.folder = binaryDecoder.decode2ByteInt();
        setMasterId(binaryDecoder.decode8ByteInt());
        setUid(binaryDecoder.decodeString());
        this.inReplyToMasterId = binaryDecoder.decode8ByteInt();
        this.inReplyToUid = binaryDecoder.decodeString();
        this.otherPartyUserId = binaryDecoder.decode8ByteInt();
        this.otherPartyUid = binaryDecoder.decodeString();
        this.otherPartyUsername = binaryDecoder.decodeString();
        this.otherPartyImageUrl = binaryDecoder.decodeString();
        this.flags = binaryDecoder.decode2ByteInt();
        this.readAt = binaryDecoder.decodeTimestamp();
        this.sentAt = binaryDecoder.decodeTimestamp();
        this.subject = binaryDecoder.decodeString();
        this.body = binaryDecoder.decodeString();
    }

    public InboxMessage toInboxMessage() {
        return new InboxMessage(this);
    }
}
