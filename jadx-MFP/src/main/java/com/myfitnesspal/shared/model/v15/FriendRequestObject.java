package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.Date;

public class FriendRequestObject implements BinaryApiSerializable {
    public static final BinaryCreator<FriendRequestObject> BINARY_CREATOR = new BinaryCreator<FriendRequestObject>() {
        public FriendRequestObject create(BinaryDecoder binaryDecoder) {
            FriendRequestObject friendRequestObject = new FriendRequestObject();
            friendRequestObject.readData(binaryDecoder);
            return friendRequestObject;
        }
    };
    private String body;
    private int folder;
    private String otherPartyFullName;
    private String otherPartyImageUrl;
    private long otherPartyMasterId;
    private String otherPartyUid;
    private String otherPartyUsername;
    private long requestMasterId;
    private String requestUid;
    private int statusCode;
    private Date timestamp;

    public static final class FolderTypes {
        public static final int RECEIVED = 1;
        public static final int SENT = 2;
    }

    public static final class StatusCodes {
        public static final int ACCEPTED = 2;
        public static final int CANCELED = 3;
        public static final int NORMAL = 0;
        public static final int PROCESSING = 5;
        public static final int REJECTED = 1;
        public static final int SPAM = 4;
    }

    public long getRequestMasterId() {
        return this.requestMasterId;
    }

    public void setRequestMasterId(long j) {
        this.requestMasterId = j;
    }

    public String getRequestUid() {
        return this.requestUid;
    }

    public void setRequestUid(String str) {
        this.requestUid = str;
    }

    public int getFolder() {
        return this.folder;
    }

    public void setFolder(int i) {
        this.folder = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public long getOtherPartyMasterId() {
        return this.otherPartyMasterId;
    }

    public void setOtherPartyMasterId(long j) {
        this.otherPartyMasterId = j;
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

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public String getOtherPartyFullName() {
        return this.otherPartyFullName;
    }

    public void setOtherPartyFullName(String str) {
        this.otherPartyFullName = str;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.folder);
        binaryEncoder.write2ByteInt(this.statusCode);
        binaryEncoder.write8ByteInt(this.requestMasterId);
        binaryEncoder.writeString(this.requestUid);
        binaryEncoder.write8ByteInt(this.otherPartyMasterId);
        binaryEncoder.writeString(this.otherPartyUid);
        binaryEncoder.writeString(this.otherPartyUsername);
        binaryEncoder.writeString(this.otherPartyFullName);
        binaryEncoder.writeString(this.otherPartyImageUrl);
        binaryEncoder.writeTimestamp(this.timestamp);
        binaryEncoder.writeString(this.body);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.folder = binaryDecoder.decode2ByteInt();
        this.statusCode = binaryDecoder.decode2ByteInt();
        this.requestMasterId = binaryDecoder.decode8ByteInt();
        this.requestUid = binaryDecoder.decodeString();
        this.otherPartyMasterId = binaryDecoder.decode8ByteInt();
        this.otherPartyUid = binaryDecoder.decodeString();
        this.otherPartyUsername = binaryDecoder.decodeString();
        this.otherPartyFullName = "Joe User";
        this.otherPartyFullName = binaryDecoder.decodeString();
        this.otherPartyImageUrl = binaryDecoder.decodeString();
        this.timestamp = binaryDecoder.decodeTimestamp();
        this.body = binaryDecoder.decodeString();
    }
}
