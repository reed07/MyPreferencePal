package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.util.RichText;
import java.util.Date;

public class StatusCommentObject extends BaseObject {
    public static final BinaryCreator<StatusCommentObject> BINARY_CREATOR = new BinaryCreator<StatusCommentObject>() {
        public StatusCommentObject create(BinaryDecoder binaryDecoder) {
            StatusCommentObject statusCommentObject = new StatusCommentObject();
            statusCommentObject.readData(binaryDecoder);
            return statusCommentObject;
        }
    };
    private RichText commentBody;
    private String commentingUserImageUrl;
    private long commentingUserLocalId;
    private long commentingUserMasterId;
    private String commentingUserUid;
    private String commentingUserUsername;
    private Date createdAt;
    private LikesDetailObject likesDetail;
    private long parentLocalId;
    private long parentMasterId;
    private String parentUid;

    public long getParentLocalId() {
        return this.parentLocalId;
    }

    public void setParentLocalId(long j) {
        this.parentLocalId = j;
    }

    public long getParentMasterId() {
        return this.parentMasterId;
    }

    public void setParentMasterId(long j) {
        this.parentMasterId = j;
    }

    public String getParentUid() {
        return this.parentUid;
    }

    public void setParentUid(String str) {
        this.parentUid = str;
    }

    public long getCommentingUserLocalId() {
        return this.commentingUserLocalId;
    }

    public void setCommentingUserLocalId(long j) {
        this.commentingUserLocalId = j;
    }

    public long getCommentingUserMasterId() {
        return this.commentingUserMasterId;
    }

    public void setCommentingUserMasterId(long j) {
        this.commentingUserMasterId = j;
    }

    public String getCommentingUserUid() {
        return this.commentingUserUid;
    }

    public void setCommentingUserUid(String str) {
        this.commentingUserUid = str;
    }

    public String getCommentingUserUsername() {
        return this.commentingUserUsername;
    }

    public void setCommentingUserUsername(String str) {
        this.commentingUserUsername = str;
    }

    public String getCommentingUserImageUrl() {
        return this.commentingUserImageUrl;
    }

    public void setCommentingUserImageUrl(String str) {
        this.commentingUserImageUrl = str;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public RichText getCommentBody() {
        return this.commentBody;
    }

    public void setCommentBody(RichText richText) {
        this.commentBody = richText;
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
        binaryEncoder.write8ByteInt(this.commentingUserLocalId);
        binaryEncoder.write8ByteInt(this.commentingUserMasterId);
        binaryEncoder.writeString(this.commentingUserUid);
        binaryEncoder.writeString(this.commentingUserUsername);
        binaryEncoder.writeString(this.commentingUserImageUrl);
        binaryEncoder.writeTimestamp(this.createdAt);
        binaryEncoder.writeRichtext(this.commentBody);
        LikesDetailObject likesDetailObject = this.likesDetail;
        if (likesDetailObject == null) {
            likesDetailObject = new LikesDetailObject();
        }
        binaryEncoder.writeObject(likesDetailObject);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setLocalId(binaryDecoder.decode8ByteInt());
        setMasterId(binaryDecoder.decode8ByteInt());
        setUid(binaryDecoder.decodeString());
        this.commentingUserLocalId = binaryDecoder.decode8ByteInt();
        this.commentingUserMasterId = binaryDecoder.decode8ByteInt();
        this.commentingUserUid = binaryDecoder.decodeString();
        this.commentingUserUsername = binaryDecoder.decodeString();
        this.commentingUserImageUrl = binaryDecoder.decodeString();
        this.createdAt = binaryDecoder.decodeTimestamp();
        this.commentBody = binaryDecoder.decodedRichText();
        this.likesDetail = (LikesDetailObject) binaryDecoder.decodeObject(LikesDetailObject.BINARY_CREATOR);
    }
}
