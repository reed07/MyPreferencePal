package io.grpc.util;

import io.grpc.ExperimentalApi;
import io.grpc.HandlerRegistry;
import io.grpc.ServerServiceDefinition;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi
@ThreadSafe
public final class MutableHandlerRegistry extends HandlerRegistry {
    private final ConcurrentMap<String, ServerServiceDefinition> services = new ConcurrentHashMap();
}
