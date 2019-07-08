package com.myfitnesspal.feature.friends.service;

import com.myfitnesspal.shared.model.v15.InboxMessageObject;
import com.uacf.core.util.ReturningFunction1;

/* renamed from: com.myfitnesspal.feature.friends.service.-$$Lambda$MessageServiceImpl$QQ7mJENN2DRBh0QQE59bzSoAs_E reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MessageServiceImpl$QQ7mJENN2DRBh0QQE59bzSoAs_E implements ReturningFunction1 {
    public static final /* synthetic */ $$Lambda$MessageServiceImpl$QQ7mJENN2DRBh0QQE59bzSoAs_E INSTANCE = new $$Lambda$MessageServiceImpl$QQ7mJENN2DRBh0QQE59bzSoAs_E();

    private /* synthetic */ $$Lambda$MessageServiceImpl$QQ7mJENN2DRBh0QQE59bzSoAs_E() {
    }

    public final Object execute(Object obj) {
        return ((InboxMessageObject) obj).toInboxMessage();
    }
}
