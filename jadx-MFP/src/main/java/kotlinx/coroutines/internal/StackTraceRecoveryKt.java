package kotlinx.coroutines.internal;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a9\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u0002H\u00052\u0006\u0010\b\u001a\u0002H\u00052\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u0001j\u0002`\u000b0\nH\u0002¢\u0006\u0002\u0010\f\u001a\u001e\u0010\r\u001a\f\u0012\b\u0012\u00060\u0001j\u0002`\u000b0\n2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010H\u0002\u001a1\u0010\u0011\u001a\u00020\u00122\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0001j\u0002`\u000b0\u00142\u0010\u0010\b\u001a\f\u0012\b\u0012\u00060\u0001j\u0002`\u000b0\nH\u0002¢\u0006\u0002\u0010\u0015\u001a\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0006HHø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a+\u0010\u001a\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u0002H\u00052\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010H\u0002¢\u0006\u0002\u0010\u001b\u001a\u001f\u0010\u001c\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u0002H\u0005H\u0000¢\u0006\u0002\u0010\u001d\u001a+\u0010\u001c\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u0002H\u00052\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0000¢\u0006\u0002\u0010\u001f\u001a\u001f\u0010 \u001a\u00020!\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u0002H\u0005H\u0002¢\u0006\u0002\u0010\"\u001a\u0018\u0010#\u001a\u00060\u0001j\u0002`\u000b2\n\u0010$\u001a\u00060\u0001j\u0002`\u000bH\u0007\u001a\u001f\u0010%\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0018\u001a\u0002H\u0005H\u0000¢\u0006\u0002\u0010\u001d\u001a1\u0010&\u001a\u0018\u0012\u0004\u0012\u0002H\u0005\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0001j\u0002`\u000b0\u00140'\"\b\b\u0000\u0010\u0005*\u00020\u0006*\u0002H\u0005H\u0002¢\u0006\u0002\u0010(\u001a\u001c\u0010)\u001a\u00020!*\u00060\u0001j\u0002`\u000b2\n\u0010*\u001a\u00060\u0001j\u0002`\u000bH\u0002\u001a#\u0010+\u001a\u00020,*\f\u0012\b\u0012\u00060\u0001j\u0002`\u000b0\u00142\u0006\u0010-\u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010.\u001a\u0010\u0010/\u001a\u00020!*\u00060\u0001j\u0002`\u000bH\u0000\u001a\u001b\u00100\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u0006*\u0002H\u0005H\u0002¢\u0006\u0002\u0010\u001d*\f\b\u0000\u00101\"\u00020\u000f2\u00020\u000f*\f\b\u0000\u00102\"\u00020\u00012\u00020\u0001\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"artificialFrame", "Ljava/lang/StackTraceElement;", "message", "", "createFinalException", "E", "", "cause", "result", "resultStackTrace", "Ljava/util/ArrayDeque;", "Lkotlinx/coroutines/internal/StackTraceElement;", "(Ljava/lang/Throwable;Ljava/lang/Throwable;Ljava/util/ArrayDeque;)Ljava/lang/Throwable;", "createStackTrace", "continuation", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "mergeRecoveredTraces", "", "recoveredStacktrace", "", "([Ljava/lang/StackTraceElement;Ljava/util/ArrayDeque;)V", "recoverAndThrow", "", "exception", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverFromStackFrame", "(Ljava/lang/Throwable;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Ljava/lang/Throwable;", "recoverStackTrace", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Throwable;", "recoveryDisabled", "", "(Ljava/lang/Throwable;)Z", "sanitize", "element", "unwrap", "causeAndStacktrace", "Lkotlin/Pair;", "(Ljava/lang/Throwable;)Lkotlin/Pair;", "elementWiseEquals", "e", "frameIndex", "", "methodName", "([Ljava/lang/StackTraceElement;Ljava/lang/String;)I", "isArtificial", "sanitizeStackTrace", "CoroutineStackFrame", "StackTraceElement", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: StackTraceRecovery.kt */
public final class StackTraceRecoveryKt {
    @NotNull
    public static final <E extends Throwable> E recoverStackTrace(@NotNull E e) {
        Intrinsics.checkParameterIsNotNull(e, "exception");
        if (recoveryDisabled(e)) {
            return e;
        }
        Throwable tryCopyException = ExceptionsConstuctorKt.tryCopyException(e);
        return tryCopyException != null ? sanitizeStackTrace(tryCopyException) : e;
    }

    private static final <E extends Throwable> E sanitizeStackTrace(@NotNull E e) {
        StackTraceElement stackTraceElement;
        StackTraceElement[] stackTrace = e.getStackTrace();
        int length = stackTrace.length;
        Intrinsics.checkExpressionValueIsNotNull(stackTrace, "stackTrace");
        int frameIndex = frameIndex(stackTrace, "kotlinx.coroutines.internal.StackTraceRecoveryKt");
        int i = frameIndex + 1;
        int frameIndex2 = frameIndex(stackTrace, "kotlin.coroutines.jvm.internal.BaseContinuationImpl");
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[((length - frameIndex) - (frameIndex2 == -1 ? 0 : length - frameIndex2))];
        int length2 = stackTraceElementArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            if (i2 == 0) {
                stackTraceElement = artificialFrame("Coroutine boundary");
            } else {
                stackTraceElement = stackTrace[(i + i2) - 1];
            }
            stackTraceElementArr[i2] = stackTraceElement;
        }
        e.setStackTrace(stackTraceElementArr);
        return e;
    }

    @NotNull
    public static final <E extends Throwable> E recoverStackTrace(@NotNull E e, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(e, "exception");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return (recoveryDisabled(e) || !(continuation instanceof CoroutineStackFrame)) ? e : recoverFromStackFrame(e, (CoroutineStackFrame) continuation);
    }

    /* access modifiers changed from: private */
    public static final <E extends Throwable> E recoverFromStackFrame(E e, CoroutineStackFrame coroutineStackFrame) {
        Pair causeAndStacktrace = causeAndStacktrace(e);
        E e2 = (Throwable) causeAndStacktrace.component1();
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) causeAndStacktrace.component2();
        Throwable tryCopyException = ExceptionsConstuctorKt.tryCopyException(e2);
        if (tryCopyException == null) {
            return e;
        }
        ArrayDeque createStackTrace = createStackTrace(coroutineStackFrame);
        if (createStackTrace.isEmpty()) {
            return e;
        }
        if (e2 != e) {
            mergeRecoveredTraces(stackTraceElementArr, createStackTrace);
        }
        return createFinalException(e2, tryCopyException, createStackTrace);
    }

    private static final <E extends Throwable> E createFinalException(E e, E e2, ArrayDeque<StackTraceElement> arrayDeque) {
        arrayDeque.addFirst(artificialFrame("Coroutine boundary"));
        StackTraceElement[] stackTrace = e.getStackTrace();
        Intrinsics.checkExpressionValueIsNotNull(stackTrace, "causeTrace");
        int frameIndex = frameIndex(stackTrace, "kotlin.coroutines.jvm.internal.BaseContinuationImpl");
        int i = 0;
        if (frameIndex == -1) {
            Collection collection = arrayDeque;
            if (collection != null) {
                Object[] array = collection.toArray(new StackTraceElement[0]);
                if (array != null) {
                    e2.setStackTrace((StackTraceElement[]) array);
                    return e2;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[(arrayDeque.size() + frameIndex)];
        for (int i2 = 0; i2 < frameIndex; i2++) {
            stackTraceElementArr[i2] = stackTrace[i2];
        }
        for (StackTraceElement stackTraceElement : arrayDeque) {
            stackTraceElementArr[frameIndex + i] = stackTraceElement;
            i++;
        }
        e2.setStackTrace(stackTraceElementArr);
        return e2;
    }

    private static final <E extends Throwable> Pair<E, StackTraceElement[]> causeAndStacktrace(@NotNull E e) {
        boolean z;
        Throwable cause = e.getCause();
        if (cause == null || !Intrinsics.areEqual((Object) cause.getClass(), (Object) e.getClass())) {
            return TuplesKt.to(e, new StackTraceElement[0]);
        }
        StackTraceElement[] stackTrace = e.getStackTrace();
        Intrinsics.checkExpressionValueIsNotNull(stackTrace, "currentTrace");
        int length = stackTrace.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            Intrinsics.checkExpressionValueIsNotNull(stackTraceElement, "it");
            if (isArtificial(stackTraceElement)) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return TuplesKt.to(cause, stackTrace);
        }
        return TuplesKt.to(e, new StackTraceElement[0]);
    }

    @NotNull
    public static final <E extends Throwable> E unwrap(@NotNull E e) {
        Intrinsics.checkParameterIsNotNull(e, "exception");
        if (recoveryDisabled(e)) {
            return e;
        }
        Throwable cause = e.getCause();
        if (cause != null) {
            boolean z = true;
            if (!(!Intrinsics.areEqual((Object) cause.getClass(), (Object) e.getClass()))) {
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "exception.stackTrace");
                int length = stackTrace.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    Intrinsics.checkExpressionValueIsNotNull(stackTraceElement, "it");
                    if (isArtificial(stackTraceElement)) {
                        break;
                    }
                    i++;
                }
                if (!z) {
                    return e;
                }
                E cause2 = e.getCause();
                if (!(cause2 instanceof Throwable)) {
                    cause2 = null;
                }
                if (cause2 != null) {
                    e = cause2;
                }
                return e;
            }
        }
        return e;
    }

    /* access modifiers changed from: private */
    public static final <E extends Throwable> boolean recoveryDisabled(E e) {
        return !DebugKt.RECOVER_STACKTRACES || !DebugKt.DEBUG || (e instanceof CancellationException) || (e instanceof NonRecoverableThrowable);
    }

    private static final ArrayDeque<StackTraceElement> createStackTrace(CoroutineStackFrame coroutineStackFrame) {
        ArrayDeque<StackTraceElement> arrayDeque = new ArrayDeque<>();
        StackTraceElement stackTraceElement = coroutineStackFrame.getStackTraceElement();
        if (stackTraceElement != null) {
            arrayDeque.add(sanitize(stackTraceElement));
        }
        while (true) {
            if (!(coroutineStackFrame instanceof CoroutineStackFrame)) {
                coroutineStackFrame = null;
            }
            if (coroutineStackFrame == null) {
                break;
            }
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                break;
            }
            StackTraceElement stackTraceElement2 = coroutineStackFrame.getStackTraceElement();
            if (stackTraceElement2 != null) {
                arrayDeque.add(sanitize(stackTraceElement2));
            }
        }
        return arrayDeque;
    }

    @NotNull
    @InternalCoroutinesApi
    public static final StackTraceElement sanitize(@NotNull StackTraceElement stackTraceElement) {
        Intrinsics.checkParameterIsNotNull(stackTraceElement, "element");
        String className = stackTraceElement.getClassName();
        Intrinsics.checkExpressionValueIsNotNull(className, "element.className");
        if (!StringsKt.contains$default((CharSequence) className, '/', false, 2, (Object) null)) {
            return stackTraceElement;
        }
        String className2 = stackTraceElement.getClassName();
        Intrinsics.checkExpressionValueIsNotNull(className2, "element.className");
        return new StackTraceElement(StringsKt.replace$default(className2, '/', '.', false, 4, (Object) null), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
    }

    @NotNull
    @InternalCoroutinesApi
    public static final StackTraceElement artificialFrame(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        StringBuilder sb = new StringBuilder();
        sb.append("\b\b\b(");
        sb.append(str);
        return new StackTraceElement(sb.toString(), "\b", "\b", -1);
    }

    public static final boolean isArtificial(@NotNull StackTraceElement stackTraceElement) {
        Intrinsics.checkParameterIsNotNull(stackTraceElement, "receiver$0");
        String className = stackTraceElement.getClassName();
        Intrinsics.checkExpressionValueIsNotNull(className, "className");
        return StringsKt.startsWith$default(className, "\b\b\b", false, 2, null);
    }

    private static final boolean elementWiseEquals(@NotNull StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
        return stackTraceElement.getLineNumber() == stackTraceElement2.getLineNumber() && Intrinsics.areEqual((Object) stackTraceElement.getMethodName(), (Object) stackTraceElement2.getMethodName()) && Intrinsics.areEqual((Object) stackTraceElement.getFileName(), (Object) stackTraceElement2.getFileName()) && Intrinsics.areEqual((Object) stackTraceElement.getClassName(), (Object) stackTraceElement2.getClassName());
    }

    private static final void mergeRecoveredTraces(StackTraceElement[] stackTraceElementArr, ArrayDeque<StackTraceElement> arrayDeque) {
        int length = stackTraceElementArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (isArtificial(stackTraceElementArr[i])) {
                break;
            } else {
                i++;
            }
        }
        int i2 = i + 1;
        int length2 = stackTraceElementArr.length - 1;
        if (length2 >= i2) {
            while (true) {
                StackTraceElement stackTraceElement = stackTraceElementArr[length2];
                Object last = arrayDeque.getLast();
                Intrinsics.checkExpressionValueIsNotNull(last, "result.last");
                if (elementWiseEquals(stackTraceElement, (StackTraceElement) last)) {
                    arrayDeque.removeLast();
                }
                arrayDeque.addFirst(stackTraceElementArr[length2]);
                if (length2 != i2) {
                    length2--;
                } else {
                    return;
                }
            }
        }
    }

    private static final int frameIndex(@NotNull StackTraceElement[] stackTraceElementArr, String str) {
        int length = stackTraceElementArr.length;
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual((Object) str, (Object) stackTraceElementArr[i].getClassName())) {
                return i;
            }
        }
        return -1;
    }
}
