package com.myfitnesspal.feature.search.event;

import com.myfitnesspal.shared.event.MfpEventBase;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/myfitnesspal/feature/search/event/MultiAddModeToggledEvent;", "Lcom/myfitnesspal/shared/event/MfpEventBase;", "isModeOn", "", "(Z)V", "()Z", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: MultiAddModeToggledEvent.kt */
public final class MultiAddModeToggledEvent extends MfpEventBase {
    private final boolean isModeOn;

    public MultiAddModeToggledEvent(boolean z) {
        this.isModeOn = z;
    }

    public final boolean isModeOn() {
        return this.isModeOn;
    }
}
