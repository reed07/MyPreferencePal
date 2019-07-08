package io.grpc.internal;

import com.facebook.stetho.server.http.HttpStatus;
import com.google.android.exoplayer2.C;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import io.grpc.CallOptions;
import io.grpc.CallOptions.Key;
import io.grpc.ClientStreamTracer.Factory;
import io.grpc.InternalLogId;
import io.grpc.InternalMetadata;
import io.grpc.InternalMetadata.TrustedAsciiMarshaller;
import io.grpc.LoadBalancer.PickResult;
import io.grpc.LoadBalancer.Subchannel;
import io.grpc.Metadata;
import io.grpc.Metadata.AsciiMarshaller;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.Status.Code;
import io.grpc.internal.ClientStreamListener.RpcProgress;
import io.grpc.internal.ClientTransport.PingCallback;
import io.grpc.internal.SharedResourceHolder.Resource;
import io.grpc.internal.StreamListener.MessageProducer;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class GrpcUtil {
    public static final Splitter ACCEPT_ENCODING_SPLITTER = Splitter.on(',').trimResults();
    public static final Key<Boolean> CALL_OPTIONS_RPC_OWNED_BY_BALANCER = Key.create("io.grpc.internal.CALL_OPTIONS_RPC_OWNED_BY_BALANCER");
    public static final Metadata.Key<byte[]> CONTENT_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf("accept-encoding", new AcceptEncodingMarshaller());
    public static final Metadata.Key<String> CONTENT_ENCODING_KEY = Metadata.Key.of("content-encoding", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> CONTENT_TYPE_KEY = Metadata.Key.of("content-type", Metadata.ASCII_STRING_MARSHALLER);
    public static final long DEFAULT_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20);
    public static final long DEFAULT_KEEPALIVE_TIME_NANOS = TimeUnit.MINUTES.toNanos(1);
    public static final ProxyDetector DEFAULT_PROXY_DETECTOR = new ProxyDetectorImpl();
    public static final long DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20);
    public static final long DEFAULT_SERVER_KEEPALIVE_TIME_NANOS = TimeUnit.HOURS.toNanos(2);
    public static final boolean IS_RESTRICTED_APPENGINE = (System.getProperty("com.google.appengine.runtime.environment") != null && "1.7".equals(System.getProperty("java.specification.version")));
    public static final Metadata.Key<byte[]> MESSAGE_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf("grpc-accept-encoding", new AcceptEncodingMarshaller());
    public static final Metadata.Key<String> MESSAGE_ENCODING_KEY = Metadata.Key.of("grpc-encoding", Metadata.ASCII_STRING_MARSHALLER);
    public static final ProxyDetector NOOP_PROXY_DETECTOR = new ProxyDetector() {
        @Nullable
        public ProxyParameters proxyFor(SocketAddress socketAddress) {
            return null;
        }
    };
    public static final Resource<Executor> SHARED_CHANNEL_EXECUTOR = new Resource<Executor>() {
        public String toString() {
            return "grpc-default-executor";
        }

        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-default-executor-%d", true));
        }

        public void close(Executor executor) {
            ((ExecutorService) executor).shutdown();
        }
    };
    public static final Supplier<Stopwatch> STOPWATCH_SUPPLIER = new Supplier<Stopwatch>() {
        public Stopwatch get() {
            return Stopwatch.createUnstarted();
        }
    };
    public static final Metadata.Key<String> TE_HEADER = Metadata.Key.of("te", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<Long> TIMEOUT_KEY = Metadata.Key.of("grpc-timeout", (AsciiMarshaller<T>) new TimeoutMarshaller<T>());
    public static final Resource<ScheduledExecutorService> TIMER_SERVICE = new Resource<ScheduledExecutorService>() {
        public ScheduledExecutorService create() {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, GrpcUtil.getThreadFactory("grpc-timer-%d", true));
            try {
                newScheduledThreadPool.getClass().getMethod("setRemoveOnCancelPolicy", new Class[]{Boolean.TYPE}).invoke(newScheduledThreadPool, new Object[]{Boolean.valueOf(true)});
            } catch (NoSuchMethodException unused) {
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
            return Executors.unconfigurableScheduledExecutorService(newScheduledThreadPool);
        }

        public void close(ScheduledExecutorService scheduledExecutorService) {
            scheduledExecutorService.shutdown();
        }
    };
    public static final Metadata.Key<String> USER_AGENT_KEY = Metadata.Key.of("user-agent", Metadata.ASCII_STRING_MARSHALLER);
    public static final Charset US_ASCII = Charset.forName(C.ASCII_NAME);
    private static final Logger log = Logger.getLogger(GrpcUtil.class.getName());

    private static final class AcceptEncodingMarshaller implements TrustedAsciiMarshaller<byte[]> {
        public byte[] parseAsciiString(byte[] bArr) {
            return bArr;
        }

        public byte[] toAsciiString(byte[] bArr) {
            return bArr;
        }

        private AcceptEncodingMarshaller() {
        }
    }

    public enum Http2Error {
        NO_ERROR(0, Status.UNAVAILABLE),
        PROTOCOL_ERROR(1, Status.INTERNAL),
        INTERNAL_ERROR(2, Status.INTERNAL),
        FLOW_CONTROL_ERROR(3, Status.INTERNAL),
        SETTINGS_TIMEOUT(4, Status.INTERNAL),
        STREAM_CLOSED(5, Status.INTERNAL),
        FRAME_SIZE_ERROR(6, Status.INTERNAL),
        REFUSED_STREAM(7, Status.UNAVAILABLE),
        CANCEL(8, Status.CANCELLED),
        COMPRESSION_ERROR(9, Status.INTERNAL),
        CONNECT_ERROR(10, Status.INTERNAL),
        ENHANCE_YOUR_CALM(11, Status.RESOURCE_EXHAUSTED.withDescription("Bandwidth exhausted")),
        INADEQUATE_SECURITY(12, Status.PERMISSION_DENIED.withDescription("Permission denied as protocol is not secure enough to call")),
        HTTP_1_1_REQUIRED(13, Status.UNKNOWN);
        
        private static final Http2Error[] codeMap = null;
        private final int code;
        private final Status status;

        static {
            codeMap = buildHttp2CodeMap();
        }

        private static Http2Error[] buildHttp2CodeMap() {
            Http2Error[] values = values();
            Http2Error[] http2ErrorArr = new Http2Error[(((int) values[values.length - 1].code()) + 1)];
            for (Http2Error http2Error : values) {
                http2ErrorArr[(int) http2Error.code()] = http2Error;
            }
            return http2ErrorArr;
        }

        private Http2Error(int i, Status status2) {
            this.code = i;
            StringBuilder sb = new StringBuilder();
            sb.append("HTTP/2 error code: ");
            sb.append(name());
            this.status = status2.augmentDescription(sb.toString());
        }

        public long code() {
            return (long) this.code;
        }

        public Status status() {
            return this.status;
        }

        public static Http2Error forCode(long j) {
            Http2Error[] http2ErrorArr = codeMap;
            if (j >= ((long) http2ErrorArr.length) || j < 0) {
                return null;
            }
            return http2ErrorArr[(int) j];
        }

        public static Status statusForCode(long j) {
            Http2Error forCode = forCode(j);
            if (forCode != null) {
                return forCode.status();
            }
            Status fromCodeValue = Status.fromCodeValue(INTERNAL_ERROR.status().getCode().value());
            StringBuilder sb = new StringBuilder();
            sb.append("Unrecognized HTTP/2 error code: ");
            sb.append(j);
            return fromCodeValue.withDescription(sb.toString());
        }
    }

    @VisibleForTesting
    static class TimeoutMarshaller implements AsciiMarshaller<Long> {
        TimeoutMarshaller() {
        }

        public String toAsciiString(Long l) {
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            if (l.longValue() < 0) {
                throw new IllegalArgumentException("Timeout too small");
            } else if (l.longValue() < 100000000) {
                StringBuilder sb = new StringBuilder();
                sb.append(l);
                sb.append("n");
                return sb.toString();
            } else if (l.longValue() < 100000000000L) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(timeUnit.toMicros(l.longValue()));
                sb2.append("u");
                return sb2.toString();
            } else if (l.longValue() < 100000000000000L) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(timeUnit.toMillis(l.longValue()));
                sb3.append("m");
                return sb3.toString();
            } else if (l.longValue() < 100000000000000000L) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(timeUnit.toSeconds(l.longValue()));
                sb4.append("S");
                return sb4.toString();
            } else if (l.longValue() < 6000000000000000000L) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(timeUnit.toMinutes(l.longValue()));
                sb5.append(Attributes.MONDAY);
                return sb5.toString();
            } else {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(timeUnit.toHours(l.longValue()));
                sb6.append("H");
                return sb6.toString();
            }
        }

        public Long parseAsciiString(String str) {
            Preconditions.checkArgument(str.length() > 0, "empty timeout");
            Preconditions.checkArgument(str.length() <= 9, "bad timeout format");
            long parseLong = Long.parseLong(str.substring(0, str.length() - 1));
            char charAt = str.charAt(str.length() - 1);
            if (charAt == 'H') {
                return Long.valueOf(TimeUnit.HOURS.toNanos(parseLong));
            }
            if (charAt == 'M') {
                return Long.valueOf(TimeUnit.MINUTES.toNanos(parseLong));
            }
            if (charAt == 'S') {
                return Long.valueOf(TimeUnit.SECONDS.toNanos(parseLong));
            }
            if (charAt == 'u') {
                return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(parseLong));
            }
            switch (charAt) {
                case 'm':
                    return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(parseLong));
                case 'n':
                    return Long.valueOf(parseLong);
                default:
                    throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", new Object[]{Character.valueOf(charAt)}));
            }
        }
    }

    public static boolean shouldBeCountedForInUse(CallOptions callOptions) {
        return !Boolean.TRUE.equals(callOptions.getOption(CALL_OPTIONS_RPC_OWNED_BY_BALANCER));
    }

    public static ProxyDetector getDefaultProxyDetector() {
        if (IS_RESTRICTED_APPENGINE) {
            return NOOP_PROXY_DETECTOR;
        }
        return DEFAULT_PROXY_DETECTOR;
    }

    public static Status httpStatusToGrpcStatus(int i) {
        Status status = httpStatusToGrpcCode(i).toStatus();
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP status code ");
        sb.append(i);
        return status.withDescription(sb.toString());
    }

    private static Code httpStatusToGrpcCode(int i) {
        if (i >= 100 && i < 200) {
            return Code.INTERNAL;
        }
        switch (i) {
            case WARNING_VALUE:
            case 431:
                return Code.INTERNAL;
            case 401:
                return Code.UNAUTHENTICATED;
            case 403:
                return Code.PERMISSION_DENIED;
            case HttpStatus.HTTP_NOT_FOUND /*404*/:
                return Code.UNIMPLEMENTED;
            case 429:
            case 502:
            case 503:
            case 504:
                return Code.UNAVAILABLE;
            default:
                return Code.UNKNOWN;
        }
    }

    public static boolean isGrpcContentType(String str) {
        boolean z = false;
        if (str == null || 16 > str.length()) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith("application/grpc")) {
            return false;
        }
        if (lowerCase.length() == 16) {
            return true;
        }
        char charAt = lowerCase.charAt(16);
        if (charAt == '+' || charAt == ';') {
            z = true;
        }
        return z;
    }

    public static String getGrpcUserAgent(String str, @Nullable String str2) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append(str2);
            sb.append(' ');
        }
        sb.append("grpc-java-");
        sb.append(str);
        sb.append('/');
        sb.append("1.17.1");
        return sb.toString();
    }

    public static URI authorityToUri(String str) {
        Preconditions.checkNotNull(str, "authority");
        try {
            URI uri = new URI(null, str, null, null, null);
            return uri;
        } catch (URISyntaxException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid authority: ");
            sb.append(str);
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public static String authorityFromHostAndPort(String str, int i) {
        try {
            URI uri = new URI(null, null, str, i, null, null, null);
            return uri.getAuthority();
        } catch (URISyntaxException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid host or port: ");
            sb.append(str);
            sb.append(" ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public static ThreadFactory getThreadFactory(String str, boolean z) {
        if (IS_RESTRICTED_APPENGINE) {
            return MoreExecutors.platformThreadFactory();
        }
        return new ThreadFactoryBuilder().setDaemon(z).setNameFormat(str).build();
    }

    public static String getHost(InetSocketAddress inetSocketAddress) {
        try {
            return (String) InetSocketAddress.class.getMethod("getHostString", new Class[0]).invoke(inetSocketAddress, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return inetSocketAddress.getHostName();
        }
    }

    @Nullable
    static ClientTransport getTransportFromPickResult(PickResult pickResult, boolean z) {
        Subchannel subchannel = pickResult.getSubchannel();
        final ClientTransport obtainActiveTransport = subchannel != null ? ((AbstractSubchannel) subchannel).obtainActiveTransport() : null;
        if (obtainActiveTransport != null) {
            final Factory streamTracerFactory = pickResult.getStreamTracerFactory();
            if (streamTracerFactory == null) {
                return obtainActiveTransport;
            }
            return new ClientTransport() {
                public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
                    return ClientTransport.this.newStream(methodDescriptor, metadata, callOptions.withStreamTracerFactory(streamTracerFactory));
                }

                public void ping(PingCallback pingCallback, Executor executor) {
                    ClientTransport.this.ping(pingCallback, executor);
                }

                public InternalLogId getLogId() {
                    return ClientTransport.this.getLogId();
                }
            };
        }
        if (!pickResult.getStatus().isOk()) {
            if (pickResult.isDrop()) {
                return new FailingClientTransport(pickResult.getStatus(), RpcProgress.DROPPED);
            }
            if (!z) {
                return new FailingClientTransport(pickResult.getStatus(), RpcProgress.PROCESSED);
            }
        }
        return null;
    }

    static void closeQuietly(MessageProducer messageProducer) {
        while (true) {
            InputStream next = messageProducer.next();
            if (next != null) {
                closeQuietly(next);
            } else {
                return;
            }
        }
    }

    public static void closeQuietly(@Nullable InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.log(Level.WARNING, "exception caught in closeQuietly", e);
            }
        }
    }

    private GrpcUtil() {
    }
}
