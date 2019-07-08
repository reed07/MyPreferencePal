package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant.Relationship;
import com.uacf.core.util.ReturningFunction1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0003*\u00020\u00042\u000e\u0010\u0005\u001a\n \u0002*\u0004\u0018\u0001H\u0003H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "TFriend", "Lcom/myfitnesspal/shared/model/v1/Friend;", "friend", "execute", "(Lcom/myfitnesspal/shared/model/v1/Friend;)Ljava/lang/String;"}, k = 3, mv = {1, 1, 13})
/* compiled from: ThirdPartyFriendCheckRequestPacket.kt */
final class ThirdPartyFriendCheckRequestPacket$writeDataInternal$1<ReturnType, T> implements ReturningFunction1<U, T> {
    public static final ThirdPartyFriendCheckRequestPacket$writeDataInternal$1 INSTANCE = new ThirdPartyFriendCheckRequestPacket$writeDataInternal$1();

    ThirdPartyFriendCheckRequestPacket$writeDataInternal$1() {
    }

    public final String execute(TFriend tfriend) {
        Intrinsics.checkExpressionValueIsNotNull(tfriend, Relationship.FRIEND);
        return tfriend.getId();
    }
}
