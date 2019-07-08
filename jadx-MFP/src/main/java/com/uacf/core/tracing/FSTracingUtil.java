package com.uacf.core.tracing;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/uacf/core/tracing/FSTracingUtil;", "", "()V", "PERIOD_REGEX", "", "SPAN_NAME_FORMAT", "createSpanNameForCallingMethod", "library_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: FSTracingUtil.kt */
public final class FSTracingUtil {
    public static final FSTracingUtil INSTANCE = new FSTracingUtil();
    private static final String PERIOD_REGEX = PERIOD_REGEX;
    private static final String SPAN_NAME_FORMAT = SPAN_NAME_FORMAT;

    private FSTracingUtil() {
    }

    @JvmStatic
    @NotNull
    public static final String createSpanNameForCallingMethod() {
        List list;
        boolean z;
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        StackTraceElement stackTraceElement = currentThread.getStackTrace()[4];
        Intrinsics.checkExpressionValueIsNotNull(stackTraceElement, "sdkMethodCalled");
        String className = stackTraceElement.getClassName();
        Intrinsics.checkExpressionValueIsNotNull(className, "sdkMethodCalled.className");
        List split = new Regex(PERIOD_REGEX).split(className, 0);
        if (!split.isEmpty()) {
            ListIterator listIterator = split.listIterator(split.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    break;
                }
                if (((String) listIterator.previous()).length() == 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    list = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        list = CollectionsKt.emptyList();
        Collection collection = list;
        if (collection != null) {
            Object[] array = collection.toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.US;
                Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
                Object[] objArr = {strArr[strArr.length - 1], stackTraceElement.getMethodName()};
                String format = String.format(locale, SPAN_NAME_FORMAT, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
                return format;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
    }
}
