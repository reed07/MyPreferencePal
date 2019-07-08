package com.lightstep.tracer.shared;

import com.mopub.common.Constants;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannelProvider;
import io.grpc.ManagedChannelProvider.ProviderNotFoundException;

public class GrpcCollectorClientProvider extends CollectorClientProvider {
    private static GrpcCollectorClientProvider INSTANCE = new GrpcCollectorClientProvider();

    /* access modifiers changed from: protected */
    public int priority() {
        return 1;
    }

    public GrpcCollectorClientProvider() {
        ManagedChannelProvider.provider();
    }

    /* access modifiers changed from: 0000 */
    public GrpcCollectorClient forOptions(AbstractTracer abstractTracer, Options options) {
        try {
            ManagedChannelBuilder forAddress = ManagedChannelBuilder.forAddress(options.collectorUrl.getHost(), options.collectorUrl.getPort());
            if (options.collectorUrl.getProtocol().equals(Constants.HTTP)) {
                forAddress.usePlaintext(true);
            }
            return new GrpcCollectorClient(abstractTracer, forAddress, options.deadlineMillis);
        } catch (ProviderNotFoundException unused) {
            return null;
        }
    }
}
