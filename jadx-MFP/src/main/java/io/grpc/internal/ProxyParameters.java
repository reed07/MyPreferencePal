package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.net.InetSocketAddress;
import javax.annotation.Nullable;

public final class ProxyParameters {
    @Nullable
    public final String password;
    public final InetSocketAddress proxyAddress;
    @Nullable
    public final String username;

    public ProxyParameters(InetSocketAddress inetSocketAddress, @Nullable String str, @Nullable String str2) {
        Preconditions.checkNotNull(inetSocketAddress);
        Preconditions.checkState(!inetSocketAddress.isUnresolved());
        this.proxyAddress = inetSocketAddress;
        this.username = str;
        this.password = str2;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof ProxyParameters)) {
            return false;
        }
        ProxyParameters proxyParameters = (ProxyParameters) obj;
        if (Objects.equal(this.proxyAddress, proxyParameters.proxyAddress) && Objects.equal(this.username, proxyParameters.username) && Objects.equal(this.password, proxyParameters.password)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.proxyAddress, this.username, this.password);
    }
}
