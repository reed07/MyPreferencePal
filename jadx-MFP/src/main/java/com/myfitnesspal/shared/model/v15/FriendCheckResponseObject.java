package com.myfitnesspal.shared.model.v15;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.Ln;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendCheckResponseObject implements BinaryApiSerializable {
    public static final BinaryCreator<FriendCheckResponseObject> BINARY_CREATOR = new BinaryCreator<FriendCheckResponseObject>() {
        public FriendCheckResponseObject create(BinaryDecoder binaryDecoder) {
            FriendCheckResponseObject friendCheckResponseObject = new FriendCheckResponseObject();
            friendCheckResponseObject.readData(binaryDecoder);
            return friendCheckResponseObject;
        }
    };
    private Date lastLoginDate;
    @Expose
    private List<AssociatedMfpFriend> matches;
    @Expose
    private List<AssociatedMfpFriend> nonMatches;

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    public void readData(BinaryDecoder binaryDecoder) {
        int decode4ByteInt = (int) binaryDecoder.decode4ByteInt();
        Ln.d("processFriendsCheckResponse: found %s matches", Integer.valueOf(decode4ByteInt));
        this.matches = new ArrayList(decode4ByteInt);
        for (int i = 0; i < decode4ByteInt; i++) {
            String decodeString = binaryDecoder.decodeString();
            String decodeString2 = binaryDecoder.decodeString();
            int decode2ByteInt = binaryDecoder.decode2ByteInt();
            Ln.d("processFriendsCheckResponse:     match %s: id = %s, mfp = %s, common = %s", Integer.valueOf(i), decodeString, decodeString2, Integer.valueOf(decode2ByteInt));
            this.matches.add(new AssociatedMfpFriend(decodeString2, decode2ByteInt, decodeString));
        }
        int decode4ByteInt2 = (int) binaryDecoder.decode4ByteInt();
        this.nonMatches = new ArrayList(decode4ByteInt2);
        for (int i2 = 0; i2 < decode4ByteInt2; i2++) {
            this.nonMatches.add(new AssociatedMfpFriend(null, 0, binaryDecoder.decodeString()));
        }
        this.lastLoginDate = binaryDecoder.decodeDate();
    }

    public List<AssociatedMfpFriend> getMatches() {
        return this.matches;
    }

    public List<AssociatedMfpFriend> getNonMatches() {
        return this.nonMatches;
    }

    public Date getLastLoginDate() {
        return this.lastLoginDate;
    }
}
