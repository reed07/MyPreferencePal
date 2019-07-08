package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import io.grpc.Attributes;
import io.grpc.InternalServiceProviders;
import io.grpc.NameResolverProvider;
import java.net.URI;

public final class DnsNameResolverProvider extends NameResolverProvider {
    public String getDefaultScheme() {
        return "dns";
    }

    /* access modifiers changed from: protected */
    public boolean isAvailable() {
        return true;
    }

    /* access modifiers changed from: protected */
    public int priority() {
        return 5;
    }

    public DnsNameResolver newNameResolver(URI uri, Attributes attributes) {
        if (!"dns".equals(uri.getScheme())) {
            return null;
        }
        String str = (String) Preconditions.checkNotNull(uri.getPath(), "targetPath");
        Preconditions.checkArgument(str.startsWith("/"), "the path component (%s) of the target (%s) must start with '/'", (Object) str, (Object) uri);
        DnsNameResolver dnsNameResolver = new DnsNameResolver(uri.getAuthority(), str.substring(1), attributes, GrpcUtil.SHARED_CHANNEL_EXECUTOR, GrpcUtil.getDefaultProxyDetector(), Stopwatch.createUnstarted(), InternalServiceProviders.isAndroid(getClass().getClassLoader()));
        return dnsNameResolver;
    }
}
