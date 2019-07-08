package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

public interface ClientTransportFactory extends Closeable {

    public static final class ClientTransportOptions {
        private String authority = "unknown-authority";
        private Attributes eagAttributes = Attributes.EMPTY;
        @Nullable
        private ProxyParameters proxyParameters;
        @Nullable
        private String userAgent;

        public String getAuthority() {
            return this.authority;
        }

        public ClientTransportOptions setAuthority(String str) {
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
            return this;
        }

        public ClientTransportOptions setEagAttributes(Attributes attributes) {
            Preconditions.checkNotNull(attributes, "eagAttributes");
            this.eagAttributes = attributes;
            return this;
        }

        @Nullable
        public String getUserAgent() {
            return this.userAgent;
        }

        public ClientTransportOptions setUserAgent(@Nullable String str) {
            this.userAgent = str;
            return this;
        }

        @Nullable
        public ProxyParameters getProxyParameters() {
            return this.proxyParameters;
        }

        public ClientTransportOptions setProxyParameters(@Nullable ProxyParameters proxyParameters2) {
            this.proxyParameters = proxyParameters2;
            return this;
        }

        public int hashCode() {
            return Objects.hashCode(this.authority, this.eagAttributes, this.userAgent, this.proxyParameters);
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof ClientTransportOptions)) {
                return false;
            }
            ClientTransportOptions clientTransportOptions = (ClientTransportOptions) obj;
            if (this.authority.equals(clientTransportOptions.authority) && this.eagAttributes.equals(clientTransportOptions.eagAttributes) && Objects.equal(this.userAgent, clientTransportOptions.userAgent) && Objects.equal(this.proxyParameters, clientTransportOptions.proxyParameters)) {
                z = true;
            }
            return z;
        }
    }

    void close();

    ScheduledExecutorService getScheduledExecutorService();

    ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions);
}
