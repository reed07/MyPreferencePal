package com.myfitnesspal.feature.settings.event;

import com.myfitnesspal.shared.event.MfpEventBase;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/myfitnesspal/feature/settings/event/FriendDeletedEvent;", "Lcom/myfitnesspal/shared/event/MfpEventBase;", "isDeleted", "", "(Z)V", "()Z", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FriendDeletedEvent.kt */
public final class FriendDeletedEvent extends MfpEventBase {
    private final boolean isDeleted;

    public FriendDeletedEvent(boolean z) {
        this.isDeleted = z;
    }

    public final boolean isDeleted() {
        return this.isDeleted;
    }
}
