package io.grpc;

import io.grpc.ServiceProviders.PriorityAccessor;
import java.util.Collections;

@Internal
public abstract class ServerProvider {
    private static final ServerProvider provider = ((ServerProvider) ServiceProviders.load(ServerProvider.class, Collections.emptyList(), ServerProvider.class.getClassLoader(), new PriorityAccessor<ServerProvider>() {
        public boolean isAvailable(ServerProvider serverProvider) {
            return serverProvider.isAvailable();
        }

        public int getPriority(ServerProvider serverProvider) {
            return serverProvider.priority();
        }
    }));

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();
}
