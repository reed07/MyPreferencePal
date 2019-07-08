package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.model.v15.InboxMessageObject;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.ReturningFunction1;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class InboxMessage extends DatabaseObject implements Parcelable {
    public static Creator<InboxMessage> CREATOR = new Creator<InboxMessage>() {
        public InboxMessage createFromParcel(Parcel parcel) {
            return new InboxMessage(parcel);
        }

        public InboxMessage[] newArray(int i) {
            return new InboxMessage[i];
        }
    };
    public static final int RECEIVED_MESSAGES = 1;
    public static final int SENT_MESSAGES = 0;
    private String body;
    private long inReplyToMessageMasterId;
    private String inReplyToMessageUid;
    private boolean isDeleted;
    private boolean isDeletedFromSentMail;
    private boolean isReadLocally;
    private boolean isReplied;
    private boolean isSentMessage;
    private boolean isSpam;
    private Date readAtDate;
    private Date sentAtDate;
    private String subject;
    private MiniUserInfo userInfo;

    public int describeContents() {
        return 0;
    }

    public InboxMessage() {
    }

    public InboxMessage(InboxMessageObject inboxMessageObject) {
        this.body = inboxMessageObject.getBody();
        this.inReplyToMessageMasterId = inboxMessageObject.getInReplyToMasterId();
        this.inReplyToMessageUid = inboxMessageObject.getInReplyToUid();
        this.readAtDate = inboxMessageObject.getReadAt();
        this.sentAtDate = inboxMessageObject.getSentAt();
        this.subject = inboxMessageObject.getSubject();
        this.localId = inboxMessageObject.getLocalId();
        this.masterDatabaseId = inboxMessageObject.getMasterId();
        this.uid = inboxMessageObject.getUid();
        this.isSpam = inboxMessageObject.isSpam();
        this.isDeleted = isDeleted();
        this.isDeletedFromSentMail = inboxMessageObject.isDeletedFromSentMail();
        this.isReplied = inboxMessageObject.hasBeenRepliedTo();
        this.isSentMessage = inboxMessageObject.isSent();
        MiniUserInfo miniUserInfo = new MiniUserInfo();
        miniUserInfo.setUsername(inboxMessageObject.getOtherPartyUsername());
        miniUserInfo.setImageUrl(inboxMessageObject.getOtherPartyImageUrl());
        miniUserInfo.setMasterDatabaseId(inboxMessageObject.getOtherPartyUserId());
        miniUserInfo.setUid(inboxMessageObject.getOtherPartyUid());
        this.userInfo = miniUserInfo;
    }

    private InboxMessage(Parcel parcel) {
        super(parcel);
        this.isSentMessage = ParcelableUtil.readBoolean(parcel);
        this.inReplyToMessageMasterId = parcel.readLong();
        this.inReplyToMessageUid = parcel.readString();
        this.isDeletedFromSentMail = ParcelableUtil.readBoolean(parcel);
        this.isDeleted = ParcelableUtil.readBoolean(parcel);
        this.isSpam = ParcelableUtil.readBoolean(parcel);
        this.isReplied = ParcelableUtil.readBoolean(parcel);
        this.readAtDate = ParcelableUtil.readDate(parcel);
        this.sentAtDate = ParcelableUtil.readDate(parcel);
        this.userInfo = (MiniUserInfo) parcel.readParcelable(MiniUserInfo.class.getClassLoader());
        this.subject = parcel.readString();
        this.body = parcel.readString();
        this.isReadLocally = ParcelableUtil.readBoolean(parcel);
    }

    public boolean isSentMessage() {
        return this.isSentMessage;
    }

    public long getInReplyToMessageMasterId() {
        return this.inReplyToMessageMasterId;
    }

    public void setInReplyToMessageMasterId(long j) {
        this.inReplyToMessageMasterId = j;
    }

    public String getInReplyToMessageUid() {
        return this.inReplyToMessageUid;
    }

    public void setInReplyToMessageUid(String str) {
        this.inReplyToMessageUid = str;
    }

    public boolean isDeletedFromSentMail() {
        return this.isDeletedFromSentMail;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public boolean isSpam() {
        return this.isSpam;
    }

    public boolean isReplied() {
        return this.isReplied;
    }

    public Date getReadAtDate() {
        return this.readAtDate;
    }

    public Date getSentAtDate() {
        return this.sentAtDate;
    }

    public MiniUserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(MiniUserInfo miniUserInfo) {
        this.userInfo = miniUserInfo;
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

    public boolean hasBeenRead() {
        return this.isReadLocally || this.isSentMessage || this.readAtDate != null;
    }

    public void markAsReadLocally() {
        this.isReadLocally = true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        ParcelableUtil.writeBoolean(parcel, this.isSentMessage);
        parcel.writeLong(this.inReplyToMessageMasterId);
        parcel.writeString(this.inReplyToMessageUid);
        ParcelableUtil.writeBoolean(parcel, this.isDeletedFromSentMail);
        ParcelableUtil.writeBoolean(parcel, this.isDeleted);
        ParcelableUtil.writeBoolean(parcel, this.isSpam);
        ParcelableUtil.writeBoolean(parcel, this.isReplied);
        ParcelableUtil.writeDate(parcel, this.readAtDate);
        ParcelableUtil.writeDate(parcel, this.sentAtDate);
        parcel.writeParcelable(this.userInfo, i);
        parcel.writeString(this.subject);
        parcel.writeString(this.body);
        ParcelableUtil.writeBoolean(parcel, this.isReadLocally);
    }

    public static int getUnreadCount(List<InboxMessage> list) {
        return Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, InboxMessage>() {
            public Boolean execute(InboxMessage inboxMessage) {
                return Boolean.valueOf(!inboxMessage.hasBeenRead());
            }
        }).size();
    }
}
