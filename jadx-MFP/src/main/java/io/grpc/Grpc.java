package io.grpc;

import io.grpc.Attributes.Key;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketAddress;
import javax.net.ssl.SSLSession;

public final class Grpc {
    @ExperimentalApi
    public static final Key<SocketAddress> TRANSPORT_ATTR_LOCAL_ADDR = Key.create("local-addr");
    @ExperimentalApi
    public static final Key<SocketAddress> TRANSPORT_ATTR_REMOTE_ADDR = Key.create("remote-addr");
    @ExperimentalApi
    public static final Key<SSLSession> TRANSPORT_ATTR_SSL_SESSION = Key.create("ssl-session");

    @ExperimentalApi
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface TransportAttr {
    }

    private Grpc() {
    }
}
