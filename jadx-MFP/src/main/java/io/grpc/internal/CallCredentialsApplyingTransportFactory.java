package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Attributes.Builder;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.internal.ClientTransportFactory.ClientTransportOptions;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

final class CallCredentialsApplyingTransportFactory implements ClientTransportFactory {
    /* access modifiers changed from: private */
    public final Executor appExecutor;
    private final ClientTransportFactory delegate;

    private class CallCredentialsApplyingTransport extends ForwardingConnectionClientTransport {
        private final String authority;
        private final ConnectionClientTransport delegate;

        CallCredentialsApplyingTransport(ConnectionClientTransport connectionClientTransport, String str) {
            this.delegate = (ConnectionClientTransport) Preconditions.checkNotNull(connectionClientTransport, "delegate");
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        /* access modifiers changed from: protected */
        public ConnectionClientTransport delegate() {
            return this.delegate;
        }

        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
            CallCredentials credentials = callOptions.getCredentials();
            if (credentials == null) {
                return this.delegate.newStream(methodDescriptor, metadata, callOptions);
            }
            MetadataApplierImpl metadataApplierImpl = new MetadataApplierImpl(this.delegate, methodDescriptor, metadata, callOptions);
            Builder all = Attributes.newBuilder().set(CallCredentials.ATTR_AUTHORITY, this.authority).set(CallCredentials.ATTR_SECURITY_LEVEL, SecurityLevel.NONE).setAll(this.delegate.getAttributes());
            if (callOptions.getAuthority() != null) {
                all.set(CallCredentials.ATTR_AUTHORITY, callOptions.getAuthority());
            }
            try {
                credentials.applyRequestMetadata(methodDescriptor, all.build(), (Executor) MoreObjects.firstNonNull(callOptions.getExecutor(), CallCredentialsApplyingTransportFactory.this.appExecutor), metadataApplierImpl);
            } catch (Throwable th) {
                metadataApplierImpl.fail(Status.UNAUTHENTICATED.withDescription("Credentials should use fail() instead of throwing exceptions").withCause(th));
            }
            return metadataApplierImpl.returnStream();
        }
    }

    CallCredentialsApplyingTransportFactory(ClientTransportFactory clientTransportFactory, Executor executor) {
        this.delegate = (ClientTransportFactory) Preconditions.checkNotNull(clientTransportFactory, "delegate");
        this.appExecutor = (Executor) Preconditions.checkNotNull(executor, "appExecutor");
    }

    public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions) {
        return new CallCredentialsApplyingTransport(this.delegate.newClientTransport(socketAddress, clientTransportOptions), clientTransportOptions.getAuthority());
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return this.delegate.getScheduledExecutorService();
    }

    public void close() {
        this.delegate.close();
    }
}
