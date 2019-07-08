package com.myfitnesspal.shared.model.v15;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.util.RichText;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class StatusUpdateObject extends BaseObject {
    public static final BinaryCreator<StatusUpdateObject> BINARY_CREATOR = new BinaryCreator<StatusUpdateObject>() {
        public StatusUpdateObject create(BinaryDecoder binaryDecoder) {
            StatusUpdateObject statusUpdateObject = new StatusUpdateObject();
            statusUpdateObject.readData(binaryDecoder);
            return statusUpdateObject;
        }
    };
    @Expose
    private List<StatusCommentObject> comments;
    @Expose
    private Date createdAt;
    @Expose
    private String creatingUserImageUrl;
    @Expose
    private long creatingUserLocalId;
    @Expose
    private long creatingUserMasterId;
    @Expose
    private String creatingUserUid;
    @Expose
    private String creatingUserUsername;
    @Expose
    private int flags;
    @Expose
    private boolean isParticipatingInCommentThread;
    @Expose
    private String itemClass;
    @Expose
    private LikesDetailObject likesDetail;
    @Expose
    private RichText messageBody;
    @Expose
    private String partnerAppId;
    @Expose
    private String partnerAppName;
    @Expose
    private int priority;
    @Expose
    private long referencedItemId;
    @Inject
    Lazy<Session> session;
    @Expose
    private long targetUserLocalId;
    @Expose
    private long targetUserMasterId;
    @Expose
    private String targetUserUid;
    @Expose
    private Date updatedAt;

    public static final class Flags {
        public static final int COMMENTABLE = 1;
        public static final int HTML = 2;
    }

    public static class ItemClass {
        public static final String CONSECUTIVE_LOGINS = "ConsecutiveLogins";
        public static final String DIARY = "Diary";
        public static final String EXERCISE = "Exercise";
        public static final String FRIEND = "Friend";
        public static final String MEASUREMENT = "Measurement";
        public static final String STATUS = "Status";
    }

    public StatusUpdateObject() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public Session getSession() {
        return (Session) this.session.get();
    }

    public boolean isParticipatingInCommentThread() {
        return this.isParticipatingInCommentThread;
    }

    public long getCreatingUserLocalId() {
        return this.creatingUserLocalId;
    }

    public void setCreatingUserLocalId(long j) {
        this.creatingUserLocalId = j;
    }

    public long getCreatingUserMasterId() {
        return this.creatingUserMasterId;
    }

    public void setCreatingUserMasterId(long j) {
        this.creatingUserMasterId = j;
    }

    public String getCreatingUserUid() {
        return this.creatingUserUid;
    }

    public void setCreatingUserUid(String str) {
        this.creatingUserUid = str;
    }

    public long getTargetUserLocalId() {
        return this.targetUserLocalId;
    }

    public void setTargetUserLocalId(long j) {
        this.targetUserLocalId = j;
    }

    public long getTargetUserMasterId() {
        return this.targetUserMasterId;
    }

    public void setTargetUserMasterId(long j) {
        this.targetUserMasterId = j;
    }

    public String getTargetUserUid() {
        return this.targetUserUid;
    }

    public void setTargetUserUid(String str) {
        this.targetUserUid = str;
    }

    public String getCreatingUserUsername() {
        return this.creatingUserUsername;
    }

    public void setCreatingUserUsername(String str) {
        this.creatingUserUsername = str;
    }

    public String getCreatingUserImageUrl() {
        return this.creatingUserImageUrl;
    }

    public void setCreatingUserImageUrl(String str) {
        this.creatingUserImageUrl = str;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date date) {
        this.updatedAt = date;
    }

    public RichText getMessageBody() {
        return this.messageBody;
    }

    public void setMessageBody(RichText richText) {
        this.messageBody = richText;
    }

    public String getItemClass() {
        return this.itemClass;
    }

    public void setItemClass(String str) {
        this.itemClass = str;
    }

    public long getReferencedItemId() {
        return this.referencedItemId;
    }

    public void setReferencedItemId(long j) {
        this.referencedItemId = j;
    }

    public String getPartnerAppId() {
        return this.partnerAppId;
    }

    public void setPartnerAppId(String str) {
        this.partnerAppId = str;
    }

    public String getPartnerAppName() {
        return this.partnerAppName;
    }

    public void setPartnerAppName(String str) {
        this.partnerAppName = str;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public int getFlags() {
        return this.flags;
    }

    public boolean isCommentable() {
        return (getFlags() & 1) == 1;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public List<StatusCommentObject> getComments() {
        return this.comments;
    }

    public void addComment(StatusCommentObject statusCommentObject) {
        if (this.comments == null) {
            this.comments = new ArrayList();
        }
        this.comments.add(statusCommentObject);
    }

    public LikesDetailObject getLikesDetail() {
        return this.likesDetail;
    }

    public void setLikesDetail(LikesDetailObject likesDetailObject) {
        this.likesDetail = likesDetailObject;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(getLocalId());
        binaryEncoder.write8ByteInt(getMasterId());
        binaryEncoder.writeString(getUid());
        binaryEncoder.write8ByteInt(this.creatingUserLocalId);
        binaryEncoder.write8ByteInt(this.creatingUserMasterId);
        binaryEncoder.writeString(this.creatingUserUid);
        binaryEncoder.write8ByteInt(this.targetUserLocalId);
        binaryEncoder.write8ByteInt(this.targetUserMasterId);
        binaryEncoder.writeString(this.targetUserUid);
        binaryEncoder.writeString(this.creatingUserUsername);
        binaryEncoder.writeString(this.creatingUserImageUrl);
        binaryEncoder.writeTimestamp(this.createdAt);
        binaryEncoder.writeTimestamp(this.updatedAt);
        binaryEncoder.writeRichtext(this.messageBody);
        binaryEncoder.writeString(this.itemClass);
        binaryEncoder.write8ByteInt(this.referencedItemId);
        binaryEncoder.writeString(this.partnerAppId);
        binaryEncoder.writeString(this.partnerAppName);
        binaryEncoder.write2ByteInt(this.priority);
        binaryEncoder.write4ByteInt((long) this.flags);
        LikesDetailObject likesDetailObject = this.likesDetail;
        if (likesDetailObject == null) {
            likesDetailObject = new LikesDetailObject();
        }
        binaryEncoder.writeObject(likesDetailObject);
        binaryEncoder.writeList(this.comments);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setLocalId(binaryDecoder.decode8ByteInt());
        setMasterId(binaryDecoder.decode8ByteInt());
        setUid(binaryDecoder.decodeString());
        this.creatingUserLocalId = binaryDecoder.decode8ByteInt();
        this.creatingUserMasterId = binaryDecoder.decode8ByteInt();
        this.creatingUserUid = binaryDecoder.decodeString();
        this.targetUserLocalId = binaryDecoder.decode8ByteInt();
        this.targetUserMasterId = binaryDecoder.decode8ByteInt();
        this.targetUserUid = binaryDecoder.decodeString();
        this.creatingUserUsername = binaryDecoder.decodeString();
        this.creatingUserImageUrl = binaryDecoder.decodeString();
        this.createdAt = binaryDecoder.decodeTimestamp();
        this.updatedAt = binaryDecoder.decodeTimestamp();
        this.messageBody = binaryDecoder.decodedRichText();
        this.itemClass = binaryDecoder.decodeString();
        this.referencedItemId = binaryDecoder.decode8ByteInt();
        this.partnerAppId = binaryDecoder.decodeString();
        this.partnerAppName = binaryDecoder.decodeString();
        this.priority = binaryDecoder.decode2ByteInt();
        this.flags = (int) binaryDecoder.decode4ByteInt();
        this.likesDetail = (LikesDetailObject) binaryDecoder.decodeObject(LikesDetailObject.BINARY_CREATOR);
        this.comments = binaryDecoder.decodeList(StatusCommentObject.BINARY_CREATOR);
        for (StatusCommentObject statusCommentObject : this.comments) {
            statusCommentObject.setParentLocalId(getLocalId());
            statusCommentObject.setParentMasterId(getMasterId());
            if (getSession() != null && statusCommentObject.getCommentingUserMasterId() > 0 && statusCommentObject.getCommentingUserMasterId() == getSession().getUser().getMasterDatabaseId()) {
                this.isParticipatingInCommentThread = true;
                return;
            }
        }
    }
}
