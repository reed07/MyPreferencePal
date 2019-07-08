package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.util.Date;

public final class MessageBuilder extends IndexableBuilder<MessageBuilder> {
    MessageBuilder() {
        super("Message");
    }

    MessageBuilder(String str) {
        super(str);
    }

    public final MessageBuilder setText(@NonNull String str) {
        return (MessageBuilder) put("text", str);
    }

    public final MessageBuilder setDateSent(@NonNull Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateSent", date.getTime());
    }

    public final MessageBuilder setDateReceived(@NonNull Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateReceived", date.getTime());
    }

    public final MessageBuilder setDateRead(@NonNull Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateRead", date.getTime());
    }

    public final MessageBuilder setSender(@NonNull PersonBuilder personBuilder) {
        return (MessageBuilder) put("sender", (S[]) new PersonBuilder[]{personBuilder});
    }

    public final MessageBuilder setRecipient(@NonNull PersonBuilder... personBuilderArr) {
        return (MessageBuilder) put(Extras.RECIPIENT, (S[]) personBuilderArr);
    }

    public final MessageBuilder setMessageAttachment(@NonNull IndexableBuilder<?>... indexableBuilderArr) {
        return (MessageBuilder) put("messageAttachment", (S[]) indexableBuilderArr);
    }

    public final MessageBuilder setIsPartOf(@NonNull ConversationBuilder... conversationBuilderArr) {
        return (MessageBuilder) put("isPartOf", (S[]) conversationBuilderArr);
    }
}
