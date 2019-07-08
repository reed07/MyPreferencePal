package kotlinx.coroutines.channels;

import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0006VWXYZ[B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010!\u001a\u00020\u000f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0004J\b\u0010(\u001a\u00020)H\u0002J!\u0010*\u001a\u000e\u0012\u0002\b\u00030+j\u0006\u0012\u0002\b\u0003`,2\u0006\u0010-\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010.J!\u0010/\u001a\u000e\u0012\u0002\b\u00030+j\u0006\u0012\u0002\b\u0003`,2\u0006\u0010-\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010.J\u001b\u00100\u001a\b\u0012\u0004\u0012\u00028\u0000012\u0006\u0010-\u001a\u00028\u0000H\u0004¢\u0006\u0002\u00102J\u0012\u00103\u001a\u0004\u0018\u00010\u00162\u0006\u00104\u001a\u000205H\u0002J\u0014\u00106\u001a\u00020%2\n\u00107\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\"\u00108\u001a\u00020%2\u0018\u00109\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010#\u0012\u0004\u0012\u00020%0:j\u0002`;H\u0016J\u0012\u0010<\u001a\u00020%2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u0013\u0010=\u001a\u00020\u000f2\u0006\u0010-\u001a\u00028\u0000¢\u0006\u0002\u0010>J\u0015\u0010?\u001a\u00020\u00162\u0006\u0010-\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010@J!\u0010A\u001a\u00020\u00162\u0006\u0010-\u001a\u00028\u00002\n\u0010B\u001a\u0006\u0012\u0002\b\u00030CH\u0014¢\u0006\u0002\u0010DJ\u0010\u0010E\u001a\u00020%2\u0006\u00107\u001a\u00020'H\u0014JV\u0010F\u001a\u00020%\"\u0004\b\u0001\u0010G2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002HG0C2\u0006\u0010-\u001a\u00028\u00002(\u0010H\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002HG0J\u0012\u0006\u0012\u0004\u0018\u00010\u00160IH\u0002ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0019\u00104\u001a\u00020%2\u0006\u0010-\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u001b\u0010M\u001a\b\u0012\u0002\b\u0003\u0018\u00010N2\u0006\u0010-\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010OJ\u001b\u0010P\u001a\b\u0012\u0002\b\u0003\u0018\u00010N2\u0006\u0010-\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010OJ\u0019\u0010Q\u001a\u00020%2\u0006\u0010-\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0010\u0010R\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010NH\u0014J\n\u0010S\u001a\u0004\u0018\u00010TH\u0004J\b\u0010U\u001a\u00020\u0005H\u0016R\u0014\u0010\u0004\u001a\u00020\u00058TX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t8DX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t8DX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015X\u0004¢\u0006\u0002\n\u0000R#\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\\"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", "E", "Lkotlinx/coroutines/channels/SendChannel;", "()V", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "closedForReceive", "Lkotlinx/coroutines/channels/Closed;", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForSend", "getClosedForSend", "isBufferAlwaysFull", "", "()Z", "isBufferFull", "isClosedForSend", "isFull", "onCloseHandler", "Lkotlinx/atomicfu/AtomicRef;", "", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queueDebugStateString", "getQueueDebugStateString", "close", "cause", "", "conflatePreviousSendBuffered", "", "node", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "countQueueSize", "", "describeSendBuffered", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "element", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "describeSendConflated", "describeTryOffer", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "enqueueSend", "send", "Lkotlinx/coroutines/channels/SendElement;", "helpClose", "closed", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "invokeOnCloseHandler", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onClosedIdempotent", "registerSelectSend", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBuffered", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendConflated", "sendSuspend", "takeFirstReceiveOrPeekClosed", "takeFirstSendOrPeekClosed", "Lkotlinx/coroutines/channels/Send;", "toString", "SendBuffered", "SendBufferedDesc", "SendConflatedDesc", "SendSelect", "TryEnqueueSendDesc", "TryOfferDesc", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: AbstractChannel.kt */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    private volatile Object onCloseHandler = null;
    @NotNull
    private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0014\u0010\u000e\u001a\u00020\f2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0016R\u0012\u0010\u0004\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "token", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    private static final class SendBuffered<E> extends LockFreeLinkedListNode implements Send {
        @JvmField
        public final E element;

        public void resumeSendClosed(@NotNull Closed<?> closed) {
            Intrinsics.checkParameterIsNotNull(closed, "closed");
        }

        public SendBuffered(E e) {
            this.element = e;
        }

        @Nullable
        public Object getPollResult() {
            return this.element;
        }

        @Nullable
        public Object tryResumeSend(@Nullable Object obj) {
            return AbstractChannelKt.SEND_RESUMED;
        }

        public void completeResumeSend(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, GooglePlayConstants.BILLING_JSON_FIELD_TOKEN);
            if (!(obj == AbstractChannelKt.SEND_RESUMED)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0014J\u0016\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0014R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "resumeToken", "", "failure", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "next", "validatePrepared", "", "node", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AbstractChannel.kt */
    protected static final class TryOfferDesc<E> extends RemoveFirstDesc<ReceiveOrClosed<? super E>> {
    }

    /* access modifiers changed from: protected */
    @NotNull
    public String getBufferDebugString() {
        return "";
    }

    /* access modifiers changed from: protected */
    public abstract boolean isBufferAlwaysFull();

    /* access modifiers changed from: protected */
    public abstract boolean isBufferFull();

    /* access modifiers changed from: protected */
    public void onClosedIdempotent(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "closed");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final LockFreeLinkedListHead getQueue() {
        return this.queue;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Object offerInternal(E e) {
        ReceiveOrClosed takeFirstReceiveOrPeekClosed;
        Object tryResumeReceive;
        do {
            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (takeFirstReceiveOrPeekClosed == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
            tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(e, null);
        } while (tryResumeReceive == null);
        takeFirstReceiveOrPeekClosed.completeResumeReceive(tryResumeReceive);
        return takeFirstReceiveOrPeekClosed.getOfferResult();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (!(prevNode instanceof Closed)) {
            prevNode = null;
        }
        Closed<?> closed = (Closed) prevNode;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Closed<?> getClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (!(nextNode instanceof Closed)) {
            nextNode = null;
        }
        Closed<?> closed = (Closed) nextNode;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r3 = r1;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlinx.coroutines.channels.Send takeFirstSendOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
        L_0x0002:
            java.lang.Object r1 = r0.getNext()
            if (r1 == 0) goto L_0x002c
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = r0
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            r3 = 0
            if (r1 != r2) goto L_0x0011
            goto L_0x0025
        L_0x0011:
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.Send
            if (r2 != 0) goto L_0x0016
            goto L_0x0025
        L_0x0016:
            r2 = r1
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L_0x001e
            goto L_0x0024
        L_0x001e:
            boolean r2 = r1.remove()
            if (r2 == 0) goto L_0x0028
        L_0x0024:
            r3 = r1
        L_0x0025:
            kotlinx.coroutines.channels.Send r3 = (kotlinx.coroutines.channels.Send) r3
            return r3
        L_0x0028:
            r1.helpDelete()
            goto L_0x0002
        L_0x002c:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstSendOrPeekClosed():kotlinx.coroutines.channels.Send");
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final ReceiveOrClosed<?> sendBuffered(E e) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        LockFreeLinkedListNode sendBuffered = new SendBuffered(e);
        do {
            Object prev = lockFreeLinkedListHead.getPrev();
            if (prev != null) {
                lockFreeLinkedListNode = (LockFreeLinkedListNode) prev;
                if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                    return (ReceiveOrClosed) lockFreeLinkedListNode;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!lockFreeLinkedListNode.addNext(sendBuffered, lockFreeLinkedListHead));
        return null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final ReceiveOrClosed<?> sendConflated(E e) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        SendBuffered sendBuffered = new SendBuffered(e);
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        do {
            Object prev = lockFreeLinkedListHead.getPrev();
            if (prev != null) {
                lockFreeLinkedListNode = (LockFreeLinkedListNode) prev;
                if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                    return (ReceiveOrClosed) lockFreeLinkedListNode;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (!lockFreeLinkedListNode.addNext(sendBuffered, lockFreeLinkedListHead));
        conflatePreviousSendBuffered(sendBuffered);
        return null;
    }

    /* access modifiers changed from: protected */
    public final void conflatePreviousSendBuffered(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "node");
        for (LockFreeLinkedListNode prevNode = lockFreeLinkedListNode.getPrevNode(); prevNode instanceof SendBuffered; prevNode = prevNode.getPrevNode()) {
            if (!prevNode.remove()) {
                prevNode.helpRemove();
            }
        }
    }

    @Nullable
    public final Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        if (offer(e)) {
            return Unit.INSTANCE;
        }
        return sendSuspend(e, continuation);
    }

    public final boolean offer(E e) {
        Object offerInternal = offerInternal(e);
        if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
            return true;
        }
        if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
            Closed closedForSend = getClosedForSend();
            if (closedForSend != null) {
                Throwable sendException = closedForSend.getSendException();
                if (sendException != null) {
                    Throwable recoverStackTrace = StackTraceRecoveryKt.recoverStackTrace(sendException);
                    if (recoverStackTrace != null) {
                        throw recoverStackTrace;
                    }
                }
            }
            return false;
        } else if (offerInternal instanceof Closed) {
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed) offerInternal).getSendException());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("offerInternal returned ");
            sb.append(offerInternal);
            throw new IllegalStateException(sb.toString().toString());
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032 A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object enqueueSend(kotlinx.coroutines.channels.SendElement r5) {
        /*
            r4 = this;
            boolean r0 = r4.isBufferAlwaysFull()
            if (r0 == 0) goto L_0x0027
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
        L_0x0008:
            java.lang.Object r1 = r0.getPrev()
            if (r1 == 0) goto L_0x001f
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r2 == 0) goto L_0x0015
            return r1
        L_0x0015:
            r2 = r5
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            boolean r1 = r1.addNext(r2, r0)
            if (r1 == 0) goto L_0x0008
            goto L_0x004f
        L_0x001f:
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            r5.<init>(r0)
            throw r5
        L_0x0027:
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
            kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1 r1 = new kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r5
            r1.<init>(r5, r5, r4)
            kotlinx.coroutines.internal.LockFreeLinkedListNode$CondAddOp r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode.CondAddOp) r1
        L_0x0032:
            java.lang.Object r2 = r0.getPrev()
            if (r2 == 0) goto L_0x0051
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            boolean r3 = r2 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r3 == 0) goto L_0x003f
            return r2
        L_0x003f:
            int r2 = r2.tryCondAddNext(r5, r0, r1)
            switch(r2) {
                case 1: goto L_0x0049;
                case 2: goto L_0x0047;
                default: goto L_0x0046;
            }
        L_0x0046:
            goto L_0x0032
        L_0x0047:
            r5 = 0
            goto L_0x004a
        L_0x0049:
            r5 = 1
        L_0x004a:
            if (r5 != 0) goto L_0x004f
            java.lang.Object r5 = kotlinx.coroutines.channels.AbstractChannelKt.ENQUEUE_FAILED
            return r5
        L_0x004f:
            r5 = 0
            return r5
        L_0x0051:
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.enqueueSend(kotlinx.coroutines.channels.SendElement):java.lang.Object");
    }

    public boolean close(@Nullable Throwable th) {
        boolean z;
        Closed closed = new Closed(th);
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            Object prev = lockFreeLinkedListHead.getPrev();
            if (prev != null) {
                LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) prev;
                if (!(lockFreeLinkedListNode instanceof Closed)) {
                    if (lockFreeLinkedListNode.addNext(closed, lockFreeLinkedListHead)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        }
        if (!z) {
            LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
            if (prevNode != null) {
                helpClose((Closed) prevNode);
                return false;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
        }
        helpClose(closed);
        invokeOnCloseHandler(th);
        return true;
    }

    private final void invokeOnCloseHandler(Throwable th) {
        Object obj = this.onCloseHandler;
        if (obj != null && obj != AbstractChannelKt.HANDLER_INVOKED && onCloseHandler$FU.compareAndSet(this, obj, AbstractChannelKt.HANDLER_INVOKED)) {
            ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1)).invoke(th);
        }
    }

    /* access modifiers changed from: private */
    public final void helpClose(Closed<?> closed) {
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            if ((prevNode instanceof LockFreeLinkedListHead) || !(prevNode instanceof Receive)) {
                onClosedIdempotent(closed);
            } else if (!prevNode.remove()) {
                prevNode.helpRemove();
            } else {
                ((Receive) prevNode).resumeReceiveClosed(closed);
            }
        }
        onClosedIdempotent(closed);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r3 = r1;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public kotlinx.coroutines.channels.ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        /*
            r4 = this;
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = r4.queue
        L_0x0002:
            java.lang.Object r1 = r0.getNext()
            if (r1 == 0) goto L_0x002c
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            r2 = r0
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            r3 = 0
            if (r1 != r2) goto L_0x0011
            goto L_0x0025
        L_0x0011:
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.ReceiveOrClosed
            if (r2 != 0) goto L_0x0016
            goto L_0x0025
        L_0x0016:
            r2 = r1
            kotlinx.coroutines.channels.ReceiveOrClosed r2 = (kotlinx.coroutines.channels.ReceiveOrClosed) r2
            boolean r2 = r2 instanceof kotlinx.coroutines.channels.Closed
            if (r2 == 0) goto L_0x001e
            goto L_0x0024
        L_0x001e:
            boolean r2 = r1.remove()
            if (r2 == 0) goto L_0x0028
        L_0x0024:
            r3 = r1
        L_0x0025:
            kotlinx.coroutines.channels.ReceiveOrClosed r3 = (kotlinx.coroutines.channels.ReceiveOrClosed) r3
            return r3
        L_0x0028:
            r1.helpDelete()
            goto L_0x0002
        L_0x002c:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractSendChannel.takeFirstReceiveOrPeekClosed():kotlinx.coroutines.channels.ReceiveOrClosed");
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DebugKt.getClassSimpleName(this));
        sb.append('@');
        sb.append(DebugKt.getHexAddress(this));
        sb.append('{');
        sb.append(getQueueDebugStateString());
        sb.append('}');
        sb.append(getBufferDebugString());
        return sb.toString();
    }

    private final String getQueueDebugStateString() {
        String str;
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            return "EmptyQueue";
        }
        if (nextNode instanceof Closed) {
            str = nextNode.toString();
        } else if (nextNode instanceof Receive) {
            str = "ReceiveQueued";
        } else if (nextNode instanceof Send) {
            str = "SendQueued";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("UNEXPECTED:");
            sb.append(nextNode);
            str = sb.toString();
        }
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        if (prevNode != nextNode) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(",queueSize=");
            sb2.append(countQueueSize());
            str = sb2.toString();
            if (prevNode instanceof Closed) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(",closedForSend=");
                sb3.append(prevNode);
                str = sb3.toString();
            }
        }
        return str;
    }

    private final int countQueueSize() {
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        Object next = lockFreeLinkedListHead.getNext();
        if (next != null) {
            int i = 0;
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual((Object) lockFreeLinkedListNode, (Object) lockFreeLinkedListHead); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                    i++;
                }
            }
            return i;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object sendSuspend(E e, @NotNull Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        SendElement sendElement = new SendElement(e, cancellableContinuation);
        while (true) {
            Object access$enqueueSend = enqueueSend(sendElement);
            if (access$enqueueSend == null) {
                CancellableContinuationKt.removeOnCancellation(cancellableContinuation, sendElement);
                break;
            } else if (access$enqueueSend instanceof Closed) {
                Closed closed = (Closed) access$enqueueSend;
                helpClose(closed);
                Continuation continuation2 = cancellableContinuation;
                Throwable sendException = closed.getSendException();
                Companion companion = Result.Companion;
                continuation2.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(sendException)));
                break;
            } else {
                Object offerInternal = offerInternal(e);
                if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
                    Continuation continuation3 = cancellableContinuation;
                    Unit unit = Unit.INSTANCE;
                    Companion companion2 = Result.Companion;
                    continuation3.resumeWith(Result.m6constructorimpl(unit));
                    break;
                } else if (offerInternal != AbstractChannelKt.OFFER_FAILED) {
                    if (offerInternal instanceof Closed) {
                        Closed closed2 = (Closed) offerInternal;
                        helpClose(closed2);
                        Continuation continuation4 = cancellableContinuation;
                        Throwable sendException2 = closed2.getSendException();
                        Companion companion3 = Result.Companion;
                        continuation4.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(sendException2)));
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("offerInternal returned ");
                        sb.append(offerInternal);
                        throw new IllegalStateException(sb.toString().toString());
                    }
                }
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
