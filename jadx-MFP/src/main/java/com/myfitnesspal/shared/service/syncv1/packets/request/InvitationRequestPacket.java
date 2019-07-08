package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class InvitationRequestPacket extends ApiRequestPacketImpl {
    private String fullName;
    private List<String> invitees;
    private String message;

    public static final class ErrorCodes {
        public static final int INVALID_FULL_NAME = 257;
        public static final int INVITATION_FAILED = 256;
    }

    public InvitationRequestPacket() {
        super(122);
    }

    public String getFullName() {
        return this.fullName;
    }

    public InvitationRequestPacket setFullName(String str) {
        this.fullName = str;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public InvitationRequestPacket setMessage(String str) {
        this.message = str;
        return this;
    }

    public List<String> getInvitees() {
        return this.invitees;
    }

    public InvitationRequestPacket setInvitees(List<String> list) {
        this.invitees = list;
        return this;
    }

    public InvitationRequestPacket addInvitees(List<String> list) {
        if (this.invitees == null) {
            this.invitees = new ArrayList();
        }
        this.invitees.addAll(list);
        return this;
    }

    public InvitationRequestPacket addInvitees(String... strArr) {
        return addInvitees(Arrays.asList(strArr));
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return CollectionUtils.size((Collection<?>) this.invitees) > 0;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeString(this.fullName);
        binaryEncoder.writeString(this.message);
        binaryEncoder.writeStringList(this.invitees);
    }
}
