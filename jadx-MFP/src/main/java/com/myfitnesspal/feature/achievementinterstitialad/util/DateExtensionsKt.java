package com.myfitnesspal.feature.achievementinterstitialad.util;

import java.util.Calendar;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0004"}, d2 = {"daysBetween", "", "Ljava/util/Date;", "date", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
/* compiled from: DateExtensions.kt */
public final class DateExtensionsKt {
    public static final long daysBetween(@NotNull Date date, @NotNull Date date2) {
        Intrinsics.checkParameterIsNotNull(date, "receiver$0");
        Intrinsics.checkParameterIsNotNull(date2, "date");
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "Calendar.getInstance()");
        long time = (date.getTime() - date2.getTime()) + ((long) (instance.getTimeZone().inDaylightTime(date) ? 3600000 : 0));
        if (time == 0) {
            return 0;
        }
        return time / ((long) 86400000);
    }
}
