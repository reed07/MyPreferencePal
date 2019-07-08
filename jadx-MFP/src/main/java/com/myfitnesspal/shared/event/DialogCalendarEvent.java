package com.myfitnesspal.shared.event;

import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/shared/event/DialogCalendarEvent;", "Lcom/myfitnesspal/shared/event/MfpEventBase;", "calendar", "Ljava/util/Calendar;", "(Ljava/util/Calendar;)V", "getCalendar", "()Ljava/util/Calendar;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: DialogCalendarEvent.kt */
public final class DialogCalendarEvent extends MfpEventBase {
    @NotNull
    private final Calendar calendar;

    public DialogCalendarEvent(@NotNull Calendar calendar2) {
        Intrinsics.checkParameterIsNotNull(calendar2, "calendar");
        this.calendar = calendar2;
    }

    @NotNull
    public final Calendar getCalendar() {
        return this.calendar;
    }
}
