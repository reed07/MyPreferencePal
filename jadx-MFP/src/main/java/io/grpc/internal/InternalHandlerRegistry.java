package io.grpc.internal;

import io.grpc.HandlerRegistry;
import io.grpc.ServerServiceDefinition;
import java.util.HashMap;
import java.util.LinkedHashMap;

final class InternalHandlerRegistry extends HandlerRegistry {

    static final class Builder {
        private final HashMap<String, ServerServiceDefinition> services = new LinkedHashMap();

        Builder() {
        }
    }
}
