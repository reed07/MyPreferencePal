package io.grpc;

@Internal
public final class InternalServiceProviders {

    public interface PriorityAccessor<T> extends io.grpc.ServiceProviders.PriorityAccessor<T> {
    }

    private InternalServiceProviders() {
    }

    public static boolean isAndroid(ClassLoader classLoader) {
        return ServiceProviders.isAndroid(classLoader);
    }
}
