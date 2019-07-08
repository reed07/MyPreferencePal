package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.net.SocketAddress;

final class ProxySocketAddress extends SocketAddress {
    private static final long serialVersionUID = -6854992294603212793L;
    private final SocketAddress address;
    private final ProxyParameters proxyParameters;

    @VisibleForTesting
    ProxySocketAddress(SocketAddress socketAddress, ProxyParameters proxyParameters2) {
        this.address = (SocketAddress) Preconditions.checkNotNull(socketAddress);
        this.proxyParameters = (ProxyParameters) Preconditions.checkNotNull(proxyParameters2);
    }

    public ProxyParameters getProxyParameters() {
        return this.proxyParameters;
    }

    public SocketAddress getAddress() {
        return this.address;
    }
}
