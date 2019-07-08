package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ClientCall.Listener;
import io.grpc.MethodDescriptor.Marshaller;
import java.util.Arrays;
import java.util.List;

public class ClientInterceptors {
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        public void cancel(String str, Throwable th) {
        }

        public void halfClose() {
        }

        public void request(int i) {
        }

        public void sendMessage(Object obj) {
        }

        public void start(Listener<Object> listener, Metadata metadata) {
        }
    };

    /* renamed from: io.grpc.ClientInterceptors$1 reason: invalid class name */
    class AnonymousClass1 implements ClientInterceptor {
        final /* synthetic */ ClientInterceptor val$interceptor;
        final /* synthetic */ Marshaller val$reqMarshaller;
        final /* synthetic */ Marshaller val$respMarshaller;

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            final ClientCall interceptCall = this.val$interceptor.interceptCall(methodDescriptor.toBuilder(this.val$reqMarshaller, this.val$respMarshaller).build(), callOptions, channel);
            return new PartialForwardingClientCall<ReqT, RespT>() {
                public void start(final Listener<RespT> listener, Metadata metadata) {
                    interceptCall.start(new PartialForwardingClientCallListener<WRespT>() {
                        public void onMessage(WRespT wrespt) {
                            listener.onMessage(methodDescriptor.getResponseMarshaller().parse(AnonymousClass1.this.val$respMarshaller.stream(wrespt)));
                        }

                        /* access modifiers changed from: protected */
                        public Listener<?> delegate() {
                            return listener;
                        }
                    }, metadata);
                }

                public void sendMessage(ReqT reqt) {
                    interceptCall.sendMessage(AnonymousClass1.this.val$reqMarshaller.parse(methodDescriptor.getRequestMarshaller().stream(reqt)));
                }

                /* access modifiers changed from: protected */
                public ClientCall<?, ?> delegate() {
                    return interceptCall;
                }
            };
        }
    }

    public static abstract class CheckedForwardingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
    }

    private static class InterceptorChannel extends Channel {
        private final Channel channel;
        private final ClientInterceptor interceptor;

        /* synthetic */ InterceptorChannel(Channel channel2, ClientInterceptor clientInterceptor, AnonymousClass1 r3) {
            this(channel2, clientInterceptor);
        }

        private InterceptorChannel(Channel channel2, ClientInterceptor clientInterceptor) {
            this.channel = channel2;
            this.interceptor = (ClientInterceptor) Preconditions.checkNotNull(clientInterceptor, "interceptor");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            return this.interceptor.interceptCall(methodDescriptor, callOptions, this.channel);
        }

        public String authority() {
            return this.channel.authority();
        }
    }

    private ClientInterceptors() {
    }

    public static Channel intercept(Channel channel, ClientInterceptor... clientInterceptorArr) {
        return intercept(channel, Arrays.asList(clientInterceptorArr));
    }

    public static Channel intercept(Channel channel, List<? extends ClientInterceptor> list) {
        Preconditions.checkNotNull(channel, "channel");
        for (ClientInterceptor interceptorChannel : list) {
            channel = new InterceptorChannel(channel, interceptorChannel, null);
        }
        return channel;
    }
}
