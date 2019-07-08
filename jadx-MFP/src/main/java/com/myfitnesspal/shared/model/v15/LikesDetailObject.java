package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.ArrayList;
import java.util.List;

public class LikesDetailObject implements BinaryApiSerializable {
    public static final BinaryCreator<LikesDetailObject> BINARY_CREATOR = new BinaryCreator<LikesDetailObject>() {
        public LikesDetailObject create(BinaryDecoder binaryDecoder) {
            LikesDetailObject likesDetailObject = new LikesDetailObject();
            likesDetailObject.readData(binaryDecoder);
            return likesDetailObject;
        }
    };
    private int flags;
    private List<LikingUser> likingUsers;
    private int totalNumberOfLikes;

    public static final class Flags {
        public static final int CURRENT_USER_HAS_LIKED = 2;
        public static final int LIKABLE = 1;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public boolean isLikable() {
        return (this.flags & 1) == 1;
    }

    public boolean currentUserHasLiked() {
        return (this.flags & 2) == 2;
    }

    public List<LikingUser> getLikingUsers() {
        return this.likingUsers;
    }

    public void setLikingUsers(List<LikingUser> list) {
        this.likingUsers = list;
    }

    public int getTotalNumberOfLikes() {
        return this.totalNumberOfLikes;
    }

    public void setTotalNumberOfLikes(int i) {
        this.totalNumberOfLikes = i;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write4ByteInt((long) this.flags);
        binaryEncoder.write4ByteInt((long) this.totalNumberOfLikes);
        binaryEncoder.writeList(this.likingUsers);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.flags = (int) binaryDecoder.decode4ByteInt();
        this.totalNumberOfLikes = (int) binaryDecoder.decode4ByteInt();
        binaryDecoder.decodeList(LikingUser.BINARY_CREATOR);
        this.likingUsers = new ArrayList();
    }
}
