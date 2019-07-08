package com.myfitnesspal.feature.settings.event;

import com.myfitnesspal.shared.event.MfpEventBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/settings/event/FrequencyEvent;", "Lcom/myfitnesspal/shared/event/MfpEventBase;", "frequency", "", "(Ljava/lang/String;)V", "getFrequency", "()Ljava/lang/String;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FrequencyEvent.kt */
public final class FrequencyEvent extends MfpEventBase {
    @NotNull
    private final String frequency;

    public FrequencyEvent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "frequency");
        this.frequency = str;
    }

    @NotNull
    public final String getFrequency() {
        return this.frequency;
    }
}
