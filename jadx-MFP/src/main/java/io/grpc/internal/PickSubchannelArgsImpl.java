package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.grpc.CallOptions;
import io.grpc.LoadBalancer.PickSubchannelArgs;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

final class PickSubchannelArgsImpl extends PickSubchannelArgs {
    private final CallOptions callOptions;
    private final Metadata headers;
    private final MethodDescriptor<?, ?> method;

    PickSubchannelArgsImpl(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions2) {
        this.method = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, Param.METHOD);
        this.headers = (Metadata) Preconditions.checkNotNull(metadata, "headers");
        this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions");
    }

    public Metadata getHeaders() {
        return this.headers;
    }

    public CallOptions getCallOptions() {
        return this.callOptions;
    }

    public MethodDescriptor<?, ?> getMethodDescriptor() {
        return this.method;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PickSubchannelArgsImpl pickSubchannelArgsImpl = (PickSubchannelArgsImpl) obj;
        if (!Objects.equal(this.callOptions, pickSubchannelArgsImpl.callOptions) || !Objects.equal(this.headers, pickSubchannelArgsImpl.headers) || !Objects.equal(this.method, pickSubchannelArgsImpl.method)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.callOptions, this.headers, this.method);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[method=");
        sb.append(this.method);
        sb.append(" headers=");
        sb.append(this.headers);
        sb.append(" callOptions=");
        sb.append(this.callOptions);
        sb.append("]");
        return sb.toString();
    }
}
