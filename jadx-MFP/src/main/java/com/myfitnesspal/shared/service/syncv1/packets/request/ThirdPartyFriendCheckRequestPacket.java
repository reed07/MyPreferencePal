package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.model.v1.Friend;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/myfitnesspal/shared/service/syncv1/packets/request/ThirdPartyFriendCheckRequestPacket;", "TFriend", "Lcom/myfitnesspal/shared/model/v1/Friend;", "Lcom/myfitnesspal/shared/service/syncv1/packets/request/ApiRequestPacketImpl;", "serviceId", "", "friends", "", "(ILjava/util/List;)V", "validatePacketData", "", "writeDataInternal", "", "encoder", "Lcom/myfitnesspal/shared/service/syncv1/BinaryEncoder;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ThirdPartyFriendCheckRequestPacket.kt */
public final class ThirdPartyFriendCheckRequestPacket<TFriend extends Friend> extends ApiRequestPacketImpl {
    private final List<TFriend> friends;
    private final int serviceId;

    public ThirdPartyFriendCheckRequestPacket(int i, @NotNull List<? extends TFriend> list) {
        Intrinsics.checkParameterIsNotNull(list, "friends");
        super(135);
        this.serviceId = i;
        this.friends = list;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return this.serviceId > 0;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(@NotNull BinaryEncoder binaryEncoder) {
        Intrinsics.checkParameterIsNotNull(binaryEncoder, "encoder");
        binaryEncoder.write2ByteInt(this.serviceId);
        binaryEncoder.writeStringList(Enumerable.select((Collection<T>) this.friends, (ReturningFunction1<U, T>) ThirdPartyFriendCheckRequestPacket$writeDataInternal$1.INSTANCE));
    }
}
