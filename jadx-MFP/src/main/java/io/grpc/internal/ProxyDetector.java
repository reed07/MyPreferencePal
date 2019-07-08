package io.grpc.internal;

import java.io.IOException;
import java.net.SocketAddress;
import javax.annotation.Nullable;

public interface ProxyDetector {
    @Nullable
    ProxyParameters proxyFor(SocketAddress socketAddress) throws IOException;
}
