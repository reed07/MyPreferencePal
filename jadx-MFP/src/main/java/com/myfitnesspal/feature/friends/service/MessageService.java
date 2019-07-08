package com.myfitnesspal.feature.friends.service;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import java.util.List;

public interface MessageService {
    void deleteMessage(long j, String str) throws ApiException;

    InboxMessage fetchMessageWithId(long j) throws ApiException;

    List<InboxMessage> fetchMessagesOfType(int i, int i2) throws ApiException;

    void markMessageRead(long j, String str) throws ApiException;

    void sendMessage(InboxMessage inboxMessage) throws ApiException;
}
