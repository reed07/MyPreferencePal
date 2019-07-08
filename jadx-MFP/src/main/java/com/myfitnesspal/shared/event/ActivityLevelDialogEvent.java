package com.myfitnesspal.shared.event;

import com.myfitnesspal.feature.goals.ui.adapter.ActivityItem;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/shared/event/ActivityLevelDialogEvent;", "Lcom/myfitnesspal/shared/event/MfpEventBase;", "item", "Lcom/myfitnesspal/feature/goals/ui/adapter/ActivityItem;", "(Lcom/myfitnesspal/feature/goals/ui/adapter/ActivityItem;)V", "getItem", "()Lcom/myfitnesspal/feature/goals/ui/adapter/ActivityItem;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ActivityLevelDialogEvent.kt */
public final class ActivityLevelDialogEvent extends MfpEventBase {
    @NotNull
    private final ActivityItem item;

    public ActivityLevelDialogEvent(@NotNull ActivityItem activityItem) {
        Intrinsics.checkParameterIsNotNull(activityItem, Attributes.ITEM);
        this.item = activityItem;
    }

    @NotNull
    public final ActivityItem getItem() {
        return this.item;
    }
}
