package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.NameResolver.Factory;
import java.net.URI;
import javax.annotation.Nullable;

final class OverrideAuthorityNameResolverFactory extends Factory {
    /* access modifiers changed from: private */
    public final String authorityOverride;
    private final Factory delegate;

    OverrideAuthorityNameResolverFactory(Factory factory, String str) {
        this.delegate = factory;
        this.authorityOverride = str;
    }

    @Nullable
    public NameResolver newNameResolver(URI uri, Attributes attributes) {
        NameResolver newNameResolver = this.delegate.newNameResolver(uri, attributes);
        if (newNameResolver == null) {
            return null;
        }
        return new ForwardingNameResolver(newNameResolver) {
            public String getServiceAuthority() {
                return OverrideAuthorityNameResolverFactory.this.authorityOverride;
            }
        };
    }

    public String getDefaultScheme() {
        return this.delegate.getDefaultScheme();
    }
}
