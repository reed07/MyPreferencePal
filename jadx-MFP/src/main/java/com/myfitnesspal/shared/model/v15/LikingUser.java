package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class LikingUser extends BaseObject {
    public static final BinaryCreator<LikingUser> BINARY_CREATOR = new BinaryCreator<LikingUser>() {
        public LikingUser create(BinaryDecoder binaryDecoder) {
            LikingUser likingUser = new LikingUser();
            likingUser.readData(binaryDecoder);
            return likingUser;
        }
    };
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(getLocalId());
        binaryEncoder.write8ByteInt(getMasterId());
        binaryEncoder.writeString(this.username);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setLocalId(binaryDecoder.decode8ByteInt());
        setMasterId(binaryDecoder.decode8ByteInt());
        this.username = binaryDecoder.decodeString();
    }
}
