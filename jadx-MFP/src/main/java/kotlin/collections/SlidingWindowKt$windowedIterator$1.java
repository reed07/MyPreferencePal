package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlin/collections/SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4}, l = {33, 39, 46, 52, 55}, m = "invokeSuspend", n = {"gap", "buffer", "skip", "e", "gap", "buffer", "skip", "gap", "buffer", "e", "gap", "buffer", "gap", "buffer"}, s = {"I$0", "L$1", "I$1", "L$2", "I$0", "L$0", "I$1", "I$0", "L$1", "L$2", "I$0", "L$1", "I$0", "L$0"})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private SequenceScope p$;

    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
        this.$step = i;
        this.$size = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$step, this.$size, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.p$ = (SequenceScope) obj;
        return slidingWindowKt$windowedIterator$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SlidingWindowKt$windowedIterator$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a6, code lost:
        if (r1.hasNext() == false) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a8, code lost:
        r7 = r1.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ac, code lost:
        if (r12 <= 0) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ae, code lost:
        r12 = r12 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b1, code lost:
        r3.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ba, code lost:
        if (r3.size() != r0.$size) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00bc, code lost:
        r0.L$0 = r5;
        r0.I$0 = r4;
        r0.L$1 = r3;
        r0.I$1 = r12;
        r0.L$2 = r7;
        r0.L$3 = r1;
        r0.label = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ce, code lost:
        if (r5.yield(r3, r0) != r6) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d0, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d3, code lost:
        if (r0.$reuseBuffer == false) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d5, code lost:
        r3.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d9, code lost:
        r3 = new java.util.ArrayList(r0.$size);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e1, code lost:
        r12 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00eb, code lost:
        if ((!r3.isEmpty()) == false) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ef, code lost:
        if (r0.$partialWindows != false) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f7, code lost:
        if (r3.size() != r0.$size) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f9, code lost:
        r0.I$0 = r4;
        r0.L$0 = r3;
        r0.I$1 = r12;
        r0.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0106, code lost:
        if (r5.yield(r3, r0) != r6) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0108, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x011a, code lost:
        if (r1.hasNext() == false) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x011c, code lost:
        r6 = r1.next();
        r3.add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0127, code lost:
        if (r3.isFull() == false) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012b, code lost:
        if (r12.$reuseBuffer == false) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012d, code lost:
        r7 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0131, code lost:
        r7 = new java.util.ArrayList(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x013b, code lost:
        r12.L$0 = r5;
        r12.I$0 = r4;
        r12.L$1 = r3;
        r12.L$2 = r6;
        r12.L$3 = r1;
        r12.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x014c, code lost:
        if (r5.yield(r7, r12) != r0) goto L_0x014f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x014e, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x014f, code lost:
        r3.removeFirst(r12.$step);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0157, code lost:
        if (r12.$partialWindows == false) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0159, code lost:
        r1 = r3;
        r3 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0162, code lost:
        if (r1.size() <= r12.$step) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0166, code lost:
        if (r12.$reuseBuffer == false) goto L_0x016c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0168, code lost:
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x016c, code lost:
        r5 = new java.util.ArrayList(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0176, code lost:
        r12.L$0 = r4;
        r12.I$0 = r3;
        r12.L$1 = r1;
        r12.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0183, code lost:
        if (r4.yield(r5, r12) != r0) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0185, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0186, code lost:
        r1.removeFirst(r12.$step);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0194, code lost:
        if ((true ^ r1.isEmpty()) == false) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0196, code lost:
        r12.I$0 = r3;
        r12.L$0 = r1;
        r12.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01a1, code lost:
        if (r4.yield(r1, r12) != r0) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01a3, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01a6, code lost:
        return kotlin.Unit.INSTANCE;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 1
            switch(r1) {
                case 0: goto L_0x0086;
                case 1: goto L_0x0068;
                case 2: goto L_0x0055;
                case 3: goto L_0x0039;
                case 4: goto L_0x0023;
                case 5: goto L_0x0012;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0012:
            java.lang.Object r0 = r11.L$0
            kotlin.collections.RingBuffer r0 = (kotlin.collections.RingBuffer) r0
            int r0 = r11.I$0
            boolean r0 = r12 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L_0x001e
            goto L_0x01a4
        L_0x001e:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r12 = r12.exception
            throw r12
        L_0x0023:
            java.lang.Object r1 = r11.L$1
            kotlin.collections.RingBuffer r1 = (kotlin.collections.RingBuffer) r1
            int r3 = r11.I$0
            java.lang.Object r4 = r11.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            boolean r5 = r12 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L_0x0034
            r12 = r11
            goto L_0x0186
        L_0x0034:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r12 = r12.exception
            throw r12
        L_0x0039:
            java.lang.Object r1 = r11.L$3
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r11.L$2
            java.lang.Object r3 = r11.L$1
            kotlin.collections.RingBuffer r3 = (kotlin.collections.RingBuffer) r3
            int r4 = r11.I$0
            java.lang.Object r5 = r11.L$0
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            boolean r6 = r12 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L_0x0050
            r12 = r11
            goto L_0x014f
        L_0x0050:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r12 = r12.exception
            throw r12
        L_0x0055:
            int r0 = r11.I$1
            java.lang.Object r0 = r11.L$0
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r0 = r11.I$0
            boolean r0 = r12 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L_0x0063
            goto L_0x01a4
        L_0x0063:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r12 = r12.exception
            throw r12
        L_0x0068:
            java.lang.Object r1 = r11.L$3
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r11.L$2
            int r3 = r11.I$1
            java.lang.Object r3 = r11.L$1
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r4 = r11.I$0
            java.lang.Object r5 = r11.L$0
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            boolean r6 = r12 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L_0x0081
            r6 = r0
            r0 = r11
            goto L_0x00d1
        L_0x0081:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r12 = r12.exception
            throw r12
        L_0x0086:
            boolean r1 = r12 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L_0x01a7
            kotlin.sequences.SequenceScope r12 = r11.p$
            int r1 = r11.$step
            int r3 = r11.$size
            int r1 = r1 - r3
            if (r1 < 0) goto L_0x0109
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
            r3 = 0
            java.util.Iterator r5 = r11.$iterator
            r6 = r0
            r3 = r4
            r0 = r11
            r4 = r1
            r1 = r5
            r5 = r12
            r12 = 0
        L_0x00a2:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x00e3
            java.lang.Object r7 = r1.next()
            if (r12 <= 0) goto L_0x00b1
            int r12 = r12 + -1
            goto L_0x00a2
        L_0x00b1:
            r3.add(r7)
            int r8 = r3.size()
            int r9 = r0.$size
            if (r8 != r9) goto L_0x00a2
            r0.L$0 = r5
            r0.I$0 = r4
            r0.L$1 = r3
            r0.I$1 = r12
            r0.L$2 = r7
            r0.L$3 = r1
            r0.label = r2
            java.lang.Object r12 = r5.yield(r3, r0)
            if (r12 != r6) goto L_0x00d1
            return r6
        L_0x00d1:
            boolean r12 = r0.$reuseBuffer
            if (r12 == 0) goto L_0x00d9
            r3.clear()
            goto L_0x00e1
        L_0x00d9:
            java.util.ArrayList r12 = new java.util.ArrayList
            int r3 = r0.$size
            r12.<init>(r3)
            r3 = r12
        L_0x00e1:
            r12 = r4
            goto L_0x00a2
        L_0x00e3:
            r1 = r3
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x01a4
            boolean r1 = r0.$partialWindows
            if (r1 != 0) goto L_0x00f9
            int r1 = r3.size()
            int r2 = r0.$size
            if (r1 != r2) goto L_0x01a4
        L_0x00f9:
            r0.I$0 = r4
            r0.L$0 = r3
            r0.I$1 = r12
            r12 = 2
            r0.label = r12
            java.lang.Object r12 = r5.yield(r3, r0)
            if (r12 != r6) goto L_0x01a4
            return r6
        L_0x0109:
            kotlin.collections.RingBuffer r4 = new kotlin.collections.RingBuffer
            r4.<init>(r3)
            java.util.Iterator r3 = r11.$iterator
            r5 = r12
            r12 = r11
            r10 = r4
            r4 = r1
            r1 = r3
            r3 = r10
        L_0x0116:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x0155
            java.lang.Object r6 = r1.next()
            r3.add(r6)
            boolean r7 = r3.isFull()
            if (r7 == 0) goto L_0x0116
            boolean r7 = r12.$reuseBuffer
            if (r7 == 0) goto L_0x0131
            r7 = r3
            java.util.List r7 = (java.util.List) r7
            goto L_0x013b
        L_0x0131:
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = r3
            java.util.Collection r8 = (java.util.Collection) r8
            r7.<init>(r8)
            java.util.List r7 = (java.util.List) r7
        L_0x013b:
            r12.L$0 = r5
            r12.I$0 = r4
            r12.L$1 = r3
            r12.L$2 = r6
            r12.L$3 = r1
            r6 = 3
            r12.label = r6
            java.lang.Object r6 = r5.yield(r7, r12)
            if (r6 != r0) goto L_0x014f
            return r0
        L_0x014f:
            int r6 = r12.$step
            r3.removeFirst(r6)
            goto L_0x0116
        L_0x0155:
            boolean r1 = r12.$partialWindows
            if (r1 == 0) goto L_0x01a4
            r1 = r3
            r3 = r4
            r4 = r5
        L_0x015c:
            int r5 = r1.size()
            int r6 = r12.$step
            if (r5 <= r6) goto L_0x018c
            boolean r5 = r12.$reuseBuffer
            if (r5 == 0) goto L_0x016c
            r5 = r1
            java.util.List r5 = (java.util.List) r5
            goto L_0x0176
        L_0x016c:
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = r1
            java.util.Collection r6 = (java.util.Collection) r6
            r5.<init>(r6)
            java.util.List r5 = (java.util.List) r5
        L_0x0176:
            r12.L$0 = r4
            r12.I$0 = r3
            r12.L$1 = r1
            r6 = 4
            r12.label = r6
            java.lang.Object r5 = r4.yield(r5, r12)
            if (r5 != r0) goto L_0x0186
            return r0
        L_0x0186:
            int r5 = r12.$step
            r1.removeFirst(r5)
            goto L_0x015c
        L_0x018c:
            r5 = r1
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            r2 = r2 ^ r5
            if (r2 == 0) goto L_0x01a4
            r12.I$0 = r3
            r12.L$0 = r1
            r2 = 5
            r12.label = r2
            java.lang.Object r12 = r4.yield(r1, r12)
            if (r12 != r0) goto L_0x01a4
            return r0
        L_0x01a4:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x01a7:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r12 = r12.exception
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
