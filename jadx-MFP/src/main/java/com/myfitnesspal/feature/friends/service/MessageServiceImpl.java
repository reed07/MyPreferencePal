package com.myfitnesspal.feature.friends.service;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.model.v15.InboxMessageObject;
import com.myfitnesspal.shared.service.syncv1.packets.PacketPayloadListExtractor;
import com.myfitnesspal.shared.service.syncv1.packets.request.AddInboxMessageRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.DeleteItemRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.MarkMessagesReadRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.RetrieveInboxMessagesByIdRequestPacket;
import com.myfitnesspal.shared.service.syncv1.packets.request.RetrieveInboxMessagesRequestPacket;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Tuple;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;

public class MessageServiceImpl implements MessageService {
    private final Provider<MfpActionApi> api;

    @Inject
    public MessageServiceImpl(Provider<MfpActionApi> provider) {
        this.api = provider;
    }

    public void markMessageRead(long j, String str) throws ApiException {
        MarkMessagesReadRequestPacket markMessagesReadRequestPacket = new MarkMessagesReadRequestPacket();
        markMessagesReadRequestPacket.setActionCode(1);
        markMessagesReadRequestPacket.setMessageIds(Collections.singletonList(Tuple.create(Long.valueOf(j), str)));
        ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(markMessagesReadRequestPacket)).post(new Object[0]);
    }

    public List<InboxMessage> fetchMessagesOfType(int i, int i2) throws ApiException {
        return Enumerable.select((Collection<T>) (List) ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(new RetrieveInboxMessagesRequestPacket(i, i2))).post((ReturningFunction1<TTransform, T>) new PacketPayloadListExtractor<TTransform,T>(27), new Object[0]), (ReturningFunction1<U, T>) $$Lambda$MessageServiceImpl$QQ7mJENN2DRBh0QQE59bzSoAs_E.INSTANCE);
    }

    public InboxMessage fetchMessageWithId(long j) throws ApiException {
        RetrieveInboxMessagesByIdRequestPacket retrieveInboxMessagesByIdRequestPacket = new RetrieveInboxMessagesByIdRequestPacket();
        retrieveInboxMessagesByIdRequestPacket.setMessageIds(Collections.singletonList(Tuple.create(Long.valueOf(j), null)));
        InboxMessageObject inboxMessageObject = (InboxMessageObject) Enumerable.firstOrDefault((List) ((MfpActionApi) ((MfpActionApi) this.api.get()).addPacket(retrieveInboxMessagesByIdRequestPacket)).post((ReturningFunction1<TTransform, T>) new PacketPayloadListExtractor<TTransform,T>(27), new Object[0]));
        if (inboxMessageObject == null) {
            return null;
        }
        return inboxMessageObject.toInboxMessage();
    }

    public void sendMessage(InboxMessage inboxMessage) throws ApiException {
        MiniUserInfo userInfo = inboxMessage.getUserInfo();
        MfpActionApi mfpActionApi = (MfpActionApi) this.api.get();
        AddInboxMessageRequestPacket addInboxMessageRequestPacket = new AddInboxMessageRequestPacket(inboxMessage.getInReplyToMessageMasterId(), inboxMessage.getUid(), inboxMessage.getInReplyToMessageUid(), userInfo.getMasterDatabaseId(), userInfo.getUid(), userInfo.getUsername(), inboxMessage.getSubject(), inboxMessage.getBody());
        ((MfpActionApi) mfpActionApi.addPacket(addInboxMessageRequestPacket)).post(new Object[0]);
    }

    public void deleteMessage(long j, String str) throws ApiException {
        MfpActionApi mfpActionApi = (MfpActionApi) this.api.get();
        DeleteItemRequestPacket deleteItemRequestPacket = new DeleteItemRequestPacket(14, j, str, 2);
        ((MfpActionApi) mfpActionApi.addPacket(deleteItemRequestPacket)).post(new Object[0]);
    }
}
