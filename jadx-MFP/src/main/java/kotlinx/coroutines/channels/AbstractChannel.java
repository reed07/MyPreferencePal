package kotlinx.coroutines.channels;

import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuation.DefaultImpls;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\b<=>?@ABCB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0013\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0004J\u0016\u0010\u001a\u001a\u00020\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0002J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0014J\b\u0010 \u001a\u00020\u0014H\u0014J\r\u0010!\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\"J\n\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0016\u0010%\u001a\u0004\u0018\u00010$2\n\u0010&\u001a\u0006\u0012\u0002\b\u00030'H\u0014J\u0011\u0010\u001b\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0013\u0010)\u001a\u0004\u0018\u00018\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0019\u0010*\u001a\u0004\u0018\u00018\u00002\b\u0010+\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0002\u0010,J\u0013\u0010-\u001a\u0004\u0018\u00018\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0017\u0010.\u001a\u00028\u00002\b\u0010+\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0002\u0010,J\u0011\u0010/\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010(JH\u00100\u001a\u00020\u0014\"\u0004\b\u0001\u001012\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H10'2\"\u00102\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H104\u0012\u0006\u0012\u0004\u0018\u00010$03H\u0002ø\u0001\u0000¢\u0006\u0002\u00105JJ\u00106\u001a\u00020\u0014\"\u0004\b\u0001\u001012\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H10'2$\u00102\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H104\u0012\u0006\u0012\u0004\u0018\u00010$03H\u0002ø\u0001\u0000¢\u0006\u0002\u00105J \u00107\u001a\u00020\u00142\n\u00108\u001a\u0006\u0012\u0002\b\u0003092\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0002J\u0010\u0010:\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010;H\u0014R\u0014\u0010\u0005\u001a\u00020\u00068DX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u0006X¤\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\bR\u0012\u0010\n\u001a\u00020\u0006X¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000e8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel;", "E", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/Channel;", "()V", "hasReceiveOrClosed", "", "getHasReceiveOrClosed", "()Z", "isBufferAlwaysEmpty", "isBufferEmpty", "isClosedForReceive", "isEmpty", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrNull", "getOnReceiveOrNull", "cancel", "", "cause", "", "cleanupSendQueueOnCancel", "describeTryPoll", "Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "enqueueReceive", "receive", "Lkotlinx/coroutines/channels/Receive;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "onReceiveDequeued", "onReceiveEnqueued", "poll", "()Ljava/lang/Object;", "pollInternal", "", "pollSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "receiveOrNullResult", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "receiveOrNullSuspend", "receiveResult", "receiveSuspend", "registerSelectReceive", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectReceiveOrNull", "removeReceiveOnCancel", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "takeFirstReceiveOrPeekClosed", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "IdempotentTokenValue", "Itr", "ReceiveElement", "ReceiveHasNext", "ReceiveSelect", "RemoveReceiveOnCancel", "TryEnqueueReceiveDesc", "TryPollDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: AbstractChannel.kt */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005R\u0010\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$IdempotentTokenValue;", "E", "", "token", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class IdempotentTokenValue<E> {
        @NotNull
        @JvmField
        public final Object token;
        @JvmField
        public final E value;

        public IdempotentTokenValue(@NotNull Object obj, E e) {
            Intrinsics.checkParameterIsNotNull(obj, GooglePlayConstants.BILLING_JSON_FIELD_TOKEN);
            this.token = obj;
            this.value = e;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0011\u0010\u000e\u001a\u00020\u000fHBø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0011\u0010\u0012\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0011\u0010\u0013\u001a\u00028\u0001HBø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "E", "Lkotlinx/coroutines/channels/ChannelIterator;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "(Lkotlinx/coroutines/channels/AbstractChannel;)V", "getChannel", "()Lkotlinx/coroutines/channels/AbstractChannel;", "result", "", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasNextResult", "hasNextSuspend", "next", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class Itr<E> implements ChannelIterator<E> {
        @NotNull
        private final AbstractChannel<E> channel;
        @Nullable
        private Object result = AbstractChannelKt.POLL_FAILED;

        public Itr(@NotNull AbstractChannel<E> abstractChannel) {
            Intrinsics.checkParameterIsNotNull(abstractChannel, "channel");
            this.channel = abstractChannel;
        }

        @NotNull
        public final AbstractChannel<E> getChannel() {
            return this.channel;
        }

        public final void setResult(@Nullable Object obj) {
            this.result = obj;
        }

        @Nullable
        public Object hasNext(@NotNull Continuation<? super Boolean> continuation) {
            if (this.result != AbstractChannelKt.POLL_FAILED) {
                return Boxing.boxBoolean(hasNextResult(this.result));
            }
            this.result = this.channel.pollInternal();
            if (this.result != AbstractChannelKt.POLL_FAILED) {
                return Boxing.boxBoolean(hasNextResult(this.result));
            }
            return hasNextSuspend(continuation);
        }

        private final boolean hasNextResult(Object obj) {
            if (!(obj instanceof Closed)) {
                return true;
            }
            Closed closed = (Closed) obj;
            if (closed.closeCause == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(closed.getReceiveException());
        }

        @Nullable
        public Object next(@NotNull Continuation<? super E> continuation) {
            Object obj = this.result;
            if (obj instanceof Closed) {
                throw StackTraceRecoveryKt.recoverStackTrace(((Closed) obj).getReceiveException());
            } else if (obj == AbstractChannelKt.POLL_FAILED) {
                return this.channel.receive(continuation);
            } else {
                this.result = AbstractChannelKt.POLL_FAILED;
                return obj;
            }
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public final /* synthetic */ Object hasNextSuspend(@NotNull Continuation<? super Boolean> continuation) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
            CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            ReceiveHasNext receiveHasNext = new ReceiveHasNext(this, cancellableContinuation);
            while (true) {
                Receive receive = receiveHasNext;
                if (!getChannel().enqueueReceive(receive)) {
                    Object pollInternal = getChannel().pollInternal();
                    setResult(pollInternal);
                    if (!(pollInternal instanceof Closed)) {
                        if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                            Continuation continuation2 = cancellableContinuation;
                            Boolean boxBoolean = Boxing.boxBoolean(true);
                            Companion companion = Result.Companion;
                            continuation2.resumeWith(Result.m6constructorimpl(boxBoolean));
                            break;
                        }
                    } else {
                        Closed closed = (Closed) pollInternal;
                        if (closed.closeCause == null) {
                            Continuation continuation3 = cancellableContinuation;
                            Boolean boxBoolean2 = Boxing.boxBoolean(false);
                            Companion companion2 = Result.Companion;
                            continuation3.resumeWith(Result.m6constructorimpl(boxBoolean2));
                        } else {
                            Continuation continuation4 = cancellableContinuation;
                            Throwable receiveException = closed.getReceiveException();
                            Companion companion3 = Result.Companion;
                            continuation4.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(receiveException)));
                        }
                    }
                } else {
                    getChannel().removeReceiveOnCancel(cancellableContinuation, receive);
                    break;
                }
            }
            Object result2 = cancellableContinuationImpl.getResult();
            if (result2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J!\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00028\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\u0014R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", "E", "Lkotlinx/coroutines/channels/Receive;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "nullOnClose", "", "(Lkotlinx/coroutines/CancellableContinuation;Z)V", "completeResumeReceive", "", "token", "", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class ReceiveElement<E> extends Receive<E> {
        @NotNull
        @JvmField
        public final CancellableContinuation<E> cont;
        @JvmField
        public final boolean nullOnClose;

        public ReceiveElement(@NotNull CancellableContinuation<? super E> cancellableContinuation, boolean z) {
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            this.cont = cancellableContinuation;
            this.nullOnClose = z;
        }

        @Nullable
        public Object tryResumeReceive(E e, @Nullable Object obj) {
            return this.cont.tryResume(e, obj);
        }

        public void completeResumeReceive(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, GooglePlayConstants.BILLING_JSON_FIELD_TOKEN);
            this.cont.completeResume(obj);
        }

        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (closed.closeCause != null || !this.nullOnClose) {
                Continuation continuation = this.cont;
                Throwable receiveException = closed.getReceiveException();
                Companion companion = Result.Companion;
                continuation.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(receiveException)));
                return;
            }
            Continuation continuation2 = this.cont;
            Companion companion2 = Result.Companion;
            continuation2.resumeWith(Result.m6constructorimpl(null));
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ReceiveElement[");
            sb.append(this.cont);
            sb.append(",nullOnClose=");
            sb.append(this.nullOnClose);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J!\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00028\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\u0015R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveHasNext;", "E", "Lkotlinx/coroutines/channels/Receive;", "iterator", "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/channels/AbstractChannel$Itr;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeReceive", "", "token", "", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class ReceiveHasNext<E> extends Receive<E> {
        @NotNull
        @JvmField
        public final CancellableContinuation<Boolean> cont;
        @NotNull
        @JvmField
        public final Itr<E> iterator;

        public ReceiveHasNext(@NotNull Itr<E> itr, @NotNull CancellableContinuation<? super Boolean> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(itr, "iterator");
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            this.iterator = itr;
            this.cont = cancellableContinuation;
        }

        @Nullable
        public Object tryResumeReceive(E e, @Nullable Object obj) {
            Object tryResume = this.cont.tryResume(Boolean.valueOf(true), obj);
            if (tryResume != null) {
                if (obj != null) {
                    return new IdempotentTokenValue(tryResume, e);
                }
                this.iterator.setResult(e);
            }
            return tryResume;
        }

        public void completeResumeReceive(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, GooglePlayConstants.BILLING_JSON_FIELD_TOKEN);
            if (obj instanceof IdempotentTokenValue) {
                IdempotentTokenValue idempotentTokenValue = (IdempotentTokenValue) obj;
                this.iterator.setResult(idempotentTokenValue.value);
                this.cont.completeResume(idempotentTokenValue.token);
                return;
            }
            this.cont.completeResume(obj);
        }

        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(closed, "closed");
            if (closed.closeCause == null) {
                obj = DefaultImpls.tryResume$default(this.cont, Boolean.valueOf(false), null, 2, null);
            } else {
                obj = this.cont.tryResumeWithException(StackTraceRecoveryKt.recoverStackTrace(closed.getReceiveException(), this.cont));
            }
            if (obj != null) {
                this.iterator.setResult(closed);
                this.cont.completeResume(obj);
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ReceiveHasNext[");
            sb.append(this.cont);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$RemoveReceiveOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "receive", "Lkotlinx/coroutines/channels/Receive;", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/channels/Receive;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private final class RemoveReceiveOnCancel extends CancelHandler {
        private final Receive<?> receive;
        final /* synthetic */ AbstractChannel this$0;

        public RemoveReceiveOnCancel(AbstractChannel abstractChannel, @NotNull Receive<?> receive2) {
            Intrinsics.checkParameterIsNotNull(receive2, "receive");
            this.this$0 = abstractChannel;
            this.receive = receive2;
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }

        public void invoke(@Nullable Throwable th) {
            if (this.receive.remove()) {
                this.this$0.onReceiveDequeued();
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("RemoveReceiveOnCancel[");
            sb.append(this.receive);
            sb.append(']');
            return sb.toString();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0014J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0014R\u0016\u0010\b\u001a\u0004\u0018\u00018\u00018\u0006@\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "pollResult", "Ljava/lang/Object;", "resumeToken", "", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "validatePrepared", "", "node", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    protected static final class TryPollDesc<E> extends RemoveFirstDesc<Send> {
    }

    /* access modifiers changed from: protected */
    public abstract boolean isBufferAlwaysEmpty();

    /* access modifiers changed from: protected */
    public abstract boolean isBufferEmpty();

    /* access modifiers changed from: protected */
    public void onReceiveDequeued() {
    }

    /* access modifiers changed from: protected */
    public void onReceiveEnqueued() {
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Object pollInternal() {
        Send takeFirstSendOrPeekClosed;
        Object tryResumeSend;
        do {
            takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (takeFirstSendOrPeekClosed == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
        } while (tryResumeSend == null);
        takeFirstSendOrPeekClosed.completeResumeSend(tryResumeSend);
        return takeFirstSendOrPeekClosed.getPollResult();
    }

    @Nullable
    public final Object receive(@NotNull Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        if (pollInternal != AbstractChannelKt.POLL_FAILED) {
            return receiveResult(pollInternal);
        }
        return receiveSuspend(continuation);
    }

    private final E receiveResult(Object obj) {
        if (!(obj instanceof Closed)) {
            return obj;
        }
        throw StackTraceRecoveryKt.recoverStackTrace(((Closed) obj).getReceiveException());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003e A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean enqueueReceive(kotlinx.coroutines.channels.Receive<? super E> r7) {
        /*
            r6 = this;
            boolean r0 = r6.isBufferAlwaysEmpty()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0031
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r6.getQueue()
        L_0x000c:
            java.lang.Object r3 = r0.getPrev()
            if (r3 == 0) goto L_0x0029
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            boolean r4 = r3 instanceof kotlinx.coroutines.channels.Send
            if (r4 != 0) goto L_0x001a
            r4 = 1
            goto L_0x001b
        L_0x001a:
            r4 = 0
        L_0x001b:
            if (r4 != 0) goto L_0x001e
            goto L_0x0059
        L_0x001e:
            r4 = r7
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r4
            boolean r3 = r3.addNext(r4, r0)
            if (r3 == 0) goto L_0x000c
            r2 = 1
            goto L_0x0059
        L_0x0029:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            r7.<init>(r0)
            throw r7
        L_0x0031:
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r6.getQueue()
            kotlinx.coroutines.channels.AbstractChannel$enqueueReceive$$inlined$addLastIfPrevAndIf$1 r3 = new kotlinx.coroutines.channels.AbstractChannel$enqueueReceive$$inlined$addLastIfPrevAndIf$1
            kotlinx.coroutines.internal.LockFreeLinkedListNode r7 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r7
            r3.<init>(r7, r7, r6)
            kotlinx.coroutines.internal.LockFreeLinkedListNode$CondAddOp r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp) r3
        L_0x003e:
            java.lang.Object r4 = r0.getPrev()
            if (r4 == 0) goto L_0x005f
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r4
            boolean r5 = r4 instanceof kotlinx.coroutines.channels.Send
            if (r5 != 0) goto L_0x004c
            r5 = 1
            goto L_0x004d
        L_0x004c:
            r5 = 0
        L_0x004d:
            if (r5 != 0) goto L_0x0050
            goto L_0x0059
        L_0x0050:
            int r4 = r4.tryCondAddNext(r7, r0, r3)
            switch(r4) {
                case 1: goto L_0x0058;
                case 2: goto L_0x0059;
                default: goto L_0x0057;
            }
        L_0x0057:
            goto L_0x003e
        L_0x0058:
            r2 = 1
        L_0x0059:
            if (r2 == 0) goto L_0x005e
            r6.onReceiveEnqueued()
        L_0x005e:
            return r2
        L_0x005f:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractChannel.enqueueReceive(kotlinx.coroutines.channels.Receive):boolean");
    }

    public void cancel() {
        cancel(null);
    }

    public boolean cancel(@Nullable Throwable th) {
        boolean close = close(th);
        cleanupSendQueueOnCancel();
        return close;
    }

    /* access modifiers changed from: protected */
    public void cleanupSendQueueOnCancel() {
        Closed closedForSend = getClosedForSend();
        if (closedForSend != null) {
            while (true) {
                Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                if (takeFirstSendOrPeekClosed == null) {
                    throw new IllegalStateException("Cannot happen".toString());
                } else if (takeFirstSendOrPeekClosed instanceof Closed) {
                    if (!(takeFirstSendOrPeekClosed == closedForSend)) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    return;
                } else {
                    takeFirstSendOrPeekClosed.resumeSendClosed(closedForSend);
                }
            }
        } else {
            throw new IllegalStateException("Cannot happen".toString());
        }
    }

    @NotNull
    public final ChannelIterator<E> iterator() {
        return new Itr<>(this);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (takeFirstReceiveOrPeekClosed != null && !(takeFirstReceiveOrPeekClosed instanceof Closed)) {
            onReceiveDequeued();
        }
        return takeFirstReceiveOrPeekClosed;
    }

    /* access modifiers changed from: private */
    public final void removeReceiveOnCancel(CancellableContinuation<?> cancellableContinuation, Receive<?> receive) {
        cancellableContinuation.invokeOnCancellation(new RemoveReceiveOnCancel(this, receive));
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object receiveSuspend(@NotNull Continuation<? super E> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ReceiveElement receiveElement = new ReceiveElement(cancellableContinuation, false);
        while (true) {
            Receive receive = receiveElement;
            if (!enqueueReceive(receive)) {
                Object pollInternal = pollInternal();
                if (!(pollInternal instanceof Closed)) {
                    if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                        Continuation continuation2 = cancellableContinuation;
                        Companion companion = Result.Companion;
                        continuation2.resumeWith(Result.m6constructorimpl(pollInternal));
                        break;
                    }
                } else {
                    Continuation continuation3 = cancellableContinuation;
                    Throwable receiveException = ((Closed) pollInternal).getReceiveException();
                    Companion companion2 = Result.Companion;
                    continuation3.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(receiveException)));
                    break;
                }
            } else {
                removeReceiveOnCancel(cancellableContinuation, receive);
                break;
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
