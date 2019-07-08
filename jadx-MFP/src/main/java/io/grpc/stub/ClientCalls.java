package io.grpc.stub;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientCall.Listener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class ClientCalls {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger logger = Logger.getLogger(ClientCalls.class.getName());

    private static final class CallToStreamObserverAdapter<T> extends ClientCallStreamObserver<T> {
        /* access modifiers changed from: private */
        public boolean autoFlowControlEnabled = true;
        private final ClientCall<T, ?> call;
        private boolean frozen;
        /* access modifiers changed from: private */
        public Runnable onReadyHandler;

        CallToStreamObserverAdapter(ClientCall<T, ?> clientCall) {
            this.call = clientCall;
        }

        /* access modifiers changed from: private */
        public void freeze() {
            this.frozen = true;
        }

        public void onNext(T t) {
            this.call.sendMessage(t);
        }

        public void onError(Throwable th) {
            this.call.cancel("Cancelled by client with StreamObserver.onError()", th);
        }

        public void onCompleted() {
            this.call.halfClose();
        }

        public void request(int i) {
            this.call.request(i);
        }
    }

    private static final class GrpcFuture<RespT> extends AbstractFuture<RespT> {
        private final ClientCall<?, RespT> call;

        GrpcFuture(ClientCall<?, RespT> clientCall) {
            this.call = clientCall;
        }

        /* access modifiers changed from: protected */
        public void interruptTask() {
            this.call.cancel("GrpcFuture was cancelled", null);
        }

        /* access modifiers changed from: protected */
        public boolean set(@Nullable RespT respt) {
            return super.set(respt);
        }

        /* access modifiers changed from: protected */
        public boolean setException(Throwable th) {
            return super.setException(th);
        }
    }

    private static final class StreamObserverToCallListenerAdapter<ReqT, RespT> extends Listener<RespT> {
        private final CallToStreamObserverAdapter<ReqT> adapter;
        private boolean firstResponseReceived;
        private final StreamObserver<RespT> observer;
        private final boolean streamingResponse;

        public void onHeaders(Metadata metadata) {
        }

        StreamObserverToCallListenerAdapter(StreamObserver<RespT> streamObserver, CallToStreamObserverAdapter<ReqT> callToStreamObserverAdapter, boolean z) {
            this.observer = streamObserver;
            this.streamingResponse = z;
            this.adapter = callToStreamObserverAdapter;
            if (streamObserver instanceof ClientResponseObserver) {
                ((ClientResponseObserver) streamObserver).beforeStart(callToStreamObserverAdapter);
            }
            callToStreamObserverAdapter.freeze();
        }

        public void onMessage(RespT respt) {
            if (!this.firstResponseReceived || this.streamingResponse) {
                this.firstResponseReceived = true;
                this.observer.onNext(respt);
                if (this.streamingResponse && this.adapter.autoFlowControlEnabled) {
                    this.adapter.request(1);
                    return;
                }
                return;
            }
            throw Status.INTERNAL.withDescription("More than one responses received for unary or client-streaming call").asRuntimeException();
        }

        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                this.observer.onCompleted();
            } else {
                this.observer.onError(status.asRuntimeException(metadata));
            }
        }

        public void onReady() {
            if (this.adapter.onReadyHandler != null) {
                this.adapter.onReadyHandler.run();
            }
        }
    }

    private static final class ThreadlessExecutor implements Executor {
        private static final Logger log = Logger.getLogger(ThreadlessExecutor.class.getName());
        private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue();

        ThreadlessExecutor() {
        }

        public void waitAndDrain() throws InterruptedException {
            Runnable runnable = (Runnable) this.queue.take();
            while (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    log.log(Level.WARNING, "Runnable threw exception", th);
                }
                runnable = (Runnable) this.queue.poll();
            }
        }

        public void execute(Runnable runnable) {
            this.queue.add(runnable);
        }
    }

    private static final class UnaryStreamToFuture<RespT> extends Listener<RespT> {
        private final GrpcFuture<RespT> responseFuture;
        private RespT value;

        public void onHeaders(Metadata metadata) {
        }

        UnaryStreamToFuture(GrpcFuture<RespT> grpcFuture) {
            this.responseFuture = grpcFuture;
        }

        public void onMessage(RespT respt) {
            if (this.value == null) {
                this.value = respt;
                return;
            }
            throw Status.INTERNAL.withDescription("More than one value received for unary call").asRuntimeException();
        }

        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                if (this.value == null) {
                    this.responseFuture.setException(Status.INTERNAL.withDescription("No value received for unary call").asRuntimeException(metadata));
                }
                this.responseFuture.set(this.value);
                return;
            }
            this.responseFuture.setException(status.asRuntimeException(metadata));
        }
    }

    private ClientCalls() {
    }

    public static <ReqT, RespT> void asyncUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver) {
        asyncUnaryRequestCall(clientCall, reqt, streamObserver, false);
    }

    public static <ReqT, RespT> RespT blockingUnaryCall(Channel channel, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, ReqT reqt) {
        ThreadlessExecutor threadlessExecutor = new ThreadlessExecutor();
        ClientCall newCall = channel.newCall(methodDescriptor, callOptions.withExecutor(threadlessExecutor));
        try {
            ListenableFuture futureUnaryCall = futureUnaryCall(newCall, reqt);
            while (!futureUnaryCall.isDone()) {
                threadlessExecutor.waitAndDrain();
            }
            return getUnchecked(futureUnaryCall);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Status.CANCELLED.withDescription("Call was interrupted").withCause(e).asRuntimeException();
        } catch (RuntimeException e2) {
            throw cancelThrow(newCall, e2);
        } catch (Error e3) {
            throw cancelThrow(newCall, e3);
        }
    }

    public static <ReqT, RespT> ListenableFuture<RespT> futureUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        GrpcFuture grpcFuture = new GrpcFuture(clientCall);
        asyncUnaryRequestCall(clientCall, reqt, (Listener<RespT>) new UnaryStreamToFuture<RespT>(grpcFuture), false);
        return grpcFuture;
    }

    private static <V> V getUnchecked(Future<V> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Status.CANCELLED.withDescription("Call was interrupted").withCause(e).asRuntimeException();
        } catch (ExecutionException e2) {
            throw toStatusRuntimeException(e2.getCause());
        }
    }

    private static StatusRuntimeException toStatusRuntimeException(Throwable th) {
        Throwable th2 = (Throwable) Preconditions.checkNotNull(th, "t");
        while (th2 != null) {
            if (th2 instanceof StatusException) {
                StatusException statusException = (StatusException) th2;
                return new StatusRuntimeException(statusException.getStatus(), statusException.getTrailers());
            } else if (th2 instanceof StatusRuntimeException) {
                StatusRuntimeException statusRuntimeException = (StatusRuntimeException) th2;
                return new StatusRuntimeException(statusRuntimeException.getStatus(), statusRuntimeException.getTrailers());
            } else {
                th2 = th2.getCause();
            }
        }
        return Status.UNKNOWN.withDescription("unexpected exception").withCause(th).asRuntimeException();
    }

    private static RuntimeException cancelThrow(ClientCall<?, ?> clientCall, Throwable th) {
        try {
            clientCall.cancel(null, th);
        } catch (Throwable th2) {
            logger.log(Level.SEVERE, "RuntimeException encountered while closing call", th2);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new AssertionError(th);
        }
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver, boolean z) {
        asyncUnaryRequestCall(clientCall, reqt, (Listener<RespT>) new StreamObserverToCallListenerAdapter<RespT>(streamObserver, new CallToStreamObserverAdapter(clientCall), z), z);
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, Listener<RespT> listener, boolean z) {
        startCall(clientCall, listener, z);
        try {
            clientCall.sendMessage(reqt);
            clientCall.halfClose();
        } catch (RuntimeException e) {
            throw cancelThrow(clientCall, e);
        } catch (Error e2) {
            throw cancelThrow(clientCall, e2);
        }
    }

    private static <ReqT, RespT> void startCall(ClientCall<ReqT, RespT> clientCall, Listener<RespT> listener, boolean z) {
        clientCall.start(listener, new Metadata());
        if (z) {
            clientCall.request(1);
        } else {
            clientCall.request(2);
        }
    }
}
