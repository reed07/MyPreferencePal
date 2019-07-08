package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.uacf.core.util.Strings;

public class UnfriendUserRequestPacket extends ApiRequestPacketImpl {
    private String userIdOrUsername;
    private final String userUid;

    public UnfriendUserRequestPacket(long j, String str) {
        super(PacketTypes.UnfriendUser);
        setUserIdOrUsername(Strings.toString(Long.valueOf(j)));
        this.userUid = str;
    }

    public UnfriendUserRequestPacket(String str, String str2) {
        super(PacketTypes.UnfriendUser);
        if (!Strings.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append("username:");
            sb.append(Strings.toString(str));
            setUserIdOrUsername(sb.toString());
            this.userUid = str2;
            return;
        }
        throw new IllegalArgumentException("userName must not be empty");
    }

    public String getUserIdOrUsername() {
        return this.userIdOrUsername;
    }

    public void setUserIdOrUsername(String str) {
        this.userIdOrUsername = str;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return Strings.notEmpty(this.userIdOrUsername) || Strings.notEmpty(this.userUid);
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.userIdOrUsername);
        binaryEncoder.writeString(this.userUid);
    }
}
