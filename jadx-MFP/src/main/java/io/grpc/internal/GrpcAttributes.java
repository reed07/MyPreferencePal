package io.grpc.internal;

import io.grpc.Attributes.Key;
import io.grpc.CallCredentials;
import io.grpc.SecurityLevel;
import java.util.Map;

public final class GrpcAttributes {
    public static final Key<String> ATTR_LB_ADDR_AUTHORITY = Key.create("io.grpc.grpclb.lbAddrAuthority");
    public static final Key<Boolean> ATTR_LB_PROVIDED_BACKEND = Key.create("io.grpc.grpclb.lbProvidedBackend");
    public static final Key<SecurityLevel> ATTR_SECURITY_LEVEL = CallCredentials.ATTR_SECURITY_LEVEL;
    public static final Key<Map<String, Object>> NAME_RESOLVER_SERVICE_CONFIG = Key.create("service-config");

    private GrpcAttributes() {
    }
}
