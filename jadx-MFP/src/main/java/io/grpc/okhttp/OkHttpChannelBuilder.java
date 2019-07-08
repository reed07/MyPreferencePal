package io.grpc.okhttp;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.myfitnesspal.shared.constants.Constants.BusyStates;
import com.squareup.okhttp.CipherSuite;
import com.squareup.okhttp.ConnectionSpec;
import com.squareup.okhttp.ConnectionSpec.Builder;
import com.squareup.okhttp.TlsVersion;
import io.grpc.Attributes;
import io.grpc.ExperimentalApi;
import io.grpc.Internal;
import io.grpc.NameResolver;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.AtomicBackoff;
import io.grpc.internal.AtomicBackoff.State;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ClientTransportFactory.ClientTransportOptions;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.SharedResourceHolder.Resource;
import io.grpc.internal.TransportTracer.Factory;
import io.grpc.okhttp.internal.Platform;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

@ExperimentalApi
public class OkHttpChannelBuilder extends AbstractManagedChannelImplBuilder<OkHttpChannelBuilder> {
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    @Deprecated
    public static final ConnectionSpec DEFAULT_CONNECTION_SPEC = new Builder(ConnectionSpec.MODERN_TLS).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384).tlsVersions(TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
    @VisibleForTesting
    static final io.grpc.okhttp.internal.ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC = new io.grpc.okhttp.internal.ConnectionSpec.Builder(io.grpc.okhttp.internal.ConnectionSpec.MODERN_TLS).cipherSuites(io.grpc.okhttp.internal.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, io.grpc.okhttp.internal.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, io.grpc.okhttp.internal.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, io.grpc.okhttp.internal.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, io.grpc.okhttp.internal.CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, io.grpc.okhttp.internal.CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, io.grpc.okhttp.internal.CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, io.grpc.okhttp.internal.CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384).tlsVersions(io.grpc.okhttp.internal.TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
    /* access modifiers changed from: private */
    public static final Resource<Executor> SHARED_EXECUTOR = new Resource<Executor>() {
        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
        }

        public void close(Executor executor) {
            ((ExecutorService) executor).shutdown();
        }
    };
    private io.grpc.okhttp.internal.ConnectionSpec connectionSpec;
    private int flowControlWindow;
    private HostnameVerifier hostnameVerifier;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private int maxInboundMetadataSize;
    private NegotiationType negotiationType;
    private ScheduledExecutorService scheduledExecutorService;
    private SSLSocketFactory sslSocketFactory;
    private Executor transportExecutor;

    private enum NegotiationType {
        TLS,
        PLAINTEXT
    }

    @Internal
    static final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final io.grpc.okhttp.internal.ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        private final Executor executor;
        private final int flowControlWindow;
        @Nullable
        private final HostnameVerifier hostnameVerifier;
        private final AtomicBackoff keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        private final int maxInboundMetadataSize;
        private final int maxMessageSize;
        @Nullable
        private final SSLSocketFactory socketFactory;
        private final ScheduledExecutorService timeoutService;
        private final Factory transportTracerFactory;
        private final boolean usingSharedExecutor;
        private final boolean usingSharedScheduler;

        private OkHttpTransportFactory(Executor executor2, @Nullable ScheduledExecutorService scheduledExecutorService, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier2, io.grpc.okhttp.internal.ConnectionSpec connectionSpec2, int i, boolean z, long j, long j2, int i2, boolean z2, int i3, Factory factory) {
            Executor executor3 = executor2;
            boolean z3 = true;
            this.usingSharedScheduler = scheduledExecutorService == null;
            this.timeoutService = this.usingSharedScheduler ? (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE) : scheduledExecutorService;
            this.socketFactory = sSLSocketFactory;
            this.hostnameVerifier = hostnameVerifier2;
            this.connectionSpec = connectionSpec2;
            this.maxMessageSize = i;
            this.enableKeepAlive = z;
            this.keepAliveTimeNanos = new AtomicBackoff("keepalive time nanos", j);
            this.keepAliveTimeoutNanos = j2;
            this.flowControlWindow = i2;
            this.keepAliveWithoutCalls = z2;
            this.maxInboundMetadataSize = i3;
            if (executor3 != null) {
                z3 = false;
            }
            this.usingSharedExecutor = z3;
            this.transportTracerFactory = (Factory) Preconditions.checkNotNull(factory, "transportTracerFactory");
            if (this.usingSharedExecutor) {
                this.executor = (Executor) SharedResourceHolder.get(OkHttpChannelBuilder.SHARED_EXECUTOR);
            } else {
                this.executor = executor3;
            }
        }

        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions) {
            if (!this.closed) {
                final State state = this.keepAliveTimeNanos.getState();
                OkHttpClientTransport okHttpClientTransport = new OkHttpClientTransport((InetSocketAddress) socketAddress, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent(), this.executor, this.socketFactory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.flowControlWindow, clientTransportOptions.getProxyParameters(), new Runnable() {
                    public void run() {
                        state.backoff();
                    }
                }, this.maxInboundMetadataSize, this.transportTracerFactory.create());
                if (this.enableKeepAlive) {
                    okHttpClientTransport.enableKeepAlive(true, state.get(), this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
                }
                return okHttpClientTransport;
            }
            throw new IllegalStateException("The transport factory is closed.");
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timeoutService;
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.usingSharedScheduler) {
                    SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timeoutService);
                }
                if (this.usingSharedExecutor) {
                    SharedResourceHolder.release(OkHttpChannelBuilder.SHARED_EXECUTOR, this.executor);
                }
            }
        }
    }

    public static OkHttpChannelBuilder forAddress(String str, int i) {
        return new OkHttpChannelBuilder(str, i);
    }

    protected OkHttpChannelBuilder(String str, int i) {
        this(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    private OkHttpChannelBuilder(String str) {
        super(str);
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = BusyStates.ALL;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
    }

    @Deprecated
    public final OkHttpChannelBuilder negotiationType(NegotiationType negotiationType2) {
        Preconditions.checkNotNull(negotiationType2, "type");
        switch (negotiationType2) {
            case TLS:
                this.negotiationType = NegotiationType.TLS;
                break;
            case PLAINTEXT:
                this.negotiationType = NegotiationType.PLAINTEXT;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown negotiation type: ");
                sb.append(negotiationType2);
                throw new AssertionError(sb.toString());
        }
        return this;
    }

    @Deprecated
    public final OkHttpChannelBuilder usePlaintext(boolean z) {
        if (z) {
            negotiationType(NegotiationType.PLAINTEXT);
            return this;
        }
        throw new IllegalArgumentException("Plaintext negotiation not currently supported");
    }

    /* access modifiers changed from: protected */
    @Internal
    public final ClientTransportFactory buildTransportFactory() {
        OkHttpTransportFactory okHttpTransportFactory = r2;
        OkHttpTransportFactory okHttpTransportFactory2 = new OkHttpTransportFactory(this.transportExecutor, this.scheduledExecutorService, createSocketFactory(), this.hostnameVerifier, this.connectionSpec, maxInboundMessageSize(), this.keepAliveTimeNanos != Long.MAX_VALUE, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory);
        return okHttpTransportFactory;
    }

    /* access modifiers changed from: protected */
    public Attributes getNameResolverParams() {
        int i;
        switch (this.negotiationType) {
            case PLAINTEXT:
                i = 80;
                break;
            case TLS:
                i = 443;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append(this.negotiationType);
                sb.append(" not handled");
                throw new AssertionError(sb.toString());
        }
        return Attributes.newBuilder().set(NameResolver.Factory.PARAMS_DEFAULT_PORT, Integer.valueOf(i)).build();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public SSLSocketFactory createSocketFactory() {
        SSLContext sSLContext;
        switch (this.negotiationType) {
            case PLAINTEXT:
                return null;
            case TLS:
                try {
                    if (this.sslSocketFactory == null) {
                        if (GrpcUtil.IS_RESTRICTED_APPENGINE) {
                            sSLContext = SSLContext.getInstance("TLS", Platform.get().getProvider());
                            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                            instance.init(null);
                            sSLContext.init(null, instance.getTrustManagers(), SecureRandom.getInstance("SHA1PRNG", Platform.get().getProvider()));
                        } else {
                            sSLContext = SSLContext.getInstance("Default", Platform.get().getProvider());
                        }
                        this.sslSocketFactory = sSLContext.getSocketFactory();
                    }
                    return this.sslSocketFactory;
                } catch (GeneralSecurityException e) {
                    throw new RuntimeException("TLS Provider failure", e);
                }
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown negotiation type: ");
                sb.append(this.negotiationType);
                throw new RuntimeException(sb.toString());
        }
    }
}
