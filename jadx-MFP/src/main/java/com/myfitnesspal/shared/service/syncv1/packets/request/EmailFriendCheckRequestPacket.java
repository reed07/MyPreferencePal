package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.model.v1.EmailFriend;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import java.util.Collection;
import java.util.List;

public class EmailFriendCheckRequestPacket extends ApiRequestPacketImpl {
    private final List<EmailFriend> friends;

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return true;
    }

    public EmailFriendCheckRequestPacket(List<EmailFriend> list) {
        super(138);
        this.friends = list;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeStringList(Enumerable.selectMany(this.friends, new ReturningFunction1<Collection<String>, EmailFriend>() {
            public Collection<String> execute(EmailFriend emailFriend) throws RuntimeException {
                if (emailFriend.hasEmailAddresses()) {
                    return emailFriend.getEmailAddresses();
                }
                return null;
            }
        }));
    }
}
